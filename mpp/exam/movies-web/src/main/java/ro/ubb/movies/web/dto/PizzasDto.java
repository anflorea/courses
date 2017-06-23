package ro.ubb.movies.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

/**
 * Created by andrapop on 2017-04-21.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PizzasDto {
    private Set<PizzaDto> pizzas;



}
