package com.axiante.mui.common.utility;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminModeUtils implements Serializable {

	public boolean isCheckDay(@NonNull final Integer dayOfWeek, @NonNull final Boolean allDays,
			List<Integer> selectedDays) {
		log.debug("controllo parametro giorno ...");
		boolean today = false;
		if (allDays) {
			today = true;
		} else {
			today = selectedDays != null && selectedDays.size() > 0 && selectedDays.contains(dayOfWeek);
		}
		return today;
	}

	/**
	 * controlla se la data+ora in timeToCheck e' all'interno dell'intervallo delle
	 * ore start-end. Se start > end, allora vengono invertiti gli estremi ed il
	 * risultato. es:
	 * 
	 * <pre>
	 * 	time= 02.00, start=23.00, end=06.00 ==> inversione = true, start = 06.00, end = 23.00
	 *  06.00<=02.00<=23.00 ? false;
	 *  inversione ? true
	 *  risultato => true
	 *
	 * 	time= 02.00, start=06.00, end=23.00 ==> inversione = false, start = 06.00, end = 23.00
	 *  06.00<=02.00<=23.00 ? false;
	 *  inversione ? false
	 *  risultato => false
	 *  
	 *  time= 09.00, start=23.00, end=06.00 ==> inversione = true, start = 06.00, end = 23.00
	 *  06.00<=09.00<=23.00 ? true;
	 *  inversione ? true
	 *  risultato => false
	 *  
	 *  time= 09.00, start=06.00, end=23.00 ==> inversione = false, start = 06.00, end = 23.00
	 *  06.00<=09.00<=23.00 ? true;
	 *  inversione ? false
	 *  risultato => true
	 * </pre>
	 *
	 * @param timeToCheck ora e giorno da controllare
	 * @param start       considera solo l'ora: inizio dell'intervallo
	 * @param end         considera solo l'ora: fine dell'intervallo
	 * @return
	 */
	public boolean isTimeBetween(@NonNull final LocalDateTime timeToCheck, @NonNull final String start,
			@NonNull final String end) {
		boolean result = false;
		final DateTimeUtils dateTimeUtils = new DateTimeUtils();
		try {
			LocalTime startTime = dateTimeUtils.timeAt(start);
			LocalTime endTime = dateTimeUtils.timeAt(end);
			LocalTime time = timeToCheck.toLocalTime();
			final boolean invert = startTime.isAfter(endTime);
			if (invert) {
				final LocalTime temp = startTime;
				startTime = endTime;
				endTime = temp;
			}
			if (log.isDebugEnabled()) {
				log.info("start : " + startTime.toString());
				log.info("end : " + endTime.toString());
				log.info("now : " + timeToCheck.toString());
			}
			if (time.isAfter(startTime) && time.isBefore(endTime)) {
				// sono in mezzo
				result = true;
			} else if (time.equals(startTime) || time.equals(endTime)) {
				// sono sugli estremi
				result = invert ? false:true;
			}
			if (invert)
				result = !result;
		} catch (Exception e) {
			log.error("errore nel calcolo della data di partenza del task", e);
		}
		return result ;
		
	}

}
