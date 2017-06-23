package test.java.movies.domain;

import junit.framework.TestCase;
import main.java.ro.ubb.movies.domain.Client;
import main.java.ro.ubb.movies.domain.MovieClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by andrapop on 2017-03-19.
 */
public class MovieClientTest extends TestCase {
    private static final Integer ID = new Integer(1);
    private static final int CLIENT_ID = new Integer(1);
    private static final int MOVIE_ID = new Integer(1);
    private static final Integer NEW_ID = new Integer(1);
    private static final int NEW_CLIENT_ID = new Integer(1);
    private static final int NEW_MOVIE_ID = new Integer(1);

    private MovieClient movieClient;

    @Before
    public void setUp() throws Exception{
        movieClient = new MovieClient(ID,CLIENT_ID,MOVIE_ID);
    }

    @After
    public void tearDown() throws Exception {
        movieClient = null;
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals("rental ids should be equal",ID,movieClient.getId());
    }

    @Test
    public void testSetId() throws Exception {
        movieClient.setId(NEW_ID);
        assertEquals("rental ids should be equal",NEW_ID,movieClient.getId());
    }


    @Test
    public void testGetClientId() throws Exception {
        assertEquals("Client ids should be equal",CLIENT_ID,movieClient.getClientID());
    }

    @Test
    public void testSetClientId() throws Exception {
        movieClient.setClientID(NEW_CLIENT_ID);

        assertEquals("Client ids should be equal",NEW_CLIENT_ID, movieClient.getClientID());
    }

    @Test
    public void testGetMovieId() throws Exception {
        assertEquals("movie ids should be equal",MOVIE_ID,movieClient.getMovieID());
    }

    @Test
    public void testSetMovieId() throws Exception {
        movieClient.setClientID(NEW_MOVIE_ID);

        assertEquals("Client ids should be equal",NEW_MOVIE_ID, movieClient.getMovieID());
    }




}
