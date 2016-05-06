package com.jf.securirty;

import java.io.PrintStream;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource
{
  protected Object determineCurrentLookupKey()
  {
    String config = CustomerContextHolder.getCustomerType();
    System.out.println("ooooooooooooooooooooooooooooooooo" + config);
    config = "dataSource1";

    return config;
  }
}