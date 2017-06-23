package ro.ubb.l5.common;

/**
 * Created by andrapop on 2017-03-27.
 */
public class MovieServiceException extends RuntimeException {
    public MovieServiceException(Throwable cause) {
        super(cause);
    }

    public MovieServiceException(String message) {
        super(message);
    }
}
