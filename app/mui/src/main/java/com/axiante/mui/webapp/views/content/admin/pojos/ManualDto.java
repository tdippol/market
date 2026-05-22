package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.persistence.entity.RolesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ManualDto {

    @Getter
    private String roleName;

    @Getter
    private String filename;

    @Getter
    private byte[] content;

    @Getter
    private Integer roleId ;
    public ManualDto(RolesEntity role) {
        this.roleName = role.getName();
        this.content = role.getHelpData();
        this.filename = role.getHelpFilename() == null ? String.format("Manuale-%s", this.roleName) : role.getHelpFilename();
        this.roleId = role.getId();
    }
}
