package ro.ubb.movies.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.movies.core.model.Pizza;
import ro.ubb.movies.web.dto.IngredientDto;
import ro.ubb.movies.web.dto.PizzaDto;

import java.util.stream.Collectors;


/**
 * Created by andrapop on 2017-05-01.
 */
@Component
public class PizzaConverter extends BaseConverter<Pizza, PizzaDto>  {
    private static final Logger log = LoggerFactory.getLogger(PizzaConverter.class);

    @Override
    public PizzaDto convertModelToDto(Pizza pizza) {
        PizzaDto pizzaDto = PizzaDto.builder().name(pizza.getName())
                .description(pizza.getDescription()).price(pizza.getPrice()).build();
        pizzaDto.setId(pizza.getId());
        pizzaDto.setIngredients(pizza.getIngredients().stream().map(i -> IngredientDto.builder().name(i.getName()).build()).collect(Collectors.toSet()));

        return pizzaDto;
    }
}
