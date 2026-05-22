package com.axiante.mui.persistence.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.common.utility.AxUUID;
import com.axiante.mui.persistence.Metadata;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ACL", schema = Metadata.SCHEMA)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
public class AclEntity implements Serializable, UUIDEnabledEntity {
	private static final long serialVersionUID = 4136417281853695956L;
	@Id
	@SequenceGenerator(schema = Metadata.SCHEMA, sequenceName = "ACL_SEQ", allocationSize = 1, name = "aclSeq")
	@GeneratedValue(generator = "aclSeq", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Integer id;
	@Basic
	@Column(name = "COMPONENT_CLASS")
	private String componentClass;
	@Basic
	@Column(name = "COMPONENT")
	private String component;
	@Basic
	@Column(name = "VISIBLE")
	private Integer visible;
	@Basic
	@Column(name = "EDITABLE")
	private Integer editable;
	@Basic
	@Column(name = "ENABLED")
	private Integer enabled;

	// bi-directional many-to-one association to Role
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "ROLE_ID")
	private RolesEntity rolesByRoleId;

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

	public AclEntity(final Integer id, final String componentClass, final String component, final Boolean visible,
			final Boolean editable, final Boolean enabled, final RolesEntity rolesByRoleId) {
		this.id = id;
		this.componentClass = componentClass;
		this.component = component;
		this.visible = visible ? 1 : 0;
		this.editable = editable ? 1 : 0;
		this.enabled = enabled ? 1 : 0;
		this.rolesByRoleId = rolesByRoleId;
	}

	public Boolean getVisible() {
		return (visible != null) && (visible == 1);
	}

	public void setVisible(final Boolean visible) {
		this.visible = visible ? 1 : 0;
	}

	public Boolean getEditable() {
		return (editable != null) && (editable == 1);
	}

	public void setEditable(final Boolean editable) {
		this.editable = editable ? 1 : 0;
	}

	public Boolean getEnabled() {
		return (enabled != null) && (enabled == 1);
	}

	public void setEnabled(final Boolean enabled) {
		this.enabled = enabled ? 1 : 0;

	}
}
