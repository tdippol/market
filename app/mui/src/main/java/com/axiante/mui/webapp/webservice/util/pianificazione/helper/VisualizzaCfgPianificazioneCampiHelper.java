package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import javax.enterprise.context.Dependent;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Slf4j
public class VisualizzaCfgPianificazioneCampiHelper {

	private DateTimeUtils dateTimeUtils = new DateTimeUtils();

	public Object invokeGetterEntity(String header, CfgPianificazioneCampiEntity pianificazioneCampo) {

		Object fieldValue = null;

		/*
		 * I variableValue corrispondono a MUI_CFG_PIANIFICAZIONE.DEF_VALUE e sono tutti
		 * di tipo String esclusi i REFERENCE value che sono Object
		 */

		if (header != null) {
			switch (header) {
			case "DESCRIZIONE":
				fieldValue = pianificazioneCampo.getDescrizione();
				break;
			case "CODICE_CAMPO":
				fieldValue = pianificazioneCampo.getCodiceCampo();
				break;
			case "DESCRIZIONE_ESTESA":
				fieldValue = pianificazioneCampo.getDescrizioneEstesa();
				break;
			case "RAGGRUPPAMENTO":
				fieldValue = pianificazioneCampo.getRaggruppamento();
				break;
			case "TIPO":
				fieldValue = pianificazioneCampo.getTipo();
				break;
			case "CAMPO":
				fieldValue = pianificazioneCampo.getCampo();
				break;
			case "ENTITY_LOOKUP":
				fieldValue = pianificazioneCampo.getEntityLookup();
				break;
			case "ENTITY_ATTRIBUTE":
				fieldValue = pianificazioneCampo.getEntityAttribute();
				break;
			case "COLUMN_WIDTH":
				fieldValue = pianificazioneCampo.getColumnWidth();
				break;
			case "CODICE_UTENTE_AGGIORNAMENTO":
				fieldValue = pianificazioneCampo.getCodiceUtenteAggiornamento();
				break;
			case "CODICE_UTENTE_INSERIMENTO":
				fieldValue = pianificazioneCampo.getCodiceUtenteInserimento();
				break;
			case "DATA_AGGIORNAMENTO":
				fieldValue = pianificazioneCampo.getDataAggiornamento() != null
						? dateTimeUtils.toExcelDate(pianificazioneCampo.getDataAggiornamento())
						: "";
				break;
			case "DATA_INSERIMENTO":
				fieldValue = pianificazioneCampo.getDataInserimento() != null
						? dateTimeUtils.toExcelDate(pianificazioneCampo.getDataInserimento())
						: "";
				break;
			default:
				log.warn(String.format("Column %s is not defined in the entity PromozionePianificazioneEntity", header));
			}
		}
		return fieldValue;
	}
}
