package main.java.ro.ubb.movies.domain.validators;

/**
 * Created by andrapop on 2017-03-20.
 */
public class RepositoryException extends MovieRentalException {
    public RepositoryException(String message){
        super(message);
    }

    public RepositoryException(String message, Throwable cause){
        super(message,cause);
    }

    public RepositoryException(Throwable cause){
        super(cause);
    }
}
