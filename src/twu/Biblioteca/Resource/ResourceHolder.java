package twu.Biblioteca.Resource;

import twu.Biblioteca.Resource.model.Book;

import java.util.ArrayList;
import java.util.List;

public class ResourceHolder {
    List<Book> books;

    public ResourceHolder() {
        books = new ArrayList<Book>();
        books.add(new Book(1, "Clean Code"));
        books.add(new Book(2, "Test Driven Development"));
        books.add(new Book(3, "Head First JAVA"));
    }

    public String listBooks() {
        String result = "";
        for (Book book : books) {
            result += book.getId() + ". " + book.getName()+"\n";
        }
        return result;
    }

    public boolean reserveBook(long bookId) {
        for (Book book : books) {
            if (book.getId()==bookId){
                return true;
            }
        }
        return false;
    }
}
