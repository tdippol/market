package com.axiante.mui.webapp.views.content.dbpromo.dtos;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RataDtoTest {

    @Test
    public void constructor_shouldWrapEntity() {
        ContributiPromoEntity entity = Mockito.mock(ContributiPromoEntity.class);
        RataDto underTest = new RataDto(entity);
        assertThat(underTest.getEntity(), equalTo(entity));
    }

    @Test
    public void getId_shouldDelegateToEntity() {
        ContributiPromoEntity entity = Mockito.mock(ContributiPromoEntity.class);
        Mockito.when(entity.getId()).thenReturn(10L);
        RataDto underTest = new RataDto(entity);
        assertThat(underTest.getId(), equalTo(10L));
    }

    @Test
    public void getLabel_shouldBuildExpectedLabel() {
        ContributiPromoEntity entity = Mockito.mock(ContributiPromoEntity.class);
        CompratoreEntity compratore = Mockito.mock(CompratoreEntity.class);
        FornitoreEntity fornitore = Mockito.mock(FornitoreEntity.class);
        Mockito.when(entity.getCompratore()).thenReturn(compratore);
        Mockito.when(entity.getFornitore()).thenReturn(fornitore);
        Mockito.when(entity.getProgressivo()).thenReturn(7);
        Mockito.when(compratore.getFullDescription()).thenReturn("Buyer");
        Mockito.when(fornitore.getFullDescription()).thenReturn("Supplier");
        RataDto underTest = new RataDto(entity);
        assertThat(underTest.getLabel(), equalTo("Buyer - Supplier - Contributo 7"));
    }

    @Test
    public void getters_shouldReturnNullWhenEntityIsNull() {
        RataDto underTest = new RataDto();
        assertNull(underTest.getCodicePrestazione());
        assertNull(underTest.getDataLiquidazione());
        assertNull(underTest.getValore());
        assertNull(underTest.getSaldo());
        assertNull(underTest.getNoteCompratore());
        assertNull(underTest.getNoteFattura());
        assertNull(underTest.getCodiceStato());
    }

    @Test
    public void getters_shouldDelegateToEntityWhenPresent() {
        ContributiPromoEntity entity = Mockito.mock(ContributiPromoEntity.class);
        Date date = new Date();
        Mockito.when(entity.getCodicePrestazione()).thenReturn("CP");
        Mockito.when(entity.getDataLiquidazione()).thenReturn(date);
        Mockito.when(entity.getValoreApplicato()).thenReturn(12.5);
        Mockito.when(entity.getSaldoMovimenti()).thenReturn(7.25);
        Mockito.when(entity.getNotaCompratore()).thenReturn("note buyer");
        Mockito.when(entity.getNotaFattura()).thenReturn("note invoice");
        Mockito.when(entity.getCodiceStato()).thenReturn("ST");
        RataDto underTest = new RataDto(entity);
        assertThat(underTest.getCodicePrestazione(), equalTo("CP"));
        assertThat(underTest.getDataLiquidazione(), equalTo(date));
        assertThat(underTest.getValore(), equalTo(12.5));
        assertThat(underTest.getSaldo(), equalTo(7.25));
        assertThat(underTest.getNoteCompratore(), equalTo("note buyer"));
        assertThat(underTest.getNoteFattura(), equalTo("note invoice"));
        assertThat(underTest.getCodiceStato(), equalTo("ST"));
    }

    @Test
    public void setEntity_shouldReplaceWrappedEntity() {
        ContributiPromoEntity first = Mockito.mock(ContributiPromoEntity.class);
        ContributiPromoEntity second = Mockito.mock(ContributiPromoEntity.class);
        RataDto underTest = new RataDto(first);
        underTest.setEntity(second);
        assertThat(underTest.getEntity(), equalTo(second));
    }
}