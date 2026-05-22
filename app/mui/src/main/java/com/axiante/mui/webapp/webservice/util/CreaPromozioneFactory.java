package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.common.promo.grid.ComboBoxValues;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.promo.params.DataTypeParams;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.DbPromoConstants;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.utils.AuditLogFiller;
import com.axiante.mui.dbpromo.business.utils.ComboBoxFactory;
import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiConsentitiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.GruppoPromozioniService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.axiante.mui.persistence.entity.GroupEntity;
import com.axiante.mui.persistence.service.MuiService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Factory per trasformare un nodo json in un oggetto crea promozione.
 *
 * @author marco
 */
@Slf4j
@Dependent
public class CreaPromozioneFactory {
    @Inject
    CanalePromozioneService canalePromozioneService;

    @Inject
    CreatePromotionService creaPromozioneService;

    @Inject
    GruppoPromozioniService gruppoPromozioniService;

    @Inject
    private StatoPromoService statoPromozioneService;

    @Inject
    Instance<MuiService> muiServiceInstance;
    @Inject
    Instance<PromoService> promoService;

    @Inject
    private ComboBoxFactory comboFactory;

    private static final Integer EMPTY_FIELDS = 7;
    private final String[] CAMPI = {"anno", "descrizione", "gruppo", "canale", "dataInizio", "dataFine", "messaggio", "slotId", "userId"};

    private final DateTimeUtils dateTimeUtils = new DateTimeUtils();

    /**
     * Deserializza json in oggetto crea promozione
     *
     * @param node json con i dati
     * @return
     */
    public CreaPromozioneEntity build(JsonNode node) {
        final CreaPromozioneEntity entity = find(node);
        return entity == null ? null : build(entity, node);
    }

    /**
     * Setta i valori dei campi dell'oggetto prelevandoli dal nodo json
     *
     * @param entity oggetto su cui operare
     * @param node   il json che rappresenta l'oggetto
     * @return entity aggiornata
     */
    private CreaPromozioneEntity build(@NonNull CreaPromozioneEntity entity, @NonNull JsonNode node) {
        for (final String campo : CAMPI) {
            final JsonNode nodo = node.get(campo);
            if (nodo != null) {
                final DBPromoAgCell cell = DbPromoAgCellUtils.decode(nodo);
                String value;
                if (cell != null) {
                    value = DbPromoAgCellUtils.getValue(cell);
                    if (StringUtils.isBlank(value)) {
                        value = null;
                    }
                    switch (campo) {
                        case "anno":
                            entity.setAnno(value);
                            break;
                        case "descrizione":
                            entity.setDescrizione(value);
                            break;
                        case "gruppo":
                            entity.setGruppoPromozioneEntity(value != null
                                    ? gruppoPromozioniService.findById(Long.parseLong(value)) : null);
                            break;
                        case "canale":
                            entity.setCanalePromozioneEntity(value != null
                                    ? canalePromozioneService.findById(Long.parseLong(value)) : null);
                            break;
                        case "dataInizio":
                            entity.setDataInizio(value != null ? dateTimeUtils.excelToDate(value) : null);
                            break;
                        case "dataFine":
                            entity.setDataFine(value != null ? dateTimeUtils.excelToDate(value) : null);
                            break;
                        case "messaggio":
                            entity.setMessaggio(value);
                            break;
                        case "slotId":
                            entity.setSlotId(value);
                            break;
                        case "userId":
                            entity.setUserId(value);
                            break;
                        default:
                            log.debug("campo sconosciuto " + campo);
                            break;
                    }
                }
            }
        }
        return entity;
    }

    /**
     * Recupera la riga di MUI_CREA_PROMOZIONE. Se non presente a db o in caso di errore ne crea una
     * ex novo.
     *
     * @param node nodo JSON dal quale ricavare userId e slotId
     * @return MUI_CREA_PROMOZIONE recuperata o creata ex novo
     */
    private CreaPromozioneEntity find(JsonNode node) {
        final String slotId = node.get("slotId") != null ? DbPromoAgCellUtils.getValue(node.get("slotId")) : null;
        final String userId = node.get("userId") != null ? DbPromoAgCellUtils.getValue(node.get("userId")) : null;
        if ((slotId == null) || (userId == null)) {
            return null;
        }
        CreaPromozioneEntity entity = null;
        try {
            entity = creaPromozioneService.findByUserIdAndSlotId(userId, slotId);
        } catch (final NoResultException managed) {
            // Non ci sono righe a DB, viene creata
            entity = (CreaPromozioneEntity) AuditLogFiller.fillAuditLogFields(new CreaPromozioneEntity(), userId);
            entity.setUserId(userId);
            entity.setSlotId(slotId);
        } catch (final Exception ex) {
            log.error("Errore database durante il recupero delle informazioni", ex);
        }
        if (entity == null) {
            entity = new CreaPromozioneEntity();
            entity.setUserId(userId);
            entity.setSlotId(slotId);
        }
        return entity;
    }

