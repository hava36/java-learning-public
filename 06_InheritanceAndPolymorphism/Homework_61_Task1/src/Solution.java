import acoounts.BankAccount;
import acoounts.DepositAccount;
import acoounts.PaymentAccount;

public class Solution {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();
        bankAccount.depositAmount(100);
        bankAccount.withdrawAmount(10);
        bankAccount.withdrawAmount(100);
        System.out.println(bankAccount.getAmount());

        BankAccount depositAccount = new DepositAccount();
        depositAccount.depositAmount(100);
        depositAccount.withdrawAmount(10);
        depositAccount.withdrawAmount(100);

        BankAccount paymentAccount = new PaymentAccount();
        paymentAccount.depositAmount(100);
        paymentAccount.withdrawAmount(10);
        paymentAccount.send(depositAccount, 89);

        System.out.println(String.format("Bank account %.2f", bankAccount.getAmount()));
        System.out.println(String.format("Deposit account %.2f", depositAccount.getAmount()));
        System.out.println(String.format("Payment account %.2f", paymentAccount.getAmount()));

    }

}
