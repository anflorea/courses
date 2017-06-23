package ro.ubb.l5.server;


import ro.ubb.l5.common.IService;
import ro.ubb.l5.common.Message;

import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.Movie;
import ro.ubb.l5.common.domain.MovieClient;
import ro.ubb.l5.common.domain.validators.*;
import ro.ubb.l5.server.repository.ClientDBRepository;
import ro.ubb.l5.server.repository.IRepository;
import ro.ubb.l5.server.repository.MovieDBRepository;
import ro.ubb.l5.server.repository.RentDBRepository;
import ro.ubb.l5.server.server.ServerService;
import ro.ubb.l5.server.service.ClientService;
import ro.ubb.l5.server.service.MovieClientService;
import ro.ubb.l5.server.service.MovieService;
import ro.ubb.l5.server.tcp.TcpServer;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author radu.
 */
public class ServerApp {
    public static void main(String[] args) {
        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<MovieClient> movieClientValidator = new MovieClientValidator();

        IRepository<Integer, Movie> movieDBRepository = new MovieDBRepository(movieValidator);
        IRepository<Integer, Client> clientDBRepository = new ClientDBRepository(clientValidator);
        IRepository<Integer, MovieClient> rentDBRepository = new RentDBRepository(movieClientValidator);

        MovieService movieService = new MovieService(movieDBRepository);
        ClientService clientService = new ClientService(clientDBRepository);
        MovieClientService rentService = new MovieClientService(rentDBRepository);

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        IService service = new ServerService(executorService, clientService, movieService, rentService);
        TcpServer tcpServer = new TcpServer(executorService, IService.SERVICE_HOST, IService.SERVICE_PORT);

        tcpServer.addHandler(IService.ADD_MOVIE, (request) -> {
            try {
                Future<String> result = service.addMovie(request.body());
                try {
                    return new Message(Message.OK, result.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }catch(RepositoryException e){
                return new Message(Message.ERROR, e.getMessage());
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.UPDATE_MOVIE, (request) -> {
            Future<String> result = service.updateMovie(request.body());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.DELETE_MOVIE, (request) -> {
            Future<String> result = service.removeMovie(request.body());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.GET_ALL_MOVIES, (request) -> {
            Future<String> result = service.getAllMovies();
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.FILTER_MOVIES, (request) -> {
            Future<String> result = service.filterMoviesYear(request.body());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.ADD_CLIENT, (request) -> {
            Future<String> result = service.addClient(request.body());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.GET_ALL_CLIENTS, (request) -> {
            Future<String> result = service.getAllClients();
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.UPDATE_CLIENT, (request) -> {
            Future<String> result = service.updateClient(request.body());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.DELETE_CLIENT, (request) -> {
            Future<String> result = service.deleteClient(request.body());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.ADD_RENTAL, (request) -> {
            Future<String> result = service.addRental(request.body());
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.GET_ALL_RENTALS, (request) -> {
            Future<String> result = service.getAllRentals();
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.GET_MOVIES_TYPE, (request) -> {
            Future<String> result = service.getNrMoviesType();
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });

        tcpServer.addHandler(IService.GET_REPORT, (request) -> {
            Future<String> result = service.getRentalReport();
            try {
                return new Message(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(Message.ERROR, "");
        });
        tcpServer.startServer();
    }
}
