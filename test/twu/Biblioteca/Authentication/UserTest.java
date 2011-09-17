package twu.Biblioteca.Authentication;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    private User user;

    @Test
    public void password_should_be_comparable() throws Exception {
        user = new User("111", "123123");
        assertThat(user.comparePassword("111111"),is(false));
        assertThat(user.comparePassword("123123"),is(true));
    }
}
