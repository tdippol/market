package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CHECK_TESTATA", schema = Metadata.SCHEMA)
@NoArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class CheckTestataEntity implements Serializable, DbPromoEntityInterface {
	private static final long serialVersionUID = 3064512283163997491L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CHECK_TESTATA_ID_GENERATOR", sequenceName = "MUI_CHECK_TESTATA_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CHECK_TESTATA_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	@EqualsAndHashCode.Include
	private Long id;

	//	TIPO_CONTROLLO VARCHAR(50),
	@Column(name="TIPO_CONTROLLO", length = 50)
	String tipoControllo;
	//	DESC_CONTROLLO VARCHAR(200),
	@Column(name="DESC_CONTROLLO", length = 200)
	String descrizioneControllo;
	//	DESC_COMPRATORE VARCHAR(100),
	@Column(name="DESC_COMPRATORE", length = 100)
	String descrizioneCompratore;
	//	SEVERITA VARCHAR(50)
	@Column(name="SEVERITA", length = 50)
	String severita;
	@Column(name="AMBITO_CONTROLLO", length = 50)
	String ambito;
	//	ID_PROMOZIONE BIGINT REFERENCES MUI_PROMOZIONE_TESTATA(ID),
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INSERIMENTO")
	private Date dataInserimento;
	@Column(name = "CODICE_UTENTE_INSERIMENTO")
	private String codiceUtenteInserimento;

	@ManyToOne
	@JoinColumn(name = "ID_PROMOZIONE")
	private PromozioneTestataEntity promozioneTestataEntity;


}
