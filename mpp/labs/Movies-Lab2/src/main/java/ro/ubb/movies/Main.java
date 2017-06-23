package main.java.ro.ubb.movies;

import main.java.ro.ubb.movies.domain.Client;
import main.java.ro.ubb.movies.domain.Movie;
import main.java.ro.ubb.movies.domain.MovieClient;
import main.java.ro.ubb.movies.domain.validators.ClientValidator;
import main.java.ro.ubb.movies.domain.validators.MovieClientValidator;
import main.java.ro.ubb.movies.domain.validators.MovieValidator;
import main.java.ro.ubb.movies.domain.validators.Validator;
import main.java.ro.ubb.movies.repository.*;
import main.java.ro.ubb.movies.service.ClientService;
import main.java.ro.ubb.movies.service.MovieClientService;
import main.java.ro.ubb.movies.service.MovieService;
import main.java.ro.ubb.movies.ui.Console;

import java.io.File;
import java.io.IOException;

/**
 * Created by andrapop on 2017-03-06.
 */
public class Main {
    public static void main(String[] args)  {
        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<MovieClient> movieClientValidator = new MovieClientValidator();

        //IN MEMORY REPOSITORY

        /*
        IRepository<Integer, Movie> movieRepository = new InMemoryRepository<>(movieValidator);
        IRepository<Integer, Client> clientRepository = new InMemoryRepository<>(clientValidator);
        IRepository<Integer, MovieClient> movieClientRepository = new InMemoryRepository<>(movieClientValidator);
        MovieService movieService = new MovieService(movieRepository);
        ClientService clientService = new ClientService(clientRepository);
        MovieClientService movieClientService = new MovieClientService(movieClientRepository);
        Console console = new Console(movieService, clientService, movieClientService);
        console.runConsole();
        */


        //IN FILE REPOSITORY



        try {
            System.out.println(new File(".").getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        IRepository<Integer, Movie> movieFileRepository = new MovieFileRepository(movieValidator, "./data/movies.txt");
        IRepository<Integer, Client> clientFileRepository = new ClientFileRepository(clientValidator, "./data/clients.txt");

        IRepository<Integer, MovieClient> movieClientRepository = new RentFileRepository(movieClientValidator, "./data/rentals.txt");
        MovieService movieService = new MovieService(movieFileRepository);
        ClientService clientService = new ClientService(clientFileRepository);
        MovieClientService movieClientService = new MovieClientService(movieClientRepository);
        Console console = new Console(movieService, clientService, movieClientService);
        console.runConsole();





        //IN XML REPOSITORY
        /*

        try {
            System.out.println(new File(".").getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        IRepository<Integer, Movie> movieXmlRepository = new MovieXmlRepository(movieValidator,"./data/movies.xml");
        IRepository<Integer, Client> clientXmlRepository = new ClientXmlRepository(clientValidator,"./data/clients.xml");

        IRepository<Integer, MovieClient> rentXmlRepository = new RentXmlRepository(movieClientValidator, "./data/rentals.xml");
        MovieService movieService = new MovieService(movieXmlRepository);
        ClientService clientService = new ClientService(clientXmlRepository);
        MovieClientService movieClientService = new MovieClientService(rentXmlRepository);
        Console console = new Console(movieService, clientService, movieClientService);
        console.runConsole();
        */


        /*

        //IN DB REPOSITORY


        IRepository<Integer, Movie> movieDBRepository = new MovieDBRepository(movieValidator);
        IRepository<Integer, Client> clientDBRepository = new ClientDBRepository(clientValidator);

        IRepository<Integer, MovieClient> rentDBRepository = new RentDBRepository(movieClientValidator);
        MovieService movieService = new MovieService(movieDBRepository);
        ClientService clientService = new ClientService(clientDBRepository);
        MovieClientService movieClientService = new MovieClientService(rentDBRepository);
        Console console = new Console(movieService, clientService, movieClientService);
        console.runConsole();
        */


    }




}
