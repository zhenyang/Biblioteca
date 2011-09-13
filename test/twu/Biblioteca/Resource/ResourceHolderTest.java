package twu.Biblioteca.Resource;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class ResourceHolderTest {

    private ResourceHolder resourceHolder;

    @Before
    public void setUp() throws Exception {
        resourceHolder = new ResourceHolder();
    }

    @Test
    public void should_list_all_the_books() throws Exception {
        String result = resourceHolder.listBooks();
        assertThat(result, containsString("1. Clean Code"));
        assertThat(result, containsString("2. Test Driven Development"));
        assertThat(result, containsString("3. Head First JAVA"));
    }

    @Test
    public void reserve_should_success_given_book_exist() throws Exception {
        assertThat(resourceHolder.reserveBook(1), is(true));
    }

    @Test
    public void reserve_should_fail_given_book_not_exist() throws Exception {
        assertThat(resourceHolder.reserveBook(6), is(false));
    }

    @Test
    public void should_list_all_the_movies() throws Exception {
        String result=resourceHolder.listMovies();
        assertThat(result,containsString("Forrest Gump - Director: Robert Zemeckis - Rating: 9"));
        assertThat(result,containsString("Eternal Sunshine of the Spotless Mind - Director: Michel Gondry - Rating: 8"));
        assertThat(result,containsString("Source Code - Director: Duncan Jones - Rating: N/A"));
    }
}
