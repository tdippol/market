package com.axiante.mui.validator.model;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PromoUpdateTest {
    @Spy
    private PromoUpdate promoUpdate;

    @Test
    public void getCanalePromozioneEntity() {
        assertNull(promoUpdate.getCanalePromozioneEntity());
    }

    @Test
    public void setCanalePromozioneEntity() {
        doNothing().when(promoUpdate).setCanalePromozioneEntity(any(CanalePromozioneEntity.class));
        final CanalePromozioneEntity canalePromo = mock(CanalePromozioneEntity.class);
        promoUpdate.setCanalePromozioneEntity(canalePromo);
        verify(promoUpdate, times(1)).setCanalePromozioneEntity(canalePromo);
    }
}