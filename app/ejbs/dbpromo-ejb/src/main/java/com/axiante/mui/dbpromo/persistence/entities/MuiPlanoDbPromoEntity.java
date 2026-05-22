package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the V_MUI_PROMO_DBPROMO database table.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "V_MUI_PLANO_DBPROMO", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries(value = {
		@NamedQuery(name = "MuiPlanoDbPromoEntity.findAll", query = "SELECT e FROM MuiPlanoDbPromoEntity e"),
		@NamedQuery(name = "MuiPlanoDbPromoEntity.countByIdPlano",
		query = "SELECT COUNT(e) FROM MuiPlanoDbPromoEntity e WHERE e.idPlano = :idPlano"),
		@NamedQuery(name = "MuiPlanoDbPromoEntity.findByIdPlano",
		query = "SELECT e FROM MuiPlanoDbPromoEntity e WHERE e.idPlano = :idPlano"),
})
public class MuiPlanoDbPromoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PLANO", unique = true, nullable = false)
	@EqualsAndHashCode.Include
	private String idPlano;

	@Column(length = 1000,name="CATEGORIA_ESPOSITIVA")
	String categoriaEspositiva;

	@Column(length = 1000,name="DESC_CATEGORIA_ESPOSITIVA")
	String descrizioneCategoria;

	@Column(length = 1000,name="DIMENSIONE")
	String dimensione;

	@Column(length = 1000,name="TIPOLOGIA")
	String tipologia;

	@Column(length = 1000,name="CODICE_CICS")
	String codiceCics;

	@Column(length = 1000,name="CODICE_PLANO")
	String codicePlano;
	@ManyToMany(mappedBy = "planogrammi")
	Set<PromozioneTestataEntity> testate;
}