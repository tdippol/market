package com.axiante.mui.webapp.utils.security;

import com.axiante.mui.dbpromo.persistence.entities.CumulabilitaEsclusivitaEntity;
import javax.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class CumulabilitaSecurity {

    /**
     * La pubblicazione di una cumulabilita' e' possibile se dataPubblicazione e dataAnnullamento
     * sono entrambe null
     *
     * @param cumulabilita cumulabilita'
     * @return true se e' possibile pubblicare la cumulabilita', false altrimenti
     */
    public boolean canPublishCumulabilita(@NonNull CumulabilitaEsclusivitaEntity cumulabilita) {
        return cumulabilita.getDataPubblicazione() == null && cumulabilita.getDataAnnullamento() == null;
    }

    /**
     * L'annullamento di una cumulabilita' e' possibile se dataAnnullamentoe' null
     *
     * @param cumulabilita cumulabilita'
     * @return true se e' possibile annullare la cumulabilita', false altrimenti
     */
    public boolean canCancelCumulabilita(@NonNull CumulabilitaEsclusivitaEntity cumulabilita) {
        return cumulabilita.getDataAnnullamento() == null;
    }

    /**
     * L'eliminazione di un buono da una cumulabilita' e' possibile se dataPubblicazione e dataAnnullamento
     * sono entrambe null
     *
     * @param cumulabilita cumulabilita'
     * @return true se e' possibile eliminare buoni associati, false altrimenti
     */
    public boolean canDeleteBuono(@NonNull CumulabilitaEsclusivitaEntity cumulabilita) {
        return cumulabilita.getDataPubblicazione() == null && cumulabilita.getDataAnnullamento() == null;
    }

    /**
     * L'aggiunta di un buono ad una cumulabilita' e' possibile se dataPubblicazione e dataAnnullamento
     * sono entrambe null
     *
     * @param cumulabilita cumulabilita'
     * @return true se e' possibile aggiungere buoni, false altrimenti
     */
    public boolean canAddBuono(@NonNull CumulabilitaEsclusivitaEntity cumulabilita) {
        return cumulabilita.getDataPubblicazione() == null && cumulabilita.getDataAnnullamento() == null;
    }
}
