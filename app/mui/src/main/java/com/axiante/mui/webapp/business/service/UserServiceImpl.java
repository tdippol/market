package com.axiante.mui.webapp.business.service;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.MuiContext;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.business.data.UserDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class UserServiceImpl implements UserService {

    private static final long serialVersionUID = 2528887945774875010L;

    @Inject
    CanalePromozioneService service;

    @Inject
    private Instance<MuiService> muiServiceInstance;

    transient private static final Object lock = new Object();

    /**
     * Deprecated: use {@link UserServiceImpl#asDto(UsersEntity, String)}
     *
     * @param user
     * @return
     */
    @Deprecated
    @Override
    public UserDTO asDto(@NonNull final UsersEntity user) {
        return asDto(user, null);
    }

    @Override
    public UserDTO asDto(@NonNull final UsersEntity user, String context) {
        synchronized (lock) {
            if (StringUtils.isBlank(context) || context.trim().equals("''")) {
                context = null;
            }
            UserDTO ret = new UserDTO();
            ret.setUser(user);
            if (user.isAdmin()) {
                final List<Long> channelCodes = channelCodesFromContext(context);
                ret.setCanali(channelCodes == null ? null
                        : channelCodes.isEmpty() ? new ArrayList<>() : service.findByCodiciCanale(channelCodes));
                ret.setCanaliCreatePromo(channelCodes == null ? null
                        : ret.getCanali().stream().map(CanalePromozioneEntity::getCodiceCanale)
                                .collect(Collectors.toList()));
                List<GroupEntity> groups;
                if (context == null) {
                    try {
                        groups = muiServiceInstance.get().readGroups();
                    } catch (Exception ex) {
                        log.error("Error reading groups", ex);
                        groups = null;
                    }
                } else {
                    groups = muiServiceInstance.get().findGroupsByContextCode(context);
                }
                if (groups != null) {
                    ret.setGruppi(groups.stream().filter(Objects::nonNull).map(GroupEntity::getCodiceGruppo)
                            .collect(Collectors.toList()));
                    ret.setCanaliOwner(muiServiceInstance.get().findOwnedCanaliByGruppi(groups).stream()
                            .map(CanaleEntity::getCodiceCanale).collect(Collectors.toList()));
                } else {
                    ret.setGruppi(new ArrayList<>());
                    ret.setCanaliOwner(new ArrayList<>());
                }
            } else if (ret.getUser().getGruppi() == null) {
                ret.setCanali(new ArrayList<>());
                ret.setCanaliCreatePromo(new ArrayList<>());
                ret.setGruppi(new ArrayList<>());
            } else {
                final List<Long> channelCodes = channelCodesFromContext(context);
                final Set<GroupEntity> groups = new HashSet<>();
                groups.addAll(user.getGruppi());
                if (context != null) {
                    final List<GroupEntity> grps = muiServiceInstance.get().findGroupsByContextCode(context);
                    groups.retainAll(grps);
                }
                final List<CanaleEntity> validi = channelCodes == null
                        ? groups.stream().map(GroupEntity::getCanali).filter(Objects::nonNull).flatMap(Set::stream)
                                .collect(Collectors.toList())
                        : groups.stream().map(GroupEntity::getCanali).filter(Objects::nonNull).flatMap(Set::stream)
                                .filter(c -> channelCodes.contains(c.getCodiceCanale())).collect(Collectors.toList());
                if (validi.isEmpty()) {
                    ret.setCanali(new ArrayList<>());
                    ret.setCanaliCreatePromo(new ArrayList<>());
                } else {
                    ret.setCanali(service.findByCodiciCanale(
                            validi.stream().map(CanaleEntity::getCodiceCanale).collect(Collectors.toList())));
                    ret.setCanaliCreatePromo(muiServiceInstance.get().findOwnershipCodiciCanaleByGruppi(groups));
                }
                ret.setGruppi(groups.stream().map(GroupEntity::getCodiceGruppo).collect(Collectors.toList()));
                ret.setCanaliOwner(muiServiceInstance.get().findOwnedCanaliByGruppi(new ArrayList<>(groups)).stream()
                        .map(CanaleEntity::getCodiceCanale).collect(Collectors.toList()));
            }
            return ret;
        }
    }

    private List<Long> channelCodesFromContext(String contextCode) {
        if (contextCode == null) {
            return null;
        }
        try {
            final MuiContext context = muiServiceInstance.get().findContextByCode(contextCode);
            final Set<CanaleEntity> canali = context.getCanali();
            if ((canali == null) || canali.isEmpty()) {
                log.error(String.format("Context %s does not have any channels", contextCode));
                return Collections.emptyList();
            }
            return canali.stream().map(CanaleEntity::getCodiceCanale).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error(String.format("Error getting context from code %s", contextCode), ex);
            return Collections.emptyList();
        }
    }
}
