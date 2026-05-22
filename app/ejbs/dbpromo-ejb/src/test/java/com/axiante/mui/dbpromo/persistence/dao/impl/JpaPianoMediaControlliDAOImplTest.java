package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PianoMediaControlliDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaControlliEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaCfgCheckEntity;
import com.axiante.mui.dbpromo.persistence.entities.SupportoMediaEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaPianoMediaControlliDAOImplTest extends AbstractDaoTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
                    JpaPianoMediaControlliDAOImpl.class)
            .activate(RequestScoped.class).inject(this).build();

    @Inject
    private PianoMediaControlliDAO dao;

    private PianoMediaEntity pianoMedia;
    private PianificazionePianoMediaEntity pianificazioneMedia;
    private PianoMediaControlliEntity check1;
    private PianoMediaControlliEntity check2;
    private PianoMediaControlliEntity check3;

    @Before
    public void setUp() throws Exception {
        GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("GR1").build();
        CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder(1L).withCodice(1L).withGruppo(gruppo).build();
        pianoMedia = createPianoMedia(1L, canale, 2023);
        final SupportoMediaEntity supportoMedia = createSupportoMedia(1L);
        pianificazioneMedia = createPianificazioneMedia(1L, pianoMedia, supportoMedia);
        final PianificazionePianoMediaEntity pianificazioneMedia1 = createPianificazioneMedia(2L, pianoMedia, supportoMedia);
        final SupportoMediaCfgCheckEntity cfgCheck = createSupportoMediaCfgCheck(1L, supportoMedia, "CHK1");
        check1 = createPianoMediaControllo(1L, pianificazioneMedia, cfgCheck);
        check2 = createPianoMediaControllo(2L, pianificazioneMedia, cfgCheck);
        check3 = createPianoMediaControllo(3L, pianificazioneMedia1, cfgCheck);
        openTransaction();
        getEm().merge(pianificazioneMedia);
        getEm().merge(check1);
        getEm().merge(check2);
        getEm().merge(check3);
        getEm().flush();
        commitTransaction();
    }

    @Test
    public void findByPianificazioneMedia_givenNullPianificazioneMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByPianificazioneMedia(null);
    }

    @Test
    public void findByPianificazioneMedia() {
        final List<PianoMediaControlliEntity> checks = dao.findByPianificazioneMedia(pianificazioneMedia);
        assertEquals(2, checks.size());
        assertTrue(checks.contains(check1));
        assertTrue(checks.contains(check2));
        assertFalse(checks.contains(check3));
    }

    @Test
    public void findByIdPianificazioniMedia_givenNullIdPianificazioni_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByIdPianificazioniMedia(null);
    }

    @Test
    public void findByIdPianificazioniMedia_givenEmptyIdPianificazioni_shouldThrowException() {
        ex.expect(IllegalArgumentException.class);
        dao.findByIdPianificazioniMedia(Collections.emptyList());
    }

    @Test
    public void findByIdPianificazioniMedia() {
        final List<PianoMediaControlliEntity> checks = dao
                .findByIdPianificazioniMedia(Stream.of(1L, 2L).collect(Collectors.toList()));
        assertEquals(3, checks.size());
    }

    @Test
    public void findByPianoMedia_givenNullPianoMedia_shouldThrowException() {
        ex.expect(NullPointerException.class);
        dao.findByPianoMedia(null);
    }

    @Test
    public void findByPianoMedia() {
        final List<PianoMediaControlliEntity> checks = dao.findByPianoMedia(pianoMedia);
        assertEquals(3, checks.size());
        assertTrue(checks.contains(check1));
        assertTrue(checks.contains(check2));
        assertTrue(checks.contains(check3));
    }

    @Test
    public void findByCodiceSupportoConfigurato() {
        final List<PianoMediaControlliEntity> checks = dao.findByCodiceSupportoConfigurato("CHK1");
        assertEquals(3, checks.size());
        assertTrue(checks.contains(check1));
        assertTrue(checks.contains(check2));
        assertTrue(checks.contains(check3));
    }

    @Test
    public void countByCodiceSupportoConfigurato() {
        assertEquals(3, dao.countByCodiceSupportoConfigurato("CHK1"));
    }

    private PianoMediaControlliEntity createPianoMediaControllo(Long id, PianificazionePianoMediaEntity pianificazioneMedia,
                                                                SupportoMediaCfgCheckEntity supportoMediaCfgChech) {
        final PianoMediaControlliEntity e = new PianoMediaControlliEntity();
        e.setId(id);
        e.setPianificazioneMedia(pianificazioneMedia);
        e.setSupportoMediaCfgCheck(supportoMediaCfgChech);
        return e;
    }

    private SupportoMediaCfgCheckEntity createSupportoMediaCfgCheck(Long id, SupportoMediaEntity supportoMedia,
                                                                    String codiceControllo) {
        final SupportoMediaCfgCheckEntity e = new SupportoMediaCfgCheckEntity();
        e.setId(id);
        e.setSupportoMedia(supportoMedia);
        e.setCodiceControllo(codiceControllo);
        return e;
    }

    private PianificazionePianoMediaEntity createPianificazioneMedia(Long id, PianoMediaEntity pianoMedia,
                                                                     SupportoMediaEntity supportoMedia) {
        final PianificazionePianoMediaEntity e = new PianificazionePianoMediaEntity();
        e.setId(id);
        e.setPianoMedia(pianoMedia);
        e.setSupportoMedia(supportoMedia);
        return e;
    }

    private PianoMediaEntity createPianoMedia(Long id, CanalePromozioneEntity canale, Integer anno) {
        final PianoMediaEntity e = new PianoMediaEntity();
        e.setId(id);
        e.setCanale(canale);
        e.setAnno(anno);
        return e;
    }

    private SupportoMediaEntity createSupportoMedia(Long id) {
        final SupportoMediaEntity e = new SupportoMediaEntity();
        e.setId(id);
        return e;
    }
}