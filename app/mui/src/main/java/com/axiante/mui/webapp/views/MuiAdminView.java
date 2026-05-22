package com.axiante.mui.webapp.views;

public interface MuiAdminView {

    boolean isViewChanged();

    void init();

    default void forceReload(){
        if ( isViewChanged() )
            init();
    }
}
