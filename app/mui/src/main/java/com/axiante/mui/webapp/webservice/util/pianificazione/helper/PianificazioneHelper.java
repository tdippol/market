package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.business.utils.DefaultMessaggiHelper;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.TipoRigaService;
import com.axiante.mui.webapp.webservice.pojo.pianificazione.ItemPojo;
import com.axiante.mui.webapp.webservice.pojo.pianificazione.ItemRiferimento;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.PianificazioneEntityPredicates;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Dependent
@Slf4j
public class PianificazioneHelper {

	@Inject
	Instance<PromoService> promoServiceInstance;

	@Inject
	Instance<PianificazioneService> pianificazioneServiceInstance;

	@Inject
	Instance<PianificazioneValidatorUtil> validatorUtilInstance;

	@Inject
	Instance<VisualizzaPianificazioneHelper> visualizzaPianificazioneHelper;

	@Inject
	Instance<PromozionePianificazioneEntityHelper> promoPianificazioneEntityHelperInstance;

	@Inject
	Instance<PromoConfigurationHelper> configurationHelperInstance;

	@Inject
	Instance<TipoRigaService> tipoRigaServiceInstance;

	@Inject
	Instance<PianificazioneEntityPredicates> predicatesInstance;

	@Inject
	PianificazioneFactory pianificazioneFactory;

	@Inject
	private Instance<CfgConfHeaderService> confHeaderServiceInstance;

	@Inject
	transient private Instance<PianificazionePromoUtil> pianificazionePromoUtil;

	@Inject
	private Instance<LinkHelper> linkHelperInstance;

	@Inject
	private Instance<DefaultMessaggiHelper> defaultMessaggiHelpers;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Salvataggio di una nuova pianificazione
	 * @param testataEntity la promozione associata
	 * @param meccanicaEntity la meccanica associata
	 * @param items lista di elementi da inserire
	 * @param tipoElemento tipo elemento
	 * @param user utente che esegue l'operazione
	 * @return true se la pianificazione è stata salvata correttamente, false altrimenti
	 */
	public boolean savePromoPianificazione(PromozioneTestataEntity testataEntity,
			MeccanicheEntity meccanicaEntity,
			Set<ItemPojo> items,
			String tipoElemento,
			@NonNull String user) {
		return savePromoPianificazione(testataEntity, meccanicaEntity, items, tipoElemento, user, null);
	}
	/**
	 * Salvataggio di una nuova pianificazione come "figlia" di di un set o raggruppamento
	 * @param pianificazioneEntity la pianificazione padre (di tipo set o raggruppamento)
	 * @param items lista di elementi da inserire
	 * @param tipoElemento tipo elemento
	 * @param user utente che esegue l'operazione
	 * @return true se la pianificazione è stata salvata correttamente, false altrimenti
	 */
	public boolean savePromoAddPianificazione(PromozionePianificazioneEntity pianificazioneEntity, Set<ItemPojo> items,
											  String tipoElemento, @NonNull String user) {
		return savePromoAddPianificazione(pianificazioneEntity, items, tipoElemento, user, null);
	}

	public boolean savePromoPianificazione(PromozioneTestataEntity testataEntity,
										   MeccanicheEntity meccanicaEntity,
										   Set<ItemPojo> items,
										   String tipoElemento,
										   @NonNull String user,
										   String gruppoSelezionato) {
		final Long idSetPianificazione = testataEntity.getMuiCfgSetPianificazione().getId();
		final Map<String, Field> mappedFields = pianificazioneServiceInstance.get().getMappedFields();
		if (isMeccanicaOnLevelSet(meccanicaEntity, idSetPianificazione)) {
			log.debug("Inserimento pianificazione per una meccanica di livello 'SET'");
			return  savePromoPianificazioneRigaSet(testataEntity, meccanicaEntity, mappedFields, user, gruppoSelezionato);
		}

		PromoPianificazioneEnum tipoElementoEnum;
		try {
			tipoElementoEnum = PromoPianificazioneEnum.valueOf(tipoElemento);
		} catch (Exception ex) {
			final String msg = String.format("Errore; il tipo elemento %s non è riconosciuto", tipoElemento);
			log.error(msg, ex);
			return false;
		}

		final Set<ItemPojo> validItems = validatorUtilInstance.get().validateItems(testataEntity, meccanicaEntity, items, tipoElementoEnum);
		if ((tipoElementoEnum == PromoPianificazioneEnum.TOTALE && validItems == null) ||
				(tipoElementoEnum != PromoPianificazioneEnum.TOTALE && (validItems == null || validItems.isEmpty()))) {
			log.warn("Non ci sono items da inserire nella pianificazione associata alla promozione con id "
					+ testataEntity.getId());
			return false;
		}

		if (isMeccanicaOnLevelRaggruppamento(meccanicaEntity, idSetPianificazione)) {
			log.debug("Inserimento pianificazione per una meccanica di livello 'RAGGRUPPAMENTO'");
			return savePromoPianificazioneRigaRaggruppamento(testataEntity, meccanicaEntity, validItems, tipoElemento,
					mappedFields, user, gruppoSelezionato);

		}

		if (isMeccanicaOnLevelElemento(meccanicaEntity, idSetPianificazione)) {
			log.debug("Inserimento pianificazione per una meccanica di livello 'ELEMENTO'");
			return savePromoPianificazioneRigaElemento(testataEntity, meccanicaEntity, validItems, tipoElemento,
					mappedFields, user);
		}

		return false;
	}

