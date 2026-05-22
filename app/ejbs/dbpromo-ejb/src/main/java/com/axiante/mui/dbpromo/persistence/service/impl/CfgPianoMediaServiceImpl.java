package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianoMediaDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgPianoMediaService;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;

@Dependent
@Transactional
public class CfgPianoMediaServiceImpl extends AbstractDbPromoService<CfgPianoMediaEntity> implements CfgPianoMediaService {
    private static final long serialVersionUID = -1162266277378472432L;
    @Inject
    @Getter
    private CfgPianoMediaDAO dao;

    @Override
    public List<CfgPianoMediaEntity> findAllAttivi() {
        return dao.findAllAttivi();
    }
}
