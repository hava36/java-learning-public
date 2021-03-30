import exceptions.AccountException;
import exceptions.NotEnoughMoneyException;
import exceptions.SecurityBlockedException;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Bank {

    private final long TRANSACTION_LIMIT = 50000l;
    private HashMap<String, Account> accounts;
    private final Random random = new Random();

    public Bank(List<Account> accountsList) {
        this.accounts = new HashMap<>();
        for (Account account : accountsList) {
            this.accounts.put(account.getAccNumber(), account);
        }
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        return accounts.size() > 3;
    }

    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount)
            throws NotEnoughMoneyException, AccountException, InterruptedException, SecurityBlockedException {

        Account accountFrom = getAccount(fromAccountNum);
        Account accountTo = getAccount(toAccountNum);
        cheackAccountBlocks(accountFrom, accountTo);

        long balanceFrom = getBalance(fromAccountNum);
        long balanceTo = getBalance(toAccountNum);
        if (balanceFrom < amount) {
            throw new NotEnoughMoneyException(String.format("Not enough money for account number %s. " +
                    "Operation was cancelled", fromAccountNum));
        }

        setBalance(fromAccountNum, balanceFrom - amount);
        setBalance(toAccountNum, balanceTo + amount);

        checkTransactionLimits(fromAccountNum, toAccountNum, amount);

    }

    public synchronized long getBalance(String accountNum) throws AccountException {
        return getAccount(accountNum).getMoney();
    }


    public void setBlockedBySecurityService(String accountNum) throws AccountException {
        Account account = getAccount(accountNum);
        account.setBlockedBySecurityService(true);
        accounts.put(accountNum, account);

    }

    public boolean isBlockedBySecurityService(String accountNum) throws AccountException {
        return getAccount(accountNum).isBlockedBySecurityService();
    }

    private Account getAccount(String accountNum) throws AccountException {
        if (accounts.containsKey(accountNum)) {
            return accounts.get(accountNum);
        }
        throw new AccountException(String.format("Account number %s does not exist ", accountNum));
    }

    private synchronized void setBalance(String accountNum, long amount) throws AccountException {
        Account account = getAccount(accountNum);
        account.setMoney(amount);
        accounts.put(accountNum, account);
    }

    private void cheackAccountBlocks(Account... accounts) throws SecurityBlockedException, AccountException {
        for (Account account : accounts
        ) {
            if (account.isBlockedBySecurityService()) {
                throw new SecurityBlockedException(String.format("Account number %s is blocked by security service ",
                        account.getAccNumber()));
            }
        }
    }

    private void checkTransactionLimits(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException, AccountException {
        if (amount > TRANSACTION_LIMIT) {
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                setBlockedBySecurityService(fromAccountNum);
                setBlockedBySecurityService(toAccountNum);
            }
            ;
        }
    }

}
