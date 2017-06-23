package ro.ubb.movies.core.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movies.core.model.Client;
import ro.ubb.movies.core.model.ClientMovie;
import ro.ubb.movies.core.model.ClientMovie_;
import ro.ubb.movies.core.model.Client_;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by flo on 22/05/2017.
 */
public class ClientRepositoryImpl extends CustomRepositorySupport<Client, Long> implements ClientRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(ClientRepositoryImpl.class);


    @Override
    @Transactional
    public List<Client> findAllWithMoviesSqlQuery() {
        log.trace("findAllWithMoviesSqlQuery: method entered");

        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();

        Query query = session.createSQLQuery("select distinct {c.*}, {cm.*}, {m.*}" +
                " from client c" +
                " left join client_movie cm on cm.client_id = c.id" +
                " left join movie m on m.id = cm.movie_id")
                .addEntity("c", Client.class)
                .addJoin("cm", "c.clientMovies")
                .addJoin("m", "cm.movie")
                .addEntity("c", Client.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Client> clients = query.list();

        log.trace("findAllWithMoviesSqlQuery: clients = {}", clients);

        return clients;
    }

    @Override
    @Transactional
    public List<Client> findAllWithMoviesJpql() {
        log.trace("findAllWithMoviesJpql: method entered");

        javax.persistence.Query query = getEntityManager().createQuery("select distinct c from Client c" +
                    " left join fetch c.clientMovies cm" +
                    " left join fetch cm.movie m");

        List<Client> clients = query.getResultList();

        log.trace("findAllWithMoviesJpql: clients={}", clients);

        return clients;
    }

    @Override
    public List<Client> findAllWithMoviesJpaCriteria() {
        log.trace("findAllWithMoviesJpaCriteria: method entered");

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);

        query.distinct(Boolean.TRUE);

        Root<Client> from = query.from(Client.class);

        Fetch<Client, ClientMovie> clientMovieFetch = from.fetch(Client_.clientMovies, JoinType.LEFT);
        clientMovieFetch.fetch(ClientMovie_.movie, JoinType.LEFT);

        List<Client> clients = getEntityManager().createQuery(query).getResultList();

        log.trace("findAllWithMoviesJpaCriteria: clients={}", clients);

        return clients;
    }
}
