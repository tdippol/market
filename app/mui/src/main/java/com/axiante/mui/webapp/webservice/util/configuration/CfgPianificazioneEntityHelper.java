package com.axiante.mui.webapp.webservice.util.configuration;

import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.common.utility.PicklistUtils;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Dependent
@Slf4j
public class CfgPianificazioneEntityHelper {

	public boolean invokeSetterForField(@NonNull CfgPianificazioneEntity entity, String fieldName, Object value) {
		boolean isSuccess = true;
		if (fieldName != null) {
			try {
				switch (fieldName.toUpperCase()) {
					case "ABILITA_PER_TIPO_ELEMENTO":
						entity.setAbilitaPerTipoElemento(getValueAsString(value));
						break;
					case "DESCRIZIONE":
						entity.setDescrizione(getValueAsString(value));
						break;
					case "HIDE":
						final Integer hide = getValueAsBoolean(value);
						if (hide != null && hide == -1) {
							log.error(String.format("Value '%s' for 'hide' is not valid", value));
							isSuccess = false;
						} else {
							entity.setHide(hide == null ? null : String.valueOf(hide));
						}
						break;
					case "LISTA":
						final String lista = getValueAsString(value);
						if (StringUtils.isBlank(lista)) {
							entity.setLista(null);
						} else {
							final List<String> list = new PicklistUtils().defineListaCellEditor(lista);
							if (list != null && list.size() == 1 && list.get(0) != null && list.get(0).trim().isEmpty()) {
								log.error(String.format("Value '%s' for 'lista' is not valid", value));
								isSuccess = false;
							} else {
								entity.setLista(lista);
							}
						}
						break;
					case "LISTACONDIZIONALE":
						final String listaCondizionale = getValueAsString(value);
						if (StringUtils.isBlank(listaCondizionale)) {
							entity.setListaCondizionale(null);
						} else {
							try{

								final ObjectMapper mapper = JsonUtils.getMapper();
								final Map<String, String> map= mapper.readValue(listaCondizionale, Map.class);
								final  PicklistUtils pickUtils = new PicklistUtils();
								final boolean worngList = map.values().stream()
										.map(pickUtils::defineListaCellEditor)
										.anyMatch(list -> list != null && list.size() == 1 && list.get(0) != null && list.get(0).trim().isEmpty());
								if ( worngList){
									log.error(String.format("Invald list value in '%s' for 'listaCondizionale' is not valid", value));
									isSuccess = false;
								} else {
									entity.setListaCondizionale(listaCondizionale);
								}
							} catch (Exception e){
								log.error(String.format("Invalid format '%s' for 'listaCondizionale' is not valid", value), e);
								isSuccess = false;
							}
						}
						break;
					case "CHIAVE":
						final String chiave = getValueAsString(value);
						if ( validateChiave(entity, chiave) ) {
							if (StringUtils.isBlank(chiave)) {
								entity.setChiave(null);
							} else {
								entity.setChiave(chiave);
							}
						} else {
							isSuccess = false;
						}
						break;
					case "MANDATORY":
						final Integer mandatory = getValueAsBoolean(value);
						if (mandatory != null && mandatory == -1) {
							log.error(String.format("Value '%s' for 'mandatory' is not valid", value));
							isSuccess = false;
						} else {
							entity.setMandatory(mandatory == null ? null : String.valueOf(mandatory));
						}
						break;
					case "ORDINAMENTO":
						entity.setOrdinamento(getValueAsInteger(value));
						break;
					case "SICUREZZA":
						final String sicurezza = getValueAsString(value);
						if ("R".equalsIgnoreCase(sicurezza) || "W".equalsIgnoreCase(sicurezza)) {
							entity.setSicurezza(sicurezza.toUpperCase());
						} else {
							log.error(String.format("Value '%s' for 'sicurezza' is not valid", value));
							isSuccess = false;
						}
						break;
					case "TIPO_LISTA":
						final String tipoLista = getValueAsString(value);
						if (!StringUtils.isBlank(tipoLista) && !tipoLista.trim().startsWith("ws:///")) {
							log.error(String.format("Value '%s' for 'tipo_lista' is not valid", value));
							isSuccess = false;
						} else {
							entity.setTipoLista(tipoLista);
						}
						break;
					case "UNIQUE_PERIODO_MECCANICA":
						entity.setUniquePeriodoMeccanica(getValueAsString(value));
						break;
					case "UNIQUE_PROMO":
						entity.setUniquePromo(getValueAsString(value));
						break;
					case "WARN":
						final Integer warn = getValueAsBoolean(value);
						if (warn != null && warn == -1) {
							log.error(String.format("Value '%s' for 'warn' is not valid", value));
							isSuccess = false;
						} else {
							entity.setWarn(warn == null ? null : String.valueOf(warn));
						}
						break;
					case "DEF_VALUE":
						entity.setDefValue(getValueAsString(value));
						break;
					case "FLAG_MULTISELECT":
						final Integer multiselect = getValueAsBoolean(value);
						if (multiselect != null && multiselect == -1) {
							log.error(String.format("Value '%s' for 'multiselect' is not valid", value));
							isSuccess = false;
						} else {
							entity.setMultiselect(multiselect == null ? null : Short.valueOf(String.valueOf(multiselect)));
						}
						break;
					case "LENGTH":
						entity.setLength(getValueAsInteger(value));
						break;
					case "FORMAT_STRING":
						entity.setFormatString(getValueAsString(value));
						break;
					case "MIN_LENGTH":
						entity.setMinLength(getValueAsInteger(value));
						break;
					case "ALLOW_ZERO":
						final Integer allowZero = getValueAsBoolean(value);
						if (allowZero != null && allowZero == -1) {
							log.error(String.format("Value '%s' for 'allowZero' is not valid", value));
							isSuccess = false;
						} else {
							entity.setAllowZero(allowZero);
						}
						break;
					case "VALIDO_SE_RAGGRUPPAMENTO":
						entity.setValidoSeRaggruppamento(getValueAsInteger(value));
						break;
					case "LOOKUP_ATTRIBUTE":
						entity.setLookupAttribute(getValueAsString(value));
						break;
					case "VALUE":
						entity.setValue(getValueAsString(value));
						break;
					case "VALUE_TYPE":
						entity.setValueType(getValueAsString(value));
						break;
					case "FORMULA":
						entity.setFormula(getValueAsString(value));
						break;
					case "FORMULA_ENABLED":
						entity.setFormulaEnabled(getAsBoolean(value));
						break;
					case "RENDERASCOMBO":
						entity.setRenderAsCombo(getAsBoolean(value));
						break;
					case "INVISIBLE":
						entity.setInvisible(getAsBoolean(value));
						break;
					case "PROPAGA":
						entity.setPropaga(getAsBoolean(value));
						break;
					default:
						log.warn(String.format("Field %s is not defined in the entity CfgPianificazioneEntity", fieldName));
						isSuccess = false;
						break;
				}
			} catch (Exception ex) {
				log.error(String.format("The format of value %s for field %s is not correct", value, fieldName), ex);
				isSuccess = false;
			}
		}
		return isSuccess;
	}

