package main.java.ro.ubb.movies.domain;

/**
 * Created by andrapop on 2017-03-06.
 */
public class BaseEntity<ID> {
    private ID id;

    public void setId(ID id){

        this.id = id;
    }

    public ID getId() {
        return id;
    }


    @Override
    public String toString() {
        return "BaseEntity {" +
                "id = " + id +
                "}";
    }
}
