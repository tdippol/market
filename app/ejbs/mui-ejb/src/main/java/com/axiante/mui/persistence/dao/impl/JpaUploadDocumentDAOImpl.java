package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.persistence.dao.UploadDocumentDAO;
import com.axiante.mui.persistence.entity.UploadDocumentEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;

@Stateless
@Slf4j
public class JpaUploadDocumentDAOImpl implements UploadDocumentDAO {

    @Inject
    @Getter(value = AccessLevel.PROTECTED)
    private EntityManager em;

    @Override
    public List<UploadDocumentEntity> readAll() {
        return em.createQuery("SELECT e FROM UploadDocumentEntity e", UploadDocumentEntity.class)
                .getResultList();
    }

    @Override
    public void delete(UploadDocumentEntity entity) throws Exception {
        try {
            entity = em.merge(entity);
            em.remove(entity);
            em.flush();
        } catch (PersistenceException ex) {
            throw new Exception("Impossibile rimuovere entity con id: " + entity.getId());
        }
    }

    @Override
    public UploadDocumentEntity persistWithAuditLog(UploadDocumentEntity entity, String user) {
        // Update entity with audit info
        final Date date = new Date(System.currentTimeMillis());
        if (entity.getId() == null) {
            entity.setDataInserimento(date);
            entity.setCodiceUtenteInserimento(user);
        } else {
            entity.setDataAggiornamento(date);
            entity.setCodiceUtenteAggiornamento(user);
        }
        entity = em.merge(entity);
        em.persist(entity);
        em.flush();
        return entity;
    }

    @Override
    public UploadDocumentEntity findByName(String name) throws Exception {
        try {
            return em.createQuery("SELECT e FROM UploadDocumentEntity e WHERE e.name=:name", UploadDocumentEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            log.warn("No document exists with name: " + name, ex);
            return null;
        }
    }
}
