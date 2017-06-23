package main.java.ro.ubb.movies.domain;



/**
 * Created by andrapop on 2017-03-06.
 */
public class Movie extends BaseEntity<Integer>{
    private String name;
    private genreType genre;
    private int year;


    public Movie() {

    }

    public Movie(int id,String name, genreType genre, int year) {
        super.setId(id);
        this.name = name;
        this.genre = genre;
        this.year = year;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public genreType getGenre() {
        return genre;
    }

    public void setGenre(genreType genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }




    @Override
    public String toString() {
        return super.toString()+ " Movie {" +
                "name = '" + name  +
                "', genre = '" + genre +
                "', year = " + year +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (year != movie.year) return false;
        if (!name.equals(movie.name)) return false;
        return genre.equals(movie.genre);
    }

}
