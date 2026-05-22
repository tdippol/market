package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.dao.CausaliRetailDAO;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoJpaDao;
import com.axiante.mui.dbpromo.persistence.entities.CausaliRetailEntity;
import lombok.AccessLevel;
import lombok.Getter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@DbPromoJpaDao
public class JpaCausaliRetailDAOImpl implements CausaliRetailDAO, Serializable {
  private static final long serialVersionUID = -799574944707645753L;

  @Inject
  @DbPromo
  @Getter(value = AccessLevel.PROTECTED)
  private EntityManager em;

  @Override
  public CausaliRetailEntity getCausalRetail(String id) {
    return getEm()
        .createNamedQuery("CausaliRetail.byCode", CausaliRetailEntity.class)
        .setParameter("codice", id)
        .getSingleResult();
  }

  @Override
  public List<CausaliRetailEntity> getAllCausalRetail() {
    return getEm().createNamedQuery("CausaliRetail.all", CausaliRetailEntity.class).getResultList();
  }

  @Override
  public CausaliRetailEntity getCausalRetailByDescription(String description) {
    return getEm()
        .createNamedQuery("CausaliRetail.byDescription", CausaliRetailEntity.class)
        .setParameter("descrizione", description)
        .getSingleResult();
  }

  @Override
  public List<CausaliRetailEntity> getCausaleRetailByIdLike(String id) {
    String like = composeLikeValue(id);
    if (like == null) return Collections.emptyList();
    if (like.isEmpty()) return getAllCausalRetail();
    return getEm()
        .createNamedQuery("CausaliRetail.likeCode", CausaliRetailEntity.class)
        .setParameter("codice", like)
        .getResultList();
  }

  @Override
  public List<CausaliRetailEntity> getCausaleRetailDescrizioneLike(String descrizione) {
    String like = composeLikeValue(descrizione);
    if (like == null) return Collections.emptyList();
    if (like.isEmpty()) return getAllCausalRetail();
    return getEm()
        .createNamedQuery("CausaliRetail.likeDescription", CausaliRetailEntity.class)
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
