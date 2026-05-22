package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CHECK_PIANIFICAZIONE", schema = Metadata.SCHEMA)
@NamedQuery(name = "CheckPianificazioneEntity.findAllByPromozioneAndCodiciCompratore",
        query = "SELECT e FROM CheckPianificazioneEntity e WHERE e.testata = :testata AND e.compratore.codiceCompratore IN :codiciCompratore")
@EntityListeners(DbPromoRemoveOnlyEntity.class)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CheckPianificazioneEntity implements Serializable, DbPromoEntityInterface {
    private static final long serialVersionUID = 3320126519900608775L;

    @Id
    @SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CHECK_PIANIFICAZIONE_ID_GENERATOR", sequenceName = "MUI_CHECK_PIANIFICAZIONE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CHECK_PIANIFICAZIONE_ID_GENERATOR")
    @Column(unique = true, nullable = false, precision = 16)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name="TIPO_ELEMENTO", length = 20)
    private String tipoElemento;

    @Column(name="CODICE_ELEMENTO", length = 20)
    private String codiceElemento;

    @Column(name="ELEMENTO", length = 100)
    private String elemento;

    @Column(name="TIPO_CONTROLLO", length = 50)
    private String tipoControllo;

    @Column(name="DESC_CONTROLLO", length = 4000)
    private String descrizioneControllo;

    @Column(name="SEVERITA", length = 50)
    private String severita;

    @Column(name = "CODICE_UTENTE_INSERIMENTO", length = 50)
    private String codiceUtenteInserimento;

    @ManyToOne
    @JoinColumn(name = "ID_PROMOZIONE")
    private PromozioneTestataEntity testata;

    @ManyToOne
    @JoinColumn(name = "ID_COMPRATORE")
    private CompratoreEntity compratore;
    
    @ManyToOne
    @JoinColumn(name = "ID_PROMOZIONE_PIANIFICAZIONE")
    private PromozionePianificazioneEntity pianificazione;
    
}
