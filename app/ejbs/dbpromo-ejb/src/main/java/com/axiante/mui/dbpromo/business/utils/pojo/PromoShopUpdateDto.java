package com.axiante.mui.dbpromo.business.utils.pojo;

import lombok.Getter;

@Getter
public class PromoShopUpdateDto {
    private String message;
    private PromoShopUpdateLevel level;

    private PromoShopUpdateDto(String message, PromoShopUpdateLevel level) {
        this.message = message;
        this.level = level;
    }

    public static PromoShopUpdateDto info(String message) {
        return new PromoShopUpdateDto(message, PromoShopUpdateLevel.INFO);
    }

    public static PromoShopUpdateDto warning(String message) {
        return new PromoShopUpdateDto(message, PromoShopUpdateLevel.WARNING);
    }

    public static PromoShopUpdateDto error(String message) {
        return new PromoShopUpdateDto(message, PromoShopUpdateLevel.ERROR);
    }
}
