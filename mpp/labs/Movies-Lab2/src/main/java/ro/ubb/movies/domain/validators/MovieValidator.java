package main.java.ro.ubb.movies.domain.validators;

import main.java.ro.ubb.movies.domain.Movie;

/**
 * Created by andrapop on 2017-03-06.
 */
public class MovieValidator implements Validator<Movie> {
    @Override
    public void validate(Movie entity) throws ValidatorException{
        String name = "[a-zA-Z]{2,}([,.\\s]+[a-zA-Z]*)*";
        if(!entity.getName().matches(name))
            throw new ValidatorException("Invalid Name !  Movie not added!");

        String number = "[1-2][0-9]{3}";

        String year = String.valueOf(entity.getYear());
        if(!year.matches(number))
            throw new ValidatorException("Invalid Year ! Movie not added!");

    }
}
