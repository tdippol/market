package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.MuiFontStileDAO;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontStileEntity;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@DbPromoJpaDao
@Slf4j
public class JpaMuiFontStileDAOImpl extends JpaDbPromoDAO<MuiFontStileEntity> implements MuiFontStileDAO {
    private static final long serialVersionUID = 5723257668390722015L;

    @Override
    public List<MuiFontStileEntity> findAll() {
        return getEm().createNamedQuery("MuiFontStileEntity.findAll", MuiFontStileEntity.class).getResultList();
    }

    @Override
    public MuiFontStileEntity findById(String id) {
        Long count = getEm().createNamedQuery("MuiFontStileEntity.countById", Long.class).setParameter("id", id).getSingleResult();
        if ( count != null && count ==1){
            return getEm().createNamedQuery("MuiFontStileEntity.findById", MuiFontStileEntity.class).setParameter("id", id).getSingleResult();
        } else {
            if (count == null || count == 0) {
                log.warn("No result found for 'FontStile' {}", id);
            } else if (count > 1) {
                log.error("Found {} results for 'FontStile' {}", count, id);
            }
        }
        return null;
    }
}
