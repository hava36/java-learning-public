package clients;

import managers.ResourceManager;

public class IndividualEntrepreneur extends Client {

    @Override
    protected float getDepositCommissionPercent(double amount) {
        float percent = 1.0f;
        if (amount < 1000d) {
            percent = 0.5f;
        }
        return percent;
    }

    @Override
    protected float getWithdrawCommissionPercent(double amount) {
        return 0f;
    }

    @Override
    public String depositCondition() {
        return ResourceManager.getMessage("entr_person_deposit_conditions");
    }

    @Override
    public String withdrawCondition() {
        return ResourceManager.getMessage("entr_person_withdraw_conditions");
    }

    @Override
    public String toString() {
        return "Individual Entrepreneur";
    }

}
