package com.axiante.mui.model;

import java.io.Serializable;
import java.util.List;

import com.axiante.tm1.mdx.filter.Filter;

public interface FilterProducer extends Serializable{
	List<Filter> getFilters(String json);
}
