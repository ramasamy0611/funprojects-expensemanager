package com.ram.projects.expensemanager.common;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class CommonUtils {
  public static void validateIfNull(Instant instant) {
    Objects.requireNonNull(instant, "Instant type data cannot be empty");
  }

  public static void validateIfNull(Date date) {
    Objects.requireNonNull(date, "Date type data cannot be empty");
  }

  public static void validateIfNull(String string) {
    if (isBlank(string)) {
      throw new NullPointerException("String cannot be empty");
    }
  }

  public static boolean isNull(Instant instant) {
    return instant == null;
  }

  public static boolean isNull(Date date) {
    return date == null;
  }

  public static boolean isNull(String string) {
    return string == null;
  }
  public static <T> boolean isNull(T object) {
    return object == null;
  }
  public static boolean isNull(Integer integer) {
    return integer == null;
  }

  public static boolean isBlank(String string) {
    return string == null || string.strip().isBlank();
  }
}
