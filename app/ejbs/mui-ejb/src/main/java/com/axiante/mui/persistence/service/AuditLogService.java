package com.axiante.mui.persistence.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.axiante.mui.persistence.entity.AuditLogEntity;

public interface AuditLogService extends Serializable {
	List<AuditLogEntity> findAllLogs();

	AuditLogEntity saveLog(AuditLogEntity entity) throws Exception;

	void removeLog(AuditLogEntity entity) throws Exception;

    List<AuditLogEntity> findAllPaginationSortedLogs(int currentPage, int pageSize,
													 Map<String, String> currentSortFilters, Map<String, Object> filters, Boolean pageable);

    void deleteAllLogs() throws Exception;

	void deleteAllLogsFiltered(Date limitDay, Boolean isDateBefore) throws Exception;

	boolean logAction(String action, String path, String username);

    List<AuditLogEntity> findAllPaginationFilteredLogs(int currentPage, int pageSize,
													   Date firstLimit, Date secondLimit, Map<String, Object> filters);

    Long countFilteredLogs(Map<String, Object> filters);


    Long countAllLogs();
}
