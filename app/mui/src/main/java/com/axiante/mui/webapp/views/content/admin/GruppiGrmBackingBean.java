package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.entity.GrmEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoGrmEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class GruppiGrmBackingBean {

    @Getter
    @Setter
    private List<GruppoGrmEntity> grm;

    @Getter
    @Setter
    private GruppoGrmEntity selectedGruppoGrm;

    @Getter
    @Setter
    private List<GrmEntity> availableGrm;

    @Getter
    @Setter
    private List<GrmEntity> selectedGrm;

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
