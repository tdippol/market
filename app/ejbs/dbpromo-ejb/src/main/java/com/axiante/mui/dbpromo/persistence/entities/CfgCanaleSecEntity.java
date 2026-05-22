package com.axiante.mui.dbpromo.persistence.entities;

import com.axiante.mui.common.CanaleSecurityEnum;
import com.axiante.mui.dbpromo.persistence.Metadata;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "MUI_CFG_CANALE_SEC", schema = Metadata.SCHEMA)
@NoArgsConstructor
@NamedQueries({
	@NamedQuery(name = "CfgCanaleSecEntity.findAll", query = "SELECT a FROM CfgCanaleSecEntity a"),
	@NamedQuery(name = "CfgCanaleSecEntity.findByCanale", query = "SELECT a FROM CfgCanaleSecEntity a WHERE a.canale = :canale"),
	@NamedQuery(name = "CfgCanaleSecEntity.countByCanale", query = "SELECT COUNT(a) FROM CfgCanaleSecEntity a WHERE a.canale = :canale")
})
public class CfgCanaleSecEntity {
	//	ID NUMERIC(16,0) NOT NULL PRIMARY KEY,
	@Id
	@SequenceGenerator(schema=Metadata.SCHEMA, allocationSize = 1, name = "MUI_SEQ_CANALE_SEC_ID_GENERATOR", sequenceName = "MUI_SEQ_CANALE_SEC")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUI_SEQ_CANALE_SEC_ID_GENERATOR")
	@Column(unique = true, nullable = false, precision = 16)
	private Long id;
	//	ID_CANALE BIGINT NOT NULL REFERENCES MUI_CANALE_PROMOZIONE(ID),
	@OneToOne
	@JoinColumn(name="ID_CANALE", referencedColumnName = "ID")
	CanalePromozioneEntity canale;
	//	OWNER_READ VARCHAR(10) NOT NULL DEFAULT 'NONE',
	@Column(name = "OWNER_READ")
	@Enumerated(EnumType.STRING)
	CanaleSecurityEnum ownerRead = CanaleSecurityEnum.NONE;
	//	OWNER_WRITE VARCHAR(10) NOT NULL DEFAULT 'NONE',
	@Column(name = "OWNER_WRITE")
	@Enumerated(EnumType.STRING)
	CanaleSecurityEnum ownerWrite = CanaleSecurityEnum.NONE;
	//	OTHER_READ VARCHAR(10) NOT NULL DEFAULT 'NONE',
	@Column(name = "OTHER_READ")
	@Enumerated(EnumType.STRING)
	CanaleSecurityEnum otherRead = CanaleSecurityEnum.NONE;
	//	OTHER_WRITE VARCHAR(10) NOT NULL DEFAULT 'NONE'
	@Column(name = "OTHER_WRITE")
	@Enumerated(EnumType.STRING)
	CanaleSecurityEnum otherWrite = CanaleSecurityEnum.NONE;
}
