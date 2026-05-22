package com.axiante.mui.common.utility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DateUtil;

@Slf4j
public class DateTimeUtils implements Serializable {
  private static final long serialVersionUID = -8968953821346395584L;
  private static final String TIME_PATTERN = "HH:mm";
  public static final String TIME_PATTERN_WITH_SECONDS = "HH:mm:ss";
  public static final String ESSELUNGA_PATTERN = "EEE dd-MM-yy";
  public static final Locale ESSELUNGA_LOCALE = Locale.ITALIAN;
  public static final String ITALIAN_DATE_PATTERN = "dd/MM/yyyy";

  public static final String I_SEMESTRE = "I SEMESTRE";
  public static final String II_SEMESTRE = "II SEMESTRE";

  public static final boolean INIZIO = true;
  public static final boolean FINE = !INIZIO;

  private static final ThreadLocal<SimpleDateFormat> timeFormatterPool = new ThreadLocal<>();
  private static final ThreadLocal<SimpleDateFormat> timeSecondsFormatterPool = new ThreadLocal<>();
  private static final ThreadLocal<SimpleDateFormat> esselungaFormatterPool = new ThreadLocal<>();

  private static final ThreadLocal<SimpleDateFormat> formatoItalianoPool = new ThreadLocal<>();

  /** la data usata da Excel per contare il numero dei giorni e trasformare una data in un numero */
  //	private static final LocalDate EXCEL_EPOCH_REF = LocalDate.of(1899, Month.DECEMBER, 30);
  public LocalDateTime todayAt(@NonNull final String time) throws Exception {
    return dateAt(LocalDateTime.now(), time);
  }

  public LocalDateTime dateAt(@NonNull final LocalDateTime date, @NonNull final String time)
      throws Exception {
    String[] s = time.split(":");
    if (s.length != 2) {
      throw new IllegalArgumentException(
          "the value " + time + " in not in the expeted format " + TIME_PATTERN);
    }
    int hours = Integer.parseInt(s[0]);
    int minutes = Integer.parseInt(s[1]);

    return date.withHour(hours).withMinute(minutes);
  }

  /**
   * calcola il semestre a partire da un anno di riferimento e una data
   *
   * @param year
   * @param date
   * @return
   */
  public String calculateSemester(@NonNull final String year, @NonNull final Date date) {
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    int startDateYear = localDate.getYear();
    int startDateMonth = localDate.getMonthValue();
    int startYear = Integer.parseInt(year);
    if (startDateYear > startYear) {
      return II_SEMESTRE;
    } else if (startDateYear < startYear) {
      return I_SEMESTRE;
    } else {
      return startDateMonth <= 6 ? I_SEMESTRE : II_SEMESTRE;
    }
  }

  /**
   * calcola la descrizione estesa di una promozione formattando la data inizio, la data fine e
   * costruendo una stringa
   *
   * @param codicePromozione
   * @param dataInizio
   * @param dataFine
   * @param descrizione
   * @return
   */
  public String calculateExtendedDescription(
      @NonNull final String codicePromozione,
      @NonNull final Date dataInizio,
      @NonNull final Date dataFine,
      String descrizione) {
    final Calendar dataInizioCalendar = Calendar.getInstance();
    dataInizioCalendar.setTime(dataInizio);
    dataInizioCalendar.add(Calendar.DATE, -1);
    final long diffInMillies =
        Math.abs(dataFine.getTime() - dataInizioCalendar.getTime().getTime());
    final long promoDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    final String formatDate = getFormatoEsselunga().format(dataInizio);
    return String.format(
        "[%s %s - %sgg] %s",
        codicePromozione,
        formatDate.substring(0, 1).toUpperCase().concat(formatDate.substring(1)),
        promoDays,
        descrizione);
  }

  public boolean isBefore(final Date date, final Date end, Boolean closed, Boolean acceptNull) {
    if (acceptNull) {
      if (Objects.isNull(date) || Objects.isNull(end)) {
        return true;
      }
    } else {
      StringBuffer message = new StringBuffer();
      if (Objects.isNull(date)) {
        message.append("date is marked not null but is null");
      }
      if (Objects.isNull(end)) {
        if (message.length() > 0) {
          message.append("\n");
        }
        message.append("end is marked not null but is null");
      }
      if (message.length() > 0) {
        throw new NullPointerException(message.toString());
      }
    }
    if (closed) {
      return date.before(end) || date.equals(end);
    }
    return date.before(end);
  }

  public boolean isAfter(final Date date, final Date start, Boolean closed, Boolean acceptNull) {
    if (acceptNull) {
      if (Objects.isNull(date) || Objects.isNull(start)) {
        return true;
      }
    } else {
      StringBuffer message = new StringBuffer();
      if (Objects.isNull(date)) {
        message.append("date is marked not null but is null");
      }
      if (Objects.isNull(start)) {
        if (message.length() > 0) {
          message.append("\n");
        }
        message.append("start is marked not null but is null");
      }
      if (message.length() > 0) {
        throw new NullPointerException(message.toString());
      }
    }
    if (closed) {
      return date.after(start) || date.equals(start);
    }
    return date.after(start);
  }

  public boolean isBefore(final Date date, final Date end, Boolean closed) {
    return isBefore(date, end, closed, false);
  }

  public boolean isAfter(final Date date, final Date start, Boolean closed) {
    return isAfter(date, start, closed, false);
  }

  /**
   * Converte una data in formato excel (=Excel.DATEVALUE)
   *
   * @param date
   * @return
   */
  public String toExcelDate(Date date) {
    if (date == null) {
      return null;
    }
    return "" + DateUtil.getExcelDate(date);
  }

  /**
   * Converte una data in formato italiano (dd/MM/yyyy) in {@link Date}
   *
   * @param date
   * @return
   */
  public Date toDate(String date) {
    return toDate(date, true);
  }

