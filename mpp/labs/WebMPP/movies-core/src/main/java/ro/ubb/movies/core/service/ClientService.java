package ro.ubb.movies.core.service;



import ro.ubb.movies.core.model.Client;

import java.util.List;
import java.util.Set;

/**
 * Created by andrapop on 2017-04-21.
 */
public interface ClientService {
    List<Client> findAll();

    Client updateClient(Long clientId, String firstname, String lastname, String phoneNr, String address, Set<Long> movie);

    Client createClient(String firstname , String lastname, String phoneNr, String address);

    Client findClient(Long clientId);

    void deleteClient(Long clientId);
}