    /**
     * Calcola e setta il codice della promozione per la testata
     *
     * @param testata
     * @param user
     */
    private void calculatePromoCode(@NonNull final PromozioneTestataEntity testata, @NonNull final String user) {
        CanaleLastProgEntity progressivo = creaPromozioneService
                .findCanaleLastProgEntityByYearAndChannel(testata.getAnno(), testata.getMuiCanalePromozione());
        if (testata.getMuiCanalePromozione().getCodeRangeMin() == null) {
            String messaggio = String.format("Canale %d non inizializzato correttamente: manca CodeRangeMin",
                    testata.getMuiCanalePromozione().getCodiceCanale());
            log.error(messaggio);
            throw new IllegalStateException(messaggio);
        }
        if (testata.getMuiCanalePromozione().getCodeRangeMax() == null) {
            String messaggio = String.format("Canale %d non inizializzato correttamente: manca CodeRangeMax",
                    testata.getMuiCanalePromozione().getCodiceCanale());
            log.error(messaggio);
            throw new IllegalStateException(messaggio);
        }
        String promocode = null;
        if (progressivo == null) {
            progressivo = new CanaleLastProgEntity();
            progressivo.setAnno(Long.parseLong(testata.getAnno()));
            progressivo.setMuiCanalePromozione(testata.getMuiCanalePromozione());
            progressivo.setLastProgressivo(testata.getMuiCanalePromozione().getCodeRangeMin());
        }
        // vuol dire che non ho creato il canale ex novo e quindi devo verificare
        Long lastProgressivo = progressivo.getLastProgressivo();
        if (lastProgressivo == null) {
            log.error(String.format("Il canale %s non e' correttamente inizializzato: progressivo mancante",
                    progressivo.getMuiCanalePromozione().getDescrizione()));
        }
        if ((lastProgressivo >= testata.getMuiCanalePromozione().getCodeRangeMin()) && (lastProgressivo < testata.getMuiCanalePromozione().getCodeRangeMax())) {
            lastProgressivo += 1;
        }
        if (lastProgressivo > testata.getMuiCanalePromozione().getCodeRangeMax()) {
            throw new IllegalStateException(String.format("Progressivi esauriti per il canale %s", testata.getMuiCanalePromozione().getCodiceCanale()));
        }
        if (lastProgressivo < testata.getMuiCanalePromozione().getCodeRangeMin()) {
            throw new IllegalStateException(String.format("Errore di configurazione: il progressivo minimo per il canale %d e' maggiore del progressivo corrente",
                    testata.getMuiCanalePromozione().getCodeRangeMin()));
        }
        progressivo.setLastProgressivo(lastProgressivo);
        try {
            creaPromozioneService.persistCanaleLastProgEntity(progressivo, user);
            promocode = String.format("%s_%s", testata.getAnno(), lastProgressivo);
        } catch (final Exception e) {
            log.error("Error persist CanaleLastProgEntity", e);
        }

        testata.setCodicePromozione(promocode);
    }

