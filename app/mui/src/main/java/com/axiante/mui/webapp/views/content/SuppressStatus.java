package com.axiante.mui.webapp.views.content;

import java.io.Serializable;
import lombok.Data;

@Data
public class SuppressStatus implements Serializable{
	private static final long serialVersionUID = -9004283086486003719L;
	boolean rows = true;
	boolean columns = true;
}
