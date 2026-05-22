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

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.persistence.Metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "GRUPPO_CANALE", schema = Metadata.SCHEMA)

@NamedQueries(value = {
		@NamedQuery(name = "GruppoCanaleEntity.findByCodiceGruppoAndCodiceCanale", query = "SELECT g FROM GruppoCanaleEntity g WHERE g.gruppo.codiceGruppo = :gruppo AND g.canale.codiceCanale = :canale"),
		@NamedQuery(name = "GruppoCanaleEntity.findByGruppoAndCanale", query = "SELECT g FROM GruppoCanaleEntity g WHERE g.gruppo = :gruppo AND g.canale = :canale"),
		@NamedQuery(name = "GruppoCanaleEntity.findAllByGruppo", query = "SELECT g FROM GruppoCanaleEntity g WHERE g.gruppo = :gruppo"),
		@NamedQuery(name = "GruppoCanaleEntity.findCreatableCanaliByGruppo", query = "SELECT g.canale FROM GruppoCanaleEntity g WHERE g.gruppo IN :gruppi AND g.create = true"),
		@NamedQuery(name = "GruppoCanaleEntity.findOwnedCanaliByGruppi", query = "SELECT g.canale FROM GruppoCanaleEntity g WHERE g.gruppo IN :gruppi AND g.owner = true"),
		@NamedQuery(name = "GruppoCanaleEntity.countByGruppoAndCanale", query = "SELECT count(g) FROM GruppoCanaleEntity g WHERE g.gruppo = :gruppo AND g.canale = :canale"),
		@NamedQuery(name = "GruppoCanaleEntity.countByCodiceGruppoAndCodiceCanale", query = "SELECT count(g) FROM GruppoCanaleEntity g WHERE g.gruppo.codiceGruppo = :gruppo AND g.canale.codiceCanale = :canale"),
		@NamedQuery(name = "GruppoCanaleEntity.findAll", query = "SELECT g FROM GruppoCanaleEntity g "),
		@NamedQuery(name = "GruppoCanaleEntity.findOwnershipCodiciCanaleByGruppi",
				query = "SELECT DISTINCT e.canale.codiceCanale FROM GruppoCanaleEntity e WHERE e.gruppo IN :gruppi AND ((e.owner = true) OR (e.create = true AND EXISTS (SELECT g FROM GruppoCompratoreEntity g WHERE g.gruppo = e.gruppo AND g.tipoAccesso = :tipoAccesso)))"),
		@NamedQuery(name = "GruppoCanaleEntity.findOwnershipCodiciCanaleByGruppiAndCompratori",
				query = "SELECT DISTINCT e.canale.codiceCanale FROM GruppoCanaleEntity e WHERE e.gruppo.codiceGruppo IN :gruppi AND ((e.owner = true) OR (e.create = true AND EXISTS (SELECT g FROM GruppoCompratoreEntity g WHERE g.gruppo = e.gruppo AND g.tipoAccesso = :tipoAccesso AND g.compratore.codiceCompratore IN :compratori)))"),
		@NamedQuery(name = "GruppoCanaleEntity.findOwnershipCodiciCanaleByGruppiAndFlagOwner",
				query = "SELECT DISTINCT e.canale.codiceCanale FROM GruppoCanaleEntity e WHERE e.gruppo.codiceGruppo IN :gruppi AND e.owner = true")
})
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GruppoCanaleEntity {
	@EmbeddedId
	private GruppoCanaleId id;

	@ManyToOne
	@MapsId("groupId")
	@JoinColumn(name = "ID_GRUPPO")
	GroupEntity gruppo;

	@ManyToOne
	@MapsId("canaleId")
	@JoinColumn(name = "ID_CANALE")
	CanaleEntity canale;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "FLAG_CREATE")
	private Boolean create = false;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "FLAG_OWNER")
	private Boolean owner = false;

	@Convert(converter = BooleanConverter.class)
	@Column(name = "FL_PROPAGA_OWNERSHIP")
	private Boolean propagaOwner = true;
}
