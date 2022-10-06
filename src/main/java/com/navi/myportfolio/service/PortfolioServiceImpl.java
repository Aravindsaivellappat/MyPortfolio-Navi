package com.navi.myportfolio.service;

import com.navi.myportfolio.adapter.PortfolioAdapter;
import com.navi.myportfolio.adapter.SIPAdapter;
import com.navi.myportfolio.dto.ChangeHistory;
import com.navi.myportfolio.dto.Portfolio;
import com.navi.myportfolio.dto.SIP;
import com.navi.myportfolio.util.Months;
import com.navi.myportfolio.util.Utilityfunctions;
import java.util.ArrayList;
import java.util.List;

public class PortfolioServiceImpl implements PortfolioService {

  @Override
  public List<ChangeHistory> allocate(String[] command) {
    //    if(command.length != 4) {}
    List<ChangeHistory> histories = new ArrayList<>();

    Portfolio portfolio = PortfolioAdapter.stringArrayToPortfolio(command);
    ChangeHistory history = new ChangeHistory();
    history.setPortfolio(portfolio);
    history.setMonth(Months.INITIAL);
    histories.add(history);
    return histories;
  }

  @Override
  public SIP sip(String[] command) {
    //    if(command.length != 4) {}
    return SIPAdapter.commandToSIP(command);
  }

  @Override
  public List<ChangeHistory> change(List<ChangeHistory> history, SIP sip, String[] command) {
    if (sip != null && history.size() > 0) { // TODO: Test if null

      Portfolio portfolio = history.get(history.size() - 1).getPortfolio();

      double curEquity = (double) portfolio.getEquity();
      double curDebt = (double) portfolio.getDebt();
      double curGold = (double) portfolio.getGold();

      if (command[4].equalsIgnoreCase(Months.JANUARY.name())) {
        curEquity += (curEquity * Utilityfunctions.getPercentFromString(command[1]));
        curDebt += (curDebt * Utilityfunctions.getPercentFromString(command[2]));
        curGold += (curGold * Utilityfunctions.getPercentFromString(command[3]));
      } else {
        curEquity = Utilityfunctions.getSipAddedAmount(sip.getEquity() + curEquity, command[1]);
        curDebt = Utilityfunctions.getSipAddedAmount(sip.getDebt() + curDebt, command[2]);
        curGold = Utilityfunctions.getSipAddedAmount(sip.getGold() + curGold, command[3]);
      }

      double total = curEquity + curDebt + curGold;

      Portfolio newPortfolio =
          new Portfolio(
              (int) Math.floor(curEquity),
              (int) Math.floor(curDebt),
              (int) Math.floor(curGold),
              (int) Math.floor(total),
              portfolio.getEquityAllocation(),
              portfolio.getDebtAllocation(),
              portfolio.getGoldAllocation(),
              false);

      if (command[4].equalsIgnoreCase(Months.JUNE.name())
          || command[4].equalsIgnoreCase(Months.DECEMBER.name())) {
        double rebalancedEquity = (total * newPortfolio.getEquityAllocation()) / 100;
        double rebalancedDebt = (total * newPortfolio.getDebtAllocation()) / 100;
        double rebalancedGold = (total * newPortfolio.getGoldAllocation()) / 100;

        total = rebalancedEquity + rebalancedDebt + rebalancedGold;
        newPortfolio.setEquity((int) Math.floor(rebalancedEquity));
        newPortfolio.setDebt((int) Math.floor(rebalancedDebt));
        newPortfolio.setGold((int) Math.floor(rebalancedGold));
        newPortfolio.setTotal((int) Math.floor(total));
        newPortfolio.setRebalaced(true);
      }

      ChangeHistory newChange = new ChangeHistory(Months.valueOf(command[4]), newPortfolio);
      history.add(newChange);
    }

    return history;
  }

  @Override
  public String balance(List<ChangeHistory> history, String[] command) {
    StringBuilder value = new StringBuilder();
    if (history.size() > 0) {
      Months requestMonth = Months.valueOf(command[1]);
      for (ChangeHistory change : history) {
        if (change.getMonth().equals(requestMonth)) {
          value.append(change.getPortfolio().getEquity()).append(" ");
          value.append(change.getPortfolio().getDebt()).append(" ");
          value.append(change.getPortfolio().getGold());
          return value.toString();
        }
      }
    }
    return "";
  }

  @Override
  public String rebalance(List<ChangeHistory> history, String[] command) {
    StringBuilder value = new StringBuilder();
    if (history.size() >= 7) {
      ChangeHistory change = history.get(history.size() - 1);
      value.append(change.getPortfolio().getEquity()).append(" ");
      value.append(change.getPortfolio().getDebt()).append(" ");
      value.append(change.getPortfolio().getGold());
    } else {
      value.append("CANNOT_REBALANCE");
    }
    return value.toString();
  }
}
