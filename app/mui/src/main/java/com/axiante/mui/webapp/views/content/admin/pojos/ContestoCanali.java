package com.axiante.mui.webapp.views.content.admin.pojos;

import com.axiante.mui.persistence.entity.CanaleEntity;
import com.axiante.mui.persistence.entity.MuiContext;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class ContestoCanali {

    @Getter
    private MuiContext context;

    @Getter
    private List<CanaleWrapper> channels;

    public ContestoCanali(MuiContext context) {
        this.context = context;
    }

    public void addCanale(CanaleEntity channel, boolean available) {
        if (channels == null) {
            channels = new ArrayList<>();
        }
        channels.add(new CanaleWrapper(channel, available));
    }
}
