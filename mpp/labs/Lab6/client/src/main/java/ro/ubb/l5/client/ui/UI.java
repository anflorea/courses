package ro.ubb.l5.client.ui;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.l5.client.service.ClientService;
import ro.ubb.l5.common.IService;

import ro.ubb.l5.common.MovieServiceException;
import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.Movie;
import ro.ubb.l5.common.domain.MovieClient;
import ro.ubb.l5.common.domain.genreType;
import ro.ubb.l5.common.domain.validators.RepositoryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by andrapop on 2017-03-27.
 */
public class UI {
    private IService service;
    private ApplicationContext context;


    public UI(IService service) {

        context = new AnnotationConfigApplicationContext(
                "ro.ubb.l5.client" +
                        ".config");

        this.service = context.getBean(ClientService.class);
    }

    public void printMenu() {
        System.out.println("-----------MENU-----------");
        System.out.println("0. Exit");
        System.out.println("--------MOVIE CRUD--------");
        System.out.println("1. View all movies");
        System.out.println("2. Add a movie");
        System.out.println("3. Remove a movie");
        System.out.println("4. Update a movie");
        System.out.println("--------CLIENT CRUD-------");
        System.out.println("5. View all clients");
        System.out.println("6. Add a client");
        System.out.println("7. Remove a client");
        System.out.println("8. Update a client");
        System.out.println("---------FILTERS----------");
        System.out.println("9. Filter movies by year");
        System.out.println("---------RENTING----------");
        System.out.println("10. RENT a movie");
        System.out.println("11. View all rentals");
        System.out.println("---------REPORTS----------");
        System.out.println("12. Movie rental report");
        System.out.println("13. Print the number of movies for each movie type");

    }


    public void runUi() {

        Boolean stop = false;
        while (stop == false) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    stop = true;
                    break;
                case 1:
                    printAllMovies();
                    break;
                case 2:
                    addMovie();
                    break;
                case 3:
                    deleteMovie();
                    break;
                case 4:
                    updateMovie();
                    break;
                case 5:
                    printAllClients();
                    break;
                case 6:
                    addClient();
                    break;
                case 7:
                    deleteClient();
                    break;
                case 8:
                    updateClient();
                    break;
                case 9:
                    filterMovies();
                    break;
                case 10:
                    addRental();
                    break;
                case 11:
                    printAllRentals();
                    break;
                case 12:
                    movieRentalReport();
                    break;
                case 13:
                    nrMoviesType();
                    break;
            }
        }

    }


    /**
     * Method for reading an entity from the standard input
     */

    private Optional<Movie> readMovie() {
        System.out.println("Read movie {id, name, genre(comedy/action/horror/drama), year}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {

            Scanner scan1 = new Scanner(System.in);
            while (!scan1.hasNextInt()) {
                scan1.next();
            }
            int id = scan1.nextInt();


            String name = bufferRead.readLine();
            genreType genre = genreType.valueOf(bufferRead.readLine());

            Scanner scan = new Scanner(System.in);

            while (!scan.hasNextInt()) {
                scan.next();
            }
            int year = scan.nextInt();

            Movie movie = new Movie(id, name, genre, year);

            return Optional.ofNullable(movie);


        } catch (IOException | IllegalArgumentException ex) {
            ex.printStackTrace();

        }
        return Optional.empty();


    }

    private void addMovie() {
        Movie movie = readMovie().get();
        try {
            service.addMovie(movie);
        }catch (MovieServiceException e){
            System.out.println(e.getMessage());
        }

    }


    /**
     * Method for updating an entity.
     */


    private void updateMovie() {
        try {
            Movie m = readMovie().get();
            service.updateMovie(m);
        }catch (MovieServiceException e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * Method for deleting an entity by its Id.
     */


    private void deleteMovie() {
        try {
            System.out.println("Give the Id: ");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            service.removeMovie(num);
        }catch (MovieServiceException e){
            System.out.println(e.getMessage());
        }

    }


    /**
     * Method for printing all entities existent.
     */


    private void printAllMovies() {
        //service.getAllMovies().thenAccept(System.out::println);
        service.getAllMovies().forEach(System.out::println);
    }


    /**
     * Method for filtering movies by its production year.
     */


    private void filterMovies() {
        System.out.println("Give year: ");
        Scanner scan = new Scanner(System.in);
        int year = scan.nextInt();
        service.filterMovies(year).forEach(System.out::println);

    }


    /**
     * Method for reading an entity from the standard input
     */
    private Client readClient() {
        System.out.println("Read client {id, firstName, lastName, phoneNr, address}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        try {

            Scanner scan1 = new Scanner(System.in);
            while (!scan1.hasNextInt()) {
                scan1.next();
            }
            int id = scan1.nextInt();

            //String id = bufferRead.readLine();
            String firstName = bufferRead.readLine();
            String lastName = bufferRead.readLine();
            String phoneNr = bufferRead.readLine();
            String address = bufferRead.readLine();

            return new Client(id,firstName,lastName,phoneNr,address);
            //System.out.println("The client was successfully added!");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void addClient(){
        try {
            service.addClient(readClient());
        }catch (MovieServiceException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method for printing all entities existent.
     */


    private void printAllClients() {
        service.getAllClients().forEach(System.out::println);
    }

    /**
     * Method for updating an entity.
     */


    private void updateClient() {
        try {
            service.updateClient(readClient());
        }catch (MovieServiceException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method for deleting an entity by its Id.
     */


    private void deleteClient() {
        try {
            System.out.println("Give the Id: ");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            service.removeClient(num);
        }catch (MovieServiceException e){
            System.out.println(e.getMessage());
        }

    }


    /**
     * Method for reading and adding a rental entity.
     *
     */


    private MovieClient readClientMovie(){

            System.out.println("ID(int): ");
            Scanner scan = new Scanner(System.in);
            while (!scan.hasNextInt()) {
                scan.next();
            }
            int id = scan.nextInt();

            System.out.println("Give movie id(int):");

            while (!scan.hasNextInt()) {
                scan.next();
            }
            int id2 = scan.nextInt();

            System.out.println("Give client id(int):");
            while (!scan.hasNextInt()) {
                scan.next();
            }
            int id1 = scan.nextInt();

            return new MovieClient(id,id1,id2);
    }

    public void addRental(){
        try {
            service.addRental(readClientMovie());
        }catch (MovieServiceException e){
            System.out.println(e.getMessage());
        }
    }

    private void printAllRentals() {
            service.getAllRentals().forEach(System.out::println);

    }

    private void movieRentalReport(){
        service.getRentalReport().entrySet().forEach(System.out::println);

    }

    private void nrMoviesType(){
        service.getNrMoviesType().entrySet().forEach(System.out::println);

    }



}




