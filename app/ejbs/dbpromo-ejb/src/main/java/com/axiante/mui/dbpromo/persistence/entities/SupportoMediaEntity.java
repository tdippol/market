package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Getter
@Setter
@Table(name = "MUI_SUPPORTO_MEDIA", schema = Metadata.SCHEMA)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
        @NamedQuery(name = "SupportoMediaEntity.findByCode",
                query = "SELECT e FROM SupportoMediaEntity e WHERE e.codiceMedia = :codice"),
        @NamedQuery(name = "SupportoMediaEntity.findAllAttivi",
                query = "SELECT e FROM SupportoMediaEntity e WHERE e.attivo = true")
})
public class SupportoMediaEntity implements Serializable, DbPromoEntityInterface, AuditLogInterface {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(schema = Metadata.SCHEMA, allocationSize = 1, name = "MUI_SUPPORTO_MEDIA_ID_GENERATOR", sequenceName = "MUI_SUPPORTO_MEDIA_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_SUPPORTO_MEDIA_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    private Long id;

    @Column(name = "CODICE_MEDIA")
    @EqualsAndHashCode.Include
    private String codiceMedia;

    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @Column(name = "COLORE")
    private String colore = DbPromoConstants.PIANO_MEDIA_DEFAULT_COLOR;

    @OneToMany(mappedBy = "supportoMedia",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            orphanRemoval = true)
    private Set<SupportoMediaCfgCheckEntity> supportoMediaChecks;

    @OneToMany(mappedBy = "supportoMedia", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, orphanRemoval = true)
    private Set<PianificazionePianoMediaEntity> pianificazioniPianoMedia;

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

    @Convert(converter = BooleanConverter.class)
    @Column(name = "FL_ATTIVO")
    private Boolean attivo = true;

    public SupportoMediaCfgCheckEntity addCheck(SupportoMediaCfgCheckEntity check) {
        if (supportoMediaChecks == null) {
            supportoMediaChecks = new HashSet<>();
        }
        check.setSupportoMedia(this);
        supportoMediaChecks.add(check);
        return check;
    }

    public SupportoMediaCfgCheckEntity removeCheck(SupportoMediaCfgCheckEntity check) {
        if (supportoMediaChecks != null) {
            supportoMediaChecks.remove(check);
        }
        check.setSupportoMedia(null);
        return check;
    }
}
