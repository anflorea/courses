package main.java.ro.ubb.movies.repository;

import main.java.ro.ubb.movies.domain.Movie;
import main.java.ro.ubb.movies.domain.MovieClient;
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
public class RentFileRepository extends InMemoryRepository<Integer, MovieClient> {
    private String filename;

    public RentFileRepository(Validator<MovieClient> validator, String filename){
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
                Integer clientId = Integer.valueOf(items.get(1));
                Integer movieId = Integer.valueOf(items.get((2)));


                MovieClient movie = new MovieClient(id, clientId, movieId);


                try {
                    super.save(movie);
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
    public Optional<MovieClient> save(MovieClient entity) throws ValidatorException {
        Optional<MovieClient> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }
    /*

    @Override
    public Optional<MovieClient> delete(Integer id){
        super.delete(id);
        saveRentalsToFile();

        return Optional.empty();
    }

    @Override
    public Optional<MovieClient> update(MovieClient entity){
        super.update(entity);
        saveRentalsToFile();
        return Optional.empty();
    }


    private void saveRentalsToFile(){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.close();
        super.findAll().forEach(m -> saveToFile(m));
    }
    */


    private void saveToFile(MovieClient entity) {
        Path path = Paths.get(filename);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getClientID() + "," + entity.getMovieID());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
