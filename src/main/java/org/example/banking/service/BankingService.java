package org.example.banking.service;

import org.example.banking.model.BankAccount;
import org.example.banking.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BankingService {


    private BankAccount bankAccount;

    public BankingService(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive");
            return;
        }
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        addTransaction("Deposit", amount);
    }

    // Withdrawal method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return;
        }
        if (amount > bankAccount.getBalance()) {
            System.out.println("Insufficient balance");
            return;
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        addTransaction("Withdrawal", amount);
    }

    // Add a transaction to the bank account statement
    private void addTransaction(String operation, double amount) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Transaction transaction = new Transaction(date, operation, amount, bankAccount.getBalance());
        bankAccount.getStatement().add(transaction);
    }

    // Print the statement
    public void printStatement() {
        System.out.printf("%-20s %-15s %-10s %-10s%n", "Date", "Operation", "Amount", "Balance");
        System.out.println("---------------------------------------------------------");
        for (Transaction transaction : bankAccount.getStatement()) {
            System.out.printf("%-20s %-15s %-10.2f %-10.2f%n",
                    transaction.getDate(),
                    transaction.getOperation(),
                    transaction.getAmount(),
                    transaction.getBalance());
        }
    }
}




