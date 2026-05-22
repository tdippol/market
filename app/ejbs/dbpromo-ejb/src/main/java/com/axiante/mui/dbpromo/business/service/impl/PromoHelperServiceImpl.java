package com.axiante.mui.dbpromo.business.service.impl;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.service.PromoHelperService;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Date;
import javax.enterprise.context.Dependent;

@Dependent
public class PromoHelperServiceImpl implements PromoHelperService {

	@Override
	public PromozioneTestataEntity prepareToDelete(PromozioneTestataEntity promo) {
		if (promo == null || promo.getPromozioneStatoEntities() == null || promo.getPromozioneStatoEntities().isEmpty())
			return null;

		PromozioneStatoEntity lastStatus = promo.getPromozioneStatoEntities().stream()
				.filter(st -> st.getDataFineStato() == null).findFirst().orElse(null);
		if (lastStatus == null)
			return null;

		final String codiceStato = lastStatus.getStatoPromozioneEntity().getCodiceStato();
		if (!codiceStato.equals(PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey())
				&& !codiceStato.equals(PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey())
				&& !codiceStato.equals(PromoStatusEnum.PIANIFICAZIONE_FINALIZZATA.getKey())
				&& !codiceStato.equals(PromoStatusEnum.SBLOCCO_PIANIFICAZIONE_PER_CORREZIONI.getKey()))
			return null;

		Date now = new Date();
		lastStatus.setDataFineStato(now);
		promo.getPromozioneStatoEntities().add(lastStatus);
		//
		promo.getPromozioneStatoEntities().add(createCancelledStatus(now));
		return promo;
	}

	private PromozioneStatoEntity createCancelledStatus(Date now) {
		PromozioneStatoEntity cancelled = new PromozioneStatoEntity();
		cancelled.setDataInizioStato(now);

		StatoPromozioneEntity spe = new StatoPromozioneEntity();
		spe.setCodiceStato(PromoStatusEnum.CANCELLATA_00.getKey());
		spe.setDescrizione(PromoStatusEnum.CANCELLATA_00.getDescription());
		cancelled.setStatoPromozioneEntity(spe);
		return cancelled;
	}

}
