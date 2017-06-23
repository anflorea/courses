package ro.ubb.movies.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andrapop on 2017-04-21.
 */

@Entity
@Table(name="pizza")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "pizzaWithIngredients",
                attributeNodes = {
                        @NamedAttributeNode("id"),
                        @NamedAttributeNode("description"),
                        @NamedAttributeNode("price"),
                        @NamedAttributeNode("ingredients")
        })
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pizza extends BaseEntity<Long> {

    private static final int SERIAL_NUMBER_LENGTH = 16;


    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private float price ;
    @OneToMany(mappedBy = "pizza", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (Float.compare(pizza.price, price) != 0) return false;
        if (name != null ? !name.equals(pizza.name) : pizza.name != null) return false;
        return description != null ? description.equals(pizza.description) : pizza.description == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
