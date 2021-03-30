package clients;

import managers.ResourceManager;

public class IndividualPerson extends Client {

    @Override
    protected float getDepositCommissionPercent(double amount) {
        return 0;
    }

    @Override
    protected float getWithdrawCommissionPercent(double amount) {
        return 0;
    }

    @Override
    public String depositCondition() {
        return ResourceManager.getMessage("ind_person_deposit_conditions");
    }

    @Override
    public String withdrawCondition() {
        return ResourceManager.getMessage("ind_person_withdraw_conditions");
    }

    @Override
    public String toString() {
        return "Individual person";
    }

}
