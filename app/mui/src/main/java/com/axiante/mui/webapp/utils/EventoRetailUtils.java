package com.axiante.mui.webapp.utils;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.persistence.dto.EventoRetailDTO;
import com.axiante.mui.dbpromo.persistence.entities.MuiEventoRetailEntity;
import com.axiante.mui.dbpromo.persistence.entities.MuiMacrospazioMediaEntity;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;

public class EventoRetailUtils {
    private final MacrospaziDateUtils macrospaziDateUtils;

    private final DateTimeUtils dateTimeUtils;

    public EventoRetailUtils() {
        macrospaziDateUtils = new MacrospaziDateUtils();
        dateTimeUtils = new DateTimeUtils();
    }

    public EventoRetailUtils(MacrospaziDateUtils macrospaziDateUtils, DateTimeUtils dateTimeUtils) {
        this.macrospaziDateUtils = macrospaziDateUtils;
        this.dateTimeUtils = dateTimeUtils;
    }

    /**
     * Checks if an event can be added. An event can be added if is a valid event, it does not already
     * exists, is a future event and is coherent with the macrospazio
     *
     * @param event
     * @return
     */
    public boolean canAdd(MuiEventoRetailEntity event) {
        if (!isValid(event,false)) return false;
        if (event.getId() != null) return false;
        // posso aggiungere solo eventi futuri
        if( !isFuture(event) ) return false;
        return isCoherent(event, false);
    }

    /**
     * checks if an event is editable. An event is editable if is a valid event, it exists and is not
     * a past event. In case the event is an active event then the end date has to be greater than or
     * equal to today: one cannot close an event that is ative. In case the event is a future event,
     * one can change the start date as long as it is greater than or equal to today (can force open
     * an event) and the end date stays in the future.
     *
     * @param original
     * @param event
     * @return
     */
    public boolean canEdit(MuiEventoRetailEntity original, MuiEventoRetailEntity event) {
        if (!isValid(event, true)) return false;
        if (event.getId() == null) return false;
        Date today = dateTimeUtils.getDateWithoutTime(new Date());
        if (isActive(original)) {
            // evento in corso, data fine non deve essere prima di oggi (posso chiuderlo oggi, non ieri)
            if (!dateTimeUtils.isAfter( event.getDataFine(),today, true)) return false;
        } else if (isFuture(original)) {
            // data inizio > oggi (posso aprirlo domani, non oggi)
            if (!dateTimeUtils.isAfter(event.getDataInizio(),today, true)) return false;
        } else {
            // ne attivo ne futuro, non si puo' editare
            return false;
        }
        // deve essere coerente con il macrospazio
        return isCoherent(event, true);
    }

    /**
     * checks if an event can be deleted. An event can be deleted if is a valid event, exists and is a
     * future event
     *
     * @param event
     * @return
     */
    public boolean canDelete(MuiEventoRetailEntity event) {
        if (!isValid(event)) return false;
        if (event.getId() == null) return false;
        if (!isFuture(event)) return false;
        return true;
    }

    /**
     * checks if an event is formally valid. An event is valid if has a value, has a cause, has a
     * supplier and the start date is before the end date
     *
     * @param event
     * @return
     */
    public boolean isValid(MuiEventoRetailEntity event) {
        return isValid(event, true);
    }

    public boolean isValid(MuiEventoRetailEntity event, boolean editMode) {
        if (event == null) return false;
        if (event.getValoreContributo() == null) return false;
        if (StringUtils.isBlank(event.getCodiceCausale())) return false;
        if (StringUtils.isBlank(event.getCodiceFornitore())) return false;
        // data inizio e fine devono esserci
        if ( event.getDataFine() == null || event.getDataInizio() == null) return false;
        // le date devono essere coerenti
        return macrospaziDateUtils.isValid(event.getDataInizio(), event.getDataFine(), editMode) ;
    }

    public boolean isCoherent(MuiEventoRetailEntity event) {
        return isCoherent(event, true);
    }
    public boolean isCoherent(MuiEventoRetailEntity event, boolean editMode) {
        if ( event == null ) return false;
        if ( event.getMacrospazio() == null ) return false;
        return isCoherent(event, event.getMacrospazio(), editMode);
    }

