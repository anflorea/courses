package ro.ubb.l5.server.service;



import ro.ubb.l5.common.domain.Movie;
import ro.ubb.l5.common.domain.genreType;
import ro.ubb.l5.common.domain.validators.RepositoryException;
import ro.ubb.l5.common.domain.validators.ValidatorException;
import ro.ubb.l5.server.repository.IRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by andrapop on 2017-03-06.
 */
public class MovieService {
    private IRepository<Integer, Movie> repository;



    public MovieService(IRepository<Integer,Movie> repository){
        this.repository = repository;

    }

    public Optional<Movie> findOne(int id){
        return repository.findOne(id);
    }

    public void addMovie(Movie movie) throws ValidatorException, RepositoryException {
        if(repository.findOne(movie.getId()).isPresent())
            throw new RepositoryException("A movie with that id exists, can't add!");
        repository.save(movie);
    }

    public void removeMovie(int id) throws ValidatorException, RepositoryException{

        if(!repository.findOne(id).isPresent())
            throw new RepositoryException("The movie does not exist, can't remove!");
        repository.delete(id);
    }

    public void updateMovie(Movie movie) throws ValidatorException, RepositoryException{

        if(!repository.findOne(movie.getId()).isPresent())
            throw new RepositoryException("The movie does not exist, can't update!");
        repository.update(movie);
    }

    public Set<Movie> getAllMovies(){
        Iterable<Movie> movies = repository.findAll();
        return StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Movie> filter(int year){
        return getAllMovies().stream().filter(m -> m.getYear()==year).collect(Collectors.toSet());
    }

    public Map<String,Integer> getNrMovies(){
       Map<String,Integer> nrMoviesType =  new HashMap<>();
       List<String> availableTypes = new ArrayList<>();
        for (genreType g: genreType.values()
             ) {
            availableTypes.add(g.name());

        }

       availableTypes.stream().forEach(type -> nrMoviesType.put(type,
               (int)getAllMovies().stream().filter(m -> m.getGenre().name().equals(type)).count()));
        return nrMoviesType;
    }





}
