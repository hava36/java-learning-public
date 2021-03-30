package clients;

import managers.ResourceManager;

public class JuridicalPerson extends Client {

    @Override
    protected float getDepositCommissionPercent(double amount) {
        return 0.0f;
    }

    @Override
    protected float getWithdrawCommissionPercent(double amount) {
        return 1f;
    }


    @Override
    public String depositCondition() {
        return ResourceManager.getMessage("jur_person_deposit_conditions");
    }

    @Override
    public String withdrawCondition() {
        return ResourceManager.getMessage("jur_person_withdraw_conditions");
    }

    @Override
    public String toString() {
        return "Juridical person";
    }

}
