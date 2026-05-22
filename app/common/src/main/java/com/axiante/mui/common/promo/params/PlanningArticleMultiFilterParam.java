package com.axiante.mui.common.promo.params;

import lombok.Data;

@Data
public class PlanningArticleMultiFilterParam {
	/**
	 * id selezionato nei filtri relativo a CompratoreEntity
	 */
	Long idCompratoreSelected;
	/**
	 * id selezionato nei filtri relativo a FornitoreEntity
	 */
	Long idFornitoreSelected;
	/**
	 * id selezionato nei filtri relativo a RepartoEntity
	 */
	Long idRepartoSelected;
	/**
	 * id selezionato nei filtri relativo a CategoriaEntity
	 */
	Long idCategoriaSelected;
	/**
	 * id selezionato nei filtri relativo a GrmEntity
	 */
	Long idGrmSelected;
	/**
	 * codice selezionato nei filtri relativo a MarchioPrivatoEntity
	 */
	String codiceMarchioPrivSelected;
	/**
	 * id di una riga master relativo a PromozionePianificazioneEntity
	 */
	Long idMasterAddPianificazione;

	String gruppoSelected;
}
