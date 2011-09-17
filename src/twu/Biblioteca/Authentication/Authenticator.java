package twu.Biblioteca.Authentication;

import java.util.ArrayList;
import java.util.List;

public class Authenticator {
    private List<User> users;
    private User currentUser;

    public Authenticator() {
        users = prepareUsers();
        currentUser = null;
    }

    private List<User> prepareUsers() {
        List<User> users = new ArrayList<User>();
        users.add(new User("111-1111", "password"));
        users.add(new User("111-1112", "password"));
        users.add(new User("111-1113", "password"));
        return users;
    }

    public Boolean login(String libraryNumber, String password) {
        clearLoggedInUser();
        for (User user : users) {
            if (user.getLibraryNumber().equals(libraryNumber) && user.comparePassword(password)) {
                setCurrentUser(user);
                return true;
            }
        }
        return false;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Boolean isLoggedIn() {
        return !(currentUser == null);
    }

    private void clearLoggedInUser() {
        setCurrentUser(null);
    }

    private void setCurrentUser(User user) {
        currentUser = user;
    }

    public String getCurrentUserLibraryNumber() {
        return isLoggedIn() ? currentUser.getLibraryNumber() : "";
    }
}
