package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiPlanoDbPromoService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.MeccanicheUtil;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.webservice.pojo.pianificazione.ItemPojo;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class PianificazionePlanoRiferimentoBackingBean implements FacesContextAware {

    @Getter
    private String title;

    @Getter
    private List<MeccanicheEntity> meccaniche;

    @Getter
    private Long idMeccanicaDefault;

    @Getter
    private Set<ItemPojo> itemSelezionati;

    @Getter
    private boolean confirmDialogBtnDisabled = true;

    @Setter
    private PromozioneTestataEntity promozioneCorrente;

    private MuiPlanoDbPromoService muiPlanoDbPromoService;
    private PianificazioneHelper pianificazioneHelper;
    private MeccanicheUtil meccanicheUtil;
    private UserDTO userDTO;

    public PianificazionePlanoRiferimentoBackingBean(MuiPlanoDbPromoService muiPlanoDbPromoService,
                                                     PianificazioneHelper pianificazioneHelper,
                                                     MeccanicheUtil meccanicheUtil, UserDTO userDto) {
        this.muiPlanoDbPromoService = muiPlanoDbPromoService;
        this.pianificazioneHelper = pianificazioneHelper;
        this.meccanicheUtil = meccanicheUtil;
        this.userDTO = userDto;
    }

    public void setIdMeccanicaDefault(Long idMeccanicaDefault) {
        this.idMeccanicaDefault = idMeccanicaDefault;
        handleConfirmDialogButton();
    }

    public void prepareDialog() {
        resetDialog();
        
        if (promozioneCorrente == null || (promozioneCorrente.getPlanogrammi() != null && promozioneCorrente.getPlanogrammi().size() > 0) && StringUtils.isBlank( promozioneCorrente.getPlanogrammi().iterator().next().getCodicePlano())) {
            final String msg = promozioneCorrente == null
                    ? "Nessuna promozione selezionata."
                    : "Nessun planogram di riferimento associato.";
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attenzione", msg));
        } else {
            setTitle();
            loadMeccaniche();
            final String js = String.format("loadArticoloDaPlanoGrid(%d, '%s'); PF('planoRiferimentoDialog').show();",
                    promozioneCorrente.getId(), promozioneCorrente.getPlanogrammi() != null && promozioneCorrente.getPlanogrammi().size() > 0 ?  promozioneCorrente.getPlanogrammi().iterator().next().getCodicePlano() : "");
            
            executeScript(js);
        }
    }

    public void resetDialog() {
        idMeccanicaDefault = null;
        if (itemSelezionati != null) {
            itemSelezionati.clear();
        } else {
            itemSelezionati = new HashSet<>();
        }
    }

    public void confirmPlanoRiferimento() {
        if (validate()) {
            boolean saved = false;
            MeccanicheEntity meccanica = getMeccanicaFromId(idMeccanicaDefault);
            if (meccanica != null) {
                saved = pianificazioneHelper.savePromoPianificazione(promozioneCorrente, meccanica,
                        itemSelezionati, PromoPianificazioneEnum.ARTICOLO.getTipoElemento(), getCurrentUser().getName());
            }
            if (saved) {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Articoli inseriti",
                        "Tutti gli articoli selezionati sono stati inseriti in pianificazione"));
            } else {
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione",
                        "Impossibile inserire articoli in pianificazione; contattare l'assistenza."));
            }
        }
    }

    public void selezionaItems() {
        final String itemSelectedJsonArray = getRequestParameterMap().get("itemSelected");
        try {
            final JsonNode jsonNode = JsonUtils.getMapper().readTree(itemSelectedJsonArray);
            if (jsonNode != null) {
                if (itemSelezionati != null) {
                    itemSelezionati.clear();
                } else {
                    itemSelezionati = new HashSet<>();
                }
                jsonNode.forEach(n -> itemSelezionati.add(ItemPojo.fromJson(n)));
            }
            handleConfirmDialogButton();
        } catch (Exception ex) {
            log.error(String.format("Error reading itemSelected\n%s", itemSelectedJsonArray), ex);
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione",
                    "Errore lettura elementi selezionati; riprovare.\nSe il problema persiste contattare l'assistenza."));
            confirmDialogBtnDisabled = true;
        }
    }

    private void setTitle() {
        // Costruisco titolo
        String descPromo = "";
        if (promozioneCorrente != null) {
            descPromo = promozioneCorrente.getDescrizioneEstesa();
        }
        title = String.format("Pianificazione: %s ", descPromo);
    }

    private void loadMeccaniche() {
        this.meccaniche = promozioneCorrente != null && promozioneCorrente.getPromozioneMeccanicheEntities() != null
                ? meccanicheUtil.getMeccanicheDisponibili(promozioneCorrente, userDTO.getCanali())
                : new LinkedList<>();
        if (this.meccaniche.size() == 1) {
            setIdMeccanicaDefault(this.meccaniche.get(0).getId());
        }
    }

    private void handleConfirmDialogButton() {
        confirmDialogBtnDisabled = idMeccanicaDefault == null || itemSelezionati == null || itemSelezionati.isEmpty();
    }

    private MeccanicheEntity getMeccanicaFromId(Long idMeccanica) {
        return meccaniche.stream().filter(m -> idMeccanica.equals(m.getId())).findFirst().orElse(null);
    }

    private boolean validate() {
        if (promozioneCorrente == null || itemSelezionati.isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            if (promozioneCorrente == null) {
                sb.append("Nessuna promozione selezionata.");
            }
            if (itemSelezionati.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append("Nessun articolo selezionato.");
            }
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attenzione", sb.toString()));
            return false;
        }
        return true;
    }
}
