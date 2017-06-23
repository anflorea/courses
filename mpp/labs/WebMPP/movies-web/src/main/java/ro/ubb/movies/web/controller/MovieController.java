package ro.ubb.movies.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import ro.ubb.movies.core.model.Movie;

import ro.ubb.movies.core.service.MovieService;
import ro.ubb.movies.web.converter.MovieConverter;
import ro.ubb.movies.web.dto.EmptyJsonResponse;
import ro.ubb.movies.web.dto.MovieDto;
import ro.ubb.movies.web.dto.MoviesDto;


import javax.validation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    private Validator validator;

    public MovieController() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public MoviesDto getMovies() {
        log.trace("getMovies");

        List<Movie> movies = movieService.findAll();

        log.trace("getMovies: movies={}", movies);

        return new MoviesDto(movieConverter.convertModelsToDtos(movies));
    }

    @RequestMapping(value = "/movies/{movieId}", method = RequestMethod.PUT)
    public Map<String, MovieDto> updateMovie(
            @PathVariable final Long movieId,
            @RequestBody final Map<String, MovieDto> movieDtoMap) {
       log.trace("updateMovie: movieId={}, movieDtoMap={}", movieId, movieDtoMap);

        MovieDto movieDto = movieDtoMap.get("movie");
        Movie movie = movieService.updateMovie(movieId, movieDto.getName(),
                movieDto.getGenre(), movieDto.getYear());

        Map<String, MovieDto> result = new HashMap<>();
        result.put("movie", movieConverter.convertModelToDto(movie));

        log.trace("updateMovie: result={}", result);

        return result;
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public Map<String, MovieDto> createMovie(
            @RequestBody final Map<String, MovieDto> movieDtoMap, BindingResult bindingResult) {
        log.trace("yeeeeee");
        log.trace("createMovie: movieDtoMap={}", movieDtoMap);

        MovieDto movieDto = movieDtoMap.get("movie");

        Set<ConstraintViolation<MovieDto>> violations = validator.validate(movieDto);

        MovieDto errorDto = new MovieDto();

        for (ConstraintViolation<MovieDto> violation : violations)
        {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError

            bindingResult.addError(new FieldError("client", propertyPath, message));
            switch (propertyPath) {
                case "name":
                    errorDto.setName(message);
                    break;
                case "genre":
                    errorDto.setGenre(message);
                    break;
            }

        }

        if (bindingResult.hasErrors()) {
            Map<String, MovieDto> result = new HashMap<>();
            result.put("error", errorDto);
            return result;
        }


        Movie movie = movieService.createMovie(
                movieDto.getName(), movieDto.getGenre(), movieDto.getYear());

        Map<String, MovieDto> result = new HashMap<>();
        result.put("movie", movieConverter.convertModelToDto(movie));

        log.trace("updateMovie: result={}", result);

        return result;
    }

    @RequestMapping(value = "movies/{movieId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteMovie(@PathVariable final Long movieId) {
        log.trace("deleteMovie: movieId={}", movieId);

        movieService.deleteMovie(movieId);

        log.trace("deleteMovie - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
