package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiPromoDbPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.MuiPromoDbPromoService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.MeccanicheUtil;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.webservice.pojo.pianificazione.ItemPojo;
import com.axiante.mui.webapp.webservice.pojo.pianificazione.ItemRiferimento;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class PianificazionePromoRiferimentoBackingBean implements FacesContextAware {

    @Getter
    private String title;

    @Setter
    private PromozioneTestataEntity promozioneCorrente;

    @Getter
    private List<MeccanicheEntity> meccaniche;

    @Getter
    private Long idMeccanicaDefault;

    @Getter
    private Set<ItemRiferimento> itemSelezionati;

    @Getter
    boolean confirmDialogBtnDisabled = true;

    private MuiPromoDbPromoService muiPromoDbPromoService;
    private PianificazioneHelper pianificazioneHelper;
    private MeccanicheUtil meccanicheUtil;

    @Getter
    private UserDTO userDTO;

    @Setter
    @Getter
    private boolean renderSelezionaGruppo;

    @Setter
    @Getter
    private String gruppoSelezionato;

    @Setter
    @Getter
    private boolean disableSelezionaGruppo;

    public PianificazionePromoRiferimentoBackingBean(MuiPromoDbPromoService muiPromoDbPromoService,
                                                     PianificazioneHelper pianificazioneHelper,
                                                     MeccanicheUtil meccanicheUtil,
                                                     UserDTO userDTO) {
        this.muiPromoDbPromoService = muiPromoDbPromoService;
        this.pianificazioneHelper = pianificazioneHelper;
        this.meccanicheUtil = meccanicheUtil;
        this.userDTO = userDTO;
    }

    public void setIdMeccanicaDefault(Long idMeccanicaDefault) {
        this.idMeccanicaDefault = idMeccanicaDefault;
        handleConfirmDialogBtn();
    }

    public void prepareDialog() {
        resetDialog();
        if (promozioneCorrente == null || StringUtils.isBlank(promozioneCorrente.getCodicePromoRiferimento())) {
            final String msg = promozioneCorrente == null
                    ? "Nessuna promozione selezionata."
                    : "Nessuna promozione di riferimento associata.";
            addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attenzione", msg));
        } else {
            setTitle();
            loadMeccaniche();
            final String js = String.format("loadArticoloGrid(%d, '%s'); PF('promozioneRiferimentoDialog').show();",
                    promozioneCorrente.getId(), promozioneCorrente.getCodicePromoRiferimento());
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

    public void confirmPromoRiferimento() {
        if (validate()) {
            final List<ItemRiferimento> skipped = new ArrayList<>();
            // Raggruppo per meccanica, cosi' posso validare la stessa meccanica una volta sola
            final Map<String, List<ItemRiferimento>> groupedItems = itemSelezionati.stream()
                    .collect(Collectors.groupingBy(ItemRiferimento::getCodiceMeccanica));
            for (Map.Entry<String, List<ItemRiferimento>> entry : groupedItems.entrySet()) {
                boolean saved = false;
                // Valido la meccanica
                MeccanicheEntity meccanica = getMeccanicaFromCode(entry.getKey());
                if (meccanica != null) {
                    // Salvo utilizzando la meccanica indicata e il valore offerta indicato
                    saved = pianificazioneHelper.saveArticoliDaPromoRiferimento(promozioneCorrente, meccanica,
                            entry.getValue(), getCurrentUser().getName());
                } else {
                    meccanica = getMeccanicaDefault();
                    if (meccanica != null) {
                        // Utilizzo la meccanica di default e valore offerta NULL
                        final Set<ItemPojo> items = entry.getValue().stream().map(ItemRiferimento::getItem)
                                .collect(Collectors.toSet());
                        saved = pianificazioneHelper.savePromoPianificazione(promozioneCorrente, meccanica,
                                items, PromoPianificazioneEnum.ARTICOLO.getTipoElemento(), getCurrentUser().getName());
                    }
                }
                if (!saved) {
                    skipped.addAll(entry.getValue());
                }
            }
            if (skipped.isEmpty()) {
                // Tutti gli articoli inseriti
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Articoli inseriti",
                        "Tutti gli articoli selezionati sono stati inseriti in pianificazione"));
            } else {
                // Alcuni articoli non inseriti, li mostro nel growl
                final String msg = String.format("I seguenti articoli non sono stati inseriti\n%s", skipped.stream()
                        .map(i -> i.getItem().getElementDescription()).collect(Collectors.joining("\n")));
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attenzione", msg));
            }
        }
    }

    private MeccanicheEntity getMeccanicaDefault() {
        return meccaniche.stream().filter(m -> idMeccanicaDefault.equals(m.getId()))
                .findFirst().orElse(null);
    }

    private MeccanicheEntity getMeccanicaFromCode(String codiceMeccanica) {
        if (StringUtils.isBlank(codiceMeccanica)) {
            return null;
        }
        return meccaniche.stream().filter(m -> codiceMeccanica.trim().equals(m.getCodiceMeccanica().trim()))
                .findFirst().orElse(null);
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
                jsonNode.forEach(n -> itemSelezionati.add(ItemRiferimento.fromJson(n)));
            }
            handleConfirmDialogBtn();
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
        String descPromoRiferimento = "";
        if (promozioneCorrente != null) {
            descPromo = promozioneCorrente.getDescrizioneEstesa();
            final String codicePromoRiferimento = promozioneCorrente.getCodicePromoRiferimento();
            if (codicePromoRiferimento != null && !codicePromoRiferimento.trim().isEmpty()) {
                try {
                    final MuiPromoDbPromoEntity promoRif = muiPromoDbPromoService.findByCodicePromozione(codicePromoRiferimento);
                    if (promoRif != null) {
                        descPromoRiferimento = promoRif.getDescrizioneEstesa();
                    }
                } catch (Exception ex) {
                    log.error(String.format("Unable to retrieve 'Descrizione Estesa' from 'Promozione Riferimento' %s",
                            codicePromoRiferimento), ex);
                }
            }
        }
        title = String.format("Pianificazione: %s -> Rif: %s", descPromo, descPromoRiferimento);
    }

    private void loadMeccaniche() {
        this.meccaniche = promozioneCorrente != null && promozioneCorrente.getPromozioneMeccanicheEntities() != null
                ? meccanicheUtil.getMeccanicheDisponibili(promozioneCorrente, userDTO.getCanali())
                : new LinkedList<>();
        if (this.meccaniche.size() == 1) {
            setIdMeccanicaDefault(this.meccaniche.get(0).getId());
        }
    }

    private void handleConfirmDialogBtn() {
        confirmDialogBtnDisabled = this.idMeccanicaDefault == null || itemSelezionati == null || itemSelezionati.isEmpty();
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
