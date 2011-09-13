import org.junit.Before;
import org.junit.Test;
import twu.Biblioteca.Library;
import twu.Biblioteca.UI.InputStreamBuilder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class LibraryTest {

    private ByteArrayOutputStream outContent;
    private PrintStream console;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void user_should_see_welcome_and_menu() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("0").atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Welcome to The Bangalore Public Library System"));
        assertThat(outContent.toString(), containsString("MENU\n1. View All Books\n2. Reserve A Book\n" +
                "3. Check Your Library Number\n0. Exit\nPlease Select A Option"));
        assertThat(outContent.toString(), containsString("Exiting!"));
    }

    @Test
    public void user_should_be_notified_when_select_a_invalid_option() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("A").then("0").atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Select a valid option!!"));
    }

    @Test
    public void user_should_see_all_books() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("1").then("0").atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("1. Clean Code"));
        assertThat(outContent.toString(), containsString("2. Test Driven Development"));
        assertThat(outContent.toString(), containsString("3. Head First JAVA"));
    }

    @Test
    public void user_should_reserve_a_book() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("2").then("1").then("0").atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Please Input The Book You Want:"));
        assertThat(outContent.toString(), containsString("Thank You! Enjoy the book."));
    }

    @Test
    public void user_should_not_reserve_a_none_exist_book() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("2").then("5").then("0").atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Please Input The Book You Want:"));
        assertThat(outContent.toString(), containsString("Sorry we don't have that book yet."));
    }

    @Test
    public void user_should_check_library_number() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("3").then("0").atSomePoint());
        Library.main(new String[]{});

        assertThat(outContent.toString(), containsString("Please talk to Librarian. Thank you."));
    }
}
