package com.axiante.mui.dbpromo.persistence.service.impl;

import com.axiante.mui.dbpromo.business.utils.DefaultMessaggiHelper;
import com.axiante.mui.dbpromo.persistence.dao.CastellettoMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.dao.CfgIdMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.dao.MuiCfgDefaultCastellettoMessaggiDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozionePianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.dto.CastellettoMessaggiDTO;
import com.axiante.mui.dbpromo.persistence.entities.CastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleDispositivoEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgIdMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiCfgDefaultCastellettoMessaggiEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiFontEntities;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CastellettoMessaggiService;
import com.axiante.mui.dbpromo.persistence.service.MuiFontEntitiesService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Dependent
@Transactional
public class CastellettoMessaggiServiceImpl extends AbstractDbPromoService<CastellettoMessaggiEntity>
        implements CastellettoMessaggiService {
    private static final long serialVersionUID = -8143947934658251049L;

    @Inject
    @Getter
    private CastellettoMessaggiDAO dao;

    @Inject
    @Getter(AccessLevel.PROTECTED)
    Instance<MuiCfgDefaultCastellettoMessaggiDAO> cfgDao;

    @Inject
    @Getter(AccessLevel.PROTECTED)
    Instance<PromozioneTestataDAO> promozioneTestataDao;

    @Inject
    @Getter(AccessLevel.PROTECTED)
    private PromozionePianificazioneDAO pianificazioneDAO;

    @Inject
    @Getter(AccessLevel.PROTECTED)
    private CfgIdMessaggiDAO idMessaggiDAO;

    @Inject
    @Getter(AccessLevel.PROTECTED)
    private Instance<MuiFontEntitiesService> fontEntitiesServiceInstance;

    @Inject
    @Getter(AccessLevel.PROTECTED)
    private Instance<DefaultMessaggiHelper> defaultMessaggiHelper;

    @Override
    public List<CastellettoMessaggiEntity> findByIdPianificazione(Long idPianificazione) {
        return getDao().findByIdPianificazione(idPianificazione);
    }

    @Override
    public List<CastellettoMessaggiEntity> findMessaggiByIdPianificazione(Long idPianificazione) {
        return getDao().findMessaggiByIdPianificazione(idPianificazione);
    }

    @Override
    public List<CastellettoMessaggiEntity> findMessaggiByIdPianificazioneAndCodiceDispositivo(
            Long idPianificazione, String codiceDispositivo) {
        return getDao()
                .findMessaggiByIdPianificazioneAndCodiceDispositivo(idPianificazione, codiceDispositivo);
    }

    @Override
    public List<CastellettoMessaggiEntity> findCastellettiByIdPianificazione(Long idPianificazione) {
        return getDao().findCastellettiByIdPianificazione(idPianificazione);
    }

    @Override
    public void remove(List<Long> idCastelletti) {
        getDao().remove(idCastelletti);
    }

    @Override
    public List<CastellettoMessaggiDTO> findEnhancedMessaggiByIdPianificazioneAndCodiceDispositivo(
            Long idPianificazione, String codiceDispositivo) {
        List<CastellettoMessaggiDTO> result =
                getDao()
                        .findMessaggiByIdPianificazioneAndCodiceDispositivo(idPianificazione, codiceDispositivo)
                        .stream()
                        .map(CastellettoMessaggiDTO::new)
                        .collect(Collectors.toList());
        // mi serve il canale e la meccanica per fare la join con cfg_id_messaggi
        // quindi li prendo dalla pianificazione
        final PromozionePianificazioneEntity pianificazione =
                pianificazioneDAO.findById(idPianificazione);
        // mi leggo la mappa di tutte le descrizioni disponibili per il canale, la meccanica ed il
        // dispositivo
        // in modo da non fare una query per ogni messaggio
        final Map<Integer, String> descrizioni =
                idMessaggiDAO
                        .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                                pianificazione
                                        .getPromozioneTestataEntity()
                                        .getCanalePromozioneEntity()
                                        .getCodiceCanale(),
                                pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                                codiceDispositivo)
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        CfgIdMessaggiEntity::getIdMessaggio, CfgIdMessaggiEntity::getDescrizione));
        final Map<String, String> fontEntities = fontEntitiesServiceInstance.get().findAll().stream()
                .collect(Collectors.toMap(MuiFontEntities::getId, MuiFontEntities::getDescrizione));
        // aggiungo descrizione e fontEntities al messaggio
        for (CastellettoMessaggiDTO messaggio : result) {
            messaggio.setDescrizione(descrizioni.get(messaggio.getIdMessaggio()));
            messaggio.setDescrizioneFontEntity(fontEntities.get(messaggio.getFontEntity()));
        }
        return result;
    }

    @Override
    public void addMessageAbove(Long idMessaggio, String codiceUtente) {
        final CastellettoMessaggiEntity entity = getDao().findById(idMessaggio);
        if (entity == null) {
            log.error("Messaggio non trovato con id {}", idMessaggio);
            return;
        }
    /*
    1. crea un messaggio con stesso idMessaggio e sequenza
    2. prendi tutti i messaggi con idPianificazione e dispositivo uguali e
       sequenza maggiore di quello trovato
    3. incrementa la sequenza di 1 per tutti incluso quello trovato
    4. salva il messaggio creato
    5. salva tutti i messaggi aggiornati

     */
        final CastellettoMessaggiEntity newEntity = new CastellettoMessaggiEntity();
        newEntity.setIdMessaggio(entity.getIdMessaggio());
        newEntity.setPianificazione(entity.getPianificazione());
        newEntity.setCodiceCanaleDispositivo(entity.getCodiceCanaleDispositivo());
        newEntity.setSeqStampa(entity.getSeqStampa());
        newEntity.setSezione(entity.getSezione());
        newEntity.setCodiceUtenteInserimento(codiceUtente);
        newEntity.setDataInserimento(new Date(System.currentTimeMillis()));
        final List<CastellettoMessaggiEntity> messaggi =
                getDao()
                        .findMessaggiByIdPianificazioneAndCodiceDispositivoAndSeqStampaGreaterThan(
                                entity.getPianificazione().getId(),
                                entity.getCodiceCanaleDispositivo(),
                                entity.getSeqStampa());
        messaggi.add(entity); // aggiungo il messaggio sopra cui inserisco il nuovo
        messaggi.forEach(
                e -> {
                    e.setSeqStampa(e.getSeqStampa() + 1); // aggiorna la sequenza
                    fillAuditLogFields(e, codiceUtente);
                });
        messaggi.add(newEntity); // il nuovo messaggio

        persistInTransaction(messaggi); // salvo tutto in un colpo
    }

    // lo so, ma cosi' non mi tocca fare un cast
    protected CastellettoMessaggiEntity fillAuditLogFields(
            @NonNull final CastellettoMessaggiEntity entity, @NonNull final String user) {
        final Date data = new Date(System.currentTimeMillis());
        if (entity.getId() != null) {
            entity.setCodiceUtenteAggiornamento(user);
            entity.setDataAggiornamento(data);
        } else {
            entity.setCodiceUtenteInserimento(user);
            entity.setDataInserimento(data);
        }
        return entity;
    }

    @Transactional
    @Override
    public void resetMessaggi(
            PromozionePianificazioneEntity pianificazione,
            CfgCanaleDispositivoEntity dispositivo,
            String codiceUtente) {

        try {
            if (!canResetMessaggi(pianificazione, dispositivo)) {
                log.error(
                        "Non posso resettare i messaggi per la pianificazione {} e il dispositivo {} perche' non esiste una configurazione",
                        pianificazione.getId(),
                        dispositivo.getCodice());
                return;
            }

            // Get existing messages
            final List<CastellettoMessaggiEntity> messaggi =
                    getDao()
                            .findMessaggiByIdPianificazioneAndCodiceDispositivo(
                                    pianificazione.getId(), dispositivo.getCodice());

            // Delete existing messages
            remove(messaggi.stream().map(CastellettoMessaggiEntity::getId).collect(Collectors.toList()));

            // Get default message configurations
            List<MuiCfgDefaultCastellettoMessaggiEntity> configurazioni =
                    getCfgDao()
                            .get()
                            .findByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                                    pianificazione
                                            .getPromozioneTestataEntity()
                                            .getCanalePromozioneEntity()
                                            .getCodiceCanale(),
                                    pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                                    dispositivo.getCodice());

            // Create new messages based on defaults
            DefaultMessaggiHelper helper = getDefaultMessaggiHelper().get();
            List<CastellettoMessaggiEntity> messaggiDaInserire =
                    configurazioni.stream()
                            .map(c -> helper.mapToMessageRow(c, pianificazione, codiceUtente))
                            .collect(Collectors.toList());

            // Insert new messages
            persistInTransaction(messaggiDaInserire);

        } catch (Exception e) {
            log.error("Errore durante il reset dei messaggi", e);
            throw e; // Re-throw to ensure transaction rollback
        }
    }

    @Override
    public boolean canResetMessaggi(
            PromozionePianificazioneEntity pianificazione, CfgCanaleDispositivoEntity dispositivo) {
        return getCfgDao()
                .get()
                .countByCodiceCanaleAndCodiceMeccanicaAndCodiceDispositivo(
                        pianificazione
                                .getPromozioneTestataEntity()
                                .getCanalePromozioneEntity()
                                .getCodiceCanale(),
                        pianificazione.getMeccanicaEntity().getCodiceMeccanica(),
                        dispositivo.getCodice())
                > 0;
    }

    @Override
    public boolean isLogoUsedInPromo(String logoFilename, Long idPianificazione) {
        Long count = getPromozioneTestataDao().get().countByLogoFilenameAndIdTestata(logoFilename, idPianificazione);
        return count > 0;
    }

    @Override
    public String getHtmlFromDb(Long idPianificazione, String codiceDispositivo, String imgPath) {
        return getDao().getHtmlFromDb(idPianificazione, codiceDispositivo, imgPath);
    }
}
