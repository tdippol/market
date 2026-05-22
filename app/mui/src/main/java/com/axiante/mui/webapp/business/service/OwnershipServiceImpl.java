package com.axiante.mui.webapp.business.service;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.OwnershipService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;

@Dependent
public class OwnershipServiceImpl implements OwnershipService {

    @Inject
    private Instance<MuiService> muiServiceInstance;

    @Override
    public boolean hasOwnership(@NonNull PromozioneTestataEntity testata, List<String> groups) {
        final List<String> buyers = testata.getOwners() != null
                ? testata.getOwners().stream().map(CompratoreEntity::getCodiceCompratore).collect(Collectors.toList())
                : Collections.emptyList();
        final List<Long> codiciCanale = muiServiceInstance.get().findOwnershipCodiciCanaleByGruppiAndCompratori(groups, buyers);
        return testata.getMuiCanalePromozione() != null
                && testata.getMuiCanalePromozione().getCodiceCanale() != null
                && codiciCanale.contains(testata.getMuiCanalePromozione().getCodiceCanale());
    }
}
