package ro.ubb.movies.core.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;

import ro.ubb.movies.core.model.Pizza;

import java.util.List;

/**
 * Created by andrapop on 2017-04-21.
 */
public interface PizzaRepository extends PizzaOrderRepository<Pizza, Long>{

    @Modifying
    @Transactional
    @Query("update Pizza p set p.price=p.price + :newPrice")
    public void updateAllPizzas(@Param("newPrice") Float newPrice);

    @Query("select distinct p from Pizza p")
    @EntityGraph(value = "pizzaWithIngredients", type = EntityGraph.EntityGraphType.LOAD)
    List<Pizza> findAllWithIngredientsGraph();

    @Query("select distinct p from Pizza p where p.id=:studentId")
    @EntityGraph(value = "pizzaWithIngredients", type = EntityGraph.EntityGraphType.LOAD)
    Pizza findOneWithIngredients(@Param("studentId") Long studentId);

}
