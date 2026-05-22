package com.axiante.mui.dbpromo.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Getter
public class ReportVendutoId implements Serializable {
    private static final long serialVersionUID = -8074662518381938112L;

    @Column(name = "ID_PROMOZIONE", nullable = false)
    private Long idPromozione;

    @Column(name = "CODICE_ITEM", nullable = false)
    private String codiceItem;

    @Column(name = "DESCRIZIONE_ITEM")
    private String descrizioneItem;

    @Column(name = "CODICE_COMPRATORE", nullable = false)
    private String codiceCompratore;

    @Column(name = "DESCRIZIONE_COMPRATORE")
    private String descrizioneCompratore;

    @Column(name = "CODICE_FORNITORE")
    private String codiceFornitore;

    @Column(name = "DESCRIZIONE_FORNITORE")
    private String descrizioneFornitore;

    @Column(name = "CODICE_REPARTO", nullable = false)
    private String codiceReparto;

    @Column(name = "DESCRIZIONE_REPARTO")
    private String descrizioneReparto;

    @Column(name = "CODICE_MECCANICA", nullable = false)
    private String codiceMeccanica;

    @Column(name = "DESCRIZIONE_MECCANICA", nullable = false)
    private String descrizioneMeccanica;
}
