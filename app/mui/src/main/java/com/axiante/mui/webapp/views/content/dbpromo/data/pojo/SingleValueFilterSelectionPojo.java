package com.axiante.mui.webapp.views.content.dbpromo.data.pojo;

public class SingleValueFilterSelectionPojo extends FilterSelectionPojo{
    String value;
    public SingleValueFilterSelectionPojo(String value){
        this.value = value;
    }

    public String getKey(){
        return value;
    }

    public String getLabel(){
        return value;
    }
}
