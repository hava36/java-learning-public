import clients.Client;
import clients.IndividualEntrepreneur;
import clients.IndividualPerson;
import clients.JuridicalPerson;
import managers.ConsoleHelper;

import java.util.Collections;

public class Solution {

    public static void main(String[] args) {

        Client individualPerson = new IndividualPerson();
        ConsoleHelper.writeMessage("\n" + individualPerson.toString() + "\n");
        ConsoleHelper.writeMessage(individualPerson.depositCondition());
        ConsoleHelper.writeMessage(individualPerson.withdrawCondition());
        individualPerson.depositAmount(1000);
        individualPerson.withdrawAmount(100);
        individualPerson.withdrawAmount(999);

        Client juridicalPerson = new JuridicalPerson();
        ConsoleHelper.writeMessage("\n" + juridicalPerson.toString() + "\n");
        ConsoleHelper.writeMessage(juridicalPerson.depositCondition());
        ConsoleHelper.writeMessage(juridicalPerson.withdrawCondition());
        juridicalPerson.depositAmount(1000);
        juridicalPerson.withdrawAmount(100);
        juridicalPerson.withdrawAmount(999);

        Client individualEntrepreneur = new IndividualEntrepreneur();
        ConsoleHelper.writeMessage("\n" + individualEntrepreneur.toString() + "\n");
        ConsoleHelper.writeMessage(individualEntrepreneur.depositCondition());
        ConsoleHelper.writeMessage(individualEntrepreneur.withdrawCondition());
        individualEntrepreneur.depositAmount(1000);
        individualEntrepreneur.depositAmount(300);
        individualEntrepreneur.withdrawAmount(100);
        individualEntrepreneur.withdrawAmount(999);

    }
    
}
