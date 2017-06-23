package main.java.ro.ubb.movies.repository;


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
public class MovieFileRepository extends InMemoryRepository<Integer, Movie> {
    private String filename;

    public MovieFileRepository(Validator<Movie> validator, String filename){
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
                String name = items.get(1);
                genreType genre = genreType.valueOf(items.get((2)));
                int year = Integer.parseInt(items.get(3));

                Movie movie = new Movie(id, name, genre, year);


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
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        //Optional<Movie> optional =
                super.save(entity);
        //if (optional.isPresent()) {
         //   return optional;
        //}
        saveToFile(entity);
        return Optional.empty();
    }

    @Override
    public Optional<Movie> delete(Integer id){
        super.delete(id);
        saveMoviesToFile();

        return Optional.empty();
    }

    @Override
    public Optional<Movie> update(Movie entity){
        super.update(entity);
        saveMoviesToFile();
        return Optional.empty();
    }

    private void saveMoviesToFile(){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.close();
        super.findAll().forEach(m -> saveToFile(m));
    }

    private void saveToFile(Movie entity) {
        Path path = Paths.get(filename);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getName() + "," + entity.getGenre() + "," + entity.getYear());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
