package com.axiante.mui.dbpromo.persistence.entities;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FornitoreEntityTest {
    @Mock
    private ContributiPromoEntity contributo;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private FornitoreEntity entity;

    @Before
    public void setUp() throws Exception {
        entity = new FornitoreEntity();
    }

    @Test
    public void whenAddAssortimentoFornitoreEntityThenEntityInList() {
        entity.setMuiAssortimentoFornitores(new HashSet<>());
        int size = entity.getMuiAssortimentoFornitores().size();
        AssortimentoFornitoreEntity e = new AssortimentoFornitoreEntity();

        e = entity.addMuiAssortimentoFornitore(e);
        assertThat(e.getFornitoreEntity(), CoreMatchers.equalTo(entity));
        assertThat(entity.getMuiAssortimentoFornitores().size(), CoreMatchers.equalTo(size + 1));
    }

    @Test
    public void whenRemoveAssortimentoFornitoreEntityThenEntityNotInList() {
        entity.setMuiAssortimentoFornitores(new HashSet<>());
        int size = entity.getMuiAssortimentoFornitores().size();
        AssortimentoFornitoreEntity e = new AssortimentoFornitoreEntity();

        e = entity.addMuiAssortimentoFornitore(e);
        assertThat(e.getFornitoreEntity(), CoreMatchers.equalTo(entity));
        assertThat(entity.getMuiAssortimentoFornitores().size(), CoreMatchers.equalTo(size + 1));

        e = entity.removeMuiAssortimentoFornitore(e);
        assertNull(e.getFornitoreEntity());
        assertThat(entity.getMuiAssortimentoFornitores().size(), CoreMatchers.equalTo(size));
    }

    @Test
    public void addContributo_whenNullContributo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addContributo(null);
    }

    @Test
    public void addContributo_whenContributiNull() {
        entity.setContributi(null);
        doNothing().when(contributo).setFornitore(entity);
        entity.addContributo(contributo);
        verify(contributo, times(1)).setFornitore(entity);
    }

    @Test
    public void addContributo() {
        entity.setContributi(new HashSet<>());
        doNothing().when(contributo).setFornitore(entity);
        entity.addContributo(contributo);
        verify(contributo, times(1)).setFornitore(entity);
    }

    @Test
    public void removeCompratore_whenNullContributo_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeCompratore(null);
    }

    @Test
    public void removeCompratore_whenContributiNull() {
        entity.setContributi(null);
        entity.removeCompratore(contributo);
        verify(contributo, never()).setFornitore(null);
    }

    @Test
    public void removeCompratore() {
        Set<ContributiPromoEntity> contributi = Stream.of(contributo).collect(Collectors.toSet());
        entity.setContributi(contributi);
        entity.removeCompratore(contributo);
        verify(contributo, times(1)).setFornitore(null);
    }

    @Test
    public void getFullDescription() {
        entity.setCodiceFornitore("F-01");
        entity.setDescrizione("FOO");
        assertEquals("[F-01] FOO", entity.getFullDescription());
    }
}
