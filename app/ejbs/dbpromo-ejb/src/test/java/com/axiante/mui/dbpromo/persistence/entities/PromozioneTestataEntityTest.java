package com.axiante.mui.dbpromo.persistence.entities;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PromozioneTestataEntityTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Spy
    static PromozioneTestataEntity entity = new PromozioneTestataEntity();

    @BeforeClass
    public static void initialize() {
        entity.setPromozioneMeccanicheEntities(new HashSet<>());
        entity.setPromozioneNegozioEntities(new HashSet<>());
        entity.setPromozionePianificazioneEntities(new HashSet<>());
        entity.setPromozioneStatoEntities(new HashSet<>());
        entity.setPromoPubblicazioneTestataEntities(new HashSet<>());
        entity.setPromoStatiConsentitiEntities(new HashSet<>());
        entity.setPromoStatiConsentitiEntities(new HashSet<>());
        entity.setPromoStatiTransizioneEntities(new HashSet<>());
        entity.setPromozioneTipiTerminaleCassa(new HashSet<>());
    }

    @Test
    public void whenAddMuiPromozioneMeccanichThenEntityInList() {
        PromozioneMeccanicheEntity muiPromozioneMeccanich = new PromozioneMeccanicheEntity();
        int size = entity.getPromozioneMeccanicheEntities().size();
        entity.addMuiPromozioneMeccanich(muiPromozioneMeccanich);
        size = entity.getPromozioneMeccanicheEntities().size() - size;
        assertEquals(1, size);
        assertThat(muiPromozioneMeccanich.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveMuiPromozioneMeccanichThenEntityInList() {
        PromozioneMeccanicheEntity muiPromozioneMeccanich = new PromozioneMeccanicheEntity();
        int size = entity.getPromozioneMeccanicheEntities().size();
        entity.addMuiPromozioneMeccanich(muiPromozioneMeccanich);
        size = entity.getPromozioneMeccanicheEntities().size() - size;
        assertEquals(1, size);
        assertThat(muiPromozioneMeccanich.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
        entity.removeMuiPromozioneMeccanich(muiPromozioneMeccanich);
        assertNull(muiPromozioneMeccanich.getPromozioneTestataEntity());
    }

    @Test
    public void whenAddMuiPromozioneNegozioEntitiesThenEntityInList() {
        PromozioneNegozioEntity e = new PromozioneNegozioEntity();
        int size = entity.getPromozioneNegozioEntities().size();
        entity.addPromozioneNegozioEntity(e);
        size = entity.getPromozioneNegozioEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveMuiPromozioneNegozioEntitiesThenEntityInList() {
        PromozioneNegozioEntity e = new PromozioneNegozioEntity();
        int size = entity.getPromozioneNegozioEntities().size();
        entity.addPromozioneNegozioEntity(e);
        size = entity.getPromozioneNegozioEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
        entity.removePromozioneNegozioEntity(e);
        assertNull(e.getPromozioneTestataEntity());
    }

    @Test
    public void whenAddMuipromozionePianificazioneEntitiesThenEntityInList() {
        PromozionePianificazioneEntity e = new PromozionePianificazioneEntity();
        int size = entity.getPromozionePianificazioneEntities().size();
        entity.addPromozionePianificazioneEntity(e);
        size = entity.getPromozionePianificazioneEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemoveMuipromozionePianificazioneEntitiesThenEntityNotInList() {
        PromozionePianificazioneEntity e = new PromozionePianificazioneEntity();
        int size = entity.getPromozionePianificazioneEntities().size();
        entity.addPromozionePianificazioneEntity(e);
        size = entity.getPromozionePianificazioneEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
        entity.removePromozionePianificazioneEntity(e);
        assertNull(e.getPromozioneTestataEntity());
    }

    @Test
    public void whenAddMuiPromozioneStatoEntitiesThenEntityInList() {
        PromozioneStatoEntity e = new PromozioneStatoEntity();
        int size = entity.getPromozioneStatoEntities().size();
        entity.addPromozioneStatoEntity(e);
        size = entity.getPromozioneStatoEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemveMuiPromozioneStatoEntitiesThenEntityNotInList() {
        PromozioneStatoEntity e = new PromozioneStatoEntity();
        int size = entity.getPromozioneStatoEntities().size();
        entity.addPromozioneStatoEntity(e);
        size = entity.getPromozioneStatoEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
        entity.removePromozioneStatoEntity(e);
        assertNull(e.getPromozioneTestataEntity());

    }


    @Test
    public void whenAddMuiPromoStatiConsentitiEntityThenEntityInList() {
        PromoStatiConsentitiEntity e = new PromoStatiConsentitiEntity();
        int size = entity.getPromoStatiConsentitiEntities().size();
        entity.addPromoStatiConsentitiEntity(e);
        size = entity.getPromoStatiConsentitiEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemovePromoStatiConsentitiEntityThenEntityNotInList() {
        PromoStatiConsentitiEntity e = new PromoStatiConsentitiEntity();
        int size = entity.getPromoStatiConsentitiEntities().size();
        entity.addPromoStatiConsentitiEntity(e);
        size = entity.getPromoStatiConsentitiEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
        entity.removePromoStatiConsentitiEntity(e);
        assertNull(e.getPromozioneTestataEntity());
    }

    @Test
    public void whenAddMuiPromoStatiTransizioneEntityThenEntityInList() {
        PromoStatiTransizioneEntity e = new PromoStatiTransizioneEntity();
        int size = entity.getPromoStatiTransizioneEntities().size();
        entity.addPromoStatiTransizioneEntity(e);
        size = entity.getPromoStatiTransizioneEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemovePromoStatiTransizioneEntityThenEntityNotInList() {
        PromoStatiTransizioneEntity e = new PromoStatiTransizioneEntity();
        int size = entity.getPromoStatiTransizioneEntities().size();
        entity.addPromoStatiTransizioneEntity(e);
        size = entity.getPromoStatiTransizioneEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
        entity.removePromoStatiTransizioneEntity(e);
        assertNull(e.getPromozioneTestataEntity());
    }

    @Test
    public void whenAddMuiPromoPubblicazioneTestataEntityThenEntityInList() {
        PromoPubblicazioneTestataEntity e = new PromoPubblicazioneTestataEntity();
        int size = entity.getPromoPubblicazioneTestataEntities().size();
        entity.addPromoPubblicazioneTestataEntity(e);
        size = entity.getPromoPubblicazioneTestataEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemovePromoPubblicazioneTestataEntityThenEntityNotInList() {
        PromoPubblicazioneTestataEntity e = new PromoPubblicazioneTestataEntity();
        int size = entity.getPromoPubblicazioneTestataEntities().size();
        entity.addPromoPubblicazioneTestataEntity(e);
        size = entity.getPromoPubblicazioneTestataEntities().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
        entity.removePromoPubblicazioneTestataEntity(e);
        assertNull(e.getPromozioneTestataEntity());
    }
    
    @Test
    public void whenAddPromozioneTipoTerminaleThenEntityInList() {
    	PromozioneTipoTerminaleEntity e = new PromozioneTipoTerminaleEntity();
        entity.setPromozioneTipiTerminaleCassa(new HashSet<>());
        int size = entity.getPromozioneTipiTerminaleCassa().size();
        entity.addPromozioneTipoTerminale(e);
        size = entity.getPromozioneTipiTerminaleCassa().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
    }

    @Test
    public void whenRemovePromozioneTipoTerminaleThenEntityNotInList() {
    	PromozioneTipoTerminaleEntity e = new PromozioneTipoTerminaleEntity();
        int size = entity.getPromozioneTipiTerminaleCassa().size();
        entity.addPromozioneTipoTerminale(e);
        size = entity.getPromozioneTipiTerminaleCassa().size() - size;
        assertEquals(1, size);
        assertThat(e.getPromozioneTestataEntity(), CoreMatchers.equalTo(entity));
        entity.removePromozioneTipoTerminale(e);
        assertNull(e.getPromozioneTestataEntity());
    }

    @Test
    public void testGetCanalePromozioneEntity() {
        CanalePromozioneEntity e = new CanalePromozioneEntity();
        entity.setMuiCanalePromozione(e);
        assertEquals(e, entity.getCanalePromozioneEntity());
    }

    @Test
    public void testGetNewNoteMarketingReturnsNull() {
        assertNull(entity.getNewNoteMarketing());
    }

    @Test
    public void testGetNewDataFineReturnsNull() {
        assertNull(entity.getNewDataFine());
    }

    @Test
    public void testGetNewDataInizioReturnsNull() {
        assertNull(entity.getNewDataInizio());
    }

    @Test
    public void testGetNewDescrizioneReturnsNull() {
        assertNull(entity.getNewDescrizione());
    }

    @Test
    public void addMuiPromozioneMeccanich_whenNullPromoMeccanica_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addMuiPromozioneMeccanich(null);
    }

    @Test
    public void removeMuiPromozioneMeccanich_whenNullPromoMeccanica_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeMuiPromozioneMeccanich(null);
    }

    @Test
    public void addPromozioneTipoTerminale_whenTipiTerminaleNull() {
        PromozioneTipoTerminaleEntity tipiTerminale = mock(PromozioneTipoTerminaleEntity.class);
        entity.setPromozioneTipiTerminaleCassa(null);
        assertEquals(tipiTerminale, entity.addPromozioneTipoTerminale(tipiTerminale));
        verify(tipiTerminale, times(1)).setPromozioneTestataEntity(entity);
    }

    @Test
    public void removePromozioneTipoTerminale_whenTipiTerminaleNull() {
        PromozioneTipoTerminaleEntity tipiTerminale = mock(PromozioneTipoTerminaleEntity.class);
        entity.setPromozioneTipiTerminaleCassa(null);
        assertEquals(tipiTerminale, entity.removePromozioneTipoTerminale(tipiTerminale));
        verify(tipiTerminale, times(1)).setPromozioneTestataEntity(null);
    }

    @Test
    public void addReparto_whenNullReparto_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addReparto(null);
    }

    @Test
    public void addReparto_whenRepartiNull() {
        RepartoEntity reparto = mock(RepartoEntity.class);
        entity.setReparti(null);
        entity.addReparto(reparto);
        verify(reparto, times(1)).getPromozioni();
    }

    @Test
    public void addReparto() {
        RepartoEntity reparto = mock(RepartoEntity.class);
        entity.setReparti(new HashSet<>());
        entity.addReparto(reparto);
        verify(reparto, times(1)).getPromozioni();
    }

    @Test
    public void removeReparto_whenNullReparto_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeReparto(null);
    }

    @Test
    public void removeReparto_whenRepartiNull() {
        RepartoEntity reparto = mock(RepartoEntity.class);
        entity.setReparti(null);
        assertEquals(reparto, entity.removeReparto(reparto));
        verify(reparto, times(1)).getPromozioni();
    }

    @Test
    public void removeReparto() {
        RepartoEntity reparto = mock(RepartoEntity.class);
        entity.setReparti(new HashSet<>());
        assertEquals(reparto, entity.removeReparto(reparto));
        verify(reparto, times(1)).getPromozioni();
    }

    @Test
    public void addCheckTestata_whenNullCheck_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addCheckTestata(null);
    }

    @Test
    public void addCheckTestata_whenChecksNull() {
        CheckTestataEntity check = mock(CheckTestataEntity.class);
        entity.setChecks(null);
        entity.addCheckTestata(check);
        verify(check, times(1)).setPromozioneTestataEntity(entity);
    }

    @Test
    public void addCheckTestata() {
        CheckTestataEntity check = mock(CheckTestataEntity.class);
        entity.setChecks(new HashSet<>());
        entity.addCheckTestata(check);
        verify(check, times(1)).setPromozioneTestataEntity(entity);
    }

    @Test
    public void removeCheckTestata_whenNullCheck_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeCheckTestata(null);
    }

    @Test
    public void removeCheckTestata_whenChecksNull() {
        CheckTestataEntity check = mock(CheckTestataEntity.class);
        entity.setChecks(null);
        assertEquals(check, entity.removeCheckTestata(check));
        verify(check, never()).setPromozioneTestataEntity(null);
    }

    @Test
    public void removeCheckTestata_whenChecksNoExists() {
        CheckTestataEntity check = mock(CheckTestataEntity.class);
        entity.setChecks(new HashSet<>());
        assertEquals(check, entity.removeCheckTestata(check));
        verify(check, never()).setPromozioneTestataEntity(null);
    }

    @Test
    public void removeCheckTestata() {
        CheckTestataEntity check = mock(CheckTestataEntity.class);
        entity.setChecks(Stream.of(check).collect(Collectors.toSet()));
        assertEquals(check, entity.removeCheckTestata(check));
        verify(check, times(1)).setPromozioneTestataEntity(null);
    }

    @Test
    public void addCheckCompratori_whenNullCheck_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addCheckCompratori(null);
    }

    @Test
    public void addCheckCompratori_whenChecksNull() {
        CheckCompratoriEntity check = mock(CheckCompratoriEntity.class);
        entity.setCheckCompratori(null);
        entity.addCheckCompratori(check);
        verify(check, times(1)).setPromozioneTestataEntity(entity);
    }

    @Test
    public void removeCheckCompratori_whenNullCheck_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeCheckCompratori(null);
    }

    @Test
    public void removeCheckCompratori_whenChecksNull() {
        CheckCompratoriEntity check = mock(CheckCompratoriEntity.class);
        entity.setCheckCompratori(null);
        assertEquals(check, entity.removeCheckCompratori(check));
        verify(check, never()).setPromozioneTestataEntity(null);
    }

    @Test
    public void removeCheckCompratori_whenChecksNoExists() {
        CheckCompratoriEntity check = mock(CheckCompratoriEntity.class);
        entity.setCheckCompratori(new HashSet<>());
        assertEquals(check, entity.removeCheckCompratori(check));
        verify(check, never()).setPromozioneTestataEntity(null);
    }

    @Test
    public void removeCheckCompratori() {
        CheckCompratoriEntity check = mock(CheckCompratoriEntity.class);
        entity.setCheckCompratori(Stream.of(check).collect(Collectors.toSet()));
        assertEquals(check, entity.removeCheckCompratori(check));
        verify(check, times(1)).setPromozioneTestataEntity(null);
    }

    @Test
    public void addCheckPianificazione_whenNullCheck_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addCheckPianificazione(null);
    }

    @Test
    public void removeCheckPianificazione_whenNullCheck_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeCheckPianificazione(null);
    }

    @Test
    public void removeCheckPianificazione_whenChecksNull() {
        CheckPianificazioneEntity check = mock(CheckPianificazioneEntity.class);
        entity.setCheckPianificazioni(null);
        assertEquals(check, entity.removeCheckPianificazione(check));
        verify(check, never()).setTestata(null);
    }

    @Test
    public void removeCheckPianificazione_whenChecksNoExists() {
        CheckPianificazioneEntity check = mock(CheckPianificazioneEntity.class);
        entity.setCheckPianificazioni(new HashSet<>());
        assertEquals(check, entity.removeCheckPianificazione(check));
        verify(check, never()).setTestata(null);
    }

    @Test
    public void removeCheckPianificazione() {
        CheckPianificazioneEntity check = mock(CheckPianificazioneEntity.class);
        entity.setCheckPianificazioni(Stream.of(check).collect(Collectors.toSet()));
        assertEquals(check, entity.removeCheckPianificazione(check));
        verify(check, times(1)).setTestata(null);
    }

    @Test
    public void addOwner_whenNullOwner_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addOwner(null);
    }

    @Test
    public void addOwner_whenOwnersNull() {
        CompratoreEntity owner = mock(CompratoreEntity.class);
        entity.setOwners(new HashSet<>());
        assertEquals(owner, entity.addOwner(owner));
        verify(owner, times(1)).getPromozioni();
    }

    @Test
    public void addOwner() {
        CompratoreEntity owner = mock(CompratoreEntity.class);
        Set<CompratoreEntity> owners = Stream.of(owner).collect(Collectors.toSet());
        entity.setOwners(owners);
        assertEquals(owner, entity.addOwner(owner));
        verify(owner, never()).getPromozioni();
    }

    @Test
    public void removeOwner_whenNullOwner_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removeOwner(null);
    }

    @Test
    public void removeOwner_whenHasOwner_shouldReturnTrue() {
        CompratoreEntity owner = mock(CompratoreEntity.class);
        Set<CompratoreEntity> owners = Stream.of(owner).collect(Collectors.toSet());
        entity.setOwners(owners);
        assertTrue(entity.removeOwner(owner));
        verify(owner, times(1)).getPromozioni();
    }

    @Test
    public void removeOwner_whenHasNoOwner_shouldReturnFalse() {
        CompratoreEntity owner = mock(CompratoreEntity.class);
        assertFalse(entity.removeOwner(owner));
        verify(owner, never()).getPromozioni();
    }

    @Test
    public void addReportSovrapposizione_whenNullReport_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addReportSovrapposizione(null);
    }

    @Test
    public void addReportSovrapposizione() {
        ReportSovrapposizioniEntity report = mock(ReportSovrapposizioniEntity.class);
        entity.setReportSovrapposizioni(new HashSet<>());
        doNothing().when(report).setTestata(entity);
        assertEquals(report, entity.addReportSovrapposizione(report));
        verify(report, times(1)).setTestata(entity);
    }

    @Test
    public void removeReportSovrapposizioni() {
        ReportSovrapposizioniEntity report = mock(ReportSovrapposizioniEntity.class);
        entity.setReportSovrapposizioni(new HashSet<>());
        doNothing().when(report).setTestata(null);
        assertEquals(report, entity.removeReportSovrapposizioni(report));
        verify(report, times(1)).setTestata(null);
    }

    @Test
    public void addPlano_whenNullPlano_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.addPlano(null);
    }

    @Test
    public void addPlano() {
        MuiPlanoDbPromoEntity plano = mock(MuiPlanoDbPromoEntity.class);
        entity.setPlanogrammi(new HashSet<>());
        entity.addPlano(plano);
        verify(entity, times(1)).addPlano(plano);
    }

    @Test
    public void removePlano_whenNullPlano_shouldThrowException() {
        ex.expect(NullPointerException.class);
        entity.removePlano(null);
    }

    @Test
    public void removePlano() {
        MuiPlanoDbPromoEntity plano = mock(MuiPlanoDbPromoEntity.class);
        entity.setPlanogrammi(new HashSet<>());
        entity.removePlano(plano);
        verify(entity, times(1)).removePlano(plano);
    }

    @Test
    public void addContributiPromozione_whenContributiNull() {
        ContributiPromoEntity contributo = mock(ContributiPromoEntity.class);
        entity.setContributiPromozione(null);
        entity.addContributiPromozione(contributo);
        verify(contributo, times(1)).setPromozione(entity);
    }

    @Test
    public void addContributiPromozione() {
        ContributiPromoEntity contributo = mock(ContributiPromoEntity.class);
        entity.setContributiPromozione(new HashSet<>());
        entity.addContributiPromozione(contributo);
        verify(contributo, times(1)).setPromozione(entity);
    }

    @Test
    public void removeContributo_whenContributiNull() {
        ContributiPromoEntity contributo = mock(ContributiPromoEntity.class);
        entity.setContributiPromozione(null);
        entity.removeContributo(contributo);
        verify(contributo, never()).setPromozione(null);
    }

    @Test
    public void removeContributo_whenContributoNotExists() {
        ContributiPromoEntity contributo = mock(ContributiPromoEntity.class);
        entity.setContributiPromozione(new HashSet<>());
        entity.removeContributo(contributo);
        verify(contributo, never()).setPromozione(null);
    }

    @Test
    public void removeContributo() {
        ContributiPromoEntity contributo = mock(ContributiPromoEntity.class);
        Set<ContributiPromoEntity> contributi = new HashSet<>();
        contributi.add(contributo);
        entity.setContributiPromozione(contributi);
        entity.removeContributo(contributo);
        verify(contributo, times(1)).setPromozione(null);
    }

    @Test
    public void addMuiPromozioneFlagEntityy_whenPromozioneFlagsNull() {
        MuiPromozioneFlagEntity flag = mock(MuiPromozioneFlagEntity.class);
        doNothing().when(flag).setPromozioneTestata(entity);
        entity.setPromozioneFlags(null);
        assertEquals(flag, entity.addMuiPromozioneFlagEntity(flag));
        verify(flag, times(1)).setPromozioneTestata(entity);
    }

    @Test
    public void addMuiPromozioneFlagEntity() {
        MuiPromozioneFlagEntity flag = mock(MuiPromozioneFlagEntity.class);
        doNothing().when(flag).setPromozioneTestata(entity);
        entity.setPromozioneFlags(new HashSet<>());
        assertEquals(flag, entity.addMuiPromozioneFlagEntity(flag));
        verify(flag, times(1)).setPromozioneTestata(entity);
    }

    @Test
    public void removePromoCompratoreEntity_whenPromozioneFlagsNull() {
        MuiPromozioneFlagEntity flag = mock(MuiPromozioneFlagEntity.class);
        assertEquals(flag, entity.removePromoCompratoreEntity(flag));
    }

    @Test
    public void removePromoCompratoreEntity_whenIdNull() {
        MuiPromozioneFlagEntity flag = mock(MuiPromozioneFlagEntity.class);
        assertEquals(flag, entity.removePromoCompratoreEntity(flag));
        verify(flag, never()).setPromozioneTestata(null);
    }

    @Test
    public void removePromoCompratoreEntity_whenIdNotNull() {
        MuiPromozioneFlagEntity flag = mock(MuiPromozioneFlagEntity.class);
        when(flag.getPromozioneTestata()).thenReturn(entity);
        when(entity.getId()).thenReturn(1L);
        assertEquals(flag, entity.removePromoCompratoreEntity(flag));
        verify(flag, times(1)).setPromozioneTestata(null);
    }

    @Test
    public void removePromoCompratoreEntity_whenDifferentId() {
        MuiPromozioneFlagEntity flag = mock(MuiPromozioneFlagEntity.class);
        PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(flag.getPromozioneTestata()).thenReturn(testata);
        when(entity.getId()).thenReturn(1L);
        when(testata.getId()).thenReturn(2L);
        assertEquals(flag, entity.removePromoCompratoreEntity(flag));
        verify(flag, never()).setPromozioneTestata(null);
    }

    @Test
    public void addPromozioneAttributo_whenAttributiNull() {
        PromozioneAttributiEntity attributo = mock(PromozioneAttributiEntity.class);
        doNothing().when(attributo).setPromozioneTestataEntity(entity);
        entity.setPromozioneAttributiEntity(null);
        entity.addPromozioneAttributo(attributo);
        verify(attributo, times(1)).setPromozioneTestataEntity(entity);
    }

    @Test
    public void addPromozioneAttributo() {
        PromozioneAttributiEntity attributo = mock(PromozioneAttributiEntity.class);
        doNothing().when(attributo).setPromozioneTestataEntity(entity);
        entity.setPromozioneAttributiEntity(new HashSet<>());
        entity.addPromozioneAttributo(attributo);
        verify(attributo, times(1)).setPromozioneTestataEntity(entity);
    }

    @Test
    public void removePromozioneAttributo_whenAttributoNull() {
        PromozioneAttributiEntity attributo = mock(PromozioneAttributiEntity.class);
        entity.removePromozioneAttributo(null);
        verify(attributo, never()).setPromozioneTestataEntity(null);
    }

    @Test
    public void removePromozioneAttributo_whenAttributiNull() {
        PromozioneAttributiEntity attributo = mock(PromozioneAttributiEntity.class);
        doNothing().when(attributo).setPromozioneTestataEntity(null);
        entity.setPromozioneAttributiEntity(null);
        entity.removePromozioneAttributo(attributo);
        verify(attributo, times(1)).setPromozioneTestataEntity(null);
    }

    @Test
    public void removePromozioneAttributo() {
        PromozioneAttributiEntity attributo = mock(PromozioneAttributiEntity.class);
        doNothing().when(attributo).setPromozioneTestataEntity(null);
        entity.setPromozioneAttributiEntity(new HashSet<>());
        entity.removePromozioneAttributo(attributo);
        verify(attributo, times(1)).setPromozioneTestataEntity(null);
    }

    @Test
    public void addMarchioPrivato_whenMarchiNull() {
        PromozioneMarchioPrivatoEntity marchioPrivato = mock(PromozioneMarchioPrivatoEntity.class);
        doNothing().when(marchioPrivato).setPromozione(entity);
        entity.setMarchiPrivati(null);
        entity.addMarchioPrivato(marchioPrivato);
        verify(marchioPrivato, times(1)).setPromozione(entity);
    }

    @Test
    public void addMarchioPrivato() {
        PromozioneMarchioPrivatoEntity marchioPrivato = mock(PromozioneMarchioPrivatoEntity.class);
        doNothing().when(marchioPrivato).setPromozione(entity);
        entity.setMarchiPrivati(new HashSet<>());
        entity.addMarchioPrivato(marchioPrivato);
        verify(marchioPrivato, times(1)).setPromozione(entity);
    }

    @Test
    public void removeMarchioPrivato_whenMarchiNull() {
        PromozioneMarchioPrivatoEntity marchioPrivato = mock(PromozioneMarchioPrivatoEntity.class);
        entity.setMarchiPrivati(null);
        entity.removeMarchioPrivato(marchioPrivato);
        verify(marchioPrivato, never()).setPromozione(null);
    }

    @Test
    public void removeMarchioPrivato() {
        PromozioneMarchioPrivatoEntity marchioPrivato = mock(PromozioneMarchioPrivatoEntity.class);
        entity.setMarchiPrivati(new HashSet<>());
        entity.removeMarchioPrivato(marchioPrivato);
        verify(marchioPrivato, times(1)).setPromozione(null);
    }

    @Test
    public void addRepartoMarchioPrivato_whenRepartiNull() {
        PromoRepartoMarchioPrivato reparto = mock(PromoRepartoMarchioPrivato.class);
        doNothing().when(reparto).setPromozione(entity);
        entity.setRepartiMarchiPrivati(null);
        entity.addRepartoMarchioPrivato(reparto);
        verify(reparto, times(1)).setPromozione(entity);
    }

    @Test
    public void addRepartoMarchioPrivato() {
        PromoRepartoMarchioPrivato reparto = mock(PromoRepartoMarchioPrivato.class);
        doNothing().when(reparto).setPromozione(entity);
        entity.setRepartiMarchiPrivati(new HashSet<>());
        entity.addRepartoMarchioPrivato(reparto);
        verify(reparto, times(1)).setPromozione(entity);
    }

    @Test
    public void removeRepartoMarchioPrivato_whenRepartiNull() {
        PromoRepartoMarchioPrivato reparto = mock(PromoRepartoMarchioPrivato.class);
        doNothing().when(reparto).setPromozione(null);
        entity.setRepartiMarchiPrivati(null);
        entity.removeRepartoMarchioPrivato(reparto);
        verify(reparto, times(1)).setPromozione(null);
    }

    @Test
    public void removeRepartoMarchioPrivato() {
        PromoRepartoMarchioPrivato reparto = mock(PromoRepartoMarchioPrivato.class);
        doNothing().when(reparto).setPromozione(null);
        entity.setRepartiMarchiPrivati(new HashSet<>());
        entity.removeRepartoMarchioPrivato(reparto);
        verify(reparto, times(1)).setPromozione(null);
    }

    @Test
    public void addTotalizzatore_whenTotalizzatoriNull() {
        PianificazioneTotalizzatoriEntity totalizzatore = mock(PianificazioneTotalizzatoriEntity.class);
        doNothing().when(totalizzatore).setTestata(entity);
        entity.setTotalizzatori(null);
        entity.addTotalizzatore(totalizzatore);
        verify(totalizzatore, times(1)).setTestata(entity);
    }

    @Test
    public void addTotalizzatore() {
        PianificazioneTotalizzatoriEntity totalizzatore = mock(PianificazioneTotalizzatoriEntity.class);
        doNothing().when(totalizzatore).setTestata(entity);
        entity.setTotalizzatori(new HashSet<>());
        entity.addTotalizzatore(totalizzatore);
        verify(totalizzatore, times(1)).setTestata(entity);
    }

    @Test
    public void removeTotalizzatori_whenTotalizzatoriNull() {
        PianificazioneTotalizzatoriEntity totalizzatore = mock(PianificazioneTotalizzatoriEntity.class);
        doNothing().when(totalizzatore).setTestata(null);
        entity.setTotalizzatori(null);
        entity.removeTotalizzatori(totalizzatore);
        verify(totalizzatore, times(1)).setTestata(null);
    }

    @Test
    public void removeTotalizzatori() {
        PianificazioneTotalizzatoriEntity totalizzatore = mock(PianificazioneTotalizzatoriEntity.class);
        doNothing().when(totalizzatore).setTestata(null);
        entity.setTotalizzatori(new HashSet<>());
        entity.removeTotalizzatori(totalizzatore);
        verify(totalizzatore, times(1)).setTestata(null);
    }
}
