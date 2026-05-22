package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("reportVenduto")
@Dependent
@Slf4j
public class ReportVendutoView extends AbstractDBPromoNavigation {
    private static final long serialVersionUID = 2240788712610124727L;

    @Inject
    private Instance<PromoService> promoServiceInstance;

    @Getter
    @Setter
    private Long idPromozioneCorrente;

    @Getter
    private List<PromozioneTestataEntity> promozioni;

    private UserDTO userDTO;
    private UserFilterUtils userFilterUtils = new UserFilterUtils();

    public void init() {
        userDTO = getUserDto();
        readPromozioni();
        autoselectFirstPromozione();
    }

    private void readPromozioni() {
        try {
            final Map<String, String> filters = userFilterUtils.createUserFiltersMapToQueryString(
                    userDTO.getUser().getDbFilters());
            promozioni = promoServiceInstance.get().findAllNotCancelled(filters, userDTO.getCanali())
                    .stream().sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error reading promozioni", ex);
            promozioni = new ArrayList<>();
        }
    }

    private void autoselectFirstPromozione() {
        if (promozioni != null && !promozioni.isEmpty()) {
            setIdPromozioneCorrente(promozioni.get(0).getId());
        } else {
            setIdPromozioneCorrente(null);
        }
    }

    @Override
    public void setNode(MenuItem node) {
        super.setNode(node);
        applyDefaultRules();
        init();
    }

    @Override
    public void applyRules() {
        // noop
    }

    @Override
    public void applyDefaultRules() {
        // noop
    }
}
