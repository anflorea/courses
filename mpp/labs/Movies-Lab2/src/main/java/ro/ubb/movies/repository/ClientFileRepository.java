package main.java.ro.ubb.movies.repository;

import main.java.ro.ubb.movies.domain.Client;
import main.java.ro.ubb.movies.domain.Movie;
import main.java.ro.ubb.movies.domain.genreType;
import main.java.ro.ubb.movies.domain.validators.Validator;
import main.java.ro.ubb.movies.domain.validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrapop on 2017-03-18.
 */
public class ClientFileRepository extends InMemoryRepository<Integer, Client> {
    private String filename;

    public ClientFileRepository(Validator<Client> validator, String filename){
        super(validator);
        this.filename = filename;

        loadData();
    }


    public void loadData(){
        Path path = Paths.get(filename);

        try{
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Integer id = Integer.valueOf(items.get(0));
                String firstName = items.get(1);
                String lastName = items.get(2);
                String phoneNr = items.get((3));
                String address = items.get(4);

                Client client= new Client(id, firstName, lastName, phoneNr, address);


                try {
                    super.save(client);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Client> save(Client entity) throws ValidatorException {
        Optional<Client> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;//testeazaaa
        }
        saveToFile(entity);
        return Optional.empty();
    }


    @Override
    public Optional<Client> delete(Integer id){
        super.delete(id);
        saveClientsToFile();

        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client entity){
        super.update(entity);
        saveClientsToFile();
        return Optional.empty();
    }

    private void saveClientsToFile(){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.close();
        super.findAll().forEach(m -> saveToFile(m));
    }

    private void saveToFile(Client entity) {
        Path path = Paths.get(filename);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getFirstName() + "," + entity.getLastName() + "," + entity.getPhoneNr()
                            + "," + entity.getAddress());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
