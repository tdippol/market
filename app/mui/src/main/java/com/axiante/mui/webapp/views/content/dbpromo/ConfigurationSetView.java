package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.CfgLivelloPianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.CfgSetPianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.MeccanicaService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneCampiService;
import com.axiante.mui.dbpromo.persistence.service.TipoRigaService;
import com.axiante.mui.webapp.views.MuiViewModel;
import com.axiante.mui.webapp.views.content.dbpromo.data.DuplicaSetBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.HeaderDialogBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.RegolaDialogBackingBean;
import com.axiante.mui.webapp.views.content.dbpromo.data.TipoElementoDialogBackingBean;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@MuiViewModel("configurationSet")
@Dependent
@Slf4j
public class ConfigurationSetView extends BasePromoView {

	private static final long serialVersionUID = 3893734129205456784L;
	private final List<String> NOT_PLANNED_STATES = Arrays.asList("00", "10");
	private static final String SEMAPHORE_RED = "#F70000";
	private static final String SEMAPHORE_ORANGE = "#FF8457";
	private static final String SEMAPHORE_GREEN = "#1CAC81";
	private static final String SEMAPHORE_NONE = "none";

	@Inject
	private CfgSetPianificazioneService cfgSetPianificazioneService;

	@Inject
	private TipoRigaService tipoRigaService;

	@Inject
	private PianificazioneCampiService pianificazioneCampiService;

	@Inject
	private CfgLivelloPianificazioneService livelloPianificazioneService;

	@Inject
	private MeccanicaService meccanicaService;

	@Inject
	private CfgConfHeaderService headerService;

	@Getter
	Long idSetPianificazioneCorrente;

	@Getter
	List<CfgSetPianificazioneEntity> setPianificazioni;

	@Getter
	CfgSetPianificazioneEntity pianificazioneSelected;

	@Getter
	PromozioneTestataEntity promotionSelected;

	@Getter
	StatoPromozioneEntity statoPromozioneSelected;

	@Getter
	List<PromozioneTestataEntity> promozioni;

	@Getter
	PromozioneTestataEntity promozioneCorrente;

	@Getter
	Object idPromozioneCorrente;

	@Getter
	String pianificazioneSelectedStatoColor;

	@Getter
	@Setter
	private DuplicaSetBackingBean duplicaSetBackingBean;

	@Getter
	@Setter
	private HeaderDialogBackingBean headerDialogBackingBean;

	@Getter
	@Setter
	private TipoElementoDialogBackingBean tipoElementoDialogBackingBean;

	@Getter
	@Setter
	private RegolaDialogBackingBean regolaDialogBackingBean;

	@Getter
	@Setter
	private boolean blockModeActive;

	private boolean isDuplicate = false;

	@Getter
    private boolean btnAddHeaderDisabled = true;

	@Getter
    private boolean btnAddTipoElementoDisabled = true;

	@Getter
    private boolean btnAddRegolaDisabled = true;

	@Getter
	@Setter
	private String removePlanningIdHeader;

	@Getter
	@Setter
	private String removePlanningIdTipoElemento;

	@Getter
	@Setter
	private String removePlanningIdRegola;

	@Getter
	private String confirmDeleteMessage;

	private List<CfgLivelloPianificazioneEntity> livelli;
	private List<MeccanicheEntity> meccanicheAvailable;
	private List<CfgPianificazTipoRigaEntity> tipoRighe;
	private List<CfgPianificazioneCampiEntity> campi;

	@Getter
	@Setter
	private Long idHeaderSelected;

