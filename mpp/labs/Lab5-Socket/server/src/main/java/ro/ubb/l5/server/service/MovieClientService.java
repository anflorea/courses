package ro.ubb.l5.server.service;



import ro.ubb.l5.server.repository.IRepository;
import ro.ubb.l5.common.domain.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by andrapop on 2017-03-13.
 */
public class MovieClientService {
    private IRepository<Integer, MovieClient> repository;
    private Map<Integer,Integer> reports;

    public MovieClientService(IRepository<Integer, MovieClient> repository) {
        this.repository = repository;
        reports= new HashMap<>();
    }

    public void addMovieClient(MovieClient mc){
        repository.save(mc);
    }
    public Set<MovieClient> getAllRentals(){
        Iterable<MovieClient> rented = repository.findAll();
        return StreamSupport.stream(rented.spliterator(), false).collect(Collectors.toSet());
    }
    public int findIdOccurences(int id){
        return (int)StreamSupport.stream(repository.findAll().spliterator(),false).filter(e -> e.getMovieID()==id).count();
    }
    public Map<Integer,Integer> reports(){
        for(MovieClient mc: repository.findAll()) {
            int id = mc.getMovieID();
            if (!reports.containsKey(id))
                reports.put(id, findIdOccurences(id));
        }


        return reports;

    }
}
