import exceptions.AccountException;
import exceptions.NotEnoughMoneyException;
import exceptions.SecurityBlockedException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws AccountException {

        Account account1 = new Account(100000, false, "1");
        Account account2 = new Account(40000, false, "2");
        Account account3 = new Account(30000, false, "3");
        Account account4 = new Account(20000, false, "4");

        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
        accountList.add(account4);

        Bank bank = new Bank(accountList);
        try {
            bank.transfer("1", "2", 20000);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        } catch (AccountException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SecurityBlockedException e) {
            e.printStackTrace();
        }
        
    }

}
