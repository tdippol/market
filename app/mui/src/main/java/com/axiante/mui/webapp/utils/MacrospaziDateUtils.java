package com.axiante.mui.webapp.utils;

import com.axiante.mui.common.utility.DateTimeUtils;

import java.util.Date;

public class MacrospaziDateUtils {
    final DateTimeUtils utils = new DateTimeUtils();

    public boolean isValidForMacrospazio(Date dataInizio, Date dataFine) {
        /*
    Macrospazi rules:
    both null -> valid
    inizio not null, fine null -> inizio >= tomorrow
    inizio null, fine not null -> fine >= tomorrow
    both not null -> inizio >= tomorrow && fine >= inizio + 1 day
         */
        return isValidForMacrospazio(dataInizio, dataFine, false);
    }

    public boolean isValidForMacrospazio(Date dataInizio, Date dataFine, boolean editMode) {
        if (editMode) {
            return validateOnEdit(dataInizio, dataFine);
        }
        return validateOnInsert(dataInizio, dataFine);
    }

    public boolean isValid(Date dataInizio, Date dataFine, boolean editMode) {
        // Normalize today and tomorrow to start of day
        Date today = utils.getDateWithoutTime(new Date());
        Date tomorrow = DateTimeUtils.addDaysToDate(today, 1);

        // Normalize inizio and fine to start of day
        Date inizio = dataInizio != null ? utils.getDateWithoutTime(dataInizio) : null;
        Date fine = dataFine != null ? utils.getDateWithoutTime(dataFine) : null;

        if (editMode) {
            //in edit mode inizio can be anything as long as it is not null
            if (inizio == null) {
                return false;
            }
        } else {
            // Check inizio: must be at least tomorrow
            if (inizio != null && (inizio.before(tomorrow))) {
                return false;
            }
        }

        // Check fine
        if (fine != null) {
            if (inizio != null) {
                Date inizioPlusOne = DateTimeUtils.addDaysToDate(inizio, 1);
                if (fine.before(inizioPlusOne)) {
                    return false;
                }
            } else {
                if (fine.before(tomorrow)) {
                    return false;
                }
            }
        }
        return true;
    }

        /*
    la validazione delle date di un macrospazio e' cambiata.
    posso cancellare data inizio
    posso cancellare data fine
    se ho data fine allora max data inizio e' data fine
    se ho data inizio allora min data fine e' max(oggi, data inizio)
    non posso iniziare nel passato
    non posso finire nel passato
     */

    protected boolean validateOnEdit(Date dataInizio, Date dataFine) {
        Date today = utils.getDateWithoutTime(new Date());
        if (dataInizio != null) {
            // ho data inizio, allora se ho data fine quests non puo' essere prima di data inizio
            if (dataFine != null && utils.getDateWithoutTime(dataFine).before(utils.getDateWithoutTime(dataInizio))) {
                return false;
            }
        }
        if ( dataFine != null) {
            // ho data fine, allora non puo essere nel passato
            return !utils.getDateWithoutTime(dataFine).before(today);
        }

        return true;
    }

    protected boolean validateOnInsert(Date dataInizio, Date dataFine) {
        if ( dataInizio != null ) {
            // se ho data inizio allora questa non puo' essere prima di oggi
            Date today = utils.getDateWithoutTime(new Date());
            if ( utils.getDateWithoutTime(dataInizio).before(today)) {
                return false;
            }
            // se ho data fine allora questa non puo' essere prima di data inizio
            if ( dataFine != null && utils.getDateWithoutTime(dataFine).before(utils.getDateWithoutTime(dataInizio))) {
                return false;
            }
        }

        if ( dataFine != null) {
            // se ho data fine allora questa non puo essere prima di oggi
            Date today = utils.getDateWithoutTime(new Date());
            if (utils.getDateWithoutTime(dataFine).before(today)) {
                return false;
            }
            // se ho data inizio allora questa non puo' essere dopo data fine
            return dataInizio == null || !utils.getDateWithoutTime(dataInizio).after(utils.getDateWithoutTime(dataFine));
        }

        return true;
    }
}
