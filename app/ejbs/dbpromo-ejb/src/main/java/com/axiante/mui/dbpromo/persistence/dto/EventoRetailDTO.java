package com.axiante.mui.dbpromo.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class EventoRetailDTO {
    private Long id;
    private String codiceFornitore;
    private String codiceCausale;
    private Date dataInizio;
    private Date dataFine;
    private BigDecimal valoreContributo;
    private String note;
    private String causaleDescription;
    private String fornitoreDescription;
    private MacrospazioMediaDTO macrospazio;

    public EventoRetailDTO(
            Long id,
            String codiceFornitore,
            String codiceCausale,
            Date dataInizio,
            Date dataFine,
            BigDecimal valoreContributo,
            String note,
            String causaleDescription,
            String fornitoreDescription,
            Long macrospazioId,
            String macrospazioCodice,
            String macrospazioDescrizione,
            Date macrospazioDataInizio,
            Date macrospazioDataFine,
            Date macrospazioDataInserimento,
            String macrospazioCodiceUtenteInserimento,
            Date macrospazioDataAggiornamento,
            String macrospazioCodiceUtenteAggiornamento
    ) {
        this.id = id;
        this.codiceFornitore = codiceFornitore;
        this.codiceCausale = codiceCausale;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.valoreContributo = valoreContributo;
        this.note = note;
        this.causaleDescription = causaleDescription;
        this.fornitoreDescription = fornitoreDescription;
        this.macrospazio = new MacrospazioMediaDTO(
                macrospazioId,
                macrospazioCodice,
                macrospazioDescrizione,
                macrospazioDataInizio,
                macrospazioDataFine,
                macrospazioDataInserimento,
                macrospazioCodiceUtenteInserimento,
                macrospazioDataAggiornamento,
                macrospazioCodiceUtenteAggiornamento
        );
    }

    public String getFornitoreString() {
        return codiceFornitore + " - " + fornitoreDescription;
    }

    public String getMacrospazioString() {
        return getMacrospazio() != null
                ? String.format("%s - %s", getMacrospazio().getCodice(), getMacrospazio().getDescrizione())
                : "";
    }

    public String getCausaleString() {
        return codiceCausale + " - " + causaleDescription;
    }
}