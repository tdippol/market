package com.axiante.mui.webapp.utils.message;

import com.axiante.mui.webapp.views.content.aggrid.actions.SelectedAction;
import javax.faces.application.FacesMessage;
import lombok.Data;
import lombok.NonNull;

public class MessageUtils {
	
	private static final FacesMessage.Severity DEFAULT_SEVERITY = FacesMessage.SEVERITY_WARN;
	private static final String DEFAULT_MESSAGE = "Errore durante l'esecuzione del processo.";
	private static final String DEFAULT_TITLE = "Errore!";
	

	public static FacesMessage toFacesMessage(@NonNull final SelectedAction action) {
		return new FacesMessage(
				action.getCustomMessageLevel() == null ? DEFAULT_SEVERITY: toFacesSeverity(action.getCustomMessageLevel()),
				action.getCustomMessageTitle() == null ? DEFAULT_TITLE : action.getCustomMessageTitle(),
				action.getCustomMessage() == null ? DEFAULT_MESSAGE : action.getCustomMessage() 
        );
	}

	
	private static FacesMessage.Severity toFacesSeverity(@NonNull final String string){
		switch (string.toUpperCase()) {
		case "ERROR":
			return FacesMessage.SEVERITY_ERROR;
		case "FATAL":
			return FacesMessage.SEVERITY_FATAL;
		case "INFO":
			return FacesMessage.SEVERITY_INFO;
		default:
			return FacesMessage.SEVERITY_WARN;
		}

	}
	
	@Data
	class SeverityMapper {
		
		String configurationString;
		FacesMessage.Severity facesSeverity;
	}
}
