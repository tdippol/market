package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.dao.FornitoriRetailDAO;
import com.axiante.mui.dbpromo.persistence.entities.FornitoriRetailEntity;
import lombok.AccessLevel;
import lombok.Getter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@DbPromoJpaDao
public class JpaFornitoriRetailDAOImpl implements FornitoriRetailDAO {
    @Inject
    @DbPromo
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public FornitoriRetailEntity getFornitoriRetailById(String codice) {
        return getEm()
                .createNamedQuery("FornitoriRetailEntity.byId", FornitoriRetailEntity.class)
                .setParameter("codice", codice)
                .getSingleResult();
    }

    @Override
    public FornitoriRetailEntity getFornitoriRetailByDescrizione(String descrizione) {
        return getEm()
                .createNamedQuery("FornitoriRetailEntity.byDescription", FornitoriRetailEntity.class)
                .setParameter("descrizione", descrizione)
                .getSingleResult();
    }

    @Override
    public List<FornitoriRetailEntity> getAllFornitoriRetail() {
        return getEm()
                .createNamedQuery("FornitoriRetailEntity.all", FornitoriRetailEntity.class)
                .getResultList();
    }

    @Override
    public List<FornitoriRetailEntity> getAllFornitoriRetailNotDeleted(Date date) {
        return getEm()
                .createNamedQuery("FornitoriRetailEntity.allNotDeleted", FornitoriRetailEntity.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<FornitoriRetailEntity> getAllFornitoriRetailByCodiceLike(String codice) {
        String like = composeLikeValue(codice);
        if (like == null) return Collections.emptyList();
        if (like.isEmpty()) return getAllFornitoriRetail();
        return getEm()
                .createNamedQuery("FornitoriRetailEntity.likeCode", FornitoriRetailEntity.class)
                .setParameter("codice", like)
                .getResultList();
    }

    @Override
    public List<FornitoriRetailEntity> getFornitoriRetailByDescrizioneLike(String descrizione) {
        String like = composeLikeValue(descrizione);
        if (like == null) return Collections.emptyList();
        if (like.isEmpty()) return getAllFornitoriRetail();
        return getEm()
                .createNamedQuery("FornitoriRetailEntity.likeDescription", FornitoriRetailEntity.class)
                .setParameter("descrizione", like)
                .getResultList();
    }

    protected String composeLikeValue(String value) {
        if (value == null) return null;
        if (value.isEmpty()) return "";
        String like = value;
        if (!like.startsWith("%")) like = "%" + like;
        if (!like.endsWith("%")) like = like + "%";
        return like;
    }
}
