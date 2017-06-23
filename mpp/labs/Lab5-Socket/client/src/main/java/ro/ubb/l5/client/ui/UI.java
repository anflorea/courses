package ro.ubb.l5.client.ui;


import ro.ubb.l5.common.IService;

import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.Movie;
import ro.ubb.l5.common.domain.genreType;
import ro.ubb.l5.common.domain.validators.RepositoryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by andrapop on 2017-03-27.
 */
public class UI {
    private IService service;

    public UI(IService service) {
        this.service = service;
    }

    public void printMenu() {
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
        // TODO show menu
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
                    readMovie();
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
                    readClient();
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
                    readClientMovie();
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
     * Method for reading an entity from the standard input and adding it.
     */

    private void readMovie() {
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

            final String info = id + "," + name + "," + genre.name() + "," + year;

            service.addMovie(info).thenAccept(System.out::println);



        } catch (IOException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Method for updating an entity.
     *
     */

    private void updateMovie() {
        System.out.println("Read movie {id, name, genre(comedy/action/horror/drama), year}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {

            Scanner scan1 = new Scanner(System.in);
            while (!scan1.hasNextInt()) {
                scan1.next();
            }
            int id = scan1.nextInt();

            //String id = bufferRead.readLine();

            String name = bufferRead.readLine();
            genreType genre = genreType.valueOf(bufferRead.readLine());

            Scanner scan = new Scanner(System.in);

            while (!scan.hasNextInt()) {
                scan.next();
            }
            int year = scan.nextInt();

            //String year = bufferRead.readLine();
            final String info = id + "," + name + "," + genre.name() + "," + year;
            service.updateMovie(info).thenAccept(System.out::println);


        } catch (IOException ex) {
            System.out.println("Catched error: "+ex.getMessage());
        }
    }


    /**
     * Method for deleting an entity by its Id.
     *
     */

    private void deleteMovie (){
        System.out.println("Give the Id: ");
        Scanner in = new Scanner(System.in);
        final String num = in.next();
        service.removeMovie(num).thenAccept(System.out::println);

    }

    /**
     * Method for printing all entities existent.
     */

    private void printAllMovies() {
        service.getAllMovies().thenAccept(System.out::println);
    }
    /**
     * Method for filtering movies by its production year.
     *
     */

    private void filterMovies(){
        System.out.println("Give year: ");
        Scanner scan = new Scanner(System.in);
        String year = scan.nextLine();
        service.filterMoviesYear(year).thenAccept(System.out::println);

    }
    /**
     * Method for reading an entity from the standard input and adding it.
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

            final String info = id + "," + firstName + "," + lastName + "," + phoneNr + "," + address;
            service.addClient(info).thenAccept(System.out::println);

            //System.out.println("The client was successfully added!");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Method for printing all entities existent.
     */

    private void printAllClients() {
        service.getAllClients().thenAccept(System.out::println);
    }

    /**
     * Method for updating an entity.
     *
     */

    private void updateClient() {
        System.out.println("Read client {id, firstName, lastName, phoneNr, address}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String id = bufferRead.readLine();
            String firstName = bufferRead.readLine();
            String lastName = bufferRead.readLine();
            String phoneNr = bufferRead.readLine();
            String address =bufferRead.readLine();

            final String info = id + "," + firstName + "," + lastName + "," + phoneNr + "," + address;
            service.updateClient(info).thenAccept(System.out::println);

        } catch (IOException ex) {
            System.out.println("Catched error: "+ex.getMessage());
        }
    }

    /**
     * Method for deleting an entity by its Id.
     *
     */

    private void deleteClient (){
        System.out.println("Give the Id: ");
        Scanner in = new Scanner(System.in);
        final String num = in.next();
        service.deleteClient(num).thenAccept(System.out::println);

    }
    /**
     * Method for reading and adding a rental entity.
     *
     */

    private void readClientMovie(){

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

            final String info = id + "," + id1 + "," + id2;
            service.addRental(info).thenAccept(System.out::println);
    }

    private void printAllRentals() {
            service.getAllRentals().thenAccept(System.out::println);

    }

    private void movieRentalReport(){
        service.getRentalReport().thenAccept(System.out::println);

    }

    private void nrMoviesType(){
        service.getNrMoviesType().thenAccept(System.out::println);

    }


}



