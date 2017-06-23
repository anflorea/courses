package ro.ubb.movies.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ubb.movies.core.model.Client;
import ro.ubb.movies.core.model.Movie;


import java.util.Set;

/**
 * Created by andrapop on 2017-04-21.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientsDto {
    private Set<ClientDto> clients;



}
