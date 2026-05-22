package com.axiante.mui.webapp.webservice.pojo.pianificazione;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@EqualsAndHashCode
public class ItemPojo {

	@Getter
	private Long itemId = null;

	@Getter
	private Long compratoreId = null;

	@Getter
	private String elementDescription = null;

	@Getter
	private String detailRowId = null;

	public ItemPojo(Long itemId, Long compratoreId, String elementDescription, String detailRowId) {
		this.itemId = itemId;
		this.compratoreId = compratoreId;
		this.elementDescription = elementDescription;
		this.detailRowId = detailRowId;
	}

	public static ItemPojo fromJson(JsonNode jsonNode) {
		final Long itemId = getNodeAsLong(jsonNode, "idItem");
		final String itemDesc = String.format("%s - %s",
				getNodeAsString(jsonNode, "codiceItem"),
				getNodeAsString(jsonNode, "descrizioneItem"));
		return new ItemPojo(itemId, null, itemDesc, String.valueOf(itemId));
	}

	public String toJson() {
		StringBuffer buffer = new StringBuffer().append("{").append("itemId:")
				.append(getItemId() == null ? null : getItemId()).append(",").append("compratoreId:")
				.append(getCompratoreId() == null ? null : getCompratoreId()).append(",").append("elementDescription:")
				.append(getElementDescription() == null ? ""
						: "\"" + getElementDescription().replace("\\'", "'") + "\"")
				.append(",").append("detailRowId:")
				.append(getDetailRowId() == null ? "" : "\"" + getDetailRowId() + "\"").append("}");
		return buffer.toString();
	}

	private static String getNodeAsString(JsonNode jsonNode, String fieldName) {
		return jsonNode.get(fieldName) != null ? jsonNode.get(fieldName).asText().trim() : "";
	}

	private static Long getNodeAsLong(JsonNode jsonNode, String fieldName) {
		final String s = getNodeAsString(jsonNode, fieldName);
		return StringUtils.isBlank(s) ? null : Long.parseLong(s);
	}
}
