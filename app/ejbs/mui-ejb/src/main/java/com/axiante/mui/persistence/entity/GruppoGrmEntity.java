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
@Table(name = "GRUPPO_GRM", schema = Metadata.SCHEMA)

@NamedQueries(value = {
		@NamedQuery(name="GruppoGrmEntity.findAll",
				query = "Select g from GruppoGrmEntity g"),
		@NamedQuery(name="GruppoGrmEntity.findByGroup",
				query = "Select g from GruppoGrmEntity g where g.gruppo = :gruppo"),
		@NamedQuery(name="GruppoGrmEntity.findByGroupAndGrm",
				query = "Select g from GruppoGrmEntity g where g.gruppo = :gruppo and g.grm = :grm"),
		@NamedQuery(name="GruppoGrmEntity.countByGroupAndGrm",
				query = "Select count(g) from GruppoGrmEntity g where g.gruppo = :gruppo and g.grm = :grm"),
		@NamedQuery(name = "GruppoGrmEntity.countByGrmIdAndCodiciGruppo",
				query = "SELECT COUNT(e) FROM GruppoGrmEntity e WHERE e.grm.id = :idGrm AND e.gruppo.codiceGruppo IN :codiciGruppo"),
		@NamedQuery(name = "GruppoGrmEntity.countWritableByGrmIdAndCodiciGruppo",
				query = "SELECT COUNT(e) FROM GruppoGrmEntity e WHERE e.grm.id = :idGrm AND e.tipoAccesso = :tipoAccesso AND e.gruppo.codiceGruppo IN :codiciGruppo"),
		@NamedQuery(name = "GruppoGrmEntity.findAllByCodiciGruppoAndTipoAccesso",
				query = "SELECT e.grm.codice FROM GruppoGrmEntity e WHERE e.gruppo.codiceGruppo IN :codiciGruppo AND e.tipoAccesso = :tipoAccesso")
})

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GruppoGrmEntity {
	@EmbeddedId
	private GruppoGrmId id;

	@ManyToOne
	@MapsId("groupId")
	@JoinColumn(name = "ID_GRUPPO")
	GroupEntity gruppo;

	@ManyToOne
	@MapsId("grmId")
	@JoinColumn(name = "ID_GRM")
	GrmEntity grm;

	@Column(name="TIPO_ACCESSO")
	@Convert(converter = SecurityEnumConverter.class)
	PianificazioneSecurityEnum tipoAccesso;

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

