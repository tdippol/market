package com.axiante.mui.webapp.webservice.util.pianificazione;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.webapp.webservice.util.pianificazione.util.DynamicColumnEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DynamicColumnDefFactoryTest {

    @Mock
    private ArticleColumnDef articleColumnDef;

    @Mock
    private GrmColumnDef grmColumnDef;

    @Mock
    private DepartmentColumnDef departmentColumnDef;

    @Mock
    private PlanningColumnDef planningColumnDef;

    @Mock
    private CopyPasteColumnDef copyPasteColumnDef;

    @Mock
    private ComplementaryColumnDef complementaryColumnDef;

    @Spy
    @InjectMocks
    DynamicColumnDefFactory dynamicColumnDefFactory;

    @Test
    public void givenDynamicColumnDef() {
        DynamicColumnDef dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(null);
        assertNull(dynamicColumnDef);

        dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef("wrong");
        assertNull(dynamicColumnDef);

        dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(DynamicColumnEnum.TOTALE.getField());
        assertNull(dynamicColumnDef);

        dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(DynamicColumnEnum.ARTICOLO.getField());
        assertNotNull(dynamicColumnDef);
        assertThat(dynamicColumnDef, instanceOf(ArticleColumnDef.class));

        dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(DynamicColumnEnum.GRM.getField());
        assertNotNull(dynamicColumnDef);
        assertThat(dynamicColumnDef, instanceOf(GrmColumnDef.class));

        dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(DynamicColumnEnum.REPARTO.getField());
        assertNotNull(dynamicColumnDef);
        assertThat(dynamicColumnDef, instanceOf(DepartmentColumnDef.class));

        dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(DynamicColumnEnum.PIANIFICAZIONE.getField());
        assertNotNull(dynamicColumnDef);
        assertThat(dynamicColumnDef, instanceOf(PlanningColumnDef.class));

        dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(DynamicColumnEnum.COPIA_INCOLLA.getField());
        assertNotNull(dynamicColumnDef);
        assertThat(dynamicColumnDef, instanceOf(CopyPasteColumnDef.class));

        dynamicColumnDef = dynamicColumnDefFactory.getDynamicColumnDef(DynamicColumnEnum.COMPLEMENTARI.getField());
        assertNotNull(dynamicColumnDef);
        assertThat(dynamicColumnDef, instanceOf(ComplementaryColumnDef.class));
    }

    @Test
    public void givenDynamicColumnDefGridName() {
        String dynamicColumnDefGridName = dynamicColumnDefFactory.getDynamicColumnDefGridName(null);
        assertNull(dynamicColumnDefGridName);

        dynamicColumnDefGridName = dynamicColumnDefFactory.getDynamicColumnDefGridName(DynamicColumnEnum.ARTICOLO.getField());
        assertNotNull(dynamicColumnDefGridName);
        assertEquals("pianificazione_dialog_inserisci_selezione", dynamicColumnDefGridName);

        dynamicColumnDefGridName = dynamicColumnDefFactory.getDynamicColumnDefGridName(DynamicColumnEnum.GRM.getField());
        assertNotNull(dynamicColumnDefGridName);
        assertEquals("pianificazione_dialog_inserisci_selezione", dynamicColumnDefGridName);

        dynamicColumnDefGridName = dynamicColumnDefFactory.getDynamicColumnDefGridName(DynamicColumnEnum.REPARTO.getField());
        assertNotNull(dynamicColumnDefGridName);
        assertEquals("pianificazione_dialog_inserisci_selezione", dynamicColumnDefGridName);

        dynamicColumnDefGridName = dynamicColumnDefFactory.getDynamicColumnDefGridName(DynamicColumnEnum.PIANIFICAZIONE.getField());
        assertNotNull(dynamicColumnDefGridName);
        assertEquals("db_pianificazione", dynamicColumnDefGridName);

        dynamicColumnDefGridName = dynamicColumnDefFactory.getDynamicColumnDefGridName(DynamicColumnEnum.COPIA_INCOLLA.getField());
        assertNotNull(dynamicColumnDefGridName);
        assertEquals("db_pianificazione_dialog_copiaIncollaTab", dynamicColumnDefGridName);

        dynamicColumnDefGridName = dynamicColumnDefFactory.getDynamicColumnDefGridName(DynamicColumnEnum.COMPLEMENTARI.getField());
        assertNotNull(dynamicColumnDefGridName);
        assertEquals("db_pianificazione_complementari", dynamicColumnDefGridName);

        dynamicColumnDefGridName = dynamicColumnDefFactory.getDynamicColumnDefGridName(DynamicColumnEnum.TOTALE.getField());
        assertNotNull(dynamicColumnDefGridName);
        assertTrue(dynamicColumnDefGridName.isEmpty());

        dynamicColumnDefGridName = dynamicColumnDefFactory.getDynamicColumnDefGridName("foo");
        assertNotNull(dynamicColumnDefGridName);
        assertTrue(dynamicColumnDefGridName.isEmpty());
    }
}
