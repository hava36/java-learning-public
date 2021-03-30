package acoounts;

import managers.ResourceManager;

public class  BankAccount {

    private double amount;

    public void depositAmount(double amount) {
        this.amount += amount;
        System.out.println(ResourceManager.getMessage("complete_deposit_operation"));
    }

    public void withdrawAmount(double amount) {
        if (withdrawISAvailable(amount)) {
            this.amount -= amount;
            System.out.println(ResourceManager.getMessage("complete_withdraw_operation"));
        } else {
            System.out.println(ResourceManager.getMessage("negative_balance"));
        }
    }

    public boolean withdrawISAvailable(double amount) {
        return amount <= this.amount;
    }

    public boolean send(BankAccount receiver, double amount) {
        if (withdrawISAvailable(amount)) {
            withdrawAmount(amount);
            receiver.depositAmount(amount);
            return true;
        }
        return false;
    }

    public double getAmount() {
        return amount;
    }

}
