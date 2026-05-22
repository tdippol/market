package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.persistence.entity.CanaleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class GruppoPromoCanalePojo {
    @Getter
    @Setter
    private String gruppoPromo;

    @Getter
    @Setter
    private CanaleEntity canale;
}
