package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneAttiva;
import com.axiante.mui.dbpromo.persistence.service.PromozioniAttiveService;
import com.axiante.mui.filter.CatalogReducer;
import com.axiante.mui.model.ApplicationFilterCatalogProducer;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.tm1.mdx.objects.Query;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@MuiViewModel("cancellaPromo")
@Named("cancellaPromo")
@SessionScoped
@Slf4j
public class CancellaPromoView extends AbstractAdminView {
    private static final long serialVersionUID = -9050656833641200453L;

    @Getter
    private List<PromozioneAttiva> promozioniAttive;

    @Getter
    private String codicePromozioneSelected;

    @Inject
    private Instance<PromozioniAttiveService> promozioniAttiveServiceInstance;

    @Getter
    private PromozioneAttiva promoAttivaSelected;

    @PostConstruct
    public void init() {
        readPromozioniAttive();
    }

    public void setCodicePromozioneSelected(String codicePromozioneSelected) {
        this.codicePromozioneSelected = codicePromozioneSelected;
        promoAttivaSelected = codicePromozioneSelected != null
                ? retrievePromoAttivaSelected(codicePromozioneSelected)
                : null;
    }

    public void deletePromo() {
        if (codicePromozioneSelected == null || getUserDto().getUsermame() == null) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile cancellare la promozione"));
            return;
        }
        boolean result = promozioniAttiveServiceInstance.get().runDeletePromozione(codicePromozioneSelected,
                    getUserDto().getUsermame());
        if (result) {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo",
                    String.format("Promozione con codice '%s' eliminata con successo", codicePromozioneSelected)));
            setCodicePromozioneSelected(null);
        } else {
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Errore esecuzione procedura P_CANCELLA_PROMO"));
        }
    }

    private void readPromozioniAttive() {
        promozioniAttive = promozioniAttiveServiceInstance.get().findAll();
    }

    private PromozioneAttiva retrievePromoAttivaSelected(String codicePromozioneSelected) {
        return promozioniAttive.stream()
                .filter(p -> codicePromozioneSelected.equals(p.getCodice()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateView() {
        // noop
    }

    @Override
    public void updateView(String grid) {
        // noop
    }

    @Override
    public Query prepareFilteredQuery(String grid) {
        return null;
    }

    @Override
    protected ApplicationFilterCatalogProducer getCatalogProducer() {
        return null;
    }

    @Override
    protected CatalogReducer getCatalogReducer() {
        return null;
    }
}
