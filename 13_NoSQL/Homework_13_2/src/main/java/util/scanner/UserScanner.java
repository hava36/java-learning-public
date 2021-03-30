package util.scanner;

import lombok.AllArgsConstructor;
import model.User;
import service.Service;

import java.util.List;

@AllArgsConstructor
public class UserScanner implements ModelScanner {

    private Service<String, User> service;

    @Override
    public void scan() {
        int counter = 1;
        while (true) {
            List<User> list = service.getAll();
            if (list.size() == 0) {
                break;
            }
            showAndUpdateFirstUser(list.get(0));
            paymentProcess(counter, list);
            sleep(1000);
            counter++;
        }
    }

    private void showAndUpdateFirstUser(User currentUser) {
        System.out.println(
                String.format("На главной странице показываем пользователя %s", currentUser.toString()));
        service.update(currentUser);
    }

    private void paymentProcess(int counter, List<User> list) {
        if (counter % 10 == 0) {
            int elementIndex = (int) (Math.random() * list.size());
            System.out.println(
                    String.format("Пользователь %s оплатил платную услугу", list.get(elementIndex)));
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
