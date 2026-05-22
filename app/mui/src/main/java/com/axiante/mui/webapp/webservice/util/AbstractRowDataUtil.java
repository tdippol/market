package com.axiante.mui.webapp.webservice.util;

import com.axiante.mui.common.utility.DateTimeUtils;

import java.math.BigDecimal;
import java.util.Date;

public abstract class AbstractRowDataUtil {

    protected String nullableValue(String value) {
        return value == null ? "" : value;
    }

    protected String nullableValue(Integer value) {
        return value == null ? "" : String.valueOf(value);
    }

    protected String nullableValue(Long value) {
        return value == null ? "" : String.valueOf(value);
    }

    protected String nullableValue(Double value) {
        return value == null ? "" : String.valueOf(value);
    }

    protected String nullableValue(BigDecimal value) {
        return value == null ? "" : String.valueOf(value);
    }

    protected String nullableValue(Date date) {
        return date == null ? "" : new DateTimeUtils().toExcelDate(date);
    }

    protected String nullableValue(Boolean value) {
        return value == null ? "false" : String.valueOf(value);
    }

    protected String boolFlagAsString(Boolean value) {
        return value == null ? String.valueOf(false) : String.valueOf(value);
    }
}
