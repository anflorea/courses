package main.java.ro.ubb.movies.ui;


import main.java.ro.ubb.movies.domain.Client;
import main.java.ro.ubb.movies.domain.Movie;
import main.java.ro.ubb.movies.domain.MovieClient;
import main.java.ro.ubb.movies.domain.genreType;
import main.java.ro.ubb.movies.domain.validators.RepositoryException;
import main.java.ro.ubb.movies.domain.validators.ValidatorException;
import main.java.ro.ubb.movies.service.ClientService;
import main.java.ro.ubb.movies.service.MovieClientService;
import main.java.ro.ubb.movies.service.MovieService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by andrapop on 2017-03-06.
 */
public class Console {

    private MovieService movieService;
    private ClientService clientService;
    private MovieClientService movieClientService;

    public Console(MovieService movieService, ClientService clientService, MovieClientService movieClientService){

        this.movieService = movieService;
        this.clientService = clientService;
        this.movieClientService = movieClientService;
    }

    private void printAllMovies(){
        Set<Movie> movies = movieService.getAllMovies();
        movies.stream().forEach(System.out::println);

    }

    private void addMovies(){

            Movie movie = readMovie();
            try {
                movieService.addMovie(movie);

            }
            catch(RepositoryException e){
                System.out.println(e.getMessage());
            }
            catch(ValidatorException v) {
                System.out.println(v.getMessage());

            }
    }

    private void removeMovie(){
        System.out.println("Give the id of the movie: ");
        Scanner scan = new Scanner(System.in);
        while(!scan.hasNextInt()) {
            scan.next();
        }
        int id = scan.nextInt();

        try{
            movieService.removeMovie(id);
        }
        catch(RepositoryException e){
            System.out.println(e.getMessage());
        }
        catch(ValidatorException v){
            System.out.println(v.getMessage());
        }
    }

    private Movie readMovie(){
        System.out.println("Read movie {id, name, genre(comedy/action/horror/drama), year}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Scanner scan1 = new Scanner(System.in);
            while(!scan1.hasNextInt()) {
                scan1.next();
            }
            int id = scan1.nextInt();

            String name = bufferRead.readLine();
            genreType genre = genreType.valueOf(bufferRead.readLine());
            Scanner scan = new Scanner(System.in);

            while(!scan.hasNextInt()) {
                scan.next();
            }
            int year = scan.nextInt();


            Movie movie = new Movie(id,name, genre, year);

            return movie;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return null;
    }

    private void updateMovie(){

        System.out.println("Give new movie data: ");
        Movie movie = readMovie();

        try{
            movieService.updateMovie(movie);
        }catch(RepositoryException e){
            System.out.println(e.getMessage());
        }
        catch (ValidatorException v){
            System.out.println(v.getMessage());
        }
    }

    private void filterMovies(){
        System.out.println("Give year: ");
        Scanner scan = new Scanner(System.in);
        while(!scan.hasNextInt()) {
            scan.next();
        }
        int year = scan.nextInt();

        Set<Movie> filtered = movieService.filter(year);
        filtered.stream().forEach(System.out::println);
    }

    private void printAllClients(){
        Set<Client> clients = clientService.getAllClients();
        clients.stream().forEach(System.out::println);
    }

    private void addClient(){
        Client client= readClient();
        try{
            clientService.addClient(client);
        }catch(RepositoryException e){
            System.out.println(e.getMessage());
        }
        catch(ValidatorException v){
            System.out.println(v.getMessage());
        }
    }

    private void removeClient(){
        System.out.println("Give the id of the client: ");
        Scanner scan = new Scanner(System.in);
        while(!scan.hasNextInt()) {
            scan.next();
        }
        int id = scan.nextInt();

        try{
            clientService.removeClient(id);
        }catch (RepositoryException e){
            System.out.println(e.getMessage());
        }
        catch(ValidatorException v){
            System.out.println(v.getMessage());
        }
    }

    private Client readClient(){
        System.out.println("Read client {id, firstName, lastName, phoneNr, address}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        try{
            Scanner scan1 = new Scanner(System.in);
            while(!scan1.hasNextInt()) {
                scan1.next();
            }
            int id = scan1.nextInt();
            String firstName = bufferRead.readLine();
            String lastName = bufferRead.readLine();
            String phoneNr = bufferRead.readLine();
            String address = bufferRead.readLine();

            Client client= new Client(id,firstName, lastName, phoneNr, address);
            return client;



        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        return null;

    }
    private void updateClient(){

        System.out.println("Give new client data: ");
        Client client = readClient();

        try{
            clientService.updateClient(client);
        }catch (RepositoryException e){
            System.out.println(e.getMessage());
        }
        catch (ValidatorException v){
            System.out.println(v.getMessage());
        }
    }
    private MovieClient readClientMovie(){
        System.out.println("ID(int): ");
        Scanner scan = new Scanner(System.in);
        while(!scan.hasNextInt()) {
            scan.next();
        }
        int id = scan.nextInt();

        System.out.println("Give movie id(int):");

        while(!scan.hasNextInt()) {
            scan.next();
        }
        int id2 = scan.nextInt();

        System.out.println("Give client id(int):");
        while(!scan.hasNextInt()) {
            scan.next();
        }
        int id1 = scan.nextInt();

        MovieClient mc = new MovieClient(id,id1,id2);
        return mc;

    }
    private void rentMovie(){
        System.out.println("Give client and movie data:");
        MovieClient mc = readClientMovie();
        if(!(movieService.findOne(mc.getMovieID()).isPresent() && clientService.findOne(mc.getClientID()).isPresent())) {
            System.out.println("The movie/client with that id does not exist");
            rentMovie();
        }
        try{
            movieClientService.addMovieClient(mc);
        }catch (ValidatorException v){
            System.out.println(v.getMessage());
        }
    }
    private void printRentedMovies(){
        Set<MovieClient> mc = movieClientService.getAllRentals();
        mc.stream().forEach(System.out::println);
    }

    private void mostRented(){

        Map<Integer, Integer> sortedMap = new HashMap<>();
        movieClientService.reports().entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).filter(e ->movieService.findOne(e.getKey()).isPresent()).
                forEach(k->System.out.println(movieService.findOne(k.getKey()).get().getName()+" : "+k.getValue()+" rental(s)"));
    }

    private void printMovieType(){
        movieService.getNrMovies().entrySet().stream().forEach(System.out::println);
    }


    public void runConsole(){
        Boolean stop = false;
        while(stop == false) {
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

            Scanner scanner = new Scanner(System.in);
            while(!scanner.hasNextInt()) {
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
                    addMovies();
                    break;
                case 3:
                    removeMovie();
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
                    removeClient();
                    break;
                case 8:
                    updateClient();
                    break;
                case 9:
                    filterMovies();
                    break;
                case 10:
                    rentMovie();
                    break;
                case 11:
                    printRentedMovies();
                    break;
                case 12:
                    mostRented();
                    break;
                case 13:
                    printMovieType();
                    break;
                default:
                    break;
            }

        }
    }

}
