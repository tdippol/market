package com.axiante.mui.webapp.utils.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.webapp.business.data.UserDTO;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromoSecurityTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    private UserDTO userDTO;

    @Mock
    private CanalePromozioneEntity canale1;

    @Mock
    private CanalePromozioneEntity canale2;

    @Mock
    private PromozioneTestataEntity promozione;

    @InjectMocks
    private PromoSecurity promoSecurity;

    @Before
    public void setUp() throws Exception {
        when(canale1.getCodiceCanale()).thenReturn(10L);
        when(canale2.getCodiceCanale()).thenReturn(20L);
    }

    @Test
    public void isAccessible_givenNullPromozione_shouldReturnFalse() {
        assertFalse(promoSecurity.isAccessible(null, userDTO));
    }

    @Test
    public void isAccessible_givenNullUserDTO_shouldReturnFalse() {
        assertFalse(promoSecurity.isAccessible(promozione, null));
    }

    @Test
    public void isAccessible_givenPromozioneWithoutChannel_shouldReturnTrue() {
        assertFalse(promoSecurity.isAccessible(promozione, userDTO));
        verify(userDTO, never()).getCanali();
    }

    @Test
    public void isAccessible_givenUserWithNullChannels_shouldReturnTrue() {
        mockPromozioneWithCanale(canale1);
        when(userDTO.getCanali()).thenReturn(null);
        assertTrue(promoSecurity.isAccessible(promozione, userDTO));
        verify(userDTO, times(1)).getCanali();
    }

    @Test
    public void isAccessible_givenPromozioneWithCanaleInCanaliAvailableForUser_shouldReturnTrue() {
        mockPromozioneWithCanale(canale1);
        mockUserWithCanali(canale1, canale2);
        assertTrue(promoSecurity.isAccessible(promozione, userDTO));
        verify(userDTO, times(2)).getCanali();
        verify(canale1, atMost(2)).getCodiceCanale();
        verify(canale2, atMost(1)).getCodiceCanale();
    }

    @Test
    public void isAccessible_givenPromozioneWithCanaleNotInCanaliAvailableForUser_shouldReturnFalse() {
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        when(canale.getCodiceCanale()).thenReturn(30L);
        mockPromozioneWithCanale(canale);
        mockUserWithCanali(canale1, canale2);
        assertFalse(promoSecurity.isAccessible(promozione, userDTO));
        verify(userDTO, times(2)).getCanali();
        verify(canale1, atMost(1)).getCodiceCanale();
        verify(canale2, atMost(1)).getCodiceCanale();
        verify(canale, times(2)).getCodiceCanale();
    }

    private void mockUserWithCanali(CanalePromozioneEntity... canali) {
        when(userDTO.getCanali()).thenReturn(Arrays.asList(canali));
    }

    private void mockPromozioneWithCanale(CanalePromozioneEntity canale) {
        when(promozione.getMuiCanalePromozione()).thenReturn(canale);
    }
}