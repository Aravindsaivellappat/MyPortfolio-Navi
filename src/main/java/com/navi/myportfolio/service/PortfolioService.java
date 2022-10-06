package com.navi.myportfolio.service;

import com.navi.myportfolio.entity.ChangeHistory;
import com.navi.myportfolio.entity.SIP;
import java.util.List;

public interface PortfolioService {

  /**
   * Allocates the initial investment amount to the portfolio
   *
   * @param command Initial investment amount in Equity, Dept & Gold
   * @return The change in portfolio as a list of changes
   */
  List<ChangeHistory> allocate(String[] command);

  /**
   * Creates a new SIP for the portfolio
   *
   * @param command Amount in each segment for SIP (Equity, Debt, Gold)
   * @return Created SIP object
   */
  SIP sip(String[] command);

  /**
   * Create a new change in the portfolio. Rebalances the portfolio if change request is for JUNE or
   * DECEMBER.
   *
   * @param history The list of changes (history).
   * @param sip Current SIP investment amount.
   * @param command Percentage of changes in each segment and the month in which change to be
   *     reflected.
   * @return List of changes with the addition of current change.
   */
  List<ChangeHistory> change(List<ChangeHistory> history, SIP sip, String[] command);

  /**
   * Pulls the balance for the month defined in the query.
   *
   * @param history The list of changes (history).
   * @param command The balance of the to be fetched.
   * @return Balance string in format (Equity Debt Gold)
   */
  String balance(List<ChangeHistory> history, String[] command);

  /**
   * Fetch the last rebalanced allocation.
   *
   * @param history The list of changes (history)
   * @return Balance string after the last rebalance or CANNOT_REBALANCE if not enough changes.
   */
  String rebalance(List<ChangeHistory> history);
}
