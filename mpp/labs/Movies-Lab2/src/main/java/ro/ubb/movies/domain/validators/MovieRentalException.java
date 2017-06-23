package main.java.ro.ubb.movies.domain.validators;

/**
 * Created by andrapop on 2017-03-06.
 */
public class MovieRentalException extends RuntimeException {
    public MovieRentalException(String message){
        super(message);
    }

    public MovieRentalException(String message, Throwable cause){
        super(message,cause);
    }

    public MovieRentalException(Throwable cause){
        super(cause);
    }
}
