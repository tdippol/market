package com.axiante.mui.common.utility;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class AuditLogUtils implements Serializable{

    /**
     * Questo metodo verifica che i filtri relativi alle date sia coerenti con le logiche implementate
     * I filtri per le date sono applicate in questi tre casi:
     * - inserimento anno (stringa di 4 caratteri): es. 2020
     * - inserimento data (stringa di 10 caratteri): es. 22.10.2020
     * - inserimento datae ora (stringa di 13 caratteri):  es. 22.10.2020 09 o es. 22.10.2020 10
     *
     * NOTA: i formati delle date sono coerenti con quelli delle sezione ADMIN della MUI
     *
     * @param filterValue
     * @return
     */
    public boolean checkFilterLogDate(final String filterValue) {
        int length = filterValue.trim().length();
        return length == 4 && new StringUtils().isInteger(filterValue) //anno
            || filterValue.trim().length() == 10 && filterValue.trim().contains(".") && !filterValue.trim().contains(" ") // data senza ora
            || filterValue.trim().length() == 13 && filterValue.trim().contains(".") && filterValue.trim().contains(" "); // data e ora in formato HH
    }

    /**
     * Questo metodo crea i filtri relativi alle date da usare nelle query di ricerca
     * I filtri per le date sono applicate in questi tre casi:
     * - inserimento anno (stringa di 4 caratteri): es. 2020
     * - inserimento data (stringa di 10 caratteri): es. 22.10.2020
     * - inserimento datae ora (stringa di 13 caratteri):  es. 22.10.2020 09 o es. 22.10.2020 10
     *
     * NOTA: i formati delle date sono coerenti con quelli delle sezione ADMIN della MUI
     *
     * @param filterValue
     * @param firstDate
     * @return
     */
    public Date composeLogDateQueryCondition(final String filterValue, boolean firstDate) {
        int length = filterValue.trim().length();
        Date filterLogDate = null;
        if(length == 4 && new StringUtils().isInteger(filterValue)) {
            //Esempio filtro 2020
            if(firstDate) {
                filterLogDate = new Calendar.Builder().setDate(Integer.parseInt(filterValue), 0, 1).setTimeOfDay(0, 0,0).build().getTime();
            } else {
                filterLogDate = new Calendar.Builder().setDate(Integer.parseInt(filterValue), 11, 31).setTimeOfDay(23, 59,59).build().getTime();
            }
        }
        if(filterValue.trim().length() == 10 && filterValue.trim().contains(".") && !filterValue.trim().contains(" ")) {
            //Esempio filtro 20.12.2020
            if(firstDate) {
                filterLogDate = new Calendar.Builder().setDate(Integer.parseInt(filterValue.trim().split("\\.")[2]),
                    Integer.parseInt(filterValue.trim().split("\\.")[1]) - 1,
                    Integer.parseInt(filterValue.trim().split("\\.")[0])).setTimeOfDay(0, 0, 0).build().getTime();
            } else {
                filterLogDate = new Calendar.Builder().setDate(Integer.parseInt(filterValue.trim().split("\\.")[2]),
                    Integer.parseInt(filterValue.trim().split("\\.")[1]) - 1,
                    Integer.parseInt(filterValue.trim().split("\\.")[0])).setTimeOfDay(23, 59, 59).build().getTime();
            }
        }
        //Esempio filtro 20.12.2020 09
        if(filterValue.trim().length() == 13 && filterValue.trim().contains(".") && filterValue.trim().contains(" ")) {
            String[] filterSplit = filterValue.split(" ");
            //data con ora
            if(firstDate) {
                filterLogDate = new Calendar.Builder().setDate(Integer.parseInt(filterSplit[0].trim().split("\\.")[2]),
                    Integer.parseInt(filterSplit[0].trim().split("\\.")[1]) - 1,
                    Integer.parseInt(filterSplit[0].trim().split("\\.")[0])).setTimeOfDay(
                    Integer.parseInt(filterSplit[1].trim()), 0, 0).build().getTime();
            } else {
                filterLogDate = new Calendar.Builder().setDate(Integer.parseInt(filterSplit[0].trim().split("\\.")[2]),
                    Integer.parseInt(filterSplit[0].trim().split("\\.")[1]) - 1,
                    Integer.parseInt(filterSplit[0].trim().split("\\.")[0])).setTimeOfDay(
                    Integer.parseInt(filterSplit[1].trim()), 59, 59).build().getTime();
            }
        }
        return filterLogDate;
    }

}
