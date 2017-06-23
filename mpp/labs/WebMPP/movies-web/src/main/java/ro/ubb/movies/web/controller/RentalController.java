package ro.ubb.movies.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.movies.core.model.Client;
import ro.ubb.movies.core.model.ClientMovie;
import ro.ubb.movies.core.service.ClientService;
import ro.ubb.movies.core.service.MovieService;
import ro.ubb.movies.web.converter.ClientMovieConverter;
import ro.ubb.movies.web.dto.ClientMovieDto;
import ro.ubb.movies.web.dto.ClientMoviesDto;

import java.util.Set;

/**
 * Created by andrapop on 2017-05-04.
 */

@RestController
public class RentalController {
    private static final Logger log = LoggerFactory.getLogger(RentalController.class);


    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMovieConverter clientMovieConverter;

    @RequestMapping(value = "/rentals/{clientId}", method = RequestMethod.GET)
    public ClientMoviesDto getClientMovies(
            @PathVariable final Long clientId) {
        log.trace("getClientMovies: clientId={}", clientId);

        Client client = clientService.findClient(clientId);

        Set<ClientMovie> clientMovies = client.getClientMovies();
        Set<ClientMovieDto> clientMovieDtos = clientMovieConverter.convertModelsToDtos(clientMovies);

        ClientMoviesDto result = new ClientMoviesDto(clientMovieDtos);

        log.trace("getClientMovies: result={}", result);

        return result;

    }
}
