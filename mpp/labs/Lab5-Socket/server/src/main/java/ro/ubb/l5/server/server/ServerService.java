package ro.ubb.l5.server.server;

import ro.ubb.l5.common.IService;
import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.Movie;
import ro.ubb.l5.common.domain.MovieClient;
import ro.ubb.l5.common.domain.genreType;
import ro.ubb.l5.common.domain.validators.RepositoryException;
import ro.ubb.l5.common.domain.validators.ValidatorException;
import ro.ubb.l5.server.service.ClientService;
import ro.ubb.l5.server.service.MovieClientService;
import ro.ubb.l5.server.service.MovieService;


import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by andrapop on 2017-03-30.
 */
public class ServerService implements IService {

    private ExecutorService executorService;
    private ClientService clientService;
    private MovieService movieService;
    private MovieClientService rentService;

    public ServerService(ExecutorService executorService, ClientService clientService, MovieService movieService, MovieClientService rentService) {
        this.executorService = executorService;
        this.clientService = clientService;
        this.movieService = movieService;
        this.rentService = rentService;
    }


    @Override
    public CompletableFuture<String> addMovie(String info) {
        String[] m = info.split(",");
        // Movie(String: name, genreType: genre, int: year)
        try {
            Movie movie = new Movie(Integer.valueOf(m[0]), m[1], genreType.valueOf(m[2]), Integer.valueOf(m[3]));
            movieService.addMovie(movie);
            return CompletableFuture.supplyAsync(() -> "Add Movie->OK ");
        } catch (RepositoryException e) {
            return CompletableFuture.supplyAsync(() -> "Add Movie->not OK ");
        }
    }

    @Override
    public CompletableFuture<String> removeMovie(String info) {
        try {
            movieService.removeMovie(Integer.valueOf(info));
            return CompletableFuture.supplyAsync(() -> "Delete Movie->OK ");
        } catch (RepositoryException | ValidatorException e) {
            return CompletableFuture.supplyAsync(() -> "Delete Movie->not OK");
        }
    }

    @Override
    public CompletableFuture<String> updateMovie(String info) {
        String[] m = info.split(",");
        // Movie(String: name, genreType: genre, int: year)
        try {
            Movie movie = new Movie(Integer.valueOf(m[0]), m[1], genreType.valueOf(m[2]), Integer.valueOf(m[3]));
            movieService.updateMovie(movie);
            return CompletableFuture.supplyAsync(() -> "Update Movie->OK ");
        } catch (RepositoryException e) {
            return CompletableFuture.supplyAsync(() -> "Update Movie->not OK ");
        }
    }

    @Override
    public CompletableFuture<String> getAllMovies() {
        String movies = "";
        for (Movie movie : movieService.getAllMovies()) {
            movies += movie.toString() + "\n";
        }
        final String res = movies;
        return CompletableFuture.supplyAsync(() -> res);
    }

    @Override
    public CompletableFuture<String> filterMoviesYear(String year) {
        String movies = "";
        for (Movie movie : movieService.filter(Integer.parseInt(year))) {
            movies += movie.toString() + "\n";
        }
        final String res = movies;
        return CompletableFuture.supplyAsync(() -> res);
    }

    @Override
    public CompletableFuture<String> addClient(String info) {

        String[] cl = info.split(",");

        try {
            Client client = new Client(Integer.parseInt(cl[0]), cl[1], cl[2], cl[3], cl[4]);

            clientService.addClient(client);
            return CompletableFuture.supplyAsync(() -> "Add Client->OK ");
        } catch (RepositoryException | ValidatorException e) {
            return CompletableFuture.supplyAsync(() -> "Add Client->not OK ");
        }
    }

    @Override
    public CompletableFuture<String> getAllClients() {
        String clients = "";
        for (Client client : clientService.getAllClients()) {
            clients += client.toString() + "\n";
        }
        final String res = clients;
        return CompletableFuture.supplyAsync(() -> res);
    }

    @Override
    public CompletableFuture<String> updateClient(String info) {
        String[] cl = info.split(",");
        // cl = Long id, String firstName, String lastName, String phoneNumber, String address
        try {
            clientService.updateClient(new Client(Integer.valueOf(cl[0]), cl[1], cl[2], cl[3], cl[4]));

            return CompletableFuture.supplyAsync(() -> "Update Client->OK  ");
        } catch (RepositoryException e) {
            return CompletableFuture.supplyAsync(() -> "Update Client->not OK");
        }
    }

    @Override
    public CompletableFuture<String> deleteClient(String info){
        try {
            clientService.removeClient(Integer.valueOf(info));
            return CompletableFuture.supplyAsync(() -> "Delete Client->OK ");
        } catch (RepositoryException |ValidatorException e) {
        return CompletableFuture.supplyAsync(() -> "Delete Client->not OK");
        }
    }

    @Override
    public CompletableFuture<String> addRental(String info) {
        String[] mc = info.split(",");

        try {
            int id = Integer.parseInt(mc[0]);
            int clientId = Integer.parseInt(mc[1]);
            int movieId = Integer.parseInt(mc[2]);

            if(!(movieService.findOne(movieId).isPresent() && clientService.findOne(clientId).isPresent()))
                throw new RepositoryException("The movie/client with that id does not exist");

            MovieClient movieClient = new MovieClient(Integer.parseInt(mc[0]), Integer.parseInt(mc[1]), Integer.parseInt(mc[2]));
            rentService.addMovieClient(movieClient);
            return CompletableFuture.supplyAsync(() -> "Add Rental->OK ");
        } catch (RepositoryException | ValidatorException e) {
            return CompletableFuture.supplyAsync(() -> "Add Rental->not OK ");
        }
    }

    @Override
    public CompletableFuture<String> getAllRentals() {
        String rentals = "";
        for (MovieClient movieClient : rentService.getAllRentals()) {
            rentals += movieClient.toString() + "\n";
        }
        final String res = rentals;
        return CompletableFuture.supplyAsync(() -> res);
    }

    @Override
    public CompletableFuture<String> getRentalReport() {
        Map<String, Integer> sortedMap = new HashMap<>();
        rentService.reports().entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue()).filter(e ->movieService.findOne(e.getKey()).isPresent()).
                forEach(k->sortedMap.put(movieService.findOne(k.getKey()).get().getName(),k.getValue()));
        String nrMoviesType = "";
        System.out.println(sortedMap.entrySet());
        for(Map.Entry<String, Integer> mt : sortedMap.entrySet()){
            nrMoviesType += mt + "\n";
        }
        final String res = nrMoviesType;
        return CompletableFuture.supplyAsync(() -> res);
    }

    @Override
    public CompletableFuture<String> getNrMoviesType() {
        String nrMoviesType = "";
        for(Map.Entry<String, Integer> mt : movieService.getNrMovies().entrySet()){
            nrMoviesType += mt + "\n";
        }
        final String res = nrMoviesType;
        return CompletableFuture.supplyAsync(() -> res);
    }


}
