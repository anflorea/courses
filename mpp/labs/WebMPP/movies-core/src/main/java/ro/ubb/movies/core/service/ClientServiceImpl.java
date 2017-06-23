package ro.ubb.movies.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movies.core.model.Client;
import ro.ubb.movies.core.model.Movie;
import ro.ubb.movies.core.repository.ClientRepository;
import ro.ubb.movies.core.repository.MovieRepository;


import java.util.List;
import java.util.Set;

/**
 * Created by andrapop on 2017-04-21.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public List<Client> findAll() {
        log.trace("findAll");
//        List<Client> clients = clientRepository.findAll();
//        List<Client> clients = clientRepository.findAllWithMoviesGraph();
//        List<Client> clients = clientRepository.findAllWithMoviesSqlQuery();
        List<Client> clients = clientRepository.findAllWithMoviesJpql();
//        List<Client> clients = clientRepository.findAllWithMoviesJpaCriteria();

        log.trace("findAll: clients={}", clients);

        return clients;
    }

    @Override
    public Client findClient(Long clientId) {
        log.trace("findClient: clientId={}", clientId);

        Client client = clientRepository.findOne(clientId);
//        Client client = clientRepository.findOneWithMovies(clientId);

        log.trace("findClient: client={}", client);

        return client;
    }

    @Override
    @Transactional
    public Client updateClient(Long clientId, String firstname, String lastname, String phoneNr, String address, Set<Long> movies) {
        log.trace("updateClient: clientId={}, firstname={}, lastname={}, phoneNr={}, address={}, movies={}",
                clientId, firstname, lastname, phoneNr, address, movies);

        Client client = clientRepository.findOne(clientId);
        client.setFirstName(firstname);
        client.setLastName(lastname);
        client.setPhoneNr(phoneNr);
        client.setAddress(address);
        System.out.println(movies);
        client.getMovies().stream()
                .map(m -> m.getId())
                .forEach(id -> {
                    if (movies.contains(id)) {
                        movies.remove(id);
                    }
                });

        List<Movie> movieList = movieRepository.findAll(movies);

        movieList.stream().forEach(m -> client.addMovie(m));



        log.trace("updateClient: client={}", client);

        return client;
    }

    @Override
    public Client createClient(String firstname , String lastname, String phoneNr, String address) {
        log.trace("createClient: firstname={}, lastname={}, phoneNr={}, address={}",
                firstname, lastname, phoneNr, address);
        Client client = Client.builder().
                firstName(firstname).
                lastName(lastname).
                phoneNr(phoneNr).
                address(address).build();
        client = clientRepository.save(client);
        System.out.println("aci");
        log.trace("createClient: client={}", client);

        return client;
    }

    @Override
    public void deleteClient(Long clientId) {
        log.trace("deleteClient: clientId={}", clientId);

        clientRepository.delete(clientId);

        log.trace("deleteClient - method end");
    }
}
