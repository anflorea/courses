package ro.ubb.l5.server.repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.ubb.l5.common.*;

import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.validators.Validator;
import ro.ubb.l5.common.domain.validators.ValidatorException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrapop on 2017-03-19.
 */

public class ClientDBRepository implements IRepository<Integer, Client> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Validator<Client> validator;


    public ClientDBRepository(Validator<Client> clientValidator){
        this.validator = clientValidator;

    }

    @Override
    public Optional<Client> findOne(Integer integer) {
        List<Client> clients = new ArrayList<>();
        findAll().spliterator().forEachRemaining(clients::add);
        return clients.stream().filter(m -> m.getId() == integer).findAny();
    }

    @Override
    public Iterable<Client> findAll(){
        String sql = "select * from clients";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Client.class));

    }

    @Override
    public Optional<Client> save(Client c) throws ValidatorException {
        if (findOne(c.getId()).isPresent())
            throw new MovieServiceException("Client with that id exists");
        validator.validate(c);
        String sql = "insert into clients (id, firstname, lastname, phonenr, address) values (?,?,?,?,?)";
        jdbcTemplate.update(sql, c.getId(), c.getFirstName(), c.getLastName(), c.getPhoneNr(), c.getAddress());
        return Optional.empty();
    }

    @Override
    public Optional<Client> delete(Integer id) {
        if (!findOne(id).isPresent())
            throw new MovieServiceException("Client with that id does not exist");
        String sql = "delete from clients where id=?";
        jdbcTemplate.update(sql, id);
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client c) throws ValidatorException {
        if (!findOne(c.getId()).isPresent())
            throw new MovieServiceException("Client with that id does not exist");
        String sql = "update clients set firstname=?, lastname=?, phonenr=?, address=? where id=?";
        jdbcTemplate.update(sql, c.getFirstName(), c.getLastName(), c.getPhoneNr(), c.getAddress(), c.getId());
        return Optional.empty();
    }


}

