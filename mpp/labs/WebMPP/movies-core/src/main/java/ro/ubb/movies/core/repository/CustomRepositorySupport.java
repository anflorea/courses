package ro.ubb.movies.core.repository;

import lombok.Getter;
import lombok.Setter;
import ro.ubb.movies.core.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by flo on 22/05/2017.
 */
@Getter
@Setter
public abstract class CustomRepositorySupport<T extends BaseEntity<ID>, ID extends Serializable> {

    @PersistenceContext
    private EntityManager entityManager;
}
