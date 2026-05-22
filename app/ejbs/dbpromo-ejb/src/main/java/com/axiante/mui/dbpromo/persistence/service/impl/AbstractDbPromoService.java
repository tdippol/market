package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.common.UUIDEnabledEntity;
import com.axiante.mui.dbpromo.persistence.dao.DbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.AuditLogInterface;
import com.axiante.mui.dbpromo.persistence.service.DbPromoService;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDbPromoService<DbPromoEntity> implements DbPromoService<DbPromoEntity> {

	private static final long serialVersionUID = 3920822586322356933L;

	@Override
	public DbPromoEntity persist(final DbPromoEntity entity) {
		return getDao().persist(entity);
	}

	@Override
	public void persist(List<DbPromoEntity> entities, int batchSize) {
		getDao().persist(entities, batchSize);
	}

	@Override
	public void remove(DbPromoEntity entity) {
		getDao().remove(entity);
	}

	@Override
	public DbPromoEntity findById(Serializable id) {
		return getDao().findById(id);
	}

	@Override
	public List<DbPromoEntity> findAll() {
		return getDao().findAll();
	}

	@Override
	public void persistWithAuditLog(List<AuditLogInterface> entities, int batchSize, String user) {
		getDao().persistWithAuditLog(entities, batchSize, user);
	}

	@Override
	public AuditLogInterface persistWithAuditLog(AuditLogInterface entity, String user) {
		return getDao().persistWithAuditLog(entity, user);
	}

	@Override
	public List<DbPromoEntity> persistInTransaction(List<DbPromoEntity> entities) {
		return getDao().persistInTransaction(entities);
	}

	@Override
	public abstract DbPromoDAO<DbPromoEntity> getDao();

	@Override
	public void ensureAllUuidAreFilled() {
		List<DbPromoEntity> list = getDao().findEmptyUuid();
		if ((list != null) && !list.isEmpty()) {
			list.forEach(c -> ((UUIDEnabledEntity) c).getUuid());
			persistInTransaction(list);
		}
	}

	@Override
	public Object findValueByAttribute(String entityName, String entityAttribute, String lookupAttribute, Object lookupValue) {
		return getDao().findValueByAttribute(entityName, entityAttribute, lookupAttribute, lookupValue);
	}

	@Override
	public void flush() {
		getDao().flush();
	}
}
