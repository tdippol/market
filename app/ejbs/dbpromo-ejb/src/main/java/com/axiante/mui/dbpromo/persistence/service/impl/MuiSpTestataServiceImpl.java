package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.persistence.dao.MuiSpTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiSpTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.MuiSpTestataService;
import lombok.Getter;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
@Transactional
public class MuiSpTestataServiceImpl extends AbstractDbPromoService<MuiSpTestataEntity> implements MuiSpTestataService {
    private static final long serialVersionUID = 1L;
    private static final String SPECIAL_PROMOTION_TECH_USER = "SPECIAL_PROMOTION";

    @Inject
    private CreatePromotionService createPromotionService;

    @Inject
    @Getter
    private MuiSpTestataDAO dao;

    @Override
    public List<MuiSpTestataEntity> findAllForVisualizza() {
        return getDao().findAllForVisualizza();
    }

    @Override
    public void deleteById(Long id) {
        getDao().deleteById(id);
    }

    @Override
    public void deactivateOtherActive(Long id) {
        getDao().deactivateOtherActive(id);
    }

    @Override
    public MuiSpTestataEntity findActive() {
        return getDao().findActive();
    }


    @Override
    public void save(MuiSpTestataEntity entity) {
        getDao().save(entity);
    }


    @Override
    public MuiSpTestataEntity findById(Long id) {
        return getDao().findById(id);
    }

    @Override
    public MuiSpTestataEntity update(MuiSpTestataEntity entity) {
        return getDao().update(entity);
    }

    @Override
    public String calculatePromoCode(String anno, CanalePromozioneEntity canale) {

        log.info("SP promo code - start, anno=" + anno
                + ", canaleId=" + canale.getId()
                + ", codiceCanale=" + canale.getCodiceCanale());

        if (anno == null || anno.trim().isEmpty()) {
            throw new IllegalArgumentException("Anno obbligatorio per il calcolo del codice promozione");
        }

        if (canale == null) {
            throw new IllegalArgumentException("Canale obbligatorio per il calcolo del codice promozione");
        }

        CanaleLastProgEntity progressivo =
                createPromotionService.findCanaleLastProgEntityByYearAndChannel(anno, canale);

        if (canale.getCodeRangeMin() == null) {
            String messaggio = String.format(
                    "Canale %d non inizializzato correttamente: manca CodeRangeMin",
                    canale.getCodiceCanale());
            throw new IllegalStateException(messaggio);
        }

        if (canale.getCodeRangeMax() == null) {
            String messaggio = String.format(
                    "Canale %d non inizializzato correttamente: manca CodeRangeMax",
                    canale.getCodiceCanale());
            throw new IllegalStateException(messaggio);
        }

        if (progressivo == null) {
            progressivo = new CanaleLastProgEntity();
            progressivo.setAnno(Long.parseLong(anno));
            progressivo.setMuiCanalePromozione(canale);
            progressivo.setLastProgressivo(canale.getCodeRangeMin());
        }

        Long lastProgressivo = progressivo.getLastProgressivo();
        if (lastProgressivo == null) {
            throw new IllegalStateException(
                    String.format(
                            "Il canale %s non e' correttamente inizializzato: progressivo mancante",
                            canale.getDescrizione()));
        }

        if ((lastProgressivo >= canale.getCodeRangeMin())
                && (lastProgressivo < canale.getCodeRangeMax())) {
            lastProgressivo += 1;
        }

        if (lastProgressivo > canale.getCodeRangeMax()) {
            throw new IllegalStateException(
                    String.format("Progressivi esauriti per il canale %s", canale.getCodiceCanale()));
        }

        if (lastProgressivo < canale.getCodeRangeMin()) {
            throw new IllegalStateException(
                    String.format(
                            "Errore di configurazione: il progressivo minimo per il canale %d e' maggiore del progressivo corrente",
                            canale.getCodeRangeMin()));
        }

        progressivo.setLastProgressivo(lastProgressivo);
        createPromotionService.persistCanaleLastProgEntity(progressivo, SPECIAL_PROMOTION_TECH_USER);

        return String.format("%s_%s", anno, lastProgressivo);
    }

    @Override
    public MuiSpTestataEntity findActiveForHeader() {
        return getDao().findActiveForHeader();
    }

    @Override
    public MuiSpTestataEntity findByIdForHeader(Long id) {
        return getDao().findByIdForHeader(id);
    }

    @Override
    public void flush() {
        getDao().flush();
    }

    @Override
    public void runProcedureAssociaReparti(Long idPromozione) {
        getDao().runProcedureAssociaReparti(idPromozione);
    }
}