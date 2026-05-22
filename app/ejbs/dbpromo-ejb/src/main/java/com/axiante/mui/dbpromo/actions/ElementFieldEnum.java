package com.axiante.mui.dbpromo.actions;

import lombok.Getter;

public enum ElementFieldEnum {

    FIELD_ORA_INIZIO("oraInizio"), FIELD_ORA_FINE("oraFine"), FIELD_NUOVA_ORA_INIZIO("nuovaOraInizio"),
    FIELD_NUOVA_ORA_FINE("nuovaOraFine"), BUTTON_AGGIUNGI_MECCANICHE("buttonAggiungiMeccaniche"),
    TAB_PROMO_RIFERIMENTO("tabPromoRiferimento"), BUTTON_SCEGLI_PROMO("buttonScegliPromo"),
    BLOCK_CARICAMENTO_NEGOZI("blockCaricamentoNegozi"), TAB_TIPO_CASSA("tabTipoCassa"),
    BUTTON_AGGIUNGI_TIPO_CASSA("buttonAggiungiTipoCassa"), TAB_REPARTI("tabReparti"),
    BUTTON_AGGIUNGI_REPARTI("buttonAggiungiReparti"), TAB_MODIFICA("tabModifica"), BUTTON_UNLOCK("buttonUnlock"),
    BUTTON_ELIMINA("buttonElimina"), FIELD_DESCRIZIONE("descrizione"), FIELD_NUOVA_DESCRIZIONE("nuovaDescrizione"),
    FIELD_DATA_INIZIO("dataInizio"), FIELD_NUOVA_DATA_INIZIO("nuovaDataInizio"), FIELD_DATA_FINE("dataFine"),
    FIELD_NUOVA_DATA_FINE("nuovaDataFine"), FIELD_NOTE("note"), BUTTON_PIANIFICA_DA_RIF("buttonPianificaDaRiferimento"),
    BUTTON_PIANIFICA_DA_PLANO("buttonPianificaDaPlano"), TAB_STORICO_UPLOAD("tabStoricoUpload"),
    TAB_COMPRATORI("tabCompratori"), BUTTON_NUOVA_PIANIFICAZIONE("buttonNuovaPianificazione"),
    BUTTON_CONFERMA_PIANIFICAZIONE("buttonConfermaPianificazione"),
    BUTTON_ELIMINA_ASSOCIAZIONE_PLANO("buttonEliminaAssociazionePlano"), BUTTON_SCEGLI_PLANO("buttonScegliPlano"),
    TAB_PLANO_RIFERIMENTO("tabPlanoRiferimento"), BUTTON_ELIMINA_ASSOCIAZIONE_PROMO("buttonEliminaAssociazionePromo"),
    TAB_CONTROLLI("tabControlli"), TAB_OWNER("tabOwner"), TAB_CONTROLLI_PIANIFICAZIONE("tabControlliPianificazione"),
    TAB_SOVRAPPOSIZIONI_PIANIFICAZIONE("tabSovrapposizioniPianificazione"),
    TEXTBOX_WARNING_NEGOZI("textBoxWarningNegozi"),
    TAB_NEGOZI_CEDI("tabNegoziCedi"),
    TAB_NEGOZI_ZONA("tabNegoziZona"),
    TAB_INIZIATIVA("tabIniziativa"), BUTTON_SCEGLI_INIZIATIVA("buttonScegliIniziatia"),
    BUTTON_ELIMINA_ASSOCIAZIONE_INIZIATIVA("buttonEliminaAssociazioneIniziativa"),
    NEGOZI_TIPO_PUNTO_VENDITA("tabNegoziPuntoVendita"),
    NEGOZI_CANALE_CONSEGNA("tabNegoziCanaleConsegna"),
    TAB_FLAG("tabFlag"),
    TAB_ATTRIBUTI("tabAttributi"),
    ;

    @Getter
    private String name;

    ElementFieldEnum(String name) {
        this.name = name;
    }
}
