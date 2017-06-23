package ro.ubb.movies.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.ubb.movies.core.model.Client;
import ro.ubb.movies.web.dto.ClientDto;

import java.util.stream.Collectors;


/**
 * Created by andrapop on 2017-05-01.
 */
@Component
public class ClientConverter extends BaseConverter<Client, ClientDto>  {
    private static final Logger log = LoggerFactory.getLogger(ClientConverter.class);

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientDto = ClientDto.builder().firstName(client.getFirstName()).lastName(client.getLastName())
                .phoneNr(client.getPhoneNr()).address(client.getAddress()).build();
        clientDto.setId(client.getId());
        clientDto.setMovies(client.getMovies().stream()
                .map(m -> m.getId())
                .collect(Collectors.toSet()));
        return clientDto;
    }
}
