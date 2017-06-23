package ro.ubb.l5.common.domain.validators;

/**
 * Created by andrapop on 2017-03-06.
 */
public class ValidatorException extends MovieRentalException {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
