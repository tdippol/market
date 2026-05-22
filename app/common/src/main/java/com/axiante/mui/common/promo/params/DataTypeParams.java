package com.axiante.mui.common.promo.params;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * Classe che mappa i parametri per le celle 
 * @author marco
 * Segue il paradigma dei builders quindi : <br>
 * <pre>
 * DataTypeParams.builder().precision(0).multiselect(false).build();
 * </pre>
 * 
 * crea una istanza di DataTypeParams con il valore di precision 0 e di multiselect false
 */
public class DataTypeParams {
	/**
	 * Precisione, valido solo per i numerici, ignorato altrimenti. Se <pre>precision = 0 </pre> allora
	 * ci si aspetta che il front-end tratti i valori come interi    
	 */
	private Integer precision;
	/**
	 * Formato della data. Valido solo i dati di tipo date/time
	 */
	private String dateFormat;
	/**
	 * Indica se la dropdown e' multiselect
	 */
	private Boolean multiselect;
	/**
	 * Mostra il campo secondi
	 */
	private Boolean showSeconds;// boolean - optional - def false
	/**
	 * Ora minima, default 00
	 */
	private Integer minHours;// int - optional - def 00
	/**
	 * Ora massima, default 24
	 */
	private Integer maxHours;// int - optional - def 24
	/**
	 * Step incremento minuti, default 1
	 */
	private Integer minuteStep;// int - optional - def 1
	/**
	 * Step incremento secondi, default 1
	 */
	private Integer secondStep;// int - optional - def 1
	/**
	 * Indica se e' possibile inserire il valore 0 nel campo numerico
	 */
	private Boolean allowZero;
	/**
	 * Lunghezza massima. Nel caso di un campo numerico indica il valore massimo
	 */
	private Integer length;
	/**
	 * Lunghezza minima
	 */
	private Integer minLength;
	/**
	 * Flag indica se e' di tipo "Formula"
	 */
	private Boolean formula;
	/**
	 * Flag indica se si possono inserire negativi. Default "false"
	 */
	@Builder.Default
	private Boolean allowNegative = Boolean.FALSE;

	public DataTypeParams() {
	}
}
