package org.example.banking.model;

public class Transaction {

    private String date;
    private String operation;
    private double amount;
    private double balance;

    public Transaction(String date, String operation, double amount, double balance) {
        this.date = date;
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
    }

    // Getters for each field
    public String getDate() {
        return date;
    }

    public String getOperation() {
        return operation;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }
}
