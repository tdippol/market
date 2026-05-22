package com.axiante.mui.dbpromo.persistence.support.builders;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;

public class CfgPianificazTipoRigaEntityBuilder {

    public static CfgPianificazTipoRigaEntity buildRigaSet(Long id) {
        return build(id, PianificazioneRowTypeEnum.SET);
    }

    public static CfgPianificazTipoRigaEntity buildRigaRaggruppamento(Long id) {
        return build(id, PianificazioneRowTypeEnum.RAGGRUPPAMENTO);
    }

    public static CfgPianificazTipoRigaEntity buildRigaElemento(Long id) {
        return build(id, PianificazioneRowTypeEnum.ELEMENTO);
    }

    public static CfgPianificazTipoRigaEntity build(Long id, PianificazioneRowTypeEnum type) {
        final CfgPianificazTipoRigaEntity entity = new CfgPianificazTipoRigaEntity();
        entity.setId(id);
        entity.setCodiceTipo(type.getTypeCode());
        entity.setDescrizione(type.getDescription());
        return entity;
    }
}
