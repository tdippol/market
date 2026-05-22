package com.axiante.mui.webapp.webservice.util;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.TemplateTotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.TotalizzatoriEntity;
import com.axiante.mui.dbpromo.persistence.service.TemplateTotalizzatoriService;
import com.axiante.mui.webapp.webservice.dto.RAFRequest;
import com.axiante.mui.webapp.webservice.dto.RAFResponse;
import com.axiante.mui.webapp.webservice.dto.RafContent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.inject.Instance;
import lombok.NonNull;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TotalizzatoreHelperTest {
    /**
     * tests:
     * 1. able to parse response as Totalizzatore
     * 2. able to check that all the child are there
     * 3. able to get the status attribute
     * 4. able to create body request (new)
     * 5. able to create body request (update)
     */

    @Mock
    Instance<TemplateTotalizzatoriService> templateServiceInstance;
    @Mock
    TemplateTotalizzatoriService service;
    @InjectMocks
    TotalizzatoreHelper helper;
    static List<TemplateTotalizzatoriEntity> templates;

    @Test
    public void whenReadingStatusOneResponseRafHasStatusOne() throws IOException {
        final RAFResponse response = helper.parse(readData("totalizzatore/risposta_stato_1.json"));
        assertNotNull(response);
        assertThat(response.getStato(), CoreMatchers.equalTo(1));
    }

    @Test
    public void whenReadingStatusTwoResponseRafHasStatusTwo() throws IOException {
        final RAFResponse response = helper.parse(readData("totalizzatore/risposta_stato_2.json"));
        assertNotNull(response);
        assertThat(response.getStato(), CoreMatchers.equalTo(2));
    }

    @Test
    public void whenReadingStatusThreeResponseRafHasStatusThree() throws IOException {
        final RAFResponse response = helper.parse(readData("totalizzatore/risposta_stato_3.json"));
        assertNotNull(response);
        assertThat(response.getStato(), CoreMatchers.equalTo(3));
    }

    @Test
    public void whenReadingEmptyResponseRafIsEmpty() throws IOException {
        final RAFResponse response = helper.parse(readData("totalizzatore/risposta_stato_vuota.json"));
        assertNotNull(response);
        assertThat(response.isEmpty(), CoreMatchers.equalTo(Boolean.TRUE));
    }


    @Test
    public void whenGeneratingNewRequestThenReturnsCorrectBody() {
        when(templateServiceInstance.get()).thenReturn(service);
        when(service.findAll()).thenReturn(templates);
        final LocalDate inizio = LocalDate.now();
        final LocalDate fine = inizio.plusDays(10);

        final Date dataInizio = java.sql.Date.valueOf(inizio);
        final Date dataFine = java.sql.Date.valueOf(fine);
        final IniziativaEntity iniziativa = mockIniziativa(dataInizio, dataFine);
        when(iniziativa.getTotalizzatori()).thenReturn(null);

        checkRequest(helper.generate(iniziativa), iniziativa, Boolean.TRUE, null);

    }

    @Test
    public void whenGeneratingUpdateRequestThenReturnsCorrectBody() {
        final LocalDate inizio = LocalDate.now();
        final LocalDate fine = inizio.plusDays(10);

        final Date dataInizio = java.sql.Date.valueOf(inizio);
        final Date dataFine = java.sql.Date.valueOf(fine);
        final IniziativaEntity iniziativa = mockIniziativa(dataInizio, dataFine);
        final Long idParent = ThreadLocalRandom.current().nextLong(1000) + 1;
        final TotalizzatoriEntity parent = mockTotalizzatore(iniziativa.getDescrizione(), null, 1, null, null);
        when(parent.getId()).thenReturn(idParent);
        final Set<TotalizzatoriEntity> totalizzatori = templates.stream()
                .map(t -> mockTotalizzatore(t.getDescrizione(), t.getActionType(), t.getSegno(), t.getExportVs(), idParent))
                .collect(Collectors.toSet());
        totalizzatori.add(parent);

        when(iniziativa.getTotalizzatori()).thenReturn(totalizzatori);
        checkRequest(helper.generate(iniziativa), iniziativa, Boolean.FALSE, idParent);
    }

    @Test(expected = IOException.class)
    public void whenUpdatingAndResponseIsNotParseableThenThrowsException() throws IOException {
        final TotalizzatoreHelper mockedHelper = spy(TotalizzatoreHelper.class);
        Mockito.when(mockedHelper.parse(anyString())).thenThrow(IOException.class);
        mockedHelper.update(Mockito.mock(IniziativaEntity.class), "some valid response");
    }

    @Test
    public void whenUpdatingAddTotalizzatoreIsCalledSevenTimes() throws IOException {
        final RAFResponse raf = helper.parse(readData("totalizzatore/risposta_stato_1.json"));
        final LocalDate inizio = LocalDate.now();
        final LocalDate fine = inizio.plusDays(10);

        final Date dataInizio = java.sql.Date.valueOf(inizio);
        final Date dataFine = java.sql.Date.valueOf(fine);

        IniziativaEntity iniziativa = spyIniziativa(dataInizio, dataFine);
        helper.update(iniziativa, raf);
        // ho raf.getFigli + parent chiamate ad addTotalizzatore
        verify(iniziativa, times(raf.getFigli().length + 1)).addTotalizzatore(any(TotalizzatoriEntity.class));
    }

    /*
     * helper method to mimic body returned from Http Response
     */
    private String readData(@NonNull String filename) {
        final ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    private IniziativaEntity mockIniziativa(@NonNull Date dataInizio, @NonNull Date dataFine) {
        final IniziativaEntity e = mock(IniziativaEntity.class);
        final long id = ThreadLocalRandom.current().nextLong(1000) + 1;
        when(e.getId()).thenReturn(id);
        when(e.getFlagDecimale()).thenReturn(id % 2 == 0); //se id pari allora TRUE
        when(e.getDataInizio()).thenReturn(dataInizio);
        when(e.getDataFine()).thenReturn(dataFine);
        when(e.getDescrizione()).thenReturn("Iniziativa " + id);
        return e;
    }

    private IniziativaEntity spyIniziativa(@NonNull Date dataInizio, @NonNull Date dataFine) {
        final IniziativaEntity e = spy(IniziativaEntity.class);
        final long id = ThreadLocalRandom.current().nextLong(1000) + 1;
        when(e.getId()).thenReturn(id);
        when(e.getFlagDecimale()).thenReturn(id % 2 == 0); //se id pari allora TRUE
        when(e.getDataInizio()).thenReturn(dataInizio);
        when(e.getDataFine()).thenReturn(dataFine);
        when(e.getDescrizione()).thenReturn("Iniziativa " + id );
        return e;
    }

    @BeforeClass
    public static void initialize() {
        templates = new ArrayList<>();
        templates.add(mockTemplate(1L, "ACCUMULO OGGETTI SULLA SPESA", 60, 1, 1));
        templates.add(mockTemplate(2L, "ACCUMULO OGGETTI SUI PF", 61, 1, 1));
        templates.add(mockTemplate(3L, "ACCUMULO OGGETTI SUI PRODOTTI", 62, 1, 1));
        templates.add(mockTemplate(4L, "REDENZIONE OGGETTI", 64, -1, 1));
        templates.add(mockTemplate(5L, "REDENZIONE OGGETTI", 66, -1, 1));
        templates.add(mockTemplate(6L, "ACCREDITI MANUALI OGGETTI", 0, 1, 0));
        templates.add(mockTemplate(7L, "STORNI MANUALI OGGETTI", 0, -1, 0));
    }

    private static TemplateTotalizzatoriEntity mockTemplate(Long id, String descrizione, Integer actionType, Integer segno, Integer exportVs) {
        TemplateTotalizzatoriEntity e = mock(TemplateTotalizzatoriEntity.class);
        when(e.getId()).thenReturn(id);
        when(e.getSegno()).thenReturn(segno);
        when(e.getDescrizione()).thenReturn(descrizione);
        when(e.getActionType()).thenReturn(actionType);
        when(e.getExportVs()).thenReturn(exportVs);
        return e;
    }

    private static TotalizzatoriEntity mockTotalizzatore(String descrizione, Integer actionType, Integer segno, Integer exportVs, Long idParent) {
        TotalizzatoriEntity e = mock(TotalizzatoriEntity.class);
        when(e.getSegno()).thenReturn(segno);
        when(e.getDescrizione()).thenReturn(descrizione);
        when(e.getActionType()).thenReturn(actionType);
        when(e.getExportVs()).thenReturn(exportVs);
        when(e.getIdParent()).thenReturn(idParent);
        return e;
    }


    private void checkRequest(RAFRequest request, IniziativaEntity iniziativa, Boolean newRequest, Long idParent) {
        assertNotNull(request);

        assertNotNull(request.getParent());
        assertThat(request.getParent().getSegno(), CoreMatchers.equalTo(1));
        assertNull(request.getParent().getActionType());
        assertNull(request.getParent().getExportVs());
        assertThat(request.getParent().getDescrizione(), CoreMatchers.equalTo(iniziativa.getDescrizione()));

        assertNotNull(request.getFigli());

        List<TotalizzatoriEntity> list = Arrays.stream(request.getFigli()).map(RafContent::toTotalizzatoriEntity).collect(Collectors.toList());
        if (newRequest) {
            assertNull(request.getParent().getIdParent());

            assertThat(list.size(), CoreMatchers.equalTo(templates.size()));
            assertThat(list, (everyItem(hasProperty("id", Is.is(Matchers.nullValue())))));
            templates.forEach(t -> {
                TotalizzatoriEntity figlio = list.stream().filter(f -> Objects.equals(t.getActionType(), f.getActionType())).filter(f -> Objects.equals(t.getSegno(), f.getSegno())).findFirst().orElse(null);
                assertNotNull(String.format("Totalizzatore per action type %s e segno %s non generato", t.getActionType(), t.getSegno()), figlio);
                assertThat("La descrizione del figlio non combacia.", figlio.getDescrizione(), CoreMatchers.equalTo(t.getDescrizione()));
                assertThat("L export vs del figlio non combacia.", figlio.getExportVs(), CoreMatchers.equalTo(figlio.getExportVs()));
            });
        } else {
            assertThat(request.getParent().getIdTotalizzatore(), CoreMatchers.equalTo(idParent));
            assertThat(list.size(), CoreMatchers.equalTo(iniziativa.getTotalizzatori().size() -1 ));
            iniziativa.getTotalizzatori().forEach(t -> {
                if ( t.getIdParent() != null ) {
                    TotalizzatoriEntity figlio = list.stream().filter(f -> Objects.equals(t.getActionType(), f.getActionType())).filter(f -> Objects.equals(t.getSegno(), f.getSegno())).findFirst().orElse(null);
                    assert figlio != null;
                    assertThat("L'id parent del figlio non combacia.", figlio.getIdParent(), CoreMatchers.equalTo(idParent));
                    assertNotNull(String.format("Totalizzatore per action type %s e segno %s non generato", t.getActionType(), t.getSegno()), figlio);
                    assertThat("La descrizione del figlio non combacia.", figlio.getDescrizione(), CoreMatchers.equalTo(t.getDescrizione()));
                    assertThat("L export vs del figlio non combacia.", figlio.getExportVs(), CoreMatchers.equalTo(figlio.getExportVs()));
                }
            });


        }

    }

}
