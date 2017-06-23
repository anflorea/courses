package test.java.movies.repository;

import main.java.ro.ubb.movies.domain.Client;
import main.java.ro.ubb.movies.domain.validators.ClientValidator;
import main.java.ro.ubb.movies.domain.validators.Validator;
import main.java.ro.ubb.movies.repository.InMemoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

/**
 * Created by andrapop on 2017-03-19.
 */
public class InMemoryRepositoryTest {

    private Client client;
    private Client new_client;
    private InMemoryRepository<Integer, Client> repository;
    private Validator<Client> validator;

    @Before
    public void setUp() throws Exception {
        client = new Client(8,"Andrea","Boceli","0765690123","US");
        new_client = new Client(8,"Edith","Piaf","09872313","France");
        validator = new ClientValidator();
        repository = new InMemoryRepository<>(validator);
    }

    @After
    public void tearDown() throws Exception {
        repository = null;
    }

    @Test
    public void testSave() throws Exception {
        int initSize = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toSet()).size();
        repository.save(client);
        int finalSize = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toSet()).size();
        assertEquals("The size should be increased by one", initSize+1,finalSize);
    }

    @Test
    public void testDelete() {
        repository.save(client);
        int initSize = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toSet()).size();
        repository.delete(client.getId());
        int finalSize = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toSet()).size();
        assertEquals("The size should be decreased by one", finalSize,initSize-1);
    }

    @Test
    public void testUpdate(){
        repository.save(client);
        repository.update(new_client);
        assert(repository.findOne(client.getId()).get().equals(new_client));
    }

    @Test
    public void testFindOne(){
        repository.save(client);
        assert(repository.findOne(client.getId()).isPresent());
    }

}