    private void calculateOwnership(@NonNull final PromozioneTestataEntity testata, @NonNull final UserDTO user) {
        // trovo tutti i codici compratore dei gruppi utente che hanno flag W
        BiFunction<String, Long, Boolean> calcolaPropagazione = (codiceGruppo, codiceCanale) -> {
            try {
                return muiServiceInstance.get().findByCodiceGruppoAndCodiceCanale(codiceGruppo, codiceCanale).getPropagaOwner();
            } catch (NoResultException | NonUniqueResultException e) {
                if (log.isDebugEnabled()) {
                    log.debug(String.format("GruppoCanale (%s,%d) non correttamente configurato ", codiceGruppo, codiceCanale), e);
                }
                log.warn(String.format("rimozione del gruppo %s perche' non correttamente configurato con il canale %d", codiceGruppo, codiceCanale));
            } catch (Exception e) {
                log.warn(String.format("Errore nel recupero del (gruppo, canale)=(%s,%d)", codiceGruppo, codiceCanale));
            }
            return false;
        };
        final Set<GroupEntity> groups = user.getUser().getGruppi();
        if ((groups != null) && !groups.isEmpty()) {
            final List<String> codes = groups.stream().map(GroupEntity::getCodiceGruppo).filter(s -> calcolaPropagazione.apply(s, testata.getMuiCanalePromozione().getCodiceCanale())).collect(Collectors.toList());
            //			Iterator<String> iterator = codes.iterator();
            //			while(iterator.hasNext()){
            //				String gruppo = iterator.next();
            //				try {
            //					GruppoCanaleEntity e =
            // muiServiceInstance.get().findByCodiceGruppoAndCodiceCanale(gruppo,
            // testata.getMuiCanalePromozione().getCodiceCanale());
            //					if(!e.getPropagaOwner()){
            //						iterator.remove();
            //					}
            //				} catch (NoResultException| NonUniqueResultException e){
            //					if(log.isDebugEnabled()){
            //						log.debug(String.format("GruppoCanale (%s,%d) non correttamente configurato ",
            // gruppo,testata.getMuiCanalePromozione().getCodiceCanale()), e);
            //					}
            //					log.warn(String.format("rimozione del gruppo %d perche' non correttamente configurato
            // con il canale %s", gruppo,testata.getMuiCanalePromozione().getCodiceCanale()));
            //					//rimuovo il codice gruppo perche' non configurato
            //					iterator.remove();
            //				}
            //			}
            if (!codes.isEmpty()) {
                List<String> codiciCompratore = muiServiceInstance.get().findAllCodiciCompratoreByCodiciGruppoAndTipoAccesso(codes, PianificazioneSecurityEnum.WRITE);
                if (!codiciCompratore.isEmpty()) {
                    List<CompratoreEntity> compratori = promoService.get().findAllBuyersByCodes(codiciCompratore);
                    // setto gli owner della testata
                    compratori.forEach(testata::addOwner);
                }
            }
        }
    }

    /**
     * Calcola in nuovo set di stati promozionali da aggiungere alla testata
     *
     * @param testata
     * @param user
     * @return
     */
    private void calculateStateSet(@NonNull final PromozioneTestataEntity testata, @NonNull final String user) {
        final Set<PromozioneStatoEntity> set = new HashSet<>();
        try {
            final PromozioneStatoEntity stato = (PromozioneStatoEntity) AuditLogFiller.fillAuditLogFields(new PromozioneStatoEntity(), user);
            stato.setDataInizioStato(Calendar.getInstance().getTime());
            stato.setDataFineStato(null);
            set.add(stato);
            final StatoPromozioneEntity statoPromozioneEntity = statoPromozioneService.findByCode(PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey());
            if (statoPromozioneEntity == null) {
                throw new RuntimeException("Stato Promozione Entity not found");
            }
            stato.setStatoPromozioneEntity(statoPromozioneEntity);

            stato.setPromozioneTestataEntity(testata);
            set.add(stato);
        } catch (final Exception e) {
            log.error(String.format("Error find StatoPromozioneEntity with codiceStato %s", PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey()), e);
        }
        testata.setPromozioneStatoEntities(set);
    }

    /**
     * Calcola il set di negozi-promo da aggiungere alla testata promozionale
     *
     * @param testata
     * @param user
     * @return
     */
    private void calculateShopSet(@NonNull final PromozioneTestataEntity testata, @NonNull final String user) {
        final CanalePromozioneEntity canale = testata.getMuiCanalePromozione();
        final Date dataInizio = testata.getDataInizio();
        final Date dataFine = testata.getDataFine();
        try {
            final Set<PromozioneNegozioEntity> promoNegozi = creaPromozioneService.findChannelShopsByChannel(canale).stream()
                    .filter(cfg -> (!Objects.nonNull(cfg.getNegozioEntity().getChiusura()) && (cfg.getNegozioEntity().getPuntoVenditaChiuso() != 1)))
                    .map(shop -> {
                        final PromozioneNegozioEntity negozio = (PromozioneNegozioEntity) AuditLogFiller.fillAuditLogFields(new PromozioneNegozioEntity(), user);
                        negozio.setDataInizio(dataInizio);
                        negozio.setDataFine(dataFine);
                        negozio.setDefaultFlag(shop.getDefaultFlag());
                        negozio.setSelezioneFlag(shop.getDefaultFlag());
                        negozio.setPromozioneTestataEntity(testata);
                        negozio.setNegozioEntity(shop.getNegozioEntity());
                        return negozio;
                    }).collect(Collectors.toSet());
            testata.setPromozioneNegozioEntities(promoNegozi);
        } catch (final Exception e) {
            log.error(String.format("Error find CfgCanaleNegoziEntity list with canalePromozioneEntity id %s", canale.getId()), e);
        }
    }

