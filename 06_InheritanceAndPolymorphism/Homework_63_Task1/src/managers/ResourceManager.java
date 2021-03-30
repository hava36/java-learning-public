package managers;

import java.util.ResourceBundle;

public class ResourceManager {

    private static ResourceBundle messages = ResourceBundle.getBundle("resources/messages");

    private ResourceManager() {

    }

    public static String getMessage(String key) {

        return messages.getString(key);

    }

}
