package com.navi.myportfolio.service;

import com.navi.myportfolio.adapter.PortfolioAdapter;
import com.navi.myportfolio.adapter.SIPAdapter;
import com.navi.myportfolio.entity.ChangeHistory;
import com.navi.myportfolio.entity.Portfolio;
import com.navi.myportfolio.entity.SIP;
import com.navi.myportfolio.util.Months;
import com.navi.myportfolio.util.UtilityFunctions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PortfolioServiceImpl implements PortfolioService {

  @Override
  public List<ChangeHistory> allocate(String[] command) {
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
    return SIPAdapter.commandToSIP(command);
  }

  @Override
  public List<ChangeHistory> change(List<ChangeHistory> history, SIP sip, String[] command) {
    if (sip != null && history.size() > 0) {

      Portfolio portfolio = history.get(history.size() - 1).getPortfolio();

      double curEquity = (double) portfolio.getEquity();
      double curDebt = (double) portfolio.getDebt();
      double curGold = (double) portfolio.getGold();

      if (command[4].equalsIgnoreCase(Months.JANUARY.name())) {
        curEquity += (curEquity * UtilityFunctions.getPercentFromString(command[1]));
        curDebt += (curDebt * UtilityFunctions.getPercentFromString(command[2]));
        curGold += (curGold * UtilityFunctions.getPercentFromString(command[3]));
      } else {
        curEquity = UtilityFunctions.getSipAddedAmount(sip.getEquity() + curEquity, command[1]);
        curDebt = UtilityFunctions.getSipAddedAmount(sip.getDebt() + curDebt, command[2]);
        curGold = UtilityFunctions.getSipAddedAmount(sip.getGold() + curGold, command[3]);
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
              portfolio.getRebalaced());

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
    String value;
    if (history.size() > 0) {
      Months requestMonth = Months.valueOf(command[1]);
      for (ChangeHistory change : history) {
        if (change.getMonth().equals(requestMonth)) {
          return UtilityFunctions.createStringFromPortfolio(change.getPortfolio());
        }
      }
    }
    return "";
  }

  @Override
  public String rebalance(List<ChangeHistory> history) {
    HashMap<Months, Portfolio> map = new HashMap<>();
    String value;

    for (ChangeHistory changeHistory : history) {
      map.put(changeHistory.getMonth(), changeHistory.getPortfolio());
    }

    if (!map.containsKey(Months.JUNE) && !map.containsKey(Months.DECEMBER)) {
      value = "CANNOT_REBALANCE";
    } else if (map.containsKey(Months.JUNE) && !map.containsKey(Months.DECEMBER)) {
      value = UtilityFunctions.createStringFromPortfolio(map.get(Months.JUNE));
    } else {
      value = UtilityFunctions.createStringFromPortfolio(map.get(Months.DECEMBER));
    }
    return value;
  }
}
