package ro.ubb.movies.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movies.core.model.Client;
import ro.ubb.movies.core.service.ClientService;
import ro.ubb.movies.web.converter.ClientConverter;
import ro.ubb.movies.web.dto.ClientDto;
import ro.ubb.movies.web.dto.ClientsDto;
import ro.ubb.movies.web.dto.EmptyJsonResponse;

import javax.validation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by andrapop on 2017-04-21.
 */
@RestController
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    private Validator validator;

    public ClientController() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ClientsDto getClients() {
        log.trace("getClients");

        List<Client> clients = clientService.findAll();

        log.trace("getClients: clients={}", clients);

        return new ClientsDto(clientConverter.convertModelsToDtos(clients));
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT)
    public Map<String, ClientDto> updateClient(
            @PathVariable final Long clientId,
            @RequestBody final Map<String, ClientDto> clientDtoMap) {
        log.trace("updateClient: clientId={}, clientDtoMap={}", clientId, clientDtoMap);

        ClientDto clientDto = clientDtoMap.get("client");
        Client client = clientService.updateClient(clientId, clientDto.getFirstName(),
                clientDto.getLastName(), clientDto.getPhoneNr(), clientDto.getAddress(), clientDto.getMovies());

        Map<String, ClientDto> result = new HashMap<>();
        result.put("client", clientConverter.convertModelToDto(client));

        log.trace("updateClient: result={}", result);

        return result;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public Map<String, ClientDto> createClient(
            @RequestBody final Map<String, ClientDto> clientDtoMap, BindingResult bindingResult) {
        log.trace("yeeeeee");
        log.trace("createClient: clientDtoMap={}", clientDtoMap);

        ClientDto clientDto = clientDtoMap.get("client");

        Set<ConstraintViolation<ClientDto>> violations = validator.validate(clientDto);

        ClientDto errorDto = new ClientDto();

        for (ConstraintViolation<ClientDto> violation : violations)
        {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError

            bindingResult.addError(new FieldError("client", propertyPath, message));
            switch (propertyPath) {
                case "lastName":
                    errorDto.setLastName(message);
                    break;
                case "firstName":
                    errorDto.setFirstName(message);
                    break;
                case "phoneNr":
                    errorDto.setPhoneNr(message);
                    break;
                case "address":
                    errorDto.setAddress(message);
                    break;
            }

        }

        if (bindingResult.hasErrors()) {
            Map<String, ClientDto> result = new HashMap<>();
            result.put("error", errorDto);
            return result;
        }

        Client client = clientService.createClient(
                clientDto.getFirstName(), clientDto.getLastName(), clientDto.getPhoneNr(), clientDto.getAddress());

        Map<String, ClientDto> result = new HashMap<>();
        result.put("client", clientConverter.convertModelToDto(client));



        log.trace("createClient: result={}", result);

        return result;
    }

    @RequestMapping(value = "clients/{clientId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteClient(@PathVariable final Long clientId) {
        log.trace("deleteClient: clientId={}", clientId);

        clientService.deleteClient(clientId);

        log.trace("deleteClient - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }


}
