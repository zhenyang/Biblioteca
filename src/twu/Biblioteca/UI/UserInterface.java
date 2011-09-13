package twu.Biblioteca.UI;

import java.io.IOException;

public class UserInterface {
    public static final int INPUT_NOT_NUMBER = -1;
    IIOTool ioTool = new ConsoleTool();

    public void welcome() {
        ioTool.output("Welcome to The Bangalore Public Library System");
    }

    public void showMenu() {
        ioTool.output("MENU\n1. View All Books\n2. Reserve A Book\n" +
                "3. Check Your Library Number\n0. Exit\nPlease Select A Option");
    }

    public int getUserInput() {
        try {
            return Integer.parseInt(ioTool.getInput());
        } catch (Exception e) {
            return INPUT_NOT_NUMBER;
        }
    }

    public void outputToUser(String message){
        ioTool.output(message);
    }
}
