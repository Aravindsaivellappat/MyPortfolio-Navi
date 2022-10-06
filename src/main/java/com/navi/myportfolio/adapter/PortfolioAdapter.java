package com.navi.myportfolio.adapter;

import com.navi.myportfolio.entity.Portfolio;

public class PortfolioAdapter {

  // Given string array of command, allocate the initial portfolio
  public static Portfolio stringArrayToPortfolio(String[] command) {

    Portfolio portfolio = new Portfolio();

    double equityAmount = Double.parseDouble(command[1]);
    double debtAmount = Double.parseDouble(command[2]);
    double goldAmount = Double.parseDouble(command[3]);
    double totalAmount = equityAmount + debtAmount + goldAmount;
    double equityPercent = (equityAmount / totalAmount) * 100;
    double debtPercent = (debtAmount / totalAmount) * 100;
    double goldPercent = (goldAmount / totalAmount) * 100;

    portfolio.setEquity((int) Math.floor(equityAmount));
    portfolio.setDebt((int) Math.floor(debtAmount));
    portfolio.setGold((int) Math.floor(goldAmount));
    portfolio.setTotal((int) Math.floor(totalAmount));
    portfolio.setEquityAllocation(Math.floor(equityPercent));
    portfolio.setDebtAllocation(Math.floor(debtPercent));
    portfolio.setGoldAllocation(Math.floor(goldPercent));
    portfolio.setTotalAllocation(Math.floor(equityPercent + goldPercent + debtPercent));

    return portfolio;
  }
}
