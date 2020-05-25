package com.ram.projects.expensemanager.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {
  private static final Logger LOG = LoggerFactory.getLogger(DateTimeUtils.class);

  public static Instant currentTimeInstant() {
    Instant instant = Instant.now(Clock.system(ZoneId.systemDefault()));
    return instant;
  }

  public static void printAvailableTimes() {
    LOG.debug("Available time zones");
    ZoneId.getAvailableZoneIds().stream()
        .map(zoneIdStr -> ZoneId.of(zoneIdStr))
        .filter(zoneId -> ("IST".equalsIgnoreCase(zoneId.getId())))
        .forEach(
            zoneId ->
                LOG.debug(
                    "Current Default ZoneId :{}, zone display name :{}, Instant from clock :{}",
                    zoneId.toString(),
                    zoneId.getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    Instant.now(Clock.system(zoneId))));
  }

  public static Instant utilDateToInstantWithoutTime(Date date) {
    return new java.sql.Date(date.getTime())
        .toLocalDate()
        .atStartOfDay()
        .truncatedTo(ChronoUnit.HOURS)
        .toInstant(ZoneOffset.UTC);
  }
}
