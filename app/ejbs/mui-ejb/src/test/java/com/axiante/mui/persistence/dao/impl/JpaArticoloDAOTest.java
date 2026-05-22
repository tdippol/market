package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.ArticoloDAO;
import com.axiante.mui.persistence.entity.ArticoloEntity;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCompratoreEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JpaArticoloDAOTest extends DaoTest {

    @Inject
    private ArticoloDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaArticoloDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Test(expected = NullPointerException.class)
    public void countByArticoloIdAndCodiciGruppo_givenNullArticoloId_shouldThrowException() {
        dao.countByArticoloIdAndCodiciGruppo(null, Arrays.asList("GR1", "GR2"));
    }

    @Test(expected = NullPointerException.class)
    public void countByArticoloIdAndCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
        dao.countByArticoloIdAndCodiciGruppo(1L, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void countByArticoloIdAndCodiciGruppo_givenEmptyCodiciGruppo_shouldThrowException() {
        dao.countByArticoloIdAndCodiciGruppo(1L, Collections.emptyList());
    }

    @Test
    public void countByArticoloIdAndCodiciGruppo_givenArticoloIdAndCodiciGruppo_shouldReturnCount() {
        CompratoreEntity compratore = createCompratore(1, "C01");
        GroupEntity gruppo = createGruppo("GR1");
        createArticolo(1L, compratore);
        createGruppoCompratore(gruppo, compratore, PianificazioneSecurityEnum.READ);
        assertEquals(1, (long) dao.countByArticoloIdAndCodiciGruppo(1L, Arrays.asList("GR1", "GF2")));
    }

    @Test(expected = NullPointerException.class)
    public void countWritableByArticoloIdAndCodiciGruppo_givenNullArticoloId_shouldThrowException() {
        dao.countWritableByArticoloIdAndCodiciGruppo(null, Arrays.asList("GR1", "GR2"));
    }

    @Test(expected = NullPointerException.class)
    public void countWritableByArticoloIdAndCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
        dao.countWritableByArticoloIdAndCodiciGruppo(1L, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void countWritableByArticoloIdAndCodiciGruppo_givenEmptyCodiciGruppo_shouldThrowException() {
        dao.countWritableByArticoloIdAndCodiciGruppo(1L, Collections.emptyList());
    }

    @Test
    public void countWritableByArticoloIdAndCodiciGruppo_givenArticoloIdAndCodiciGruppo_shouldReturnCount() {
        CompratoreEntity compratore1 = createCompratore(1, "C01");
        CompratoreEntity compratore2 = createCompratore(2, "C02");
        GroupEntity gruppo1 = createGruppo("GR1");
        GroupEntity gruppo2 = createGruppo("GR2");
        createArticolo(1L, compratore1);
        createArticolo(2L, compratore2);
        createGruppoCompratore(gruppo1, compratore1, PianificazioneSecurityEnum.WRITE);
        createGruppoCompratore(gruppo2, compratore2, PianificazioneSecurityEnum.READ);
        assertEquals(1, (long) dao.countWritableByArticoloIdAndCodiciGruppo(1L, Arrays.asList("GR1", "GR2")));
    }

    @Test(expected = NullPointerException.class)
    public void findWritableCompratoriByIdArticoliAndCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
        dao.findWritableCompratoriByIdArticoliAndCodiciGruppo(Arrays.asList(1L, 2L), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findWritableCompratoriByIdArticoliAndCodiciGruppo_givenEmptyCodiciGruppo_shouldThrowException() {
        dao.findWritableCompratoriByIdArticoliAndCodiciGruppo(Arrays.asList(1L, 2L), Collections.emptyList());
    }

    @Test(expected = NullPointerException.class)
    public void findWritableCompratoriByIdArticoliAndCodiciGruppo_givenNullIdArticoli_shouldThrowException() {
        dao.findWritableCompratoriByIdArticoliAndCodiciGruppo(null, Arrays.asList("FOO", "BAR"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findWritableCompratoriByIdArticoliAndCodiciGruppo_givenEmptyIdArticoli_shouldThrowException() {
        dao.findWritableCompratoriByIdArticoliAndCodiciGruppo(Collections.emptyList(), Arrays.asList("FOO", "BAR"));
    }

    @Test
    public void findWritableCompratoriByIdArticoliAndCodiciGruppo_givenIdArticoliAndCodiceGruppo_shouldReturnCodiciCompratori() {
        CompratoreEntity compratore1 = createCompratore(1, "C01");
        CompratoreEntity compratore2 = createCompratore(2, "C02");
        GroupEntity gruppo1 = createGruppo("GR1");
        GroupEntity gruppo2 = createGruppo("GR2");
        createArticolo(1L, compratore1);
        createArticolo(2L, compratore2);
        createGruppoCompratore(gruppo1, compratore1, PianificazioneSecurityEnum.WRITE);
        createGruppoCompratore(gruppo2, compratore2, PianificazioneSecurityEnum.READ);
        final List<String> codiciCompratore = dao.findWritableCompratoriByIdArticoliAndCodiciGruppo(Arrays.asList(1L, 2L),
                Arrays.asList("GR1", "GR2"));
        assertEquals(1, codiciCompratore.size());
        assertTrue(codiciCompratore.contains("C01"));
        assertFalse(codiciCompratore.contains("C02"));
    }

    private void createGruppoCompratore(GroupEntity gruppo, CompratoreEntity compratore,
                                        PianificazioneSecurityEnum security) {
        GruppoCompratoreEntity entity = new GruppoCompratoreEntity();
        entity.setGruppo(gruppo);
        entity.setCompratore(compratore);
        entity.setTipoAccesso(security);
        try {
            getEm().getTransaction().begin();
            entity = getEm().merge(entity);
            getEm().persist(entity);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    private void createArticolo(Long id, CompratoreEntity compratore) {
        ArticoloEntity entity = new ArticoloEntity();
        entity.setId(id);
        entity.setCompratore(compratore);
        try {
            getEm().getTransaction().begin();
            entity = getEm().merge(entity);
            getEm().persist(entity);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    private GroupEntity createGruppo(String codice) {
        GroupEntity entity = new GroupEntity();
        entity.setCodiceGruppo(codice);
        try {
            getEm().getTransaction().begin();
            entity = getEm().merge(entity);
            getEm().persist(entity);
            getEm().getTransaction().commit();
            return entity;
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
        return null;
    }

    private CompratoreEntity createCompratore(Integer id, String codice) {
        CompratoreEntity entity = new CompratoreEntity();
        entity.setId(id);
        entity.setCodiceCompratore(codice);
        entity.setFlagAttivo(1L);
        try {
            getEm().getTransaction().begin();
            entity = getEm().merge(entity);
            getEm().persist(entity);
            getEm().getTransaction().commit();
            return entity;
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
        return null;
    }
}