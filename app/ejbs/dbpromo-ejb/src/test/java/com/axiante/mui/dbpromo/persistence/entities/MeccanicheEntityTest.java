package com.axiante.mui.dbpromo.persistence.entities;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MeccanicheEntityTest {
	final MeccanicheEntity entity = new MeccanicheEntity();

	@Before
	public void init() {
		entity.setMuiCfgAbilitaMeccCanales(new HashSet<>());
		entity.setPromozioneMeccanicheEntities(new HashSet<>());
		entity.setPromozionePianificazioneEntities(new HashSet<>());
	}

	@Test
	public void whenAddCfgAbilitaMeccanicheThenEntityInList() {
		CfgAbilitaMeccCanaleEntity e = new CfgAbilitaMeccCanaleEntity();
		int size = entity.getMuiCfgAbilitaMeccCanales().size();
		entity.addMuiCfgAbilitaMeccCanale(e);
		assertThat(entity.getMuiCfgAbilitaMeccCanales().size(), CoreMatchers.equalTo(size + 1));
		assertThat(e.getMeccanicheEntity(), CoreMatchers.equalTo(entity));
	}

	@Test
	public void whenRemoveCfgAbilitaMeccanicheThenEntityNotInList() {
		CfgAbilitaMeccCanaleEntity e = new CfgAbilitaMeccCanaleEntity();
		int size = entity.getMuiCfgAbilitaMeccCanales().size();
		entity.addMuiCfgAbilitaMeccCanale(e);
		assertThat(entity.getMuiCfgAbilitaMeccCanales().size(), CoreMatchers.equalTo(size + 1));
		assertThat(e.getMeccanicheEntity(), CoreMatchers.equalTo(entity));
		entity.removeMuiCfgAbilitaMeccCanale(e);
		assertThat(entity.getMuiCfgAbilitaMeccCanales().size(), CoreMatchers.equalTo(size));
		assertNull(e.getMeccanicheEntity());
	}

	@Test
	public void whenAddPromozioneMeccanicheThenEntityInList() {
		PromozioneMeccanicheEntity e = new PromozioneMeccanicheEntity();
		int size = entity.getPromozioneMeccanicheEntities().size();
		entity.addMuiPromozioneMeccanich(e);
		assertThat(entity.getPromozioneMeccanicheEntities().size(), CoreMatchers.equalTo(size + 1));
		assertThat(e.getMeccanicheEntity(), CoreMatchers.equalTo(entity));
	}

	@Test
	public void whenRemovePromozioneMeccanicheThenEntityNotInList() {
		PromozioneMeccanicheEntity e = new PromozioneMeccanicheEntity();
		int size = entity.getPromozioneMeccanicheEntities().size();
		entity.addMuiPromozioneMeccanich(e);
		assertThat(entity.getPromozioneMeccanicheEntities().size(), CoreMatchers.equalTo(size + 1));
		assertThat(e.getMeccanicheEntity(), CoreMatchers.equalTo(entity));

		entity.removeMuiPromozioneMeccanich(e);
		assertThat(entity.getPromozioneMeccanicheEntities().size(), CoreMatchers.equalTo(size));
		assertNull(e.getMeccanicheEntity());
	}

	@Test
	public void whenAddPromozionePianificazioneEntityThenEntityInList() {
		PromozionePianificazioneEntity e = new PromozionePianificazioneEntity();
		int size = entity.getPromozionePianificazioneEntities().size();
		entity.addMuiPromozionePianificazione(e);
		assertThat(entity.getPromozionePianificazioneEntities().size(), CoreMatchers.equalTo(size + 1));
		assertThat(e.getMeccanicaEntity(), CoreMatchers.equalTo(entity));
	}

	@Test
	public void whenRemovePromozionePianificazioneEntityThenEntityNotInList() {
		PromozionePianificazioneEntity e = new PromozionePianificazioneEntity();
		int size = entity.getPromozionePianificazioneEntities().size();
		entity.addMuiPromozionePianificazione(e);
		assertThat(entity.getPromozionePianificazioneEntities().size(), CoreMatchers.equalTo(size + 1));
		assertThat(e.getMeccanicaEntity(), CoreMatchers.equalTo(entity));
		entity.removeMuiPromozionePianificazione(e);
		assertThat(entity.getPromozioneMeccanicheEntities().size(), CoreMatchers.equalTo(size));
		assertNull(e.getMeccanicaEntity());
	}

	@Test
	public void testEquals() {
		MeccanicheEntity _this = new MeccanicheEntity();
		assertThat(_this, CoreMatchers.not(CoreMatchers.equalTo("")));
		assertThat(_this, CoreMatchers.not(CoreMatchers.equalTo(null)));
		MeccanicheEntity _that = _this;
		assertThat(_this, CoreMatchers.equalTo(_that));

		_that = new MeccanicheEntity();
		_this.setCodiceMeccanica(null);
		_that.setCodiceMeccanica(null);
		assertThat(_this, CoreMatchers.equalTo(_that));
		_that.setCodiceMeccanica("codice");
		assertThat(_this, CoreMatchers.not(CoreMatchers.equalTo(_that)));

		_this.setCodiceMeccanica("a code");
		_that.setCodiceMeccanica("a code");
		assertThat(_this, CoreMatchers.equalTo(_that));

		_this.setId(1L);
		_that.setId(1L);
		assertThat(_this, CoreMatchers.equalTo(_that));

		_that.setCodiceMeccanica("another code");
		assertThat(_this, CoreMatchers.not(CoreMatchers.equalTo(_that)));

		_that.setCodiceMeccanica(_this.getCodiceMeccanica());
		_that.setId(2L);

		assertThat(_this, CoreMatchers.not(CoreMatchers.equalTo(_that)));

		_that.setCodiceMeccanica(null);
		assertThat(_this, CoreMatchers.not(CoreMatchers.equalTo(_that)));
		_that.setCodiceMeccanica("a code");
		_this.setCodiceMeccanica(null);
		assertThat(_this, CoreMatchers.not(CoreMatchers.equalTo(_that)));
	}

	@Test
	public void removeMuiCfgConfHeaderEntity() {
		CfgConfHeaderEntity cfgConfHeader = mock(CfgConfHeaderEntity.class);
		doNothing().when(cfgConfHeader).setMeccanicaEntity(null);
		entity.setMuiCfgConfHeaders(new HashSet<>());
		assertEquals(cfgConfHeader, entity.removeMuiCfgConfHeaderEntity(cfgConfHeader));
		verify(cfgConfHeader, times(1)).setMeccanicaEntity(null);
	}

	@Test
	public void testHashCode() {
		assertEquals(42, entity.hashCode());
	}

	@Test
	public void getKey() {
		entity.setId(1L);
		assertEquals("1", entity.getKey());
	}

	@Test
	public void getLabel() {
		entity.setCodiceMeccanica("M-001");
		entity.setDescrizione("FOO");
		assertEquals("M-001 - FOO", entity.getLabel());
	}
}
