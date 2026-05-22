package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import java.util.List;

public interface CreaPianoMediaService extends DbPromoService<CreaPianoMediaEntity> {
    List<CreaPianoMediaEntity> findByUserId(String userId);

    CreaPianoMediaEntity findByUserIdAndSlotId(String userId, String slotId);
}
