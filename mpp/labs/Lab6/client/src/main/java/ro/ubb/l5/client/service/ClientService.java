package ro.ubb.l5.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.l5.common.*;
import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.Movie;
import ro.ubb.l5.common.domain.MovieClient;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Created by andrapop on 2017-03-30.
 */
public class ClientService implements IService {
    @Autowired
    private IService service;



    public void addMovie(Movie m) throws MovieServiceException {
        service.addMovie(m);

    }

    @Override
    public void removeMovie(int id) throws MovieServiceException {
        service.removeMovie(id);
    }

    @Override
    public void updateMovie(Movie m) throws MovieServiceException {
        service.updateMovie(m);
    }

    @Override
    public List<Movie> filterMovies(int year) {
        return service.filterMovies(year);
    }

    @Override
    public Iterable<Client> getAllClients() {
        return service.getAllClients();
    }

    @Override
    public void addClient(Client c) throws MovieServiceException {
        service.addClient(c);
    }

    @Override
    public void removeClient(int id) throws MovieServiceException {
        service.removeClient(id);
    }

    @Override
    public void updateClient(Client c) throws MovieServiceException {
        service.updateClient(c);
    }

    @Override
    public void addRental(MovieClient mc) throws MovieServiceException{
         service.addRental(mc);
    }

    @Override
    public Iterable<MovieClient> getAllRentals() {
        return service.getAllRentals();
    }

    @Override
    public Map<String, Integer> getRentalReport() {
        return service.getRentalReport();
    }

    @Override
    public Map<String, Integer> getNrMoviesType() {
        return service.getNrMoviesType();
    }


    public Iterable<Movie> getAllMovies() {
        return service.getAllMovies();
    }



}
