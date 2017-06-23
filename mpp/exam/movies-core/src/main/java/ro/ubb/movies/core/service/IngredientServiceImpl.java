package ro.ubb.movies.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.movies.core.model.Ingredient;
import ro.ubb.movies.core.repository.IngredientRepository;

import java.util.List;

/**
 * Created by flo on 19/06/2017.
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
}
