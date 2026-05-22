package com.axiante.mui.webapp.webservice.util.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.webapp.support.builders.CfgPianificazioneEntityBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CfgPianificazioneEntityHelperTest {

    @Spy
    private CfgPianificazioneEntityHelper helper;

    // Useful entities
    private CfgPianificazioneEntity pianificazioneEntity;

    @Before
    public void setUp() {
        pianificazioneEntity = new CfgPianificazioneEntityBuilder(1L).withHide("0").withMultiselect((short) 0)
                .withMandatory("1").withWarn("0").withLength(null).withOrdinamento(42).withSicurezza("W")
                .withDescrizione("FOO").withDefValue("DefValue").withLista("[1..10]").withTipoLista("ws:///foo/bar")
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void updateEntity_givenNullHeaderEntity_shouldThrowException() {
        helper.invokeSetterForField(null, "foo", "bar");
    }

    @Test
    public void updateEntity_givenValidParams_shouldReturnEmptyList_andUpdateEntity() {
        assertTrue(helper.invokeSetterForField(pianificazioneEntity, "descrizione", "BAR"));
        assertEquals("BAR", pianificazioneEntity.getDescrizione());
        assertTrue(helper.invokeSetterForField(pianificazioneEntity, "mandatory", "false"));
        assertEquals("0", pianificazioneEntity.getMandatory());
        assertTrue(helper.invokeSetterForField(pianificazioneEntity, "def_value", "BAZ"));
        assertEquals("BAZ", pianificazioneEntity.getDefValue());
        assertTrue(helper.invokeSetterForField(pianificazioneEntity, "lista", "[42;69]"));
        assertEquals("[42;69]", pianificazioneEntity.getLista());
        assertTrue(helper.invokeSetterForField(pianificazioneEntity, "tipo_lista", "ws:///foo/bar/baz"));
        assertEquals("ws:///foo/bar/baz", pianificazioneEntity.getTipoLista());
    }

    @Test
    public void updateEntity_givenHideAsOtherThanBoolean_shouldReturnFalse() {
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "hide", "FOO"));
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "hide", "42"));
    }

    @Test
    public void updateEntity_givenMultiselectAsOtherThanBoolean_shouldReturnFalse() {
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "flag_multiselect", "FOO"));
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "flag_multiselect", "42"));
    }

    @Test
    public void updateEntity_givenMandatoryAsOtherThanBoolean_shouldReturnFalse() {
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "mandatory", "FOO"));
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "mandatory", "42"));
    }

    @Test
    public void updateEntity_givenWarnAsOtherThanBoolean_shouldReturnFalse() {
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "warn", "FOO"));
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "warn", "42"));
    }

    @Test
    public void updateEntity_givenLengthAsString_shouldReturnFalse() {
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "length", "FOO"));
    }

    @Test
    public void updateEntity_givenOrdinamentoAsString_shouldReturnFalse() {
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "ordinamento", "FOO"));
    }

    @Test
    public void updateEntity_givenSicurezzaAsOtherThanRW_shouldReturnFalse() {
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "sicurezza", "FOO"));
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "sicurezza", "42"));
    }

    @Test
    public void updateEntity_givenWrongLista_shouldReturnFalse() {
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "lista", "wronglist"));
    }

    @Test
    public void updateEntity_givenValidLista_shouldReturnTrue() {
        assertTrue(helper.invokeSetterForField(pianificazioneEntity, "lista", "[42..69]"));
    }

    @Test
    public void updateEntity_givenWrongTipoLista_shouldReturnFalse() {
        assertFalse(helper.invokeSetterForField(pianificazioneEntity, "tipo_lista", "ws://foobar"));
    }

    @Test
    public void isEntityLookup_givenAssociatedFieldAsEntityLookup_shouldReturnTrue() {
        final CfgPianificazioneCampiEntity entity = new CfgPianificazioneCampiEntity();
        entity.setCampo("ENTITY_LOOKUP");
        entity.setEntityLookup("LookupEntity");
        entity.setEntityAttribute("LookupAttribute");
        pianificazioneEntity.setMuiCfgPianificazioneCampi(entity);
        assertTrue(helper.isEntityLookup(pianificazioneEntity));
    }

    @Test
    public void isEntityLookup_givenAssociatedFieldAsNotEntityLookup_shouldReturnFalse() {
        assertFalse(helper.isEntityLookup(pianificazioneEntity));
        final CfgPianificazioneCampiEntity entity = new CfgPianificazioneCampiEntity();
        pianificazioneEntity.setMuiCfgPianificazioneCampi(entity);
        assertFalse(helper.isEntityLookup(pianificazioneEntity));
        pianificazioneEntity.getMuiCfgPianificazioneCampi().setEntityLookup("LookupEntity");
        assertFalse(helper.isEntityLookup(pianificazioneEntity));
        pianificazioneEntity.getMuiCfgPianificazioneCampi().setEntityLookup(null);
        pianificazioneEntity.getMuiCfgPianificazioneCampi().setEntityAttribute("LookupAttribute");
        assertFalse(helper.isEntityLookup(pianificazioneEntity));
    }
}
