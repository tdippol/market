package com.axiante.mui.webapp.views.content.dbpromo.data.cumulabilita;

import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiReportBSId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddCumulabilitaBeanTest {

    private AddCumulabilitaBean underTest;

    private static final String DESCRIZIONE = "Test Descrizione";
    private static final String PREFISSO_BS = "BS01";
    private static final String CODICE_PROMO = "PROMO01";
    private static final Date DATA_INIZIO = new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1));
    private static final Date DATA_FINE = new Date();

    @Before
    public void setUp() {
        underTest = new AddCumulabilitaBean();
    }

    @Test
    public void initialState_shouldHaveConfirmButtonDisabled() {
        assertNull(underTest.getDescrizione());
        assertNull(underTest.getPrefissoBs());
        assertNull(underTest.getCodicePromo());
        assertNull(underTest.getDataInizio());
        assertNull(underTest.getDataFine());
        assertNull(underTest.getSelectedBuono());
        assertTrue("Confirm button should be disabled on initial state", underTest.isConfirmBtnDisabled());
    }

    @Test
    public void reset_shouldClearAllFieldsAndDisableConfirmButton() {
        fillWithValidData();
        assertFalse("Pre-condition failed: button should be enabled", underTest.isConfirmBtnDisabled());
        underTest.reset();
        assertNull(underTest.getDescrizione());
        assertNull(underTest.getPrefissoBs());
        assertNull(underTest.getCodicePromo());
        assertNull(underTest.getDataInizio());
        assertNull(underTest.getDataFine());
        assertNull(underTest.getSelectedBuono());
        assertTrue("Confirm button should be disabled after reset", underTest.isConfirmBtnDisabled());
    }

    @Test
    public void confirmButtonShouldBeEnabledWhenAllFieldsAreValid() {
        fillWithValidData();
        assertTrue("Bean should be valid", underTest.isValidCumulabilita());
        assertFalse("Confirm button should be enabled when bean is valid", underTest.isConfirmBtnDisabled());
    }

    @Test
    public void confirmButtonShouldBeDisabledIfDescriptionIsBlank() {
        fillWithValidData();
        underTest.setDescrizione(null);
        assertTrue("Button should be disabled for null description", underTest.isConfirmBtnDisabled());
        fillWithValidData();
        underTest.setDescrizione("");
        assertTrue("Button should be disabled for empty description", underTest.isConfirmBtnDisabled());
        fillWithValidData();
        underTest.setDescrizione("   ");
        assertTrue("Button should be disabled for blank description", underTest.isConfirmBtnDisabled());
    }

    @Test
    public void confirmButtonShouldBeDisabledIfPrefissoBsIsBlank() {
        fillWithValidData();
        underTest.setPrefissoBs(null);
        assertTrue("Button should be disabled for null prefissoBs", underTest.isConfirmBtnDisabled());
        fillWithValidData();
        underTest.setPrefissoBs("");
        assertTrue("Button should be disabled for empty prefissoBs", underTest.isConfirmBtnDisabled());
        fillWithValidData();
        underTest.setPrefissoBs("  ");
        assertTrue("Button should be disabled for blank prefissoBs", underTest.isConfirmBtnDisabled());
    }

    @Test
    public void confirmButtonShouldBeDisabledIfCodicePromoIsBlank() {
        fillWithValidData();
        underTest.setCodicePromo(null);
        assertTrue("Button should be disabled for null codicePromo", underTest.isConfirmBtnDisabled());
        fillWithValidData();
        underTest.setCodicePromo("");
        assertTrue("Button should be disabled for empty codicePromo", underTest.isConfirmBtnDisabled());
        fillWithValidData();
        underTest.setCodicePromo("  ");
        assertTrue("Button should be disabled for blank codicePromo", underTest.isConfirmBtnDisabled());
    }

    @Test
    public void confirmButtonShouldBeDisabledForInvalidDates() {
        fillWithValidData();
        underTest.setDataInizio(null);
        assertTrue("Button should be disabled for null dataInizio", underTest.isConfirmBtnDisabled());
        fillWithValidData();
        underTest.setDataFine(null);
        assertTrue("Button should be disabled for null dataFine", underTest.isConfirmBtnDisabled());
        fillWithValidData();
        underTest.setDataFine(underTest.getDataInizio()); // End date == start date
        assertTrue("Button should be disabled when start and end dates are the same", underTest.isConfirmBtnDisabled());
        fillWithValidData();
        Date startDate = underTest.getDataInizio();
        underTest.setDataFine(new Date(startDate.getTime() - 1000)); // End date < start date
        assertTrue("Button should be disabled when end date is before start date", underTest.isConfirmBtnDisabled());
    }

    @Test
    public void setData_shouldPopulateFieldsFromEntityAndEnableButton() {
        MuiReportBSEntity entity = mock(MuiReportBSEntity.class);
        MuiReportBSId pk = mock(MuiReportBSId.class);
        when(entity.getId()).thenReturn(pk);
        when(pk.getPrefissoBS()).thenReturn(PREFISSO_BS);
        when(pk.getCodicePromozione()).thenReturn(CODICE_PROMO);
        when(entity.getDataInizio()).thenReturn(DATA_INIZIO);
        when(entity.getDataFine()).thenReturn(DATA_FINE);
        underTest.setDescrizione(DESCRIZIONE); // Description is not on the entity, so set it manually to make the bean valid
        underTest.setData(entity);
        assertThat(underTest.getPrefissoBs(), is(equalTo(PREFISSO_BS)));
        assertThat(underTest.getCodicePromo(), is(equalTo(CODICE_PROMO)));
        assertThat(underTest.getDataInizio(), is(equalTo(DATA_INIZIO)));
        assertThat(underTest.getDataFine(), is(equalTo(DATA_FINE)));
        assertThat(underTest.getSelectedBuono(), is(equalTo(entity)));
        assertTrue(underTest.isValidCumulabilita());
        assertFalse("Confirm button should be enabled after setting valid data", underTest.isConfirmBtnDisabled());
    }

    @Test
    public void toCumulabilitaBean_shouldCreateEntityFromBeanData() {
        underTest.setDescrizione("  test description  ");
        underTest.setPrefissoBs(PREFISSO_BS);
        underTest.setCodicePromo(CODICE_PROMO);
        underTest.setDataInizio(DATA_INIZIO);
        underTest.setDataFine(DATA_FINE);
        CumulabilitaEsclusivitaEntity result = underTest.toCumulabilitaBean();
        assertNotNull(result);
        assertThat("Description should be trimmed and upper-cased", result.getDescrizione(), is(equalTo("TEST DESCRIPTION")));
        assertThat(result.getPrefissoBS(), is(equalTo(PREFISSO_BS)));
        assertThat(result.getCodicePromozione(), is(equalTo(CODICE_PROMO)));
        assertThat(result.getDataInizio(), is(equalTo(DATA_INIZIO)));
        assertThat(result.getDataFine(), is(equalTo(DATA_FINE)));
    }

    private void fillWithValidData() {
        underTest.setDescrizione(DESCRIZIONE);
        underTest.setPrefissoBs(PREFISSO_BS);
        underTest.setCodicePromo(CODICE_PROMO);
        underTest.setDataInizio(DATA_INIZIO);
        underTest.setDataFine(DATA_FINE);
    }
}