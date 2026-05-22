package com.axiante.mui.dbpromo.business.utils.promo;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Set;
import lombok.NonNull;

public class PromoAcl {

	public static boolean isDeletable(@NonNull final PromozioneTestataEntity testata) {
		synchronized (testata) {
			final StatoPromozioneEntity stato = getStatoCorrente(testata);
			return (stato != null) && isDeletable(stato);
		}
	}

	public static boolean isClosed(@NonNull final PromozioneTestataEntity testata) {
		synchronized (testata) {
			final StatoPromozioneEntity stato = getStatoCorrente(testata);
			return (stato != null) && isClosed(stato);
		}
	}

	public static boolean isEditable(@NonNull final PromozioneTestataEntity testata) {
		synchronized (testata) {
			final StatoPromozioneEntity stato = getStatoCorrente(testata);
			return (stato != null) && isEditable(stato);
		}
	}

	public static boolean isSbloccoEsecuzione(@NonNull final PromozioneTestataEntity testata) {
		synchronized (testata) {
			final StatoPromozioneEntity stato = getStatoCorrente(testata);
			return (stato != null) && isSbloccoEsecuzione(stato);
		}
	}

	public static boolean isCreated(@NonNull final PromozioneTestataEntity testata) {
		synchronized (testata) {
			final StatoPromozioneEntity stato = getStatoCorrente(testata);
			return (stato != null) && isCreated(stato);
		}
	}

	public static boolean isPromoInEsecuzione(@NonNull final StatoPromozioneEntity statoPromozioneEntity) {
		synchronized (statoPromozioneEntity) {
			return PromoStatusEnum.PROMOZIONE_IN_ESECUZIONE.getKey().equals(statoPromozioneEntity.getCodiceStato())
					|| PromoStatusEnum.SBLOCCO_PROMO_IN_ESECUZIONE_PER_CORREZIONI.getKey().equals(statoPromozioneEntity.getCodiceStato());
		}
	}

	public static boolean isClosed(@NonNull final StatoPromozioneEntity stato) {
		return PromoStatusEnum.PROMOZIONIE_CONCLUSA.getKey().equals(stato.getCodiceStato());
	}

	public static boolean isEditable(@NonNull final StatoPromozioneEntity stato) {
		synchronized (stato) {
			return PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.PIANIFICAZIONE_FINALIZZATA.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.SBLOCCO_PIANIFICAZIONE_PER_CORREZIONI.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.IN_PIANIFICAZIONE_CON_ERRORI.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.IN_ESECUZIONE_CON_ERRORI.getKey().equals(stato.getCodiceStato())	;
		}
	}

	// #3402: cancellabile per stati 10, 30
	public static boolean isDeletable(@NonNull final StatoPromozioneEntity stato) {
		synchronized (stato) {
			return PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey().equals(stato.getCodiceStato());
		}
	}

	// #3402: descrizione editabile per stati 10, 30, 311
	public static boolean isEditableDescription(@NonNull final StatoPromozioneEntity stato) {
		synchronized (stato) {
			return PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.SBLOCCO_PIANIFICAZIONE_PER_CORREZIONI.getKey().equals(stato.getCodiceStato());
		}
	}

	// #3402: data inizio editabile per stati 10, 30, 311 (come per descrizione editabile)
	public static boolean isEditableStartDate(@NonNull final StatoPromozioneEntity stato) {
		return isEditableDescription(stato);
	}

	// #3402: data fine editabile per stati 10, 30, 311, 410
	public static boolean isEditableEndDate(@NonNull final StatoPromozioneEntity stato) {
		synchronized (stato) {
			return PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.SBLOCCO_PIANIFICAZIONE_PER_CORREZIONI.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.SBLOCCO_PROMO_IN_ESECUZIONE_PER_CORREZIONI.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.IN_ESECUZIONE_CON_ERRORI.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.IN_PIANIFICAZIONE_CON_ERRORI.getKey().equals(stato.getCodiceStato())
					;
		}
	}

	// #3402: note editabile per tutti tranne 500
	public static boolean isEditableNotes(@NonNull final StatoPromozioneEntity stato) {
		synchronized (stato) {
			return !isClosed(stato);
		}
	}

	public static boolean isEditableValorePunto(@NonNull final PromozioneTestataEntity testata) {
		synchronized (testata) {
			return !isClosed(testata);
		}
	}

	public static boolean isEditableValorePunto(@NonNull final StatoPromozioneEntity stato) {
		synchronized (stato) {
			return !isClosed(stato);
		}
	}

	public static boolean isCancelled(@NonNull final StatoPromozioneEntity stato) {
		synchronized (stato) {
			return PromoStatusEnum.CANCELLATA_00.getKey().equals(stato.getCodiceStato());
		}
	}

