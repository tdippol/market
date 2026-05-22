package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.PianoMediaStatusEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaPromoDbpromoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatiConsentitiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.CfgPianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaPromoDbpromoService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PianoMediaFactory {

    @Inject
    private Instance<ApplicationProperties> applicationPropertiesInstance;

    @Inject
    private Instance<CanalePromozioneService> canaleServiceInstance;

    @Inject
    private Instance<StatoPromoService> statoPromoServiceInstance;

    @Inject
    private Instance<CfgPianoMediaService> cfgPianoMediaServiceInstance;

    @Inject
    private Instance<PianoMediaPromoDbpromoService> pianoMediaPromoServiceInstance;

    @Inject
    private Instance<PianificazionePianoMediaService> pianificazionePianoMediaServiceInstance;

    @Inject
    CreatePromotionService creaPromozioneService;

    public PianoMediaEntity build(@NonNull CreaPianoMediaEntity creaPianoMedia,
                                  @NonNull String user) {
        String codiceCanale = applicationPropertiesInstance.get().getProperty(ApplicationProperties.CANALE_PIANO_MEDIA);
        if (codiceCanale == null) {
            log.error("Canale 'Piano Media' not configured");
            return null;
        }
        try {
            final CanalePromozioneEntity canalePromo = canaleServiceInstance.get()
                    .findByCodiceCanale(Long.parseLong(codiceCanale));
            if (canalePromo == null) {
                log.error(String.format("Cannot find canale 'Piano Media' with code '%s'", codiceCanale));
                return null;
            }
            final PianoMediaEntity pianoMedia = (PianoMediaEntity) AuditLogFiller
                    .fillAuditLogFields(new PianoMediaEntity(), user);
            pianoMedia.setCanale(canalePromo);
            pianoMedia.setAnno(creaPianoMedia.getAnno());
            pianoMedia.setDescrizione(creaPianoMedia.getDescrizione());
            pianoMedia.setDataInizio(creaPianoMedia.getDataInizio());
            pianoMedia.setDataFine(creaPianoMedia.getDataFine());
            pianoMedia.setPromoMaster(creaPianoMedia.getPromoMaster());
            pianoMedia.setPromoSecondaryA(creaPianoMedia.getPromoSecondaryA());
            pianoMedia.setPromoSecondaryB(creaPianoMedia.getPromoSecondaryB());
            pianoMedia.setPromoSecondaryC(creaPianoMedia.getPromoSecondaryC());
            // Aggiungo il primo stato (10 - PIANO_MEDIA_CREATO)
            addStatus(pianoMedia, PianoMediaStatusEnum.PIANO_MEDIA_CREATO.getKey(), user);
            // Calcolo gli stati consentiti e le transizioni
            calcolaStatiConsentiti(pianoMedia, user);
            calcolaStatiTransizione(pianoMedia, user);
            return pianoMedia;
        } catch (Exception ex) {
            log.error(String.format("Error finding canale 'Piano Media' with code '%s'", codiceCanale));
            return null;
        }
    }

    private void addStatus(@NonNull final PianoMediaEntity pianoMedia, @NonNull final String statusCode,
                           @NonNull final String user) {
        try {
            final StatoPromozioneEntity stato = statoPromoServiceInstance.get().findByCode(statusCode);
            if (stato == null) {
                throw new RuntimeException(String.format("Cannot find StatoPromozioneEntity with code '%s'", statusCode));
            }
            final PianoMediaStatoEntity pianoMediaStato = (PianoMediaStatoEntity) AuditLogFiller
                    .fillAuditLogFields(new PianoMediaStatoEntity(), user);
            pianoMediaStato.setStato(stato);
            pianoMediaStato.setDataInizioStato(Calendar.getInstance().getTime());
            pianoMediaStato.setDataFineStato(null);
            pianoMedia.addPianoMediaStato(pianoMediaStato);
        } catch (Exception ex) {
            log.error(String.format("Error adding Stato '%s' to PianoMedia", statusCode), ex);
        }
    }

    public Set<PianificazionePianoMediaEntity> createDefaults(final PianoMediaEntity pianoMedia, String userName) {
        Set<PianificazionePianoMediaEntity> pianificazioni = new HashSet<>();
        // 1. Recupera la promozione master ed estrai data inizio e fine
        final PianoMediaPromoDbpromoEntity promoRif = pianoMediaPromoServiceInstance.get()
                .findByCodicePromo(pianoMedia.getPromoMaster());
        if (promoRif != null) {
            Date dataInizio = promoRif.getDataInizio();
            Date dataFine = promoRif.getDataFine();
            // 2. cerca le configurazioni di default
            List<CfgPianoMediaEntity> configs = cfgPianoMediaServiceInstance.get().findAllAttivi();
            // 3. per ogni configurazione calcola la data inizio e fine della pianificazione
            configs.stream().forEach(conf -> {
                try {
                    PianificazionePianoMediaEntity pianificazione = (PianificazionePianoMediaEntity) AuditLogFiller.fillAuditLogFields(new PianificazionePianoMediaEntity(), userName);
                    pianificazione.setPianoMedia(pianoMedia);
                    pianificazione.setSupportoMedia(conf.getSupportoMedia());
                    pianificazione.setDataInizio(calculateDataInizio(dataInizio, conf));
                    pianificazione.setDataFine(calculateDataFine(pianificazione.getDataInizio(), dataFine, conf));
                    try{
                        pianificazione = pianificazionePianoMediaServiceInstance.get().persist(pianificazione);
                    } catch (Exception e){
                        log.error(String.format("Errore nel salvataggio della configurazione per il media %s per il piano %s ",
                                pianificazione.getSupportoMedia().getDescrizione(), pianoMedia.getDescrizione()), e);
                    }
                    pianificazioni.add(pianificazione);
                } catch (IllegalAccessException e){
                    log.error("Errore nella creazione della configurazione del piano media", e);
                }
            });
        }
        // 3. ritorna le pianificazioni
        return pianificazioni;
    }

    Date calculateDataInizio(Date dataInizio, CfgPianoMediaEntity cfgPianoMedia) throws IllegalAccessException {
        if ( cfgPianoMedia == null ){
            throw new RuntimeException("Configurazione non valorizzata per il media ");
        }

        switch (cfgPianoMedia.getTipoDataInizio()){
            case CAMPAGNA:
                if ( dataInizio == null )
                    throw new RuntimeException("Data inizio pianificazione non valorizzata");
                return dataInizio;
            case GIORNI_PRIMA:
                if ( cfgPianoMedia.getScostamento() == null ){
                    throw new RuntimeException(String.format("Scostamento non valorizzato per il media %s", cfgPianoMedia.getSupportoMedia().getDescrizione()));
                }
                return DateTimeUtils.addDaysToDate(dataInizio, -cfgPianoMedia.getScostamento());
            default:
                throw new IllegalAccessException("Configurazione non valida per data inizio piano media");
        }
    }

    Date calculateDataFine(Date dataInizio, Date dataFine, CfgPianoMediaEntity cfgPianoMedia) {
        if ( cfgPianoMedia == null ){
            throw new RuntimeException("Configurazione non valorizzata per il media ");
        }

        switch (cfgPianoMedia.getTipoDataFine()) {
            case CAMPAGNA:
                if ( dataFine == null ){
                    throw new RuntimeException("Data fine pianificazione non valorizzata");
                }
                return dataFine;
            case GIORNI_PRIMA:
                if ( dataFine == null ){
                    throw new RuntimeException("Data fine pianificazione non valorizzata");
                }
                if ( cfgPianoMedia.getDurata() == null ){
                    throw new RuntimeException(String.format("Giorni prima non valorizzato per il media %s", cfgPianoMedia.getSupportoMedia().getDescrizione()));
                }
                return DateTimeUtils.addDaysToDate(dataFine, -cfgPianoMedia.getDurata());
            case DURATA:
                if ( dataInizio == null ){
                    throw new RuntimeException("Data inizio pianificazione non valorizzata");
                }
                if ( cfgPianoMedia.getDurata() == null ){
                    throw new RuntimeException(String.format("Durata non valorizzata per il media %s", cfgPianoMedia.getSupportoMedia().getDescrizione()));
                }
                return DateTimeUtils.addDaysToDate(dataInizio, cfgPianoMedia.getDurata());
            default:
                throw new RuntimeException(String.format("Configurazione non valida per data fine piano media %", cfgPianoMedia.getTipoDataFine()));
        }
    }


    private void calcolaStatiConsentiti(@NonNull final PianoMediaEntity pianoMedia, @NonNull final String user) {
        try {
            pianoMedia.setStatiConsentiti(
                    creaPromozioneService.findAllPromoAllowedStateByChannel(pianoMedia.getCanale()).stream()
                            .map(statoConsentito -> {
                                final PianoMediaStatiConsentitiEntity stato = (PianoMediaStatiConsentitiEntity) AuditLogFiller
                                        .fillAuditLogFields(new PianoMediaStatiConsentitiEntity(), user);
                                stato.setStatoPromozioneEntity(statoConsentito.getStatoPromozioneEntity());
                                stato.setPianoMediaEntity(pianoMedia);
                                return stato;
                            }).collect(Collectors.toSet()));
        } catch (final Exception e) {
            log.error(String.format("Error find CfgStatiCanaleConsentEntity list with canalePromozioneEntity id %s",
                    pianoMedia.getCanale().getId()), e);
        }
    }

    private void calcolaStatiTransizione(@NonNull final PianoMediaEntity pianoMedia, @NonNull final String user) {
        try {
            pianoMedia.setStatiTransizione(creaPromozioneService
                    .findAllPromoTransitionByChannel(pianoMedia.getCanale()).stream().map(config -> {
                        final PianoMediaStatiTransizioneEntity transizione = (PianoMediaStatiTransizioneEntity) AuditLogFiller
                                .fillAuditLogFields(new PianoMediaStatiTransizioneEntity(), user);
                        transizione.setDescrizione(config.getDescrizione());
                        transizione.setStatoTransizione(config.getStatoTransizione());
                        transizione.setStatoPromozione(config.getStatoPromozione());
                        transizione.setPianoMediaEntity(pianoMedia);
                        transizione.setFlagCompratore(config.getFlagCompratore());
                        transizione.setFlagControlli(config.getFlagControlli());
                        transizione.setFlagPubblica(config.getFlagPubblica());
                        transizione.setStatoErrore(config.getStatoErrore());
                        transizione.setFlagAutomatico(config.getFlagAutomatico());
                        return transizione;
                    }).collect(Collectors.toSet()));
        } catch (final Exception e) {
            log.error(String.format("Error find CfgStatiTransizioniEntity list with canalePromozioneEntity id %s",
                    pianoMedia.getCanale().getId()), e);
        }
    }
}
