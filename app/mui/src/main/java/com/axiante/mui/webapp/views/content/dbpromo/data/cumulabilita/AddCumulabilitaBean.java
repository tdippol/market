package com.axiante.mui.webapp.views.content.dbpromo.data.cumulabilita;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Getter
public class AddCumulabilitaBean {
    String descrizione;
    String prefissoBs;
    String codicePromo;
    Date dataInizio;
    Date dataFine;

    boolean confirmBtnDisabled = true;

    MuiReportBSEntity selectedBuono;

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        this.confirmBtnDisabled = !isValidCumulabilita();
    }

    public void setPrefissoBs(String prefissoBs) {
        this.prefissoBs = prefissoBs;
        this.confirmBtnDisabled = !isValidCumulabilita();
    }

    public void setCodicePromo(String codicePromo) {
        this.codicePromo = codicePromo;
        this.confirmBtnDisabled = !isValidCumulabilita();
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
        this.confirmBtnDisabled = !isValidCumulabilita();
    }

    public void setDataFine(Date datafine) {
        this.dataFine = datafine;
        this.confirmBtnDisabled = !isValidCumulabilita();
    }

    public void setData(MuiReportBSEntity e) {
        this.prefissoBs = e.getId().getPrefissoBS();
        this.codicePromo = e.getId().getCodicePromozione();
        this.dataInizio = e.getDataInizio();
        this.dataFine = e.getDataFine();
        this.confirmBtnDisabled = !isValidCumulabilita();
        this.selectedBuono = e;
    }

    public void reset() {
        this.descrizione = null;
        this.prefissoBs = null;
        this.codicePromo = null;
        this.dataInizio = null;
        this.dataFine = null;
        this.confirmBtnDisabled = !isValidCumulabilita();
        this.selectedBuono = null;
    }

    public boolean isValidCumulabilita() {
        DateTimeUtils dtUtils = new DateTimeUtils();
        return !StringUtils.isBlank(descrizione) && dataInizio != null
                && dataFine != null && !StringUtils.isBlank(prefissoBs)
                && !StringUtils.isBlank(codicePromo)
                && dtUtils.isAfter(dataFine, dataInizio, false);
    }

    public CumulabilitaEsclusivitaEntity toCumulabilitaBean() {
        final CumulabilitaEsclusivitaEntity entity = new CumulabilitaEsclusivitaEntity();
        entity.setDescrizione(getDescrizione().toUpperCase().trim());
        entity.setCodicePromozione(getCodicePromo());
        entity.setPrefissoBS(getPrefissoBs());
        entity.setDataInizio(getDataInizio());
        entity.setDataFine(getDataFine());
        return entity;
    }
}
