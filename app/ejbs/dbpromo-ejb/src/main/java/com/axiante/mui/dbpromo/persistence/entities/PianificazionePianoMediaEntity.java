package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MUI_PIANIFICAZIONE_PIANO", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "PianificazionePianoMediaEntity.findByPianoMedia",
                query = "SELECT e FROM PianificazionePianoMediaEntity e WHERE e.pianoMedia = :pianoMedia"),
        @NamedQuery(name = "PianificazionePianoMediaEntity.findAttiviByPianoMedia",
                query = "SELECT e FROM PianificazionePianoMediaEntity e WHERE e.pianoMedia = :pianoMedia and e.supportoMedia.attivo=true")


})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PianificazionePianoMediaEntity implements Serializable, AuditLogInterface, DbPromoEntityInterface {
    private static final long serialVersionUID = -7651146878009273627L;
    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_PIANIFICAZ_PIANO_ID_GENERATOR",
            sequenceName = "MUI_PIANIFICAZ_PIANO_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_PIANIFICAZ_PIANO_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MEDIA")
    SupportoMediaEntity supportoMedia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PIANO")
    PianoMediaEntity pianoMedia;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INIZIO")
    private Date dataInizio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_FINE")
    private Date dataFine;

    @Column(name = "CODICE_UTENTE_AGGIORNAMENTO")
    private String codiceUtenteAggiornamento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO")
    private String codiceUtenteInserimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_AGGIORNAMENTO")
    private Date dataAggiornamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

    @OneToMany(mappedBy = "pianificazioneMedia", orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }
    )
    Set<PianificazioneAnagraficaPianoMediaEntity> anagrafichePianificazione;

    public PianificazioneAnagraficaPianoMediaEntity addPianificazioneAnagrafica(PianificazioneAnagraficaPianoMediaEntity pianificazioneAnagrafica) {
        if ( getAnagrafichePianificazione() == null ){
            setAnagrafichePianificazione(new HashSet<>());
        }
        getAnagrafichePianificazione().add(pianificazioneAnagrafica);
        pianificazioneAnagrafica.setPianificazioneMedia(this);
        return pianificazioneAnagrafica;
    }

    public PianificazioneAnagraficaPianoMediaEntity removePianificazioneAnagrafica(PianificazioneAnagraficaPianoMediaEntity pianificazioneAnagrafica) {
        if (getAnagrafichePianificazione() != null && pianificazioneAnagrafica.getPianificazioneMedia() != null
                && pianificazioneAnagrafica.getPianificazioneMedia().equals(this)) {
            getAnagrafichePianificazione().remove(pianificazioneAnagrafica);
            pianificazioneAnagrafica.setPianificazioneMedia(null);
        }
        return pianificazioneAnagrafica;
    }
}
