package ro.ubb.movies.web.dto;

import lombok.*;
import ro.ubb.movies.core.model.BaseEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by andrapop on 2017-04-24.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MovieDto extends BaseDto{
    @NotNull
    @Size(min=3, max=20, message = "The name must be between 3 and 20 characters")
    private String name;
    @NotNull
    @Size(min=2, max=15, message = "Genre must be between 2 and 15")
    private String genre;
    @NotNull
    @Pattern(regexp="(^$|[0-9]{4})", message = "Not a valid year")
    private Integer year;

    @Override
    public String toString() {
        return "MovieDto{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }
}
