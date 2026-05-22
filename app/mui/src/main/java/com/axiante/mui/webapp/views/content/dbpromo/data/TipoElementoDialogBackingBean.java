package com.axiante.mui.webapp.views.content.dbpromo.data;

import lombok.Getter;
import lombok.Setter;

public class TipoElementoDialogBackingBean {

    @Getter
    Integer raggruppamento = 0;

    @Getter
    @Setter
    boolean totale;

    @Getter
    @Setter
    boolean reparto;

    @Getter
    @Setter
    boolean grm;

    @Getter
    @Setter
    boolean articolo = true;

    @Getter
    @Setter
    boolean attributo;

    @Getter
    @Setter
    boolean omogeneo;

    @Getter
    boolean confirmBtnDisabled = false;

    public void setRaggruppamento(Integer raggruppamento) {
        this.raggruppamento = raggruppamento;
        this.confirmBtnDisabled = this.raggruppamento == null;
    }

    public void resetDialog() {
        setRaggruppamento(0);
        setTotale(false);
        setReparto(false);
        setGrm(false);
        setArticolo(true);
        setAttributo(false);
        setOmogeneo(false);
        confirmBtnDisabled = false;
    }

    public boolean isValid() {
        return raggruppamento != null;
    }
}
