package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.dbpromo.persistence.Metadata;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityListeners(DbPromoReadOnlyEntity.class)
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MUI_CFG_CHECK", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CfgCheckEntity implements Serializable{
	private static final long serialVersionUID = 7218124088024339326L;
	@EqualsAndHashCode.Include
	@Id
	private Long id;
	@Column(name = "CODICE", length = 20, nullable = false)
	String codice;
	@Column(name = "DESCRIZIONE", length = 250, nullable = false)
	String descrizione;
	@Column(name = "MESSAGGIO_ERRORE", length = 250, nullable = false)
	String messaggioErrore;
	@Column(name = "AMBITO", length = 20, nullable = false)
	String ambito;
	@Column(name = "TIPO_CONTROLLO", length = 50, nullable = false)
	String tipoControllo;
}
