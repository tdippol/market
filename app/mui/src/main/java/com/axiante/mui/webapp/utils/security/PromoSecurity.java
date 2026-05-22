package com.axiante.mui.webapp.utils.security;

import com.axiante.mui.common.CanaleSecurityEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleSecEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgCanaleSecService;
import com.axiante.mui.persistence.entity.RolesEntity;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.business.OwnershipService;
import com.axiante.mui.webapp.business.data.UserDTO;
import java.util.Objects;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class PromoSecurity {
    @Inject
    private Instance<OwnershipService> ownershipServiceInstance;
    @Inject
    private Instance<CfgCanaleSecService> canaleSecServiceInstance;

    /**
     * Controlla se la promozione e' accessibile per l'utente in uno dei 2 casi:
     * <ul>
     *     <li>i canali associati all'utente sono null</li>
     *     <li>i canali associati all'utente comprendono quello della promozione</li>
     * </ul>
     * La promozione non e' accessibile se:
     * <ul>
     *     <li>promozione e' null</li>
     *     <li>userDTO e' null</li>
     *     <li>promozione non ha canale associato</li>
     *     <li>i canali associati all'utente non comprendono quello della promozione</li>
     * </ul>
     *
     * @param promozione testata promozionale
     * @param userDTO    utente
     * @return true se utente ha accesso, false altrimenti
     */
    public boolean isAccessible(PromozioneTestataEntity promozione, UserDTO userDTO) {
        if (promozione == null || userDTO == null || promozione.getMuiCanalePromozione() == null) {
            return false;
        }
        return userDTO.getCanali() == null || userDTO.getCanali().stream().filter(Objects::nonNull)
                .map(CanalePromozioneEntity::getCodiceCanale).filter(Objects::nonNull)
                .distinct().anyMatch(c -> c.equals(promozione.getMuiCanalePromozione().getCodiceCanale()));
    }

    /**
     * Controlla che la promozione sia accessibile in scrittura dall'utente.
     * La promozione è accessibile in scrittura se l'utente è owner e il canale è scrivibile per l'owner
     * oppure se l'utente non è owner e il canale è scrivibile per gli utenti non owner
     *
     * @param promozione la testata promozionale
     * @param userDTO    utente
     * @return true se l'utente ha accesso in scrittura, false altrimenti
     */
    public boolean isWriteable(PromozioneTestataEntity promozione, UserDTO userDTO) {
        if (promozione == null || userDTO == null || promozione.getMuiCanalePromozione() == null) {
            return false;
        }
        final boolean hasOwnership = ownershipServiceInstance.get().hasOwnership(promozione, userDTO.getGruppi());
        final CfgCanaleSecEntity cfgCanaleSec = canaleSecServiceInstance.get()
                .findByCanale(promozione.getMuiCanalePromozione());
        if (hasOwnership) {
            return !CanaleSecurityEnum.NONE.equals(cfgCanaleSec.getOwnerWrite());
        } else {
            return !CanaleSecurityEnum.NONE.equals(cfgCanaleSec.getOtherWrite());
        }
    }

    private boolean isAdmin(UserDTO user) {
        UsersEntity e = user.getUser();
        return (e != null) && (e.getRoles() != null) && e.getRoles().stream().filter(Objects::nonNull).filter(RolesEntity::isAdmin).findFirst().isPresent();

    }


}
