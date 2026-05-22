package com.axiante.mui.dbpromo.persistence.entities;

import java.util.Date;

public interface AuditLogInterface extends DbPromoEntityInterface {
	String getCodiceUtenteAggiornamento();

	void setCodiceUtenteAggiornamento(String codiceUtente);

	String getCodiceUtenteInserimento();

	void setCodiceUtenteInserimento(String codiceUtente);

	Date getDataAggiornamento();

	void setDataAggiornamento(Date data);

	Date getDataInserimento();

	void setDataInserimento(Date data);
}
