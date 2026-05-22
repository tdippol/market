package com.axiante.tm1.json.objects;

import lombok.Getter;
import lombok.Setter;

public class TM1Request {
	@Getter
	static final String field = "@odata.context";
	@Getter
	@Setter
	String value;
	@Getter
	@Setter
	String id;
}
