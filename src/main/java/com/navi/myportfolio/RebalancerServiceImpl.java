package com.navi.myportfolio;

import java.io.FileInputStream;
import java.util.Scanner;

public class RebalancerServiceImpl implements RebalancerService{

  @Override
  public void parseCommands(FileInputStream fileInputStream) {
    Scanner scanner = new Scanner(fileInputStream);

    while (scanner.hasNextLine()) {
      String command = scanner.nextLine();
      System.out.println(command);
    }

    scanner.close();
  }
}
