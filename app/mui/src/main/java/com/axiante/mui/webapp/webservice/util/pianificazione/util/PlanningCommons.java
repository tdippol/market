package com.axiante.mui.webapp.webservice.util.pianificazione.util;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.promo.grid.DBPromoCellTypeEnum;
import com.axiante.mui.common.promo.grid.DBPromoColTypeEnum;
import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.common.utility.StringUtils;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.utils.promo.PromoAcl;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class PlanningCommons {

	final private StringUtils utils = new StringUtils();

	public boolean isPlanningEditableCellAndRow(@NonNull PromozioneTestataEntity testata) {
        final StatoPromozioneEntity statoCorrente = PromoAcl.getStatoCorrente(testata);
        return !testata.getMuiCanalePromozione().getStatiBlocco().contains(statoCorrente);
	}

	/**
	 * Questo metodo determina il tipo di cella per ogni row nel rowData
	 *
	 * @param lista
	 * @param field
	 * @param type
	 * @return
	 */
	@Deprecated
	public String defineCellType(String tipoLista, List<String> lista, String field, String type) {
		return defineCellType(tipoLista, lista, field, type, Boolean.FALSE);
	}

	public String defineCellType(String tipoLista, List<String> lista, String field, String type, Boolean renderAsCombo) {
		String cellType;
		if ((tipoLista != null && tipoLista.startsWith("ws:")) || (lista != null && lista.size() > 1)) {
			cellType = Boolean.TRUE.equals(renderAsCombo)
					? DBPromoCellTypeEnum.COMBOBOX.getType()
					: DBPromoCellTypeEnum.PICKLIST.getType();
		} else if (PianificazioneConstants.REFERENCE_DATA_INIZIO_COLUMN.equals(field)
				|| PianificazioneConstants.REFERENCE_DATA_FINE_COLUMN.equals(field)) {
			cellType = DBPromoCellTypeEnum.DATE.getType();
		} else if (utils.isIntegerType(type) || utils.isNumberType(type)) {
			cellType = DBPromoCellTypeEnum.NUMERIC.getType();
		} else if (utils.isTime(type)) {
			cellType = DBPromoCellTypeEnum.TIME.getType();
		} else {
			cellType = DBPromoCellTypeEnum.STRING.getType();
		}
		return cellType;
	}

	/**
	 * Questo metodo determina il tipo di cella per ogni row nel columnDef
	 *
	 * @param lista
	 * @param field
	 * @param type
	 * @return
	 */
	public String defineDBPromoColumnType(List<String> lista, String field, String type) {
		String cellType;
		if (lista != null && lista.size() > 1) {
			cellType = DBPromoColTypeEnum.STRING.getType();
		} else if (PianificazioneConstants.REFERENCE_DATA_INIZIO_COLUMN.equals(field)
				|| PianificazioneConstants.REFERENCE_DATA_FINE_COLUMN.equals(field)) {
			cellType = DBPromoColTypeEnum.DATE.getType();
		} else if (utils.isInteger(type) || utils.isNumber(type)) {
			cellType = DBPromoColTypeEnum.NUMERIC.getType();
		} else if (DBPromoColTypeEnum.TIME.getType().equalsIgnoreCase(type.trim())) {
			return DBPromoColTypeEnum.TIME.getType();
		} else {
			cellType = DBPromoColTypeEnum.STRING.getType();
		}
		return cellType;
	}

	/**
	 * Se l'header è TIPO ELEMENTO, ELEMENTO o MECCANICA il value è popolato per
	 * righe sia MASTER sia DETAIL
	 *
	 * @param header
	 * @return
	 */
	public boolean checkSpecialHeader(String header) {
		boolean specialHeader = false;
		switch (header) {
		case PianificazioneConstants.CAMPO_PIANIFICAZIONE_TIPO_ELEMENTO:
		case PianificazioneConstants.CAMPO_PIANIFICAZIONE_ELEMENTO:
		case PianificazioneConstants.CAMPO_PIANIFICAZIONE_MECCANICA:
			specialHeader = true;
			break;
		}
		return specialHeader;
	}

	public Boolean checkColumnEditable(@NonNull PromozioneTestataEntity testata, String sicurezza) {
	    return checkColumnEditable(testata, sicurezza, null);
    }

    public Boolean checkColumnEditable(@NonNull PromozioneTestataEntity testata, String sicurezza, String field) {
	    if (isPlanningEditableCellAndRow(testata)) {
            if (PianificazioneSecurityEnum.READ.getSecurity().equals(sicurezza)) {
                return PianificazioneSecurityEnum.READ.isEditable();
            } else if (PianificazioneSecurityEnum.WRITE.getSecurity().equals(sicurezza)) {
                return PianificazioneSecurityEnum.WRITE.isEditable();
            } else {
                return false;
            }
        } else {
            if (field != null) {
                return isNoteEditable(field, PromoAcl.getStatoCorrente(testata).getCodiceStato())
                        && PianificazioneSecurityEnum.WRITE.getSecurity().equals(sicurezza)
                        && PianificazioneSecurityEnum.WRITE.isEditable();
            } else {
                return false;
            }
        }
    }

	public Boolean getBooleanHideValue(String hide) {
		// Hide ha valori 0, 1 o null
		return PianificazioneConstants.PLANNING_HIDE_TRUE.equals(hide);
	}

	/**
	 * Questo metodo ritorna l'editabilità di una cella. La colonna BUDGET è
	 * speciale e una cella deve essere editabile se e solo se la riga non è di tipo
	 * MASTER e il tipo elemento è ARTICOLO
	 *
	 * @param field
	 * @param rowType
	 * @param tipoRigaEntity
	 * @param dbPromoAgCellRowMap
	 * @return
	 */
	public boolean isCellEditable(String field, String rowType, String tipoRigaEntity,
			HashMap<String, DBPromoAgCell> dbPromoAgCellRowMap) {
		if (PianificazioneConstants.CAMPO_PIANIFICAZIONE_BUDGET.equals(field)) {
			return rowType.equals(tipoRigaEntity)
					&& dbPromoAgCellRowMap.get(PianificazioneConstants.CAMPO_PIANIFICAZIONE_TIPO_ELEMENTO) != null
					&& PromoPianificazioneEnum.ARTICOLO.getTipoElemento().equals(dbPromoAgCellRowMap
							.get(PianificazioneConstants.CAMPO_PIANIFICAZIONE_TIPO_ELEMENTO).getValue());
		} else {
			return dbPromoAgCellRowMap.containsKey(field) // Configurazione doppia per un
					// campo, una per tipoRiga
					// MASTER e una per tipoRiga
					// DETAIL
					|| checkSpecialHeader(field) // campi speciali
					|| rowType.equals(tipoRigaEntity);
		}
	}

	/**
	 * se la promozione e' in stato 400 o 31 allora posso sempre editare le note
	 *
	 * @param field
	 * @param statoPromozione
	 * @return
	 */
	public boolean isNoteEditable(String field, String statoPromozione) {
		if (PromoStatusEnum.PIANIFICAZIONE_FINALIZZATA.getKey().equals(statoPromozione)
				|| PromoStatusEnum.PROMOZIONE_IN_ESECUZIONE.getKey().equals(statoPromozione)) {
			return PianificazioneConstants.CAMPO_PIANIFICAZIONE_NOTE.equals(field);
		}
		return false;
	}

	/**
	 * Questo metodo determina se una riga è MASTER o DETAIL
	 *
	 * @param codiceTipo
	 * @return
	 */
	public String getRowType(String codiceTipo) {
		String rowType = null;
		PianificazioneRowTypeEnum pianificazioneRowTypeEnum = checkPianificazioneRowTypeEnum(codiceTipo);
		if (pianificazioneRowTypeEnum != null) {
			switch (pianificazioneRowTypeEnum) {
			case MASTER:
				rowType = PianificazioneRowTypeEnum.MASTER.getDescription();
				break;
			case DETAIL:
				rowType = PianificazioneRowTypeEnum.DETAIL.getDescription();
				break;
			case SET:
				rowType = PianificazioneRowTypeEnum.SET.getDescription();
				break;
			case RAGGRUPPAMENTO:
				rowType = PianificazioneRowTypeEnum.RAGGRUPPAMENTO.getDescription();
				break;
			case ELEMENTO:
				rowType = PianificazioneRowTypeEnum.ELEMENTO.getDescription();
				break;
			}
		} else {
			log.debug(String.format("A rowType definition for %s not exist", codiceTipo));
		}
		return rowType;
	}

	private PianificazioneRowTypeEnum checkPianificazioneRowTypeEnum(String codiceTipo) {
		Optional<PianificazioneRowTypeEnum> pianificazioneRowTypeEnum = Arrays
				.stream(PianificazioneRowTypeEnum.values()).filter(Objects::nonNull)
				.filter(column -> column.getTypeCode().equalsIgnoreCase(codiceTipo)).findFirst();
		return pianificazioneRowTypeEnum.orElse(null);
	}

	public String toValue(Object o, String cellType) {
		if (o == null) {
			return null;
		}
		if (cellType != null && DBPromoCellTypeEnum.TIME.getType().toLowerCase().equalsIgnoreCase(cellType.trim())) {
			return DateTimeUtils.toTime((Date) o);
		}
		return o.toString();
	}

	/**
	 * Controlla se la riga di configurazione e' valida in caso di
	 * valore di "VALIDO_SE_RAGGRUPPAMENTO"
	 * @param configurazione
	 * @param pianificazione
	 * @return true se la pianificazione non segue la configurazione, false altrimenti
	 */
	public boolean overrideConfiguration(CfgPianificazioneEntity configurazione, PromozionePianificazioneEntity pianificazione) {
		boolean override = false;
		if ( configurazione.getValidoSeRaggruppamento() != null ) {
			// se la meccanica e' di tipo set
			CfgLivelloPianificazioneEntity  livello = pianificazione.getPromozioneTestataEntity()
					.getMuiCfgSetPianificazione().getMuiCfgConfHeaders().stream()
					.filter(h -> h.getMeccanicaEntity().equals(pianificazione.getMeccanicaEntity()))
					.map(CfgConfHeaderEntity::getLivelloPianificazione)
					.findAny().orElse(null);
			if ( livello != null && "SET".equals(livello.getCodice())) {
				// se num_raggruppamento != valido_se_raggruppamento allora override
				if (
						!(
						pianificazione.getNumRaggruppamento() != null &&
						Integer.valueOf(pianificazione.getNumRaggruppamento()).intValue() == configurazione.getValidoSeRaggruppamento().intValue() )
							) {
					override = true;
				}
			}
		}
		return override;
	}

}
