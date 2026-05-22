package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.persistence.entity.CanaleEntity;
import lombok.Getter;
import lombok.Setter;

public class CanaleWrapper {

    @Getter
    private CanaleEntity ch;

    @Getter
    @Setter
    private boolean available;

    CanaleWrapper(CanaleEntity ch, boolean available) {
        this.ch = ch;
        this.available = available;
    }
}
