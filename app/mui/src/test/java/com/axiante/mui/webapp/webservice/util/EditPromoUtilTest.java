package com.axiante.mui.webapp.webservice.util;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.webapp.business.ActionService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EditPromoUtilTest {

	@Mock
	private Instance<ActionService> actionServiceInstance;

	@Mock
	private ActionService actionService;

	@InjectMocks
	private EditPromoUtil  editPromoUtil;

	private PromozioneTestataEntity testata = new PromozioneTestataEntity();
	private PromozioneTestataEntity testata2 = new PromozioneTestataEntity();
	private List<PromozioneTestataEntity> listaTestata = new ArrayList<>();
	
	@Before
	public void setUp() {
		Set<PromozioneStatoEntity> setPromozioneStatoEntities = new HashSet<>();
		PromozioneStatoEntity promozioneStato = new PromozioneStatoEntity();
		PromozioneStatoEntity promozioneStato2 = new PromozioneStatoEntity();
		StatoPromozioneEntity statoPromozione = new StatoPromozioneEntity();
		StatoPromozioneEntity statoPromozione2 = new StatoPromozioneEntity();
		statoPromozione.setCodiceStato("1");
		statoPromozione.setLabel("Test");
		statoPromozione2.setCodiceStato("2");
		statoPromozione2.setLabel("Test2");
		promozioneStato.setStatoPromozioneEntity(statoPromozione);
		promozioneStato2.setStatoPromozioneEntity(statoPromozione2);
		promozioneStato.setDataInizioStato(new Date());
		promozioneStato.setDataFineStato(new Date());
		promozioneStato2.setDataInizioStato(new Date());
		setPromozioneStatoEntities.add(promozioneStato);
		setPromozioneStatoEntities.add(promozioneStato2);
		CanalePromozioneEntity canalePromozioneEntity = new CanalePromozioneEntity();
		canalePromozioneEntity.setDescrizione("Canale Promozione entity DESC");
		GruppoPromozioneEntity gruppoPromozioneEntity = new GruppoPromozioneEntity();
		gruppoPromozioneEntity.setDescrizione("Gruppo Promozione entity DESC");
		canalePromozioneEntity.setGruppoPromozioneEntity(gruppoPromozioneEntity);
		
		testata.setDataInizio(new Date());
		testata.setDataFine(new Date());
		testata.setDescrizione("Natale");
		testata.setCodicePromozione("123");
		testata.setId((long) 1);
		testata.setPromozioneStatoEntities(setPromozioneStatoEntities);
		testata.setDescrizioneEstesa("Descrizione Estesa");
		testata.setMuiCanalePromozione(canalePromozioneEntity);
		testata.setNoteMarketing("Note Marketing Testata1");
		
		
		testata2.setDataInizio(new Date());
		testata2.setDataFine(new Date());
		testata2.setDescrizione("Natale");
		testata2.setCodicePromozione("124");
		testata2.setId((long) 2);
		testata2.setPromozioneStatoEntities(setPromozioneStatoEntities);
		testata2.setDescrizioneEstesa("Descrizione Estesa2");
		testata2.setMuiCanalePromozione(canalePromozioneEntity);
		testata2.setNoteMarketing("Note Marketing Testata2");

		listaTestata.add(testata);
		listaTestata.add(testata2);

		when(actionServiceInstance.get()).thenReturn(actionService);
	}
	
	@Test
	public void testCreateDescrizioneEstesa() {
		assertNotNull(editPromoUtil.createDescrizioneEstesa(testata));
	}
	
	@Test
	public void testCreateRowData() {
		assertNotNull(editPromoUtil.createRowData(listaTestata, null, false));
	}
}
