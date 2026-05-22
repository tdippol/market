package com.axiante.mui.webapp.exporters;

import com.axiante.mui.persistence.dto.GruppoUtenteDto;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.webapp.views.content.admin.pojos.GruppoCanalePromoPojo;
import com.axiante.mui.webapp.views.content.admin.pojos.GruppoConAssociazioneExport;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public interface GroupsExporter extends Serializable {
    InputStream produce(List<GroupEntity> groupList, List<GruppoCanalePromoPojo> channels, List<GruppoUtenteDto> users,
                        List<GruppoConAssociazioneExport> buyers, List<GruppoConAssociazioneExport> reparti,
                        List<GruppoConAssociazioneExport> grm);
}