    /**
     * Calcola le meccaniche disponibili per il canale promozionale e le aggiunge alla testata.
     *
     * @param testata
     * @param user
     * @return
     */
    private void calculatePromoMeccaniche(@NonNull final PromozioneTestataEntity testata, @NonNull final String user) {
        try {
            testata.setPromozioneMeccanicheEntities(creaPromozioneService
                    .findEnabledChannelMechanicByChannel(testata.getMuiCanalePromozione()).stream()
                    .map(mechanic -> {
                        final PromozioneMeccanicheEntity meccanica = (PromozioneMeccanicheEntity) AuditLogFiller.fillAuditLogFields(new PromozioneMeccanicheEntity(), user);
                        meccanica.setMeccanicheEntity(mechanic.getMeccanicheEntity());
                        meccanica.setPromozioneTestataEntity(testata);
                        return meccanica;
                    }).collect(Collectors.toSet()));
        } catch (final Exception e) {
            log.error(String.format("Error find CfgCanaleNegoziEntity list with canalePromozioneEntity id %s", testata.getMuiCanalePromozione().getId()), e);
        }
    }

    /**
     * Recupera il set di pianificazioni valido per le date passate come parametro
     *
     * @param dataInizio
     * @param dataFine
     * @return
     */
    private CfgSetPianificazioneEntity findSetPianificazione(@NonNull final Date dataInizio, @NonNull final Date dataFine) {
        CfgSetPianificazioneEntity entity = null;
        try {
            entity = creaPromozioneService.findByLockedAndDataInizio(dataInizio);
            if (entity == null) {
                log.warn(String.format("Not found any CfgSetPianificazioneEntity with dataInizio %s and dataFine %s", dataInizio, dataFine));
            }
        } catch (final Exception e) {
            log.error(String.format("Error looking up CfgSetPianificazioneEntity with dataInizio %s and dataFine %s", dataInizio, dataFine), e);
        }
        return entity;
    }

    /**
     * Calcola gli stati consentiti per la testata promozionales
     *
     * @param testata
     * @param user
     * @return
     */
    private void calcolaStatiConsentiti(@NonNull final PromozioneTestataEntity testata, @NonNull final String user) {
        try {
            testata.setPromoStatiConsentitiEntities(creaPromozioneService
                    .findAllPromoAllowedStateByChannel(testata.getMuiCanalePromozione()).stream()
                    .map(statoConsentito -> {
                        final PromoStatiConsentitiEntity stato = (PromoStatiConsentitiEntity) AuditLogFiller.fillAuditLogFields(new PromoStatiConsentitiEntity(), user);
                        stato.setStatoPromozioneEntity(statoConsentito.getStatoPromozioneEntity());
                        stato.setPromozioneTestataEntity(testata);
                        return stato;
                    }).collect(Collectors.toSet()));
        } catch (final Exception e) {
            log.error(String.format("Error find CfgStatiCanaleConsentEntity list with canalePromozioneEntity id %s", testata.getMuiCanalePromozione().getId()), e);
        }
    }

    /**
     * Calcola le transizioni di stato per la testata promozionale
     *
     * @param testata
     * @param user
     * @return
     */
    private void calcolaStatiTransizione(@NonNull final PromozioneTestataEntity testata, @NonNull final String user) {
        try {
            testata.setPromoStatiTransizioneEntities(creaPromozioneService
                    .findAllPromoTransitionByChannel(testata.getMuiCanalePromozione()).stream()
                    .map(config -> {
                        final PromoStatiTransizioneEntity transizione = (PromoStatiTransizioneEntity) AuditLogFiller.fillAuditLogFields(new PromoStatiTransizioneEntity(), user);
                        transizione.setDescrizione(config.getDescrizione());
                        transizione.setStatoTransizione(config.getStatoTransizione());
                        transizione.setStatoPromozione(config.getStatoPromozione());
                        transizione.setPromozioneTestataEntity(testata);
                        transizione.setFlagCompratore(config.getFlagCompratore());
                        transizione.setFlagControlli(config.getFlagControlli());
                        transizione.setFlagPubblica(config.getFlagPubblica());
                        transizione.setStatoErrore(config.getStatoErrore());
                        transizione.setFlagAutomatico(config.getFlagAutomatico());
                        return transizione;
                    }).collect(Collectors.toSet()));
        } catch (final Exception e) {
            log.error(String.format("Error find CfgStatiTransizioniEntity list with canalePromozioneEntity id %s",
                    testata.getMuiCanalePromozione().getId()), e);
        }
    }

    public PromozioneTestataEntity build(@NonNull final PromozioneTestataEntity entity, @NonNull final JsonNode node,
                                         @NonNull final UserDTO user) {
        build(entity, node, user.getUser().getName());
        calculateOwnership(entity, user);
        return entity;
    }

