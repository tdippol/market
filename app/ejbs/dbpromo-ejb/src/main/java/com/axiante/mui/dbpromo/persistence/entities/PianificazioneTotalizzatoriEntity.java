package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_TOTALIZZATORI_PIANIF", schema = Metadata.SCHEMA)
@NamedQuery(name = "PianificazioneTotalizzatoriEntity.findAllWithParentByIdPianificazione",
        query = "SELECT e FROM PianificazioneTotalizzatoriEntity e WHERE e.pianificazione.id = :idPianificazione AND e.idParent IS NOT NULL")
@NoArgsConstructor
@EqualsAndHashCode
public class PianificazioneTotalizzatoriEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, nullable = false, precision = 16, name = "ID_TOTALIZZATORE")
    @JsonProperty("idTotalizzatore")
    private Long id;

    @Column(name = "DESCRIZIONE", length = 200)
    String descrizione;

    @Column(name = "ACTION_TYPE", precision = 16)
    Integer actionType;

    @Column(name = "SEGNO", precision = 1, scale = 0)
    private Integer segno;

    @Column(name = "EXPORT_VS", precision = 1, scale = 0)
    private Integer exportVs;

    @Column(precision = 16, name = "ID_PARENT")
    private Long idParent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PIANIFICAZIONE")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private PromozionePianificazioneEntity pianificazione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROMOZIONE")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private PromozioneTestataEntity testata;
}
