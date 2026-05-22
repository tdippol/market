package com.axiante.mui.webapp.views.content.dbpromo.dtos;

import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import java.io.Serializable;
import java.util.Date;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RataDto implements Serializable {
    private static final long serialVersionUID = -7360443724343810317L;

    private ContributiPromoEntity entity;

    public RataDto(ContributiPromoEntity entity) {
        this.entity = entity;
    }

    public Long getId() {
        return entity.getId();
    }

    public String getLabel() {
        return String.format("%s - %s - Contributo %d", entity.getCompratore().getFullDescription(),
                entity.getFornitore().getFullDescription(), entity.getProgressivo());
    }

    public ContributiPromoEntity getEntity() {
        return entity;
    }

    public void setEntity(ContributiPromoEntity entity) {
        this.entity = entity;
    }

    public String getCodicePrestazione() {
        return entity != null ? entity.getCodicePrestazione() : null;
    }

    public Date getDataLiquidazione() {
        return entity != null ? entity.getDataLiquidazione() : null;
    }

    public Double getValore() {
        return entity != null ? entity.getValoreApplicato() : null;
    }

    public Double getSaldo() {
        return entity != null ? entity.getSaldoMovimenti() : null;
    }

    public String getNoteCompratore() {
        return entity != null ? entity.getNotaCompratore() : null;
    }

    public String getNoteFattura() {
        return entity != null ? entity.getNotaFattura() : null;
    }

    public String getCodiceStato() {
        return entity != null ? entity.getCodiceStato() : null;
    }
}
