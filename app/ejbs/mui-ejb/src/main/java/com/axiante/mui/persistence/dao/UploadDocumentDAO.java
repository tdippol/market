package com.axiante.mui.persistence.dao;

import com.axiante.mui.persistence.entity.UploadDocumentEntity;

import java.util.List;

public interface UploadDocumentDAO {
    List<UploadDocumentEntity> readAll();
    void delete(UploadDocumentEntity entity) throws Exception;
    UploadDocumentEntity persistWithAuditLog(UploadDocumentEntity entity, String user);
    UploadDocumentEntity findByName(String name) throws Exception;
}
