package com.axiante.mui.dbpromo.business.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.axiante.mui.dbpromo.persistence.dao.ItemDAO;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.service.impl.ItemServiceImpl;
import java.util.Collections;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {
    @Mock
    private ItemDAO dao;
    @Spy
    @InjectMocks
    ItemServiceImpl service;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void testPersist() throws Exception {
        ItemEntity e = mock(ItemEntity.class);

        service.persist(e);
        verify(dao).persist(e);
    }

    @Test
    public void testFindByCode() {
        String code = "some";
        service.findByCode(code);
        verify(dao).findByCode(code);
    }

    @Test
    public void testFindCodiceById(){
        Long id = 1L;
        service.findCodiceById(id);
        verify(dao).findCodiceById(id);
    }

    @Test
    public void testFindByIdsAndCompratoreAndFornitoreThrowsExceptionWhenNullCompratoreInput(){
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("codiceCompratore is marked non-null but is null");
        service.findByIdsAndCompratoreAndFornitore(null, null, null);
    }
    @Test
    public void testFindByIdsAndCompratoreAndFornitoreThrowsExceptionWhenNullFornnitoreInput(){
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("codiceFornitore is marked non-null but is null");
        service.findByIdsAndCompratoreAndFornitore(null, "codiceCompratore", null);
    }
    @Test
    public void testFindByIdsAndCompratoreAndFornitoreReturnsEmptyDataWhenIdsIsNullOrEmpty(){
        assertThat(service.findByIdsAndCompratoreAndFornitore(null, "codiceCompratore", "codiceFornitore").isEmpty(), is(true));
        assertThat(service.findByIdsAndCompratoreAndFornitore(Collections.emptyList(), "codiceCompratore", "codiceFornitore").isEmpty(), is(true));
    }
    @Test
    public void testFindByIdsAndCompratoreAndFornitore(){
        service.findByIdsAndCompratoreAndFornitore(Collections.singletonList(1L), "codiceCompratore", "codiceFornitore");
        verify(dao).findByIdsAndCompratoreAndFornitore(Collections.singletonList(1L), "codiceCompratore", "codiceFornitore");
    }

    @Test
    public void testFindByIdsAndCompratoriAndFornitoreReturnsEmptyListWhenAnyInputIsEmptyOrNull(){
        List<String> emptyList = Collections.emptyList();
        String emptyString = "";

        assertThat(service.findByIdsAndCompratoriAndFornitore(null, null, emptyString).isEmpty(), is(true));
        assertThat(service.findByIdsAndCompratoriAndFornitore(Collections.emptyList(), null, emptyString).isEmpty(), is(true));
        assertThat(service.findByIdsAndCompratoriAndFornitore(null, Collections.emptyList(), emptyString).isEmpty(), is(true));
        assertThat(service.findByIdsAndCompratoriAndFornitore(Collections.emptyList(), Collections.emptyList(), emptyString).isEmpty(), is(true));

    }

    @Test
    public void testFindByIdsAndCompratoriAndFornitoreThrowsExceptionWhenNullFornnitoreInput(){
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("codiceFornitore is marked non-null but is null");
        service.findByIdsAndCompratoriAndFornitore(Collections.emptyList(), Collections.emptyList(), null);
    }
    @Test
    public void testFindByIdsAndCompratoriAndFornitore(){
        service.findByIdsAndCompratoriAndFornitore(Collections.singletonList(1L), Collections.singletonList("codiceCompratore"), "codiceFornitore");
        verify(dao).findByIdsAndCompratoriAndFornitore(Collections.singletonList(1L), Collections.singletonList("codiceCompratore"), "codiceFornitore");
    }
}
