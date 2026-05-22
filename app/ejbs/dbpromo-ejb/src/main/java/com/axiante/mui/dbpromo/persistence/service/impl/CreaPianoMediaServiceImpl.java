package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CreaPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.CreaPianoMediaService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.NonNull;

@Dependent
@Transactional
public class CreaPianoMediaServiceImpl extends AbstractDbPromoService<CreaPianoMediaEntity>
        implements CreaPianoMediaService {
    private static final long serialVersionUID = 8331858629021899795L;

    @Inject
    @Getter
    private CreaPianoMediaDAO dao;

    @Override
    public List<CreaPianoMediaEntity> findByUserId(@NonNull String userId) {
        return dao.findByUserId(userId);
    }

    @Override
    public CreaPianoMediaEntity findByUserIdAndSlotId(@NonNull String userId, @NonNull String slotId) {
        

        return dao.findByUserIdAndSlotId(userId, slotId);
    }
}
