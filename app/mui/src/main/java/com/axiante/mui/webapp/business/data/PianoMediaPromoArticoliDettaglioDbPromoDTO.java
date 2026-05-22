package com.axiante.mui.webapp.business.data;

import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoArticoliDettaglioDbPromoEntity;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class PianoMediaPromoArticoliDettaglioDbPromoDTO {
    @EqualsAndHashCode.Include
    String codicePromozione;
    @EqualsAndHashCode.Include
    String codiceItem;
    @EqualsAndHashCode.Include
    String tipoItem;
    @EqualsAndHashCode.Include
    String codiceMeccanica;
    String codiceCondizione;
    Boolean flVolantino;
    Date dataInizio;
    Date dataFine;
    @EqualsAndHashCode.Include
    String codiceZona;
    @EqualsAndHashCode.Include
    String codiceSocieta;
    String valore;
    // #5104: la zona viene letta dalla vista V_MUI_PN_MED_PROMO_DBP_ART_L2
    String zona;

    PianoMediaPromoArticoliDettaglioDbPromoDTO(@NonNull PianoMediaPromoArticoliDettaglioDbPromoEntity entity) {
        codicePromozione = entity.getCodicePromozione();
        codiceItem = entity.getCodiceItem();
        tipoItem = entity.getTipoItem();
        codiceMeccanica = entity.getCodiceMeccanica();
        codiceCondizione = entity.getCodiceCondizione();
        flVolantino = entity.getFlVolantino();
        dataInizio = entity.getDataInizio();
        dataFine = entity.getDataFine();
        codiceZona = entity.getCodiceZona();
        codiceSocieta = entity.getCodiceSocieta();
        valore = entity.getValore();
        zona = entity.getDescrizioneZona();
    }

    PianoMediaPromoArticoliDettaglioDbPromoDTO merge(PianoMediaPromoArticoliDettaglioDbPromoDTO that) {
        if (this.equals(that)) {
            valore += "\n" + that.valore;
        }
        return this;
    }

    boolean isDifferenziato(PianoMediaPromoArticoliDettaglioDbPromoDTO that) {
        return !codiceMeccanica.equals(that.codiceMeccanica) ||
                !flVolantino.equals(that.flVolantino) ||
                !dataInizio.equals(that.dataInizio) ||
                !dataFine.equals(that.dataFine);
    }
}
