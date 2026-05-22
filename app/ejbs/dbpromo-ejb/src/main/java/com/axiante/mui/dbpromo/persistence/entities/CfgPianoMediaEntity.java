package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.business.enumeration.PianoMediaTipoData;
import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MUI_CFG_PIANO_MEDIA", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries({
		@NamedQuery(name = "CfgPianoMediaEntity.findAllAttivi", query = "SELECT c FROM CfgPianoMediaEntity c WHERE c.supportoMedia.attivo = true "),
})
public class CfgPianoMediaEntity implements Serializable{
	private static final long serialVersionUID = 7218124088024339326L;
	@EqualsAndHashCode.Include
	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_CFG_PIANO_MEDIA_ID_GENERATOR", sequenceName = "MUI_CFG_PIANO_MEDIA_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_CFG_PIANO_MEDIA_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16, name = "ID")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_SUPPORTO_MEDIA", nullable = false)
	SupportoMediaEntity supportoMedia;
	@Column(name = "TIPO_DATA_INIZIO")
	@Enumerated(EnumType.STRING)
	PianoMediaTipoData tipoDataInizio;
	@Column(name = "SCOSTAMENTO")
	Integer scostamento;
	@Column(name = "TIPO_DATA_FINE")
	@Enumerated(EnumType.STRING)
	PianoMediaTipoData tipoDataFine;
	@Column(name = "DURATA")
	Integer durata;
}
