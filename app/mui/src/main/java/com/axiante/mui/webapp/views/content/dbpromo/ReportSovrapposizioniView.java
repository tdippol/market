package com.axiante.mui.webapp.views.content.dbpromo;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.dbpromo.business.utils.Constants;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.views.AbstractDBPromoNavigation;
import com.axiante.mui.webapp.views.MuiViewModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.QueryTimeoutException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.menu.MenuItem;

@MuiViewModel("reportSovrapposizioni")
@Dependent
@Slf4j
public class ReportSovrapposizioniView extends AbstractDBPromoNavigation {
	private static final long serialVersionUID = 8256200460568245853L;

	@Inject
	private Instance<PromoService> promoServiceInstance;

	@Inject
	private Instance<ApplicationProperties> applicationPropertiesInstance;

	@Getter
	private List<PromozioneTestataEntity> promozioni;

	@Getter
	private Long idPromozioneCorrente;

	private UserFilterUtils userFilterUtils = new UserFilterUtils();
	private PromozioneTestataEntity promozioneSelected;

	@Override
	public void setNode(MenuItem node) {
		super.setNode(node);
		applyDefaultRules();
		init();
	}

	public void init() {
		final UsersEntity user = getCurrentUser();
		readPromotions(user.getDbFilters());
		setIdPromozioneCorrente(promozioni != null && !promozioni.isEmpty() ? promozioni.get(0).getId() : null);
	}

	public void confirmCheckOverlap() {
		if (promozioneSelected != null) {
			if (!executeStoredProcedure(promozioneSelected.getId(), getCurrentUser().getName())) {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
						"Sovrapposizioni ricalcolate con errori"));
			} else {
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sovrapposizioni ricalcolate", null));
			}
		} else {
			addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore",
					"Nessuna promozione selezionata; impossibile calcolare sovrapposizioni"));
		}
	}

	public void setIdPromozioneCorrente(Long idPromozioneCorrente) {
		this.idPromozioneCorrente = idPromozioneCorrente;
		if (idPromozioneCorrente != null) {
			final PromozioneTestataEntity testata = promoServiceInstance.get().findById(idPromozioneCorrente);
			if (testata != null) {
				promozioneSelected = testata;
			} else {
				log.error(String.format("Cannot find promozione with id %d", idPromozioneCorrente));
				promozioneSelected = null;
			}
		} else {
			promozioneSelected = null;
		}
	}

	public void setPromozioneSelected(PromozioneTestataEntity promozioneSelected) {
		this.promozioneSelected = promozioneSelected;
	}

	private void readPromotions(String dbFilters) {
		try {
			promozioni = promoServiceInstance.get()
					.findAllNotCancelled(userFilterUtils.createUserFiltersMapToQueryString(dbFilters),
							getUserDto().getCanali())
					.stream().sorted(Comparator.comparing(PromozioneTestataEntity::getDataInizio))
					.collect(Collectors.toList());
		} catch (final Exception ex) {
			log.error("Error reading promozioni", ex);
			promozioni = new ArrayList<>();
		}
	}

	private boolean executeStoredProcedure(Long idPromozione, String username) {
		boolean result = true;
		try {
			final Integer timeout = applicationPropertiesInstance.get().getProperty(
					ApplicationProperties.STORED_PROCEDURE_TIMEOUT,
					ApplicationProperties.DEFAULT_STORED_PROCEDURE_TIMEOUT);
			result = promoServiceInstance.get().runFunctionCalcolaSovrapposizioni(idPromozione, username, timeout);
		} catch (QueryTimeoutException ex) {
			log.error("Timeout exceeded", ex);
			result = false;
		} catch (Exception ex) {
			log.error(String.format("Stored procedure %s terminata con errore", Constants.SP_CALCOLA_SOVRAPPOSIZIONI),
					ex);
			result = false;
		}
		return result;
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
