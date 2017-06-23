package main.java.ro.ubb.movies.repository;


import main.java.ro.ubb.movies.domain.BaseEntity;
import main.java.ro.ubb.movies.domain.validators.Validator;
import main.java.ro.ubb.movies.domain.validators.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by andrapop on 2017-03-06.
 */
public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements IRepository<ID, T> {
    private Map<ID, T> entities;
    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator){
        this.validator = validator;
        this.entities = new HashMap<ID, T>();
    }

    @Override
    public Optional<T> findOne(ID id) {
        if (id == null)
            throw new IllegalArgumentException("Id must not be null");
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        Set<T> all = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return all;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null");
        validator.validate(entity);
        return Optional.ofNullable(entities.put(entity.getId(),entity));
    }

    @Override
    public Optional<T> delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("Id must not be null");
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if(entity == null)
            throw new IllegalArgumentException("Entity must not be null");
        validator.validate(entity);

        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));

    }

}
