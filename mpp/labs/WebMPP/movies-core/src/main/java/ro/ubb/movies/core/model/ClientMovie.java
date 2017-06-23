package ro.ubb.movies.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by andrapop on 2017-05-04.
 */

@Entity
@Table(name = "client_movie")
@IdClass(ClientMoviePK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ClientMovie implements Serializable{

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientMovie that = (ClientMovie) o;

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