	/**
	 * Data una configurazione per un campo chiave, verifica che
	 * 1. Il campo dichiarato sia un campo effettivo di MUI_CFG_PIANIFICAZIONE_CAMPI
	 * 2. Il campo sia dichiarato nella configurazione corrente
	 * @param entity : la riga di configurazione che stiamo validando
	 * @param value : il valore del campo chiave
	 * @return true se il campo chiave e' null oppure se esiste un campo che soddisfa <pre>1</pre> o <pre>2</pre>
	 */
	private boolean validateChiave(@NonNull CfgPianificazioneEntity entity, String value){
		if (StringUtils.isBlank(value))
			return true; // valori null ammessi: cazzi vobis
		return entity.getMuiCfgConfHeader()
				.getMuiCfgPianificaziones()// tutte le configurazioni
				.stream()
				.filter(c -> !c.getId().equals(entity.getId())) //tranne se stessa altrimenti e' circolare
				.map(CfgPianificazioneEntity::getMuiCfgPianificazioneCampi)// tra tutti i campi mappati
				.map(CfgPianificazioneCampiEntity::getCampo)
				.anyMatch(c -> c.equals(value));
	}

	public Object invokeGetterForField(@NonNull CfgPianificazioneEntity entity, String fieldName) {
		if (fieldName != null) {
			switch (fieldName.toUpperCase()) {
				case "ABILITA_PER_TIPO_ELEMENTO":
					return entity.getAbilitaPerTipoElemento();
				case "DESCRIZIONE":
					return entity.getDescrizione();
				case "HIDE":
					return entity.getHide();
				case "LISTA":
					return entity.getLista();
				case "MANDATORY":
					return entity.getMandatory();
				case "ORDINAMENTO":
					return entity.getOrdinamento();
				case "SICUREZZA":
					return entity.getSicurezza();
				case "TIPO_LISTA":
					return entity.getTipoLista();
				case "UNIQUE_PERIODO_MECCANICA":
					return entity.getUniquePeriodoMeccanica();
				case "UNIQUE_PROMO":
					return entity.getUniquePromo();
				case "WARN":
					return entity.getWarn();
				case "DEF_VALUE":
					return entity.getDefValue();
				case "FLAG_MULTISELECT":
					return entity.getMultiselect();
				case "LENGTH":
					return entity.getLength();
				case "FORMAT_STRING":
					return entity.getFormatString();
				case "MIN_LENGTH":
					return entity.getMinLength();
				case "ALLOW_ZERO":
					return entity.getAllowZero();
				case "VALIDO_SE_RAGGRUPPAMENTO":
					return entity.getValidoSeRaggruppamento();
				default:
					log.warn(String.format("Field %s is not defined in the entity CfgPianificazioneEntity", fieldName));
					return null;
			}
		}
		return null;
	}

