package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.persistence.entity.GruppoCanaleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class GruppoCanalePromoPojo {
    @Getter
    @Setter
    private String codiceGruppoPromo;

    @Getter
    @Setter
    private String descGruppoPromo;

    @Getter
    @Setter
    private GruppoCanaleEntity gruppoCanale;
}
