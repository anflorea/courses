package ro.ubb.movies.core.service;

import ro.ubb.movies.core.model.Movie;


import java.util.List;

/**
 * Created by andrapop on 2017-04-10.
 */
public interface MovieService {
    List<Movie> findAll();

    Movie updateMovie(Long movieId, String name, String genre, Integer year);

    Movie createMovie(String name, String genre, Integer year);

    void deleteMovie(Long movieId);
}
