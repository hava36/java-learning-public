import exceptions.AccountException;
import exceptions.NotEnoughMoneyException;
import exceptions.SecurityBlockedException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing of operations between accounts")
public class TestBank {

    private Bank bank;

    public TestBank() throws Exception {
        setUp();
    }

    private void setUp() throws Exception {

        Account account1 = new Account(100000, false, "1");
        Account account2 = new Account(40000, false, "2");
        Account account3 = new Account(30000, false, "3");
        Account account4 = new Account(20000, false, "4");
        Account account5 = new Account(20000, true, "5");

        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
        accountList.add(account4);
        accountList.add(account5);

        bank = new Bank(accountList);

    }

    @Test
    @DisplayName("Available transfer between accounts (from account number '1' to account number '2')")
    public void ValidTransferBetweenAccountsTest() throws AccountException, InterruptedException {

        List<Thread> threads = getThreads();

        for (Thread thread:threads
             ) {
            thread.start();
        }

        Thread.sleep(1000);

        assertEquals(85000, bank.getBalance("1"));
        assertEquals(63000, bank.getBalance("2"));
        assertEquals(20000, bank.getBalance("3"));
        assertEquals(22000, bank.getBalance("4"));

    }

    @Test
    @DisplayName("Fraud checking")
    public void FraudChecking() throws AccountException, InterruptedException, SecurityBlockedException, NotEnoughMoneyException {
        bank.transfer("1", "2", 60000);
        assertEquals(true, bank.isBlockedBySecurityService("1"));
        assertEquals(true, bank.isBlockedBySecurityService("2"));
    }

    @Test
    @DisplayName("Not enough money transfer between accounts (from account number '1' to account number '2')")
    public void NotEnoughMoneyExceptionTransferTest() {
        Throwable thrown = assertThrows(NotEnoughMoneyException.class, () -> {
            bank.transfer("1", "2", 120000);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Account existence exception transfer between accounts (from account number '1' to account number '2')")
    public void AccountExistenceExceptionTransferTest() {
        Throwable thrown = assertThrows(AccountException.class, () -> {
            bank.transfer("22", "2", 120000);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Blocked by security service exception transfer between accounts (from account number '1' to account number '2')")
    public void BlockedBySecurityServiceExceptionTransferTest() {
        Throwable thrown = assertThrows(SecurityBlockedException.class, () -> {
            bank.transfer("5", "2", 120000);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Check blocked accounts")
    public void AccountIsBlockedTest() throws AccountException {
        assertEquals(false, bank.isBlockedBySecurityService("2"));
        assertEquals(true, bank.isBlockedBySecurityService("5"));
    }

    @Test
    @DisplayName("Set account block test")
    public void SetAccountBlockTest() throws AccountException {
        bank.setBlockedBySecurityService("2");
        assertEquals(true, bank.isBlockedBySecurityService("2"));
    }

    private List<Thread> getThreads() {

        Thread thread1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                bank.transfer("1", "2", 20000);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                bank.transfer("3", "4", 10000);
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                bank.transfer("4", "1", 5000);
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                bank.transfer("4", "2", 3000);
            }
        });

        List<Thread> threads = new ArrayList<>();
        threads.add(thread1);
        threads.add(thread2);
        threads.add(thread3);
        threads.add(thread4);

        return threads;

    }
    
}
