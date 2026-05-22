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
public class GruppoCompratoreId implements Serializable {

	private static final long serialVersionUID = 730517285666804297L;

	@Column(name = "ID_GRUPPO")
	private Integer groupId;

	@Column(name = "ID_COMPRATORE")
	private Integer compratoreId;
}