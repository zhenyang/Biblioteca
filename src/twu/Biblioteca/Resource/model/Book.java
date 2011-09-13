package twu.Biblioteca.Resource.model;

public class Book {
    private long id;
    private String name;

    public Book(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
