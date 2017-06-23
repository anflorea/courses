package main.java.ro.ubb.movies.repository;

import main.java.ro.ubb.movies.domain.Client;
import main.java.ro.ubb.movies.domain.MovieClient;
import main.java.ro.ubb.movies.domain.validators.Validator;
import main.java.ro.ubb.movies.domain.validators.ValidatorException;
import main.java.ro.ubb.movies.util.SqlUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrapop on 2017-03-19.
 */
public class RentDBRepository implements IRepository<Integer,MovieClient>{
    private Validator<MovieClient> validator;
    private SqlUtil sqlUtil;

    public RentDBRepository(Validator<MovieClient> rentValidator){
        this.validator = rentValidator;
        sqlUtil = new SqlUtil();
    }

    @Override
    public Optional<MovieClient> findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<MovieClient> findAll() {
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;
        List<MovieClient> rentals = new ArrayList<>();

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM rentals;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                int clientId = rs.getInt("clientid");
                int movieId = rs.getInt("movieid");

                MovieClient rental = new MovieClient(id,clientId,movieId);
                rentals.add(rental);
            }
            stmt.close();
            c.close();
        }
        catch(SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }



        return rentals;


    }
    @Override
    public Optional<MovieClient> save(MovieClient entity) throws ValidatorException {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null");
        validator.validate(entity);
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;
        //c.prepareCall("select name from Movies");

        try {
            stmt = c.createStatement();

            String sql = "INSERT INTO rentals (id,clientid,movieid) "
                    + "VALUES ('"+entity.getId()+"','"+entity.getClientID()+"', '"+entity.getMovieID()+"');";

            stmt.executeUpdate(sql);
            c.commit();
            stmt.execute("select * from clients;");
            c.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
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
