package twu.Biblioteca;

import twu.Biblioteca.Authentication.Authenticator;
import twu.Biblioteca.Resource.ResourceHolder;
import twu.Biblioteca.UI.UserInterface;

public class Library {
    private static UserInterface ui = new UserInterface();
    private static ResourceHolder resourceHolder = new ResourceHolder();
    private static boolean isGoing = true;
    private static Authenticator authenticator = new Authenticator();

    public static void main(String[] args) {
        start();
        while (isGoing) {
            ui.showMenu();
            processUserSelection(ui.getUserInputInteger());
        }
    }

    private static void start() {
        ui.welcome();
        isGoing = true;
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
            case 4:
                ui.outputToUser(resourceHolder.listMovies());
                break;
            case 5:
                login();
                break;
            case 0:
                exit();
                break;
            default:
                ui.outputToUser("Select a valid option!!");
        }
    }

    private static void login() {
        ui.outputToUser("Please input your library number:");
        String libraryNumber = ui.getUserInputString();
        ui.outputToUser("Please input your password:");
        String password = ui.getUserInputString();
        if (authenticator.login(libraryNumber, password)) {
            ui.outputToUser("Log in success!");
        } else {
            ui.outputToUser("Invalid library number or password.");
        }
    }

    private static void checkLibraryNumber() {
        if (authenticator.isLoggedIn()) {
            ui.outputToUser("Your library number is " + authenticator.getCurrentUserLibraryNumber());
        } else {
            ui.outputToUser("Please talk to Librarian. Thank you.");
        }
    }

    private static void reserveBook() {
        ui.outputToUser("Please Input The Book You Want:");
        long bookId = ui.getUserInputInteger();
        if (resourceHolder.reserveBook(bookId)) {
            ui.outputToUser("Thank You! Enjoy the book.");
        } else {
            ui.outputToUser("Sorry we don't have that book yet.");
        }
    }

    private static void exit() {
        ui.outputToUser("Exiting!");
        isGoing = false;
    }
}