    /**
     * checks if an event is coherent with a macrospazio. It is coherent if the event dates fall
     * within the dates of the macrospazio (if present)
     *
     * @param event
     * @param macrospazio
     * @return
     */
    public boolean isCoherent(MuiEventoRetailEntity event, MuiMacrospazioMediaEntity macrospazio) {
        return isCoherent(event, macrospazio, true);
    }

    /**
     * checks if an event is coherent with a macrospazio. It is coherent if the event dates fall
     * within the dates of the macrospazio (if present)
     *
     * @param event
     * @param macrospazio
     * @return
     */
    public boolean isCoherent(@NonNull MuiEventoRetailEntity event, MuiMacrospazioMediaEntity macrospazio, boolean editMode) {
        if (macrospazio == null) return false;
        if (macrospazio.getDataInizio() != null) {
            // quindi devo solo controllare che data inizio evento >= data inizio macrospazio
            // e data fine evento <= data fine macrospazio
            // controlla data inizio evento
            if (!dateTimeUtils.isAfter(event.getDataInizio(), macrospazio.getDataInizio(), true))
                return false;
        }
        if (macrospazio.getDataFine() != null) {
            // controlla data fine evento
            if (!dateTimeUtils.isAfter(macrospazio.getDataFine(), event.getDataFine(), true))
                return false;
            // data inizio evento non puo' essere dopo perche ho gia' controllato
        }
        return true;
    }

    /**
     * Checks if an event is active. An event is active if it has started and has not ended yet
     *
     * @param event
     * @return
     */
    public boolean isActive(MuiEventoRetailEntity event) {
        return hasBegun(event) && !hasEnded(event);
    }

    /**
     * checks if an event is future. An event is a future event if has not started yet
     *
     * @param event
     * @return
     */
    public boolean isFuture(MuiEventoRetailEntity event) {
        return !hasBegun(event);
    }


    /**
     * Checks if an event is active. An event is active if it has started and has not ended yet
     *
     * @param event
     * @return
     */
    public boolean isActive(EventoRetailDTO event) {
        return hasBegun(event) && !hasEnded(event);
    }

    /**
     * checks if an event is future. An event is a future event if it has not started yet
     *
     * @param event
     * @return
     */
    public boolean isFuture(EventoRetailDTO event) {
        return !hasBegun(event);
    }

    /**
     * Checks if an event has begun. An event has begun if today is after or equal to the start date
     *
     * @param evento
     * @return
     */
    public boolean hasBegun(EventoRetailDTO evento) {
        return hasBegun(evento.getDataInizio());
    }

    /**
     * Checks if an event has begun. An event has begun if today is after or equal to the start date
     *
     * @param evento
     * @return
     */
    public boolean hasBegun(MuiEventoRetailEntity evento) {
        return hasBegun(evento.getDataInizio());
    }

    /**
     * A date has begun if today is after or equal to the start date
     *
     * @param dataInizio
     * @return
     */
    protected boolean hasBegun(Date dataInizio) {
        Date currentDate = dateTimeUtils.getDateWithoutTime(new Date(System.currentTimeMillis()));
        return dateTimeUtils.isAfter(currentDate, dataInizio, true);
    }

    /**
     * Checks if an event has ended. An event has ended if today is after the end date
     *
     * @param evento
     * @return
     */
    public boolean hasEnded(EventoRetailDTO evento) {
        return hasEnded(evento.getDataFine());
    }

    /**
     * Checks if an event has ended. An event has ended if today is after the end date
     *
     * @param evento
     * @return
     */
    public boolean hasEnded(MuiEventoRetailEntity evento) {
        return hasEnded(evento.getDataFine());
    }

    protected boolean hasEnded(Date dataFine) {
        Date currentDate = dateTimeUtils.getDateWithoutTime(new Date(System.currentTimeMillis()));
        return dateTimeUtils.isAfter(currentDate, dataFine, false);
    }

    /**
     * checks if an event is deletable. An event is deletable if it is a future event
     *
     * @param evento
     * @return
     */
    public boolean isDeletable(EventoRetailDTO evento) {
        return isFuture(evento);
    }

    /**
     * checks if an event is editable. An event is editable if it is a future event or an active event
     *
     * @param evento
     * @return
     */
    public boolean isEditable(EventoRetailDTO evento) {
        return isFuture(evento) || !hasEnded(evento);
    }

}
