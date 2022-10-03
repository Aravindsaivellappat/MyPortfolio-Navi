package com.navi.myportfolio.util;

public enum OperationCommands {
  ALLOCATE("ALLOCATE"),
  SIP("SIP"),
  CHANGE("CHANGE"),
  BALANCE("BALANCE"),
  REBALANCE("REBALANCE");

  private final String value;
  OperationCommands(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
