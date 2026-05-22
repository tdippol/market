package com.axiante.mui.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Getter
public class GruppoRepartoId implements Serializable{
	private static final long serialVersionUID = 676594818315323803L;

	@Column(name = "ID_GRUPPO")
	private Integer gruppoId;

	@Column(name = "ID_REPARTO")
	private Integer repartoId;

}
