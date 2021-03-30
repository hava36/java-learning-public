package clients;

import managers.ConsoleHelper;
import managers.ResourceManager;

public abstract class Client {

    private double amount;

    public void depositAmount(double amount) {
        double commission = amount * getDepositCommissionPercent(amount)/100;
        this.amount += amount - commission;
        ConsoleHelper.writeMessage(String.format(ResourceManager.getMessage("deposit_completed"),
                amount, commission, getAmount()));
    }

    public void withdrawAmount(double amount) {
        double commission = amount * getWithdrawCommissionPercent(amount)/100;
        amount += commission;

        if (this.amount >= amount) {
            this.amount -= amount;
            ConsoleHelper.writeMessage(String.format(ResourceManager.getMessage("withdraw_completed"),
                    amount, commission, getAmount()));
        } else {
            ConsoleHelper.writeMessage(String.format(ResourceManager.getMessage("not_enough_money"), getAmount(), amount));
        }

    }

    public double getAmount() {
        return amount;
    }

    protected abstract float getDepositCommissionPercent(double amount);
    protected abstract float getWithdrawCommissionPercent(double amount);
    public abstract String depositCondition();
    public abstract String withdrawCondition();

}
