package com.axiante.mui.dbpromo.persistence.dao.impl;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromozionePianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dto.SottoclasseCountDto;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class JpaPromozionePianificazioneDAOImplTest extends AbstractDaoTest {
    @Inject
    private PromozionePianificazioneDAO dao;

    @Rule
    public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
                    EntityManagerFactoryProducer.class, JpaPromozionePianificazioneDAOImpl.class).activate(RequestScoped.class)
            .inject(this).build();

    @Before
    public void setUp() throws Exception {
        final MeccanicheEntity meccanica = createMeccanica(1L, "M001", "MECCANICA 001");
        final GruppoPromozioneEntity gruppo = createGruppo(1L, "G001");
        final CanalePromozioneEntity canale = createCanale(1L, "CANALE 001", gruppo);
        final CfgPianificazTipoRigaEntity tipoRiga = createTipoRiga(1L, PianificazioneRowTypeEnum.ELEMENTO);
        final StatoPromozioneEntity statoPromo30 = createStatoPromo(1L, PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE);
        final StatoPromozioneEntity statoPromo400 = createStatoPromo(2L, PromoStatusEnum.PROMOZIONE_IN_ESECUZIONE);

        final PromozioneStatoEntity promoStato1 = createPromozioneStato(1L, statoPromo30);
        final PromozioneTestataEntity promozione1 = createPromozione(1L, "PROMO-001", canale,
                promoStato1);
        final PromozionePianificazioneEntity pianificazione1 = createPianificazione(1L, "FOO", promozione1,
                meccanica, tipoRiga);

        final PromozioneStatoEntity promoStato2 = createPromozioneStato(2L, statoPromo400);
        final PromozioneTestataEntity promozione2 = createPromozione(2L, "PROMO-002", canale,
                promoStato2);
        final PromozionePianificazioneEntity pianificazione2 = createPianificazione(2L, "BAR", promozione2,
                meccanica, tipoRiga);

        final PromozioneStatoEntity promoStato3 = createPromozioneStato(3L, statoPromo400);
        final PromozioneTestataEntity promozione3 = createPromozione(3L, "PROMO-003", canale,
                promoStato3);
        final PromozionePianificazioneEntity pianificazione3 = createPianificazione(3L, "BAZ", promozione3,
                meccanica, tipoRiga);

        final PromozioneStatoEntity promoStato4 = createPromozioneStato(4L, statoPromo400);
        final PromozioneTestataEntity promozione4 = createPromozione(4L, "PROMO-004", canale,
                promoStato4);
        final PromozionePianificazioneEntity pianificazione4 = createPianificazione(4L, "BAZ", promozione4,
                meccanica, tipoRiga);
        persist(meccanica, gruppo, canale, tipoRiga, statoPromo30, statoPromo400,
                promoStato1, promoStato2, promoStato3, promoStato4,
                promozione1, promozione2, promozione3, promozione4,
                pianificazione1, pianificazione2, pianificazione3, pianificazione4);
    }

    @Test
    public void countSottoclassiUsedInPromoInProgress() {
        final List<SottoclasseCountDto> result = dao.countSottoclassiUsedInPromoInProgress().stream()
                .sorted(Comparator.comparing(SottoclasseCountDto::getCodice)).collect(Collectors.toList());
        assertEquals(2, result.size());
        assertEquals("BAR", result.get(0).getCodice());
        assertEquals(1L, result.get(0).getCount().longValue());
        assertEquals("BAZ", result.get(1).getCodice());
        assertEquals(2L, result.get(1).getCount().longValue());
    }

    private PromozionePianificazioneEntity createPianificazione(Long id, String sottoclasse, PromozioneTestataEntity promozione,
                                                                MeccanicheEntity meccanica, CfgPianificazTipoRigaEntity tipoRiga) {
        final PromozionePianificazioneEntity entity = new PromozionePianificazioneEntity();
        entity.setId(id);
        entity.setSottoclasse(sottoclasse);
        entity.setPromozioneTestataEntity(promozione);
        entity.setMeccanicaEntity(meccanica);
        entity.setTipoRiga(tipoRiga);
        return entity;
    }

    private CfgPianificazTipoRigaEntity createTipoRiga(Long id, PianificazioneRowTypeEnum type) {
        final CfgPianificazTipoRigaEntity entity = new CfgPianificazTipoRigaEntity();
        entity.setId(id);
        entity.setCodiceTipo(type.getTypeCode());
        entity.setDescrizione(type.getDescription());
        return entity;
    }

    private StatoPromozioneEntity createStatoPromo(Long id, PromoStatusEnum stato) {
        final StatoPromozioneEntity entity = new StatoPromozioneEntity();
        entity.setId(id);
        entity.setCodiceStato(stato.getKey());
        entity.setDescrizione(stato.getDescription());
        return entity;
    }

    private PromozioneStatoEntity createPromozioneStato(Long id, StatoPromozioneEntity statoPromo) {
        final PromozioneStatoEntity entity = new PromozioneStatoEntity();
        entity.setId(id);
        entity.setStatoPromozioneEntity(statoPromo);
        return entity;
    }

    private PromozioneTestataEntity createPromozione(Long id, String codicePromozione, CanalePromozioneEntity canale,
                                                     PromozioneStatoEntity promoStato) {
        final PromozioneTestataEntity entity = new PromozioneTestataEntity();
        entity.setId(id);
        entity.setCodicePromozione(codicePromozione);
        entity.setDataInizio(new Date());
        entity.setDataFine(new Date());
        entity.setCanalePromozioneEntity(canale);
        if (entity.getPromozioneStatoEntities() == null) {
            entity.setPromozioneStatoEntities(new HashSet<>());
        }
        entity.addPromozioneStatoEntity(promoStato);
        return entity;
    }

    private MeccanicheEntity createMeccanica(Long id, String codice, String descrizione) {
        final MeccanicheEntity entity = new MeccanicheEntity();
        entity.setId(id);
        entity.setCodiceMeccanica(codice);
        entity.setDescrizione(descrizione);
        return entity;
    }

    private CanalePromozioneEntity createCanale(Long id, String descrizione, GruppoPromozioneEntity gruppo) {
        final CanalePromozioneEntity entity = new CanalePromozioneEntity();
        entity.setId(id);
        entity.setCodiceCanale(id);
        entity.setDescrizione(descrizione);
        entity.setGruppoPromozioneEntity(gruppo);
        return entity;
    }

    private GruppoPromozioneEntity createGruppo(Long id, String codice) {
        final GruppoPromozioneEntity entity = new GruppoPromozioneEntity();
        entity.setId(id);
        entity.setCodiceGruppo(codice);
        entity.setDescrizione(String.format("GRUPPO %03d", id));
        return entity;
    }
}