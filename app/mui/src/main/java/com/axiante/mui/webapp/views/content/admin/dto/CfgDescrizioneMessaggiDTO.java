package com.axiante.mui.webapp.views.content.admin.dto;

import lombok.Data;

@Data
public class CfgDescrizioneMessaggiDTO {

    Integer idMessaggio;
    String descrizione;
    boolean edit = false;

    public void clear() {
        this.idMessaggio = null;
        this.descrizione = null;
        edit = false;
    }

    public boolean validate() {
        return this.idMessaggio != null && this.descrizione != null;
    }
}
