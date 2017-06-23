package ro.ubb.movies.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movies.core.model.ClientMovie;
import ro.ubb.movies.web.dto.ClientMovieDto;

/**
 * Created by andrapop on 2017-05-05.
 */
@Component
public class ClientMovieConverter extends BaseConverterGeneric<ClientMovie, ClientMovieDto>{
    @Override
    public ClientMovie convertDtoToModel(ClientMovieDto clientMovieDto) {
        throw new RuntimeException("not yet implemented.");
    }

    /**
     *
     * @param clientMovie
     * @return data transfer object
     */

    @Override
    public ClientMovieDto convertModelToDto(ClientMovie clientMovie) {
        ClientMovieDto clientMovieDto = ClientMovieDto.builder()
                .clientId(clientMovie.getClient().getId())
                .movieId(clientMovie.getMovie().getId())
                .movieName(clientMovie.getMovie().getName()).build();

        return clientMovieDto;
    }
    /*

    public Map<Long, Integer> convertDtoToMap(ClientMovieDto clientMovieDto) {
        Map<Long, Integer>
    }
    */


}
