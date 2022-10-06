package com.navi.myportfolio.service;

import com.navi.myportfolio.entity.ChangeHistory;
import com.navi.myportfolio.entity.SIP;
import com.navi.myportfolio.util.OperationCommands;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RebalancerServiceImpl implements RebalancerService {

  private static final PortfolioService portfolioService = new PortfolioServiceImpl();

  @Override
  public void parseCommands(FileInputStream fileInputStream) {
    Scanner scanner = new Scanner(fileInputStream);

    int lineCount = 1;
    List<String> result = new ArrayList<>();
    List<ChangeHistory> history = new ArrayList<>();
    SIP sip = new SIP();

    while (scanner.hasNextLine()) {
      String command = scanner.nextLine();

      String[] arguments = command.split(" ");
      if (arguments.length == 0) System.out.println("Incorrect command format");

      // TODO: Change to switch
      if (arguments[0].equalsIgnoreCase(OperationCommands.ALLOCATE.getValue())) {
        history = portfolioService.allocate(arguments);
      } else if (arguments[0].equalsIgnoreCase(OperationCommands.SIP.getValue())) {
        sip = portfolioService.sip(arguments);
      } else if (arguments[0].equalsIgnoreCase(OperationCommands.BALANCE.getValue())) {
        result.add(portfolioService.balance(history, arguments));
      } else if (arguments[0].equalsIgnoreCase(OperationCommands.CHANGE.getValue())) {
        history = portfolioService.change(history, sip, arguments);
      } else if (arguments[0].equalsIgnoreCase(OperationCommands.REBALANCE.getValue())) {
        result.add(portfolioService.rebalance(history));
      } else {
        System.out.println("Command unknown at line: " + lineCount);
      }
    }

    for(String line: result) System.out.println(line);

    scanner.close();
  }
}
