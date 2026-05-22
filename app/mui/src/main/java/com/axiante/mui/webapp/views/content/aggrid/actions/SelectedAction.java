package com.axiante.mui.webapp.views.content.aggrid.actions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SelectedAction implements Serializable {
	private static final long serialVersionUID = -2584867820357137404L;
	private String source;
	private String componentId;
	private String process;
	private String label;
	private List<String> refresh;
	private Integer timeout;
	private List<SelectedParam> params;
	private boolean suppressDialog;
	private boolean autoLaunchAction;
	private boolean silent;
	private String customMessage;
	private String customMessageLevel;
	private String customMessageTitle;

	public String getSilentActionParams() {
		String ret = null;
		if (params != null) {
			// { "ss_promoweek": ["descrizione","anno"],"ss_promoweek2":
			// ["descrizione2"],"ss_compratore": ["descrizione"]}
			final Map<String, Set<String>> parameters = new HashMap<>();
			params.forEach(p -> {
				Set<String> attributes = parameters.get(p.getDimension());
				if (attributes == null) {
					attributes = new HashSet<>();
				}
				attributes.add(p.getAttribute());
				parameters.put(p.getDimension(), attributes);
			});
			ret = "{" + parameters.entrySet().stream().map(e -> toJson(e)).collect(Collectors.joining(",")) + "}";
		} else {
			log.warn("empty parameters for action " + componentId);
			ret = "{}";
		}
		return ret;
	}

	private String toJson(final Map.Entry<String, Set<String>> entry) {
		StringBuffer ret = new StringBuffer();
		ret.append("\"").append(entry.getKey()).append("\"").append(":").append("[");
		ret.append(entry.getValue().stream().map(s -> {
			return "\"" + s + "\"";
		}).collect(Collectors.joining(",")));
		return ret.append("]").toString();
	}

	public List<SelectedParam> getVisibleParams() {
		List<SelectedParam> visibleParams = new ArrayList<>();
		if (this.params != null) {
			for (SelectedParam param : this.params) {
				if (param.isVisible()) {
					visibleParams.add(param);
				}
			}
		}
		return visibleParams;
	}
}
