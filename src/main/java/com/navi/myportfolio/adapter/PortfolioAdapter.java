package com.navi.myportfolio.adapter;

import com.navi.myportfolio.dto.Portfolio;

public class PortfolioAdapter {

  public static Portfolio commandToPortfolio(String[] command) {

    Portfolio portfolio = new Portfolio();

    double equityAmount = Double.parseDouble(command[1]);
    double debtAmount = Double.parseDouble(command[2]);
    double goldAmount = Double.parseDouble(command[3]);
    double totalAmount = equityAmount + debtAmount + goldAmount;
    double equityPercent = (equityAmount/totalAmount) * 100;
    double debtPercent = (debtAmount/totalAmount)*100;
    double goldPercent = (goldAmount/totalAmount)*100;

    portfolio.setEquity((int) Math.floor(equityAmount));
    portfolio.setDebt((int) Math.floor(debtAmount));
    portfolio.setGold((int) Math.floor(goldAmount));
    portfolio.setTotal((int) Math.floor(totalAmount));
    portfolio.setEquityAllocation((int) Math.floor(equityPercent));
    portfolio.setDebtAllocation((int) Math.floor(debtPercent));
    portfolio.setGoldAllocation((int) Math.floor(goldPercent));
    portfolio.setTotalAllocation((int) Math.floor(equityPercent+goldPercent+debtPercent));

    return portfolio;
  }

}
