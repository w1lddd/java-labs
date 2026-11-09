package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {
    @Test
    void createsAccountWithNonNegativeBalance() {
        assertEquals(0, new BankAccount(0).getBalance());
        assertEquals(100, new BankAccount(100).getBalance());
    }

    @Test
    void rejectsNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }

    @Test
    void depositsMoney() {
        BankAccount account = new BankAccount(100);
        account.deposit(50);
        assertEquals(150, account.getBalance());
    }

    @Test
    void rejectsNonPositiveDepositWithoutChangingBalance() {
        BankAccount account = new BankAccount(100);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10));
        assertEquals(100, account.getBalance());
    }

    @Test
    void withdrawsMoney() {
        BankAccount account = new BankAccount(100);
        account.withdraw(30);
        assertEquals(70, account.getBalance());
    }

    @Test
    void allowsWithdrawingEntireBalance() {
        BankAccount account = new BankAccount(100);
        account.withdraw(100);
        assertEquals(0, account.getBalance());
    }

    @Test
    void rejectsOverdraftWithoutChangingBalance() {
        BankAccount account = new BankAccount(100);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(101));
        assertEquals(100, account.getBalance());
    }

    @Test
    void rejectsNonPositiveWithdrawalWithoutChangingBalance() {
        BankAccount account = new BankAccount(100);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-10));
        assertEquals(100, account.getBalance());
    }

    @Test
    void preventsBalanceOverflow() {
        BankAccount account = new BankAccount(Integer.MAX_VALUE - 1);
        account.deposit(1);
        assertEquals(Integer.MAX_VALUE, account.getBalance());
        assertThrows(IllegalArgumentException.class, () -> account.deposit(1));
        assertEquals(Integer.MAX_VALUE, account.getBalance());
    }
}