	@PostConstruct
	public void init() {
		readSetPianificazioni();
		if (setPianificazioni != null && setPianificazioni.size() > 0) {
			final CfgSetPianificazioneEntity pianificazione = setPianificazioni.get(0);
			setConfigurationSetCorrente(pianificazione);
			if ( pianificazione.getMuiCfgConfHeaders() != null ) {
				Optional<CfgConfHeaderEntity> header = pianificazione.getMuiCfgConfHeaders().stream().min((a, b) -> {
					try {
						return a.getMeccanicaEntity().getCodiceMeccanica().compareTo(b.getMeccanicaEntity().getCodiceMeccanica());
					} catch (NullPointerException e) {
						log.error("NPE !!! you should check this comparator", e);
					}
					return 0;
				});
				if ( header.isPresent())
					idHeaderSelected = header.get().getId();
				else { 
					log.info("nessun header presente per la pianificazione " + pianificazione.getDescrizione());
					idHeaderSelected = -1L;
				}
				
			}
		}
		headerDialogBackingBean = new HeaderDialogBackingBean();
		tipoElementoDialogBackingBean = new TipoElementoDialogBackingBean();
		regolaDialogBackingBean = new RegolaDialogBackingBean();
		readLivelliPianificazione();
		readTipoRighe();
		readCampi();
	}

