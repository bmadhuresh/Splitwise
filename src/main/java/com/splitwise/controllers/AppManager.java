package com.splitwise.controllers;

import com.splitwise.model.TransactionType;
import com.splitwise.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppManager {

    HashMap<Integer, User> users;

    private void addUser(int userId){
        this.users.put(userId, new User(userId));
    }

    public AppManager(){
        this.users = new HashMap<Integer, User>();
    }

    public void equalTransaction(
            int lenderId,
            ArrayList<Integer> borrowerIdList,
            double amount
    ){

        double amountBorrowed = amount/borrowerIdList.size();
        if(!users.containsKey((lenderId))){
            this.addUser(lenderId);
        }
        User lender = users.get(lenderId);
        for(int borrowerId : borrowerIdList){
            if(!users.containsKey((borrowerId))){
                this.addUser(borrowerId);
            }
            User borrower = users.get(borrowerId);
            lender.lendsTo(borrower,amountBorrowed );
            borrower.borrowsFrom(lender, amountBorrowed);
        }
    }

    public  void percentTransaction(
            int lenderId,
            ArrayList<Integer> borrowerIdList,
            double amount,
            ArrayList<Double> percentages
    ){
        if(!users.containsKey((lenderId))){
            this.addUser(lenderId);
        }

        User lender = users.get(lenderId);

        for(int i =0; i<percentages.size(); ++i){
            int borrowerId = borrowerIdList.get(i);
            double amountLent = (percentages.get(i)/100)*amount;

            if(!users.containsKey((borrowerId))){
                this.addUser(borrowerId);
            }
            User borrower = users.get(borrowerId);
            lender.lendsTo(borrower,amountLent );
            borrower.borrowsFrom(lender, amountLent);
        }
    }

    public  void exactTransaction(
            int lenderId,
            ArrayList<Integer> borrowerIdList,
            double amount,
            ArrayList<Double> exactAmounts
    ){
        if(!users.containsKey((lenderId))){
            this.addUser(lenderId);
        }

        User lender = users.get(lenderId);

        for(int i =0; i<exactAmounts.size(); ++i){
            int borrowerId = borrowerIdList.get(i);
            double amountLent = exactAmounts.get(i);

            if(!users.containsKey((borrowerId))){
                this.addUser(borrowerId);
            }
            User borrower = users.get(borrowerId);
            lender.lendsTo(borrower,amountLent );
            borrower.borrowsFrom(lender, amountLent);
        }
    }

    public void display(int userId){
        if(users.containsKey((userId))){
            this.users.get(userId).display();
        }else{
            System.out.println("No balances");
        }
    }

    public void displayAll(){
        boolean hasAtleastOneLender = false;
        for(Map.Entry userEntry : users.entrySet()){
            User user = (User)userEntry.getValue();
            HashMap<Integer,Double> lenderList = user.getLenders();
            if(lenderList.size() > 0){
                hasAtleastOneLender = true;
                for(Map.Entry lenderEntry : lenderList.entrySet()){
                    int lenderId = (int)lenderEntry.getKey();
                    double amountOwed = (double)lenderEntry.getValue();
                    System.out.println("User"+user.getId()+" owes "+" User"+lenderId+": "+ amountOwed);
                }
            }
        }
        if(!hasAtleastOneLender){
            System.out.println("No balances");
        }
    }
}
