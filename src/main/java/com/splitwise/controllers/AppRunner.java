package com.splitwise.controllers;

public class AppRunner {
    public static void main(String[] args) {

        AppManager appManager = new AppManager();
        String [] commands = {
                "SHOW",
                "SHOW u1",
                "EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL",
                "SHOW u4",
                "SHOW u1",
                "EXPENSE u1 1250 2 u2 u3 EXACT 370 880",
                "SHOW",
                "EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20",
                "SHOW u1",
                "SHOW"
        };

        for(String command : commands){
            String[] commandParams = command.split(" ", -1);

        }
    }
}
