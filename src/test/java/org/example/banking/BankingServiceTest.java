package org.example.banking;

import org.example.banking.model.BankAccount;
import org.example.banking.model.Transaction;
import org.example.banking.service.BankingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class BankingServiceTest {

    private BankingService accountService;
    private BankAccount account;

    @BeforeEach
    public void setUp() {
        // This method will be called before each test to initialize the account and service
        account = new BankAccount();
        accountService = new BankingService(account);
    }

    @Test
    public void testDeposit() {
        accountService.deposit(1000);
        assertEquals("Balance should be 1000 after depositing 1000",1000.0, account.getBalance());

        accountService.deposit(500);
        assertEquals("Balance should be 1500 after depositing 500",1500.0, account.getBalance());
    }

    @Test
    public void testDepositNegativeAmount() {
        accountService.deposit(-500);
        assertEquals("Balance should remain 0 after trying to deposit a negative amount",0.0, account.getBalance());
    }

    @Test
    public void testWithdraw() {
        accountService.deposit(1000);
        accountService.withdraw(500);
        assertEquals("Balance should be 500 after withdrawing 500 from 1000",500.0, account.getBalance());

        accountService.withdraw(200);
        assertEquals("Balance should be 300 after withdrawing another 200",300.0, account.getBalance());
    }

    @Test
    public void testWithdrawMoreThanBalance() {
        accountService.deposit(500);
        accountService.withdraw(1000); // Attempt to withdraw more than available balance
        assertEquals("Balance should remain 500 after trying to withdraw more than balance",500.0, account.getBalance());
    }

    @Test
    public void testWithdrawNegativeAmount() {
        accountService.withdraw(-200); // Withdraw negative amount should do nothing
        assertEquals("Balance should remain 0 after trying to withdraw a negative amount",0.0, account.getBalance());
    }

    @Test
    public void testTransactionHistory() {
        accountService.deposit(1000);
        accountService.withdraw(200);
        accountService.deposit(300);

        // Check if 3 transactions exist
        assertEquals(3, account.getStatement().size());

        // Verify first transaction (Deposit 1000)
        Transaction firstTransaction = account.getStatement().get(0);
        assertEquals("First transaction should be a deposit", firstTransaction.getOperation(), "Deposit");
        assertEquals("First deposit should be 1000",1000.0, firstTransaction.getAmount());

        // Verify second transaction (Withdraw 200)
        Transaction secondTransaction = account.getStatement().get(1);
        assertEquals("Second transaction should be a withdrawal", secondTransaction.getOperation(), "Withdrawal");
        assertEquals("Second wothdrawl should be 200",200.0, secondTransaction.getAmount());

        // Verify third transaction (Deposit 300)
        Transaction thirdTransaction = account.getStatement().get(2);
        assertEquals("Third transaction should be a deposit", thirdTransaction.getOperation(), "Deposit");
        assertEquals("Third deposit should be 300",300.0, thirdTransaction.getAmount());
    }
}

