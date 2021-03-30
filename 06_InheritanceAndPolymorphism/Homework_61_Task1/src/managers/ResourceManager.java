package managers;

import java.util.ResourceBundle;

public class ResourceManager {

    private static ResourceBundle alerts = ResourceBundle.getBundle("resources/messages");

    private ResourceManager() {

    }

    public static String getMessage(String key) {
        return alerts.getString(key);
    }

}
