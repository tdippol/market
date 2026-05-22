package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MUI_PIANO_MED_PIANIFICAZ_MED", schema = Metadata.SCHEMA)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(name = "PianificazioneAnagraficaPianoMediaEntity.findByPianificazioneDettaglioAndPianificazioneMedia",
                query = "SELECT e FROM PianificazioneAnagraficaPianoMediaEntity e WHERE e.pianificazioneDettaglio = :pianificazioneDettaglio and e.pianificazioneMedia = :pianificazioneMedia"),
})
public class PianificazioneAnagraficaPianoMediaEntity implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = -7651146878009273627L;
    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PIAN_MED_PIANIF_MED_ID_GENERATOR",
            sequenceName = "MUI_PIAN_MED_PIANIF_MED_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PIAN_MED_PIANIF_MED_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PIANIFICAZIONE")
    @EqualsAndHashCode.Include
    PianoMediaPianificazioneDettaglioEntity pianificazioneDettaglio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PIANIFICAZIONE_MEDIA")
    @EqualsAndHashCode.Include
    PianificazionePianoMediaEntity pianificazioneMedia;

    @Column(name = "ATTIVO")
    @Convert(converter = BooleanConverter.class)
    Boolean attivo = Boolean.FALSE;
}