    /**
     * A partire da utente e riga, preleva l'oggetto dal database e crea un oggetto testata da
     * salvare. Se l'oggetto DB non viene trovato, viene deserializzato il nodo json
     *
     * @param testata
     * @param node
     * @param userName
     * @return
     */
    public PromozioneTestataEntity build(@NonNull final PromozioneTestataEntity testata, @NonNull final JsonNode node,
                                         @NonNull final String userName) {
        CreaPromozioneEntity creaPromo = find(node);
        if ((creaPromo.getCodiceUtenteInserimento() == null) && (creaPromo.getCodiceUtenteAggiornamento() == null)) {
            // creata a mano: leggi da json
            creaPromo = build(creaPromo, node);
        }
        testata.setAnno(creaPromo.getAnno());
        testata.setCreaPromozioneEntity(creaPromo);
        if (creaPromo.getDescrizione() != null) {
            testata.setDescrizione(creaPromo.getDescrizione().toUpperCase());
        }
        testata.setDataInizio(creaPromo.getDataInizio());
        testata.setDataFine(creaPromo.getDataFine());
        testata.setMuiCanalePromozione(creaPromo.getCanalePromozioneEntity());

        calculatePromoCode(testata, userName);
        calculateStateSet(testata, userName);
        testata.setSemestre(dateTimeUtils.calculateSemester(testata.getAnno(), testata.getDataInizio()));
        // CodicePromozione could be null; the NullPointerException is catched on the
        // caller
        testata.setDescrizioneEstesa(dateTimeUtils.calculateExtendedDescription(testata.getCodicePromozione(),
                testata.getDataInizio(), testata.getDataFine(), testata.getDescrizione()));
        calculateShopSet(testata, userName);
        // #4997: se il flag FL_MECCANICA_SINGOLA è attivo sul canale, NON aggiungo meccaniche alla
        // testata
        if (!Boolean.TRUE.equals(testata.getMuiCanalePromozione().getFlMeccanicaSingola())) {
            calculatePromoMeccaniche(testata, userName);
        }
        testata.setMuiCfgSetPianificazione(findSetPianificazione(testata.getDataInizio(), testata.getDataFine()));
        if (testata.getMuiCfgSetPianificazione() != null) {
            calcolaStatiConsentiti(testata, userName);
            calcolaStatiTransizione(testata, userName);
        }
        return testata;
    }

    /**
     * Serializza una entity
     *
     * @param entity
     * @param allGroups
     * @param canali
     * @return
     */
    public ObjectNode serialize(CreaPromozioneEntity entity, List<GruppoPromozioneEntity> allGroups,
                                List<CanalePromozioneEntity> canali) {
        final HashedMap<String, DBPromoAgCell> map = new HashedMap<>();
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        final String[] anni = {String.format("%s", currentYear), String.format("%s", currentYear + 1), ""};
        // tutti i gruppi che hanno almeno un canale associato
        final List<GruppoPromozioneEntity> groups = new ArrayList<>();
        if (allGroups != null) {
            groups.addAll(allGroups.stream()
                    .filter(group -> (group.getMuiCanalePromoziones() != null) && !group.getMuiCanalePromoziones().isEmpty())
                    .collect(Collectors.toList()));
        }

        final List<ComboBoxValues> gruppi = comboFactory.from(groups);

        map.put("slotId", DBPromoAgCell.builder().name("ID").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(entity.getSlotId() == null ? "" : entity.getSlotId()).build());

        map.put("anno", DBPromoAgCell.builder().name("Anno").editable(true).type(DBPromoCellTypeEnum.PICKLIST.getType())
                .value(entity.getAnno() == null ? "" : entity.getAnno()).picklistValues(anni).build());

        map.put("descrizione", DBPromoAgCell.builder().name("Descrizione").editable(true).type(DBPromoCellTypeEnum.STRING.getType())
                .value(entity.getDescrizione() != null ? entity.getDescrizione() : "")
                .dataTypeParams(DataTypeParams.builder().length(40).build()).build());

        map.put("dataInizio", DBPromoAgCell.builder().name("Data Inizio").editable(true).type(DBPromoCellTypeEnum.DATE.getType())
                .value(entity.getDataInizio() != null ? dateTimeUtils.toExcelDate(entity.getDataInizio()) : "").build());

        map.put("dataFine", DBPromoAgCell.builder().name("Data Fine").editable(true).type(DBPromoCellTypeEnum.DATE.getType())
                .value(entity.getDataFine() != null ? dateTimeUtils.toExcelDate(entity.getDataFine()) : "").build());

        map.put("messaggio", DBPromoAgCell.builder().name("Messaggio").editable(false).type(DBPromoCellTypeEnum.STRING.getType())
                .value(entity.getMessaggio() != null ? entity.getMessaggio() : "").build());

        map.put("gruppo", DBPromoAgCell.builder().name("Gruppo").editable(true).type(DBPromoCellTypeEnum.COMBOBOX.getType())
                .value(entity.getGruppoPromozioneEntity() != null ? "" + entity.getGruppoPromozioneEntity().getId() : null)
                .comboBoxValues(gruppi).build());

        map.put("canale", DBPromoAgCell.builder().name("Canale").editable(true).type(DBPromoCellTypeEnum.COMBOBOX.getType())
                .value(entity.getCanalePromozioneEntity() != null ? "" + entity.getCanalePromozioneEntity().getId() : null)
                .comboBoxValues(entity.getGruppoPromozioneEntity() == null
                        ? null : comboFactory.from(availableChannels(entity.getGruppoPromozioneEntity(), canali))).build());

        map.put("userId", DBPromoAgCell.builder().name("userId").type(DBPromoCellTypeEnum.STRING.getType())
                .value(entity.getUserId() == null ? "" : entity.getUserId()).build());

        return JsonUtils.getMapper().valueToTree(map);
    }

