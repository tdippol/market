// java
package com.axiante.mui.webapp.views.util;

import javax.faces.component.UIInput;
import javax.faces.event.AjaxBehaviorEvent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public final class UiUtils {

    private UiUtils() { /* utility */ }

    public static <T> T getEventValue(AjaxBehaviorEvent event, Class<T> targetClass) {
        Objects.requireNonNull(event, "event must not be null");
        Object source = event.getSource();
        if (!(source instanceof UIInput)) {
            throw new IllegalArgumentException("Event source is not a UIInput: " + (source == null ? "null" : source.getClass().getName()));
        }
        Object value = ((UIInput) source).getValue();
        if (value == null) {
            return null;
        }
        return targetClass.cast(value);
    }

    public static String getEventStringValue(AjaxBehaviorEvent event) {
        return getEventValue(event, String.class);
    }

    public static Double getEventDoubleValue(AjaxBehaviorEvent event) {
        return getEventValue(event, Double.class);
    }

    public static BigDecimal getEventBigDecimalValue(AjaxBehaviorEvent event) {
        return getEventValue(event, BigDecimal.class);
    }

    public static Date getEventDateValue(AjaxBehaviorEvent event) {
        return getEventValue(event, Date.class);
    }
}