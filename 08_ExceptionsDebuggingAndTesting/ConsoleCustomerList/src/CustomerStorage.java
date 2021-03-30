import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        String name = components[0] + " " + components[1];
        String phoneNumber = components[3];
        String eMail = components[2];
        checkData(phoneNumber, eMail);
        storage.put(name, new Customer(name, phoneNumber, eMail));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        if (storage.containsKey(name)) {
            storage.remove(name);
        } else {
            throw new IllegalArgumentException(String.format("Name '%s' does not exists", name));
        }

    }

    private void checkData(String phoneNumber, String eMail) {
        StringBuilder errorsBuilder = new StringBuilder();
        if (!isCorrectPhoneNumber(phoneNumber)) {
            errorsBuilder.append(String.format("Phone number '%s' is incorrect; ", phoneNumber));
        }
        if (!isCorrectEmail(eMail)) {
            errorsBuilder.append(String.format("e-mail '%s' is incorrect; ", eMail));;
        }
        if (errorsBuilder.length() > 0) {
            throw new IllegalArgumentException(errorsBuilder.toString());
        }
    }

    private boolean isCorrectPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
    }

    private boolean isCorrectEmail(String email) {
        return email.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
    }

    public int getCount()
    {
        return storage.size();
    }
}