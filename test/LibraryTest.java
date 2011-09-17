import org.junit.Before;
import org.junit.Test;
import twu.Biblioteca.Library;
import twu.Biblioteca.UI.InputStreamBuilder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class LibraryTest {

    private static final String VIEW_BOOKS = "1";
    private static final String RESERVE_BOOK = "2";
    private static final String CHECK_LIBRARY_NUMBER = "3";
    private static final String VIEW_MOVIES = "4";
    private static final String LOGIN = "5";
    private static final String EXIT = "0";
    private ByteArrayOutputStream outContent;
    private static final String LIBRARY_NUMBER = "111-1111";
    private static final String VALID_PASSWORD = "password";
    private static final String INVALID_PASSWORD = "invalidPassword";

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void user_should_see_welcome_and_menu() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn(EXIT).atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Welcome to The Bangalore Public Library System"));
        assertThat(outContent.toString(), containsString("MENU\n1. View All Books\n2. Reserve A Book\n" +
                "3. Check Your Library Number\n4. view all movies\n5. Log in\n0. Exit\nPlease Select A Option"));
        assertThat(outContent.toString(), containsString("Exiting!"));
    }

    @Test
    public void user_should_be_notified_when_select_a_invalid_option() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("A").then(EXIT).atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Select a valid option!!"));
    }

    @Test
    public void user_should_see_all_books() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn(VIEW_BOOKS).then(EXIT).atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("1. Clean Code"));
        assertThat(outContent.toString(), containsString("2. Test Driven Development"));
        assertThat(outContent.toString(), containsString("3. Head First JAVA"));
    }

    @Test
    public void user_should_reserve_a_book() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn(RESERVE_BOOK).then(VIEW_BOOKS).then(EXIT).atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Please Input The Book You Want:"));
        assertThat(outContent.toString(), containsString("Thank You! Enjoy the book."));
    }

    @Test
    public void user_should_not_reserve_a_none_exist_book() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn(RESERVE_BOOK).then("5").then(EXIT).atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Please Input The Book You Want:"));
        assertThat(outContent.toString(), containsString("Sorry we don't have that book yet."));
    }

    @Test
    public void user_should_check_library_number() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn(CHECK_LIBRARY_NUMBER).then(EXIT).atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Please talk to Librarian. Thank you."));
    }

    @Test
    public void user_should_see_a_list_of_movies() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn(VIEW_MOVIES).then(EXIT).atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("4. view all movies"));
        assertThat(outContent.toString(), containsString("Forrest Gump - Director: Robert Zemeckis - Rating: 9"));
    }

    @Test
    public void user_should_be_notified_when_login_success() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn(LOGIN).toReturn(LIBRARY_NUMBER).toReturn(VALID_PASSWORD).then(EXIT).atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Please input your library number:"));
        assertThat(outContent.toString(), containsString("Please input your password:"));
        assertThat(outContent.toString(), containsString("Log in success!"));
    }

    @Test
    public void user_should_be_notified_when_login_fail() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn(LOGIN).toReturn(LIBRARY_NUMBER).toReturn(INVALID_PASSWORD).then(EXIT).atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Please input your library number:"));
        assertThat(outContent.toString(), containsString("Please input your password:"));
        assertThat(outContent.toString(), containsString("Invalid library number or password."));
    }

    @Test
    public void user_should_check_library_number_when_logged_in() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn(LOGIN).toReturn(LIBRARY_NUMBER).toReturn(VALID_PASSWORD).toReturn(CHECK_LIBRARY_NUMBER).then(EXIT).atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Your library number is "+LIBRARY_NUMBER));
    }

}
