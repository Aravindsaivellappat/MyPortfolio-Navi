package com.navi.myportfolio.service;

import com.navi.myportfolio.adapter.PortfolioAdapter;
import com.navi.myportfolio.adapter.SIPAdapter;
import com.navi.myportfolio.dto.Portfolio;
import com.navi.myportfolio.dto.SIP;

public class PortfolioServiceImpl implements PortfolioService {

  @Override
  public Portfolio allocate(String[] command) {
//    if(command.length != 4) {}
    return PortfolioAdapter.commandToPortfolio(command);
  }

  @Override
  public SIP sip(Portfolio portfolio, String[] command) {
//    if(command.length != 4) {}
    return SIPAdapter.commandToSIP(command);
  }

  @Override
  public void change(Portfolio portfolio, String[] command) {

  }

  @Override
  public void balance(Portfolio portfolio, String[] command) {

  }

  @Override
  public void rebalance(Portfolio portfolio, String[] command) {

  }
}
