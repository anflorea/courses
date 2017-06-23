package ro.ubb.movies.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Movie extends BaseEntity<Long> {


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "year", nullable = false)
    private Integer year;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<ClientMovie> clientMovies = new HashSet<>();

    public Set<Client> getClients() {
        return Collections.unmodifiableSet(
                clientMovies.stream()
                .map(cm -> cm.getClient())
                .collect(Collectors.toSet())
        );
    }

    public void addClient(Client client) {
        ClientMovie clientMovie = new ClientMovie();
        clientMovie.setClient(client);
        clientMovie.setMovie(this);
        clientMovies.add(clientMovie);
    }

    @Override
    public String toString() {
        return super.toString()+ " Movie {" +
                "name = '" + name  +
                "', genre = '" + genre +
                "', year = " + year +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (name != null ? !name.equals(movie.name) : movie.name != null) return false;
        if (genre != null ? !genre.equals(movie.genre) : movie.genre != null) return false;
        if (year != null ? !year.equals(movie.year) : movie.year != null) return false;
        return clientMovies != null ? clientMovies.equals(movie.clientMovies) : movie.clientMovies == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
