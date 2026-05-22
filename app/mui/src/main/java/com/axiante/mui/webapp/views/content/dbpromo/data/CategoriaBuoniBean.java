package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.DescCategoriaBuoniEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.DescCategoriaBuoniService;
import com.axiante.mui.webapp.views.FacesContextAware;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.Serializable;

@Dependent
@Slf4j
public class CategoriaBuoniBean implements Serializable, FacesContextAware {
    private static final long serialVersionUID = -5097493377377990684L;

    @Getter
    @Setter
    private boolean addCategoriaBuonoBtnDisabled = true;

    @Getter
    @Setter
    private Long idPromozione = null;

    @Inject
    private Instance<DescCategoriaBuoniService> categoriaBuoniServiceInstance;

    private PromozioneTestataEntity promozione;

    public void addCategoriaBuono() {
        if (promozione != null) {
            try {
                DescCategoriaBuoniEntity categoriaBuoni = new DescCategoriaBuoniEntity();
                categoriaBuoni.setIdPromozione(promozione.getId());
                categoriaBuoniServiceInstance.get().persist(categoriaBuoni);
            } catch (Exception ex) {
                log.error(String.format("Error creating categoriaBuono for promozione with id %s",
                        promozione != null ? promozione.getId() : "<null>"), ex);
            }
        }
        handleCategoriaBuonoBtnDisabled();
    }

    public void setPromozione(PromozioneTestataEntity promozione) {
        this.promozione = promozione;
        idPromozione = promozione != null ? promozione.getId() : null;
        handleCategoriaBuonoBtnDisabled();
    }

    private void handleCategoriaBuonoBtnDisabled() {
        if (promozione == null || promozione.getId() == null) {
            addCategoriaBuonoBtnDisabled = true;
        } else {
            DescCategoriaBuoniEntity categoriaBuono = categoriaBuoniServiceInstance.get()
                    .findByIdPromozione(promozione.getId());
            addCategoriaBuonoBtnDisabled = categoriaBuono != null;
        }
    }
}
