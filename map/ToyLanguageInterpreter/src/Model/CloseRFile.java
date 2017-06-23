package Model;

import Utils.IFileTable;
import javafx.animation.Interpolatable;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by flo on 2016-11-14.
 */
public class CloseRFile implements IStmt {
    private String fileDesc;

    public CloseRFile(String fd) {
        fileDesc = fd;
    }

    public PrgState execute(PrgState state) throws CustomException {
        Integer id = state.getSymTable().getValue(fileDesc);
        if (id == null)
            throw new CustomException("File not open");
        FileData fd = state.getFileTable().getValue(id);
        if (fd == null)
            throw new CustomException("No file data");
        BufferedReader buff = fd.getReader();
        try {
            buff.close();
        } catch (IOException e) {
            throw new CustomException("Can't close file");
        }
        IFileTable ft=state.getFileTable();
        //ft.remove(id);
        return null;

    }

    @Override
    public String toString()
    {
        return "closeRFile(" + this.fileDesc + ")";
    }

}
