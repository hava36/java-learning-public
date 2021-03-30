package acoounts;

import managers.ResourceManager;

import java.time.LocalDate;

public class DepositAccount extends BankAccount {

    private LocalDate lastDepositeDate;

    @Override
    public void depositAmount(double amount) {
        super.depositAmount(amount);
        lastDepositeDate = LocalDate.now();
    }

    @Override
    public boolean withdrawISAvailable(double amount) {
        if (lastDepositeDate.plusMonths(1).isBefore(LocalDate.now())) {
            return super.withdrawISAvailable(amount);
        }
        System.out.println(String.format(ResourceManager.getMessage("ban_operation"), LocalDate.now().toString()));
        return false;
    }
}
