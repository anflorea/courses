package ro.ubb.l5.server.service;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ro.ubb.l5.common.IService;
import ro.ubb.l5.common.MovieServiceException;
import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.Movie;
import ro.ubb.l5.common.domain.MovieClient;
import ro.ubb.l5.common.domain.genreType;

import ro.ubb.l5.server.repository.ClientDBRepository;
import ro.ubb.l5.server.repository.MovieDBRepository;
import ro.ubb.l5.server.repository.RentDBRepository;


import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by andrapop on 2017-03-30.
 */
public class ServerService implements IService {


    @Autowired
    ClientDBRepository clientDBRepository;

    @Autowired
    MovieDBRepository movieDBRepository;

    @Autowired
    RentDBRepository rentDBRepository;

    @Override
    public Iterable<Movie> getAllMovies() {
        return movieDBRepository.findAll();
    }

    @Override
    public void addMovie(Movie m) throws MovieServiceException {
        movieDBRepository.save(m);
    }

    @Override
    public void removeMovie(int id) {
        movieDBRepository.delete(id);
    }

    @Override
    public void updateMovie(Movie m) {
        movieDBRepository.update(m);
    }

    @Override
    public List<Movie> filterMovies(int year) {
        List<Movie> movies = new ArrayList<>();
        movieDBRepository.findAll().spliterator().forEachRemaining(movies::add);
        return movies.stream().filter(m -> m.getYear()==year).collect(Collectors.toList());
    }

    @Override
    public Iterable<Client> getAllClients() {
        return clientDBRepository.findAll();
    }

    @Override
    public void addClient(Client c) {
        clientDBRepository.save(c);
    }

    @Override
    public void removeClient(int id) {
        clientDBRepository.delete(id);
    }

    @Override
    public void updateClient(Client c) {
        clientDBRepository.update(c);

    }

    @Override
    public void addRental(MovieClient mc) {
        if(!movieDBRepository.findOne(mc.getClientID()).isPresent() || !movieDBRepository.findOne(mc.getMovieID()).isPresent())
            throw new MovieServiceException("Movie/Client with that id does not exist");

        rentDBRepository.save(mc);
    }

    @Override
    public Iterable<MovieClient> getAllRentals() {
        return rentDBRepository.findAll();
    }

    @Override
    public Map<String, Integer> getRentalReport() {
        Map<Integer, Integer> reports= rentDBRepository.report();
        Map<String, Integer> sortedMap =  new HashMap<>();

        reports.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue()).filter(e ->movieDBRepository.
                findOne(e.getKey()).isPresent()).
                forEach(k->sortedMap.put(movieDBRepository.findOne(k.getKey()).get().getName(),k.getValue()));
        return sortedMap;
    }

    @Override
    public Map<String, Integer> getNrMoviesType() {
        Map<String,Integer> nrMoviesType =  new HashMap<>();
        List<String> availableTypes = new ArrayList<>();
        for (genreType g: genreType.values()) {
            availableTypes.add(g.name());

        }

        List<Movie> movies = new ArrayList<>();
        movieDBRepository.findAll().spliterator().forEachRemaining(movies::add);

        availableTypes.stream().forEach(type -> nrMoviesType.put(type,
                (int)movies.stream().filter(m -> m.getGenre().name().equals(type)).count()));
        return nrMoviesType;
    }


}
