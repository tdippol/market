package com.axiante.mui.webapp.views.content.admin.dto;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CfgLoghiMessaggiConfigurationDTO implements Serializable {
    private static final long serialVersionUID = -6157872037305669992L;

    @EqualsAndHashCode.Include
    private Long codiceCanale;

    @EqualsAndHashCode.Include
    private String codiceMeccanica;

    @EqualsAndHashCode.Include
    String codiceDispositivo;

    @EqualsAndHashCode.Include
    private Short seqStampa;

    private String sezione = MessaggiSezioneEnum.MESSAGGI.name();
    private String testo;
    private Boolean taglioCarta;
    private Boolean barcode;
    private String font;
    private String allineamento;
    private Boolean bold;
    private String logo;
    private MuiBottoneEntity bottone;
    private Boolean codice;
    private String regolamento;
    private Boolean barra;
    private MuiFontStileEntity fontStile;
    private String variabile;

    private Integer idMessaggio = 101;

    private MuiFontEntities fontEntity;

    public MuiCfgDefaultCastellettoMessaggiEntity update(MuiCfgDefaultCastellettoMessaggiEntity entity) {
        entity.setCodiceCanale(this.codiceCanale);
        entity.setCodiceMeccanica(this.codiceMeccanica);
        entity.setCodiceDispositivo(this.codiceDispositivo);
        entity.setSeqStampa(this.seqStampa);
        entity.setSezione(this.sezione != null ? MessaggiSezioneEnum.valueOf(this.sezione) : null);
        entity.setTesto(this.testo);
        entity.setTaglioCarta(this.taglioCarta);
        entity.setBarcode(this.barcode);
        entity.setFont(this.font != null ? MessaggiFontEnum.fromValue(this.font) : null);
        entity.setAllineamento(this.allineamento != null ? MessaggiAllineamentoEnum.fromValue(this.allineamento) : null);
        entity.setBold(this.bold);
        entity.setLogo(this.logo);
        entity.setBottone(this.bottone);
        entity.setCodice(this.codice);
        entity.setRegolamento(this.regolamento);
        entity.setBarra(this.barra);
        entity.setFontStile(this.fontStile);
        entity.setIdMessaggio(this.idMessaggio);
        entity.setVariabile(this.variabile);
        entity.setFontEntity(fontEntity);
        return entity;
    }

    public CfgLoghiMessaggiConfigurationDTO(MuiCfgDefaultCastellettoMessaggiEntity entity) {
        this.codiceCanale = entity.getCodiceCanale();
        this.codiceMeccanica = entity.getCodiceMeccanica();
        this.codiceDispositivo = entity.getCodiceDispositivo();
        this.seqStampa = entity.getSeqStampa();
        this.sezione = entity.getSezione() != null ? entity.getSezione().name() : null;
        this.testo = entity.getTesto();
        this.taglioCarta = entity.getTaglioCarta();
        this.barcode = entity.getBarcode();
        this.font = entity.getFont() != null ? entity.getFont().getValue() : null;
        this.allineamento = entity.getAllineamento() != null ? entity.getAllineamento().getValue() : null;
        this.bold = entity.getBold();
        this.logo = entity.getLogo();
        this.bottone = entity.getBottone();
        this.codice = entity.getCodice();
        this.regolamento = entity.getRegolamento();
        this.barra = entity.getBarra();
        this.fontStile = entity.getFontStile();
        this.idMessaggio = entity.getIdMessaggio();
        this.variabile = entity.getVariabile();
        this.fontEntity = entity.getFontEntity();
    }

}
