package com.axiante.mui.dbpromo.actions;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ActionEnum {

    RENDER_FASCIA_ORARIA("renderFasciaOraria"), EDIT_FASCIA_ORARIA("editFasciaOraria"),
    BUTTON_AGGIUNGI_MECCANICHE("buttonAggiungiMeccaniche"), TAB_PROMO_RIFERIMENTO("tabPromoRiferimento"),
    BUTTON_SCEGLI_PROMO("buttonScegliPromo"), UPLOAD_NEGOZI("uploadNegozi"), TAB_TIPO_CASSA("tabTipoCassa"),
    BUTTON_AGGIUNGI_TIPO_CASSA("buttonAggiungiTipoCassa"), TAB_REPARTI("tabReparti"),
    BUTTON_AGGIUNGI_REPARTI("buttonAggiungiReparti"), TAB_MODIFICA("tabModifica"), BUTTON_UNLOCK("buttonUnlock"),
    BUTTON_ELIMINA("buttonElimina"), EDIT_DESCRIZIONE("editDescrizione"), EDIT_DATA_INIZIO("editDataInizio"),
    EDIT_DATA_FINE("editDataFine"), EDIT_NOTE("editNote"), RENDER_BTN_PIANIFICA_DA_RIF("renderBtnPianificaDaRif"),
    RENDER_BTN_PIANIFICA_DA_PLANO("renderBtnPianificaDaPlano"), TAB_STORICO_UPLOAD("tabStoricoUpload"),
    TAB_COMPRATORI("tabCompratori"), RENDER_BTN_NUOVA_PIANIFICAZIONE("renderBtnNuovaPianificazione"),
    RENDER_BTN_CONFERMA_PIANIFICAZIONE("renderBtnConfermaPianificazione"),
    BUTTON_ELIMINA_ASSOCIAZIONE_PLANO("buttonEliminaAssociazionePlano"), BUTTON_SCEGLI_PLANO("buttonScegliPlano"),
    TAB_PLANO_RIFERIMENTO("tabPlanoRiferimento"), BUTTON_ELIMINA_ASSOCIAZIONE_PROMO("buttonEliminaAssociazionePromo"),
    TAB_CONTROLLI("tabControlli"), TAB_OWNER("tabOwner"), TAB_CONTROLLI_PIANIFICAZIONE("tabControlliPianificazione"),
    TAB_SOVRAPPOSIZIONI_PIANIFICAZIONE("tabSovrapposizioniPianificazione"),
    TEXTBOX_WARNING_NEGOZI("textBoxWarningNegozi"),
    TAB_NEGOZI_CEDI("tabNegoziCedi"),
    TAB_NEGOZI_ZONA("tabNegoziZona"),
    TAB_INIZIATIVA("tabIniziativa"), BUTTON_SCEGLI_INIZIATIVA("buttonScegliIniziativa"),
    BUTTON_ELIMINA_ASSOCIAZIONE_INIZIATIVA("buttonEliminaAssociazioneIniziativa"),

    RENDER_NEGOZI_PUNTO_VENDITA("tabNegoziPuntoVendita"),

    RENDER_NEGOZI_CANALE_CONSEGNA("tabNegoziCanaleConsegna"),

    TAB_FLAG_TESTATA("tabFlagTestata"),

    EDIT_FLAG_TESTATA("editFlagTestata"),
    TAB_ATTRIBUTI_TESTATA("tabAttributiTestata"),

    EDIT_ATTRIBUTI_TESTATA("editAttributiTestata"),

    EDIT_SHOP_WHILE_EXECUTION_IN_PROGRESS("enablesShopEditWhileExecutionInProgress"),
    ;


    @Getter
    private String name;

    ActionEnum(String name) {
        this.name = name;
    }

    public static ActionEnum fromName(@NonNull final String name) {
        switch (name) {
            case "renderFasciaOraria":
                return RENDER_FASCIA_ORARIA;
            case "editFasciaOraria":
                return EDIT_FASCIA_ORARIA;
            case "buttonAggiungiMeccaniche":
                return BUTTON_AGGIUNGI_MECCANICHE;
            case "tabPromoRiferimento":
                return TAB_PROMO_RIFERIMENTO;
            case "buttonScegliPromo":
                return BUTTON_SCEGLI_PROMO;
            case "uploadNegozi":
                return UPLOAD_NEGOZI;
            case "tabTipoCassa":
                return TAB_TIPO_CASSA;
            case "buttonAggiungiTipoCassa":
                return BUTTON_AGGIUNGI_TIPO_CASSA;
            case "tabReparti":
                return TAB_REPARTI;
            case "buttonAggiungiReparti":
                return BUTTON_AGGIUNGI_REPARTI;
            case "tabModifica":
                return TAB_MODIFICA;
            case "buttonUnlock":
                return BUTTON_UNLOCK;
            case "buttonElimina":
                return BUTTON_ELIMINA;
            case "editDescrizione":
                return EDIT_DESCRIZIONE;
            case "editDataInizio":
                return EDIT_DATA_INIZIO;
            case "editDataFine":
                return EDIT_DATA_FINE;
            case "editNote":
                return EDIT_NOTE;
            case "renderBtnPianificaDaRif":
                return RENDER_BTN_PIANIFICA_DA_RIF;
            case "buttonEliminaAssociazionePlano":
                return BUTTON_ELIMINA_ASSOCIAZIONE_PLANO;
            case "textBoxWarningNegozi":
                return TEXTBOX_WARNING_NEGOZI;
            case "tabNegoziCedi":
                return TAB_NEGOZI_CEDI;
            case "tabNegoziZona":
                return TAB_NEGOZI_ZONA;
            case "tabIniziativa":
                return TAB_INIZIATIVA;
            case "buttonEliminaAssociazioneIniziativa":
                return BUTTON_ELIMINA_ASSOCIAZIONE_INIZIATIVA;
            case "enablesShopEditWhileExecutionInProgress":
                    return EDIT_SHOP_WHILE_EXECUTION_IN_PROGRESS;
        }
        log.error(String.format("Action with name '%s' not managed", name));
        return null;
    }
}
