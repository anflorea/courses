package ro.ubb.movies.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movies.core.model.Pizza;
import ro.ubb.movies.core.service.PizzaService;
import ro.ubb.movies.web.converter.PizzaConverter;
import ro.ubb.movies.web.dto.PizzaDto;
import ro.ubb.movies.web.dto.PizzasDto;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andrapop on 2017-04-21.
 */
@RestController
public class PizzaController {

    private static final Logger log = LoggerFactory.getLogger(PizzaController.class);

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private PizzaConverter pizzaConverter;

    private Validator validator;

    public PizzaController() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public PizzasDto getPizzas() {
        log.trace("getPizzas");

        List<Pizza> pizzas = pizzaService.findAll();

        log.trace("getPizzas: pizzas={}", pizzas);

        return new PizzasDto(pizzaConverter.convertModelsToDtos(pizzas));
    }

//    @RequestMapping(value = "/pizzas_paged", method = RequestMethod.GET) !!! DEPRECATED
//    public PizzasDto getPizzasPaged(
//            @RequestParam int page
//    ) {
//        log.trace("getPizzasPaged: page={}", page);
//
//        List<Pizza> pizzas = pizzaService.getPizzaPage(page);
//
//        log.trace("getPizzasPaged: page={}, Pizzas={}", page, pizzas);
//
//        return new PizzasDto(pizzaConverter.convertModelsToDtos(pizzas));
//    }

    @RequestMapping(value = "/pizzas/{pizzaId}", method = RequestMethod.GET)
    public Map<String, PizzaDto> getOnePizza(
            @PathVariable final Long pizzaId) {
        log.trace("getOnePizza: pizzaId={}", pizzaId);

        Pizza pizza = pizzaService.findPizza(pizzaId);

        Map<String, PizzaDto> result = new HashMap<>();
        result.put("pizza", pizzaConverter.convertModelToDto(pizza));

        log.trace("getOnePizza: result={}", result);

        return result;
    }

    @RequestMapping(value = "/pizzas/{pizzaId}", method = RequestMethod.PUT)
    public Map<String, PizzaDto> updatePizza(
            @PathVariable final Long pizzaId,
            @RequestBody final Map<String, PizzaDto> pizzaDtoMap) {
        log.trace("update Pizza: pizzaId={}, pizzaDtoMap={}", pizzaId, pizzaDtoMap);

        PizzaDto pizzaDto = pizzaDtoMap.get("pizza");

        Pizza pizza = pizzaService.updatePizza(pizzaDto.getId(), pizzaDto.getName(), pizzaDto.getDescription(), pizzaDto.getPrice());

        Map<String, PizzaDto> result = new HashMap<>();
        result.put("pizza", pizzaConverter.convertModelToDto(pizza));

        log.trace("update Pizza: result={}", result);

        return result;
    }

    @RequestMapping(value = "/pizzas/update", method = RequestMethod.PUT)
    public PizzasDto updateAllPrices(
            @RequestBody Map<String, Float> priceDto) {
        log.trace("update all prices: priceDto={}", priceDto);

        Float price = priceDto.get("price");

        List<Pizza> pizzas = pizzaService.updateAllPrices(price);

        log.trace("update all prices: pizzas={}", pizzas);

        return new PizzasDto(pizzaConverter.convertModelsToDtos(pizzas));
    }

    @RequestMapping(value = "/pizzas", method = RequestMethod.POST)
    public Map<String, PizzaDto> createClient(
            @RequestBody final Map<String, PizzaDto> pizzaDtoMap) {
        log.trace("createClient: clientDtoMap={}", pizzaDtoMap);

        PizzaDto pizzaDto = pizzaDtoMap.get("pizza");

        Pizza pizza = pizzaService.createPizza(
                pizzaDto.getName(),pizzaDto.getDescription(), pizzaDto.getPrice());

        Map<String, PizzaDto> result = new HashMap<>();
        result.put("pizza", pizzaConverter.convertModelToDto(pizza));


        log.trace("createPizzas: result={}", result);

        return result;
    }


}
