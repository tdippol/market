package com.axiante.mui.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.axiante.mui.common.promo.DBPromoAuditLogEnum;
import com.axiante.mui.common.utility.AuditLogUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.persistence.dao.AuditLogDAO;
import com.axiante.mui.persistence.entity.AuditLogEntity;

import lombok.AccessLevel;
import lombok.Getter;

@Stateless
public class JpaAuditLogDAOImpl implements AuditLogDAO {
  @Inject
  @Getter(value = AccessLevel.PROTECTED)
  private EntityManager em;

  static final String DELETE_ALL = "DELETE FROM AuditLogEntity";

  @Override
  public List<AuditLogEntity> readAll() {
    return getEm()
        .createQuery("select t from AuditLogEntity t", AuditLogEntity.class)
        .getResultList();
  }

  @Override
  public AuditLogEntity persist(AuditLogEntity entity) throws Exception {
    try {
      entity = getEm().merge(entity);
      getEm().flush();
    } catch (final EntityExistsException e) {
      throw new Exception("Riga di log gia' presente");
    }
    return entity;
  }

  @Override
  public void remove(AuditLogEntity entity) throws Exception {
    try {
      entity = getEm().merge(entity);
      getEm().remove(entity);
    } catch (final PersistenceException e) {
      throw new Exception("Impossibile rimuovere oggetto in uso.");
    }
  }

  @Override
  public List<AuditLogEntity> findAllPaginationSortedLogs(
      int currentPage,
      int pageSize,
      Map<String, String> currentSortFilters,
      Map<String, Object> filters,
      Boolean pageable) {

    CriteriaBuilder cb = getEm().getCriteriaBuilder();
    CriteriaQuery<AuditLogEntity> cq = cb.createQuery(AuditLogEntity.class);
    Root<AuditLogEntity> root = cq.from(AuditLogEntity.class);

    List<Predicate> predicates = buildPredicates(cb, root, filters);
    if (!predicates.isEmpty()) {
      cq.where(cb.and(predicates.toArray(new Predicate[0])));
    }

    List<Order> orders = buildSortOrders(cb, root, currentSortFilters);
    if (!orders.isEmpty()) {
      cq.orderBy(orders);
    }

    TypedQuery<AuditLogEntity> query = getEm().createQuery(cq.select(root));
    if (Boolean.TRUE.equals(pageable)) {
      query.setFirstResult(currentPage * pageSize);
      query.setMaxResults(pageSize);
    }

    return query.getResultList();
  }

  @Override
  public Long countFilteredLogs(Map<String, Object> filters) {
    CriteriaBuilder cb = getEm().getCriteriaBuilder();
    CriteriaQuery<Long> cq = cb.createQuery(Long.class);
    Root<AuditLogEntity> root = cq.from(AuditLogEntity.class);

    List<Predicate> predicates = buildPredicates(cb, root, filters);
    if (!predicates.isEmpty()) {
      cq.where(cb.and(predicates.toArray(new Predicate[0])));
    }

    cq.select(cb.count(root));
    return getEm().createQuery(cq).getSingleResult();
  }

  private List<Predicate> buildPredicates(
      CriteriaBuilder cb, Root<AuditLogEntity> root, Map<String, Object> filters) {
    List<Predicate> predicates = new ArrayList<>();
    AtomicReference<Date> filterStartDate = new AtomicReference<>();
    AtomicReference<Date> filterEndDate = new AtomicReference<>();
    AuditLogUtils auditLogUtils = new AuditLogUtils();

    filters.forEach(
        (key, value) -> {
          DBPromoAuditLogEnum filterEnum = DBPromoAuditLogEnum.valueOf(key.toUpperCase());
          switch (filterEnum) {
            case LOGDATE:
              if (auditLogUtils.checkFilterLogDate(value.toString())) {
                filterStartDate.set(
                    auditLogUtils.composeLogDateQueryCondition(value.toString(), true));
                filterEndDate.set(
                    auditLogUtils.composeLogDateQueryCondition(value.toString(), false));
                if (filterStartDate.get() != null && filterEndDate.get() != null) {
                  predicates.add(
                      cb.greaterThanOrEqualTo(root.get("logDate"), filterStartDate.get()));
                  predicates.add(cb.lessThanOrEqualTo(root.get("logDate"), filterEndDate.get()));
                }
              }
              break;
            case USERNAME:
              predicates.add(
                  cb.like(
                      cb.upper(root.get("userName")), "%" + value.toString().toUpperCase() + "%"));
              break;
            case ACTION:
              predicates.add(
                  cb.like(
                      cb.upper(root.get("action")), "%" + value.toString().toUpperCase() + "%"));
              break;
            case PATH:
              predicates.add(
                  cb.like(cb.upper(root.get("path")), "%" + value.toString().toUpperCase() + "%"));
              break;
          }
        });
    return predicates;
  }

