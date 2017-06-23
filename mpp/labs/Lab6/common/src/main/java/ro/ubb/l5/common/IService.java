package ro.ubb.l5.common;

import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.Movie;
import ro.ubb.l5.common.domain.MovieClient;

import java.util.List;
import java.util.Map;


/**
 * Created by andrapop on 2017-03-30.
 */
public interface IService {

    //CompletableFuture<String> addMovie(String info);

    //CompletableFuture<String> removeMovie(String info);

    //CompletableFuture<String> updateMovie(String info);

    Iterable<Movie> getAllMovies();

    void addMovie(Movie m) throws MovieServiceException;

    void removeMovie(int id);

    void updateMovie(Movie m);

    List<Movie> filterMovies(int year);


    Iterable<Client> getAllClients();

    void addClient(Client c);

    void removeClient(int id);

    void updateClient(Client c);

    void addRental(MovieClient mc);

    Iterable<MovieClient> getAllRentals();

    Map<String, Integer> getRentalReport();

    Map<String, Integer> getNrMoviesType();

    /*

    CompletableFuture<String> filterMoviesYear(String year);



    CompletableFuture<String> addClient(String info);

    CompletableFuture<String> getAllClients();

    CompletableFuture<String> updateClient(String info);

    CompletableFuture<String> deleteClient(String info);


    CompletableFuture<String> addRental(String info);
    CompletableFuture<String> getAllRentals();
    CompletableFuture<String> getRentalReport();

    CompletableFuture<String> getNrMoviesType();
    */


}
