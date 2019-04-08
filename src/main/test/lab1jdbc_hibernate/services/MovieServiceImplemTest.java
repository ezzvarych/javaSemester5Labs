package lab1jdbc_hibernate.services;

import lab1jdbc_hibernate.models.Director;
import lab1jdbc_hibernate.models.Movie;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieServiceImplemTest {
   // private final Connection connection = DBConnection.getConnection();
    private final MovieService movieService = new MovieServiceImpl();
    private final DirectorService directorService = new DirectorServiceImpl();

    /**
     * Comment @Disabled and input another data to insert to avoid conflict with existing DB
     */
    /*@AfterEach
    public void afterInsertMovie() {
        movieService.delete(movieService
                .getByTitle("New film")
                .getId());
    }*/
    @Test
    public void testInsertMovie() {
        Movie movieToInsert = new Movie("New film", directorService.getByName("Steven Spielberg"),
                90, 2019, "New film");
        Movie insertedMovie = movieService.insertEntity(movieToInsert);
        assertEquals(movieToInsert, insertedMovie);

        movieService.delete(movieToInsert.getId());
    }

    @Disabled
    @Test
    public void testDeleteMovie() {
        Movie movie = movieService.getById(1024);
        assertNotNull(movie);
        boolean del = movieService.delete(movie.getId());
        assertTrue(del);
        assertNull(movieService.getById(1024));
    }
    /**
     * Test adding movies with existing names
     */
    @Test
    public void testInsertEqualMovieException() {
        Movie movie = new Movie("Jaws", new Director(2), 120, 1985, "Hello");
        assertThrows(PersistenceException.class,
                () -> {movieService.insertEntity(movie);});
        List<Movie> afterInsert = movieService.getAll();
        //assertEquals(beforeInsert.size(), afterInsert.size());
    }

    @Test
    public void testUpdateMovie() {
        Movie movie1 = movieService.getById(1);
        movie1.setShortDescr("My new description");
        Movie updated = movieService.updateEntity(movie1);
        assertNotNull(updated);
        Movie movie2 = movieService.getById(1);
        assertEquals(movie1, movie2);

        movie1.setShortDescr("Old descr");
        movieService.updateEntity(movie1);
    }

    @Test
    public void testGetByTitle() {
        Movie movie = movieService.getByTitle("Avatar");
        assertEquals(movie.getId(), 2);
    }
}