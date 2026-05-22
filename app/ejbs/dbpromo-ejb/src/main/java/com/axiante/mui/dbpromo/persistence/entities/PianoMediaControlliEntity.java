package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_PIANO_MEDIA_CONTROLLI", schema = Metadata.SCHEMA)
@NamedQueries({
        @NamedQuery(name = "PianoMediaControlliEntity.findByPianificazioneMedia",
                query = "SELECT e FROM PianoMediaControlliEntity e WHERE e.pianificazioneMedia = :pianificazioneMedia"),
        @NamedQuery(name = "PianoMediaControlliEntity.findByIdPianificazioniMedia",
                query = "SELECT e FROM PianoMediaControlliEntity e WHERE e.pianificazioneMedia.id IN :idPianificazioniMedia"),
        @NamedQuery(name = "PianoMediaControlliEntity.findByPianoMedia",
                query = "SELECT e FROM PianoMediaControlliEntity e WHERE e.pianificazioneMedia.pianoMedia = :pianoMedia"),
        @NamedQuery(name="PianoMediaControlliEntity.findByCodiceSupportoConfigurato",
                query="SELECT e FROM PianoMediaControlliEntity e WHERE e.supportoMediaCfgCheck.codiceControllo = :codiceControllo"),
        @NamedQuery(name="PianoMediaControlliEntity.countByCodiceSupportoConfigurato",
                query="SELECT COUNT(e) FROM PianoMediaControlliEntity e WHERE e.supportoMediaCfgCheck.codiceControllo = :codiceControllo"),
})
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PianoMediaControlliEntity implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = -1168732646190958623L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_MEDIA_CONTROLLI_ID_GENERATOR",
            sequenceName = "MUI_MEDIA_CONTROLLI_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_MEDIA_CONTROLLI_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PIANIFICAZIONE_MEDIA")
    private PianificazionePianoMediaEntity pianificazioneMedia;

    @ManyToOne
    @JoinColumn(name = "ID_SUPPORTO_MEDIA_CHECK")
    private SupportoMediaCfgCheckEntity supportoMediaCfgCheck;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_INSERIMENTO")
    private Date dataInserimento;

    @Column(name = "CODICE_UTENTE_INSERIMENTO", length = 50)
    private String codiceUtenteInserimento;
}
