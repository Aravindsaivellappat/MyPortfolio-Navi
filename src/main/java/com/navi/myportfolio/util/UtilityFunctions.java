package com.navi.myportfolio.util;

import com.navi.myportfolio.entity.Portfolio;

public class UtilityFunctions {

  // Given string of percentage value, return the extracted percent.
  public static Double getPercentFromString(String percentString) {
    return Double.parseDouble(percentString.substring(0, percentString.length() - 1)) / 100;
  }

  // Given amount and percent, return the value after the calculation of SIP and percentage of
  // change
  public static Double getSipAddedAmount(Double amount, String percent) {
    return amount + (amount * getPercentFromString(percent));
  }

  // Given portfolio, return the segment values as a string (Equity Debt Gold)
  public static String createStringFromPortfolio(Portfolio portfolio) {
    return portfolio.getEquity() + " " + portfolio.getDebt() + " " + portfolio.getGold();
  }
}
