package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.grid.DbPromoAgCellUtils;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.common.utility.StringUtils;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CheckPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiIniziativaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.CheckPianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.MuiIniziativaService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.views.FacesContextAware;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.NumSetUtils;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromoConfigurationHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromozionePianificazioneEntityHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.VisualizzaPianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PlanningCommons;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Dependent
@Slf4j
public class PianificazionePromoUtil implements FacesContextAware {
	private static final String STRING = "STRING";
	private static final String NUMBER = "NUMBER";
	private static final String INTEGER = "INTEGER";
	private static final String DATE = "DATE";
	private static final String TIME = "TIME";
	private static final List<String> CODICI_MECCANICHE_CASHBACK = Arrays.asList("M800", "M801");

	@Inject
	private PianificazioneService pianificazioneService;

	@Inject
	private CanalePromozioneService canalePromozioneService;

	@Inject
	private VisualizzaPianificazioneHelper visualizzaPianificazioneHelper;

	@Inject
	private PromozionePianificazioneEntityHelper promoPianificazioneEntityHelper;

	@Inject
	private PromoService promoService;

	@Inject
	private PlanningCommons planningCommons;

	@Inject
	private PromoConfigurationHelper pianificazioneHelper;

	@Inject
	private Instance<PianificazioneSecurityUtil> securityUtilInstance;

	@Inject
	private Instance<ItemService> itemServiceInstance;

	@Inject
	Instance<CheckPianificazioneService> checkPianificazioneInstance;

	@Inject
	private Instance<MuiIniziativaService> muiIniziativaServiceInstance;

	@Inject
	private Instance<NumSetUtils> numSetUtilsInstance;

	private DateTimeUtils dateTimeUtils = new DateTimeUtils();
	private StringUtils picklistUtils = new StringUtils();

