package com.axiante.mui.persistence.entity;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.business.SecurityEnumConverter;
import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

@Entity
@Table(name = "PIANO_MEDIA_SEC", schema = Metadata.SCHEMA)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries(value = {
    @NamedQuery(name = "PianoMediaSecurityEntity.findByUser",
            query = "Select p from PianoMediaSecurityEntity p where :user member of p.group.users"),
        @NamedQuery(name = "PianoMediaSecurityEntity.findAll",
                query = "Select p from PianoMediaSecurityEntity p"),
})
public class PianoMediaSecurityEntity {
    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, sequenceName = "PIANO_MEDIA_SEC_ID_SEQ", allocationSize = 1,
            name = "pianoMediaSecuritySeq")
    @GeneratedValue(generator = "pianoMediaSecuritySeq", strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPPO", nullable = false)
    private GroupEntity group;

    @Convert(converter = SecurityEnumConverter.class)
    @Column(name = "scheda")
    private PianificazioneSecurityEnum accessoScheda = PianificazioneSecurityEnum.READ;

    @Convert(converter = SecurityEnumConverter.class)
    @Column(name = "pianificazione")
    private PianificazioneSecurityEnum accessoPianificazione = PianificazioneSecurityEnum.READ;

    @Basic
    @Column(name = "pianificazione_compratore")
    @Convert(converter = BooleanConverter.class)
    private Boolean pianificazioneCompratore = Boolean.FALSE;

    @Basic
    @Column(name = "filtro_articoli")
    @Convert(converter = BooleanConverter.class)
    private Boolean filtroArticoli = Boolean.FALSE;

}
