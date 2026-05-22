package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PromozioneTestataDAO extends DbPromoDAO<PromozioneTestataEntity> {

    PromozioneTestataEntity findByPromoCode(String promoCode);

    boolean runFunctionAccodamentoPubblicazioni(Long idPromozione, String username, Long idNuovoStatoPromozione)
            throws Exception;

    boolean runFunctionCheckStatusTransiction(Long idPromozione, String username, Long idNuovoStatoPromozione)
            throws Exception;

    boolean runFunctionExportPianificazioni(Long idPromozione, String idCompratori, String username, Integer timeout)
            throws Exception;

    boolean runFunctionCalcolaSovrapposizioni(Long idPromozione, String username, Integer timeout) throws Exception;

    // With filters on channels!
    List<PromozioneTestataEntity> findAllSecured(List<CanalePromozioneEntity> canali);

    List<PromozioneTestataEntity> findAllNotCancelled(List<CanalePromozioneEntity> canali);

    List<PromozioneTestataEntity> findAllNotCancelled(Map<String, String> userFilters,
                                                      List<CanalePromozioneEntity> canali);

    List<PromozioneTestataEntity> findAllNotCancelledWithPlannedMecc(List<CanalePromozioneEntity> canali,
                                                                     List<Long> idMeccaniche);

    List<PromozioneTestataEntity> findAllNotCancelledWithPlannedMecc(Map<String, String> userFilters,
                                                                     List<CanalePromozioneEntity> canali,
                                                                     List<Long> idMeccaniche);

    List<PromozioneTestataEntity> findOverlappingPromo(PromozioneTestataEntity promozioneTestataEntity,
                                                       List<CanalePromozioneEntity> canali);

    List<PromozioneTestataEntity> findAllByAnnoAndCanale(String anno, CanalePromozioneEntity canale);

    List<PromozioneTestataEntity> findOverlappingByCodiciMeccanica(PromozioneTestataEntity promozione,
                                                                   List<String> codiciMeccanica);

    List<PromozioneTestataEntity> findOverlappingByAnnoAndCodiceMeccanicaWithOffset(PromozioneTestataEntity promozione,
                                                                                    List<String> codiciMeccanica,
                                                                                    Integer overlapOffsetStart,
                                                                                    Integer overlapOffsetEnd);

    List<PromozioneTestataEntity> findAllByAnnoAndMeccanica(PromozioneTestataEntity promozione,
                                                            List<String> codiciMeccanica);

    Long countByLogoFilenameAndIdTestata(String logoFilename, Long idTestata);

    List<PromozioneTestataEntity> findByCanaleMeccanicheDate(Long idCanale, List<String> codiciMeccaniche,
                                                             Date dataInizio, Date dataFine);

    PromozioneTestataEntity findByIdFullEagerFetch(Long id);

    Long copiaPromozione(String source, String codiceUtente, Long idPromozione, Date dataInizio, Date dataFine);

    List<PromozioneTestataEntity> findOverlappingPromoWithAttributo(PromozioneTestataEntity promozioneTestataEntity,
                                                                    Long idAttributo, String valoreAttributo);
}
