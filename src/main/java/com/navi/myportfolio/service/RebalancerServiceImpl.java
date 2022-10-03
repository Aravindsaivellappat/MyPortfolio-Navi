package com.navi.myportfolio.service;

import com.navi.myportfolio.dto.SIP;
import com.navi.myportfolio.util.OperationCommands;
import com.navi.myportfolio.dto.Portfolio;
import java.io.FileInputStream;
import java.util.Scanner;

public class RebalancerServiceImpl implements RebalancerService {

  private final static PortfolioService portfolioService = new PortfolioServiceImpl();

  @Override
  public void parseCommands(FileInputStream fileInputStream) {
    Scanner scanner = new Scanner(fileInputStream);

    int lineCount = 1;
    while (scanner.hasNextLine()) {
      String command = scanner.nextLine();

      String[] arguments = command.split(" ");
      if (arguments.length == 0) System.out.println("Incorrect command format");

      Portfolio portfolio = new Portfolio();
      SIP sip = new SIP();

      //TODO: Change to switch
      if(arguments[0].equalsIgnoreCase(OperationCommands.ALLOCATE.getValue())) {
        portfolio = portfolioService.allocate(arguments);
        System.out.println(portfolio.toString());
      } else if(arguments[0].equalsIgnoreCase(OperationCommands.SIP.getValue())) {
        sip = portfolioService.sip(portfolio, arguments);
        System.out.println(sip.toString());
      } else if(arguments[0].equalsIgnoreCase(OperationCommands.BALANCE.getValue())) {
        portfolioService.balance(portfolio, arguments);
      } else if(arguments[0].equalsIgnoreCase(OperationCommands.CHANGE.getValue())) {
        portfolioService.change(portfolio, arguments);
      } else if(arguments[0].equalsIgnoreCase(OperationCommands.REBALANCE.getValue())) {
        portfolioService.rebalance(portfolio, arguments);
      } else {
        System.out.println("Command unknown at line: " + lineCount);
      }
    }

    scanner.close();
  }
}
