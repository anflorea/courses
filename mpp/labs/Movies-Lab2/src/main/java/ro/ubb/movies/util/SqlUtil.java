package main.java.ro.ubb.movies.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by andrapop on 2017-03-19.
 */
public class SqlUtil {
    public SqlUtil(){}

    public Connection getConnection(){
        Connection c = null;
        try {

            Class.forName("org.postgresql.Driver");


            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/MovieDB",
                            "postgres", "Ramses19");
            c.setAutoCommit(false);
        }
        catch(Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return c;

    }
}
