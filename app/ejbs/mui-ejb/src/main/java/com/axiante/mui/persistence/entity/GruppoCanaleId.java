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
public class GruppoCanaleId implements Serializable {
	private static final long serialVersionUID = 5883172013547723083L;

	@Column(name = "ID_GRUPPO")
	private Integer groupId;

	@Column(name = "ID_CANALE")
	private Integer canaleId;
}