package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.AuditLogInterface;
import java.io.Serializable;
import java.util.List;

/**
 * Base interface for the DBPromo DAO
 *
 * @author marco
 *
 * @param <DbPromoEntity>: the entity to persist
 */
public interface DbPromoDAO<DbPromoEntity> extends Serializable{
	DbPromoEntity persist(DbPromoEntity entity);

	default void persist(List<DbPromoEntity> entities) {
		persist(entities, 100);
	}

	void remove(DbPromoEntity entity);

	void persist(List<DbPromoEntity> entities, int batchSize);

	DbPromoEntity findById(Serializable id);

	List<DbPromoEntity> findAll();

	default void persistWithAuditLog(List<AuditLogInterface> entities, String user) {
		persistWithAuditLog(entities, 100, user);
	}

	void persistWithAuditLog(List<AuditLogInterface> entities, int batchSize, String user);

	AuditLogInterface persistWithAuditLog(AuditLogInterface entity, String user);

	List<DbPromoEntity> persistInTransaction(List<DbPromoEntity> entities);

	List<DbPromoEntity> findEmptyUuid();

	Object findValueByAttribute(String entityName, String entityAttribute, String lookupAttribute, Object lookupValue);

	void flush();
}
