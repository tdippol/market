package com.axiante.mui.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Getter
public class MenuRoleId implements Serializable {
    private static final long serialVersionUID = 5883172013547723083L;

    @Column(name = "ID_MENU")
    private Integer menuId;

    @Column(name = "ID_ROLES")
    private Integer roleId;
}