  private List<Order> buildSortOrders(
      CriteriaBuilder cb, Root<AuditLogEntity> root, Map<String, String> sortFilters) {
    List<Order> orders = new ArrayList<>();
    sortFilters.forEach(
        (key, value) -> {
          DBPromoAuditLogEnum sortEnum = DBPromoAuditLogEnum.valueOf(key.toUpperCase());
          boolean asc = value.equals(DbPromoConstants.ASCENDING_SQL);
          switch (sortEnum) {
            case LOGDATE:
              orders.add(asc ? cb.asc(root.get("logDate")) : cb.desc(root.get("logDate")));
              break;
            case USERNAME:
              orders.add(asc ? cb.asc(root.get("userName")) : cb.desc(root.get("userName")));
              break;
            case ACTION:
              orders.add(asc ? cb.asc(root.get("action")) : cb.desc(root.get("action")));
              break;
            case PATH:
              orders.add(asc ? cb.asc(root.get("path")) : cb.desc(root.get("path")));
              break;
          }
        });
    return orders;
  }

  @Override
  public void deleteAll() throws Exception {
    getEm().createQuery(DELETE_ALL).executeUpdate();
  }

  @Override
  public void deleteAllFiltered(Date limitDay, Boolean isDateBefore) throws Exception {
    if (isDateBefore) {
      getEm()
          .createQuery("DELETE FROM AuditLogEntity a WHERE a.logDate < :limitDay")
          .setParameter("limitDay", limitDay)
          .executeUpdate();
    } else {
      getEm()
          .createQuery("DELETE FROM AuditLogEntity a WHERE a.logDate >= :limitDay")
          .setParameter("limitDay", limitDay)
          .executeUpdate();
    }
  }

  @Override
  public List<AuditLogEntity> findAllPaginationFilteredLogs(
      int currentPage,
      int pageSize,
      Date firstLimit,
      Date secondLimit,
      Map<String, Object> filters) {

    final CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
    final CriteriaQuery<AuditLogEntity> criteriaQuery =
        criteriaBuilder.createQuery(AuditLogEntity.class);
    final Root<AuditLogEntity> root = criteriaQuery.from(AuditLogEntity.class);

    final List<Predicate> applyFilters = new ArrayList<>();
    final AtomicReference<Date> filterStartDate = new AtomicReference<>();
    final AtomicReference<Date> filterEndDate = new AtomicReference<>();

    filters.forEach(
        (filterKey, filterValue) -> {
          final AuditLogUtils auditLogUtils = new AuditLogUtils();
          switch (DBPromoAuditLogEnum.valueOf(filterKey.toUpperCase())) {
            case LOGDATE:
              if (auditLogUtils.checkFilterLogDate(filterValue.toString())) {
                filterStartDate.set(
                    auditLogUtils.composeLogDateQueryCondition(filterValue.toString(), true));
                filterEndDate.set(
                    auditLogUtils.composeLogDateQueryCondition(filterValue.toString(), false));
                if (filterStartDate.get() != null && filterEndDate.get() != null) {
                  applyFilters.add(
                      criteriaBuilder.greaterThanOrEqualTo(
                          root.get("logDate"), filterStartDate.get()));
                  applyFilters.add(
                      criteriaBuilder.lessThanOrEqualTo(root.get("logDate"), filterEndDate.get()));
                }
              }
              break;
            case USERNAME:
              applyFilters.add(
                  criteriaBuilder.like(
                      criteriaBuilder.upper(root.get("userName")),
                      "%" + filterValue.toString().toUpperCase() + "%"));
              break;
            case ACTION:
              applyFilters.add(
                  criteriaBuilder.like(
                      criteriaBuilder.upper(root.get("action")),
                      "%" + filterValue.toString().toUpperCase() + "%"));
              break;
            case PATH:
              applyFilters.add(
                  criteriaBuilder.like(
                      criteriaBuilder.upper(root.get("path")),
                      "%" + filterValue.toString().toUpperCase() + "%"));
              break;
          }
        });

    if (firstLimit != null) {
      if (secondLimit == null) {
        applyFilters.add(criteriaBuilder.greaterThanOrEqualTo(root.get("logDate"), firstLimit));
      } else {
        applyFilters.add(criteriaBuilder.greaterThanOrEqualTo(root.get("logDate"), firstLimit));
        applyFilters.add(criteriaBuilder.lessThanOrEqualTo(root.get("logDate"), secondLimit));
      }
    }

    if (!applyFilters.isEmpty()) {
      final Predicate finalPredicate = criteriaBuilder.and(applyFilters.toArray(new Predicate[0]));
      criteriaQuery.where(finalPredicate);
    }

    criteriaQuery.orderBy(criteriaBuilder.desc(root.get("logDate")));

    final CriteriaQuery<AuditLogEntity> select = criteriaQuery.select(root);
    final TypedQuery<AuditLogEntity> typedQuery = getEm().createQuery(select);
    typedQuery.setFirstResult(currentPage * pageSize);
    typedQuery.setMaxResults(pageSize);

    return typedQuery.getResultList();
  }

  public Long countAll() {
    return getEm().createNamedQuery("AuditLogEntity.countAll", Long.class).getSingleResult();
  }
}
