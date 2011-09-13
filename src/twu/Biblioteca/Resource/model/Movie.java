package twu.Biblioteca.Resource.model;

public class Movie {
    private String name;
    private String director;
    public static final int NOT_RATED = -1;

    public Movie(String name, String director, int rating) {
        this.name = name;
        this.director = director;
        this.rating = rating;
    }

    public Movie(String name, String director) {
        this(name, director, NOT_RATED);
    }

    private int rating;

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }
}
