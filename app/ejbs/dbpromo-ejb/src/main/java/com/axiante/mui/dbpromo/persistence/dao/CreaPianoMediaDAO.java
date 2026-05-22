package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import java.util.List;

public interface CreaPianoMediaDAO extends DbPromoDAO<CreaPianoMediaEntity> {
    List<CreaPianoMediaEntity> findByUserId(String userId);

    CreaPianoMediaEntity findByUserIdAndSlotId(String userId, String slotId);
}
