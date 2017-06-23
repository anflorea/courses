package ro.ubb.l5.common.domain;

import java.io.Serializable;

/**
 * Created by andrapop on 2017-03-06.
 */
public class BaseEntity<ID> implements Serializable {
    private static final long serialVersionUID = 1;
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
