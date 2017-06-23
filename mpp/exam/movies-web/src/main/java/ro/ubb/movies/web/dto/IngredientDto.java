package ro.ubb.movies.web.dto;

import lombok.*;

/**
 * Created by flo on 19/06/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IngredientDto  extends BaseDto {

    private String name;

    @Override
    public String toString() {
        return "IngredientDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
