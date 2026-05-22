package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneCampiDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;

@DbPromoJpaDao
public class JpaCfgPianificazioneCampiEntityDAOImpl extends JpaDbPromoDAO<CfgPianificazioneCampiEntity>
        implements CfgPianificazioneCampiDAO {
    private static final long serialVersionUID = 4066195805491491162L;
}
