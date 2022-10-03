package com.navi.myportfolio.service;

import com.navi.myportfolio.dto.Portfolio;
import com.navi.myportfolio.dto.SIP;

public interface PortfolioService {

  Portfolio allocate(String[] command);
  SIP sip(Portfolio portfolio, String[] command);
  void change(Portfolio portfolio, String[] command);
  void balance(Portfolio portfolio, String[] command);
  void rebalance(Portfolio portfolio, String[] command);

}
