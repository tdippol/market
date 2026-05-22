package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "MUI_PIANO_MEDIA_PIANIFICAZIONE", schema = Metadata.SCHEMA)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(name = "PianoMediaPianificazioneDettaglioEntity.findByPianoMedia",
                query = "SELECT e FROM PianoMediaPianificazioneDettaglioEntity e WHERE e.pianoMedia = :pianoMedia"),
})
public class PianoMediaPianificazioneDettaglioEntity implements Serializable {
    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PIANO_MEDIA_PIANIFICAZIONE_ID_GENERATOR",
            sequenceName = "MUI_PIAN_MED_PIANIFICAZ_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PIANO_MEDIA_PIANIFICAZIONE_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_piano", nullable = false)
    @EqualsAndHashCode.Include
    PianoMediaEntity pianoMedia;

    @Column(name = "CODICE_PROMOZIONE", nullable = false)
    @EqualsAndHashCode.Include
    String codicePromozione;

    @Column(name = "CODICE_ITEM", nullable = false)
    @EqualsAndHashCode.Include
    String codiceItem;

    @Column(name = "TIPO_ITEM", nullable = false, length = 2)
    @EqualsAndHashCode.Include
    String tipoItem;

    @Column(name = "CIVETTA")
    @Convert(converter = BooleanConverter.class)
    Boolean civetta = false;

    @Column(name = "PEZZO_RANK")
    Integer pezzoRank;

    @Column(name = "FATTURATO_RANK")
    Integer fatturatoRank;

    @Column(name = "NOTE_COMPRATORE")
    String noteCompratore;

    @OneToMany(mappedBy = "pianificazioneDettaglio", orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }
    )
    Set<PianificazioneAnagraficaPianoMediaEntity> pianificazioniAnagrafiche;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATO")
    StatoPromozioneEntity stato;

    public PianificazioneAnagraficaPianoMediaEntity addPianificazioneAnagrafica(@NonNull PianificazioneAnagraficaPianoMediaEntity pianificazioneAnagrafica) {
        if (getPianificazioniAnagrafiche() == null) {
            setPianificazioniAnagrafiche(new HashSet<>());
        }
        getPianificazioniAnagrafiche().add(pianificazioneAnagrafica);
        pianificazioneAnagrafica.setPianificazioneDettaglio(this);
        return pianificazioneAnagrafica;
    }

    public PianificazioneAnagraficaPianoMediaEntity removePianificazioneAnagrafica(@NonNull PianificazioneAnagraficaPianoMediaEntity pianificazioneAnagrafica) {
        if (getPianificazioniAnagrafiche() != null && pianificazioneAnagrafica.getPianificazioneDettaglio() != null
                && pianificazioneAnagrafica.getPianificazioneDettaglio().equals(this)) {
            getPianificazioniAnagrafiche().remove(pianificazioneAnagrafica);
            pianificazioneAnagrafica.setPianificazioneDettaglio(null);
        }
        return pianificazioneAnagrafica;
    }

}
