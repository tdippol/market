package com.axiante.mui.dbpromo.persistence.dto;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiAllineamentoEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiFontEnum;
import com.axiante.mui.dbpromo.business.enumeration.MessaggiSezioneEnum;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiBottoneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class CastellettoMessaggiDTO {

    private final Long id;
    private final PromozionePianificazioneEntity pianificazione;
    private final Integer seqStampa;
    private final MessaggiSezioneEnum sezione;
    private final String testo;
    private final Boolean taglioCarta;
    private final Boolean barcode;
    private final MessaggiFontEnum font;
    private final MessaggiAllineamentoEnum allineamento;
    private final Boolean bold;
    private final String logo;
    private final String codiceUtenteAggiornamento;
    private final String codiceUtenteInserimento;
    private final Date dataInserimento;
    private final Date dataAggiornamento;
    private final String codiceCanaleDispositivo;
    private final MuiFontStileEntity fontStile;
    private final MuiBottoneEntity bottone;
    private final Boolean codice;
    private final String regolamento;
    private final Boolean barra;
    private final Integer idMessaggio;
    private final String variabile;
    private final String fontEntity;
    @Setter
    private String descrizione;
    @Setter
    private String descrizioneFontEntity;

    public CastellettoMessaggiDTO(CastellettoMessaggiEntity entity) {
        this.id = entity.getId();
        this.pianificazione = entity.getPianificazione();
        this.seqStampa = entity.getSeqStampa();
        this.sezione = entity.getSezione();
        this.testo = entity.getTesto();
        this.taglioCarta = entity.getTaglioCarta();
        this.barcode = entity.getBarcode();
        this.font = entity.getFont();
        this.allineamento = entity.getAllineamento();
        this.bold = entity.getBold();
        this.logo = entity.getLogo();
        this.codiceUtenteAggiornamento = entity.getCodiceUtenteAggiornamento();
        this.codiceUtenteInserimento = entity.getCodiceUtenteInserimento();
        this.dataAggiornamento = entity.getDataAggiornamento();
        this.dataInserimento = entity.getDataInserimento();
        this.codiceCanaleDispositivo = entity.getCodiceCanaleDispositivo();
        this.fontStile = entity.getFontStile();
        this.bottone = entity.getBottone();
        this.codice = entity.getCodice();
        this.regolamento = entity.getRegolamento();
        this.barra = entity.getBarra();
        this.idMessaggio = entity.getIdMessaggio();
        this.variabile = entity.getVariabile();
        this.fontEntity = entity.getFontEntity();
    }

    public CastellettoMessaggiEntity toEntity() {
        CastellettoMessaggiEntity entity = new CastellettoMessaggiEntity();
        entity.setId(this.id);
        entity.setPianificazione(this.pianificazione);
        entity.setSeqStampa(this.seqStampa);
        entity.setSezione(this.sezione);
        entity.setTesto(this.testo);
        entity.setTaglioCarta(this.taglioCarta);
        entity.setBarcode(this.barcode);
        entity.setFont(this.font);
        entity.setAllineamento(this.allineamento);
        entity.setBold(this.bold);
        entity.setLogo(this.logo);
        entity.setCodiceUtenteAggiornamento(this.codiceUtenteAggiornamento);
        entity.setCodiceUtenteInserimento(this.codiceUtenteInserimento);
        entity.setDataAggiornamento(this.dataAggiornamento);
        entity.setDataInserimento(this.dataInserimento);
        entity.setCodiceCanaleDispositivo(this.codiceCanaleDispositivo);
        entity.setFontStile(this.fontStile);
        entity.setBottone(this.bottone);
        entity.setCodice(this.codice);
        entity.setRegolamento(this.regolamento);
        entity.setBarra(this.barra);
        entity.setIdMessaggio(this.idMessaggio);
        entity.setVariabile(this.variabile);
        entity.setFontEntity(this.fontEntity);

        return entity;
    }

}
