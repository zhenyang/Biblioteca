package twu.Biblioteca;

import twu.Biblioteca.Resource.ResourceHolder;
import twu.Biblioteca.UI.UserInterface;

public class Library {
    private static UserInterface ui = new UserInterface();
    private static ResourceHolder resourceHolder = new ResourceHolder();
    private static boolean isGoing = true;

    public static void main(String[] args) {
        start();
        while (isGoing) {
            ui.showMenu();
            processUserSelection(ui.getUserInput());
        }
    }

    private static void start() {
        ui.welcome();
        isGoing=true;
    }

    private static void processUserSelection(int userInput) {
        switch (userInput) {
            case 1:
                ui.outputToUser(resourceHolder.listBooks());
                break;
            case 2:
                reserveBook();
                break;
            case 3:
                checkLibraryNumber();
                break;
            case 0:
                exit();
                break;
            default:
                ui.outputToUser("Select a valid option!!");
        }
    }

    private static void checkLibraryNumber() {
        ui.outputToUser("Please talk to Librarian. Thank you.");
    }

    private static void reserveBook() {
        ui.outputToUser("Please Input The Book You Want:");
        long bookId=ui.getUserInput();
        if(resourceHolder.reserveBook(bookId)){
             ui.outputToUser("Thank You! Enjoy the book.");
        }
        else {
            ui.outputToUser("Sorry we don't have that book yet.");
        }
    }

    private static void exit() {
        ui.outputToUser("Exiting!");
        isGoing = false;
    }
}
