package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.dao.DbPromoDAO;
import com.axiante.mui.dbpromo.persistence.entities.AuditLogInterface;
import java.io.Serializable;
import java.util.List;

public interface DbPromoService<DbPromoEntity> extends Serializable{
	DbPromoDAO<DbPromoEntity> getDao();

	DbPromoEntity persist(DbPromoEntity entity);

	 void persist(List<DbPromoEntity> entities, int batchSize);

	void remove(DbPromoEntity entity);

	DbPromoEntity findById(Serializable id);

	List<DbPromoEntity> findAll();

	void persistWithAuditLog(List<AuditLogInterface> entities, int batchSize, String user);

	AuditLogInterface persistWithAuditLog(AuditLogInterface entity, String user);

	List<DbPromoEntity> persistInTransaction(List<DbPromoEntity> entities);

	void ensureAllUuidAreFilled();

	Object findValueByAttribute(String entityName, String entityAttribute, String lookupAttribute, Object lookupValue);

	void flush();
}
