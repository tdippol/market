package com.axiante.mui.webapp.business.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.persistence.service.MuiService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OwnershipServiceImplTest {

    @Mock
    private Instance<MuiService> muiServiceInstance;

    @Mock
    private MuiService muiService;

    @Mock
    private PromozioneTestataEntity testata;

    @InjectMocks
    private OwnershipServiceImpl service;

    @Before
    public void setUp() throws Exception {
        when(muiServiceInstance.get()).thenReturn(muiService);
    }

    @Test(expected = NullPointerException.class)
    public void hasOwnership_givenNullTestata_shouldThrowException() {
        service.hasOwnership(null, null);
    }

    @Test
    public void hasOwnership_givenEmptyGroups_shouldReturnFalse() {
        service.hasOwnership(testata, new ArrayList<>());
    }

    @Test
    public void hasOwnership_shouldReturnTrue_whenOwnedCanaliContainsCanaleTestata() {
        final CanalePromozioneEntity canale = mockCanale(10L);
        final CompratoreEntity compratore1 = mockCompratore("S01");
        final CompratoreEntity compratore2 = mockCompratore("S02");
        final List<String> groups = Collections.singletonList("FOO");
        final List<Long> codiciCanale = Collections.singletonList(10L);
        final Set<CompratoreEntity> compratori = new HashSet<>();
        compratori.add(compratore1);
        compratori.add(compratore2);
        final List<String> codiciCompratori = compratori.stream().map(CompratoreEntity::getCodiceCompratore)
                .collect(Collectors.toList());
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        when(testata.getOwners()).thenReturn(compratori);
        when(muiService.findOwnershipCodiciCanaleByGruppiAndCompratori(groups, codiciCompratori)).thenReturn(codiciCanale);
        assertTrue(service.hasOwnership(testata, groups));
        verify(muiService, times(1))
                .findOwnershipCodiciCanaleByGruppiAndCompratori(groups, codiciCompratori);
    }

    private CompratoreEntity mockCompratore(String code) {
        final CompratoreEntity entity = mock(CompratoreEntity.class);
        when(entity.getCodiceCompratore()).thenReturn(code);
        return entity;
    }

    private CanalePromozioneEntity mockCanale(Long code) {
        final CanalePromozioneEntity entity = mock(CanalePromozioneEntity.class);
        when(entity.getCodiceCanale()).thenReturn(code);
        return entity;
    }
}