package com.axiante.mui.dbpromo.actions;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.NonNull;

@ApplicationScoped
public class ActionProducer implements Serializable {
    private static final long serialVersionUID = 3198097636463014722L;

    @Getter
    private Set<Action> actions;

    @PostConstruct
    public void init() {
        actions = new HashSet<>();
        Action action = Action.builder().code(ActionEnum.RENDER_FASCIA_ORARIA).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).form(FormEnum.MODIFICA_PROMO).field(ElementFieldEnum.FIELD_ORA_INIZIO)
                .field(ElementFieldEnum.FIELD_ORA_FINE).field(ElementFieldEnum.FIELD_NUOVA_ORA_INIZIO)
                .field(ElementFieldEnum.FIELD_NUOVA_ORA_FINE).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.EDIT_FASCIA_ORARIA).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).form(FormEnum.MODIFICA_PROMO).field(ElementFieldEnum.FIELD_ORA_INIZIO)
                .field(ElementFieldEnum.FIELD_ORA_FINE).field(ElementFieldEnum.FIELD_NUOVA_ORA_INIZIO)
                .field(ElementFieldEnum.FIELD_NUOVA_ORA_FINE).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_AGGIUNGI_MECCANICHE).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.BUTTON_AGGIUNGI_MECCANICHE).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_PROMO_RIFERIMENTO).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TAB_PROMO_RIFERIMENTO).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_SCEGLI_PROMO).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.BUTTON_SCEGLI_PROMO).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.UPLOAD_NEGOZI).type(ActionTypeEnum.RENDER).form(FormEnum.SCHEDA_PROMO)
                .field(ElementFieldEnum.BLOCK_CARICAMENTO_NEGOZI).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_TIPO_CASSA).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TAB_TIPO_CASSA).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_AGGIUNGI_TIPO_CASSA).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.BUTTON_AGGIUNGI_TIPO_CASSA).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_REPARTI).type(ActionTypeEnum.RENDER).form(FormEnum.SCHEDA_PROMO)
                .field(ElementFieldEnum.TAB_REPARTI).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_AGGIUNGI_REPARTI).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.BUTTON_AGGIUNGI_REPARTI).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_MODIFICA).type(ActionTypeEnum.RENDER).form(FormEnum.SCHEDA_PROMO)
                .field(ElementFieldEnum.TAB_MODIFICA).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_UNLOCK).type(ActionTypeEnum.ACTIVE).form(FormEnum.SCHEDA_PROMO)
                .field(ElementFieldEnum.BUTTON_UNLOCK).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_ELIMINA).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).form(FormEnum.MODIFICA_PROMO).field(ElementFieldEnum.BUTTON_ELIMINA)
                .build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.EDIT_DESCRIZIONE).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).form(FormEnum.MODIFICA_PROMO).field(ElementFieldEnum.FIELD_DESCRIZIONE)
                .field(ElementFieldEnum.FIELD_NUOVA_DESCRIZIONE).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.EDIT_DATA_INIZIO).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).form(FormEnum.MODIFICA_PROMO).field(ElementFieldEnum.FIELD_DATA_INIZIO)
                .field(ElementFieldEnum.FIELD_NUOVA_DATA_INIZIO).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.EDIT_DATA_FINE).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).form(FormEnum.MODIFICA_PROMO).field(ElementFieldEnum.FIELD_DATA_FINE)
                .field(ElementFieldEnum.FIELD_NUOVA_DATA_FINE).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.EDIT_NOTE).type(ActionTypeEnum.ACTIVE).form(FormEnum.SCHEDA_PROMO)
                .form(FormEnum.MODIFICA_PROMO).field(ElementFieldEnum.FIELD_NOTE).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.RENDER_BTN_PIANIFICA_DA_RIF).type(ActionTypeEnum.RENDER)
                .form(FormEnum.PIANIFICAZIONE).field(ElementFieldEnum.BUTTON_PIANIFICA_DA_RIF).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.RENDER_BTN_PIANIFICA_DA_PLANO).type(ActionTypeEnum.RENDER)
                .form(FormEnum.PIANIFICAZIONE).field(ElementFieldEnum.BUTTON_PIANIFICA_DA_PLANO).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_STORICO_UPLOAD).type(ActionTypeEnum.RENDER)
                .form(FormEnum.PIANIFICAZIONE).field(ElementFieldEnum.TAB_STORICO_UPLOAD).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_COMPRATORI).type(ActionTypeEnum.RENDER)
                .form(FormEnum.PIANIFICAZIONE).field(ElementFieldEnum.TAB_COMPRATORI).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.RENDER_BTN_NUOVA_PIANIFICAZIONE).type(ActionTypeEnum.RENDER)
                .form(FormEnum.PIANIFICAZIONE).field(ElementFieldEnum.BUTTON_NUOVA_PIANIFICAZIONE).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.RENDER_BTN_CONFERMA_PIANIFICAZIONE).type(ActionTypeEnum.RENDER)
                .form(FormEnum.PIANIFICAZIONE).field(ElementFieldEnum.BUTTON_CONFERMA_PIANIFICAZIONE).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_ELIMINA_ASSOCIAZIONE_PLANO).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.BUTTON_ELIMINA_ASSOCIAZIONE_PLANO).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_SCEGLI_PLANO).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.BUTTON_SCEGLI_PLANO).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_PLANO_RIFERIMENTO).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TAB_PLANO_RIFERIMENTO).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_ELIMINA_ASSOCIAZIONE_PROMO).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.BUTTON_ELIMINA_ASSOCIAZIONE_PROMO).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_CONTROLLI).type(ActionTypeEnum.RENDER).form(FormEnum.SCHEDA_PROMO)
                .field(ElementFieldEnum.TAB_CONTROLLI).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_OWNER).type(ActionTypeEnum.RENDER).form(FormEnum.SCHEDA_PROMO)
                .field(ElementFieldEnum.TAB_OWNER).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_CONTROLLI_PIANIFICAZIONE).type(ActionTypeEnum.RENDER)
                .form(FormEnum.PIANIFICAZIONE).field(ElementFieldEnum.TAB_CONTROLLI_PIANIFICAZIONE).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_SOVRAPPOSIZIONI_PIANIFICAZIONE).type(ActionTypeEnum.RENDER)
                .form(FormEnum.PIANIFICAZIONE).field(ElementFieldEnum.TAB_SOVRAPPOSIZIONI_PIANIFICAZIONE).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TEXTBOX_WARNING_NEGOZI).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TEXTBOX_WARNING_NEGOZI).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_NEGOZI_CEDI).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TAB_NEGOZI_CEDI).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_NEGOZI_ZONA).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TAB_NEGOZI_ZONA).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.TAB_INIZIATIVA).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TAB_INIZIATIVA).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_SCEGLI_INIZIATIVA).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.BUTTON_SCEGLI_INIZIATIVA).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.BUTTON_ELIMINA_ASSOCIAZIONE_INIZIATIVA).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.BUTTON_ELIMINA_ASSOCIAZIONE_INIZIATIVA).build();
        actions.add(action);

        action = Action.builder().code(ActionEnum.RENDER_NEGOZI_PUNTO_VENDITA).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.NEGOZI_TIPO_PUNTO_VENDITA).build();
        actions.add(action);

        action = Action.builder().code(ActionEnum.RENDER_NEGOZI_CANALE_CONSEGNA).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.NEGOZI_CANALE_CONSEGNA).build();
        actions.add(action);

        action = Action.builder().code(ActionEnum.TAB_FLAG_TESTATA).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TAB_FLAG).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.EDIT_FLAG_TESTATA).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TAB_FLAG).build();
        actions.add(action);

        action = Action.builder().code(ActionEnum.TAB_ATTRIBUTI_TESTATA).type(ActionTypeEnum.RENDER)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TAB_ATTRIBUTI).build();
        actions.add(action);
        action = Action.builder().code(ActionEnum.EDIT_ATTRIBUTI_TESTATA).type(ActionTypeEnum.ACTIVE)
                .form(FormEnum.SCHEDA_PROMO).field(ElementFieldEnum.TAB_ATTRIBUTI).build();
        actions.add(action);
    }

    public synchronized Action find(@NonNull String codiceAzione) {
        return actions.stream().filter(a -> codiceAzione.equals(a.getCode().getName())).findFirst().orElse(null);
    }
}