    public String createRowData(String numberOfSlots, List<CreaPromozioneEntity> listOfEntities, String userId,
                                List<CanalePromozioneEntity> canali, List<Long> creaPromoCanali) throws Exception {
        final ArrayNode arrayNode = JsonUtils.getMapper().createArrayNode();
        final String json = "";
        final int rowDataSlots = Integer.parseInt((numberOfSlots != null) && !numberOfSlots.isEmpty() ? numberOfSlots : "0");

        final Function<CreaPromozioneEntity, Integer> toSlotId = i -> {
            Integer ret = null;
            if (i.getSlotId() != null) {
                try {
                    ret = Integer.parseInt(i.getSlotId());
                } catch (final Exception e) {
                    log.warn("errore di conversione", e);
                    ret = 0;
                }
            }
            return ret;
        };

        final List<GruppoPromozioneEntity> availableGroups = availableGroups(canali, creaPromoCanali);
        final List<ComboBoxValues> comboBoxValues = comboFactory.from(availableGroups);

        // se ho dati allora li inserisco
        // 1: ordinare per slot id
        final List<CreaPromozioneEntity> list = listOfEntities.stream().sorted(Comparator.comparing(CreaPromozioneEntity::getSlotId)).collect(Collectors.toList());
        IntStream.rangeClosed(1, rowDataSlots).forEach(i -> {
            final CreaPromozioneEntity entity = list.stream().filter(element -> i == toSlotId.apply(element)).findFirst().orElse(null);
            if (entity == null) {
                // riga vuota
                arrayNode.add(getEmptyRow(userId, String.format("%03d", i), comboBoxValues)); // slots start at 1
            } else if (isWorkable(entity, canali, availableGroups)) {
                // serializza
                final List<CanalePromozioneEntity> creatableChannels = canali == null ? null : canali.isEmpty()
                        ? Collections.emptyList() : canali.stream()
                        .filter(c -> creaPromoCanali.contains(c.getCodiceCanale())).collect(Collectors.toList());
                arrayNode.add(serialize(entity, availableGroups, creatableChannels));
            }
        });

        if (arrayNode.size() == 0) {
            return null;
        }

        try {
            final ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.set(DbPromoConstants.ROW_DATA, arrayNode);
            return JsonUtils.getMapper().writeValueAsString(objectNode);
        } catch (final JsonProcessingException e) {
            log.error("Error serializing row data", e);
        }

        return json;
    }

    private boolean isWorkable(CreaPromozioneEntity entity, List<CanalePromozioneEntity> canali, List<GruppoPromozioneEntity> gruppi) {
        if (canali == null) {
            return true;
        }
        final boolean channelValid = (entity.getCanalePromozioneEntity() == null)
                || canali.stream().map(CanalePromozioneEntity::getCodiceCanale).collect(Collectors.toList())
                .contains(entity.getCanalePromozioneEntity().getCodiceCanale());
        final boolean groupValid = (entity.getGruppoPromozioneEntity() == null)
                || gruppi.stream().map(GruppoPromozioneEntity::getCodiceGruppo).collect(Collectors.toList())
                .contains(entity.getGruppoPromozioneEntity().getCodiceGruppo());
        return channelValid && groupValid;
    }

