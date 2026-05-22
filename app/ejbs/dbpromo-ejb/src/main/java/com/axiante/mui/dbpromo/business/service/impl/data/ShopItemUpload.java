package com.axiante.mui.dbpromo.business.service.impl.data;

import lombok.Data;
import lombok.Getter;

@Data
public class ShopItemUpload {

    @Getter
    private String sigla = null;
    @Getter
    private boolean valid = false;

    public ShopItemUpload(String sigla, boolean valid) {
        this.sigla = sigla;
        this.valid = valid;
    }

}
