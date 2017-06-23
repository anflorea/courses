package main.java.ro.ubb.movies.repository;

import main.java.ro.ubb.movies.domain.Client;
import main.java.ro.ubb.movies.domain.Movie;
import main.java.ro.ubb.movies.domain.genreType;
import main.java.ro.ubb.movies.domain.validators.Validator;
import main.java.ro.ubb.movies.domain.validators.ValidatorException;
import main.java.ro.ubb.movies.util.SqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrapop on 2017-03-19.
 */
public class MovieDBRepository implements IRepository<Integer, Movie> {
    private Validator<Movie> validator;
    private SqlUtil sqlUtil;

    public MovieDBRepository(Validator<Movie> movieValidator){
        this.validator = movieValidator;
        sqlUtil = new SqlUtil();
    }


    @Override
    public Optional<Movie> findOne(Integer integer) {
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;
        Movie movie = null;
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM movies;");
            while (rs.next()) {
                int id = rs.getInt("id");
                if (id == integer) {

                    String name = rs.getString("name");
                    String genre = rs.getString("genre");
                    Integer year = rs.getInt("year");


                   movie = new Movie(id, name, genreType.valueOf(genre),year);
                }
            }
            stmt.close();
            c.close();
        }
        catch(SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }



        return Optional.ofNullable(movie);
    }

    @Override
    public Iterable<Movie> findAll() {
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;
        List<Movie> movies = new ArrayList<>();


        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM movies;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String genre = rs.getString("genre");
                Integer year = rs.getInt("year");


                Movie movie = new Movie(id,name,genreType.valueOf(genre),year);
                movies.add(movie);

            }
            stmt.execute("select * from movies;");
            stmt.close();
            c.close();
        }
        catch(SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }



        return movies;


    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        if (entity == null)
            throw new IllegalArgumentException("Entity must not be null");
        validator.validate(entity);

        Connection c = sqlUtil.getConnection();
        Statement stmt = null;


        try {
            stmt = c.createStatement();

            String sql = "INSERT INTO movies (id,name,genre,year) "
                    + "VALUES ('"+entity.getId()+"','"+entity.getName()+"', '"+entity.getGenre().name()+"','"+entity.getYear()+"');";

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
    public Optional<Movie> delete(Integer integer) {
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            String sql = "DELETE from movies where id='"+integer+"'";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        }
        catch(SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }



        return Optional.empty();

    }

    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException {
        Connection c = sqlUtil.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            if(!findOne(entity.getId()).isPresent())
                throw new ValidatorException("Movie does not exist!");
            else {
                String sql = "UPDATE movies set name = '" + entity.getName() + "', genre = '" + entity.getGenre().name() +
                        "', year ='" + entity.getYear() + "' where ID='" + entity.getId() + "';";
                stmt.executeUpdate(sql);
                c.commit();
            }
            stmt.close();
            c.close();
        }
        catch(SQLException e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }



        return Optional.empty();

    }
}
