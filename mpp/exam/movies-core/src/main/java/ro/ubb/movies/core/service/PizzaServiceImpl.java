package ro.ubb.movies.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movies.core.model.Pizza;
import ro.ubb.movies.core.repository.PizzaRepository;

import java.util.List;

/**
 * Created by andrapop on 2017-04-21.
 */
@Service
public class PizzaServiceImpl implements PizzaService {

    private static final Logger log = LoggerFactory.getLogger(PizzaServiceImpl.class);

    @Autowired
    private PizzaRepository PizzaRepository;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public List<Pizza> findAll() {
        log.trace("findAll");
//        List<Pizza> pizzas = PizzaRepository.findAll();
        List<Pizza> pizzas = PizzaRepository.findAllWithIngredientsGraph();

        log.trace("findAll: Pizzas={}", pizzas);

        return pizzas;
    }

//    @Override
//    public List<Pizza> getPizzaPage(int page) {
//        log.trace("getPizzaPaged: page={}", page);
//
//        Pageable pageable = new PageRequest(page, 2, new Sort (new Sort.Order(Sort.Direction.ASC, "name")));
//        Page<Pizza> pizzas = PizzaRepository.findAll(pageable);
//
//
//        log.trace("getPizzaPaged: page={}, Pizzas={}", page, pizzas.getContent());
//
//        return pizzas.getContent();
//    }

    @Override
    public Pizza findPizza(Long pizzaId) {
        log.trace("findPizza: PizzaId={}", pizzaId);

        Pizza Pizza = PizzaRepository.findOneWithIngredients(pizzaId);

        log.trace("findPizza: Pizza={}", Pizza);

        return Pizza;
    }



    @Override
    public Pizza createPizza( String name, String description, float price) {
        log.trace("createPizza:name={}, descrioption={}, price={}",
                name, description, price);
        Pizza pizza = Pizza.builder().

                name(name).
                description(description).
        price(price).build();
        pizza = PizzaRepository.save(pizza);
        log.trace("createPizza: Pizza={}", pizza);

        return pizza;
    }

    @Override
    @Transactional
    public Pizza updatePizza(Long pizzaId, String pizzaName, String pizzaDescription, float pizzaPrice) {
        log.trace("updatePizza: id={}, name={}, description={}, price={}", pizzaId, pizzaName, pizzaDescription, pizzaPrice);

        Pizza pizza = PizzaRepository.findOneWithIngredients(pizzaId);

        pizza.setName(pizzaName);
        pizza.setDescription(pizzaDescription);
        pizza.setPrice(pizzaPrice);

        log.trace("updatePizza, pizza={}", pizza);

        return pizza;
    }

    @Override
    @Transactional
    public List<Pizza> updateAllPrices(Float price) {
        log.trace("updateAllPrices: price={}", price);

//        List<Pizza> pizzas = PizzaRepository.findAll();
//
//        pizzas = pizzas.stream().map( pizza -> { pizza.setPrice(pizza.getPrice() + price); return pizza; }).collect(Collectors.toList());

        PizzaRepository.updateAllPizzas(price);

        List<Pizza> pizzas = PizzaRepository.findAllWithIngredientsGraph();

        log.trace("updateAllPrices: pizzas={}", pizzas);

        return pizzas;
    }
}
