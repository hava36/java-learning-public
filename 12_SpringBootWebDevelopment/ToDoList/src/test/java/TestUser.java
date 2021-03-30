import main.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

@DisplayName("Testing of creating and updating user")
public class TestUser {

    private List<UserEntity> users;

    public TestUser() {
        setUp();
    }

    private void setUp() {
        users = new ArrayList<UserEntity>();
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setLastName("Johnson");
        user.setFirstName("Mike");
        users.add(user);
    }

    @Test
    @DisplayName("Testing of getter and setter methods for first name")
    public void firstNameGetAndSetTest() {

        UserEntity user = users.get(0);
        user.setFirstName("John");

        Assertions.assertEquals("John", user.getFirstName());

    }

    @Test
    @DisplayName("Testing of getter and setter methods for last name")
    public void lastNameGetAndSetTest() {

        UserEntity user = users.get(0);
        user.setLastName("McDonald");

        Assertions.assertEquals("McDonald", user.getLastName());

    }

    @Test
    @DisplayName("Testing of getter and setter methods for id")
    public void idGetAndSetTest() {

        UserEntity user = users.get(0);
        user.setId(2L);

        Assertions.assertEquals(2, user.getId());

    }

}
