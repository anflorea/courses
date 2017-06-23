package Model;

/**
 * Created by flo on 2016-10-28.
 */

public interface IStmt {
    String toString();
    PrgState execute(PrgState state) throws CustomException;

}


