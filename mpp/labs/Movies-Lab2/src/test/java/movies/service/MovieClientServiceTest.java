package test.java.movies.service;

import main.java.ro.ubb.movies.domain.Client;
import main.java.ro.ubb.movies.domain.MovieClient;
import main.java.ro.ubb.movies.domain.validators.ClientValidator;
import main.java.ro.ubb.movies.domain.validators.MovieClientValidator;
import main.java.ro.ubb.movies.domain.validators.Validator;
import main.java.ro.ubb.movies.repository.IRepository;
import main.java.ro.ubb.movies.repository.InMemoryRepository;
import main.java.ro.ubb.movies.service.MovieClientService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

/**
 * Created by andrapop on 2017-03-19.
 */
public class MovieClientServiceTest {

    private Validator<MovieClient> validator;
    private InMemoryRepository<Integer, MovieClient> repository;
    private MovieClientService service;

    private MovieClient mc;

    @Before
    public void setUp() throws Exception {
        validator = new MovieClientValidator();
        repository = new InMemoryRepository(validator);
        mc = new MovieClient(1,1,1);
        service = new MovieClientService(repository);

    }

    @After
    public void tearDown() throws Exception {
        repository = null;
        mc = null;
        service = null;

    }

    @Test
    public void addMovieClient() throws Exception {

        int initSize = service.getAllRentals().size();
        service.addMovieClient(mc);
        int finalSize = service.getAllRentals().size();
        assertEquals(initSize + 1,finalSize);
    }


    @Test
    public void findIdOccurences() throws Exception {
        service.addMovieClient(mc);
        assertEquals(service.findIdOccurences(mc.getMovieID()),1);
    }

    @Test
    public void reports() throws Exception {
        service.addMovieClient(mc);
        int reportSize = StreamSupport.stream(service.reports().entrySet().spliterator(), false).collect(Collectors.toSet()).size();
        assertEquals(reportSize,1);
    }

}