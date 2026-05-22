package com.axiante.mui.persistence.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.axiante.mui.persistence.entity.AuditLogEntity;

public interface AuditLogDAO {
	List<AuditLogEntity> readAll();

	AuditLogEntity persist(AuditLogEntity entity) throws Exception;

	void remove(AuditLogEntity entity) throws Exception;

	List<AuditLogEntity> findAllPaginationSortedLogs(int currentPage, int pageSize,
			Map<String, String> currentSortFilters, Map<String, Object> filters, Boolean pageable);
    Long countFilteredLogs(Map<String, Object> filters);
	void deleteAll() throws Exception;

	void deleteAllFiltered(Date limitDay, Boolean isDateBefore) throws Exception;

	List<AuditLogEntity> findAllPaginationFilteredLogs(int currentPage, int pageSize, Date firstLimit, Date secondLimit,
			Map<String, Object> filters);

    Long countAll();
}
