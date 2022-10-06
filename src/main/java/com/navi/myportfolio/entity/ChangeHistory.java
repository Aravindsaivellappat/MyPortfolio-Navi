package com.navi.myportfolio.entity;

import com.navi.myportfolio.util.Months;
import java.io.Serializable;

public class ChangeHistory implements Serializable {

  private Months month;
  private Portfolio portfolio;

  public ChangeHistory() {
  }

  public ChangeHistory(Months month, Portfolio portfolio) {
    this.month = month;
    this.portfolio = portfolio;
  }

  public Months getMonth() {
    return month;
  }

  public void setMonth(Months month) {
    this.month = month;
  }

  public Portfolio getPortfolio() {
    return portfolio;
  }

  public void setPortfolio(Portfolio portfolio) {
    this.portfolio = portfolio;
  }

  @Override
  public String toString() {
    return "Passbook{" + "month=" + month + ", portfolio=" + portfolio + '}';
  }
}
