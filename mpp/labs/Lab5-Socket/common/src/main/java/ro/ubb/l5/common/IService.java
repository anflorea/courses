package ro.ubb.l5.common;

import ro.ubb.l5.common.domain.Movie;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by andrapop on 2017-03-30.
 */
public interface IService {
    String SERVICE_HOST = "localhost";
    int SERVICE_PORT = 1234;

    String GET_ALL_MOVIES = "getAllMovies";
    String ADD_MOVIE = "addMovie";
    String DELETE_MOVIE = "removeMovie";
    String UPDATE_MOVIE = "updateMovie";
    String FILTER_MOVIES = "filterMoviesYear";
    String GET_MOVIES_TYPE = "getNrMoviesType";

    String ADD_CLIENT ="addClient";
    String GET_ALL_CLIENTS = "getAllClients";
    String DELETE_CLIENT = "deleteClient";
    String UPDATE_CLIENT = "updateClient";

    String ADD_RENTAL = "addRental";
    String GET_ALL_RENTALS = "getAllRentals";
    String GET_REPORT ="getRentalReport";


    /*
    String ADD_CLIENT = "addClient";
    String REMOVE_CLIENT = "removeClient";
    String UPDATE_CLIENT = "updateClient";

*/
    CompletableFuture<String> addMovie(String info);

    CompletableFuture<String> removeMovie(String info);

    CompletableFuture<String> updateMovie(String info);

    CompletableFuture<String> getAllMovies();

    CompletableFuture<String> filterMoviesYear(String year);



    CompletableFuture<String> addClient(String info);

    CompletableFuture<String> getAllClients();

    CompletableFuture<String> updateClient(String info);

    CompletableFuture<String> deleteClient(String info);


    CompletableFuture<String> addRental(String info);
    CompletableFuture<String> getAllRentals();
    CompletableFuture<String> getRentalReport();

    CompletableFuture<String> getNrMoviesType();


}
