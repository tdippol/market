package com.axiante.mui.dbpromo.persistence.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TipoTerminaleCassaEntityTest {
	
	static final TipoTerminaleCassaEntity entity = new TipoTerminaleCassaEntity();
	static final TipoTerminaleCassaEntity entity2 = new TipoTerminaleCassaEntity();
	@Before
	public void init() {
		entity.setId((long) 1);
		entity.setDescrizione("DescrizioneTipo1");
		entity.setTipo("Tipo1");
		entity.setTipoTerminale(1);
		entity2.setId((long) 2);
		entity2.setDescrizione("DescrizioneTipo2");
		entity2.setTipo("Tipo2");
		entity2.setTipoTerminale(2);
	}

	@SuppressWarnings({"SimplifiableAssertion", "EqualsWithItself"})
	@Test
	public void equalsReturnsTrue() {
		assertTrue(entity.equals(entity));
	}

	@SuppressWarnings("SimplifiableAssertion")
	@Test
	public void equalsReturnsFalse() {
		assertFalse(entity.equals(null));
	}

	@SuppressWarnings("SimplifiableAssertion")
	@Test
	public void equalsReturnsFalseWhenNotEqualObjects() {
		assertFalse(entity.equals(entity2));
	}

	@SuppressWarnings("ObviousNullCheck")
	@Test
	public void testHashCode() {
		assertNotNull(entity.hashCode());
	}

	@SuppressWarnings("SimplifiableAssertion")
	@Test
	public void testEquals_whenIdNull_shouldReturnFalse() {
		entity.setId(null);
		assertFalse(entity.equals(entity2));
	}

	@SuppressWarnings("SimplifiableAssertion")
	@Test
	public void testEquals_whenDifferentId_shouldReturnFalse() {
		entity.setId(1L);
		entity2.setId(2L);
		assertFalse(entity.equals(entity2));
	}

	@SuppressWarnings("SimplifiableAssertion")
	@Test
	public void testEquals_whenSameId_shouldReturnTrue() {
		entity.setId(1L);
		entity2.setId(1L);
		assertTrue(entity.equals(entity2));
	}
}
