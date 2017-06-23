package ro.ubb.movies.web.dto;

import lombok.*;

/**
 * Created by andrapop on 2017-05-05.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ClientMovieDto {
    private Long clientId;
    private Long movieId;
    private String movieName;
}
