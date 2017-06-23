package ro.ubb.l5.server.repository;





import com.sun.org.apache.bcel.internal.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ro.ubb.l5.common.MovieServiceException;
import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.Movie;
import ro.ubb.l5.common.domain.validators.Validator;
import ro.ubb.l5.common.domain.validators.ValidatorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrapop on 2017-03-19.
 */


public class MovieDBRepository implements IRepository<Integer, Movie> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Validator<Movie> validator;


    public MovieDBRepository(Validator<Movie> movieValidator){
        this.validator = movieValidator;

    }


    @Override
    public Optional<Movie> findOne(Integer integer) {
        List<Movie> movies = new ArrayList<>();
        findAll().spliterator().forEachRemaining(movies::add);
        return movies.stream().filter(m -> m.getId() == integer).findAny();
    }

    @Override
    public Iterable<Movie> findAll() {

        String sql = "select * from movies";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Movie.class));
    }

    @Override
    public Optional<Movie> save(Movie m) throws ValidatorException {
        if (findOne(m.getId()).isPresent())
            throw new MovieServiceException("Movie with that id exists");
        validator.validate(m);
        String sql = "insert into movies (id, name, genre, year) values (?,?,?,?)";
        jdbcTemplate.update(sql, m.getId(), m.getName(), m.getGenre().name(), m.getYear());
        return Optional.empty();
    }

    @Override
    public Optional<Movie> delete(Integer id) {
        if (!findOne(id).isPresent())
            throw new MovieServiceException("Movie does not exist");
        String sql = "delete from movies where id=?";
        jdbcTemplate.update(sql, id);
        return Optional.empty();
    }

    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        if (!findOne(entity.getId()).isPresent())
            throw new MovieServiceException("Movie does not exist");
        String sql = "update movies set name=?, genre=?, year=? where id=?";
        jdbcTemplate.update(sql, entity.getName(), entity.getGenre().name(), entity.getYear(), entity.getId());
        return Optional.empty();
    }
}