	public static boolean isShopEditable(@NonNull final PromozioneTestataEntity promozioneTestataEntity) {
		synchronized (promozioneTestataEntity) {
			String codiceStatoPromozione = getCodiceStatoPromozione(promozioneTestataEntity);
			return  PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey().equals(codiceStatoPromozione)
					|| PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey().equals(codiceStatoPromozione)
					|| PromoStatusEnum.SBLOCCO_PIANIFICAZIONE_PER_CORREZIONI.getKey().equals(codiceStatoPromozione)
					|| PromoStatusEnum.SBLOCCO_PROMO_IN_ESECUZIONE_PER_CORREZIONI.getKey().equals(codiceStatoPromozione)
					|| PromoStatusEnum.IN_PIANIFICAZIONE_CON_ERRORI.getKey().equals(codiceStatoPromozione)
					|| PromoStatusEnum.IN_ESECUZIONE_CON_ERRORI.getKey().equals(codiceStatoPromozione);

		}
	}
	public static boolean isCfgPianificazioneEditable(@NonNull final CfgSetPianificazioneEntity cfgSetPianificazione) {
		synchronized (cfgSetPianificazione) {
			if ((cfgSetPianificazione.getLocked() != null) && (1 == cfgSetPianificazione.getLocked())) {
				return true;
			}
			final Set<PromozioneTestataEntity> testataEntities = cfgSetPianificazione.getPromozioneTestataEntities();
			return (testataEntities == null) || testataEntities.stream()
					.noneMatch(t -> (t.getPromozionePianificazioneEntities() != null)
							&& (t.getPromozionePianificazioneEntities().size() > 0));
		}
	}

	public static boolean isSbloccoEsecuzione(@NonNull final StatoPromozioneEntity stato) {
		synchronized (stato) {
			return PromoStatusEnum.SBLOCCO_PROMO_IN_ESECUZIONE_PER_CORREZIONI.getKey().equals(stato.getCodiceStato());
		}
	}

	public static boolean isCreated(@NonNull final StatoPromozioneEntity stato) {
		synchronized (stato) {
			return PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey().equals(stato.getCodiceStato());
		}
	}

	public static StatoPromozioneEntity getStatoCorrente(@NonNull final PromozioneTestataEntity testata) {
		synchronized (testata) {
			return testata.getPromozioneStatoEntities().stream()
					.filter(st -> st.getDataFineStato() == null)
					.findFirst()
					.map(PromozioneStatoEntity::getStatoPromozioneEntity)
					.orElse(null);
		}
	}

	public static String getCodiceStatoPromozione(@NonNull final PromozioneTestataEntity promozioneTestataEntity) {
		synchronized (promozioneTestataEntity) {
			StatoPromozioneEntity statoPromozioneEntity = getStatoCorrente(promozioneTestataEntity);
			if (statoPromozioneEntity == null) {
				return null;
			}
			return statoPromozioneEntity.getCodiceStato();
		}
	}

	/**
	 * Questo metodo determina se un compratorore è selezionabile nel tab compratori
	 *
	 * @param stato
	 * @return
	 */
	public static boolean isBuyerSelectEnabled(@NonNull final StatoPromozioneEntity stato) {
		synchronized (stato) {
			return PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.PIANIFICAZIONE_DISPONIBILE.getKey().equals(stato.getCodiceStato())
					|| PromoStatusEnum.SBLOCCO_PIANIFICAZIONE_PER_CORREZIONI.getKey().equals(stato.getCodiceStato());
		}
	}

	/**
	 * controlla che la meccanica non sia presente in una pianificazione della
	 * testata promozionale
	 */
	public static boolean isMeccanicaCancellabile(@NonNull final MeccanicheEntity meccanica,
			@NonNull final PromozioneTestataEntity testata) {
		synchronized (testata) {
			return testata.getPromozionePianificazioneEntities().stream()
					.map(PromozionePianificazioneEntity::getMeccanicaEntity).map(MeccanicheEntity::getId)
					.noneMatch(id -> meccanica.getId().equals(id));
		}
	}

	/**
	 * nessun controllo sulla cancellazione del reparto dalla testata
	 * @param reparto
	 * @param testata
	 * @return
	 */
	public static boolean isRepartoCancellabile(@NonNull final RepartoEntity reparto,
			@NonNull final PromozioneTestataEntity testata) {
		return true;
	}


	public static boolean isActive(@NonNull final StatoPromozioneEntity stato) {
		return !(isCancelled(stato) || isClosed(stato));
	}

	public static boolean isActive(@NonNull final PromozioneTestataEntity testata) {
		return isActive(getStatoCorrente(testata));
	}

	/**
	 * Editabile SOLO SE fl_differenziazione_negozi sul canale associato alla testata = TRUE e
	 * fl_differenziazione_meccanica sulla testata = TRUE
	 *
	 * @param testata
	 * @return
	 */
	public static boolean isShopCodiceMeccanicaEditable(@NonNull PromozioneTestataEntity testata) {
		synchronized (testata) {
			return testata.getMuiCanalePromozione() != null
					&& Boolean.TRUE.equals(testata.getMuiCanalePromozione().getFlDifferenziazioneNegozi())
					&& Boolean.TRUE.equals(testata.getFlDifferenziazioneMeccanica());
		}
	}
}
