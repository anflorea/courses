package ro.ubb.movies.core.service;

import ro.ubb.movies.core.model.Ingredient;

import java.util.List;

/**
 * Created by flo on 19/06/2017.
 */
public interface IngredientService {
    List<Ingredient> findAll();
}