	public boolean savePromoAddPianificazione(PromozionePianificazioneEntity pianificazioneEntity, Set<ItemPojo> items,
											  String tipoElemento, @NonNull String user, String gruppoSelezionato) {
		final PromozioneTestataEntity testataEntity = pianificazioneEntity.getPromozioneTestataEntity();
		final MeccanicheEntity meccanicaEntity = pianificazioneEntity.getMeccanicaEntity();
		final Map<String, Field> mappedFields = pianificazioneServiceInstance.get().getMappedFields();

		PromoPianificazioneEnum tipoElementoEnum;
		try {
			tipoElementoEnum = PromoPianificazioneEnum.valueOf(tipoElemento);
		} catch (Exception ex) {
			log.error(String.format("Errore; il tipo elemento %s non è riconosciuto", tipoElemento), ex);
			return false;
		}

		final Set<ItemPojo> validItems = validatorUtilInstance.get().validateItems(testataEntity, meccanicaEntity, items, tipoElementoEnum);
		if ((tipoElementoEnum == PromoPianificazioneEnum.TOTALE && validItems == null) ||
				(tipoElementoEnum != PromoPianificazioneEnum.TOTALE && (validItems == null || validItems.isEmpty()))) {
			log.warn(String.format("Non ci sono items da inserire nella pianificazione associata alla promozione con id %d",
					testataEntity.getId()));
			return false;
		}

		if (PlanningLevelEnum.SET.getCode().equals(pianificazioneEntity.getTipoRiga().getCodiceTipo())) {
			log.debug("Aggiunta riga pianificazione di tipo 'RAGGRUPPAMENTO' ad una pianificazione di tipo 'SET' esistente");
			return addRigaRaggruppamentoToRigaSet(pianificazioneEntity, testataEntity, meccanicaEntity, validItems,
					tipoElemento, mappedFields, user);
		}

		if (PlanningLevelEnum.RAGGRUPPAMENTO.getCode().equals(pianificazioneEntity.getTipoRiga().getCodiceTipo())) {
			log.debug("Aggiunta righe pianificazione di tipo 'ELEMENTO' ad una pianificazione di tipo 'RAGGRUPPAMENTO' esistente");
			return addRigheElementoToRigaRaggruppamento(pianificazioneEntity, testataEntity, meccanicaEntity,
					validItems, tipoElemento, mappedFields, user);
		}

		return false;
	}

	public boolean saveArticoliDaPromoRiferimento(@NonNull PromozioneTestataEntity testata, MeccanicheEntity meccanica,
												  List<ItemRiferimento> items, @NonNull String username) {
		if (!isMeccanicaOnLevelElemento(meccanica, testata.getMuiCfgSetPianificazione().getId())) {
			// Controllo che la meccanica sia pianificabile a livello ELEMENTO
			return false;
		}
		final Set<ItemPojo> itemsPojo = validatorUtilInstance.get().validateItems(testata, meccanica,
				items.stream().map(ItemRiferimento::getItem).collect(Collectors.toSet()),
				PromoPianificazioneEnum.ARTICOLO);
		if (itemsPojo == null || itemsPojo.isEmpty()) {
			return false;
		}
		try {
			// Keep only items that passed validation
			final Set<ItemRiferimento> validItems = items.stream().filter(i -> itemsPojo.contains(i.getItem()))
					.collect(Collectors.toSet());
			final Map<String, Field> mappedFields = pianificazioneServiceInstance.get().getMappedFields();
			List<PromozionePianificazioneEntity> pianificazioni = new ArrayList<>();
			for ( ItemRiferimento i : validItems ) {
				final PromozionePianificazioneEntity pianificazione = pianificazioneFactory.buildRigaElemento(testata, meccanica,
						PromoPianificazioneEnum.ARTICOLO.getTipoElemento(), i.getItem(), mappedFields, username);
				if (pianificazione.getDataInizio() == null) {
					pianificazione.setDataInizio(testata.getDataInizio());
				}
				if (pianificazione.getDataFine() == null) {
					pianificazione.setDataFine(testata.getDataFine());
				}
				if (!StringUtils.isBlank(i.getOfferta())) {
					pianificazione.setValore(new BigDecimal(i.getOfferta()));
				}
				fixDatePianificazioneBasedOnCanale(pianificazione, testata);
				if ( !validatePianificazioneWithParent(pianificazione, testata.getDataFine())){
					return false;
				}
				pianificazioni.add(pianificazione);
			}
			pianificazioneServiceInstance.get().persist(pianificazioni, pianificazioni.size());
			return true;
		} catch (Exception ex) {
			log.error(String.format("Error saving pianificazione for testata id %d", testata.getId()), ex);
			return false;
		}
	}

	/**
	 * Controlla se la meccanica associata al set pianificazione è di tipo 'SET'
	 * @param entity meccanica
	 * @param idSetPianificazione set pianificazione
	 * @return true se la meccanica è di tipo 'SET', false altrimenti
	 */
	public boolean isMeccanicaOnLevelSet(MeccanicheEntity entity, Long idSetPianificazione) {
		return isMeccanicaOnLevel(entity, idSetPianificazione, PlanningLevelEnum.SET);
	}

	/**
	 * Controlla se la meccanica associata al set pianificazione è di tipo 'RAGGRUPPAMENTO'
	 * @param entity meccanica
	 * @param idSetPianificazione set pianificazione
	 * @return true se la meccanica è di tipo 'RAGGRUPPAMENTO', false altrimenti
	 */
	public boolean isMeccanicaOnLevelRaggruppamento(MeccanicheEntity entity, Long idSetPianificazione) {
		return isMeccanicaOnLevel(entity, idSetPianificazione, PlanningLevelEnum.RAGGRUPPAMENTO);
	}

	/**
	 * Controlla se la meccanica associata al set pianificazione è di tipo 'ELEMENTO'
	 * @param entity meccanica
	 * @param idSetPianificazione set pianificazione
	 * @return true se la meccanica è di tipo 'RAGGRUPPAMENTO', false altrimenti
	 */
	public boolean isMeccanicaOnLevelElemento(MeccanicheEntity entity, Long idSetPianificazione) {
		return isMeccanicaOnLevel(entity, idSetPianificazione, PlanningLevelEnum.ELEMENTO);
	}

	/**
	 * Controlla se la riga di pianificazione può contenere o meno elementi di tipo omogeneo
	 * @param pianificazioneEntity la riga di pianificazione
	 * @return true se la pianificazione ha solo elementi omogenei, false altrimenti
	 */
	public boolean isTipoElementoOmogeneo(PromozionePianificazioneEntity pianificazioneEntity) {
		return validatorUtilInstance.get().validateOmogeneo(pianificazioneEntity.getPromozioneTestataEntity(),
				pianificazioneEntity.getMeccanicaEntity(), pianificazioneEntity.getNumRaggruppamento());
	}

