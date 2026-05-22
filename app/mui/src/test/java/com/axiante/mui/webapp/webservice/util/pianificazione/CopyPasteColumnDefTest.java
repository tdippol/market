package com.axiante.mui.webapp.webservice.util.pianificazione;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.promo.params.PlanningArticleMultiFilterParam;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.io.IOException;
import org.junit.Test;

public class CopyPasteColumnDefTest extends ColumnDefTest {
    private CopyPasteColumnDef columnDef = new CopyPasteColumnDef();

    @Test
    public void generateColumnDefByPromoConfiguration_givenPromozioneTestata_shouldReturnJsonString() throws IOException {
        // Good path
        final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
        final String hiddenCols = readJsonAsString("hiddenColsForCopyPaste.json");
        String json = columnDef.generateColumnDefByPromoConfiguration(entity, hiddenCols, "grid1", null);
        assertNotNull(json);
        DocumentContext cxt = JsonPath.parse(json);
        assertFalse(cxt.read("$.columnDef[1].hide", Boolean.class));
        assertTrue(cxt.read("$.columnDef[2].hide", Boolean.class));
        // Unable to apply filter
        json = columnDef.generateColumnDefByPromoConfiguration(entity, "wrong", "grid2", null);
        assertNotNull(json);
        cxt = JsonPath.parse(json);
        assertFalse(cxt.read("$.columnDef[1].hide", Boolean.class));
        assertFalse(cxt.read("$.columnDef[2].hide", Boolean.class));
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnRowData() {
        final String json = columnDef.generateRowDataByPromoConfiguration("foo", "bar");
        assertNotNull(json);
        assertEquals(PianificazioneConstants.COPY_PASTE_ROWS, JsonPath.read(json, "$.rowData.length()"));
    }

    @Test
    public void generateRowDataByPromoPianificazioneMaster_shouldReturnNull() {
        final PromozionePianificazioneEntity entity = mock(PromozionePianificazioneEntity.class);
        assertNull(columnDef.generateRowDataByPromoPianificazioneMaster(entity));
    }

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnNull() {
        final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
        final PlanningArticleMultiFilterParam param = mock(PlanningArticleMultiFilterParam.class);
        assertNull(columnDef.generateRowDataByPromoConfiguration(entity, 1L, param, true, null));
    }

    @Test
    public void generateRowDataFilteredByPromoConfiguration_shouldReturnNull() {
        assertNull(columnDef.generateRowDataFilteredByPromoConfiguration("foo", "bar"));
    }

    @Test
    public void generateRowDataByPromoElementMechanic_shouldReturnNull() {
        final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
        assertNull(columnDef.generateRowDataByPromoElementMechanic(entity, 1L, "foo", "bar"));
    }
}
