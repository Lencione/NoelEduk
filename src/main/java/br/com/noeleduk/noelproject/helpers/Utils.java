package br.com.noeleduk.noelproject.helpers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Utils {
  public static Date getDateFromLocalDate(LocalDate startDate) {
    ZonedDateTime zonedDateTime = startDate.atStartOfDay(ZoneId.systemDefault());
    return Date.from(zonedDateTime.toInstant());
  }
}
