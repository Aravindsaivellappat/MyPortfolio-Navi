package com.navi.myportfolio;

import com.navi.myportfolio.service.RebalancerService;
import com.navi.myportfolio.service.RebalancerServiceImpl;
import java.io.FileInputStream;
import java.io.IOException;

/** MyMoney Application Starter - by Aravind Sai */
public class MyPortfolioApplication {

    private final static RebalancerService rebalancerService = new RebalancerServiceImpl();

    public static void main(String[] args) {
        if (args[0] == null || args[0].trim().isEmpty()) {
            System.out.println("You need to pass file path as parameter");
        } else {

            try {
                FileInputStream inputFileStream = new FileInputStream(args[0]);
                rebalancerService.parseCommands(inputFileStream);

                // TODO: Reorganise to folders
                // TODO: Change Service and Impl names
                // TODO: Add global error handling
                // TODO: Check if enum to constants for use in switch
                // TODO: Add global logger

            } catch (IOException e) {
                System.out.println("Error reading file. Please try again");
            }
        }
    }
}
