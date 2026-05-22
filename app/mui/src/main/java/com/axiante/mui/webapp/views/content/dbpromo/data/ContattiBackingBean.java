package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.PromozioneCostiContattoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozioneCostiContattoService;
import com.axiante.mui.webapp.views.FacesContextAware;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.math.BigDecimal;

@Dependent
@Slf4j
public class ContattiBackingBean implements FacesContextAware {
    @Getter
    @Setter
    private boolean locked = true;

    @Getter
    @Setter
    private BigDecimal valorePerContatto;

    @Getter
    @Setter
    private BigDecimal rettificaPerContatto;

    @Getter
    @Setter
    private Integer numeroContatti;

    @Inject
    private Instance<PromozioneCostiContattoService> promoCostiContattoServiceInstance;

    private PromozioneTestataEntity promozioneCorrente;
    private PromozioneCostiContattoEntity promoCostiContattoCorrente;

    public void saveContatti() {
        if (getSaveContattiBtnDisabled()) {
            addErrorMessage("Errore", "Impossibile salvare contatti promozione; controllare i valori impostati");
        } else {
            savePromozioneCostiContatto(promoCostiContattoCorrente != null
                    ? promoCostiContattoCorrente : new PromozioneCostiContattoEntity());
        }
    }

    public void unlockEditContatti() {
        if (locked) {
            locked = false;
        }
    }

    public void discardContatti() {
        if (promoCostiContattoCorrente == null) {
            resetForm(false);
        } else {
            rettificaPerContatto = promoCostiContattoCorrente.getValoreRettCostoContatto();
            numeroContatti = promoCostiContattoCorrente.getNumeroContatti();
        }
        locked = true;
    }

    public void setPromozioneCorrente(PromozioneTestataEntity promozioneCorrente) {
        this.promozioneCorrente = promozioneCorrente;
        if (promozioneCorrente != null) {
            loadForm(promozioneCorrente);
        } else {
            resetForm(true);
        }
    }

    public boolean getSaveContattiBtnDisabled() {
        return promozioneCorrente == null || numeroContatti == null;
    }

    private void loadForm(@NonNull PromozioneTestataEntity promozione) {
        valorePerContatto = promozione.getMuiCanalePromozione().getValoreCostoContatto();
        PromozioneCostiContattoEntity promoCostiContatto = promoCostiContattoServiceInstance.get()
                .findByIdPromozione(promozione.getId());
        if (promoCostiContatto != null) {
            promoCostiContattoCorrente = promoCostiContatto;
            rettificaPerContatto = promoCostiContatto.getValoreRettCostoContatto();
            numeroContatti = promoCostiContatto.getNumeroContatti();
        } else {
            log.warn("No contatti found for promozione {}", promozione.getId());
            resetForm(false);
        }
    }

    private void savePromozioneCostiContatto(PromozioneCostiContattoEntity promozioneCostiContatto) {
        try {
            if (promozioneCostiContatto.getPromozione() == null) {
                promozioneCostiContatto.setPromozione(promozioneCorrente);
            }
            promozioneCostiContatto.setValoreCostoContatto(valorePerContatto);
            promozioneCostiContatto.setValoreRettCostoContatto(rettificaPerContatto);
            promozioneCostiContatto.setNumeroContatti(numeroContatti);
            BigDecimal importoTotale = rettificaPerContatto == null
                    ? valorePerContatto.multiply(BigDecimal.valueOf(numeroContatti))
                    : rettificaPerContatto.multiply(BigDecimal.valueOf(numeroContatti));
            promozioneCostiContatto.setImportoTotale(importoTotale);
            promoCostiContattoServiceInstance.get().persistWithAuditLog(promozioneCostiContatto, getCurrentUser().getName());
            addInfoMessage("Info", "Costi contatto salvati per la promozione corrente");
            locked = true;
        } catch (Exception ex) {
            log.error("Error saving PromozioneCostiContatto", ex);
            addErrorMessage("Errore", "Errore salvataggio contatti promozione; contattare l'assistenza");
        }
    }

    private void resetForm(boolean resetValorePerContatto) {
        if (resetValorePerContatto) {
            valorePerContatto = null;
        }
        rettificaPerContatto = null;
        numeroContatti = null;
    }
}
