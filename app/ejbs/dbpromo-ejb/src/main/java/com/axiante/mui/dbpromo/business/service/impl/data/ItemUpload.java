package com.axiante.mui.dbpromo.business.service.impl.data;

import lombok.Data;
import lombok.Getter;

@Data
public class ItemUpload {

    @Getter
    private Long itemId = null;
    @Getter
    private Long compratoreId = null;
    @Getter
    private String elementDescription = null;
    @Getter
    private String detailRowId = null;
    @Getter
    private String type = null; // BLANK || STRING

    @Getter
    private boolean valid = false;

    public ItemUpload(String detailRowId, String type) {
        this.detailRowId = detailRowId;
        this.type = type;
    }

    public ItemUpload(String detailRowId, boolean valid, String type) {
        this.detailRowId = detailRowId;
        this.valid = valid;
        this.type = type;
    }

    public ItemUpload(Long itemId, Long compratoreId, String elementDescription, String detailRowId, boolean valid) {
        this.itemId = itemId;
        this.compratoreId = compratoreId;
        this.elementDescription = elementDescription;
        this.detailRowId = detailRowId;
        this.valid = valid;
    }

}
