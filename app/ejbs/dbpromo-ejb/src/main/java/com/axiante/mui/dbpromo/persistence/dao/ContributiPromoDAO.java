package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ContributiPromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.FornitoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.List;

public interface ContributiPromoDAO extends DbPromoDAO<ContributiPromoEntity> {
    List<ContributiPromoEntity> findAllByPromozioni(List<PromozioneTestataEntity> promozioni);
    List<ContributiPromoEntity> findAllByPromozione(PromozioneTestataEntity promozione);
    Long countByPromozioneAndCompratoreAndFornitore(PromozioneTestataEntity promozione, CompratoreEntity compratore,
                                                    FornitoreEntity fornitore);
    List<Long> findAllItemsIdByPromozione(PromozioneTestataEntity promozione);
    Integer nextProgressivo(PromozioneTestataEntity promozione,
                            CompratoreEntity compratore,
                            FornitoreEntity fornitore);
}
