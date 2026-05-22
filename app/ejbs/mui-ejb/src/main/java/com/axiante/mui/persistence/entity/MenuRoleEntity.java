package com.axiante.mui.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.axiante.mui.business.BooleanConverter;
import com.axiante.mui.persistence.Metadata;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MENU_ROLES", schema = Metadata.SCHEMA)
@Getter
@Setter
@NamedQueries(@NamedQuery(name = "MenuRoleEntity.findByMenuAndRoles", query = "Select distinct m from MenuRoleEntity m where m.menu = :menu and m.role in :roles"))
public class MenuRoleEntity implements Serializable {
    private static final long serialVersionUID = 1308957387920922062L;

    @EmbeddedId
    private MenuRoleId id;

    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name = "ID_MENU")
    MenuEntity menu;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "ID_ROLES")
    RolesEntity role;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "ADMIN_MODE_VISIBLE")
    private Boolean adminModeVisible = true;
}
