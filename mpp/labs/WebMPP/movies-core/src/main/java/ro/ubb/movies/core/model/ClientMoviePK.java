package ro.ubb.movies.core.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by andrapop on 2017-05-04.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientMoviePK implements Serializable {

    private Client client;
    private Movie movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientMoviePK that = (ClientMoviePK) o;

        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        return movie != null ? movie.equals(that.movie) : that.movie == null;

    }

    @Override
    public int hashCode() {
        int result = client != null ? client.hashCode() : 0;
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        return result;
    }
}
