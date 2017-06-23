package ro.ubb.movies.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movies.core.model.Movie;
import ro.ubb.movies.core.repository.MovieRepository;


import java.util.*;


@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public List<Movie> findAll() {
        log.trace("findAll");
        List<Movie> movies = movieRepository.findAll();
        log.trace("findAll: movies={}", movies);

        return movies;
    }

    @Override
    @Transactional
    public Movie updateMovie(Long movieId, String name, String genre, Integer year) {
        log.trace("updateMovie: movieId={}, name={}, genre={}, year={}",
                movieId, name, genre, year);

        Movie movie = movieRepository.findOne(movieId);
        movie.setName(name);
        movie.setGenre(genre);
        movie.setYear(year);

        log.trace("updateMovie: movie={}", movie);

        return movie;
    }

    @Override
    public Movie createMovie(String name, String genre, Integer year) {
        log.trace("createMovie: name={}, genre={}, year={}",
                name, genre, year);

        Movie movie = Movie.builder()
                .name(name)
                .genre(genre)
                .year(year).build();
        movie = movieRepository.save(movie);
        System.out.println("aci");
        log.trace("createMovie: movie={}", movie);

        return movie;
    }

    @Override
    public void deleteMovie(Long movieId) {
        log.trace("deleteMovie: movieId={}", movieId);

        movieRepository.delete(movieId);

        log.trace("deleteMovie - method end");
    }
}
