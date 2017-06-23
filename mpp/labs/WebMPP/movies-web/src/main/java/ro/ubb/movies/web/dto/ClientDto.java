package ro.ubb.movies.web.dto;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;


/**
 * Created by andrapop on 2017-05-01.
 */
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public class ClientDto extends BaseDto {
        @NotNull
        @Size(min=3, max=20, message = "The name must have between 3 and 20 characters")
        private String firstName;
        @NotNull
        @Size(min=3, max=20, message = "The name must have between 3 and 20 characters")
        private String lastName;
        @NotNull(message = "Pone number can not be empty")
        @Pattern(regexp="(^$|[0-9]{10})", message = "Not a valid phone number")
        private String phoneNr;
        @NotNull
        @NotEmpty
        private String address;
    private Set<Long> movies;


    @Override
    public String toString() {
        return "ClientDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNr='" + phoneNr + '\'' +
                ", address='" + address + '\'' +
                ", movies=" + movies +
                '}' + super.toString();
    }
}

