package com.axiante.mui.persistence.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.axiante.mui.persistence.dao.AuditLogDAO;
import com.axiante.mui.persistence.entity.AuditLogEntity;
import com.axiante.mui.persistence.service.AuditLogService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class AuditLogServiceImpl implements AuditLogService {

  private static final long serialVersionUID = -7621687851740572145L;
  @Inject private AuditLogDAO dao;

  @Override
  public List<AuditLogEntity> findAllLogs() {
    return dao.readAll();
  }

  @Override
  public AuditLogEntity saveLog(AuditLogEntity entity) throws Exception {
    return dao.persist(entity);
  }

  @Override
  public void removeLog(AuditLogEntity entity) throws Exception {
    dao.remove(entity);
  }

  @Override
  public List<AuditLogEntity> findAllPaginationSortedLogs(
      int currentPage,
      int pageSize,
      Map<String, String> currentSortFilters,
      Map<String, Object> filters,
      Boolean pageable) {
    return dao.findAllPaginationSortedLogs(
        currentPage, pageSize, currentSortFilters, filters, pageable);
  }

  @Override
  public void deleteAllLogs() throws Exception {
    dao.deleteAll();
  }

  @Override
  public void deleteAllLogsFiltered(Date limitDay, Boolean isDateBefore) throws Exception {
    dao.deleteAllFiltered(limitDay, isDateBefore);
  }

  @Override
  public List<AuditLogEntity> findAllPaginationFilteredLogs(
      int currentPage,
      int pageSize,
      Date firstLimit,
      Date secondLimit,
      Map<String, Object> filters) {
    return dao.findAllPaginationFilteredLogs(
        currentPage, pageSize, firstLimit, secondLimit, filters);
  }

  @Override
  public Long countFilteredLogs(Map<String, Object> filters) {
    return dao.countFilteredLogs(filters);
  }

  @Override
  public boolean logAction(
      @NonNull final String action, @NonNull final String path, @NonNull final String username) {
    try {
      AuditLogEntity _log = new AuditLogEntity();
      _log.setLogDate(new Date(System.currentTimeMillis()));
      _log.setUserName(username);
      _log.setAction(action);
      _log.setPath(path);
      saveLog(_log);
      return true;
    } catch (Exception e) {
      log.error("error logging user action ", e);
      return false;
    }
  }

  public Long countAllLogs() {
    return dao.countAll();
  }
}
