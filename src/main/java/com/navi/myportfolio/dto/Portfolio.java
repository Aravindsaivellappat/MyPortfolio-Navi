package com.navi.myportfolio.dto;

import java.io.Serializable;

public class Portfolio implements Serializable {

  private Integer equity = 0;
  private Integer debt = 0;
  private Integer gold = 0;
  private Integer total = 0;
  private Double equityAllocation = 0.0;
  private Double debtAllocation = 0.0;
  private Double goldAllocation = 0.0;
  private Double totalAllocation = 0.0;
  private Boolean isRebalaced = false;

  public Portfolio() {}

  public Portfolio(Integer equity, Integer debt, Integer gold, Integer total, Double equityAllocation,
      Double debtAllocation, Double goldAllocation, Boolean isRebalaced) {
    this.equity = equity;
    this.debt = debt;
    this.gold = gold;
    this.total = total;
    this.equityAllocation = equityAllocation;
    this.debtAllocation = debtAllocation;
    this.goldAllocation = goldAllocation;
    this.isRebalaced = isRebalaced;
  }

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

  public Double getEquityAllocation() {
    return equityAllocation;
  }

  public void setEquityAllocation(Double equityAllocation) {
    this.equityAllocation = equityAllocation;
  }

  public Double getDebtAllocation() {
    return debtAllocation;
  }

  public void setDebtAllocation(Double debtAllocation) {
    this.debtAllocation = debtAllocation;
  }

  public Double getGoldAllocation() {
    return goldAllocation;
  }

  public void setGoldAllocation(Double goldAllocation) {
    this.goldAllocation = goldAllocation;
  }

  public Double getTotalAllocation() {
    return totalAllocation;
  }

  public void setTotalAllocation(Double totalAllocation) {
    this.totalAllocation = totalAllocation;
  }

  public Boolean getRebalaced() {
    return isRebalaced;
  }

  public void setRebalaced(Boolean rebalaced) {
    isRebalaced = rebalaced;
  }

  @Override
  public String toString() {
    return "Portfolio{" + "equity=" + equity + ", debt=" + debt + ", gold=" + gold + ", total=" + total +
           ", equityAllocation=" + equityAllocation + ", debtAllocation=" + debtAllocation + ", goldAllocation=" +
           goldAllocation + ", totalAllocation=" + totalAllocation + ", isRebalaced=" + isRebalaced + '}';
  }
}
