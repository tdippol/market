package com.axiante.mui.dbpromo.persistence.service;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoArticoloEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;

public interface FatturazioneService extends DbPromoService<ContributiPromoEntity> {
    List<ContributiPromoEntity> findAllByPromozioni(List<PromozioneTestataEntity> promozioni);
    List<ContributiPromoEntity> findAllByPromozione(PromozioneTestataEntity promozione);
    Long countByPromozioneAndCompratoreAndFornitore(PromozioneTestataEntity promozione, CompratoreEntity compratore,
                                                    FornitoreEntity fornitore);
    List<Long> findAllItemsIdByPromozione(PromozioneTestataEntity promozione);
    Integer nextProgressivoByPromozioneAndCompratoreAndFornitore(PromozioneTestataEntity promozione,
                                                                 CompratoreEntity compratore,
                                                                 FornitoreEntity fornitore);
    List<ContributiPromoArticoloEntity> findAllContributiArticoliByIds(List<Long> ids);
    List<ContributiPromoArticoloEntity> findAllContributiArticoliByIdRata(Long idRata);
}
