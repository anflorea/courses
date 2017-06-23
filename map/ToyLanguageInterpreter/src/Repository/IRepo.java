package Repository;

/**
 * Created by flo on 2016-11-02.
 */

import java.io.IOException;
import java.util.ArrayList;
import Model.*;

public interface IRepo{
    public void addProgramState(PrgState programState);
    public ArrayList<PrgState> getPrgList();
    public void setPrgList(ArrayList<PrgState> list);
    public void logPrgStateExec(PrgState state) throws IOException;
    void serialize();
    void deserialize();
}