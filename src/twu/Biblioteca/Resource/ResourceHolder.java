package twu.Biblioteca.Resource;

import twu.Biblioteca.Resource.model.Book;
import twu.Biblioteca.Resource.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class ResourceHolder {
    List<Book> books;
    List<Movie> movies;

    public ResourceHolder() {
        books = createBooks();
        movies = createMovies();
    }

    private List<Movie> createMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie("Forrest Gump", "Robert Zemeckis", 9));
        movies.add(new Movie("Eternal Sunshine of the Spotless Mind", "Michel Gondry", 8));
        movies.add(new Movie("Source Code", "Duncan Jones"));
        return movies;
    }

    private List<Book> createBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book(1, "Clean Code"));
        books.add(new Book(2, "Test Driven Development"));
        books.add(new Book(3, "Head First JAVA"));
        return books;
    }

    public String listBooks() {
        String result = "";
        for (Book book : books) {
            result += book.getId() + ". " + book.getName() + "\n";
        }
        return result;
    }

    public boolean reserveBook(long bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return true;
            }
        }
        return false;
    }

    public String listMovies() {
        String result = "";
        for (Movie movie : movies) {
            result += movie.getName() + " - Director: " + movie.getDirector() + " - Rating: " +
                    (movie.getRating() == Movie.NOT_RATED ? "N/A" : movie.getRating()) + "\n";
        }
        return result;
    }
}