	/**
	 * Controlla se è possibile aggiungere alla testata la meccanica indicata o se è stato raggiunto il numero di max Set
	 * @param testataEntity la promozione associata
	 * @param meccanicaEntity la meccanica associata
	 * @return true se è possibile aggiungere la meccanica, false altrimenti
	 */
	public boolean hasMeccanicaSetSlots(PromozioneTestataEntity testataEntity, MeccanicheEntity meccanicaEntity) {
		final CfgConfHeaderEntity confHeaderEntity = configurationHelperInstance.get()
				.getHeaderFromTestataAndMeccanica(testataEntity, meccanicaEntity);
		if (confHeaderEntity == null) {
			throw new IllegalArgumentException(String.format("Unable to find header for testata %s and meccanica %s",
					testataEntity.getId(), meccanicaEntity.getId()));
		}
		final Integer maxSet = confHeaderEntity.getMaxSet();
		if (maxSet == null) {
			return true;
		}
		return getUsedSetFromTestataWithMeccanica(testataEntity, meccanicaEntity) < maxSet;
	}

	/**
	 * Recupero lista elementi possibili per una data pianificazione
	 * @param pianificazioneEntity pianificazione
	 * @return lista elementi possibili
	 */
	public List<String> findTipoElementiForPianificazione(PromozionePianificazioneEntity pianificazioneEntity) {
		try {
			final List<CfgTipoElementoEntity> tipiElemento = pianificazioneEntity.getPromozioneTestataEntity()
					.getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream()
					.filter(h -> h.getMeccanicaEntity().equals(pianificazioneEntity.getMeccanicaEntity()))
					.flatMap(h -> h.getTipiElemento().stream())
					.distinct()
					.collect(Collectors.toList());
			final Integer raggr = pianificazioneEntity.getNumRaggruppamento() == null
					? 0
							: Float.valueOf(pianificazioneEntity.getNumRaggruppamento()).intValue();
			CfgTipoElementoEntity tipoElemento = tipiElemento.stream()
					.filter(e -> raggr.equals(e.getRaggruppamento()))
					.findAny()
					.orElse(null);
			if (tipoElemento == null) {
				tipoElemento = tipiElemento.stream().filter(e -> e.getRaggruppamento().equals(0)).findAny().orElse(null);
				if (tipoElemento == null) {
					log.error("Errore in configurazione: richiesto tipo elemento per raggruppamento " + raggr
							+ " ma configurazione non trovata per pianificazione " + pianificazioneEntity.getId());
					return Collections.emptyList();
				}
			}
			return buildListaElementi(tipoElemento);
		} catch (Exception ex) {
			log.error("Errore in configurazione per pianificazione " + pianificazioneEntity.getId(), ex);
			return Collections.emptyList();
		}
	}

	/**
	 * Costruzione lista elementi abilitati (valore=1) per la entity data
	 * @param entity tipo elemento
	 * @return lista elementi abilitati
	 */
	private List<String> buildListaElementi(@NonNull CfgTipoElementoEntity entity) {
		List<String> list = new ArrayList<>();
		if (entity.getArticolo() == 1) {
			list.add(ElementType.ARTICOLO.name());
		}
		if (entity.getAttributo() == 1) {
			list.add(ElementType.ATTRIBUTO.name());
		}
		if (entity.getGrm() == 1) {
			list.add(ElementType.GRM.name());
		}
		if (entity.getReparto() == 1) {
			list.add(ElementType.REPARTO.name());
		}
		if (entity.getTotale() == 1) {
			list.add(ElementType.TOTALE.name());
		}
		return list;
	}

