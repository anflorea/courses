package ro.ubb.l5.common.domain;

/**
 * Created by andrapop on 2017-03-13.
 */
public class MovieClient extends BaseEntity<Integer> {
    private int clientID;
    private int movieID;

    public MovieClient(){}

    public MovieClient(int id, int clientID, int movieID){
        super.setId(id);
        this.clientID = clientID;
        this.movieID = movieID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    @Override
    public String toString() {
        return super.toString()+" MovieClient{" +
                " clientID=" + clientID +
                ", movieID=" + movieID +
                '}';
    }
}
