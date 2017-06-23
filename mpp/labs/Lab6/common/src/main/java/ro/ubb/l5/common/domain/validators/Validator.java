package ro.ubb.l5.common.domain.validators;

/**
 * Created by andrapop on 2017-03-06.
 */
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
