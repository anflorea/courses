package ro.ubb.movies.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by andrapop on 2017-04-24.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
    private Long id;
}