    /**
     * Crea una riga vuota della tabella assegnando slot id e user id come da parametri
     *
     * @param userId
     * @param slotId
     * @param comboBoxValues
     * @return
     * @throws Exception
     */
    private ObjectNode getEmptyRow(@NonNull final String userId, @NonNull final String slotId, List<ComboBoxValues> comboBoxValues) {

        final HashedMap<String, DBPromoAgCell> dbPromoAgCellRowMap = new HashedMap<>();
        final String[] fields = {"slotId", "anno", "descrizione", "dataInizio", "dataFine", "messaggio", "canale", "gruppo"};
        final DBPromoCellTypeEnum[] types = {DBPromoCellTypeEnum.STRING, DBPromoCellTypeEnum.PICKLIST,
                DBPromoCellTypeEnum.STRING, DBPromoCellTypeEnum.DATE, DBPromoCellTypeEnum.DATE, DBPromoCellTypeEnum.STRING,
                DBPromoCellTypeEnum.COMBOBOX, DBPromoCellTypeEnum.COMBOBOX};

        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        final String[] anni = {String.format("%s", currentYear), String.format("%s", currentYear + 1), ""};

        for (int i = 0; i < fields.length; ++i) {
            dbPromoAgCellRowMap.put(fields[i], DBPromoAgCell.builder().name(fields[i]).type(types[i].getType())
                    .value(fields[i].equals("slotId") ? slotId : "").picklistValues(fields[i].equals("anno") ? anni : null)
                    .comboBoxValues(fields[i].equals("gruppo") ? comboBoxValues : null).editable(!fields[i].equals("slotId") && !fields[i].equals("messaggio")).dataTypeParams(fields[i].equals("descrizione") ? DataTypeParams.builder().length(40).build() : null).build());
        }
        dbPromoAgCellRowMap.put("userId", DBPromoAgCell.builder().name("userId").type(DBPromoCellTypeEnum.STRING.getType())
                .value(userId).build());

        return JsonUtils.getMapper().valueToTree(dbPromoAgCellRowMap);
    }

    /**
     * Se tutti i campi di crea promo sono vuoti allora la riga e' vuota
     *
     * @param node
     * @return
     */
    public boolean isRowNodeEmpty(JsonNode node) {
        return getEmptyCells(node).size() == EMPTY_FIELDS;
    }

    /**
     * Se tutte le celle sono popolate o se tutte meno il messaggio sono popolate allora true
     *
     * @param node
     * @return
     */
    public boolean isAllCellsPopulated(JsonNode node) {
        final List<String> emptyCells = getEmptyCells(node);
        return emptyCells.isEmpty() || ((emptyCells.size() == 1) && emptyCells.contains("messaggio"));
    }

    /**
     * Scorre la riga alla ricerca dei campi di crea promo e ritorna quelli senza unvalore NB: se il
     * valore e' stringa vuota (== "" o con soli spazi) o null allora il campo e' vuoto
     *
     * @param json
     * @return
     */
    public List<String> getEmptyCells(JsonNode json) {
        return getEmptyCells(json, false);
    }

    public List<String> getEmptyCells(JsonNode json, Boolean noMessage) {
        // { "anno", "descrizione", "gruppo", "canale", "dataInizio", "dataFine",
        // "messaggio" });
        final Stream<String> stream = Arrays.stream(CAMPI).filter(c -> !c.equals("slotId")).filter(c -> !c.equals("userId"));
        final List<String> fields = noMessage ? stream.filter(c -> !c.equals("messaggio")).collect(Collectors.toList()) : stream.collect(Collectors.toList());
        final List<String> emptyValues = new ArrayList<>();
        for (final String field : fields) {
            if (StringUtils.isEmpty(DbPromoAgCellUtils.getValue(DbPromoAgCellUtils.decode(json.get(field))))) {
                emptyValues.add(field);
            }
        }
        return emptyValues;
    }

    /**
     * Cancella la riga dalla tabella mui_crea_promozione. La cancellazione e' una update di tutti i
     * campi (tranne id e user) con valore a null
     *
     * @param node
     * @param user
     * @throws Exception
     */
    public void deleteRow(@NonNull final JsonNode node, @NonNull final String user) throws Exception {
        final CreaPromozioneEntity creaPromozione = cleanEntity(node);
        creaPromozioneService.persist(creaPromozione, user);
        DbPromoAgCellUtils.putValue((ObjectNode) node, "anno", "", true);
        DbPromoAgCellUtils.putValue((ObjectNode) node, "descrizione", "", true);
        DbPromoAgCellUtils.putValue((ObjectNode) node, "gruppo", "", true);
        DbPromoAgCellUtils.putValue((ObjectNode) node, "canale", "", true);
        DbPromoAgCellUtils.putValue((ObjectNode) node, "dataInizio", "", true);
        DbPromoAgCellUtils.putValue((ObjectNode) node, "dataFine", "", true);
        DbPromoAgCellUtils.putValue((ObjectNode) node, "messaggio", "", true);
    }

