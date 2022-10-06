package com.navi.myportfolio.service;

import java.io.FileInputStream;

public interface RebalancerService {

  /**
   * Parses input file-stream and executes each line of commands
   *
   * @param fileInputStream The lines of commands to be executed
   */
  void parseCommands(FileInputStream fileInputStream);
}
