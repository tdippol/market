package com.axiante.mui.webapp.views.content.dbpromo.data.logoMessaggi;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Slf4j
public class MessaggioFormBean implements Serializable {
    private static final long serialVersionUID = 5517156802740839982L;
    private static final Integer TESTO_MAX_LENGHT_DEFAULT = 4000;

    private Integer seqStampa;
    private Boolean taglioCarta;
    private Boolean barcode;
    private Boolean bold;
    private MessaggiFontEnum font;
    private MessaggiAllineamentoEnum allineamento;
    private String testo;
    private MuiBottoneEntity bottone;
    private Boolean codice;
    private String regolamento;
    private Boolean barra;
    private MuiFontStileEntity fontStile;
    private String variabile;
    private Integer idMessaggio;
    private String logoFilename;
    private String fontEntity;
    private Integer testoMaxLength = TESTO_MAX_LENGHT_DEFAULT;

    List<FormValueChangeListener> formValueListeners = new ArrayList<>();

    public void preloadDefaults(CfgCanaleDispositivoEntity dispositivo) {
        taglioCarta = Boolean.FALSE;
        barcode = Boolean.FALSE;
        bold = Boolean.FALSE;
        font = MessaggiFontEnum.NORMALE;
        allineamento = MessaggiAllineamentoEnum.SINISTRA;
        testo = null;
        seqStampa = null;
        idMessaggio = null;
        logoFilename = null;
        bottone = null;
        codice = Boolean.FALSE;
        regolamento = null;
        barra = Boolean.FALSE;
        fontStile = null;
        variabile = null;
        fontEntity = null;
        if (dispositivo != null) {
            testoMaxLength = dispositivo.getNumeroCaratteri() != null
                    ? dispositivo.getNumeroCaratteri() : TESTO_MAX_LENGHT_DEFAULT;
        } else {
            testoMaxLength = TESTO_MAX_LENGHT_DEFAULT;
        }
    }

    public void loadSelectedMessage(CastellettoMessaggiEntity messaggioSelected) {
        seqStampa = messaggioSelected.getSeqStampa();
        taglioCarta = messaggioSelected.getTaglioCarta();
        barcode = messaggioSelected.getBarcode();
        font = messaggioSelected.getFont();
        allineamento = messaggioSelected.getAllineamento();
        bold = messaggioSelected.getBold();
        testo = messaggioSelected.getTesto();
        logoFilename = messaggioSelected.getLogo();
        fontStile = messaggioSelected.getFontStile();
        bottone = messaggioSelected.getBottone();
        codice = messaggioSelected.getCodice();
        regolamento = messaggioSelected.getRegolamento();
        barra = messaggioSelected.getBarra();
        idMessaggio = messaggioSelected.getIdMessaggio();
        variabile = messaggioSelected.getVariabile();
        fontEntity = messaggioSelected.getFontEntity();
    }

    public void addFormValueChangeListener(FormValueChangeListener listener) {
        if (listener != null) {
            formValueListeners.add(listener);
        }
    }

    public void notifyFormValueChange(MessaggiComponentsEnum component, Object oldValue, Object newValue) {
        for (FormValueChangeListener listener : formValueListeners) {
            listener.onFormValueChange(component, oldValue, newValue);
        }
    }

    public void removeFormValueChangeListener(FormValueChangeListener listener) {
        formValueListeners.remove(listener);
    }

    public void setTaglioCarta(Boolean taglioCarta) {
        Boolean oldValue = this.taglioCarta;
        this.taglioCarta = taglioCarta;
        notifyFormValueChange(MessaggiComponentsEnum.TAGLIO_CARTA, oldValue, taglioCarta);
    }

    public void setBarcode(Boolean barcode) {
        Boolean oldValue = this.barcode;
        this.barcode = barcode;
        notifyFormValueChange(MessaggiComponentsEnum.BARCODE, oldValue, barcode);
    }

    public void reloadDefaultsBut(CfgCanaleDispositivoEntity dispositivo, MessaggiComponentsEnum component) {
        if (!component.equals(MessaggiComponentsEnum.TAGLIO_CARTA)) taglioCarta = Boolean.FALSE;
        if (!component.equals(MessaggiComponentsEnum.BARCODE)) barcode = Boolean.FALSE;
        if (!component.equals(MessaggiComponentsEnum.BOLD)) bold = Boolean.FALSE;
        if (!component.equals(MessaggiComponentsEnum.FONT)) font = MessaggiFontEnum.NORMALE;
        if (!component.equals(MessaggiComponentsEnum.ALLINEAMENTO))
            allineamento = MessaggiAllineamentoEnum.SINISTRA;
        if (!component.equals(MessaggiComponentsEnum.TESTO)) testo = null;
        if (!component.equals(MessaggiComponentsEnum.LOGO)) logoFilename = null;
        if (!component.equals(MessaggiComponentsEnum.BOTTONE)) bottone = null;
        if (!component.equals(MessaggiComponentsEnum.CODICE)) codice = Boolean.FALSE;
        if (!component.equals(MessaggiComponentsEnum.REGOLAMENTO)) regolamento = null;
        if (!component.equals(MessaggiComponentsEnum.BARRA)) barra = Boolean.FALSE;
        if (!component.equals(MessaggiComponentsEnum.FONT_STILE)) fontStile = null;
        if (!component.equals(MessaggiComponentsEnum.VARIABILE)) variabile = null;
        if (!component.equals(MessaggiComponentsEnum.FONT_ENTITY)) fontEntity = null;
        if (dispositivo != null) {
            testoMaxLength = dispositivo.getNumeroCaratteri() != null
                    ? dispositivo.getNumeroCaratteri()
                    : TESTO_MAX_LENGHT_DEFAULT;
        } else {
            testoMaxLength = TESTO_MAX_LENGHT_DEFAULT;
        }
    }

    public boolean messaggioSelectedChanged(@NonNull CastellettoMessaggiEntity messaggioSelected) {
        return !Objects.equals(idMessaggio, messaggioSelected.getIdMessaggio())
                || !Objects.equals(barcode, messaggioSelected.getBarcode())
                || !Objects.equals(codice, messaggioSelected.getCodice())
                || !Objects.equals(fontStile, messaggioSelected.getFontStile())
                || !Objects.equals(font, messaggioSelected.getFont())
                || !Objects.equals(allineamento, messaggioSelected.getAllineamento())
                || !Objects.equals(bold, messaggioSelected.getBold())
                || !Objects.equals(fontEntity, messaggioSelected.getFontEntity())
                || !Objects.equals(bottone, messaggioSelected.getBottone())
                || !Objects.equals(regolamento, messaggioSelected.getRegolamento())
                || !Objects.equals(taglioCarta, messaggioSelected.getTaglioCarta())
                || !Objects.equals(barra, messaggioSelected.getBarra())
                || !Objects.equals(variabile, messaggioSelected.getVariabile())
                || !Objects.equals(testo, messaggioSelected.getTesto())
                || !Objects.equals(logoFilename, messaggioSelected.getLogo());
    }
}
