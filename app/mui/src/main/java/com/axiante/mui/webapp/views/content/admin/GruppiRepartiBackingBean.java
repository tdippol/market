package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoRepartoEntity;
import com.axiante.mui.persistence.entity.RepartoEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class GruppiRepartiBackingBean {

    @Getter
    @Setter
    private List<GruppoRepartoEntity> reparti;

    @Getter
    @Setter
    private GruppoRepartoEntity selectedGruppoReparto;

    @Getter
    @Setter
    private List<RepartoEntity> availableReparti;

    @Getter
    @Setter
    private List<RepartoEntity> selectedReparti;

    @Getter
    @Setter
    private Integer idCopyGroup;

    @Getter
    @Setter
    private List<GroupEntity> groupsForCopy;

    @Getter
    @Setter
    private PianificazioneSecurityEnum tipoAccesso;
}
