package com.axiante.mui.webapp.views.content.dbpromo.specialPromotion;

import com.axiante.mui.dbpromo.persistence.entities.MuiSpStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.Dependent;
import java.io.Serializable;

@Dependent
@Getter
@Setter
public class StatoCompratoreBackingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private MuiSpTestataEntity specialPromotionCorrente;
    private MuiSpStatoEntity statoCorrente;

    public void loadData(MuiSpTestataEntity specialPromotionCorrente, MuiSpStatoEntity statoCorrente) {
        this.specialPromotionCorrente = specialPromotionCorrente;
        this.statoCorrente = statoCorrente;
    }

    public void clear() {
        this.specialPromotionCorrente = null;
        this.statoCorrente = null;
    }

    public boolean isDisponibile() {
        return statoCorrente != null
                && statoCorrente.getStatoPromozioneEntity() != null
                && !"10".equals(statoCorrente.getStatoPromozioneEntity().getCodiceStato());
    }

    public String getDescrizioneHeader() {
        if (specialPromotionCorrente == null) {
            return "";
        }

        if (specialPromotionCorrente.getDescrizioneEstesa() != null
                && !specialPromotionCorrente.getDescrizioneEstesa().trim().isEmpty()) {
            return specialPromotionCorrente.getDescrizioneEstesa();
        }

        return specialPromotionCorrente.getDescrizione() == null
                ? ""
                : specialPromotionCorrente.getDescrizione();
    }

    public String getStatoHeader() {
        if (statoCorrente == null || statoCorrente.getStatoPromozioneEntity() == null) {
            return "";
        }

        String codiceStato = statoCorrente.getStatoPromozioneEntity().getCodiceStato();
        String descrizioneStato = statoCorrente.getStatoPromozioneEntity().getDescrizione();

        if (codiceStato == null && descrizioneStato == null) {
            return "";
        }

        if (codiceStato == null) {
            return descrizioneStato;
        }

        if (descrizioneStato == null) {
            return codiceStato;
        }

        return codiceStato + " - " + descrizioneStato;
    }
}