package twu.Biblioteca.Authentication;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AuthenticatorTest {

    private static final String LIBRARY_NUMBER1 = "111-1111";
    private static final String LIBRARY_NUMBER2 = "111-1112";
    private static final String VALID_PASSWORD = "password";
    private static final String INVALID_PASSWORD = "invalidPassword";
    private static final String INVALID_LIBRARY_NUMBER = "1-111111";
    private Authenticator authenticator;

    @Before
    public void setUp() throws Exception {
        authenticator = new Authenticator();
        authenticator.setUsers(prepareUsers());
    }

    private List<User> prepareUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(LIBRARY_NUMBER1, VALID_PASSWORD));
        users.add(new User(LIBRARY_NUMBER2, VALID_PASSWORD));
        return users;
    }

    @Test
    public void should_login_success_given_valid_username_and_password() throws Exception {
        assertThat(authenticator.login(LIBRARY_NUMBER1, VALID_PASSWORD), is(true));
    }

    @Test
    public void should_login_fail_given_invalid_username_and_password() throws Exception {
        assertThat(authenticator.login(LIBRARY_NUMBER1, INVALID_PASSWORD), is(false));
        assertThat(authenticator.login(INVALID_LIBRARY_NUMBER, VALID_PASSWORD), is(false));
    }

    @Test
    public void should_return_whether_logged_in() throws Exception {
        assertThat(authenticator.isLoggedIn(), is(false));
        authenticator.login(LIBRARY_NUMBER1, VALID_PASSWORD);
        assertThat(authenticator.isLoggedIn(), is(true));
        authenticator.login(LIBRARY_NUMBER2, INVALID_PASSWORD);
        assertThat(authenticator.isLoggedIn(), is(false));
    }

    @Test
    public void should_return_logged_in_user_library_number() throws Exception {
        assertThat(authenticator.getCurrentUserLibraryNumber(),is(""));
        authenticator.login(LIBRARY_NUMBER1, VALID_PASSWORD);
        assertThat(authenticator.getCurrentUserLibraryNumber(),is(LIBRARY_NUMBER1));
    }
}
