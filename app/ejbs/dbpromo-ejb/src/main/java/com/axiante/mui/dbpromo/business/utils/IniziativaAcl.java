package com.axiante.mui.dbpromo.business.utils;

import com.axiante.mui.dbpromo.business.enumeration.IniziativaStatusEnum;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.IniziativaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;

public class IniziativaAcl {
    public static synchronized IniziativaStatoEntity getLastStatus(@NonNull final IniziativaEntity iniziativa) {
        final List<IniziativaStatoEntity> stati = iniziativa.getStati().stream()
                .filter(s -> s.getDataFineStato() == null)
                .collect(Collectors.toList());
        if (stati.size() > 1) {
            throw new IllegalArgumentException("There can be only one status with a null end date");
        }
        return stati.isEmpty() ? null : stati.get(0);
    }

    public static synchronized StatoPromozioneEntity getStatoCorrente(@NonNull final IniziativaEntity iniziativa) {
        final IniziativaStatoEntity lastStatus = getLastStatus(iniziativa);
        return lastStatus == null ? null : lastStatus.getStatoPromo();
    }

    public static synchronized boolean isPublished(@NonNull final IniziativaEntity iniziativa) {
        final StatoPromozioneEntity statoCorrente = getStatoCorrente(iniziativa);
        return statoCorrente != null && IniziativaStatusEnum.PUBBLICATA.getCodice().equals(statoCorrente.getCodiceStato());
    }

    public static synchronized boolean isDeleted(@NonNull final IniziativaEntity iniziativa) {
        final StatoPromozioneEntity statoCorrente = getStatoCorrente(iniziativa);
        return statoCorrente != null && IniziativaStatusEnum.CANCELLATA_00.getCodice().equals(statoCorrente.getCodiceStato());
    }

    /**
     * Controlla se l'iniziativa puo' essere pubblicata: non e' gia' stata pubblica e il campo TOTALIZZATORE_ALLINEATO
     * e' diverso da 0
     *
     * @param iniziativa iniziativa
     * @return true se puo' essere pubblicata, false altrimenti
     */
    public static synchronized boolean canBePublished(@NonNull final IniziativaEntity iniziativa) {
        return !isPublished(iniziativa) && Boolean.TRUE.equals(iniziativa.getTotalizzatoreAllineato());
    }
}
