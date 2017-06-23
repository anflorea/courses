package ro.ubb.movies.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.movies.core.model.Client;

import java.util.List;

/**
 * Created by andrapop on 2017-04-21.
 */
public interface ClientRepository extends MovieRentalRepository<Client, Long>, ClientRepositoryCustom {

    @Query("select distinct c from Client c")
    @EntityGraph(value = "clientWithMovies", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithMoviesGraph();

    @Query("select distinct c from Client c where c.id=?1")
    @EntityGraph(value = "clientWithMovies", type = EntityGraph.EntityGraphType.LOAD)
    Client findOneWithMovies(Long clientId);
}
