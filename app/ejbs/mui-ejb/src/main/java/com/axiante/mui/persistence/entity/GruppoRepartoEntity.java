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
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="GRUPPO_REPARTO", schema = Metadata.SCHEMA)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries(value = {
		@NamedQuery(name = "GruppoRepartoEntity.countByGruppoAndReparto",
				query = "SELECT count(g) from GruppoRepartoEntity g where g.gruppo = :gruppo and g.reparto = :reparto"),
		@NamedQuery(name = "GruppoRepartoEntity.findByGruppoAndReparto",
				query = "SELECT g from GruppoRepartoEntity g where g.gruppo = :gruppo and g.reparto = :reparto"),
		@NamedQuery(name = "GruppoRepartoEntity.findByGruppo",
				query = "SELECT g from GruppoRepartoEntity g where g.gruppo = :gruppo"),
		@NamedQuery(name = "GruppoRepartoEntity.countByRepartoIdAndCodiciGruppo",
				query = "SELECT COUNT(gr) FROM GruppoRepartoEntity gr WHERE gr.reparto.id = :idReparto AND gr.gruppo.codiceGruppo IN :codiciGruppo"),
		@NamedQuery(name = "GruppoRepartoEntity.countWriteableByRepartoIdAndCodiciGruppo",
				query = "SELECT COUNT(gr) FROM GruppoRepartoEntity gr WHERE gr.reparto.id = :idReparto AND gr.tipoAccesso = :tipoAccesso AND gr.gruppo.codiceGruppo IN :codiciGruppo"),
		@NamedQuery(name = "GruppoRepartoEntity.findAllByCodiciGruppoAndTipoAccesso",
				query = "SELECT e.reparto.codiceReparto FROM GruppoRepartoEntity e WHERE e.gruppo.codiceGruppo IN :codiciGruppo AND e.tipoAccesso = :tipoAccesso")
})
public class GruppoRepartoEntity {
	@EmbeddedId
	@Include
	private GruppoRepartoId id;

	@Column(name="TIPO_ACCESSO")
	@Convert(converter = SecurityEnumConverter.class)
	PianificazioneSecurityEnum tipoAccesso;


	@ManyToOne
	@MapsId("gruppoId")
	@JoinColumn(name = "ID_GRUPPO")
	GroupEntity gruppo;

	@ManyToOne
	@MapsId("repartoId")
	@JoinColumn(name = "ID_REPARTO")
	RepartoEntity reparto;

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
