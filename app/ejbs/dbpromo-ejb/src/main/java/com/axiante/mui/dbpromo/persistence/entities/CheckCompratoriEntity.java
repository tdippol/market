package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@Table(name = "MUI_CHECK_COMPRATORI", schema = Metadata.SCHEMA)
@NoArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@NamedQueries(value = {
		@NamedQuery(name = "CheckCompratoriEntity.findByPromozione",
				query = "Select c from  CheckCompratoriEntity c where c.promozioneTestataEntity = :promozione"),
		@NamedQuery(name = "CheckCompratoriEntity.findByCompratore",
				query = "Select c from  CheckCompratoriEntity c where c.compratoreEntity = :compratore"),
		@NamedQuery(name = "CheckCompratoriEntity.findByPromozioneAndCompratore",
				query = "Select c from  CheckCompratoriEntity c where c.promozioneTestataEntity = :promozione and c.compratoreEntity = :compratore"),
		@NamedQuery(name = "CheckCompratoriEntity.countByPromozioneAndCompratore",
				query = "Select COUNT(c) from  CheckCompratoriEntity c where c.promozioneTestataEntity = :promozione and c.compratoreEntity = :compratore")
})
public class CheckCompratoriEntity implements Serializable, DbPromoEntityInterface, AuditLogInterface {
	private static final long serialVersionUID = 3064512283163997491L;

	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CHECK_COMPRATORI_ID_GENERATOR", sequenceName = "MUI_CHECK_COMPRATORI_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CHECK_COMPRATORI_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name="ESITO", length = 50)
	String esito;
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
	//	ID_PROMOZIONE BIGINT REFERENCES MUI_PROMOZIONE_TESTATA(ID),
	@ManyToOne
	@JoinColumn(name = "ID_PROMOZIONE")
	private PromozioneTestataEntity promozioneTestataEntity;

	@ManyToOne
	@JoinColumn(name = "ID_COMPRATORE")
	private CompratoreEntity compratoreEntity;
	
	@Column(name="FL_MODIFICATO")
	@Convert(converter = BooleanConverter.class)
	Boolean modificato;


}
