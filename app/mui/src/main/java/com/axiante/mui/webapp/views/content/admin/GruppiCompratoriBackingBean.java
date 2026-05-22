package com.axiante.mui.webapp.views.content.admin;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCompratoreEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class GruppiCompratoriBackingBean {

    @Getter
    @Setter
    private List<GruppoCompratoreEntity> compratori;

    @Getter
    @Setter
    private GruppoCompratoreEntity selectedGruppoCompratore;

    @Getter
    @Setter
    private List<CompratoreEntity> availableCompratori;

    @Getter
    @Setter
    private List<CompratoreEntity> selectedCompratori;

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
