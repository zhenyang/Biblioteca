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
    public void it_should_list_all_the_books() throws Exception {
        String result=resourceHolder.listBooks();
        assertThat(result, containsString("1. Clean Code"));
        assertThat(result, containsString("2. Test Driven Development"));
        assertThat(result, containsString("3. Head First JAVA"));
    }

    @Test
    public void reserve_should_success_given_book_exist() throws Exception {
        assertThat(resourceHolder.reserveBook(1),is(true));
    }
    @Test
    public void reserve_should_fail_given_book_not_exist() throws Exception {
        assertThat(resourceHolder.reserveBook(6),is(false));
    }
}
