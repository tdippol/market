package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgCanaleNegoziDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleNegoziEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.NegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoNegozioEntity;
import java.util.HashSet;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaCfgCanaleNegoziDAOImplTest extends AbstractDaoTest {

    @Inject
    private CfgCanaleNegoziDAO cfgCanaleNegoziDAO;

    @Rule
    public WeldInitiator weld = WeldInitiator
            .from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaCfgCanaleNegoziDAOImpl.class, DbPromo.class)
            .activate(RequestScoped.class).inject(this).build();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    CanalePromozioneEntity canalePromozione = null;

    @Before
    public void setUp() throws Exception {

        final NegozioEntity negozioEntity = createNegozio("TYPE_1", 2L, "111", "FMT", "00", "descrizione 1", "S");
        final TipoNegozioEntity tipoNegozioEntity = createTipoNegozioEntity(negozioEntity, "descrizione 1", "S");
        negozioEntity.setTipoNegozioEntity(tipoNegozioEntity);

        final CfgCanaleNegoziEntity cfgCanaleNegozi = createCfgCanaleNegozi(canalePromozione, negozioEntity);
        //
        canalePromozione = createCanalePromozione(100L, 10L, 100L, "descrizione test",
                createGruppoPromozioneEntity("GR1", "gruppo 1"));

        canalePromozione.setMuiCfgCanaleNegozis(new HashSet<>());
        canalePromozione.addMuiCfgCanaleNegozi(cfgCanaleNegozi);

        openTransaction();
        cfgCanaleNegoziDAO.persist(canalePromozione);
        commitTransaction();
    }

    @Test
    public void shouldFindById() {
        final CanalePromozioneEntity canalePromozioneEntity = cfgCanaleNegoziDAO.findById(2L);
        assertNotNull(canalePromozioneEntity);
        assertTrue(canalePromozioneEntity.getId() == 2L);
        assertTrue(canalePromozioneEntity.getCodiceCanale() == 100L);
    }

    @Test
    public void shouldReadAll() {
        List<CanalePromozioneEntity> canalePromozioneList = cfgCanaleNegoziDAO.findAll();
        assertNotNull(canalePromozioneList);
        assertFalse(canalePromozioneList.isEmpty());
    }

    @Test
    public void shouldFindAllById() {
        final List<CfgCanaleNegoziEntity> allByIdCanale = cfgCanaleNegoziDAO.findAllByIdCanale(canalePromozione);
        assertNotNull(allByIdCanale);
        assertFalse(allByIdCanale.isEmpty());
    }

    @Test
    public void shouldNotFindById() {
        final List<CfgCanaleNegoziEntity> allByIdCanale = cfgCanaleNegoziDAO.findAllByIdCanale(null);
        assertNotNull(allByIdCanale);
        assertTrue(allByIdCanale.isEmpty());
    }

    private TipoNegozioEntity createTipoNegozioEntity(NegozioEntity negozioEntity, String descrizione, String codiceTipoNegozio) {
        TipoNegozioEntity tipoNegozioEntity = new TipoNegozioEntity();
        tipoNegozioEntity.setNegozioEntities(new HashSet<>());
        tipoNegozioEntity.addMuiNegozio(negozioEntity);

        tipoNegozioEntity.setDescrizione(descrizione);
        tipoNegozioEntity.setId(99L);
        tipoNegozioEntity.setCodiceTipoNegozio(codiceTipoNegozio);
        return tipoNegozioEntity;
    }

    private NegozioEntity createNegozio(String tipoNegozio, Long codiceCluster, String codiceNegozio,
                                        String codiceFormato, String codiceZona, String descrizione, String societa) {
        NegozioEntity negozioEntity = new NegozioEntity();
        negozioEntity.setId(5L);
        negozioEntity.setTipoNegozio(tipoNegozio);
        negozioEntity.setCodiceCluster(codiceCluster);
        negozioEntity.setCodiceNegozio(codiceNegozio);
        negozioEntity.setCodiceFormato(codiceFormato);
        negozioEntity.setCodiceZona(codiceZona);
        negozioEntity.setDescrizione(descrizione);
        negozioEntity.setSocieta(societa);
        return negozioEntity;
    }

    private CfgCanaleNegoziEntity createCfgCanaleNegozi(CanalePromozioneEntity canalePromozioneEntity, NegozioEntity negozioEntity) {
        CfgCanaleNegoziEntity cfgCanaleNegoziEntity = new CfgCanaleNegoziEntity();
        cfgCanaleNegoziEntity.setId(1L);
        cfgCanaleNegoziEntity.setMuiCanalePromozione(canalePromozioneEntity);
        cfgCanaleNegoziEntity.setNegozioEntity(negozioEntity);
        return cfgCanaleNegoziEntity;
    }

    private CanalePromozioneEntity createCanalePromozione(Long codiceCanale, Long codeRangeMin, Long codeRangeMax, String descrizione, GruppoPromozioneEntity gruppoPromozioneEntity) {
        CanalePromozioneEntity canalePromozioneEntity = new CanalePromozioneEntity();
        canalePromozioneEntity.setId(2L);
        canalePromozioneEntity.setCodiceCanale(codiceCanale);
        canalePromozioneEntity.setCodeRangeMin(codeRangeMin);
        canalePromozioneEntity.setCodeRangeMax(codeRangeMax);
        canalePromozioneEntity.setDescrizione(descrizione);
        canalePromozioneEntity.setGruppoPromozioneEntity(gruppoPromozioneEntity);
        return canalePromozioneEntity;
    }

    private GruppoPromozioneEntity createGruppoPromozioneEntity(String codiceGruppo, String descrizione) {
        GruppoPromozioneEntity gruppoPromozioneEntity = new GruppoPromozioneEntity();
        gruppoPromozioneEntity.setId(2L);
        gruppoPromozioneEntity.setCodiceGruppo(codiceGruppo);
        gruppoPromozioneEntity.setDescrizione(descrizione);
        return gruppoPromozioneEntity;
    }
}
