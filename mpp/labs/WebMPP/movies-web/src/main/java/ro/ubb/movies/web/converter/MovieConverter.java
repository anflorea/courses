package ro.ubb.movies.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ro.ubb.movies.core.model.Movie;
import ro.ubb.movies.web.dto.MovieDto;

/**
 * Created by andrapop on 2017-04-24.
 */
@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {

    private static final Logger log = LoggerFactory.getLogger(MovieConverter.class);

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto movieDto = MovieDto.builder().name(movie.getName())
                .genre(movie.getGenre())
                .year(movie.getYear())
                .build();
        movieDto.setId(movie.getId());
        return movieDto;
    }
}
