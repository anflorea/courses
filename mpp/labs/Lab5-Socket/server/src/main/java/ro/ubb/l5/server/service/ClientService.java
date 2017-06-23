package ro.ubb.l5.server.service;



import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.validators.RepositoryException;
import ro.ubb.l5.common.domain.validators.ValidatorException;
import ro.ubb.l5.server.repository.IRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by andrapop on 2017-03-06.
 */
public class ClientService {
    private IRepository<Integer, Client> repository;

    public ClientService(IRepository<Integer,Client> repository){
        this.repository = repository;
    }

    public void addClient(Client client) throws ValidatorException {
        if(repository.findOne(client.getId()).isPresent())
            throw new RepositoryException("A client with that id exists, can't add!");
        repository.save(client);
    }
    public Optional<Client> findOne(int id){
        return repository.findOne(id);
    }

    public void removeClient(int id) throws ValidatorException, RepositoryException{
        if(!repository.findOne(id).isPresent())
            throw new RepositoryException("The client does not exist, can't remove!");
        repository.delete(id);
    }

    public void updateClient(Client client) throws ValidatorException, RepositoryException{
        if(!repository.findOne(client.getId()).isPresent())
            throw new RepositoryException("The client does not exist, can't update!");
        repository.update(client);
    }

    public Set<Client> getAllClients(){
        Iterable<Client> clients = repository.findAll();
        return StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }




}
