package test.java.movies.domain;

import main.java.ro.ubb.movies.domain.Movie;
import main.java.ro.ubb.movies.domain.genreType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by andrapop on 2017-03-14.
 */
public class MovieTest {
    private static final Integer ID = new Integer(1);
    private static final Integer NEW_ID = new Integer(2);
    private static final String NAME = "movieName";
    private static final String NEW_NAME = "newMovieName";
    private static final genreType GENRE = genreType.ACTION;
    private static final genreType NEW_GENRE = genreType.COMEDY;
    private static final int YEAR = 1997;
    private static final int NEW_YEAR = 2000;

    private Movie movie;

    @Before
    public void setUp() throws Exception{
        movie = new Movie(ID,NAME,GENRE,YEAR);
    }

    @After
    public void tearDown() throws Exception{
        movie = null;
    }

    @Test
    public void testGetID() throws Exception{
        assertEquals("Movie id's should be equal",ID,movie.getId());
    }

    @Test
    public void testGetName() throws Exception{
        assertEquals("Movie names should be equal",NAME,movie.getName());
    }

    @Test
    public void testGetGenre() throws Exception{
        assertEquals("Movie genres should be equal",GENRE,movie.getGenre());
    }

    @Test
    public void testGetYear() throws Exception{
        assertEquals("Movie years should be equal",YEAR,movie.getYear());
    }

    @Test
    public void testSetId() throws Exception{
        movie.setId(NEW_ID);
        assertEquals("Movie ids should be equal",NEW_ID,movie.getId());
    }

    @Test
    public void testSetName() throws Exception{
        movie.setName(NEW_NAME);
        assertEquals("Movie ids should be equal",NEW_NAME,movie.getName());
    }

    @Test
    public void testSetGenre() throws Exception{
        movie.setGenre(NEW_GENRE);
        assertEquals("Movie names should be equal",NEW_GENRE,movie.getGenre());
    }

    @Test
    public void testSetYear() throws Exception{
        movie.setYear(NEW_YEAR);
        assertEquals("Movie year should be equal",NEW_YEAR,movie.getYear());
    }






}
