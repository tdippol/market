package com.axiante.mui.webapp.views.content.aggrid.params;

import java.io.Serializable;
import lombok.Data;

@Data
public class GridParam implements Serializable {
	private static final long serialVersionUID = 2729309923843742409L;
	private Boolean rowSuppressionEnabled;
	private Boolean gridFilterEnabled;
    private Boolean colSuppressionEnabled;
    private String title;
    private Integer height;
    private String css;
    private String subTitle;
}
