package org.example.banking.model;

import java.util.ArrayList;

public class BankAccount {


    private double balance;
    private ArrayList<Transaction> statement;

    public BankAccount() {
        this.balance = 0;
        this.statement = new ArrayList<>();
    }

    // Getter and Setter for Balance
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Getter and Setter for Statement
    public ArrayList<Transaction> getStatement() {
        return statement;
    }

    public void setStatement(ArrayList<Transaction> statement) {
        this.statement = statement;
    }
}
