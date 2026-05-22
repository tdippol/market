package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.CfgMeccanicaMissioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.ObiettivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazioneTotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgMeccanicaMissioneService;
import com.axiante.mui.dbpromo.persistence.service.ObiettivoService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTestataService;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.webservice.util.PromozioneObiettiviUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
@Slf4j
public class ObiettivoBean implements Serializable, FacesContextAware {
    private static final long serialVersionUID = -208679823434214706L;

    @Getter
    @Setter
    private BigDecimal target;

    @Getter
    @Setter
    private BigDecimal buonoPremio;

    @Getter
    @Setter
    private Boolean attivo;

    @Getter
    @Setter
    private Boolean buonoPremioFieldDisabled = false;

    @Getter
    private List<PromozioneTestataEntity> promozioniPremio;

    @Getter
    private Long idPromozioneSelected;

    @Inject
    private Instance<PromozioneTestataService> promozioneServiceInstance;

    @Inject
    private Instance<ObiettivoService> obiettivoServiceInstance;

    @Inject
    private Instance<CfgMeccanicaMissioneService> meccanicaServiceInstance;

    @Inject
    private Instance<PromozioneObiettiviUtil> promozioneObiettiviUtilInstance;

    private List<String> meccanicheAbilitate;
    private PianificazioneTotalizzatoriEntity totalizzatore;
    private String codiceIniziativa;
    private PromozioneTestataEntity promozionePremioSelected;

    public void initCreaObiettivoDialog(PromozioneTestataEntity promozione) {
        loadMeccanicheAbilitate();
        retrieveCodiceIniziativa(promozione);
        retrieveTotalizzatore(promozione);
        resetFields();
        loadPromozioniPremio(promozione);
    }

    public void setIdPromozioneSelected(Long idPromozioneSelected) {
        this.idPromozioneSelected = idPromozioneSelected;
        promozionePremioSelected = idPromozioneSelected != null ? retrievePromozioneById(idPromozioneSelected) : null;
        handleBuonoPremioFieldDisabled();
    }

    public void confirmObiettivo() {
        if (validate()) {
            try {
                saveObiettivo();
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Obiettivo salvato",
                        "Obiettivo salvato correttamente"));
            } catch (Exception ex) {
                log.error("Error saving obiettivo", ex);
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                        "Errore salvataggio obiettivo; contattare l'assistenza"));
            }
        } else {
            log.warn(String.format("Obiettivo not valid; idTotalizzatore: %s, codiceIniziativa: %s, target: %s, buonoPremio: %s, attivo: %s",
                    totalizzatore != null ? totalizzatore.getId() : "null", codiceIniziativa, target, buonoPremio, attivo));
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Dati inseriti non validi"));
        }
    }

    private void loadMeccanicheAbilitate() {
        meccanicheAbilitate = meccanicaServiceInstance.get().findAll().stream()
                .map(CfgMeccanicaMissioneEntity::getCodiceMeccanica)
                .collect(Collectors.toList());
    }

    private void loadPromozioniPremio(PromozioneTestataEntity promozione) {
        if (promozione == null || meccanicheAbilitate == null || meccanicheAbilitate.isEmpty()) {
            if (promozione == null) {
                log.error("Unable to load promozioni premio: promozione null");
            } else {
                log.error("Unable to load promozioni premio: meccaniche abilitate null or empty");
            }
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile caricare le promozioni premio; contattare l'assistenza"));
            return;
        }
        try {
            promozioniPremio = promozioneServiceInstance.get().findByCanaleMeccanicheDate(promozione.getMuiCanalePromozione().getId(),
                    meccanicheAbilitate, promozione.getDataInizio(), promozione.getDataFine());
        } catch (Exception ex) {
            log.error("Unable to load promozioni premio", ex);
            promozioniPremio = Collections.emptyList();
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
                    "Impossibile caricare le promozioni premio; contattare l'assistenza"));
        }
    }

    private boolean validate() {
        return totalizzatore != null && codiceIniziativa != null && promozioniPremio != null;
    }

    private void saveObiettivo() {
        ObiettivoEntity obiettivo = new ObiettivoEntity();
        obiettivo.setPromozione(promozionePremioSelected);
        obiettivo.setTarget(target);
        obiettivo.setBuonoPremio(buonoPremio);
        obiettivo.setFlagAttivo(attivo);
        obiettivo.setTotalizzatore(totalizzatore);
        obiettivoServiceInstance.get().persistWithAuditLog(obiettivo, getCurrentUser().getName());
    }

    private void handleBuonoPremioFieldDisabled() {
        boolean buonoPremioFieldDisabled = promozionePremioSelected != null
                && promozioneObiettiviUtilInstance.get().hasMeccanicaPrecaricata(promozionePremioSelected);
        if (buonoPremioFieldDisabled) {
            buonoPremio = null;
        }
        this.buonoPremioFieldDisabled = buonoPremioFieldDisabled;
    }

    private void resetFields() {
        setIdPromozioneSelected(null);
        target = null;
        buonoPremio = null;
        attivo = null;
    }

    private void retrieveTotalizzatore(PromozioneTestataEntity promozione) {
        if (promozione != null && !promozione.getTotalizzatori().isEmpty()) {
            final PianificazioneTotalizzatoriEntity totalizzatore = promozione.getTotalizzatori().stream()
                    .filter(t -> t.getIdParent() == null)
                    .findFirst()
                    .orElse(null);
            if (totalizzatore == null) {
                log.error("Unable to retrieve totalizzatore from promozione with id: {}", promozione.getId());
            }
            this.totalizzatore = totalizzatore;
        } else {
            log.error("Unable to retrieve id totalizzatore: promozione null or empty totalizzatori");
            this.totalizzatore = null;
        }
    }

    private void retrieveCodiceIniziativa(PromozioneTestataEntity promozione) {
        if (promozione != null && promozione.getAnno() != null && promozione.getCodicePromozione() != null) {
            codiceIniziativa = String.format("%s%s", promozione.getAnno().substring(2),
                    promozione.getCodicePromozione().split("_")[1]);
        } else {
            log.error("Unable to calculate codice iniziativa: promozione null");
            codiceIniziativa = null;
        }
    }

    private PromozioneTestataEntity retrievePromozioneById(Long idPromozione) {
        PromozioneTestataEntity promozione = null;
        if (idPromozione != null) {
            promozione = promozioniPremio.stream()
                    .filter(p -> idPromozione.equals(p.getId()))
                    .findFirst()
                    .orElse(null);
        }
        return promozione;
    }
}
