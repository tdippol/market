package com.axiante.mui.webapp.views.content.dbpromo.data;

import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import java.util.Date;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class CastellettoMessaggi {
	@Getter
	String descrizioneSconto;
	@Getter
	Integer utilizzi;
	@Getter
	@Setter
	Date scadenza;

	public CastellettoMessaggi(@NonNull PromozionePianificazioneEntity pianificazione) {
		setDescrizioneSconto(pianificazione.getDescrizioneSconto());
		setUtilizzi(pianificazione.getValiditaPeriodo());
		setScadenza(pianificazione.getDataFine());
	}

	private void setDescrizioneSconto(String descrizioneSconto) {
		this.descrizioneSconto = toSafeString(descrizioneSconto, null);
	}

	private void setUtilizzi(Integer utilizzi) {
		this.utilizzi = utilizzi;
	}

	private String toSafeString(String string, Integer endValue) {
		if (string == null) {
			return "";
		}
		if (string.length() > 40) {
			if (endValue == null) {
				return string.substring(0, 40);
			} else {
				return string.substring(0, 40 - (endValue.toString().length()));
			}
		}
		return string;

	}
}
