package Model;

import Utils.IFileTable;
import Utils.ISymTable;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by flo on 2016-11-11.
 */
public class OpenRFile implements IStmt {
    private String fileId;
    private String filename;

    public OpenRFile(String fi, String fn){
        fileId = fi;
        filename = fn;

    }
    public PrgState execute(PrgState state) throws CustomException{
        ISymTable st = state.getSymTable();
        IFileTable ft = state.getFileTable();
        if(st.contains(fileId))
            throw new CustomException("File already opened!");

        try{
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(filename));

            int id = FileIdGenerator.generateId();
            FileData fd = new FileData(filename,br);
            ft.add(id, fd);
            st.add(fileId, id);
        }

        catch(FileNotFoundException e) {
            System.out.print(e.getMessage());
        }

        return null;


    }
    public String toString()
    {
        return "openRFile(" + this.fileId + ", " + this.filename + ")";
    }

}
