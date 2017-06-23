package ro.ubb.movies.web.dto;

import lombok.*;

import java.util.Set;


/**
 * Created by andrapop on 2017-05-01.
 */
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public class PizzaDto extends BaseDto {


        private String name;

        private String description;

        private float price;

        private Set<IngredientDto> ingredients;


    @Override
    public String toString() {
        return "PizzaDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", ingrediets=" + ingredients +
                '}';
    }
}

