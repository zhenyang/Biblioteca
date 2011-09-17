package twu.Biblioteca.UI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

public class UserInterfaceTest {

    private UserInterface userInterface;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        userInterface = new UserInterface();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void user_should_see_welcome_when_app_start() throws Exception {
        userInterface.welcome();
        assertThat(outContent.toString(), containsString("Welcome to The Bangalore Public Library System\n"));
    }

    @Test
    public void user_should_see_menu() throws Exception {
        userInterface.showMenu();
        assertThat(outContent.toString(), containsString("MENU\n1. View All Books\n2. Reserve A Book\n" +
                "3. Check Your Library Number\n4. view all movies\n5. Log in\n0. Exit\nPlease Select A Option"));
    }

    @Test
    public void user_should_input_a_number_for_selection() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("1").atSomePoint());
        assertThat(userInterface.getUserInputInteger(), is(1));
    }

    @Test
    public void user_should_be_notified_when_input_not_a_number() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("A").atSomePoint());
        assertThat(userInterface.getUserInputInteger(), is(-1));
    }

    @Test
    public void user_should_see_view_movie_menu() throws Exception {
        userInterface.showMenu();

        assertThat(outContent.toString(), containsString("4. view all movies"));
    }

    @Test
    public void user_should_see_login_menu() throws Exception {
        userInterface.showMenu();

        assertThat(outContent.toString(), containsString("5. Log in"));
    }
}