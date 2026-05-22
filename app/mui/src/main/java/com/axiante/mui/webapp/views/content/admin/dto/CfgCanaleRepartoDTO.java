package com.axiante.mui.webapp.views.content.admin.dto;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleReparto;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
public class CfgCanaleRepartoDTO {
    @Setter
    CanalePromozioneEntity canale;
    @Setter
    RepartoEntity reparto;
    @Setter
    Integer maxTestate=0;

    boolean selected = false;

    CfgCanaleReparto config;
    public CfgCanaleRepartoDTO(CfgCanaleReparto config){
        updateConfig(config);
    }

    public void setSelected(boolean selected){
        if(selected && config == null ){
            config = new CfgCanaleReparto();
            config.setCanale(this.canale);
            config.setReparto(this.reparto);
            config.setMaxTestate(this.maxTestate);
        }
    }

    public void setMaxTestate(Integer maxTestate){
        this.maxTestate = maxTestate;
        if (config == null ){
            config = new CfgCanaleReparto();
            config.setCanale(this.canale);
            config.setReparto(this.reparto);
        }
        config.setMaxTestate(this.maxTestate);
    }

    public String getKey(){
        if ( reparto != null ){
            return reparto.getKey();
        } else {
            throw new IllegalStateException("Dto creato senza reparto");
        }
    }

    public void updateConfig(CfgCanaleReparto config){
        if ( config != null ) {
            this.config = config;
            this.canale = config.getCanale();
            this.reparto = config.getReparto();
            this.maxTestate = config.getMaxTestate();
            selected = true;
        } else {
            selected = false;
        }
    }

    public boolean isSelected(){
        return this.selected;
    }
}
