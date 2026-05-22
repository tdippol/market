package com.axiante.mui.common.utility;

import com.axiante.mui.common.PianificazioneConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PicklistUtils {
	private StringUtils utils = new StringUtils();

	public List<String> defineListaCellEditor(String lista) {
		List<String> defineCellEditorList = new ArrayList<>();
		defineCellEditorList.add(PianificazioneConstants.CONFIGURATION_FIELD_LIST_BLANK_VALUE);
		if (lista != null && !lista.isEmpty()) {
			if (lista.startsWith(PianificazioneConstants.CONFIGURATION_FIELD_LIST_LEFT_SQUARE_BRACKET)
					&& lista.endsWith(PianificazioneConstants.CONFIGURATION_FIELD_LIST_RIGHT_SQUARE_BRACKET)) {
				if (lista.contains(PianificazioneConstants.CONFIGURATION_FIELD_LIST_SEMICOLON_SEPARATOR)) {
					defineCellEditorList.clear();
					defineCellEditorList = Arrays.asList(lista.substring(1, lista.length() - 1)
							.split(PianificazioneConstants.CONFIGURATION_FIELD_LIST_SEMICOLON_SEPARATOR));
				} else if (lista.contains(PianificazioneConstants.CONFIGURATION_FIELD_LIST_DOUBLE_DOT_SEPARATOR)) {
					String[] listLimitValuesArray = lista.substring(1, lista.length() - 1)
							.split(PianificazioneConstants.CONFIGURATION_FIELD_LIST_DOUBLE_DOT_SEPARATOR_SPLIT_VERSION);
					if (listLimitValuesArray.length == 2) {
						if (utils.isInteger(listLimitValuesArray[0]) && utils.isInteger(listLimitValuesArray[1])) {
							defineCellEditorList.clear();
							defineCellEditorList =IntStream
									.rangeClosed(Integer.parseInt(listLimitValuesArray[0]),
											Integer.parseInt(listLimitValuesArray[1]))
									.boxed().map(String::valueOf).collect(Collectors.toList());

						} else {
							log.warn(String.format("Both limit values of list %s are not of type integer", lista));
						}
					} else {
						log.warn(String.format("Values in list %s are more than 2 for format [valueX..valueY]", lista));
					}
				} else {
					defineCellEditorList = Arrays.asList(lista.substring(1, lista.length() - 1));
				}
			} else {
				log.warn(String.format("List '%s' has invalid format; should be [valueA;valueB;valueC] or [valueX..valueY]", lista));
			}
		}
		if (defineCellEditorList.size() > 0) {
			return defineCellEditorList;
		}
		return null;
	}

	public List<String> convertToList(String lista) {
		List<String> ret = null;
		if (lista != null) {
			ret = new ArrayList<>();
			final String string = lista.replaceAll("\\[", "").replaceAll("\\]", "");
			if (lista.contains(";")) {
				ret.addAll(Arrays.asList(string.split(";")));
			} else {
				ret.add(string);
			}
		}
		return ret;
	}

	public List<Integer> defineListaCellEditorAsInts(String lista) {
		List<String> temp = defineListaCellEditor(lista);
		if (temp != null) {
			return temp.stream().map(s -> {
				try {
					return Integer.parseInt(s);
				} catch (Exception e) {
					if (log.isDebugEnabled()) {
						log.warn("invalid integer value " + s + " defined in range", e);
					}
					return null;
				}
			}).filter(Objects::nonNull).collect(Collectors.toList());
		}
		return null;
	}
}
