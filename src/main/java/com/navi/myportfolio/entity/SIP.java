package com.navi.myportfolio.entity;

import java.io.Serializable;

public class SIP implements Serializable {

  private Integer equity = 0;
  private Integer debt = 0;
  private Integer gold = 0;

  public Integer getEquity() {
    return equity;
  }

  public void setEquity(Integer equity) {
    this.equity = equity;
  }

  public Integer getDebt() {
    return debt;
  }

  public void setDebt(Integer debt) {
    this.debt = debt;
  }

  public Integer getGold() {
    return gold;
  }

  public void setGold(Integer gold) {
    this.gold = gold;
  }

  @Override
  public String toString() {
    return "SIP{" + "equity=" + equity + ", debt=" + debt + ", gold=" + gold + '}';
  }
}
