package com.splitwise.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    int id;
    HashMap<Integer, Double> Borrowers;
    HashMap<Integer, Double> Lenders;

    public User(int id){
        this.id = id;
        this.Borrowers = new HashMap<Integer, Double>();
        this.Lenders = new HashMap<Integer, Double>();
    }

    public int getId(){
        return this.id;
    }

    public void display(){
        if(Borrowers.size() == 0 && Lenders.size() == 0){
            System.out.println("No balances");
            return;
        }
        for(Map.Entry borrower : Borrowers.entrySet()){
            int borrowerId = (Integer) borrower.getKey();
            double amountBorrowedFromMe = (Double) borrower.getValue();
            System.out.println("User"+borrowerId + " owes " + " User"+ this.id + " : "+ amountBorrowedFromMe);
        }
        for(Map.Entry lender : Lenders.entrySet()){
            int lenderId = (Integer) lender.getKey();
            double amountLentToMe = (Double) lender.getValue();
            System.out.println("User"+this.id + " owes " + " User"+ lenderId +" : "+ amountLentToMe);
        }
    }

    public HashMap<Integer, Double> getLenders(){
        return Lenders;
    }

    public void borrowsFrom(User lender, double amount){
        int lenderId = lender.getId();
        if(Lenders.containsKey(lenderId)){
            double netLoan = Lenders.get(lenderId) + amount;
            Lenders.put(lenderId, netLoan);
        }else if(Borrowers.containsKey(lenderId)){
            double amountBorrowedFromMe = Borrowers.get(lenderId);
            if(amountBorrowedFromMe > amount){
                Borrowers.put(lenderId, amountBorrowedFromMe - amount);
            }else if(amountBorrowedFromMe < amount){
                double netLoan = amount - amountBorrowedFromMe;
                Borrowers.remove(lenderId);
                Lenders.put(lenderId, netLoan);
            }else{
                Borrowers.remove(lenderId);
            }
        }else{
            Lenders.put(lenderId, amount);
        }
    }

    public void lendsTo(User borrower, double amount){
        int borrowerId = borrower.getId();
        if(Borrowers.containsKey(borrowerId)){
            double totalAmountBorrowedFromMe = Borrowers.get(borrowerId) + amount;
            Borrowers.put(borrowerId, totalAmountBorrowedFromMe);
        }else if(Lenders.containsKey(borrowerId)){
            double amountLendToMe = Lenders.get(borrowerId);
            if(amountLendToMe > amount){
                Lenders.put(borrowerId, amountLendToMe - amount);
            }else if(amountLendToMe < amount){
                double netAmountOwedToMe = amount - amountLendToMe;
                Lenders.remove(borrowerId);
                Borrowers.put(borrowerId,netAmountOwedToMe);
            }else{
                Lenders.remove(borrowerId);
            }
        }else{
            Borrowers.put(borrowerId, amount);
        }
    }
}
