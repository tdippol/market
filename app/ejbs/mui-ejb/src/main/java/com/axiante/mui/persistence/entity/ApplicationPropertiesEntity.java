package com.axiante.mui.persistence.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.persistence.Metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "APPLICATION_PROPERTIES", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQueries(value = {
		@NamedQuery(name = "ApplicationProperties.findByCode", query = "SELECT p FROM ApplicationPropertiesEntity p WHERE p.key = :key"),
		@NamedQuery(name = "ApplicationProperties.countByCode", query = "SELECT count(p) FROM ApplicationPropertiesEntity p WHERE p.key = :key")
})
@Getter
@Setter
public class ApplicationPropertiesEntity implements Serializable, UUIDEnabledEntity {
	private static final long serialVersionUID = -1997319198311814262L;

	@Id
	@SequenceGenerator(schema = Metadata.SCHEMA, sequenceName = "APPLICATION_PROPERTIES_ID_SEQ", allocationSize = 1, name = "applicationPropertiesSeq")
	@GeneratedValue(generator = "applicationPropertiesSeq", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_APPLICATION_PROPERTIES")
	private int idApplicationProperties;

	@Basic
	@Column(name = "AP_KEY")
	private String key;

	@Basic
	@Column(name = "AP_VALUE")
	private String value;

	@Column(name = "UUID", length = 32)
	@EqualsAndHashCode.Include
	private String uuid;

	@Override
	@PreUpdate
	@PrePersist
	public String getUuid() {
		if (this.uuid == null) {
			this.uuid = AxUUID.randomUUID().toString();
		}
		return uuid;
	}

}
