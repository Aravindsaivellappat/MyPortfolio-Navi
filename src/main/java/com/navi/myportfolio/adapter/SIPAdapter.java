package com.navi.myportfolio.adapter;

import com.navi.myportfolio.entity.SIP;

public class SIPAdapter {

  // Create SIP entity from string of values
  public static SIP commandToSIP(String[] command) {
    SIP sip = new SIP();

    Integer equitySIP = Integer.parseInt(command[1]);
    Integer debtSIP = Integer.parseInt(command[2]);
    Integer goldSIP = Integer.parseInt(command[3]);

    sip.setEquity(equitySIP);
    sip.setDebt(debtSIP);
    sip.setGold(goldSIP);

    return sip;
  }
}
