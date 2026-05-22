package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.AuditLogInterface;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaDbPromoDAOTest {
    @Mock
    private EntityManager em;

    @Spy
    @InjectMocks
    private JpaRepartoDAOImpl dao;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void persist_givenNullEntity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.persist((RepartoEntity) null);
    }

    @Test
    public void persist_givenListOfEntities_shouldSaveAll() {
        @SuppressWarnings("unchecked")
		List<RepartoEntity> entities = mock(List.class);
        when(entities.size()).thenReturn(5);
        when(entities.get(0)).thenReturn(null);
        for (int i = 1; i < 5; i++) {
            when(entities.get(i)).thenReturn(mock(RepartoEntity.class));
        }
        dao.persist(entities);
        verify(em, times(4)).persist(any());
    }

    @Test
    public void persistInTransaction_givenNullEntity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.persistInTransaction(null);
    }

    @Test
    public void persistInTransaction_givenListOfEntities_shouldSaveAll() {
        @SuppressWarnings("unchecked")
		List<RepartoEntity> entities = mock(List.class);
        when(entities.size()).thenReturn(5);
        for (int i = 0; i < 5; i++) {
            when(entities.get(i)).thenReturn(mock(RepartoEntity.class));
        }
        dao.persistInTransaction(entities);
        verify(em, times(entities.size())).persist(any());
    }

    @Test
    public void persist_givenNullList_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.persist((List<RepartoEntity>) null);
    }

    @Test
    public void remove_givenNullEntity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.remove((RepartoEntity) null);
    }

    @Test
    public void remove_givenValidEntity_shouldRemoveGivenEntity() {
        final RepartoEntity mockEntity = mock(RepartoEntity.class);
        dao.remove(mockEntity);
        verify(em, times(1)).remove(any());
        verify(em, times(1)).flush();
    }

    @Test
    public void findById_givenNullId_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findById(null);
    }

    @Test
    public void persistWithAuditLog_givenListOfEntities_shouldSaveAll() {
        @SuppressWarnings("unchecked")
		List<AuditLogInterface> entities = mock(List.class);
        when(entities.size()).thenReturn(5);
        when(entities.get(0)).thenReturn(null);
        for (int i = 1; i < 5; i++) {
            when(entities.get(i)).thenReturn(mock(AuditLogInterface.class));
        }
        dao.persistWithAuditLog(entities, "mockito");
        verify(em, times(4)).persist(any());
    }

    @Test
    public void persistWithAuditLog_givenEntity_shouldSave() {
        dao.persistWithAuditLog(mock(AuditLogInterface.class), "mockito");
        verify(em, times(1)).persist(any());
        verify(em, times(1)).flush();
    }

    @Test
    public void getTransaction_shouldReturnValidTransaction() {
        dao.getTransaction();
        verify(em, times(1)).getTransaction();
    }

    @Test
    public void flush_shouldCallFlushOnEntityManager() {
        dao.flush();
        verify(em, times(1)).flush();
    }
}
