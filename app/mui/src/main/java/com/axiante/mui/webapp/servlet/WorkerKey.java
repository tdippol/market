package com.axiante.mui.webapp.servlet;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public 	class WorkerKey implements Serializable {
	private static final long serialVersionUID = -3896097867706944672L;
	String user;
	String grid;
}