    /**
     * Helper: crea una entity MUI CREA PROMOZIONE vuota tranne che per l'utente e lo slot. Se
     * l'entita' e' presente nel database allora recupera l'entita' da db e la aggiorna, altrimenti ne
     * crea una nuova
     *
     * @param node
     * @return
     */
    private CreaPromozioneEntity cleanEntity(@NonNull final JsonNode node) {
        DBPromoAgCell cell = DbPromoAgCellUtils.decode(node.get("userId"));
        final String userId = cell != null ? DbPromoAgCellUtils.getValue(cell) : node.get("userId").asText();
        cell = DbPromoAgCellUtils.decode(node.get("slotId"));
        final String slotId = cell != null ? DbPromoAgCellUtils.getValue(cell) : node.get("slotId").asText();
        CreaPromozioneEntity creaPromozioneEntity = null;
        try {
            creaPromozioneEntity = creaPromozioneService.findByUserIdAndSlotId(userId, slotId);
        } catch (final Exception e) {
            log.error("error retrieving a canale promozione entity", e);
        }
        if (creaPromozioneEntity == null) {
            creaPromozioneEntity = new CreaPromozioneEntity();
            creaPromozioneEntity.setSlotId(userId);
            creaPromozioneEntity.setUserId(slotId);
        }
        creaPromozioneEntity.setCodiceUtenteAggiornamento(null);
        creaPromozioneEntity.setCodiceUtenteInserimento(null);
        creaPromozioneEntity.setDataInserimento(null);
        creaPromozioneEntity.setDataAggiornamento(null);
        creaPromozioneEntity.setAnno(null);
        creaPromozioneEntity.setDescrizione(null);
        creaPromozioneEntity.setDataInizio(null);
        creaPromozioneEntity.setDataFine(null);
        creaPromozioneEntity.setMessaggio(null);
        creaPromozioneEntity.setCanalePromozioneEntity(null);
        creaPromozioneEntity.setGruppoPromozioneEntity(null);

        return creaPromozioneEntity;
    }

    /**
     * Ritorna la lista dei gruppi disponibili, in base al filtro sui canali: - null, non viene
     * considerato e vengono restituiti tutti i gruppi - lista vuota, l'utente non ha visibilità sui
     * canali e viene restituita una lista vuota - lista popolata, viene applicato il filtro e vengono
     * restituiti solo i gruppi associali ai canali
     *
     * @param canali          filtro canali abilitati per un dato utente
     * @param creaPromoCanali codici canali con flag_create
     * @return lista gruppi disponibili
     */
    private List<GruppoPromozioneEntity> availableGroups(List<CanalePromozioneEntity> canali, List<Long> creaPromoCanali) {
        if ((canali != null) && canali.isEmpty()) {
            return Collections.emptyList();
        }
        final List<GruppoPromozioneEntity> allGroups = creaPromozioneService.getAllGroups();
        if (canali == null) {
            return allGroups.stream()
                    .filter(g -> (g != null) && (g.getMuiCanalePromoziones() != null)
                            && !g.getMuiCanalePromoziones().isEmpty()).collect(Collectors.toList());
        }
        final List<String> codiciGruppo = canali.stream()
                .filter(c -> creaPromoCanali.contains(c.getCodiceCanale()))
                .map(CanalePromozioneEntity::getGruppoPromozioneEntity).distinct()
                .map(GruppoPromozioneEntity::getCodiceGruppo).collect(Collectors.toList());
        return allGroups.stream()
                .filter(g -> (g != null) && (g.getMuiCanalePromoziones() != null)
                        && !g.getMuiCanalePromoziones().isEmpty() && codiciGruppo.contains(g.getCodiceGruppo()))
                .collect(Collectors.toList());
    }

    /**
     * Ritorna la lista dei canali disponibili per un dato gruppo, in base al filtro sui canali: -
     * null, non viene considerato e vengono restituiti tuttii canali per il gruppo - lista vuota,
     * l'utente non ha visibilità sui canali e viene restituita una lista vuota - lista popolata,
     * viene applicato il filtro e vengono restituiti solo i canali per il gruppo presenti nel filtro
     *
     * @param gruppoPromozioneEntity gruppo selezionato
     * @param canali                 filtro canali abilitati per un dato utente
     * @return lista gruppi disponibili
     */
    private List<CanalePromozioneEntity> availableChannels(@NonNull GruppoPromozioneEntity gruppoPromozioneEntity,
                                                           List<CanalePromozioneEntity> canali) {
        if (canali == null) {
            return new ArrayList<>(gruppoPromozioneEntity.getMuiCanalePromoziones());
        }

        if (canali.isEmpty()) {
            return Collections.emptyList();
        }

        final List<Long> codiciCanale = canali.stream().map(CanalePromozioneEntity::getCodiceCanale).collect(Collectors.toList());
        return gruppoPromozioneEntity.getMuiCanalePromoziones().stream()
                .filter(c -> codiciCanale.contains(c.getCodiceCanale())).collect(Collectors.toList());
    }
}
