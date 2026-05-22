package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.dao.CfgSetPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgSetPianificazioneService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Dependent
@Transactional
@Slf4j
public class CfgSetPianificazioneServiceImpl extends AbstractDbPromoService<CfgSetPianificazioneEntity>
        implements CfgSetPianificazioneService {
    private static final long serialVersionUID = -6542589035256048229L;

    @Inject
    @Getter
    private CfgSetPianificazioneDAO dao;

    @Override
    public List<CfgSetPianificazioneEntity> getAllEnabled() {
        return getDao().findAllEnabled();
    }

    /**
     * Returns the active set, the one with end date equals to null There can be
     * only one (at most) active set
     *
     * @return the active set, or null if there isn't one
     * @throws RuntimeException if there are two or more, or all set (if any) have
     *                          end date not null
     */
    @Override
    public CfgSetPianificazioneEntity findOpenSet() throws RuntimeException {
        final CfgSetPianificazioneEntity entity = getDao().findOpenSet();
        if (entity != null) {
            return entity;
        }
        final List<CfgSetPianificazioneEntity> allEnabled = getDao().findAllEnabled();
        if (!allEnabled.isEmpty()) {
            throw new RuntimeException("There are no set with an end date equals to null");
        }
        return null;
    }

    public CfgSetPianificazioneEntity findById(long idSetPianificazione) {
        return getDao().findById(idSetPianificazione);
    }

    @Override
    public CfgSetPianificazioneEntity duplicateSet(
            CfgSetPianificazioneEntity openSet, @NonNull CfgSetPianificazioneEntity pianificazione,
            String description, Date startDate, String user) {
        if (openSet == null) {
            log.error("You're trying to duplicate a SetPianificazione without a valid open SetPianificazione!");
            throw new RuntimeException("Unable to duplicate given set");
        }
        final Date endDate = calculateEndDate(startDate);
        if (isInvalidEndDateForSet(openSet, endDate)) {
            log.error(
                    "You're trying to set end date for SetPianificazione before to the max start date for Promotions associated!");
            throw new RuntimeException("Unable to duplicate given set");
        }
        final CfgSetPianificazioneEntity entity = prepareNewSet(description, startDate, user);
        openSet.setDataFine(endDate);
        openSet.setCodiceUtenteAggiornamento(user);
        openSet.setDataAggiornamento(new Date());
        final List<CfgSetPianificazioneEntity> entities = Arrays.asList(entity, openSet);
        getDao().persist(entities);
        return entities.get(0);
    }

    @Override
    public CfgSetPianificazioneEntity createSet(CfgSetPianificazioneEntity openSet, String description, Date startDate,
                                                String user) {
        final CfgSetPianificazioneEntity entity = prepareNewSet(description, startDate, user);
        if (openSet != null) {
            final Date endDate = calculateEndDate(startDate);
            if (isInvalidEndDateForSet(openSet, endDate)) {
                log.error(
                        "You're trying to set end date for SetPianificazione before to the max start date for Promotions associated!");
                throw new RuntimeException("Unable to create given set");
            }
            openSet.setDataFine(calculateEndDate(startDate));
            openSet.setCodiceUtenteAggiornamento(user);
            openSet.setDataAggiornamento(new Date());
        }
        final List<CfgSetPianificazioneEntity> entities = openSet != null ? Arrays.asList(entity, openSet)
                : Collections.singletonList(entity);
        getDao().persist(entities);
        return entities.get(0);
    }

    @Override
    public CfgSetPianificazioneEntity persist(CfgSetPianificazioneEntity cfgSetPianificazione, String name) {
        return getDao()
                .persist((CfgSetPianificazioneEntity) AuditLogFiller.fillAuditLogFields(cfgSetPianificazione, name));

    }

    private Date calculateEndDate(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    private CfgSetPianificazioneEntity prepareNewSet(String description, Date startDate, String user) {
        final CfgSetPianificazioneEntity entity = (CfgSetPianificazioneEntity) AuditLogFiller
                .fillAuditLogFields(new CfgSetPianificazioneEntity(), user);
        entity.setDescrizione(description);
        entity.setDataInizio(startDate);
        return entity;
    }

    private boolean isInvalidEndDateForSet(CfgSetPianificazioneEntity openSet, Date endDate) {
        final Set<PromozioneTestataEntity> testataEntities = openSet.getPromozioneTestataEntities();
        if (testataEntities != null && !testataEntities.isEmpty()) {
            final Date maxDate = testataEntities.stream()
                    .max(Comparator.comparing(PromozioneTestataEntity::getDataInizio)).get().getDataInizio();
            return !endDate.after(maxDate);
        }
        return false;
    }

}
