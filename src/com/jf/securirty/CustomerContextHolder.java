package com.jf.securirty;

import java.util.Calendar;

public abstract class CustomerContextHolder
{
  public static final String DATA_SOURCE_MAJOR = "dataSource";
  public static final String DATA_SOURCE_MINOR = "dataSource1";
  private static final ThreadLocal<String> dataSource = new ThreadLocal();

  public static void setCustomerType(String customerType) {
    if (checkTime())
      dataSource.set(customerType);
  }

  public static String getCustomerType()
  {
    return dataSource.get() == null ? "dataSource" : (String)dataSource.get();
  }

  public static void clearCustomerType() {
    dataSource.remove();
  }

  private static boolean checkTime()
  {
    int month = 0;
    int day = 0;
    Calendar c = Calendar.getInstance();
    month = c.get(2) + 1;
    day = c.get(5);

    return (day <= 25) && (month <= 4);
  }
}