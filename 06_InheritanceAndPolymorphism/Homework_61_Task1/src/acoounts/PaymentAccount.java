package acoounts;

public class PaymentAccount extends BankAccount {

    @Override
    public void withdrawAmount(double amount) {
        amount *= 1.01;
        super.withdrawAmount(amount);
    }

}
