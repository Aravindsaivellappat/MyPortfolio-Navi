package com.navi.myportfolio;

import java.io.FileInputStream;

public interface RebalancerService {

  void parseCommands(FileInputStream fileInputStream);

}
