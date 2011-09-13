package twu.Biblioteca.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleTool implements IIOTool {

    public void output(String message) {
        System.out.println(message);
    }

    public String getInput() throws IOException {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