	/**
	 * Crea una riga di pianificazione di tipo 'SET' associata alla testata e alla meccanica indicata
	 * con tante righe figlio quanto è il valore di MinRaggruppamento ricavato dall'header di configurazione
	 * @param testataEntity testata promozione
	 * @param meccanicaEntity meccanica
	 * @param mappedFields lista campi mappati nella entity
	 * @param user utente che esegue l'operazione
	 * @return true se il salvataggio è avvenuto correttamente, false altrimenti
	 */
	private boolean savePromoPianificazioneRigaSet(PromozioneTestataEntity testataEntity,
			MeccanicheEntity meccanicaEntity,
			Map<String, Field> mappedFields,
			String user) {
		return savePromoPianificazioneRigaSet(testataEntity, meccanicaEntity, mappedFields, user, null);
	}
	private boolean savePromoPianificazioneRigaSet(PromozioneTestataEntity testataEntity,
												   MeccanicheEntity meccanicaEntity,
												   Map<String, Field> mappedFields,
												   String user,
												   String gruppoSelezionato) {
		try {
			final CfgConfHeaderEntity confHeaderEntity = configurationHelperInstance.get()
					.getHeaderFromTestataAndMeccanica(testataEntity, meccanicaEntity);
			if (confHeaderEntity == null) {
				log.error(String.format("Errore nel recupero confHeader per la promozione con id %s e meccanica %s",
						testataEntity.getId(), meccanicaEntity.getId()));
				return false;
			}

			if (!validatorUtilInstance.get().validateSet(testataEntity, meccanicaEntity, confHeaderEntity)) {
				log.error(String.format("Il set che si vuole inserire per la promozione con id %s e meccanica %s non è valido",
						testataEntity.getId(), meccanicaEntity.getId()));
				return false;
			}

			final int usedSet = (int) getUsedSetFromTestataWithMeccanica(testataEntity, meccanicaEntity);
			int n = confHeaderEntity.getMinSet() == null ? 1 : confHeaderEntity.getMinSet();
			if (n > 1) {
				// se n = 800 e l'ultimo usato e' 801 allora n e' 1
				n = n > usedSet ? n - usedSet : 1;
			}
			if (confHeaderEntity.getMaxSet() != null) {
				// se il massimo e' 899 allora n e' il minimo tra (n, e 899 - 800) quindi n = 1
				n = Math.min(n, confHeaderEntity.getMaxSet() - usedSet);
			}
			List<PromozionePianificazioneEntity> pianificazioni = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				// Creazione riga padre di tipo 'SET'
				final PromozionePianificazioneEntity promoPianificazioneEntity = pianificazioneFactory.buildRigaSet(testataEntity,
						meccanicaEntity, confHeaderEntity, mappedFields, user);
				//4772
				fixDatePianificazioneBasedOnCanale(promoPianificazioneEntity, testataEntity);
				promoPianificazioneEntity.setCodiceGruppo(gruppoSelezionato);
				if (!validatePianificazioneWithParent(promoPianificazioneEntity,testataEntity.getDataFine())){
					return false;
				}
				final Integer counterGrouping = confHeaderEntity.getMinRaggruppamento();
				if (counterGrouping != null) {
					// Creazione minRaggruppamento righe figlio tipo 'RAGGRUPPAMENTO'
					for (int j = 0; j < counterGrouping; j++) {
						final PromozionePianificazioneEntity childEntity = pianificazioneFactory.buildRigaRaggruppamento(testataEntity,
								meccanicaEntity, j + 1, mappedFields, true, user);
						childEntity.setCodiceGruppo(gruppoSelezionato);
						promoPianificazioneEntity.addDetail(childEntity);
					}
				}
				// #3376 - Set DataInizio e DataFine sui figli
				promoPianificazioneEntity.getChildren().forEach(p -> {
					p.setDataInizio(promoPianificazioneEntity.getDataInizio());
					p.setDataFine(promoPianificazioneEntity.getDataFine());
				});
				pianificazioni.add(promoPianificazioneEntity);
			}
			for (int i = 0; i < pianificazioni.size(); ++i){
				PromozionePianificazioneEntity e = pianificazioni.get(i);
				e = pianificazioneServiceInstance.get().persist(e);
				// MG-09-01-2026: Tolto creazione "automatica" dai defaults
//				defaultMessaggiHelpers.get().addDefaultMessageRows(e, user);
				pianificazioni.set(i,e);
			}

			return true;
		} catch (Exception ex) {
			log.error(String.format("Errore salvataggio MUI_PROMOZIONE_PIANIFICAZIONE per testata con id %d",
					testataEntity.getId()), ex);
			return false;
		}
	}

	/**
	 * Crea una riga di pianificazione di tipo 'RAGGRUPPAMENTO' associata alla testata e alla meccanica indicata
	 * con tante righe figlio quanti sono gli elementi selezionati
	 * @param testataEntity testata promozione
	 * @param meccanicaEntity meccanica
	 * @param items lista elementi da aggiungere nelle righe figlio
	 * @param tipoElemento tipo elemento da inserire
	 * @param mappedFields lista campi mappati nella entity
	 * @param user utente che esegue l'operazione
	 * @return true se il salvataggio è avvenuto correttamente, false altrimenti
	 */
	private boolean savePromoPianificazioneRigaRaggruppamento(PromozioneTestataEntity testataEntity,
			MeccanicheEntity meccanicaEntity,
			Set<ItemPojo> items,
			String tipoElemento,
			Map<String, Field> mappedFields,
			String user) {
		return savePromoPianificazioneRigaRaggruppamento(testataEntity, meccanicaEntity, items, tipoElemento,
				mappedFields, user, null);
	}
	private boolean savePromoPianificazioneRigaRaggruppamento(PromozioneTestataEntity testataEntity,
															  MeccanicheEntity meccanicaEntity,
															  Set<ItemPojo> items,
															  String tipoElemento,
															  Map<String, Field> mappedFields,
															  String user,
															  String gruppoSelezionato) {

		try {
			if (!validatorUtilInstance.get().validateRaggruppamento(testataEntity, meccanicaEntity)) {
				log.error(String.format("Il set che si vuole inserire per la promozione con id %s e meccanica %s non è valido",
						testataEntity.getId(), meccanicaEntity.getId()));
				return false;
			}
			// Creazione riga padre di tipo 'RAGGRUPPAMENTO'
			final PromozionePianificazioneEntity promoPianificazioneEntity = pianificazioneFactory.buildRigaRaggruppamento(testataEntity,
					meccanicaEntity, getNextNumRaggruppamento(testataEntity.getPromozionePianificazioneEntities()),
					mappedFields, false, user);
			promoPianificazioneEntity.setCodiceGruppo(gruppoSelezionato);
			if (promoPianificazioneEntity.getDataInizio() == null) {
				promoPianificazioneEntity.setDataInizio(testataEntity.getDataInizio());
			}
			if (promoPianificazioneEntity.getDataFine() == null) {
				promoPianificazioneEntity.setDataFine(testataEntity.getDataFine());
			}
			//#4772
			fixDatePianificazioneBasedOnCanale(promoPianificazioneEntity, testataEntity);
			// a questo punto, se la data inizio della pianificazione è successiva alla data fine della testata, non è possibile salvare la riga
			if ( !validatePianificazioneWithParent(promoPianificazioneEntity, testataEntity.getDataFine()) ) {
				return false;
			}
			// a questo punto non devo controllare i figli perche' sto inserendo un raggruppamento nuovo
			// e le date vengono prese dalla data del raggruppamento
			// Creazione righe figlio tante quanti sono gli elementi selezionati
			if (PromoPianificazioneEnum.TOTALE.getTipoElemento().equals(tipoElemento)) {
				final PromozionePianificazioneEntity childEntity = pianificazioneFactory.buildRigaElementoTotale(testataEntity, meccanicaEntity,
						mappedFields, user);
				promoPianificazioneEntity.addDetail(childEntity);
			} else {
				items.forEach(item -> {
					final PromozionePianificazioneEntity childEntity = pianificazioneFactory.buildRigaElemento(testataEntity, meccanicaEntity,
							tipoElemento, item, mappedFields, user);
					promoPianificazioneEntity.addDetail(childEntity);
				});
			}
			// #3376 - Set DataInizio e DataFine sui figli
			promoPianificazioneEntity.getChildren().forEach(p -> {
				p.setDataInizio(promoPianificazioneEntity.getDataInizio());
				p.setDataFine(promoPianificazioneEntity.getDataFine());
			});

			PromozionePianificazioneEntity paranoia =  pianificazioneServiceInstance.get().persist(promoPianificazioneEntity);
			// MG-09-01-2026: Tolto creazione "automatica" dai defaults
//			defaultMessaggiHelpers.get().addDefaultMessageRows(paranoia, user);
			return true;
		} catch (Exception ex) {
			log.error(String.format("Errore salvataggio MUI_PROMOZIONE_PIANIFICAZIONE per testata con id %d",
					testataEntity.getId()), ex);
			return false;
		}
	}

	/**
	 * Crea una riga di pianificazione di tipo 'ELEMENTO' associata alla testata e alla meccanica indicata
	 * con tante righe quanti sono gli elementi selezionati, senza 'padre'
	 * @param testataEntity testata promozione
	 * @param meccanicaEntity meccanica
	 * @param items lista elementi da aggiungere nelle righe
	 * @param tipoElemento tipo elemento da inserire
	 * @param mappedFields lista campi mappati nella entity
	 * @param user utente che esegue l'operazione
	 * @return true se il salvataggio è avvenuto correttamente, false altrimenti
	 */
	private boolean savePromoPianificazioneRigaElemento(PromozioneTestataEntity testataEntity,
			MeccanicheEntity meccanicaEntity,
			Set<ItemPojo> items,
			String tipoElemento,
			Map<String, Field> mappedFields,
			String user) {
		try {
			List<PromozionePianificazioneEntity> pianificazioni = new ArrayList<>();
			// Creazione righe di tipo 'ELEMENTO' senza padre
			if (PromoPianificazioneEnum.TOTALE.getTipoElemento().equals(tipoElemento)) {
				final PromozionePianificazioneEntity childEntity = pianificazioneFactory.buildRigaElementoTotale(testataEntity, meccanicaEntity,
						mappedFields, user);
				if (childEntity.getDataInizio() == null) {
					childEntity.setDataInizio(testataEntity.getDataInizio());
				}
				if (childEntity.getDataFine() == null) {
					childEntity.setDataFine(testataEntity.getDataFine());
				}
				fixDatePianificazioneBasedOnCanale(childEntity, testataEntity);
				if( !validatePianificazioneWithParent(childEntity, testataEntity.getDataFine()) ) {
					return false;
				}
				pianificazioni.add(childEntity);
			} else {
				for (ItemPojo item : items) {
					final PromozionePianificazioneEntity childEntity = pianificazioneFactory.buildRigaElemento(testataEntity, meccanicaEntity,
							tipoElemento, item, mappedFields, user);
					if (childEntity.getDataInizio() == null) {
						childEntity.setDataInizio(testataEntity.getDataInizio());
					}
					if (childEntity.getDataFine() == null) {
						childEntity.setDataFine(testataEntity.getDataFine());
					}
					fixDatePianificazioneBasedOnCanale(childEntity, testataEntity);
					if(validatePianificazioneWithParent(childEntity, testataEntity.getDataFine()) ) {
						pianificazionePromoUtil.get().updateCheckCompratore(childEntity, true);
						pianificazioni.add(childEntity);
					} else {
						return false;
					}
				}
			}
			pianificazioni.forEach(this::fixDatePianificazioneBasedOnCanale);
			// MG-03-09-2024: gestione campo LINK su meccanica M018
			if ("M018".equalsIgnoreCase(meccanicaEntity.getCodiceMeccanica())) {
				final CfgPianificazioneEntity configurazione = configurationHelperInstance.get()
						.getConfigurationForField(testataEntity.getMuiCfgSetPianificazione(),
								meccanicaEntity, PianificazioneConstants.REFERENCE_LINK);
				if (configurazione != null && configurazione.getLista() != null) {
					final String lista = configurazione.getLista();
					pianificazioni.forEach(pianificazione -> {
						List<String> values = linkHelperInstance.get().getAvailableValues(lista, pianificazione);
						if (values != null && !values.isEmpty()) {
							pianificazione.setLink(values.get(0));
						} else {
							log.error(String.format("Nessun valore disponibile per il campo '%s' sulla pianificazione '%s'",
									PianificazioneConstants.REFERENCE_LINK, pianificazione.getElemento()));
							pianificazione.setLink(null);
						}
						pianificazione = pianificazioneServiceInstance.get().persist(pianificazione);
						// MG-09-01-2026: Tolto creazione "automatica" dai defaults
//						defaultMessaggiHelpers.get().addDefaultMessageRows(pianificazione, user);
					});
				} else {
					log.error(String.format("Campo '%s' non configurato come lista per la meccanica '%s'",
							PianificazioneConstants.REFERENCE_LINK, meccanicaEntity.getCodiceMeccanica()));
				}
			} else {
				for ( int i = 0; i < pianificazioni.size(); ++i){
					PromozionePianificazioneEntity e = pianificazioneServiceInstance.get().persist(pianificazioni.get(i));
					// MG-09-01-2026: Tolto creazione "automatica" dai defaults
//					defaultMessaggiHelpers.get().addDefaultMessageRows(e,user);
					pianificazioni.set(i,e);
				}
			}
			return true;
		} catch (Exception ex) {
			log.error(String.format("Errore salvataggio MUI_PROMOZIONE_PIANIFICAZIONE per testata con id %d",
					testataEntity.getId()), ex);
			return false;
		}
	}

	/**
	 * Aggiunge una riga pianificazione di tipo 'RAGGRUPPAMENTO' con n righe figlio di tipo 'ELEMENTO'
	 * ad una riga di tipo 'SET' esistente
	 * @param pianificazioneEntity la pianificazione padre di tipo 'SET'
	 * @param testataEntity la promozione associata
	 * @param meccanicaEntity la meccanica associata
	 * @param items lista di elementi da inserire
	 * @param tipoElemento tipo elemento
	 * @param mappedFields lista campi mappati nella entity
	 * @param user utente che esegue l'operazione
	 * @return true se la pianificazione è stata salvata correttamente, false altrimenti
	 */
	private boolean addRigaRaggruppamentoToRigaSet(PromozionePianificazioneEntity pianificazioneEntity,
			PromozioneTestataEntity testataEntity,
			MeccanicheEntity meccanicaEntity,
			Set<ItemPojo> items,
			String tipoElemento,
			Map<String, Field> mappedFields,
			String user) {
		return addRigaRaggruppamentoToRigaSet(pianificazioneEntity, testataEntity, meccanicaEntity, items, tipoElemento,
				mappedFields, user, pianificazioneEntity.getCodiceGruppo());
	}
	private boolean addRigaRaggruppamentoToRigaSet(PromozionePianificazioneEntity pianificazioneEntity,
												   PromozioneTestataEntity testataEntity,
												   MeccanicheEntity meccanicaEntity,
												   Set<ItemPojo> items,
												   String tipoElemento,
												   Map<String, Field> mappedFields,
												   String user, String gruppoSelezionato) {

		if (!validatorUtilInstance.get().validateRaggruppamento(testataEntity, meccanicaEntity)) {
			final String msg = String.format("Errore; impossibile aggiungere un nuovo raggruppamento al set %s " +
					"della promozione con id %s e meccanica id %s", pianificazioneEntity.getNumSet(),
					testataEntity.getId(), meccanicaEntity.getId());
			log.error(msg);
			return false;
		}

		try {
			int numRaggr = pianificazioneEntity.getChildren() != null
					? getNextNumRaggruppamento(pianificazioneEntity.getChildren())
							: 1;
			final PromozionePianificazioneEntity rigaRaggr = pianificazioneFactory.buildRigaRaggruppamento(testataEntity, meccanicaEntity,
					numRaggr, mappedFields, true, user);
			rigaRaggr.setCodiceGruppo(gruppoSelezionato);
			if (rigaRaggr.getDataInizio() == null) {
				rigaRaggr.setDataInizio(pianificazioneEntity.getDataInizio());
			}
			if (rigaRaggr.getDataFine() == null) {
				rigaRaggr.setDataFine(pianificazioneEntity.getDataFine());
			}
			//#4772
			// controlla che sia possibile inserire il raggruppamento in base alla data di inizio e fine del set
			fixDatePianificazioneBasedOnCanale(rigaRaggr, pianificazioneEntity.getPromozioneTestataEntity().getCanalePromozioneEntity(),pianificazioneEntity.getDataInizio());
			if ( !validatePianificazioneWithParent(rigaRaggr,pianificazioneEntity.getDataFine()) ) {
				return false;
			}
			propagaValori(pianificazioneEntity, rigaRaggr);

			// non devo rifare il controllo #4772 perche'
			// inserisco all'interno del raggruppamento e comanda la data del raggruppamento
			if (PromoPianificazioneEnum.TOTALE.getTipoElemento().equals(tipoElemento)) {
				// Add singola riga di tipo 'ELEMENTO' con il totale
				final PromozionePianificazioneEntity rigaElem = pianificazioneFactory.buildRigaElementoTotale(testataEntity, meccanicaEntity,
						mappedFields, user);
				if (rigaElem.getDataInizio() == null) {
					rigaElem.setDataInizio(rigaRaggr.getDataInizio());
				}
				if (rigaElem.getDataFine() == null) {
					rigaElem.setDataFine(rigaRaggr.getDataFine());
				}
				try {
					rigaRaggr.addDetail(propagaValori(rigaRaggr,rigaElem));
				} catch (IllegalArgumentException e ){
					log.error("Impossibile propagare i valori dal parent al child", e);
					rigaRaggr.addDetail(rigaElem);
				}
			} else {
				// Add n righe di tipo 'ELEMENTO' con gli articoli / grm / reparti selezionati
				items.forEach(item -> {
					final PromozionePianificazioneEntity rigaElem = pianificazioneFactory.buildRigaElemento(testataEntity, meccanicaEntity,
							tipoElemento, item, mappedFields, user);
					if (rigaElem.getDataInizio() == null) {
						rigaElem.setDataInizio(rigaRaggr.getDataInizio());
					}
					if (rigaElem.getDataFine() == null) {
						rigaElem.setDataFine(rigaRaggr.getDataFine());
					}
					try {
						rigaRaggr.addDetail(propagaValori(rigaRaggr,rigaElem));
					} catch (IllegalArgumentException e ){
						log.error("Impossibile propagare i valori dal parent al child", e);
						rigaRaggr.addDetail(rigaElem);
					}
				});
			}
			pianificazioneEntity.addDetail(rigaRaggr);
			pianificazioneServiceInstance.get().savePromozionePianificazioneEntity(pianificazioneEntity, user);
			// MG-09-01-2026: Tolto creazione "automatica" dai defaults
//			defaultMessaggiHelpers.get().addDefaultMessageRows(pianificazioneEntity, user);
			return true;
		} catch (Exception ex) {
			log.error(String.format("Errore salvataggio MUI_PROMOZIONE_PIANIFICAZIONE per testata con id %d",
					testataEntity.getId()), ex);
			return false;
		}
	}

	/**
	 * Aggiunge n righe pianificazione di tipo 'ELEMENTO' ad una riga di tipo 'RAGGRUPPAMENTO' esistente
	 * @param pianificazioneEntity la pianificazione padre di tipo 'RAGGRUPPAMENTO'
	 * @param testataEntity la promozione associata
	 * @param meccanicaEntity la meccanica associata
	 * @param items lista di elementi da inserire
	 * @param tipoElemento tipo elemento
	 * @param mappedFields lista campi mappati nella entity
	 * @param user utente che esegue l'operazione
	 * @return true se la pianificazione è stata salvata correttamente, false altrimenti
	 */
	private boolean addRigheElementoToRigaRaggruppamento(PromozionePianificazioneEntity pianificazioneEntity,
			PromozioneTestataEntity testataEntity,
			MeccanicheEntity meccanicaEntity,
			Set<ItemPojo> items,
			String tipoElemento,
			Map<String, Field> mappedFields,
			String user) {
		final boolean isOmogeneo = validatorUtilInstance.get().validateOmogeneo(testataEntity, meccanicaEntity,
				pianificazioneEntity.getNumRaggruppamento());
		final PromozionePianificazioneEntity firstChild = pianificazioneEntity.getChildren().stream()
				.findFirst()
				.orElse(null);
		if (isOmogeneo && (firstChild != null) && !tipoElemento.equals(firstChild.getTipoElemento())) {
			log.error(String.format("Il raggruppamento per la promozione id %s e meccanica id %s permette solo elementi " +
					"omogenei di tipo '%s'",
					testataEntity.getId(), meccanicaEntity.getId(), firstChild.getTipoElemento()));
			return false;
		}

		try {
			if (PromoPianificazioneEnum.TOTALE.getTipoElemento().equals(tipoElemento)) {
				// Add singola riga di tipo 'ELEMENTO' con il totale
				final PromozionePianificazioneEntity rigaElem = pianificazioneFactory.buildRigaElementoTotale(testataEntity, meccanicaEntity,
						mappedFields, user);
				if (rigaElem.getDataInizio() == null) {
					rigaElem.setDataInizio(pianificazioneEntity.getDataInizio());
				}
				if (rigaElem.getDataFine() == null) {
					rigaElem.setDataFine(pianificazioneEntity.getDataFine());
				}
				//#4772
				fixDatePianificazioneBasedOnCanale(rigaElem, pianificazioneEntity.getPromozioneTestataEntity().getCanalePromozioneEntity(), pianificazioneEntity.getDataInizio());
				if ( !validatePianificazioneWithParent(rigaElem,pianificazioneEntity.getDataFine() )) {
					return false;
				}
				try {
					pianificazioneEntity.addDetail(propagaValori(pianificazioneEntity, rigaElem));
				} catch (IllegalArgumentException e) {
					log.error("Impossibile propagare i valori dal parent al child", e);
					pianificazioneEntity.addDetail(rigaElem);
				}
			} else {
				// Add n righe di tipo 'ELEMENTO' con gli articoli / grm / reparti selezionati
				for(ItemPojo item : items){
					final PromozionePianificazioneEntity rigaElem = pianificazioneFactory.buildRigaElemento(testataEntity, meccanicaEntity,
							tipoElemento, item, mappedFields, user);
					if (rigaElem.getDataInizio() == null) {
						rigaElem.setDataInizio(pianificazioneEntity.getDataInizio());
					}
					if (rigaElem.getDataFine() == null) {
						rigaElem.setDataFine(pianificazioneEntity.getDataFine());
					}
					//#4772
					fixDatePianificazioneBasedOnCanale(rigaElem, pianificazioneEntity.getPromozioneTestataEntity().getCanalePromozioneEntity(), pianificazioneEntity.getDataInizio());
					if ( !validatePianificazioneWithParent(rigaElem,pianificazioneEntity.getDataFine() )) {
						return false;
					}
					try {
						pianificazioneEntity.addDetail(propagaValori(pianificazioneEntity, rigaElem));
					} catch (IllegalArgumentException e) {
						log.error("Impossibile propagare i valori dal parent al child", e);
						pianificazioneEntity.addDetail(rigaElem);
					}
				}
			}
			pianificazioneServiceInstance.get().savePromozionePianificazioneEntity(pianificazioneEntity, user);
			// MG-09-01-2026: Tolto creazione "automatica" dai defaults
//			defaultMessaggiHelpers.get().addDefaultMessageRows(pianificazioneEntity, user);
			return true;
		} catch (Exception ex) {
			log.error(String.format("Errore salvataggio MUI_PROMOZIONE_PIANIFICAZIONE per testata con id %d",
					testataEntity.getId()), ex);
			return false;
		}
	}
	PromozionePianificazioneEntity propagaValori(PromozionePianificazioneEntity da, PromozionePianificazioneEntity a){
		if( da == null || a == null ) throw  new IllegalArgumentException("L'oggetto da e l'oggetto a devono essere valorizzati");
		// leggo l'header di configurazione
		final CfgConfHeaderEntity confHeader = confHeaderServiceInstance.get()
				.findByMeccanicaIdAndSetPianificazioneId(da.getMeccanicaEntity().getId(),
						da.getPromozioneTestataEntity().getMuiCfgSetPianificazione().getId());
		// prendo tutti i campi configurati per quel tipo riga per la propagazione
		final List<CfgPianificazioneEntity> campiDaPropagare = confHeader.getMuiCfgPianificaziones().stream()
				.filter(CfgPianificazioneEntity::getPropaga)
				.filter(c->c.getMuiCfgPianificazTipoRiga().getId() == da.getTipoRiga().getId())
				.collect(Collectors.toList());
		if ( campiDaPropagare != null && !campiDaPropagare.isEmpty()) {
			//propaga i valori di tutti i campi
			final VisualizzaPianificazioneHelper invoker = visualizzaPianificazioneHelper.get();
			final PromozionePianificazioneEntityHelper setter = promoPianificazioneEntityHelperInstance.get();
			campiDaPropagare.forEach(configurazione -> {
				//prendo il valore del campo
				final String campoDaPropagare = configurazione.getMuiCfgPianificazioneCampi().getCampo();
				if ( campoDaPropagare != null ) {
					setter.invokeSetterEntity(a, campoDaPropagare, invoker.invokeGetterEntity(campoDaPropagare, da));
				}
			});
		}
		// ritorno la riga aggiornata da salvare
		return a;
	}

	/**
	 * Controlla se la meccanica associata al set pianificazione è del tipo specificato
	 * @param entity meccanica
	 * @param idSetPianificazione set pianificazione
	 * @param level livello / tipo
	 * @return true se la meccanica è del tipo specificato, false altrimenti
	 */
	private boolean isMeccanicaOnLevel(MeccanicheEntity entity, Long idSetPianificazione, PlanningLevelEnum level) {
		return entity.getMuiCfgConfHeaders().stream()
				.anyMatch(h -> level.name().equals(h.getLivelloPianificazione().getCodice())
						&& idSetPianificazione.equals(h.getMuiCfgSetPianificazione().getId()));
	}




	public List<Long> getUsedItems(PromozioneTestataEntity testata, String tipoElemento) {
		return testata.getPromozionePianificazioneEntities().stream()
				.filter(p -> tipoElemento.equalsIgnoreCase(p.getTipoElemento()))
				.map(p -> Long.parseLong(p.getCodiceElemento()))
				.collect(Collectors.toList());
	}


	public Boolean getDuplicaFlagForCanale(@NonNull final CanalePromozioneEntity canale, ElementType elementType) {
		switch (elementType) {
			case ARTICOLO:
				return canale.getDuplicaArticolo();
			case GRM:
				return canale.getDuplicaGrm();
			case REPARTO:
				return canale.getDuplicaReparto();
			default:
				log.error(String.format("Element type '%s' not managed", elementType.getDescription()));
				return null;
		}
	}

	@Deprecated
	public Boolean getDuplicaFlagForHeader(@NonNull final CfgConfHeaderEntity confHeader,
										   PromoPianificazioneEnum tipoElemento) {
		return validatorUtilInstance.get().getDuplicaFlagForHeader(confHeader, tipoElemento);
	}
	@Deprecated
	public Boolean getDuplicaFlagForHeader(@NonNull final CfgConfHeaderEntity confHeader,
										   ElementType tipoElemento) {
		return validatorUtilInstance.get().getDuplicaFlagForHeader(confHeader, tipoElemento.getDescription());
	}

	/**
         * Recupero il prossimo valore da utilizzare per il numero raggruppamento, secondo questa regola:
         * - max utilizzato per numRaggruppamento tra tutte le righe di pianificazione + 1
         * - 1 se non ci sono ancora raggruppamenti
         * @param pianificazioni lista righe pianificazione
         * @return il valore da utilizzare per il numero raggruppamento
         */
	private int getNextNumRaggruppamento(Set<PromozionePianificazioneEntity> pianificazioni) {
		int lastUsed = pianificazioni.stream()
				.filter(Objects::nonNull)
				.filter(p -> p.getNumRaggruppamento() != null)
				.mapToInt(p -> new Integer(p.getNumRaggruppamento()))
				.max()
				.orElse(0);
		return lastUsed + 1;
	}




	/**
	 * Recupero il numero di pianificazioni tipo 'SET' utilizzate nella testata con una determinata meccanica
	 * @param testataEntity la testata associata
	 * @param meccanicaEntity la meccanica associata
	 * @return numero di pianificazioni di tipo 'SET' utilizzate nella testata con una determinata meccanica
	 */
	private long getUsedSetFromTestataWithMeccanica(PromozioneTestataEntity testataEntity, MeccanicheEntity meccanicaEntity) {
		final PianificazioneEntityPredicates predicates = predicatesInstance.get();
		return testataEntity.getPromozionePianificazioneEntities().stream()
				.filter(predicates.byTipoRiga(PianificazioneRowTypeEnum.SET)
						.and(predicates.byMeccanica(meccanicaEntity)))
				.count();
	}

	public boolean duplicaTotaleShouldRemoveTipoElementoTotale(Long idMeccanica, PromozioneTestataEntity promozione) {
		if (idMeccanica == null || promozione == null) return false;
		if (promozione.getMuiCanalePromozione().getDuplicaTotale()) {
			// se la meccanica non ammette i totali duplicati
			boolean duplicaTotale = promozione.getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream().filter(h->h.getMeccanicaEntity().getId()==idMeccanica).map(CfgConfHeaderEntity::getDuplicaTotale).findFirst().orElse(false);
			if ( !duplicaTotale) {
				// controllare che la meccanica corrente non abbia gia' un totale
				return promozione.getPromozionePianificazioneEntities().stream()
						.filter(Objects::nonNull)
						.filter(p -> p.getMeccanicaEntity().getId() == idMeccanica)
						.filter(p -> ElementType.TOTALE.name().equals(p.getTipoElemento()))
						.findAny().isPresent();
			} else {
				return false;
			}
		} else {
			// controllare che la pianificazione corrente non abbia gia' un totale
			return promozione.getPromozionePianificazioneEntities().stream()
					.filter(Objects::nonNull)
					.filter(p -> ElementType.TOTALE.name().equals(p.getTipoElemento()))
					.findAny().isPresent();

		}
	}

	public PromozionePianificazioneEntity fixDatePianificazioneBasedOnCanale(final PromozionePianificazioneEntity pianificazione){
		return fixDatePianificazioneBasedOnCanale(pianificazione, pianificazione.getPromozioneTestataEntity());
	}
	public PromozionePianificazioneEntity fixDatePianificazioneBasedOnCanale(final PromozionePianificazioneEntity pianificazione, final PromozioneTestataEntity testata){
		if (testata == null){
			log.error("Pianificazione non ha testata associata", pianificazione.getId());
			return  fixDatePianificazioneBasedOnCanale(pianificazione, (CanalePromozioneEntity)null, testata.getDataInizio());
		} else {
			return  fixDatePianificazioneBasedOnCanale(pianificazione, testata.getMuiCanalePromozione(),testata.getDataInizio());
		}
	}
	public PromozionePianificazioneEntity fixDatePianificazioneBasedOnCanale(final PromozionePianificazioneEntity pianificazione, final CanalePromozioneEntity canalePromozione, Date dataInizio){
		boolean isOverride = false;
		if (canalePromozione == null){
			log.error("Canale mancante, non posso verificare se la data inizio pianificazione deve essere sovrascritta");
		} else {
			isOverride = canalePromozione.getFlOverridePianificazioneInizio();
		}
		if ( isOverride){
			DateTimeUtils dateTimeUtils = new DateTimeUtils();
			// se la promozione ha data futura allora la data inizio e' la data della promozione
			LocalDate today = LocalDate.now();
			LocalDate tomorrow = today.plusDays(1);
			if (dateTimeUtils.isAfter(dataInizio, java.sql.Date.valueOf(today), false)) {
				pianificazione.setDataInizio(dataInizio);
			} else {
				pianificazione.setDataInizio(java.sql.Date.valueOf(tomorrow));
			}
		}
		return pianificazione;
	}

	boolean validatePianificazioneWithParent(PromozionePianificazioneEntity promoPianificazioneEntity, Date dataFine){
		if ( promoPianificazioneEntity != null && promoPianificazioneEntity.getDataInizio() != null && dataFine != null) {
			if (promoPianificazioneEntity.getDataInizio().after(dataFine)) {
				log.error(String.format("La data inizio della pianificazione %s è successiva alla data fine del parent %s",
						sdf.format(promoPianificazioneEntity.getDataInizio()), sdf.format(dataFine)));
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	@Deprecated
	public List<Long> getUsedItems(PromozioneTestataEntity testata, Long idMeccanica, String livello,
								   String tipoElemento) {
		return validatorUtilInstance.get().getUsedItems(testata, idMeccanica, livello, tipoElemento);
	}
}
