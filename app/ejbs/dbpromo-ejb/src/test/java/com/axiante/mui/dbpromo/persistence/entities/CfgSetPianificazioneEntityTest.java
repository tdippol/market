package com.axiante.mui.dbpromo.persistence.entities;

//
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertThat;
//
//import java.util.HashSet;
//
//import org.hamcrest.CoreMatchers;
//import org.junit.Before;
//import org.junit.Test;
//
public class CfgSetPianificazioneEntityTest {
//	private final CfgSetPianificazioneEntity entity = new CfgSetPianificazioneEntity();
//
//	@Before
//	public void init() {
//		entity.setMuiCfgPianificaziones(new HashSet<CfgPianificazioneEntity>());
//		entity.setPromozioneTestataEntities(new HashSet<PromozioneTestataEntity>());
//	}
//
//	@Test
//	public void whenAddCfgPianificazioneEntityThenEntityInList() {
//		CfgPianificazioneEntity e = new CfgPianificazioneEntity();
//		int size = entity.getMuiCfgPianificaziones().size();
//		entity.getMuiCfgPianificaziones().add(e);
//		e.setMuiCfgSetPianificazione(entity);
//		assertThat(entity.getMuiCfgPianificaziones().size(), CoreMatchers.equalTo(size + 1));
//		assertThat(e.getMuiCfgSetPianificazione(), CoreMatchers.equalTo(entity));
//	}
//
//	@Test
//	public void whenRemoveCfgPianificazioneEntityThenEntityNotInList() {
//		CfgPianificazioneEntity e = new CfgPianificazioneEntity();
//		int size = entity.getMuiCfgPianificaziones().size();
//		entity.getMuiCfgPianificaziones().add(e);
//		e.setMuiCfgSetPianificazione(entity);
//
//		assertThat(entity.getMuiCfgPianificaziones().size(), CoreMatchers.equalTo(size + 1));
//		assertThat(e.getMuiCfgSetPianificazione(), CoreMatchers.equalTo(entity));
//
//		entity.getMuiCfgPianificaziones().remove(e);
//		e.setMuiCfgSetPianificazione(null);
//
//		assertThat(entity.getMuiCfgPianificaziones().size(), CoreMatchers.equalTo(size));
//		assertNull(e.getMuiCfgSetPianificazione());
//
//	}
//
//	@Test
//	public void whenAddPromozioneTestataEntityThenEntityInList() {
//		PromozioneTestataEntity e = new PromozioneTestataEntity();
//		int size = entity.getPromozioneTestataEntities().size();
//		entity.addPromozioneTestataEntity(e);
//		assertThat(entity.getPromozioneTestataEntities().size(), CoreMatchers.equalTo(size + 1));
//		assertThat(e.getMuiCfgSetPianificazione(), CoreMatchers.equalTo(entity));
//	}
//
//	@Test
//	public void whenRemovePromozioneTestataEntityThenEntityNotInList() {
//		PromozioneTestataEntity e = new PromozioneTestataEntity();
//		int size = entity.getPromozioneTestataEntities().size();
//		entity.addPromozioneTestataEntity(e);
//		assertThat(entity.getPromozioneTestataEntities().size(), CoreMatchers.equalTo(size + 1));
//		assertThat(e.getMuiCfgSetPianificazione(), CoreMatchers.equalTo(entity));
//
//		entity.removePromozioneTestataEntity(e);
//		assertThat(entity.getPromozioneTestataEntities().size(), CoreMatchers.equalTo(size));
//		assertNull(e.getMuiCfgSetPianificazione());
//	}
}
