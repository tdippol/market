package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The persistent class for the MUI_ASSORTIMENTO_FORNITORE database table.
 *
 */
@Entity
@Getter
@Setter
@Table(name = "V_MUI_FONT_ENTITIES", schema = Metadata.SCHEMA)
@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@NamedQueries({
		@NamedQuery(name = "MuiFontEntities.find",
				query = "Select m from MuiFontEntities m where m.id = :id"),
		@NamedQuery(name = "MuiFontEntities.findAll",
				query = "Select m from MuiFontEntities m order by m.descrizione")
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MuiFontEntities implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, name = "ID_FONT_ENTITIES")
	@EqualsAndHashCode.Include
	private String id;

	@Column(name = "DESCRIZIONE_ENTITIES", length = 255, nullable = false)
	String descrizione;
}