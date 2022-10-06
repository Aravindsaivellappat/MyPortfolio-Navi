package com.navi.myportfolio.util;

public class Utilityfunctions {

  public static Double getPercentFromString(String percentString) {
    return Double.parseDouble(percentString.substring(0, percentString.length() - 1)) / 100;
  }

  public static Double getSipAddedAmount(Double amount, String percent) {
    return amount + (amount * getPercentFromString(percent));
  }
}
