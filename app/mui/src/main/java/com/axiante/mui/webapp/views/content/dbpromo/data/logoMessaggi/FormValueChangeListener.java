package com.axiante.mui.webapp.views.content.dbpromo.data.logoMessaggi;

import com.axiante.mui.dbpromo.business.enumeration.MessaggiComponentsEnum;

public interface FormValueChangeListener {
    void onFormValueChange(MessaggiComponentsEnum component, Object oldValue, Object newValue);
}