	public void lockButton() {
		try {
			final long lockedFlag = blockModeActive ? 1 : 0;
			if (pianificazioneSelected != null) {
				pianificazioneSelected.setLocked(lockedFlag);
				cfgSetPianificazioneService.persist(pianificazioneSelected, getCurrentUser().getName());
			} else {
				addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Errore", "Nessuna pianicazione selezionata"));
			}
			setStateColor();
			updateGridButtons();
		} catch (final Exception ex) {
			final String msg = "Errore durante il cambiamento del configurazione del Set Pianificazione";
			log.warn(msg, ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Errore", msg));
		}

	}

	public void initDialog(boolean isDuplicate) {
		final Date minDateSelectable = calculateMinimumDate();
		this.isDuplicate = isDuplicate;
		final String description = isDuplicate ? preparaDescrizione() : "";
		duplicaSetBackingBean = new DuplicaSetBackingBean();
		duplicaSetBackingBean.setDescrizione(description);
		duplicaSetBackingBean.setData(minDateSelectable);
		duplicaSetBackingBean.setMinDate(minDateSelectable);
	}

	public void initHeaderDialog() {
		headerDialogBackingBean.resetDialog();
		reloadCurrentSetPianificazione();
		readMeccanicheAvailableForSet(pianificazioneSelected);
		headerDialogBackingBean.setLivelli(livelli);
		headerDialogBackingBean.setMeccaniche(meccanicheAvailable);
	}

	public void initTipoElementoDialog() {
		tipoElementoDialogBackingBean.resetDialog();
		reloadCurrentSetPianificazione();
	}

	public void initRegolaDialog() {
		regolaDialogBackingBean.resetDialog();
		reloadCurrentSetPianificazione();
		regolaDialogBackingBean.setTipoRighe(tipoRighe);
		regolaDialogBackingBean.setCampi(campi);
	}

	public void confirmHeaderDialog() {
		if (headerDialogBackingBean.isValid()) {
			try {
				createHeader();
				reloadCurrentSetPianificazione();
			} catch (Exception ex) {
				final String msg = String.format("Errore nella creazione nuovo header per il set pianificazione %s (%s)",
						idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
				log.warn(msg, ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
			}
		} else {
			final String msg = String.format("Header per il set pianificazione %s (%s) non valido",
					idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
			log.warn(msg);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
		}
	}

	private void createHeader() {
		final CfgConfHeaderEntity headerEntity = new CfgConfHeaderEntity();
		headerEntity.setMeccanicaEntity(headerDialogBackingBean.getMeccanicaSelected());
		headerEntity.setLivelloPianificazione(headerDialogBackingBean.getLivelloSelected());
		headerEntity.setMinSet(headerDialogBackingBean.getMinSet());
		headerEntity.setMaxSet(headerDialogBackingBean.getMaxSet());
		headerEntity.setMinRaggruppamento(headerDialogBackingBean.getMinRaggr());
		headerEntity.setMaxRaggruppamento(headerDialogBackingBean.getMaxRaggr());
		headerEntity.setUnicaInPromo(headerDialogBackingBean.isUnicaInPromo() ? 1 : 0);
		pianificazioneSelected.addMuiCfgConfHeaderEntity(headerEntity);
		cfgSetPianificazioneService.persist(pianificazioneSelected, getCurrentUser().getName());
		final String msg = String.format("Header con meccanica %s e livello %s creato correttamente "
						+ "per il set pianificazione %s (%s)",
				headerDialogBackingBean.getMeccanicaSelected().getCodiceMeccanica(),
				headerEntity.getLivelloPianificazione().getCodice(),
				idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
		log.debug(msg);
		addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
	}

	public void confirmTipoElementoDialog() {
		if (tipoElementoDialogBackingBean.isValid()) {
			try {
				CfgConfHeaderEntity headerEntity = getHeaderFromSetPianificazione(String.valueOf(idHeaderSelected));
				if (headerEntity == null) {
					final String msg = String.format("Header associato al set pianificazione %s (%s) non esistente",
							idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
					log.warn(msg);
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
				} else if (!validateRaggruppamento(headerEntity, tipoElementoDialogBackingBean.getRaggruppamento())) {
					final String msg = String.format("Il valore settato per 'raggruppamento' %s è già utilizzato "
									+ "all'interno del header con meccanica '%s' e set '%s'",
							tipoElementoDialogBackingBean.getRaggruppamento(),
							headerEntity.getMeccanicaEntity().getCodiceMeccanica(),
							headerEntity.getLivelloPianificazione().getCodice());
					log.warn(msg);
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
				} else {
					createTipoElemento(headerEntity);
					reloadCurrentSetPianificazione();
				}
			} catch (Exception ex) {
				final String msg = String.format("Errore nella creazione nuovo tipo elemento associato a header "
								+ "per il set pianificazione %s (%s)",
						idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
				log.warn(msg, ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
			}
		} else {
			final String msg = String.format("Tipo elemento per il set pianificazione %s (%s) non valido",
					idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
			log.warn(msg);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
		}
	}

	private void createTipoElemento(CfgConfHeaderEntity headerEntity) {
		final CfgTipoElementoEntity tipoElementoEntity = new CfgTipoElementoEntity();
		tipoElementoEntity.setRaggruppamento(tipoElementoDialogBackingBean.getRaggruppamento());
		tipoElementoEntity.setTotale(tipoElementoDialogBackingBean.isTotale() ? 1 : 0);
		tipoElementoEntity.setReparto(tipoElementoDialogBackingBean.isReparto() ? 1 : 0);
		tipoElementoEntity.setGrm(tipoElementoDialogBackingBean.isGrm() ? 1 : 0);
		tipoElementoEntity.setArticolo(tipoElementoDialogBackingBean.isArticolo() ? 1 : 0);
		tipoElementoEntity.setAttributo(tipoElementoDialogBackingBean.isAttributo() ? 1 : 0);
		tipoElementoEntity.setOmogeneo(tipoElementoDialogBackingBean.isOmogeneo() ? 1 : 0);
		headerEntity.addTipoElemento(tipoElementoEntity);
		headerService.persist(headerEntity);
		final String msg = String.format("Tipo elemento associato ad header con meccanica %s e livello %s "
						+ "creato correttamente per il set pianificazione %s (%s)",
				headerEntity.getMeccanicaEntity().getCodiceMeccanica(),
				headerEntity.getLivelloPianificazione().getCodice(),
				idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
		log.debug(msg);
		addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
	}

	public void confirmRegolaDialog() {
		if (regolaDialogBackingBean.isValid()) {
			try {
				CfgConfHeaderEntity headerEntity = getHeaderFromSetPianificazione(String.valueOf(idHeaderSelected));
				if (headerEntity != null) {
					createRegola(headerEntity);
					reloadCurrentSetPianificazione();
				} else {
					final String msg = String.format("Header associato al set pianificazione %s (%s) non esistente",
							idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
					log.warn(msg);
					addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
				}
			} catch (Exception ex) {
				final String msg = String.format("Errore nella creazione nuova regola associato a header "
								+ "per il set pianificazione %s (%s)",
						idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
				log.warn(msg, ex);
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
			}
		} else {
			final String msg = String.format("Regola associata a header per il set pianificazione %s (%s) non valida",
					idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
			log.warn(msg);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", msg));
		}
	}

	private void createRegola(CfgConfHeaderEntity headerEntity) {
		final CfgPianificazioneEntity pianificazioneEntity = new CfgPianificazioneEntity();
		pianificazioneEntity.setMuiCfgPianificazTipoRiga(regolaDialogBackingBean.getTipoRigaSelected());
		pianificazioneEntity.setMuiCfgPianificazioneCampi(regolaDialogBackingBean.getCampoSelected());
		headerEntity.addPianificazione(pianificazioneEntity);
		headerService.persist(headerEntity);
		final String msg = String.format("Regola associata ad header con meccanica %s e livello %s "
						+ "creata correttamente per il set pianificazione %s (%s)",
				headerEntity.getMeccanicaEntity().getCodiceMeccanica(),
				headerEntity.getLivelloPianificazione().getCodice(),
				idSetPianificazioneCorrente, pianificazioneSelected.getDescrizione());
		log.debug(msg);
		addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
	}

	public void confirmBtnClickedDuplicaSet() {
		try {
			final CfgSetPianificazioneEntity openSet = cfgSetPianificazioneService.findOpenSet();
			final CfgSetPianificazioneEntity setPianificazione = isDuplicate
					? cfgSetPianificazioneService.duplicateSet(openSet, pianificazioneSelected,
							getDuplicaSetBackingBean().getDescrizione(), getDuplicaSetBackingBean().getData(),
							getCurrentUser().getName())
					: cfgSetPianificazioneService.createSet(openSet, getDuplicaSetBackingBean().getDescrizione(),
							getDuplicaSetBackingBean().getData(), getCurrentUser().getName());
			if (setPianificazione != null) {
				final String msg = String.format("Set Pianificazione %s correttamente",
						isDuplicate ? "duplicato" : "creato");
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
				readSetPianificazioni();
				setConfigurationSetCorrente(setPianificazione);
			}
		} catch (final Exception ex) {
			final String msg = String.format("Errore durante la %s del Set Pianificazione",
					isDuplicate ? "duplicazione" : "creazione");
			log.warn(msg, ex);
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
					String.format("%s\n%s", msg, ex.getMessage())));
		}
	}

	public void setIdSetPianificazioneCorrente(Long idSetPianificazioneCorrente) {
		this.idSetPianificazioneCorrente = idSetPianificazioneCorrente;
		setPianificazioneSelected(getSetPianificazioneFromId(idSetPianificazioneCorrente));
		setStateColor();
		this.btnAddHeaderDisabled = idSetPianificazioneCorrente == null
				|| pianificazioneSelected == null
				|| meccanicheAvailable.isEmpty()
				|| !PromoAcl.isCfgPianificazioneEditable(pianificazioneSelected);
		this.btnAddTipoElementoDisabled = true;
		this.btnAddRegolaDisabled = true;
	}

	public void updateGridButtons() {
		if (idSetPianificazioneCorrente == null || pianificazioneSelected == null) {
			this.btnAddHeaderDisabled = true;
			this.btnAddTipoElementoDisabled = true;
			this.btnAddRegolaDisabled = true;
		} else {
			// Get params from remoteCommand
			final Map<String, String> params = getRequestParameterMap();
			boolean rowSelected = false;
			try {
				rowSelected = Boolean.parseBoolean(params.get("rowSelected"));
			} catch (Exception ex) {
				log.error("Unable to get rowSelected from header grid", ex);
			}
			final boolean editable = PromoAcl.isCfgPianificazioneEditable(pianificazioneSelected);
			this.btnAddHeaderDisabled = meccanicheAvailable.isEmpty() || !editable;
			this.btnAddTipoElementoDisabled = !(editable && rowSelected);
			this.btnAddRegolaDisabled = !(editable && rowSelected);
		}
	}

	public void updateIdHeader() {
		final String idHeader = getRequestParameterMap().get("idHeader");
		if (idHeader == null || idHeader.trim().isEmpty()) {
			idHeaderSelected = null;
		} else {
			try {
				idHeaderSelected = Long.parseLong(idHeader);
			} catch (Exception ex) {
				log.error("Unable to get rowSelected from header grid", ex);
			}
		}
	}

	public void resetHeaderId() {
		setIdHeaderSelected(null);
		this.btnAddTipoElementoDisabled = true;
		this.btnAddRegolaDisabled = true;
		reloadCurrentSetPianificazione();
		readMeccanicheAvailableForSet(pianificazioneSelected);
	}

	private void setStateColor() {
		if (pianificazioneSelected != null) {
			final String stateColor = isBlockModeActive() ? SEMAPHORE_ORANGE
					: hasPromozioniInCorrectState() ? SEMAPHORE_GREEN : SEMAPHORE_RED;
			setPianificazioneSelectedStatoColor(stateColor);
		} else {
			setPianificazioneSelectedStatoColor(SEMAPHORE_NONE);
		}
	}

	public void setPianificazioneSelected(CfgSetPianificazioneEntity pianificazioneSelected) {
		this.pianificazioneSelected = pianificazioneSelected;
		if (pianificazioneSelected != null) {
			setBlockModeActive(
					getPianificazioneSelected().getLocked() != null && getPianificazioneSelected().getLocked().equals(1L));
			readMeccanicheAvailableForSet(pianificazioneSelected);
		} else {
			setBlockModeActive(false);
		}
	}

	public void setPianificazioneSelectedStatoColor(String pianificazioneSelectedStatoColor) {
		this.pianificazioneSelectedStatoColor = pianificazioneSelectedStatoColor;
	}

	public String preparaDescrizione() {
		return pianificazioneSelected != null ? String.format("%s - Copia", pianificazioneSelected.getDescrizione())
				: null;
	}

	/**
	 * The minimum selectable date must be the maximum between: - max start date of
	 * promotions associated with open set (set with end date equals to NULL) -
	 * start date of open set plus 1 day - current day plus 1 day (tomorrow)
	 *
	 * @return the minimum selectable date
	 */
	private Date calculateMinimumDate() {
		final Date tomorrow = tomorrowOf(new Date());
		final CfgSetPianificazioneEntity openSet = cfgSetPianificazioneService.findOpenSet();
		if (openSet == null) {
			return tomorrow;
		}
		final Date startDate = tomorrowOf(openSet.getDataInizio());
		final PromozioneTestataEntity e = openSet.getPromozioneTestataEntities().stream()
				.max(Comparator.comparing(PromozioneTestataEntity::getDataInizio)).orElse(null);
		final Date promotionDate = e != null ? tomorrowOf(e.getDataInizio()) : null;
		final List<Date> dates = Arrays.asList(tomorrow, startDate, promotionDate);
		return dates.stream().filter(Objects::nonNull).max(Date::compareTo).orElse(tomorrow);
	}

	/**
	 * Return the "tomorrow" of a given date
	 *
	 * @param date current date
	 * @return date plus 1 day
	 */
	private Date tomorrowOf(Date date) {
		if (date == null) {
			return null;
		}
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	private void readSetPianificazioni() {
		setPianificazioni = cfgSetPianificazioneService.getAllEnabled();
		log.debug("Trovati {} set pianificazioni", setPianificazioni.size());
	}

	private void setConfigurationSetCorrente(CfgSetPianificazioneEntity pianificazione) {
		setPianificazioneSelected(pianificazione);
		if (pianificazioneSelected != null) {
			setIdSetPianificazioneCorrente(pianificazioneSelected.getId());
		}
	}

	/**
	 * Check if pianificazione has promozioni in correct state: DATA_FINE_STATO IS
	 * NULL && STATO_PROMOZIONE IN ('00', '10')
	 *
	 * @return true when condition meets, false otherwise
	 */
	private boolean hasPromozioniInCorrectState() {
		final Set<PromozioneTestataEntity> testate = pianificazioneSelected.getPromozioneTestataEntities();
		return testate.stream()
				.noneMatch(t -> t.getPromozioneStatoEntities().stream().anyMatch(ps -> ps.getDataFineStato() == null
						&& !NOT_PLANNED_STATES.contains(ps.getStatoPromozioneEntity().getCodiceStato())));
	}

	/**
	 * Open a confirm dialog before calling ws to remove selected header, with all his childs
	 * (MUI_CFG_CONF_HEADER with associated MUI_CFG_TIPO_ELEMENTO and MUI_CFG_PIANIFICAZIONE)
	 */
	public void removePlanningHeader() {
		final String idHeader = getRequestParameterMap().get("idHeader");
		final CfgConfHeaderEntity header = getHeaderFromSetPianificazione(idHeader);
		if (header == null) {
			log.error(String.format("Error getting header %s from set pianificazione %s",
					idHeader, pianificazioneSelected.getId()));
			return;
		}
		confirmDeleteMessage = String.format("Sei sicuro di voler cancellare l'header con meccanica '%s' e livello '%s' " +
						"e tutti i tipi elemento e le regole associate?",
				header.getMeccanicaEntity().getCodiceMeccanica(), header.getLivelloPianificazione().getCodice());
		setRemovePlanningIdHeader(idHeader);
		executeScript("PF('removePlanningHeader_dialog').show();");
	}

	/**
	 * Open a confirm dialog before calling ws to remove selected tipo elemento (MUI_CFG_TIPO_ELEMENTO)
	 */
	public void removePlanningTipoElemento() {
		final Map<String, String> params = getRequestParameterMap();
		final String idHeader = params.get("idHeader");
		final String idTipoElemento = params.get("idTipoElemento");
		final CfgConfHeaderEntity header = getHeaderFromSetPianificazione(idHeader);
		if (header == null) {
			log.error(String.format("Error getting header %s from set pianificazione %s",
					idHeader, pianificazioneSelected.getId()));
			return;
		}
		try {
			Long id = Long.parseLong(idTipoElemento);
			final CfgTipoElementoEntity tipoElemento = header.getTipiElemento().stream()
					.filter(e -> id.equals(e.getId())).findFirst().orElse(null);
			if (tipoElemento == null) {
				log.error(String.format("Error getting tipo elemento %s from header %s",
						idTipoElemento, idHeader));
				return;
			}
			confirmDeleteMessage = String.format("Sei sicuro di voler cancellare il tipo elemento 'raggruppamento %s' " +
							"per l'header con meccanica '%s' e livello '%s'?",
					tipoElemento.getRaggruppamento(),
					header.getMeccanicaEntity().getCodiceMeccanica(),
					header.getLivelloPianificazione().getCodice());
			setRemovePlanningIdTipoElemento(idTipoElemento);
			executeScript("PF('removePlanningTipoElemento_dialog').show();");
		} catch (Exception ex) {
			log.error("Error getting idTipoElemento from request params " + idTipoElemento, ex);
		}
	}

	/**
	 * Open a confirm dialog before calling ws to remove selected regola (MUI_CFG_PIANIFICAZIONE)
	 */
	public void removePlanningRegola() {
		final Map<String, String> params = getRequestParameterMap();
		final String idHeader = params.get("idHeader");
		final String idCfgPianificazione = params.get("idCfgPianificazione");
		final CfgConfHeaderEntity header = getHeaderFromSetPianificazione(idHeader);
		if (header == null) {
			log.error(String.format("Error getting header %s from set pianificazione %s",
					idHeader, pianificazioneSelected.getId()));
			return;
		}
		try {
			Long id = Long.parseLong(idCfgPianificazione);
			final CfgPianificazioneEntity pianificazione = header.getMuiCfgPianificaziones().stream()
					.filter(p -> id.equals(p.getId())).findFirst().orElse(null);
			if (pianificazione == null) {
				log.error(String.format("Error getting pianificazione %s from header %s", idCfgPianificazione, idHeader));
				return;
			}
			confirmDeleteMessage = String.format("Sei sicuro di voler cancellare la pianificazione tipo riga '%s' " +
					"e campo '%s' per l'header con meccanica '%s' e livello '%s'?",
					pianificazione.getMuiCfgPianificazTipoRiga().getDescrizione(),
					pianificazione.getMuiCfgPianificazioneCampi().getCampo(),
					header.getMeccanicaEntity().getCodiceMeccanica(),
					header.getLivelloPianificazione().getCodice());
			setRemovePlanningIdRegola(idCfgPianificazione);
			executeScript("PF('removePlanningRegola_dialog').show();");
		} catch (Exception ex) {
			log.error("Error getting idCfgPianificazione from request params " + idCfgPianificazione, ex);
		}
	}

	/**
	 * Retrieve Header with given id from selected SetPianificazione
	 * @param idHeader header id
	 * @return retrieved header or null if no header found
	 */
	private CfgConfHeaderEntity getHeaderFromSetPianificazione(String idHeader) {
		try {
			Long id = Long.parseLong(idHeader);
			return pianificazioneSelected.getMuiCfgConfHeaders().stream()
					.filter(h -> id.equals(h.getId())).findFirst().orElse(null);
		} catch (Exception ex) {
			log.error("Error getting idHeader from request params " + idHeader, ex);
			return null;
		}
	}

	/**
	 * Retrieve, from all loaded, current MUI_CFG_SET_PIANIFICAZIONE by id
	 * @param id cfgSetPianificazione id
	 * @return current MUI_CFG_SET_PIANIFICAZIONE, or null if not found
	 */
	private CfgSetPianificazioneEntity getSetPianificazioneFromId(Long id) {
		return id == null
				? null
				: setPianificazioni.stream().filter(p -> id.equals(p.getId())).findFirst().orElse(null);
	}

	/**
	 * Retrieve all available MUI_CFG_LIV_PIANIFICAZIONE
	 */
    private void readLivelliPianificazione() {
        livelli = livelloPianificazioneService.findAll().stream()
                .sorted(Comparator.comparing(CfgLivelloPianificazioneEntity::getCodice))
                .collect(Collectors.toList());
    }

	/**
	 * Retrieve all available MUI_MECCANICA, e.g. all 'meccaniche' not used in current 'set pianificazione'
	 * @param pianificazioneSelected current MUI_CFG_SET_PIANIFICAZIONE
	 */
	private void readMeccanicheAvailableForSet(CfgSetPianificazioneEntity pianificazioneSelected) {
		final List<MeccanicheEntity> usedMeccaniche = pianificazioneSelected.getMuiCfgConfHeaders().stream()
				.map(CfgConfHeaderEntity::getMeccanicaEntity)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		meccanicheAvailable = meccanicaService.findAll().stream()
				.filter(m -> !usedMeccaniche.contains(m))
				.sorted(Comparator.comparing(MeccanicheEntity::getCodiceMeccanica))
				.collect(Collectors.toList());
	}

	/**
	 * Retrieve all available MUI_CFG_PIANIFICAZ_TIPO_RIGA
	 */
	private void readTipoRighe() {
		tipoRighe = tipoRigaService.findAll().stream()
				.sorted(Comparator.comparing(CfgPianificazTipoRigaEntity::getCodiceTipo))
				.collect(Collectors.toList());
	}

	/**
	 * Retrieve all available MUI_CFG_PIANIFICAZIONE_CAMPI
	 */
	private void readCampi() {
		campi = pianificazioneCampiService.findAll().stream()
				.sorted(Comparator.comparing(CfgPianificazioneCampiEntity::getCampo))
				.collect(Collectors.toList());
	}

	/**
	 * Check if value for 'raggruppamento' is valid.
	 * A 'raggruppamento' is valid when is not already used in given header
	 * @param headerEntity header
	 * @param raggruppamento value to be setted on new 'TipoElemento'
	 * @return true if raggruppamento is valid, false otherwise
	 */
	private boolean validateRaggruppamento(CfgConfHeaderEntity headerEntity, Integer raggruppamento) {
		if (raggruppamento == null) {
			return false;
		}
		return headerEntity.getTipiElemento().stream().noneMatch(t -> t.getRaggruppamento().equals(raggruppamento));
	}

	/**
	 * Reload current set pianificazione
	 */
	private void reloadCurrentSetPianificazione() {
		readSetPianificazioni();
		setIdSetPianificazioneCorrente(idSetPianificazioneCorrente);
	}

	@Override
	public void applyRules() {
		// noop
	}

	@Override
	public void applyDefaultRules() {
		// noop
	}
}
