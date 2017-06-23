package ro.ubb.movies.core.repository;

import ro.ubb.movies.core.model.Client;

import java.util.List;

/**
 * Created by flo on 22/05/2017.
 */
public interface ClientRepositoryCustom {
    List<Client> findAllWithMoviesSqlQuery();

    List<Client> findAllWithMoviesJpql();

    List<Client> findAllWithMoviesJpaCriteria();
}
