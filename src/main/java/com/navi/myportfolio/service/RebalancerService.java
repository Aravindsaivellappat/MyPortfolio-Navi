package com.navi.myportfolio.service;

import java.io.FileInputStream;

public interface RebalancerService {

  void parseCommands(FileInputStream fileInputStream);

}
