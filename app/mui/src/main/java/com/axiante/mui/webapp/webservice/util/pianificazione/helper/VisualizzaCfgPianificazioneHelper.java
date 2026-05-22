package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import javax.enterprise.context.Dependent;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class VisualizzaCfgPianificazioneHelper {

	private DateTimeUtils cfgPianificazioneHelperService = new DateTimeUtils();

	public Object invokeGetterEntity(String header, CfgPianificazioneEntity pianificazione) {

		Object fieldValue = null;

		/*
		 * I variableValue corrispondono a MUI_CFG_PIANIFICAZIONE.DEF_VALUE e sono tutti
		 * di tipo String esclusi i REFERENCE value che sono Object
		 */

		if (header != null) {
			switch (header) {
			case "DESCRIZIONE":
				fieldValue = pianificazione.getDescrizione();
				break;
			case "HIDE":
				fieldValue = pianificazione.getHide();
				break;
			case "LISTA":
				fieldValue = pianificazione.getLista();
				break;
			case "MANDATORY":
				fieldValue = pianificazione.getMandatory();
				break;
			case "ORDINAMENTO":
				fieldValue = pianificazione.getOrdinamento();
				break;
			case "SICUREZZA":
				fieldValue = pianificazione.getSicurezza();
				break;
			case "TIPO_LISTA":
				fieldValue = pianificazione.getTipoLista();
				break;
			case "UNIQUE_PERIODO_MECCANICA":
				fieldValue = pianificazione.getUniquePeriodoMeccanica();
				break;
			case "UNIQUE_PROMO":
				fieldValue = pianificazione.getUniquePromo();
				break;
			case "WARN":
				fieldValue = pianificazione.getWarn();
				break;
			case "DEF_VALUE":
				fieldValue = pianificazione.getDefValue();
				break;
			case "FLAG_MULTISELECT":
				fieldValue = pianificazione.getMultiselect();
				break;
			case "LENGTH":
				fieldValue = pianificazione.getLength();
				break;
			case "CODICE_UTENTE_AGGIORNAMENTO":
				fieldValue = pianificazione.getCodiceUtenteAggiornamento();
				break;
			case "CODICE_UTENTE_INSERIMENTO":
				fieldValue = pianificazione.getCodiceUtenteInserimento();
				break;
			case "DATA_AGGIORNAMENTO":
				fieldValue = pianificazione.getDataAggiornamento() != null
						? cfgPianificazioneHelperService.toExcelDate(pianificazione.getDataAggiornamento())
						: "";
				break;
			case "DATA_INSERIMENTO":
				fieldValue = pianificazione.getDataInserimento() != null
						? cfgPianificazioneHelperService.toExcelDate(pianificazione.getDataInserimento())
						: "";
				break;

			case "FORMAT_STRING":
				fieldValue = pianificazione.getFormatString() != null ? pianificazione.getFormatString() : "";
				break;
			case "MIN_LENGTH":
				fieldValue = pianificazione.getMinLength() != null ? pianificazione.getMinLength() : "";
				break;
			case "ALLOW_ZERO":
				fieldValue = pianificazione.getAllowZero() != null ? pianificazione.getAllowZero() : "";
				break;
			case "VALIDO_SE_RAGGRUPPAMENTO":
				fieldValue = pianificazione.getValidoSeRaggruppamento() != null
						? pianificazione.getValidoSeRaggruppamento()
						: "";
				break;

			default:
				log.warn(String.format("Column %s is not defined in the entity PromozionePianificazioneEntity", header));
			}
		}
		return fieldValue;
	}
}
