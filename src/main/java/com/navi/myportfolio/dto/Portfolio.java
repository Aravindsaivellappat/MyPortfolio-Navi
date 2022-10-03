package com.navi.myportfolio.dto;

import java.io.Serializable;

public class Portfolio implements Serializable {

  private Integer equity = 0;
  private Integer debt = 0;
  private Integer gold = 0;
  private Integer total = 0;
  private Integer equityAllocation = 0;
  private Integer debtAllocation = 0;
  private Integer goldAllocation = 0;
  private Integer totalAllocation = 0;

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

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Integer getEquityAllocation() {
    return equityAllocation;
  }

  public void setEquityAllocation(Integer equityAllocation) {
    this.equityAllocation = equityAllocation;
  }

  public Integer getDebtAllocation() {
    return debtAllocation;
  }

  public void setDebtAllocation(Integer debtAllocation) {
    this.debtAllocation = debtAllocation;
  }

  public Integer getGoldAllocation() {
    return goldAllocation;
  }

  public void setGoldAllocation(Integer goldAllocation) {
    this.goldAllocation = goldAllocation;
  }

  public Integer getTotalAllocation() {
    return totalAllocation;
  }

  public void setTotalAllocation(Integer totalAllocation) {
    this.totalAllocation = totalAllocation;
  }

  @Override
  public String toString() {
    return "Portfolio{"
        + "equity="
        + equity
        + ", debt="
        + debt
        + ", gold="
        + gold
        + ", total="
        + total
        + ", equityAllocation="
        + equityAllocation
        + ", debtAllocation="
        + debtAllocation
        + ", goldAllocation="
        + goldAllocation
        + ", totalAllocation="
        + totalAllocation
        + '}';
  }
}