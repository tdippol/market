package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.common.interfaces.ComboBoxCapable;
import com.axiante.mui.common.promo.grid.ComboBoxValues;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComboBoxFactoryTest {

	@Spy
	@InjectMocks
	private ComboBoxFactory comboBoxFactory;

	@Mock
	private ComboBoxCapable entity1;

	@Mock
	private ComboBoxCapable entity2;

	@Before
	public void setUp() {
		when(entity1.getKey()).thenReturn("key");
		when(entity1.getLabel()).thenReturn("label");
		when(entity2.getKey()).thenReturn("foo");
		when(entity2.getLabel()).thenReturn("bar");
	}

	@Test(expected = NullPointerException.class)
	public void from_givenNullEntity_shouldThrowException() {
		comboBoxFactory.from((ComboBoxCapable) null);
	}

	@Test
	public void createComboBoxValueFromComboBoxCapable() {
		final ComboBoxValues cb = comboBoxFactory.from(entity1);
		assertNotNull(cb);
		assertEquals("key", cb.getKey());
		assertEquals("label", cb.getLabel());
	}

	@Test(expected = NullPointerException.class)
	public void fromList_givenNullList_shouldThrowException() {
		comboBoxFactory.from((List<ComboBoxCapable>) null);
	}

	@Test
	public void createListComboBoxValueFromComboBox_shouldSortAndAddBlankItem() {
		final List<ComboBoxCapable> entities = Arrays.asList(entity1, entity2);
		final List<ComboBoxValues> values = comboBoxFactory.from(entities);
		assertEquals(3, values.size());
		ComboBoxValues cb = values.get(0);
		assertEquals("foo", cb.getKey());
		assertEquals("bar", cb.getLabel());
		cb = values.get(1);
		assertEquals("key", cb.getKey());
		assertEquals("label", cb.getLabel());
		cb = values.get(2);
		assertTrue(cb.getKey().isEmpty());
		assertTrue(cb.getLabel().isEmpty());
		verify(comboBoxFactory, times(1)).from(entities, true);
		verify(comboBoxFactory, times(1)).from(entities, true, true);
	}

	@Test(expected = NullPointerException.class)
	public void fromListAndErasure_givenNullList_shouldThrowException() {
		comboBoxFactory.from(null,true);
	}

	@Test
	public void createListComboBoxValueFromComboBoxCapableWithErasure_shouldSortAndAddBlankItem() {
		final List<ComboBoxCapable> entities = Arrays.asList(entity1, entity2);
		final List<ComboBoxValues> values = comboBoxFactory.from(entities, true);
		assertEquals(3, values.size());
		ComboBoxValues cb = values.get(0);
		assertEquals("foo", cb.getKey());
		assertEquals("bar", cb.getLabel());
		cb = values.get(1);
		assertEquals("key", cb.getKey());
		assertEquals("label", cb.getLabel());
		cb = values.get(2);
		assertTrue(cb.getKey().isEmpty());
		assertTrue(cb.getLabel().isEmpty());
		verify(comboBoxFactory, times(1)).from(entities, true, true);
	}

	@Test
	public void createListComboBoxValueFromComboBoxCapableWithErasureFalse_shouldSortAndNotAddBlankItem() {
		final List<ComboBoxCapable> entities = Arrays.asList(entity1, entity2);
		final List<ComboBoxValues> values = comboBoxFactory.from(entities, false);
		assertEquals(2, values.size());
		ComboBoxValues cb = values.get(0);
		assertEquals("foo", cb.getKey());
		assertEquals("bar", cb.getLabel());
		cb = values.get(1);
		assertEquals("key", cb.getKey());
		assertEquals("label", cb.getLabel());
		verify(comboBoxFactory, times(1)).from(entities, false, true);
	}

	@Test
	public void createListComboBoxValueFromComboBoxCapableWithErasureFalseAndNullSorted_shouldNotSortAndNotAddBlankItem() {
		final List<ComboBoxCapable> entities = Arrays.asList(entity1, entity2);
		final List<ComboBoxValues> values = comboBoxFactory.from(entities, false, null);
		assertEquals(2, values.size());
		ComboBoxValues cb = values.get(0);
		assertEquals("key", cb.getKey());
		assertEquals("label", cb.getLabel());
		cb = values.get(1);
		assertEquals("foo", cb.getKey());
		assertEquals("bar", cb.getLabel());
		verify(comboBoxFactory, times(1)).from(entities, false, null);
	}

	@Test(expected = NullPointerException.class)
	public void fromListWithErasureAndSort_givenNullList_shouldThrowException() {
		comboBoxFactory.from(null, true, true);
	}

	@Test
	public void fromListWithErasureAndSort_givenErasureFalseAndSortedFalse_shouldNotSortAndNotAddBlankItem() {
		final List<ComboBoxCapable> entities = Arrays.asList(entity1, entity2);
		final List<ComboBoxValues> values = comboBoxFactory.from(entities, false, false);
		assertEquals(2, values.size());
		ComboBoxValues cb = values.get(0);
		assertEquals("key", cb.getKey());
		assertEquals("label", cb.getLabel());
		cb = values.get(1);
		assertEquals("foo", cb.getKey());
		assertEquals("bar", cb.getLabel());
	}
}
