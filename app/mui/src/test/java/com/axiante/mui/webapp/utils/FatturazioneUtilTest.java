package com.axiante.mui.webapp.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.webservice.util.pianificazione.PianificazioneEntityPredicates;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FatturazioneUtilTest {

    @Mock
    private Instance<PromoService> promoServiceInstance;

    @Mock
    private PromoService promoService;

    @Mock
    private Instance<PianificazioneSecurityUtil> securityUtilInstance;

    @Mock
    private PianificazioneSecurityUtil securityUtil;

    @Mock
    private Instance<PianificazioneEntityPredicates> predicatesInstance;

    @Mock
    private PianificazioneEntityPredicates predicates;

    @InjectMocks
    private FatturazioneUtil fatturazioneUtil;

    private UserDTO userDTO;
    private List<CanalePromozioneEntity> canali;
    private List<String> gruppi;

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Before
    public void setUp() {
        when(promoServiceInstance.get()).thenReturn(promoService);
        when(securityUtilInstance.get()).thenReturn(securityUtil);
        when(predicatesInstance.get()).thenReturn(predicates);
        when(predicates.byTipoRiga(PianificazioneRowTypeEnum.ELEMENTO)).thenCallRealMethod();
        canali = new ArrayList<>();
        canali.add(mock(CanalePromozioneEntity.class));
        gruppi = new ArrayList<>();
        gruppi.add("FOO");
        userDTO = mockUserDto(canali, gruppi);
    }

    @Test
    public void readAvailableFatturazioni_givenNullUserDto_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        fatturazioneUtil.readAvailableFatturazioni(null);
    }

    @Test
    public void readPromozioni_givenNullUserDto_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        fatturazioneUtil.readPromozioni(null);
    }

    @Test
    public void readPromozioniWithWritableItems_givenNullUserDto_shouldThrowException() throws Exception {
        ex.expect(NullPointerException.class);
        fatturazioneUtil.readPromozioniWithWritableItems(null);
    }

    @Test
    public void readPromozioniWithWritableItems() throws Exception {
        final List<PromozioneTestataEntity> testate = new ArrayList<>();
        final PromozionePianificazioneEntity pianificazione1 = mockPianificazione("1", true);
        final PromozionePianificazioneEntity pianificazione2 = mockPianificazione("2", false);
        final PromozionePianificazioneEntity pianificazione3 = mockPianificazione("3", false);
        final PromozionePianificazioneEntity pianificazione4 = mockPianificazione("4", false);
        final PromozioneTestataEntity testata1 = mockTestata(pianificazione1, pianificazione2);
        final PromozioneTestataEntity testata2 = mockTestata(pianificazione3, pianificazione4);
        testate.add(testata1);
        testate.add(testata2);
        when(promoService.findAllNotCancelled(anyMap(), eq(canali))).thenReturn(testate);
        final List<PromozioneTestataEntity> promozioni = fatturazioneUtil.readPromozioniWithWritableItems(userDTO);
        assertEquals(1, promozioni.size());
    }

    @Test
    public void readCompratoriWithWritableItems_givenNullPromozioneshouldThrowException() {
        ex.expect(NullPointerException.class);
        fatturazioneUtil.readCompratoriWithWritableItems(null, userDTO);
    }

    @Test
    public void readCompratoriWithWritableItems_givenNullUserDto_shouldThrowException() {
        ex.expect(NullPointerException.class);
        final PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
        fatturazioneUtil.readCompratoriWithWritableItems(promozione, null);
    }

    @Test
    public void readCompratoriWithWritableItems() {
        final CompratoreEntity compratore = mockCompratore("C11");
        final PromozionePianificazioneEntity pianificazione1 = mockPianificazione("11", true);
        final PromozionePianificazioneEntity pianificazione2 = mockPianificazione("12", false);
        final PromozioneTestataEntity testata = mockTestata(pianificazione1, pianificazione2);
        lenient().when(securityUtil.getCompratoriWithPlannedItems(testata, Arrays.asList(11L, 12L), gruppi))
                .thenReturn(Collections.singletonList("C11"));
        lenient().when(securityUtil.getCompratoriWithPlannedItems(testata, Arrays.asList(12L, 11L), gruppi))
                .thenReturn(Collections.singletonList("C11"));
        when(promoService.findAllBuyersByCodes(Collections.singletonList("C11")))
                .thenReturn(Collections.singletonList(compratore));
        final List<CompratoreEntity> compratori = fatturazioneUtil.readCompratoriWithWritableItems(testata, userDTO);
        assertEquals(1, compratori.size());
    }

    private CompratoreEntity mockCompratore(String codice) {
        final CompratoreEntity compratore = mock(CompratoreEntity.class);
        lenient().when(compratore.getCodiceCompratore()).thenReturn(codice);
        return compratore;
    }

    private PromozioneTestataEntity mockTestata(PromozionePianificazioneEntity... pianificazioni) {
        final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
        when(testata.getPromozionePianificazioneEntities()).thenReturn(new HashSet<>(Arrays.asList(pianificazioni)));
        return testata;
    }

    private PromozionePianificazioneEntity mockPianificazione(String codiceElemento, boolean writable) {
        final CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        when(tipoRiga.getCodiceTipo()).thenReturn("E");
        final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(pianificazione.getCodiceElemento()).thenReturn(codiceElemento);
        lenient().when(securityUtil.isWriteable(pianificazione, gruppi)).thenReturn(writable);
        return pianificazione;
    }

    private UserDTO mockUserDto(List<CanalePromozioneEntity> canali, List<String> gruppi) {
        final UserDTO userDTO = mock(UserDTO.class);
        final UsersEntity user = mock(UsersEntity.class);
        when(user.getDbFilters()).thenReturn("{}");
        when(userDTO.getUser()).thenReturn(user);
        when(userDTO.isAdmin()).thenReturn(false);
        when(userDTO.getCanali()).thenReturn(canali);
        when(userDTO.getGruppi()).thenReturn(gruppi);
        return userDTO;
    }

}