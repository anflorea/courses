package ro.ubb.movies.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by andrapop on 2017-04-21.
 */

@Entity
@Table(name="client")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientWithMovies", attributeNodes = {
                @NamedAttributeNode(value = "clientMovies", subgraph = "clientMoviesGraph")
        }, subgraphs = {
                @NamedSubgraph(name = "clientMoviesGraph", attributeNodes = {
                        @NamedAttributeNode(value = "movie")
                })
        })
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Client extends BaseEntity<Long> {

    private static final int SERIAL_NUMBER_LENGTH = 16;

    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "phoneNr", nullable = false)
    private String phoneNr;
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<ClientMovie> clientMovies = new HashSet<>();

    public Set<Movie> getMovies() {
       // return new HashSet<Movie>();
        return clientMovies == null ? new HashSet<>() :
       Collections.unmodifiableSet(
              clientMovies.stream()
                       .map(cm -> cm.getMovie())
                        .collect(Collectors.toSet())
        );
    }

    public void addMovie(Movie movie) {
        System.out.println("daaaa" + movie.getId() + " " + this.getId());
        ClientMovie clientMovie = new ClientMovie();
        clientMovie.setMovie(movie);
        clientMovie.setClient(this);
        clientMovies.add(clientMovie);

    }

    public void addMovies(Set<Movie> movies) {
        movies.stream()
                .forEach(movie -> addMovie(movie));
    }


    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNr='" + phoneNr + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
        if (phoneNr != null ? !phoneNr.equals(client.phoneNr) : client.phoneNr != null) return false;
        if (address != null ? !address.equals(client.address) : client.address != null) return false;
        return clientMovies != null ? clientMovies.equals(client.clientMovies) : client.clientMovies == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNr != null ? phoneNr.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);

        return result;
    }
}
