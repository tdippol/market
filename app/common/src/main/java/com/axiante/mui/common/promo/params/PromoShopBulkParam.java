package com.axiante.mui.common.promo.params;

import com.axiante.mui.common.promo.ShopUpdateTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author marco
 *
 */
@Getter
@Setter
public class PromoShopBulkParam {

	ShopUpdateTypeEnum type;
	/**
	 * true-> imposta SI false-> imposta NO null -> reset Default
	 */
	Boolean flag;
	/**
	 * valore del tipo negozio. Se type == null ignored
	 */
	Long idTipoNegozio;
	/**
	 * Lista dei negozi selezionati. Se null comanda idPromozione,
	 */
	Long[] negozi;
	/**
	 * codice della promozione. Se negozi.length != 0 ignorato, altrimenti se !=
	 * null contiene il codice della promozione i cui negozi devono essere
	 * modificati
	 */
	Long idPromozione;

	String tipoConsegna;

	String zonaSocieta;

	String zonaCodice;

	String codiceCedi;
}