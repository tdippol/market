package com.axiante.mui.persistence.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.utility.AdminModeUtils;
import com.axiante.mui.persistence.dao.ApplicationPropertiesDAO;
import com.axiante.mui.persistence.entity.ApplicationPropertiesEntity;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.service.ApplicationPropertiesService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class ApplicationPropertiesServiceImpl implements ApplicationPropertiesService {
    private static final long serialVersionUID = 4313432411961082558L;

    @Inject
    ApplicationPropertiesDAO dao;

    AdminModeUtils utils = new AdminModeUtils();

    @Override
    public ApplicationPropertiesEntity saveProperty(ApplicationPropertiesEntity entity) {
        try {
            entity = dao.persist(entity);
        } catch (Exception e) {
            log.error("error saving property " + entity.getKey(), e);
        }
        return entity;
    }

    @Override
    public boolean saveProperty(ApplicationPropertiesEntity entity, String newValue) {
        boolean result = false;
        try {
            entity.setValue(newValue);
            dao.persist(entity);
            result = true;
        } catch (Exception e) {
            log.error("error saving property " + entity.getKey(), e);
        }
        return result;
    }

    @Override
    public ApplicationPropertiesEntity getPropertiesEntity(String key) {
        ApplicationPropertiesEntity entity = null;
        try {
            entity = dao.findByCode(key);
        } catch (Exception e) {
            log.warn("error getting property [" + key + "] from database", e);
        }
        return entity;
    }

    @Override
    public boolean calculateAdminMode() {
        boolean result = false;
        try {
            if (isManualAdminOn()) {
                result = true;
            } else // scheduler active ?
            if (isAdminSchedulerOn()) {
                // are we in the correct day of week ?
                Boolean allDays = isAllDaysOn();
                result = utils.isCheckDay(LocalDateTime.now().getDayOfWeek().getValue(), allDays,
                        allDays ? null : getSelectedDays());
                if (result) {
                    // are we in the correct time?
                    String start = getOrCreateProperty(ApplicationProperties.ADMIN_MODE_FROM_TIME,
                            ApplicationProperties.DEFAULT_ADMIN_MODE_FROM_TIME).getValue();
                    String end = getOrCreateProperty(ApplicationProperties.ADMIN_MODE_TO_TIME,
                            ApplicationProperties.DEFAULT_ADMIN_MODE_TO_TIME).getValue();
                    result = utils.isTimeBetween(LocalDateTime.now(), start, end);
                }
            }
        } catch (Exception e) {
            log.error("error calculating admin mode", e);
        }

        return result;
    }

    @Override
    public ApplicationPropertiesEntity getOrCreateProperty(@NonNull final String key, final String value) {
        ApplicationPropertiesEntity entity = null;
        try {
            entity = dao.findByCode(key);
        } catch (IllegalStateException e) {
            try {
                // ci sono + elementi di quelli che mi aspetto: torno l'ultimo. L'errore e' gia'
                // loggato
                List<ApplicationPropertiesEntity> list = dao.findAnyByCode(key);
                list.sort(Comparator.comparing(ApplicationPropertiesEntity::getIdApplicationProperties));
                entity = list.get(list.size() - 1);
                list = null;
            } catch (Exception ex) {
                log.error("error retrieving list of data for attribute " + key, ex);
                return null;
            }
        } catch (Exception e) {
            log.warn(String.format("unhandled error retrieving property [ %s ] from database", key), e);
            return null;
        }
        if (entity == null) {
            log.info(String.format("property %s not set in db: creating with value %s", key, value));
            entity = new ApplicationPropertiesEntity();
            entity.setKey(key);
            entity.setValue(value);
            try {
                entity = dao.persist(entity);
            } catch (Exception e) {
                log.error("Error saving new property " + key, e);
            }
        }
        return entity;
    }

    protected boolean isManualAdminOn() throws Exception {
        ApplicationPropertiesEntity manualMode = getOrCreateProperty(ApplicationProperties.MANUAL_ADMIN_MODE,
                ApplicationProperties.DEFAULT_MANUAL_ADMIN_MODE.toString());
        return Boolean.valueOf(manualMode.getValue());
    }

    protected boolean isAdminSchedulerOn() throws Exception {
        ApplicationPropertiesEntity scheduler = getOrCreateProperty(ApplicationProperties.ADMIN_MODE_SCHEDULER_ACTIVE,
                ApplicationProperties.DEFAULT_ADMIN_MODE_SCHEDULER_ACTIVE.toString());
        return Boolean.valueOf(scheduler.getValue());
    }

    protected boolean isAllDaysOn() throws Exception {
        ApplicationPropertiesEntity entity = getOrCreateProperty(ApplicationProperties.ADMIN_MODE_ALL_DAYS,
                ApplicationProperties.DEFAULT_ADMIN_MODE_ALL_DAYS.toString());
        return Boolean.valueOf(entity.getValue());
    }

    protected List<Integer> getSelectedDays() throws Exception {
        ApplicationPropertiesEntity entity = getOrCreateProperty(ApplicationProperties.ADMIN_MODE_SELECTED_DAYS, null);
        List<Integer> selectedDays = null;
        if ((entity != null) && (entity.getValue() != null)) {
            selectedDays = Arrays.asList(entity.getValue().split(",")).stream().map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        return selectedDays;
    }

    @Override
    public List<ApplicationPropertiesEntity> findAll() {
        return dao.readAll();
    }

    @Override
    public List<ApplicationPropertiesEntity> findAnyById(String key) throws Exception {
        return dao.findAnyByCode(key);
    }

    @Override
    public boolean hasAccessAsAdmin(final RolesEntity role) {
        if ((role != null) && !role.isAdmin()) {
            return role.getOverrideAdmin();
        }
        return true;
    }

    @Override
    public boolean hasAccessAsAdmin(final Set<RolesEntity> roles) {
        if (roles != null) {
            return roles.stream().filter(Objects::nonNull).map(this::hasAccessAsAdmin).filter(b -> b == true)
                    .findFirst().orElse(Boolean.FALSE);
        }
        return true;
    }

}
