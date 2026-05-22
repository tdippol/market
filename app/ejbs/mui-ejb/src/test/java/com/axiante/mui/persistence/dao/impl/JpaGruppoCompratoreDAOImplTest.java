package com.axiante.mui.persistence.dao.impl;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.persistence.EntityManagerProducer;
import com.axiante.mui.persistence.dao.GruppoCompratoreDAO;
import com.axiante.mui.persistence.entity.CompratoreEntity;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.entity.GruppoCompratoreEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JpaGruppoCompratoreDAOImplTest extends DaoTest {

    @Inject
    private GruppoCompratoreDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(EntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaGruppoCompratoreDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private GroupEntity group1;
    private GroupEntity group2;

    @Before
    public void setUp() {
        group1 = createGroup(11, "G-01");
        group2 = createGroup(12, "G-02");
        CompratoreEntity compratore1 = createCompratore(1, "S01");
        CompratoreEntity compratore2 = createCompratore(2, "S02");
        GruppoCompratoreEntity groupBuyer1 = createGruppoCompratore(group1, compratore1, PianificazioneSecurityEnum.READ);
        GruppoCompratoreEntity groupBuyer2 = createGruppoCompratore(group2, compratore2, PianificazioneSecurityEnum.WRITE);
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            persist(group1, group2, compratore1, compratore2, groupBuyer1, groupBuyer2);
            em.getTransaction().commit();
        }
    }

    @Test
    public void findAllCodiciCompratoreByGroupAndTipoAccesso_givenNullSecurity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllCodiciCompratoreByGroupAndTipoAccesso(Arrays.asList(group1, group2), null);
    }

    @Test
    public void findAllCodiciCompratoreByGroupAndTipoAccesso_givenNullGroup_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        ex.expectMessage("groups cannot be null or empty");
        dao.findAllCodiciCompratoreByGroupAndTipoAccesso(null, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void findAllCodiciCompratoreByGroupAndTipoAccesso_givenEmptyGroup_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        ex.expectMessage("groups cannot be null or empty");
        dao.findAllCodiciCompratoreByGroupAndTipoAccesso(Collections.emptyList(), PianificazioneSecurityEnum.READ);
    }

    @Test
    public void findAllCodiciCompratoreByGroupAndTipoAccesso_givenGroupAndSecurity_shouldReturnEntities() {
        List<String> codes = dao.findAllCodiciCompratoreByGroupAndTipoAccesso(Arrays.asList(group1, group2),
                PianificazioneSecurityEnum.READ);
        assertEquals(1, codes.size());
        assertTrue(codes.contains("S01"));
        assertFalse(codes.contains("S02"));
        codes = dao.findAllCodiciCompratoreByGroupAndTipoAccesso(Arrays.asList(group1, group2),
                PianificazioneSecurityEnum.WRITE);
        assertEquals(1, codes.size());
        assertFalse(codes.contains("S01"));
        assertTrue(codes.contains("S02"));
        codes = dao.findAllCodiciCompratoreByGroupAndTipoAccesso(Collections.singletonList(group1),
                PianificazioneSecurityEnum.WRITE);
        assertTrue(codes.isEmpty());
    }

    @Test
    public void findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso_givenNullSecurity_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(
                Arrays.asList(group1.getCodiceGruppo(), group2.getCodiceGruppo()), null);
    }

    @Test
    public void findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso_givenNullCodiciGruppo_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(null, PianificazioneSecurityEnum.READ);
    }

    @Test
    public void findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso_givenEmptyCodiciGruppo_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(Collections.emptyList(), PianificazioneSecurityEnum.READ);
    }

    @Test
    public void findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso_givenCodiciGruppoAndSecurity_shouldReturnEntities() {
        List<String> codes = dao.findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(
                Arrays.asList(group1.getCodiceGruppo(), group2.getCodiceGruppo()), PianificazioneSecurityEnum.READ);
        assertEquals(1, codes.size());
        assertTrue(codes.contains("S01"));
        assertFalse(codes.contains("S02"));
        codes = dao.findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(
                Arrays.asList(group1.getCodiceGruppo(), group2.getCodiceGruppo()), PianificazioneSecurityEnum.WRITE);
        assertEquals(1, codes.size());
        assertFalse(codes.contains("S01"));
        assertTrue(codes.contains("S02"));
        codes = dao.findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(
                Collections.singletonList(group1.getCodiceGruppo()), PianificazioneSecurityEnum.WRITE);
        assertTrue(codes.isEmpty());
    }

    @Test
    public void findAllCodiciCompratoreByCodiciGruppo_givenNullCodiciGruppo_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllCodiciCompratoreByCodiciGruppo(null);
    }

    @Test
    public void findAllCodiciCompratoreByCodiciGruppo_givenEmptyCodiciGruppo_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findAllCodiciCompratoreByCodiciGruppo(Collections.emptyList());
    }

    @Test
    public void findAllCodiciCompratoreByCodiciGruppo_givenCodiciGruppo_shouldReturnEntities() {
        List<String> codes = dao.findAllCodiciCompratoreByCodiciGruppo(
                Arrays.asList(group1.getCodiceGruppo(), group2.getCodiceGruppo()));
        assertEquals(2, codes.size());
        assertTrue(codes.contains("S01"));
        assertTrue(codes.contains("S02"));
        codes = dao.findAllCodiciCompratoreByCodiciGruppo(Arrays.asList(group1.getCodiceGruppo(), "G-42"));
        assertEquals(1, codes.size());
        assertTrue(codes.contains("S01"));
        assertFalse(codes.contains("S02"));
        codes = dao.findAllCodiciCompratoreByCodiciGruppo(Arrays.asList("G-23", "G-42"));
        assertTrue(codes.isEmpty());
    }

    private GruppoCompratoreEntity createGruppoCompratore(GroupEntity group, CompratoreEntity compratore,
                                                          PianificazioneSecurityEnum security) {
        final GruppoCompratoreEntity entity = new GruppoCompratoreEntity();
        entity.setGruppo(group);
        entity.setCompratore(compratore);
        entity.setTipoAccesso(security);
        return entity;
    }

    private GroupEntity createGroup(Integer id, String codice) {
        final GroupEntity group = new GroupEntity();
        group.setId(id);
        group.setCodiceGruppo(codice);
        return group;
    }

    private CompratoreEntity createCompratore(Integer id, String codice) {
        final CompratoreEntity compratore = new CompratoreEntity();
        compratore.setId(id);
        compratore.setCodiceCompratore(codice);
        compratore.setFlagAttivo(1L);
        return compratore;
    }

    private void persist(Object... entities) {
        for (Object entity : entities) {
            entity = em.merge(entity);
            em.persist(entity);
        }
    }
}