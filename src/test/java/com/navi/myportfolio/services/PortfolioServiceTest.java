package com.navi.myportfolio.services;

import static junit.framework.Assert.assertEquals;

import com.navi.myportfolio.entity.ChangeHistory;
import com.navi.myportfolio.entity.SIP;
import com.navi.myportfolio.service.PortfolioServiceImpl;
import com.navi.myportfolio.util.Months;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PortfolioServiceTest {

  PortfolioServiceImpl portfolioService = new PortfolioServiceImpl();

  @Test
  public void testAllocate_returnsListOfChangesWithAllocation() {
    String[] commands = new String[] {"ALLOCATE", "8000", "6000", "3500"};

    List<ChangeHistory> changes = portfolioService.allocate(commands);
    assertEquals(changes.size(), 1);
    assertEquals(changes.get(0).getMonth().name(), Months.INITIAL.name());
    assertEquals(changes.get(0).getPortfolio().getEquity().intValue(), 8000);
    assertEquals(changes.get(0).getPortfolio().getDebt().intValue(), 6000);
    assertEquals(changes.get(0).getPortfolio().getGold().intValue(), 3500);
    assertEquals(changes.get(0).getPortfolio().getEquityAllocation(), 45.00);
    assertEquals(changes.get(0).getPortfolio().getDebtAllocation(), 34.00);
    assertEquals(changes.get(0).getPortfolio().getGoldAllocation(), 20.00);
  }

  @Test
  public void testSIP_returnsValidSIPObject() {
    String[] commands = new String[] {"SIP", "3000", "2000", "1000"};

    SIP sip = portfolioService.sip(commands);
    assertEquals(sip.getEquity().intValue(), 3000);
    assertEquals(sip.getDebt().intValue(), 2000);
    assertEquals(sip.getGold().intValue(), 1000);
  }

  @Test
  public void testChangeCommand_returnsValidChangeAddedToPortfolioChanges() {
    String[] command1 = new String[] {"ALLOCATE", "8000", "6000", "3500"};
    String[] command2 = new String[] {"SIP", "3000", "2000", "1000"};
    String[] command3 = new String[] {"CHANGE", "11.00%", "9.00%", "4.00%", "JANUARY"};
    String[] command4 = new String[] {"CHANGE", "-6.00%", "21.00%", "-3.00%", "FEBRUARY"};

    List<ChangeHistory> changes = portfolioService.allocate(command1);
    SIP sip = portfolioService.sip(command2);

    changes = portfolioService.change(changes, sip, command3);
    assertEquals(changes.get(changes.size() - 1).getMonth().name(), Months.JANUARY.name());

    assertEquals(changes.get(changes.size() - 1).getPortfolio().getEquity().intValue(), 8880);
    assertEquals(changes.get(changes.size() - 1).getPortfolio().getDebt().intValue(), 6540);
    assertEquals(changes.get(changes.size() - 1).getPortfolio().getGold().intValue(), 3640);

    changes = portfolioService.change(changes, sip, command4);

    assertEquals(changes.get(changes.size() - 1).getPortfolio().getEquity().intValue(), 11167);
    assertEquals(changes.get(changes.size() - 1).getPortfolio().getDebt().intValue(), 10333);
    assertEquals(changes.get(changes.size() - 1).getPortfolio().getGold().intValue(), 4500);
  }

  @Test
  public void testBalanceCommand_returnsBalanceStringOfSpecifiedMonth() {
    String[] command1 = new String[] {"ALLOCATE", "8000", "6000", "3500"};
    String[] command2 = new String[] {"SIP", "3000", "2000", "1000"};
    String[] command3 = new String[] {"CHANGE", "11.00%", "9.00%", "4.00%", "JANUARY"};
    String[] command4 = new String[] {"CHANGE", "-6.00%", "21.00%", "-3.00%", "FEBRUARY"};
    String[] command5 = new String[] {"BALANCE", "FEBRUARY"};

    List<ChangeHistory> changes = portfolioService.allocate(command1);
    SIP sip = portfolioService.sip(command2);

    changes = portfolioService.change(changes, sip, command3);
    changes = portfolioService.change(changes, sip, command4);

    String balance = portfolioService.balance(changes, command5);

    assertEquals(balance, "11167 10333 4500");

  }

  @Test
  public void testRebalance_returnsValidRebalancedAmount() {
    String[] command1 = new String[] {"ALLOCATE", "8000", "6000", "3500"};
    String[] command2 = new String[] {"SIP", "3000", "2000", "1000"};
    String[] command3 = new String[] {"CHANGE", "11.00%", "9.00%", "4.00%", "JANUARY"};
    String[] command4 = new String[] {"CHANGE", "-6.00%", "21.00%", "-3.00%", "FEBRUARY"};
    String[] command5 = new String[] {"CHANGE", "12.50%", "18.00%", "12.50%", "MARCH"};
    String[] command6 = new String[] {"CHANGE", "23.00%", "-3.00%", "7.00%", "APRIL"};
    String[] command7 = new String[] {"CHANGE", "10.00%", "10.00%", "10.00%", "MAY"};
    String[] command8 = new String[] {"CHANGE", "10.00%", "10.00%", "10.00%", "JUNE"};

    List<ChangeHistory> changes = portfolioService.allocate(command1);
    SIP sip = portfolioService.sip(command2);

    changes = portfolioService.change(changes, sip, command3);
    changes = portfolioService.change(changes, sip, command4);
    changes = portfolioService.change(changes, sip, command5);
    changes = portfolioService.change(changes, sip, command6);
    changes = portfolioService.change(changes, sip, command7);
    changes = portfolioService.change(changes, sip, command8);

    String rebalance = portfolioService.rebalance(changes);

    assertEquals(rebalance, "31848 24063 14154");
  }

  @Test
  public void testRebalance_returnsCannotRebalance() {
    String[] command1 = new String[] {"ALLOCATE", "8000", "6000", "3500"};
    String[] command2 = new String[] {"SIP", "3000", "2000", "1000"};
    String[] command3 = new String[] {"CHANGE", "11.00%", "9.00%", "4.00%", "JANUARY"};
    String[] command4 = new String[] {"CHANGE", "-6.00%", "21.00%", "-3.00%", "FEBRUARY"};
    String[] command5 = new String[] {"CHANGE", "12.50%", "18.00%", "12.50%", "MARCH"};

    List<ChangeHistory> changes = portfolioService.allocate(command1);
    SIP sip = portfolioService.sip(command2);

    changes = portfolioService.change(changes, sip, command3);
    changes = portfolioService.change(changes, sip, command4);
    changes = portfolioService.change(changes, sip, command5);

    String rebalance = portfolioService.rebalance(changes);

    assertEquals(rebalance, "CANNOT_REBALANCE");
  }
}