	/**
	 * Cancella una riga di pianificazione. Se la riga non ha "parent" allora viene
	 * cancellata la riga, tutti i suoi figli ed eventuali upload associati. Se la
	 * riga e' di tipo Elemento all'interno di un Raggruppamento ed e' l'ultimo
	 * figlio, allora viene cancellato anche il raggruppamento. Se la riga e' di
	 * tipo Raggruppamento ed e' all'interno di un set, allora vengono riodinate le
	 * righe di tipo raggruppamento rimaste in base al num set; la funzione si
	 * assicura che non ci siano "buchi" in num set.
	 *
	 * @param pianificazione
	 * @param username
	 * @return
	 */
	public String delete(@NonNull final PromozionePianificazioneEntity pianificazione, String username) {
		PlanningLevelEnum tipo = PlanningLevelEnum.fromCode(pianificazione.getTipoRiga().getCodiceTipo());
		String message = null;
		if (!canCancelType(tipo)) {
			// elemento sconosciuto: db aggiornato a mano ...
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", "Tipo riga sconosciuto "
					+ pianificazione.getTipoRiga().getCodiceTipo() + ". Contattare l'assistenza"));
			log.error("Unknown row type " + pianificazione.getTipoRiga().getCodiceTipo());
			return message;
		}
		PromozioneTestataEntity promozione = pianificazione.getPromozioneTestataEntity();
		if (pianificazione.getParent() == null) {
			// posso cancellare senza tanti sbatti:
			if (removePianificazione(pianificazione)) {
				try {
					updateCheckCompratore(pianificazione, promozione, true);
					promoService.persist(promozione, username);
					// pippone per il messggio
					if (PlanningLevelEnum.ELEMENTO.equals(tipo)) {
						message = String.format("Cancellazione dell'elemento %s con meccanica %s avvenuta con successo",
								pianificazione.getElemento(), pianificazione.getMeccanicaEntity().getDescrizione());

					} else {
						message = "Cancellazione del " + tipo.getDescription().toLowerCase() + " avvenuta con successo";
					}
				} catch (Exception e) {
					log.error("Error persisting changes to db", e);
					addMessage(null, createFatalMessage(tipo));
				}
			} else {
				addMessage(null, createFatalMessage(tipo));
			}
			return message;
		}
		// a questo punto devo capire dentro cosa mi sono ficcato:
		switch (tipo) {
			case RAGGRUPPAMENTO:
				PromozionePianificazioneEntity set = pianificazione.getParent();
				if (!PlanningLevelEnum.fromCode(set.getTipoRiga().getCodiceTipo()).equals(PlanningLevelEnum.SET)) {
					// padre errato
					log.error("Tipo riga padre di " + pianificazione.getId() + " errato. Trovato "
							+ set.getTipoRiga().getDescrizione() + " aspettavo SET");
					addMessage(null, createFatalMessage(tipo));
					return message;
				}
				set.removeDetail(pianificazione);// rimuove il figlio dal padre
				// rimuove la pianificazione dalla testata
				promozione.removePromozionePianificazioneEntity(pianificazione);
				recalculateRaggruppamento(set);
				try {
					updateCheckCompratore(pianificazione, promozione, true);
					promoService.persist(promozione, username);
					pianificazioneService.savePromozionePianificazioneEntity(set, username);
					message = String.format("Cancellazione del raggruppamento avvenuta con successo [elemento: %s; meccanica: %s]",
							pianificazione.getElemento(), pianificazione.getMeccanicaEntity().getDescrizione());
				} catch (Exception e) {
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
							"Errore durante la cancellazione del raggruppamento. Contattare l'assistenza"));
					log.error("Errore durante la cancellazione del raggruppamento " + pianificazione.getId(), e);
				}
				break;
			case ELEMENTO:
				Integer minRaggruppamento = 0;
				if (pianificazioneHelper == null) {
					log.error(message);
				}
				CfgConfHeaderEntity configHeader = pianificazioneHelper.getHeaderFromTestataAndMeccanica(
						pianificazione.getPromozioneTestataEntity(), pianificazione.getMeccanicaEntity());
				if ((configHeader != null) && (configHeader.getMinRaggruppamento() != null)) {
					minRaggruppamento = configHeader.getMinRaggruppamento();
				}
				PromozionePianificazioneEntity raggruppamento = pianificazione.getParent();
				if ((raggruppamento != null) && (raggruppamento.getChildren() != null)
						&& (raggruppamento.getChildren().size() == 1) && (raggruppamento.getParent() != null)
						&& (raggruppamento.getParent().getChildren().size() > minRaggruppamento)
						&& PlanningLevelEnum.fromCode(raggruppamento.getTipoRiga().getCodiceTipo())
						.equals(PlanningLevelEnum.RAGGRUPPAMENTO)) {
					// elimina raggruppamento , forse in set
					final PromozioneTestataEntity testata = raggruppamento.getPromozioneTestataEntity();
					if (delete(raggruppamento, username) != null) {
						// override del messaggio
						message = String.format(
								"Cancellazione dell'elemento %s con meccanica %s e del raggruppamento %s avvenuta con successo",
								pianificazione.getElemento(), pianificazione.getMeccanicaEntity().getDescrizione(),
								pianificazione.getNumRaggruppamento());
						try {
							updateCheckCompratore(pianificazione, testata, true);
							promoService.persist(promozione, username);
						} catch (Exception e) {
							addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
									"Errore durante la cancellazione del raggruppamento. Contattare l'assistenza"));
							log.error("Errore durante la cancellazione del raggruppamento " + pianificazione.getId(), e);
						}
					} else {
						// errore cancellazione parent
						log.error("Errore durante la cancellazione del raggruppamento " + raggruppamento.getId());
						message = null;
					}
				} else {
					// elimina elemento
					final PromozioneTestataEntity testata = pianificazione.getPromozioneTestataEntity();
					if (removePianificazione(pianificazione)) {
						message = String.format("Cancellazione dell'elemento %s con meccanica %s avvenuta con successo",
								pianificazione.getElemento(), pianificazione.getMeccanicaEntity().getDescrizione());
						try {
							updateCheckCompratore(pianificazione, testata, true);
							promoService.persist(promozione, username);
						} catch (Exception e) {
							addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
									"Errore durante la cancellazione elemento. Contattare l'assistenza"));
							log.error("Errore durante la cancellazione elemento " + pianificazione.getId(), e);
						}

					} else {
						addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", String.format(
								"Errore durante la cancellazione dell'elemento %s con meccanica %s. Contattare l'assistenza",
								pianificazione.getElemento(), pianificazione.getMeccanicaEntity().getDescrizione())));
					}
				}
				break;
			case SET:
				// il set non dovrebbe avere un padre ....
				log.error("La riga di tipo set con id " + pianificazione.getId() + " non dovrebbe avere un padre ! ");
				addMessage(null, createFatalMessage(tipo));
			default:
				// non ho idea di cosa sto cancellando
				log.error("La riga di tipo " + tipo.getCode() + " con id " + pianificazione.getId()
						+ " non puo' essere gestita ! ");
				addMessage(null, createFatalMessage(tipo));
				break;
		}
		return message;
	}

	boolean canCancelType(PlanningLevelEnum level) {
		boolean result = (level != null);
		if (result) {
			switch (level) {
				case ELEMENTO:
				case RAGGRUPPAMENTO:
				case SET:
					result = true;
					break;
				default:
					result = false;
					break;
			}
		}

		return result;
	}

	FacesMessage createFatalMessage(PlanningLevelEnum level) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
				"Errore durante la cancellazione del" + (PlanningLevelEnum.ELEMENTO.equals(level) ? "l' " : " ")
						+ level.getDescription().toLowerCase() + ". Contattare l'assistenza");
	}

	private boolean removePianificazione(@NonNull final PromozionePianificazioneEntity pianificazione) {
		boolean success = false;
		removeUploads(pianificazione);
		removeDetails(pianificazione);
		if (pianificazione.getParent() != null) {
			pianificazione.getParent().removeDetail(pianificazione);
		}

		try {
			PromozioneTestataEntity testata = pianificazione.getPromozioneTestataEntity();
			pianificazioneService.removePromozionePianificazioneEntity(pianificazione);
			if (testata != null) {
				testata.removePromozionePianificazioneEntity(pianificazione);
			}
			success = true;
		} catch (Exception e) {
			if (PlanningLevelEnum.ELEMENTO.getCode().equals(pianificazione.getTipoRiga().getCodiceTipo())) {
				log.error("error deleting elemento " + pianificazione.getId(), e);
			} else if (PlanningLevelEnum.RAGGRUPPAMENTO.getCode()
					.equals(pianificazione.getTipoRiga().getCodiceTipo())) {
				log.error("error deleting raggruppamento " + pianificazione.getId(), e);
			} else {
				log.error("error deleting master row " + pianificazione.getId(), e);
			}
		}
		return success;
	}

	/**
	 * Rimuove gli uploads (da database) della promozione
	 *
	 * @param e
	 */
	private void removeUploads(@NonNull final PromozionePianificazioneEntity e) {
		if (e.getUploadFidaty() != null) {
			Iterator<UploadFidayEntity> iterator = e.getUploadFidaty().iterator();
			UploadFidayEntity upload = null;
			while (iterator.hasNext()) {
				upload = iterator.next();
				upload.setPromozionePianificazioneEntity(null);
				iterator.remove();
			}
		}
	}

	/**
	 * Rimuove a cascata verso il basso tutti i dettagli
	 *
	 * @param e
	 */
	private void removeDetails(@NonNull final PromozionePianificazioneEntity e) {
		if (e.getChildren() != null) {
			Iterator<PromozionePianificazioneEntity> iterator = e.getChildren().iterator();
			PromozionePianificazioneEntity detail = null;
			while (iterator.hasNext()) {
				detail = iterator.next();
				removeChecks(detail);
				removeDetails(detail);
				detail.setParent(null);
				iterator.remove();
			}
		}
	}

	private void removeChecks(@NonNull final PromozionePianificazioneEntity e) {
		if (e.getChecks() != null && !e.getChecks().isEmpty()) {
			Iterator<CheckPianificazioneEntity> iterator = e.getChecks().iterator();
			while (iterator.hasNext()) {
				checkPianificazioneInstance.get().remove(iterator.next());
				iterator.remove();
			}
		}
	}

	private void recalculateRaggruppamento(@NonNull final PromozionePianificazioneEntity set) {
		PlanningLevelEnum tipo = PlanningLevelEnum.fromCode(set.getTipoRiga().getCodiceTipo());
		if (PlanningLevelEnum.SET.equals(tipo)) {
			final AtomicInteger numRaggruppamento = new AtomicInteger(0);
			set.getChildren().stream().sorted(Comparator.comparingInt(e -> Integer.parseInt(e.getNumRaggruppamento())))
					.forEach(e -> e.setNumRaggruppamento(String.valueOf(numRaggruppamento.incrementAndGet())));
		} else {
			log.warn("Attempt to recalculate numRaggruppamento outside a set");
		}
	}

	public String emptyRaggruppamento(PromozionePianificazioneEntity pianificazione, List<String> codiciGruppo) {
		boolean success = false;
		String message = null;
		if (PlanningLevelEnum.RAGGRUPPAMENTO.getCode().equals(pianificazione.getTipoRiga().getCodiceTipo())) {

			if (pianificazione.getUploadFidaty() != null) {
				Iterator<UploadFidayEntity> iterator = pianificazione.getUploadFidaty().iterator();
				UploadFidayEntity upload = null;
				while (iterator.hasNext()) {
					upload = iterator.next();
					upload.setPromozionePianificazioneEntity(null);
					iterator.remove();
				}
			}

			if (pianificazione.getChildren() != null) {
				Iterator<PromozionePianificazioneEntity> iterator = pianificazione.getChildren().iterator();
				PromozionePianificazioneEntity detail = null;
				while (iterator.hasNext()) {
					detail = iterator.next();
					if (securityUtilInstance.get().isWriteable(detail, codiciGruppo)) {
						detail.setParent(null);
						try {
							PromozioneTestataEntity testata = pianificazione.getPromozioneTestataEntity();
							pianificazioneService.removePromozionePianificazioneEntity(detail);
							if (testata != null) {
								testata.removePromozionePianificazioneEntity(detail);
							}
							success = true;
						} catch (Exception e) {
							log.error("Errore durante svuotamento di raggruppamento " + pianificazione.getId(), e);

							addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
									"Errore durante il svuotamento del raggruppamento. Contattare l'assistenza"));

							success = false;
						}
						iterator.remove();
					}
				}
			}

			if (success) {
				message = "Svuotamento del raggruppamento avvenuto con successo";
			} else {
				message = "Errore durante il svuotamento del raggruppamento. Contattare l'assistenza";
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
						"Errore durante il svuotamento del raggruppamento. Contattare l'assistenza"));
			}
		}
		return message;
	}

	/**
	 * Questo metodo verifica che il valore da aggiornare nella pianificazione sia
	 * conforme col la configurazione (tipo di dato e range di valori, se presente
	 * in configurazione)
	 *
	 * @param pianificazione
	 * @param valueToBeUpdated
	 * @param lista
	 */
	public boolean validateUpdatedPlanningData(CfgPianificazioneEntity pianificazione, Object valueToBeUpdated,
											   String lista) {
		boolean isValueValidated = false;
		String valueToBeUpdatedAsString = null;
		String tipo = pianificazione.getMuiCfgPianificazioneCampi().getTipo().trim();
		switch (tipo.toUpperCase()) {
			case STRING:
				isValueValidated = valueToBeUpdated.getClass().equals(String.class)
						&& validateMinLength(String.valueOf(valueToBeUpdated), pianificazione)
						&& validateLength(String.valueOf(valueToBeUpdated), pianificazione);
				valueToBeUpdatedAsString = valueToBeUpdated.toString();
				break;
			case NUMBER:
			case INTEGER:
				valueToBeUpdatedAsString = String.valueOf(valueToBeUpdated);
				isValueValidated = valueToBeUpdatedAsString
						.contains(PianificazioneConstants.CONFIGURATION_FIELD_MULTISELECT_DOUBLE_PIPE_SEPARATOR)
						? validateAsMultiselect(valueToBeUpdatedAsString, pianificazione,
						NUMBER.equalsIgnoreCase(tipo))
						: validateAsSingleValue(valueToBeUpdatedAsString, pianificazione,
						NUMBER.equalsIgnoreCase(tipo));
				break;
			case DATE:
				Date date = null;
				if (valueToBeUpdated.getClass().equals(Date.class)) {
					date = (Date) valueToBeUpdated;
				}
				isValueValidated = date != null;
				valueToBeUpdatedAsString = dateTimeUtils.toExcelDate(date);
				break;
			case TIME:
				valueToBeUpdatedAsString = String.valueOf(valueToBeUpdated);
				isValueValidated = DateTimeUtils.toTime(valueToBeUpdatedAsString, false) != null;
				break;
			default:
				log.warn(String.format(
						"Value type is %s for columnd %s define in the entity PromozionePianificazioneEntity is not string, number, integer or date",
						pianificazione.getMuiCfgPianificazioneCampi().getCampo(), tipo));
		}
		if (isValueValidated && (lista != null)) {
			// Il valore aggiornato deve rientrare nel range dei valori della lista definito
			// nella configurazione
			List<String> listOfValues = getCorrectFormatPickListValues(lista);
			if (valueToBeUpdatedAsString
					.contains(PianificazioneConstants.CONFIGURATION_FIELD_MULTISELECT_DOUBLE_PIPE_SEPARATOR)) {
				String[] multiSelectValues = valueToBeUpdatedAsString.split(
						PianificazioneConstants.CONFIGURATION_FIELD_MULTISELECT_DOUBLE_PIPE_SEPARATOR_SPLIT_VERSION);
				// conto le corrispondenze tra i valori passati e i valori in lista
				long valueInLista = Arrays.stream(multiSelectValues).filter(listOfValues::contains).count();
				isValueValidated = (int) valueInLista == multiSelectValues.length;
			} else {
				isValueValidated = listOfValues.contains(valueToBeUpdatedAsString);
			}
		}
		return isValueValidated;
	}

	public boolean isClusterClienteDisabled(PromozionePianificazioneEntity pianificazione) {
		return isUploadComplementariBlocked(pianificazione);
	}

	public boolean isUploadComplementariBlocked(PromozionePianificazioneEntity pianificazione) {
		return hasCashbackMultitransazione(pianificazione)
				&& pianificazione.getPromozioneTestataEntity().getSottoscrizioni() != null
				&& !pianificazione.getPromozioneTestataEntity().getSottoscrizioni().isEmpty();
	}

	private boolean hasCashbackMultitransazione(PromozionePianificazioneEntity pianificazione) {
		return CODICI_MECCANICHE_CASHBACK.contains(pianificazione.getMeccanicaEntity().getCodiceMeccanica())
				&& isMultiTransazione(pianificazione);
	}

	private boolean isMultiTransazione(PromozionePianificazioneEntity pianificazione) {
		return "MULTITRANSAZIONE".equalsIgnoreCase(pianificazione.getMultiTransazione())
				|| pianificazione.getChildren().stream().anyMatch(this::isMultiTransazione);
	}

	/**
	 * Validazione valore come stringa multi-valori numerici (lista separata da ||)
	 *
	 * @param valueToBeUpdatedAsString valore da validare come stringa multi-valori
	 *                                 numerici
	 * @param pianificazione           configurazione pianificazione
	 * @param isNumber                 true se i valori sono decimali, false se
	 *                                 interi
	 * @return true se tutti i valori in lista sono validi, false altrimenti
	 */
	private boolean validateAsMultiselect(String valueToBeUpdatedAsString, CfgPianificazioneEntity pianificazione,
										  boolean isNumber) {
		final String[] values = valueToBeUpdatedAsString
				.split(PianificazioneConstants.CONFIGURATION_FIELD_MULTISELECT_DOUBLE_PIPE_SEPARATOR_SPLIT_VERSION);
		Predicate<String> predicate = s -> picklistUtils.isInteger(s);
		if (isNumber) {
			predicate = predicate.or(s -> picklistUtils.isNumber(s));
		}
		Predicate<String> predicate1 = s -> validateZeroAlloweed(s, pianificazione)
				&& validateMinLength(s, pianificazione, isNumber) && validateLength(s, pianificazione, isNumber);
		return Arrays.stream(values).allMatch(predicate.and(predicate1));
	}

	/**
	 * Validazione valore come numerico
	 *
	 * @param valueToBeUpdatedAsString valore numerico da validare
	 * @param pianificazione           configurazione pianificazione
	 * @param isNumber                 true se il valore è decimale, false se intero
	 * @return true se il valore è valido, false altrimenti
	 */
	private boolean validateAsSingleValue(String valueToBeUpdatedAsString, CfgPianificazioneEntity pianificazione,
										  boolean isNumber) {
		boolean isNumeric = picklistUtils.isInteger(valueToBeUpdatedAsString);
		if (isNumber) {
			isNumeric = isNumeric || picklistUtils.isNumber(valueToBeUpdatedAsString);
		}
		return isNumeric && validateZeroAlloweed(valueToBeUpdatedAsString, pianificazione)
				&& validateMinLength(valueToBeUpdatedAsString, pianificazione, isNumber)
				&& validateLength(valueToBeUpdatedAsString, pianificazione, isNumber);
	}

	private boolean validateZeroAlloweed(Object value, CfgPianificazioneEntity pianificazione) {
		if (pianificazione.getAllowZero() == 0) {
			return !value.equals("0");
		}
		return true;
	}

	private boolean validateMinLength(String value, CfgPianificazioneEntity pianificazione) {
		return (pianificazione.getMinLength() == null) || (value.length() >= pianificazione.getMinLength());
	}

	private boolean validateLength(String value, CfgPianificazioneEntity pianificazione) {
		return (pianificazione.getLength() == null) || (value.length() <= pianificazione.getLength());
	}

	private boolean validateMinLength(String value, CfgPianificazioneEntity pianificazione, boolean isNumber) {
		final Integer minLength = pianificazione.getMinLength();
		if (minLength == null) {
			return true;
		}
		return isNumber ? BigDecimal.valueOf(Double.parseDouble(value)).compareTo(BigDecimal.valueOf(minLength)) >= 0
				: Integer.parseInt(value) >= minLength;
	}

	private boolean validateLength(String value, CfgPianificazioneEntity pianificazione, boolean isNumber) {
		final Integer length = pianificazione.getLength();
		if (length == null) {
			return true;
		}
		return isNumber ? BigDecimal.valueOf(Double.parseDouble(value)).compareTo(BigDecimal.valueOf(length)) <= 0
				: Integer.parseInt(value) <= length;
	}

	/**
	 * Questo metodo ritorna le picklist nel corretto formato
	 *
	 * @param lista
	 * @return List<String>
	 */
	public List<String> getCorrectFormatPickListValues(String lista) {
		List<String> listOfValues = new ArrayList<>();
		if ((lista != null) &&
				lista.startsWith(PianificazioneConstants.CONFIGURATION_FIELD_LIST_LEFT_SQUARE_BRACKET)
				&& lista.endsWith(PianificazioneConstants.CONFIGURATION_FIELD_LIST_RIGHT_SQUARE_BRACKET)) {
			if (lista.contains(PianificazioneConstants.CONFIGURATION_FIELD_LIST_SEMICOLON_SEPARATOR)) {
				listOfValues = Arrays.asList(lista.substring(1, lista.length() - 1)
						.split(PianificazioneConstants.CONFIGURATION_FIELD_LIST_SEMICOLON_SEPARATOR));
			} else if (lista.contains(PianificazioneConstants.CONFIGURATION_FIELD_LIST_DOUBLE_DOT_SEPARATOR)) {
				String[] listLimitValuesArray = lista.substring(1, lista.length() - 1)
						.split(PianificazioneConstants.CONFIGURATION_FIELD_LIST_DOUBLE_DOT_SEPARATOR_SPLIT_VERSION);
				if (listLimitValuesArray.length == 2) {
					if (picklistUtils.isInteger(listLimitValuesArray[0])
							&& picklistUtils.isInteger(listLimitValuesArray[1])) {
						List<Integer> listRange = IntStream.rangeClosed(Integer.parseInt(listLimitValuesArray[0]),
								Integer.parseInt(listLimitValuesArray[1])).boxed().collect(Collectors.toList());
						listOfValues = listRange.stream().map(String::valueOf).collect(Collectors.toList());
					} else {
						log.warn(String.format("Both limit values of list %s are not of type integer", lista));
					}
				} else {
					log.warn(String.format("Values in list %s are more than 2 for format [valueX..valueY]", lista));
				}
			} else {
				listOfValues = Collections.singletonList(lista.substring(1, lista.length() - 1));
			}

		}
		return listOfValues;
	}

	/**
	 * Questo metodo verifica che il valore da aggiornare nella pianificazione sia
	 * utilizzabile, quindi non usato da nessun'altra promozione non cancellata il
	 * cui range di date (inizio e fine) contenga o si sovrapponga con quello
	 * dell'attuale promozione Ogni meccanica ha una sua lista di valori per ogni
	 * promozione.
	 *
	 * @param testate
	 * @param valueToBeUpdated
	 * @param columnToBeUpdated
	 */
	public boolean checkValueAvailability(List<PromozioneTestataEntity> testate, Object valueToBeUpdated,
										  String columnToBeUpdated, MeccanicheEntity meccanica) {
		boolean ret = false;
		for (PromozioneTestataEntity t : testate) {
			if (t != null) {
				for (PromozionePianificazioneEntity p : t.getPromozionePianificazioneEntities()) {
					if ((p.getMeccanicaEntity() != null) && p.getMeccanicaEntity().getId().equals(meccanica.getId())) {
						ret = valueToBeUpdated == null
								? Objects
								.isNull(visualizzaPianificazioneHelper.invokeGetterEntity(columnToBeUpdated, p))
								: valueToBeUpdated.equals(
								visualizzaPianificazioneHelper.invokeGetterEntity(columnToBeUpdated, p));
					}
					if (ret) {
						log.error("testata : " + t.getId() + " promozione " + p.getId() + " invalida "
								+ columnToBeUpdated + " con valore " + valueToBeUpdated);
						break;
					}
				}
			}
			if (ret) {
				break;
			}
		}
		return !ret;
	}

	/**
	 * Questo metodo definisce la lista dei valori della picklist per una
	 * determinata colonna recuperati dalla configurazione su database, escludendo
	 * quelli già usati dalle promozioni il cui range di date (inizio e fine)
	 * contenga o si sovrapponga con quello dell'attuale promozione. Ogni meccanica
	 * ha una sua lista di valori per ogni promozione.
	 *
	 * @param testata
	 * @param picklistValues
	 */
	public String[] createColumnValueArray(PromozioneTestataEntity testata, String[] picklistValues, String column,
										   MeccanicheEntity meccanicheEntity, List<CanalePromozioneEntity> canali) {

		final List<PromozioneTestataEntity> testataEntities = promoService.findOverlappingPromo(testata, canali);

		final List<String> correctPickList = new ArrayList<>(Arrays.asList(picklistValues));
		final List<String> lookupList = Arrays.asList(picklistValues);
		testataEntities.stream().filter(Objects::nonNull)
				.flatMap(entity -> entity.getPromozionePianificazioneEntities().stream()).filter(Objects::nonNull)
				.filter(entity -> meccanicheEntity.getId().equals(entity.getMeccanicaEntity().getId()))
				.forEach(entity -> {
					Object valueFromEntity = visualizzaPianificazioneHelper.invokeGetterEntity(column, entity);
					if ((valueFromEntity != null) && lookupList.contains((String) valueFromEntity)) {
						correctPickList.remove(String.valueOf(valueFromEntity));
					}
				});

		return correctPickList.toArray(new String[0]);
	}

	public boolean isDateUpdatedValid(PromozionePianificazioneEntity pianificazione, Date dataInizio, Date dataFine,
									  Date referenceDataInizio, Date referenceDataFine, Boolean changeDataInizio) {
		boolean result = isDateUpdatedValid(dataInizio, dataFine, referenceDataInizio, referenceDataFine);
		// devo controllare se ha figli
		if (result && !pianificazione.getChildren().isEmpty()) {
			Date nuovaDataInizio = null;
			Date nuovaDataFine = null;
			Date nuovaReferenceInizio = null;
			Date nuovaReferenceFine = null;
			for (PromozionePianificazioneEntity child : pianificazione.getChildren()) {
				// per ogni figlio
				if (changeDataInizio) {
					nuovaDataInizio = dataInizio;
					nuovaDataFine = child.getDataFine();
					nuovaReferenceInizio = dataInizio;
					nuovaReferenceFine = pianificazione.getDataFine();
				} else {
					nuovaDataInizio = child.getDataInizio();
					nuovaDataFine = dataFine;
					nuovaReferenceInizio = pianificazione.getDataInizio();
					nuovaReferenceFine = dataFine;
				}
				result = isDateUpdatedValid(child, nuovaDataInizio, nuovaDataFine, nuovaReferenceInizio,
						nuovaReferenceFine, changeDataInizio);
				if (!result) {
					break;
				}
			}
		}
		return result;
	}

	/**
	 * mi dice se dataInizio e dataFine sono compatibili tra loro e con le date del
	 * parent
	 *
	 * @param dataInizio
	 * @param dataFine
	 * @param referenceDataInizio
	 * @param referenceDataFine
	 * @return
	 */
	protected boolean isDateUpdatedValid(Date dataInizio, Date dataFine, Date referenceDataInizio,
										 Date referenceDataFine) {
		// dataInizio >= padre.dataInizio
		return dateTimeUtils.isAfter(dataInizio, referenceDataInizio, true, true)
				// dataFine <= padre.dataFine
				&& dateTimeUtils.isBefore(dataFine, referenceDataFine, true, true)
				// dataInizio <= dataFine
				&& dateTimeUtils.isBefore(dataInizio, dataFine, true, true);
	}

	/**
	 * Questo metodo determina se una meccanica sia ancora disponibile nella
	 * creazione di una pianificazione per una promozione in funzione del valore del
	 * campo RIPETIZIONE_MECCANICHE di MUI_CANALE_PROMOZIONE
	 *
	 * @param promozioneTestata
	 * @param codiceMeccanica
	 * @return
	 */
	public boolean isMeccanicaAvailable(PromozioneTestataEntity promozioneTestata, String codiceMeccanica) {

		CanalePromozioneEntity canalePromozione = canalePromozioneService
				.findByCodiceCanale(promozioneTestata.getCanalePromozioneEntity().getCodiceCanale());

		if (canalePromozione == null) {
			// non ci sono righe per il codice canale in MUI_CANALE_PROMOZIONE
			log.warn(String.format("Nella tabella MUI_CANALE_PROMOZIONE non sono presenti righe per il codiceCanale %s",
					promozioneTestata.getCanalePromozioneEntity().getCodiceCanale()));
			return true;
		} else if (canalePromozione.getRipetizioneMeccaniche() == null) {
			// RIPETIZIONE_MECCANICHE == null quindi non ci sono limitazioni nell'uso della
			// meccanica
			return true;
		} else {
			// RIPETIZIONE_MECCANICHE != null controllare che il numero di righe master di
			// MUI_PROMOZIONE_PIANIFICAZIONE che usano
			// il codice meccanica sia minore del valore di RIPETIZIONE_MECCANICHE
			long meccanicheMasterUsate = promozioneTestata.getPromozionePianificazioneEntities().stream()
					.filter(entity -> (entity.getParent() == null // Righe master
					) && codiceMeccanica.equals(entity.getMeccanicaEntity().getCodiceMeccanica())) // Righe master
					// che usano
					// il codice
					// meccanica
					.count();
			return meccanicheMasterUsate < canalePromozione.getRipetizioneMeccaniche().longValue();
		}
	}

	/**
	 * Questo metodo ritorna la lista dei progressivi disponibili non usati a parità
	 * del valore del campo BuonoScontoRadice effettuando il controllo solo
	 * all'interno della promozione corrente.
	 *
	 * @param testata
	 * @param buonoScontoRadice
	 * @param pickListValues
	 * @param range
	 * @return
	 */
	public List<Integer> getAvailableProgressiveDiscountCodesBuoniPotenziamento(
			@NonNull final PromozioneTestataEntity testata, final Integer buonoScontoRadice,
			List<String> pickListValues, final List<Integer> range) {
		final List<Integer> values = range != null ? range
				: pickListValues.stream().filter(value -> picklistUtils.isInteger(value)).map(Integer::parseInt)
				.collect(Collectors.toList());
		if (buonoScontoRadice == null) {
			log.warn(
					"Il valore per 'Buono Sconto Radice' non e' impostato; impossibile determinare i codici sconto progressivi");
			return new ArrayList<>();
		}
		final List<Integer> usedValues = pianificazioneService.getUsedProgressiveDiscountCodesBuoniPotenziamento(buonoScontoRadice,
				testata.getDataInizio(), testata.getDataFine());
		values.removeAll(usedValues);
		return values;
	}

	/**
	 * Questo metodo verifica che il valore inserito per codiceOnLine sia univoco
	 * per una promozione e l'insieme delle PROMOZIONI dei CANALI BUONO CATEGORIA e
	 * BUONI POTENZIAMENTO
	 * Quindi il codiceOnline può essere riusato per la stessa promozione nelle sue
	 * pianificazioni, ma non può essere riusato da promozioni differenti
	 * all'interno di uno stesso periodo
	 *
	 * @param testata
	 * @param valueToBeUpdated
	 * @return
	 */
	public boolean checkCodiceOnLineUniqueness(@NonNull final PromozioneTestataEntity testata, Object valueToBeUpdated,
											   List<CanalePromozioneEntity> canali) {
		List<String> codiciOnlineUsedByOtherTestate = promoService.findOverlappingPromo(testata, canali).stream()
				.filter(testataOverlap -> !testataOverlap.getId().equals(testata.getId())
						&& (PianificazioneConstants.BUONI_CATEGORIA
						.equals(testataOverlap.getCanalePromozioneEntity().getDescrizione())
						|| PianificazioneConstants.BUONI_POTENZIAMENTO
						.equals(testataOverlap.getCanalePromozioneEntity().getDescrizione())))
				.map(PromozioneTestataEntity::getPromozionePianificazioneEntities).flatMap(Collection::stream)
				.filter(Objects::nonNull).map(PromozionePianificazioneEntity::getCodiceOnline).distinct()
				.collect(Collectors.toList());
		return (valueToBeUpdated == null) || valueToBeUpdated.toString().isEmpty()
				// Non ci sono codiciOnline già usati in altre promozioni overlappate
				|| codiciOnlineUsedByOtherTestate.isEmpty()
				// Il codiciOnline non è ancora stato usato in altre promozioni overlappate
				|| !codiciOnlineUsedByOtherTestate.contains(valueToBeUpdated.toString());
	}

	/**
	 * Questo metodo aggiorna un JsonNode in funzione della seguente regola:
	 * I seguenti campi sono editabili solo se Numero Raggruppamento = 1: -
	 * Descrizione Sconto - Numero Utilizzi - Data fine - Data inizio - Stampa
	 * Offerta Scontrino Se il numero raggruppamento è <> da 1 devono essere
	 * valorizzati a null e non editabili
	 *
	 * @param node
	 * @param numeroRaggruppamentoAgCell
	 * @param pianificazionePromozione
	 */
	public boolean updateNodeByNumeroRaggruppamento(JsonNode node, DBPromoAgCell numeroRaggruppamentoAgCell,
													PromozionePianificazioneEntity pianificazionePromozione) {

		AtomicBoolean updated = new AtomicBoolean(false);

		final List<String> updateableColumns = Arrays.asList(
				PianificazioneConstants.REFERENCE_DESCRIZIONE_SCONTO_COLUMN,
				PianificazioneConstants.REFERENCE_NUMERO_UTILIZZI_COLUMN,
				PianificazioneConstants.REFERENCE_DATA_FINE_COLUMN,
				PianificazioneConstants.REFERENCE_DATA_INIZIO_COLUMN,
				PianificazioneConstants.REFERENCE_STAMPA_OFFERTA_SCONTRINO_COLUMN);

		updateableColumns.forEach(column -> {
			try {
				if (PianificazioneConstants.PRECARICATI_SU_CARTA_NUMERO_RAGGRUPPAMENTO_EDITABLE
						.equals(numeroRaggruppamentoAgCell.getValue())) {
					DbPromoAgCellUtils.putValueWithFieldName((ObjectNode) node, column, "true", "editable");
				} else {
					DbPromoAgCellUtils.putValueWithFieldName((ObjectNode) node, column, "false", "editable");
					DbPromoAgCellUtils.putValueWithFieldName((ObjectNode) node, column, null, "value");
					promoPianificazioneEntityHelper.invokeSetterEntity(pianificazionePromozione, column, null);
				}
				updated.set(true);
			} catch (IOException e) {
				log.error("Error parsing object to JSON", e);
				updated.set(false);
			}
		});
		return updated.get();
	}

	/**
	 * Questo metodo ripristina il valore persistito in caso di validazione fallita
	 *
	 * @param node
	 * @param column
	 * @param pianificazionePromozione
	 */
	@SuppressWarnings("null")
	public void resetPersistedValue(JsonNode node, String column,
									PromozionePianificazioneEntity pianificazionePromozione, Object defValue, Object valueToBeUpdated) {
		Object value;
		if ((valueToBeUpdated == null) && (defValue != null)) {
			value = defValue;
		} else {
			value = visualizzaPianificazioneHelper.invokeGetterEntity(column, pianificazionePromozione);
		}
		final String cellType = getCellTypeForField(pianificazionePromozione, column);
		final String valueAsString = planningCommons.toValue(value, cellType);
		DbPromoAgCellUtils.putValue(node, column, valueAsString, true);

	}

	/**
	 * Questo metodo restituisce il corretto numRaggruppamento in fase di
	 * inserimento seguendo la seguente logica:
	 * se già presenti una o più righe master il numero set va PREVALORIZATO con il
	 * MAX numero set utilizzato nella promozione, Il numero raggruppamenti ( valore
	 * a tendina da 1 a 5 ) deve assumere il primo valore disponibile se non
	 * presenti righe il numero set va PREVALORIZZATO con il primo VALORE
	 * disponibile nel periodo, il numero Raggruppamento con il valore 1 ( valore di
	 * default )
	 *
	 * @param testata
	 * @param range
	 * @return
	 */
	public String getNumeroRaggruppamentoPrecaricatiSuCarta(@NonNull final PromozioneTestataEntity testata,
															String range) {
		String numeroRaggruppamento = null;
		if (testata.getPromozionePianificazioneEntities() != null) {
			final List<String> numRaggruppamentoRange = getCorrectFormatPickListValues(range);
			if (testata.getPromozionePianificazioneEntities().isEmpty()) {
				numeroRaggruppamento = (numRaggruppamentoRange != null) && !numRaggruppamentoRange.isEmpty()
						? numRaggruppamentoRange.get(0)
						: null;
			} else {
				final PromozionePianificazioneEntity lastPianificazione = findLastPianificazionePrecaricatiSuCarta(
						testata, numRaggruppamentoRange.size());
				if ((lastPianificazione != null) && (lastPianificazione.getNumRaggruppamento() != null)) {
					final List<Integer> availableNumRaggruppamenti = getAvailableNumeroRaggruppamentoSuCarta(testata,
							lastPianificazione.getNumSet(), numRaggruppamentoRange, null);
					final Integer value = findCorrectListValue(availableNumRaggruppamenti.isEmpty()
							? numRaggruppamentoRange.stream().map(Integer::parseInt).collect(Collectors.toList())
							: availableNumRaggruppamenti);
					numeroRaggruppamento = value == null ? null : String.valueOf(value);
				} else {
					numeroRaggruppamento = numRaggruppamentoRange.get(0);
				}
			}
		}
		return numeroRaggruppamento;
	}

	/**
	 * Questo metodo restitusce per la promozione corrente la pianificazione con
	 * valore massimo sia per il numSet sia per il relativo numRaggruppamento
	 *
	 * @param testata
	 * @param sizeRangeNumRaggruppamento
	 * @return
	 */
	private PromozionePianificazioneEntity findLastPianificazionePrecaricatiSuCarta(
			@NonNull final PromozioneTestataEntity testata, Integer sizeRangeNumRaggruppamento) {
		// Caso dopo modifica, in inserimento controlla se per tutte le pianificazioni
		// per ogni numSet persistito hanno tutte le possibili coppie
		// numSet-numRaggruppamento
		final Map<String, List<String>> mapPianificazioni = testata.getPromozionePianificazioneEntities().stream()
				.filter(Objects::nonNull).filter(getLastPianificazioneMaxNumSetPredicate(testata))
				.collect(Collectors.groupingBy(PromozionePianificazioneEntity::getNumSet,
						Collectors.mapping(PromozionePianificazioneEntity::getNumRaggruppamento, Collectors.toList())));

		Map.Entry<String, List<String>> numSetRaggruppamentoIncompleto = mapPianificazioni.entrySet().stream()
				.filter(key -> key.getValue().size() != sizeRangeNumRaggruppamento).findFirst().orElse(null);

		Optional<PromozionePianificazioneEntity> ret = testata.getPromozionePianificazioneEntities().stream()
				.filter(Objects::nonNull)
				.filter(numSetRaggruppamentoIncompleto == null ? getLastPianificazioneMaxNumSetPredicate(testata)
						: getCompletePianificazioneNumSetRaggruppamentoPredicate(testata,
						numSetRaggruppamentoIncompleto))
				.max(Comparator.comparing(PromozionePianificazioneEntity::getNumSet)
						.thenComparing(PromozionePianificazioneEntity::getNumRaggruppamento));
		return ret.orElse(null);
	}

	private Predicate<PromozionePianificazioneEntity> getCompletePianificazioneNumSetRaggruppamentoPredicate(
			@NonNull final PromozioneTestataEntity testata,
			Map.Entry<String, List<String>> numSetRaggruppamentoIncompleto) {
		return pianificazione -> (pianificazione.getNumSet() != null) && (pianificazione.getNumRaggruppamento() != null)
				&& testata.getId().equals(pianificazione.getPromozioneTestataEntity().getId())
				&& numSetRaggruppamentoIncompleto.getKey().equals(pianificazione.getNumSet());
	}

	private Predicate<PromozionePianificazioneEntity> getLastPianificazioneMaxNumSetPredicate(
			@NonNull PromozioneTestataEntity testata) {
		return pianificazione -> (pianificazione.getNumSet() != null) && (pianificazione.getNumRaggruppamento() != null)
				&& testata.getId().equals(pianificazione.getPromozioneTestataEntity().getId());
	}

	/**
	 * Questo metodo restituisce la lista dei valori del range secondo questa regola
	 * Se è disponibile un solo valore restituisce quello
	 *
	 * @param range
	 * @return
	 */
	private Integer findCorrectListValue(List<Integer> range) {
		if (range.size() == 1) {
			return range.get(0);
		} else {
			return range.stream().min(Comparator.comparingInt(value -> value)).orElse(null);
		}
	}

	/**
	 * Questo metodo cerca il valore corretto per le colonne in cui non devono
	 * essere inclusi valori già usati nello stesso periodo della promozione e da
	 * ogni relativa meccanica
	 *
	 * @param pianificazione
	 * @param testata
	 * @param valore
	 * @param columnField
	 * @param pickListAllValues
	 * @return
	 */
	public Object getCorrectValuesSpecialColumn(PromozionePianificazioneEntity pianificazione,
												PromozioneTestataEntity testata, Object valore, String columnField, List<String> pickListAllValues,
												List<CanalePromozioneEntity> canali) {
		if ((pickListAllValues != null) && !pickListAllValues.isEmpty()) {
			String[] picklist = createColumnValueArray(testata, pickListAllValues.toArray(new String[0]), columnField,
					pianificazione.getMeccanicaEntity(), canali);
			if ((valore == null) || Arrays.stream(picklist).noneMatch(valore::equals)) {
				valore = picklist[0];
			}
		} else {
			log.warn(String.format("Error picklist null for column %s. Insert operation should be disabled",
					columnField));
		}
		return valore;
	}

	/**
	 * Questo metodo ritorna la lista dei num raggruppamento disponibili non usati a
	 * parità del valore del campo NumSet effettuando il controllo nella promozione
	 * corrente.
	 *
	 * @param testata
	 * @param pickListValues
	 * @param range
	 * @return
	 */
	public List<Integer> getAvailableNumeroRaggruppamentoSuCarta(@NonNull final PromozioneTestataEntity testata,
																 String numSet, List<String> pickListValues, List<Integer> range) {
		final List<Integer> values = range != null ? range
				: pickListValues.stream().filter(value -> picklistUtils.isInteger(value)).map(Integer::parseInt)
				.collect(Collectors.toList());
		final List<Integer> unavailableValues = getUsedNumeroRaggruppamentoPrecaricatiSuCarta(testata, numSet);
		values.removeAll(unavailableValues);
		return values;
	}

	/**
	 * Questo metodo restituisce la lista dei valori per il numRaggruppamento già
	 * usati dalla promozioni corrente I valori nella promozione corrente devono
	 * essere inclusi se e solo c'è una corrispondenza nel numSet
	 *
	 * @param testata
	 * @param numSet
	 * @return
	 */
	private List<Integer> getUsedNumeroRaggruppamentoPrecaricatiSuCarta(@NonNull final PromozioneTestataEntity testata,
																		String numSet) {
		return testata.getPromozionePianificazioneEntities().stream().filter(Objects::nonNull)
				.filter(pianificazione -> numSet.equals(pianificazione.getNumSet()))
				.map(PromozionePianificazioneEntity::getNumRaggruppamento).distinct().filter(Objects::nonNull)
				.map(Integer::parseInt).collect(Collectors.toList());
	}

	/**
	 * Recupero tipo cella per la pianificazione corrente relativo al dato campo
	 *
	 * @param promoPianificazione pianificazione corrente
	 * @param column              campo
	 * @return tipo cella per il determinato campo; default è string
	 */
	private String getCellTypeForField(@NonNull PromozionePianificazioneEntity promoPianificazione,
									   @NonNull String column) {
		final CfgConfHeaderEntity header = promoPianificazione.getPromozioneTestataEntity().getMuiCfgSetPianificazione()
				.getMuiCfgConfHeaders().stream()
				.filter(h -> h.getMeccanicaEntity().equals(promoPianificazione.getMeccanicaEntity())).findFirst()
				.orElse(null);
		if (header == null) {
			return DBPromoCellTypeEnum.STRING.getType();
		}
		final CfgPianificazioneEntity pianificazione = header.getMuiCfgPianificaziones().stream()
				.filter(p -> column.equals(p.getMuiCfgPianificazioneCampi().getCampo())).findFirst().orElse(null);
		return pianificazione == null ? DBPromoCellTypeEnum.STRING.getType()
				: pianificazione.getMuiCfgPianificazioneCampi().getTipo();
	}

	public void updateCheckCompratore(@NonNull PromozionePianificazioneEntity pianificazione, boolean value) {
		updateCheckCompratore(pianificazione, null, true);
	}

	public void updateCheckCompratore(@NonNull PromozionePianificazioneEntity pianificazione, PromozioneTestataEntity e,
									  boolean value) {
		if (e == null) {
			e = pianificazione.getPromozioneTestataEntity();
		}
		if (e == null) {
			log.error("informazioni di testata mancanti: impossibile aggiornare lo stato del check compratore");
			return;
		}
		ItemEntity item = getItemFromPianificazione(pianificazione);

		if (item != null && item.getCompratoreEntity() != null) {
			Set<CheckCompratoriEntity> checks = e.getCheckCompratori();
			if (checks != null) {
				checks.stream().filter(c -> c.getCompratoreEntity() != null)
						.filter(c -> c.getCompratoreEntity().getId().equals(item.getCompratoreEntity().getId()))
						.forEach(c -> c.setModificato(value));
			}
		}
	}

	private ItemEntity getItemFromPianificazione(@NonNull PromozionePianificazioneEntity pianificazione) {
		ItemEntity item = null;
		if ("ARTICOLO".equals(pianificazione.getTipoElemento())
				&& "E".equals(pianificazione.getTipoRiga().getCodiceTipo()) || pianificazione.getTipoRiga() == null) {
			try {
				item = itemServiceInstance.get().findById(Long.parseLong(pianificazione.getCodiceElemento()));
			} catch (Exception e) {
				log.error(String.format("Errore in conversione id item %s in long", pianificazione.getCodiceElemento()), e);
			}
		}
		return item;
	}

	/**
	 * Data una lista di configurazioni filtra tutte le configurazioni che dipendono
	 * da una chiave (altro campo in pianificazione)
	 * @param configurazioni
	 * @return
	 */
	public List<CfgPianificazioneEntity> getConfigurazioniWithChiave(List<CfgPianificazioneEntity> configurazioni){
		return configurazioni.stream()
				.filter(Objects::nonNull)
				.filter(c->org.apache.commons.lang3.StringUtils.isNotBlank(c.getChiave()))
				.collect(Collectors.toList());
	}

	/**
	 * Data una lista di configurazioni filtra tutte le configurazioni che dipendono dalla
	 * chiave passata in input
	 * @param configurazioni
	 * @param chiave
	 * @return
	 */
	public List<CfgPianificazioneEntity> getConfigurazioniWithChiave(List<CfgPianificazioneEntity> configurazioni, @NonNull String chiave){
		return configurazioni.stream()
				.filter(Objects::nonNull)
				.filter(c->org.apache.commons.lang3.StringUtils.isNotBlank(c.getChiave()))
				.filter(c->chiave.equals(c.getChiave()))
				.collect(Collectors.toList());
	}

	/**
	 * data la lista delle configurazioni con chiave, cerca se la chiave
	 * e' un elemento che appartiene alla lista
	 * @param configurazioni
	 * @param chiave
	 * @return
	 */
	public boolean configurazioneContainsChiave(@NonNull List<CfgPianificazioneEntity> configurazioni, @NonNull String chiave){
		return getConfigurazioniWithChiave(configurazioni).stream()
				.map(CfgPianificazioneEntity::getChiave)
				.filter(org.apache.commons.lang3.StringUtils::isNotBlank)
				.anyMatch(chiave::equals);
	}

	/**
	 * Data una configurazione: se la configurazione ha una lista allora ritorna quella, altrimenti:
	 *  se la configurazione ha una lista condizionale, il metodo cerca il valore corrente per la chiave dichiarata e
	 *  ritorna il valore della lista corrispondente al valore della chiave
	 * @param cfg : la configurazione per l'elemento di pianificazione corrente
	 * @param pianificazione : la riga di pianificazione corrente
	 * @return
	 */
	public String getLista(@NonNull CfgPianificazioneEntity cfg, PromozionePianificazioneEntity pianificazione) {
		String lista = cfg.getLista();
		if (org.apache.commons.lang3.StringUtils.isBlank(lista)) {
			// la lista e' vuota: prendo la lista corrispondente all'eventuale campo chiave
			final String chiave = cfg.getChiave();
			final String listaCondizionale = cfg.getListaCondizionale();
			if (org.apache.commons.lang3.StringUtils.isNotBlank(chiave) && org.apache.commons.lang3.StringUtils.isNotBlank(listaCondizionale)) {
				// devo prendere il codice campo corrispondente nel set delle pianificazioni
				CfgPianificazioneEntity cfgChiave = cfg.getMuiCfgConfHeader().getMuiCfgPianificaziones().stream()
						.filter(c -> c.getMuiCfgPianificazioneCampi().getCodiceCampo().equalsIgnoreCase(chiave))
						.findFirst().orElse(null);
				if ( cfgChiave != null ) {
					Object key = visualizzaPianificazioneHelper.invokeGetterEntity(cfgChiave.getMuiCfgPianificazioneCampi().getCampo(), pianificazione);
					if ( key != null ){
						// a questo punto prendo la configurazione della lista condizionale:
						try {
							//noinspection unchecked
							Map<Object, String> listaCondizionaleMap = JsonUtils.getMapper()
									.readValue(listaCondizionale, Map.class);
							if (listaCondizionaleMap != null) {
								lista = listaCondizionaleMap.get(key);
							}
						} catch (Exception e){
							log.error("Errore in configurazione database: lista condizionale non valida", e);
						}
					}
				}
			} else if (PianificazioneConstants.REFERENCE_NUM_SET_COLUMN.equals(cfg.getMuiCfgPianificazioneCampi().getCampo())
					&& org.apache.commons.lang3.StringUtils.isNotBlank(listaCondizionale)) {
				lista = numSetUtilsInstance.get().getListaByCfgPianificazioneAndCanale(cfg,
						pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity());
			}
		}
		return lista;
	}

	public boolean isPianificazioneBoundToDateCheck(@NonNull PromozioneTestataEntity testata){
		return testata.getCanalePromozioneEntity().getFlCheckDate();
	}

	public boolean isPianificazioneLockedOnDataInizio(@NonNull PromozionePianificazioneEntity pianificazione){
		if ( pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getFlCheckDate() ) {
			int tolleranza = pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getTolleranzaDataInizio();
			/**
			 * data inizio promozione e' data alle 00:00:00
			 * se data inizio + tolleranza e' minore di adesso allora la promozione e' bloccata
			 */
			LocalDateTime dataInizio = LocalTime.MIN.atDate(new java.sql.Date(pianificazione.getDataInizio().getTime()).toLocalDate());
			LocalDateTime oggi = LocalDateTime.now();

			return dataInizio.plusHours(tolleranza).isBefore(oggi);
		}
		return false;
	}

	public boolean isPianificazioneLockedOnDataFine(@NonNull PromozionePianificazioneEntity pianificazione){
		/**
		 * data fine promozione e' data alle 00:00:00
		 * se data fine + tolleranza e' minore di adesso allora la promozione e' bloccata
		 */
		if ( pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getFlCheckDate() ) {
			int tolleranza = pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getTolleranzaDataFine();
			LocalDateTime dataFine = LocalTime.MIN.atDate(new java.sql.Date(pianificazione.getDataFine().getTime()).toLocalDate());
			LocalDateTime oggi = LocalDateTime.now();

			return dataFine.plusHours(tolleranza).isBefore(oggi);
		}
		return false;
	}

	public boolean isPianificazioneDeleteEnabledAfterDataFine(@NonNull PromozionePianificazioneEntity pianificazione){
		boolean result = false;
		if ( pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getFlCheckDateOverrideDelete() ) {
			int tolleranza = pianificazione.getPromozioneTestataEntity().getCanalePromozioneEntity().getDeleteActiveDaysAfterDataFine();
			LocalDateTime dataFine = LocalTime.MIN.atDate(new java.sql.Date(pianificazione.getDataFine().getTime()).toLocalDate());
			LocalDateTime oggi = LocalDateTime.now();

			result = dataFine.plusDays(tolleranza).isBefore(oggi);
		}
		return result;
	}

	public void checkCodiceIniziativa(PromozionePianificazioneEntity pianificazione) {
		if (pianificazione.getCodiceIniziativa() != null) {
			final boolean isValid = muiIniziativaServiceInstance.get()
					.findAllByDataInizioAndDataFine(pianificazione.getDataInizio(), pianificazione.getDataFine())
					.stream()
					.map(MuiIniziativaEntity::getCodiceIniziativa)
					.anyMatch(c -> pianificazione.getCodiceIniziativa().equals(c));
			if (!isValid) {
				pianificazione.setCodiceIniziativa(null);
			}
		}
	}
}
