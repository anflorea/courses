package test.java.movies.service;

import main.java.ro.ubb.movies.domain.Movie;
import main.java.ro.ubb.movies.domain.MovieClient;
import main.java.ro.ubb.movies.domain.genreType;
import main.java.ro.ubb.movies.domain.validators.MovieClientValidator;
import main.java.ro.ubb.movies.domain.validators.Validator;
import main.java.ro.ubb.movies.repository.InMemoryRepository;
import main.java.ro.ubb.movies.service.MovieClientService;
import main.java.ro.ubb.movies.service.MovieService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by andrapop on 2017-03-19.
 */
public class MovieServiceTest {

    private Validator<Movie> validator;
    private InMemoryRepository<Integer, Movie> repository;
    private MovieService service;

    private Movie movie;

    @Before
    public void setUp() throws Exception {
        validator = new MovieClientValidator();
        repository = new InMemoryRepository(validator);
        movie = new Movie(1,"Pirates", genreType.valueOf("COMEDY"),1997);
        service = new MovieService(repository);
    }

    @After
    public void tearDown() throws Exception {
        validator = null;
        repository = null;
        movie = null;
        service = null;
    }

    @Test
    public void filter() throws Exception {
        service.addMovie(movie);
        assertEquals(service.filter(1997).size(),1);
    }

    @Test
    public void getNrMovies() throws Exception {
        service.addMovie(movie);
        assertEquals(service.getNrMovies().get(movie.getGenre().name()).intValue(),1);

    }

}