	/**
	 * Check if given entity is a "Lookup Entity", defined as follow:
	 * - fields {@see CfgPianificazioneCampiEntity#entityLookup} and {@see CfgPianificazioneCampiEntity#entityAttribute}
	 * must be both not null and not empty
	 * @param entity pianificazione
	 * @return true if entity is a "Lookup Entity", false otherwise
	 */
	public boolean isEntityLookup(CfgPianificazioneEntity entity) {
		return entity.getMuiCfgPianificazioneCampi() != null
				&& !StringUtils.isBlank(entity.getMuiCfgPianificazioneCampi().getEntityLookup())
				&& !StringUtils.isBlank(entity.getMuiCfgPianificazioneCampi().getEntityAttribute());
	}

	/**
	 * Check if given entity is a "Formula Enabled", defined as follow:
	 * - field {@see CfgPianificazioneEntity#formulaEnabled} must be not null and true
	 * @param entity pianificazione
	 * @return true if entity is a "Formula Enabled", false otherwise
	 */
	public boolean isFormulaEnabled(CfgPianificazioneEntity entity) {
		return entity.getFormulaEnabled() != null && entity.getFormulaEnabled();
	}

	private Integer getValueAsInteger(Object value) {
		final String valueAsString = getValueAsString(value);
		return StringUtils.isBlank(valueAsString) || "null".equalsIgnoreCase(valueAsString.trim())
				? null
				: Integer.parseInt(valueAsString);
	}

	private Integer getValueAsBoolean(Object value) {
		String valueAsString = getValueAsString(value);
		if (StringUtils.isBlank(valueAsString)) {
			return null;
		}
		valueAsString = valueAsString.trim();
		return "true".equalsIgnoreCase(valueAsString)
				? 1
				: "false".equalsIgnoreCase(valueAsString)
					? 0
					: -1;
	}

	private String getValueAsString(Object value) {
		return value == null ? null : (String) value;
	}

	private Boolean getAsBoolean(Object value) {
		String valueAsString = getValueAsString(value);
		if (StringUtils.isBlank(valueAsString)) {
			return false;
		}
		valueAsString = valueAsString.trim();
		return "true".equalsIgnoreCase(valueAsString);
	}
}
