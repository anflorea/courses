package ro.ubb.movies.core.service;


import ro.ubb.movies.core.model.Pizza;

import java.util.List;

/**
 * Created by andrapop on 2017-04-21.
 */
public interface PizzaService {
    List<Pizza> findAll();

//    List<Pizza> getPizzaPage(int page);

    Pizza createPizza( String name, String descrition, float pricelient);

    Pizza findPizza(Long pizzaId);

    Pizza updatePizza(Long pizzaId, String pizzaName, String pizzaDescription, float price);

    List<Pizza> updateAllPrices(Float price);
}