  /**
   * Converte una data in formato italiano (dd/MM/yyyy) in {@link Date}
   *
   * @param date
   * @param lenient
   * @return
   */
  public Date toDate(String date, boolean lenient) {
    try {
      SimpleDateFormat formatoItaliano = getFormatoItaliano();
      formatoItaliano.setLenient(lenient);
      return this.toDate(date, formatoItaliano);
    } catch (Exception e) {
      log.error(String.format("Error parsing %s as valid date", date), e);
      return null;
    }
  }

  /**
   * converte una data in formato excel (numerico come {@link String})
   *
   * @param excelDate
   * @return
   */
  public Date excelToDate(String excelDate) {
    if (excelDate == null || excelDate.isEmpty()) {
      return null;
    }
    return DateUtil.getJavaDate(new BigDecimal(excelDate).doubleValue());
  }

  /**
   * Converte una string in Date
   *
   * @param dateInput
   * @param sdf
   * @return
   */
  public Date toDate(String dateInput, SimpleDateFormat sdf) {
    if (dateInput == null || dateInput.isEmpty()) {
      return null;
    }
    try {
      if (sdf != null) {
        return sdf.parse(dateInput);
      } else {
        log.error("trying to format a null date");
        return null;
      }
    } catch (ParseException e) {
      log.error(
          String.format("error converting %s to date with format %s", dateInput, sdf.toPattern()),
          e);
      return null;
    }
  }

  /**
   * converte una stringa in formato HH:mm in un oggetto LocalTime
   *
   * @param time
   * @return
   * @throws Exception
   */
  public LocalTime timeAt(@NonNull final String time) throws Exception {
    String[] s = time.split(":");
    if (s.length != 2) {
      throw new IllegalArgumentException(
          "the value " + time + " in not in the expeted format " + TIME_PATTERN);
    }
    int hours = Integer.parseInt(s[0]);
    int minutes = Integer.parseInt(s[1]);

    return LocalTime.of(hours, minutes);
  }

  /**
   * Restituisce la minima data inizio in DBPROMO In creazione promozione , in negozi promo (form e
   * TAB ) , in modifica promo , ed in pianificazione, le date inizio devono essere consentite da
   * "oggi +1" non da "oggi"
   *
   * @return
   */
  public LocalDateTime getDBPromoMinDate() {
    return LocalDateTime.now();
  }

  public boolean compatibleDates(
      @NonNull final Date date, @NonNull final Date newDate, boolean inizio) {
    if (inizio) {
      return isAfter(getDateWithoutTime(date), getDateWithoutTime(newDate), true);
    } else {
      return isBefore(getDateWithoutTime(date), getDateWithoutTime(newDate), true);
    }
  }

  public boolean equalsNoTime(@NonNull final Date d1, @NonNull final Date d2) {
    return getDateWithoutTime(d1).equals(getDateWithoutTime(d2));
  }

  public Date getDateWithoutTime(@NonNull final Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }

  public static String toTime(@NonNull final Date time) {
    SimpleDateFormat sdf = timeFormatterPool.get();
    if (sdf == null) {
      sdf = new SimpleDateFormat(TIME_PATTERN);
      timeFormatterPool.set(sdf);
    }
    return sdf.format(time);
  }

  public static String toTimeWithSeconds(@NonNull final Date time) {
    SimpleDateFormat sdf = timeSecondsFormatterPool.get();
    if (sdf == null) {
      sdf = new SimpleDateFormat(TIME_PATTERN_WITH_SECONDS);
      timeFormatterPool.set(sdf);
    }
    return sdf.format(time);
  }

  public static synchronized Date toTime(@NonNull final String time) {
    return toTime(time, true);
  }

  public static Date toTime(@NonNull final String time, boolean lenient) {
    try {
      SimpleDateFormat sdf = timeFormatterPool.get();
      if (sdf == null) {
        sdf = new SimpleDateFormat(TIME_PATTERN);
        timeFormatterPool.set(sdf);
      }
      synchronized (sdf) {
        sdf.setLenient(lenient);
        return sdf.parse(time);
      }
    } catch (ParseException e) {
      log.error("error parsing time " + time, e);
    }
    return null;
  }

  public static Date toTimeWithSeconds(@NonNull final String time) {
    try {
      SimpleDateFormat sdf = timeSecondsFormatterPool.get();
      if (sdf == null) {
        sdf = new SimpleDateFormat(TIME_PATTERN_WITH_SECONDS);
        timeFormatterPool.set(sdf);
      }
      return sdf.parse(time);
    } catch (ParseException e) {
      log.error("error parsing time " + time, e);
    }
    return null;
  }

  public static SimpleDateFormat getTimeFormat() {
    SimpleDateFormat sdf = timeFormatterPool.get();
    if (sdf == null) {
      sdf = new SimpleDateFormat(TIME_PATTERN);
      timeFormatterPool.set(sdf);
    }
    return sdf;
  }

  public static SimpleDateFormat getFormatoEsselunga() {
    SimpleDateFormat sdf = esselungaFormatterPool.get();
    if (sdf == null) {
      sdf = new SimpleDateFormat(ESSELUNGA_PATTERN, ESSELUNGA_LOCALE);
      esselungaFormatterPool.set(sdf);
    }
    return sdf;
  }

  public static SimpleDateFormat getFormatoItaliano() {
    SimpleDateFormat sdf = formatoItalianoPool.get();
    if (sdf == null) {
      sdf = new SimpleDateFormat(ITALIAN_DATE_PATTERN);
      formatoItalianoPool.set(sdf);
    }
    return sdf;
  }

  public static Date addDaysToDate(@NonNull final Date date, int days) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, days);
    return cal.getTime();
  }


}
