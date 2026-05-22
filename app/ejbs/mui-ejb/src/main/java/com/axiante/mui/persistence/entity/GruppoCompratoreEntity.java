package com.axiante.mui.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.axiante.mui.business.SecurityEnumConverter;
import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.Metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "GRUPPO_COMPRATORE", schema = Metadata.SCHEMA)

@NamedQueries(value = {
		@NamedQuery(name = "GruppoCompratoreEntity.findByGruppoAndCanale", query = "SELECT g FROM GruppoCompratoreEntity g WHERE g.gruppo = :gruppo AND g.compratore = :compratore"),
		@NamedQuery(name = "GruppoCompratoreEntity.findAllByGruppo", query = "SELECT g FROM GruppoCompratoreEntity g WHERE g.gruppo = :gruppo"),
		@NamedQuery(name = "GruppoCompratoreEntity.countByGruppoAndCompratore", query = "SELECT count(g) FROM GruppoCompratoreEntity g WHERE g.gruppo = :gruppo AND g.compratore = :compratore"),
		@NamedQuery(name = "GruppoCompratoreEntity.findByGruppoAndCompratore", query = "SELECT g FROM GruppoCompratoreEntity g WHERE g.gruppo = :gruppo AND g.compratore = :compratore"),
		@NamedQuery(name = "GruppoCompratoreEntity.findAll", query = "SELECT g FROM GruppoCompratoreEntity g "),
		@NamedQuery(name = "GruppoCompratoreEntity.findAllCodiciCompratoreByGroupAndTipoAccesso",
				query = "SELECT e.compratore.codiceCompratore FROM GruppoCompratoreEntity e WHERE e.gruppo IN :gruppo AND e.tipoAccesso = :tipoAccesso"),
		@NamedQuery(name = "GruppoCompratoreEntity.findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso",
				query = "SELECT e.compratore.codiceCompratore FROM GruppoCompratoreEntity e WHERE e.gruppo.codiceGruppo IN :gruppo AND e.tipoAccesso = :tipoAccesso"),
		@NamedQuery(name = "GruppoCompratoreEntity.findAllCodiciCompratoreByCodiciGruppo",
				query = "SELECT e.compratore.codiceCompratore FROM GruppoCompratoreEntity e WHERE e.gruppo.codiceGruppo IN :codiciGruppo")
})
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GruppoCompratoreEntity {
	@EmbeddedId
	private GruppoCompratoreId id;

	@ManyToOne
	@MapsId("groupId")
	@JoinColumn(name = "ID_GRUPPO")
	GroupEntity gruppo;

	@ManyToOne
	@MapsId("compratoreId")
	@JoinColumn(name = "ID_COMPRATORE")
	CompratoreEntity compratore;

	@Convert(converter = SecurityEnumConverter.class)
	@Column(name = "TIPO_ACCESSO")
	private PianificazioneSecurityEnum tipoAccesso;

	public void setTipoAccesso(PianificazioneSecurityEnum accesso) {
		if ( accesso == null ) {
			this.tipoAccesso = PianificazioneSecurityEnum.READ;
		} else {
			this.tipoAccesso = accesso;
		}
	}
	public PianificazioneSecurityEnum getTipoAccesso() {
		return this.tipoAccesso == null ? PianificazioneSecurityEnum.READ : this.tipoAccesso;
	}

}
