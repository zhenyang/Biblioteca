package twu.Biblioteca.UI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        assertThat(outContent.toString(), is("Welcome to The Bangalore Public Library System\n"));
    }

    @Test
    public void user_should_see_menu() throws Exception {
        userInterface.showMenu();
        assertThat(outContent.toString(), is("MENU\n1. View All Books\n2. Reserve A Book\n" +
                "3. Check Your Library Number\n0. Exit\nPlease Select A Option"));
    }

    @Test
    public void user_should_input_a_number_for_selection() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("1").atSomePoint());
        assertThat(userInterface.getUserInput(),is(1));
    }

    @Test
    public void user_should_be_notified_when_input_not_a_number() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("A").atSomePoint());
        assertThat(userInterface.getUserInput(),is(-1));
        assertThat(outContent.toString(),is("Please Input A Number\n"));
    }
}