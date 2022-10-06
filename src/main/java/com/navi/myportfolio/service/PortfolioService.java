package com.navi.myportfolio.service;

import com.navi.myportfolio.dto.ChangeHistory;
import com.navi.myportfolio.dto.Portfolio;
import com.navi.myportfolio.dto.SIP;
import java.util.List;

public interface PortfolioService {

  List<ChangeHistory> allocate(String[] command);
  SIP sip(String[] command);
  List<ChangeHistory> change(List<ChangeHistory> history, SIP sip, String[] command);
  String balance(List<ChangeHistory> history, String[] command);
  String rebalance(List<ChangeHistory> history, String[] command);

}
