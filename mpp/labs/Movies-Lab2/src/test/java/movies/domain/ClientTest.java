package test.java.movies.domain;

import junit.framework.TestCase;
import main.java.ro.ubb.movies.domain.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by andrapop on 2017-03-14.
 */
public class ClientTest extends TestCase {
    private static final Integer ID = new Integer(1);
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PHONE_NR = "phoneNr";
    private static final String ADDRESS = "address";
    private static final Integer NEW_ID = new Integer(2);
    private static final String NEW_FIRST_NAME = "newFirstName";
    private static final String NEW_LAST_NAME = "newLastName";
    private static final String NEW_PHONE_NR = "newPhoneNr";
    private static final String NEW_ADDRESS = "newAddress";

    private Client client;

    @Before
    public void setUp() throws Exception{
        client = new Client(ID,FIRST_NAME,LAST_NAME,PHONE_NR,ADDRESS);
    }

    @After
    public void tearDown() throws Exception {
        client = null;
    }

    @Test
    public void testGetID() throws Exception {
        assertEquals("Client ids should be equal",ID,client.getId());
    }

    @Test
    public void testSetId() throws Exception {
        client.setId(NEW_ID);
        assertEquals("Client ids should be equal",NEW_ID,client.getId());
    }


    @Test
    public void testGetFirstName() throws Exception {
        assertEquals("Client first names should be equal",FIRST_NAME,client.getFirstName());
    }

    @Test
    public void testSetFirstName() throws Exception {
        client.setFirstName(NEW_FIRST_NAME);
        assertEquals("Client first names should be equal",NEW_FIRST_NAME,client.getFirstName());
    }

    @Test
    public void testGetLastName() throws Exception {
        assertEquals("Client last names should be equal",LAST_NAME,client.getLastName());
    }

    @Test
    public void testSetLastName() throws Exception {
        client.setLastName(NEW_LAST_NAME);
        assertEquals("Client last names should be equal",NEW_LAST_NAME,client.getLastName());
    }

    @Test
    public void testGetPhoneNr() throws Exception {
        assertEquals("Client phone nr should be equal",PHONE_NR,client.getPhoneNr());
    }

    @Test
    public void testSetPhoneNr() throws Exception {
        client.setPhoneNr(NEW_PHONE_NR);
        assertEquals("Client phone nr should be equal",NEW_PHONE_NR,client.getPhoneNr());
    }

    @Test
    public void testGetAddress() throws Exception {
        assertEquals("Client addresses should be equal",ADDRESS,client.getAddress());
    }

    @Test
    public void testSetAddress() throws Exception {
        client.setAddress(NEW_ADDRESS);
        assertEquals("Client addresses should be equal",NEW_ADDRESS,client.getAddress());
    }

}