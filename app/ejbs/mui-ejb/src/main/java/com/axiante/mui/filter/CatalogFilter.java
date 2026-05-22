package com.axiante.mui.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CatalogFilter implements Serializable{
	private String name;
	private List<String>  attributes = new ArrayList<>();
}
