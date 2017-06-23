package ro.ubb.l5.server.repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ro.ubb.l5.common.domain.Movie;
import ro.ubb.l5.common.domain.MovieClient;
import ro.ubb.l5.common.domain.validators.Validator;
import ro.ubb.l5.common.domain.validators.ValidatorException;


import java.util.*;


/**
 * Created by andrapop on 2017-03-19.
 */

public class RentDBRepository implements IRepository<Integer,MovieClient>{
    @Autowired
    JdbcTemplate jdbcTemplate;

    private Validator<MovieClient> validator;


    public RentDBRepository(Validator<MovieClient> rentValidator){
        this.validator = rentValidator;

    }

    @Override
    public Optional<MovieClient> findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<MovieClient> findAll() {
        String sql = "select * from rentals";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(MovieClient.class));
    }
    @Override
    public Optional<MovieClient> save(MovieClient mc) throws ValidatorException {
        String sql = "insert into rentals (id, clientid, movieid) values (?,?,?)";
        jdbcTemplate.update(sql, mc.getId(), mc.getClientID(), mc.getMovieID());
        return Optional.empty();
    }

    private int findNoRentals(int Id) {

        List<MovieClient> rentals = new ArrayList<>();
        findAll().spliterator().forEachRemaining(rentals::add);

        return (int)rentals.stream().filter(r ->r.getMovieID() == Id).count();
    }

    public Map<Integer, Integer> report(){
        Map<Integer, Integer> reports= new HashMap<>();
        for(MovieClient mc: findAll()) {
            int id = mc.getMovieID();
            if (!reports.containsKey(id))
                reports.put(id, findNoRentals(id));
        }
        return reports;
    }

    @Override
    public Optional<MovieClient> delete(Integer integer) {
        return null;
    }

    @Override
    public Optional<MovieClient> update(MovieClient entity) throws ValidatorException {
        return null;
    }


}

