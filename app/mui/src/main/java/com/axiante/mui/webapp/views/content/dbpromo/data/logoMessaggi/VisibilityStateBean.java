package com.axiante.mui.webapp.views.content.dbpromo.data.logoMessaggi;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Getter
@Setter
@Slf4j
public class VisibilityStateBean implements Serializable {
    private boolean taglioCartaVisible = false;
    private boolean barcodeVisible = false;
    private boolean fontVisible = false;
    private boolean allineamentoVisible = false;
    private boolean boldVisible = false;
    private boolean testoVisible = false;
    private boolean logoVisible = false;
    private boolean bottoneVisible = false;
    private boolean codiceVisible = false;
    private boolean regolamentoVisible = false;
    private boolean seqStampaVisible = false;
    private boolean barraVisible = false;
    private boolean fontStileVisible = false;
    private boolean idMessaggioVisible = false;
    private boolean variabileVisible = false;
    private boolean fontEntityVisible = false;

    private boolean taglioCartaDisabled = false;
    private boolean barcodeDisabled = false;
    private boolean fontDisabled = false;
    private boolean allineamentoDisabled = false;
    private boolean boldDisabled = false;
    private boolean testoDisabled = false;
    private boolean logoDisabled = false;
    private boolean bottoneDisabled = false;
    private boolean codiceDisabled = false;
    private boolean regolamentoDisabled = false;
    private boolean seqStampaDisabled = false;
    private boolean barraDisabled = false;
    private boolean fontStileDisabled = false;
    private boolean idMessaggioDisabled = false;
    private boolean variabileDisabled = false;
    private boolean fontEntityDisabled = false;

    public void preloadDefaults(){
        logoDisabled = Boolean.FALSE;
        testoDisabled = Boolean.FALSE;

    }

  public void setDefaultActiveComponents(boolean editable) {
      idMessaggioDisabled = !editable;

      taglioCartaDisabled = !editable;
      barcodeDisabled = !editable;
      fontDisabled = !editable;
      allineamentoDisabled = !editable;
      boldDisabled = !editable;
      testoDisabled = !editable;
      logoDisabled = !editable;
      bottoneDisabled = !editable;
      codiceDisabled = !editable;
      regolamentoDisabled = !editable;
      seqStampaDisabled = true;
      barraDisabled = !editable;
      fontStileDisabled = !editable;
      variabileDisabled = !editable;
      fontEntityDisabled = !editable;
  }

        public void enableVisible(MessaggiComponentsEnum component) {
        switch (component) {
            case TAGLIO_CARTA:
                taglioCartaVisible = true;
                break;
            case BARCODE:
                barcodeVisible = true;
                break;
            case FONT:
                fontVisible = true;
                break;
            case ALLINEAMENTO:
                allineamentoVisible = true;
                break;
            case BOLD:
                boldVisible = true;
                break;
            case LOGO:
                logoVisible = true;
                break;
            case BARRA:
                barraVisible = true;
                break;
            case TESTO:
                testoVisible = true;
                break;
            case CODICE:
                codiceVisible = true;
                break;
            case BOTTONE:
                bottoneVisible = true;
                break;
            case FONT_STILE:
                fontStileVisible = true;
                break;
            case SEQ_STAMPA:
                seqStampaVisible = true;
                break;
            case REGOLAMENTO:
                regolamentoVisible = true;
                break;
            case ID_MESSAGGIO:
                idMessaggioVisible = true;
                break;
            case VARIABILE:
                variabileVisible = true;
                break;
            case FONT_ENTITY:
                fontEntityVisible = true;
                break;
            default:
                log.error("Unhandled component type {} ", component.getValue());
        }
    }

    public void disableComponent(MessaggiComponentsEnum comp){
        switch (comp) {
            case TAGLIO_CARTA:
                taglioCartaDisabled = true;
                break;
            case BARCODE:
                barcodeDisabled = true;
                break;
            case FONT:
                fontDisabled = true;
                break;
            case ALLINEAMENTO:
                allineamentoDisabled = true;
                break;
            case BOLD:
                boldDisabled = true;
                break;
            case LOGO:
                logoDisabled = true;
                break;
            case BARRA:
                barraDisabled = true;
                break;
            case TESTO:
                testoDisabled = true;
                break;
            case CODICE:
                codiceDisabled = true;
                break;
            case BOTTONE:
                bottoneDisabled = true;
                break;
            case FONT_STILE:
                fontStileDisabled = true;
                break;
            case SEQ_STAMPA:
                seqStampaDisabled = true;
                break;
            case REGOLAMENTO:
                regolamentoDisabled = true;
                break;
            case ID_MESSAGGIO:
                idMessaggioDisabled = true;
                break;
            case VARIABILE:
                variabileDisabled = true;
                break;
            case FONT_ENTITY:
                fontEntityDisabled = true;
                break;

            default:
                log.error("Unhandled component type {} ", comp.getValue());
        }
    }

    public void enableComponent(MessaggiComponentsEnum comp){
        switch (comp) {
            case TAGLIO_CARTA:
                taglioCartaDisabled = false;
                break;
            case BARCODE:
                barcodeDisabled = false;
                break;
            case FONT:
                fontDisabled = false;
                break;
            case ALLINEAMENTO:
                allineamentoDisabled = false;
                break;
            case BOLD:
                boldDisabled = false;
                break;
            case LOGO:
                logoDisabled = false;
                break;
            case BARRA:
                barraDisabled = false;
                break;
            case TESTO:
                testoDisabled = false;
                break;
            case CODICE:
                codiceDisabled = false;
                break;
            case BOTTONE:
                bottoneDisabled = false;
                break;
            case FONT_STILE:
                fontStileDisabled = false;
                break;
            case SEQ_STAMPA:
                seqStampaDisabled = false;
                break;
            case REGOLAMENTO:
                regolamentoDisabled = false;
                break;
            case ID_MESSAGGIO:
                idMessaggioDisabled = false;
                break;
            case VARIABILE:
                variabileDisabled = false;
                break;
            case FONT_ENTITY:
                fontEntityDisabled = false;
                break;

            default:
                log.error("Unhandled component type {} ", comp.getValue());
        }
    }
}