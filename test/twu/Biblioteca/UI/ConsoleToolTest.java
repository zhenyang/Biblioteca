package twu.Biblioteca.UI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleToolTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ConsoleTool consoleTool;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        consoleTool = new ConsoleTool();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void should_output_to_console() throws Exception {
        consoleTool.output("Hello");
        assertThat(outContent.toString(), is("Hello\n"));
    }

    @Test
    public void should_get_input_from_console() throws Exception {
        System.setIn(new InputStreamBuilder().toReturn("Hello, World!").then("again").atSomePoint());
        assertThat(consoleTool.getInput(),is("Hello, World!"));
        assertThat(consoleTool.getInput(),is("again"));
    }
}
