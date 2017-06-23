package ro.ubb.l5.client.service;
import ro.ubb.l5.client.tcp.TcpClient;
import ro.ubb.l5.common.*;
import ro.ubb.l5.common.domain.Movie;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Created by andrapop on 2017-03-30.
 */
public class ClientService implements IService {

    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ClientService(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    public CompletableFuture<String> addMovie(String info) {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.ADD_MOVIE, info);
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });

        return result;
    }

    public CompletableFuture<String> removeMovie(String info) {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.DELETE_MOVIE, info);
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;

    }

    public CompletableFuture<String> updateMovie(String info) {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.UPDATE_MOVIE, info);
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;
    }

    public CompletableFuture<String> getAllMovies() {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.GET_ALL_MOVIES, "");
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });

        return result;
    }

    public CompletableFuture<String> filterMoviesYear(String info) {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.FILTER_MOVIES, info);
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });

        return result;
    }

    public CompletableFuture<String> addClient(String info) {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.ADD_CLIENT, info);
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });

        return result;
    }

    public CompletableFuture<String> getAllClients() {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.GET_ALL_CLIENTS, "");
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;
    }

    public CompletableFuture<String> updateClient(String info) {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.UPDATE_CLIENT, info);
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;
    }

    public CompletableFuture<String> deleteClient(String info) {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.DELETE_CLIENT, info);
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;
    }

    @Override
    public CompletableFuture<String> addRental(String info) {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.ADD_RENTAL, info);
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;
    }

    @Override
    public CompletableFuture<String> getAllRentals() {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.GET_ALL_RENTALS, "");
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;
    }

    @Override
    public CompletableFuture<String> getRentalReport() {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.GET_REPORT, "");
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;
    }

    @Override
    public CompletableFuture<String> getNrMoviesType() {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            Message request = new Message(IService.GET_MOVIES_TYPE, "");
            Message response = tcpClient.sendAndReceive(request);
            String body = response.body();
            return body;
        });
        return result;
    }


}
