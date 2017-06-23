package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by flo on 2016-11-14.
 */
public class ReadFile implements IStmt {
    private String FileDesc;
    private String VarName;

    public ReadFile(String f, String v){
        FileDesc = f;
        VarName = v;
    }

    @Override
    public PrgState execute(PrgState prg) throws CustomException {
        Integer desc = prg.getSymTable().getValue(FileDesc);

        if (desc == null) {
            throw new CustomException("FileDesc");
        }

        FileData data = prg.getFileTable().getValue(desc);

        BufferedReader buff = data.getReader();
        try {
            String var = buff.readLine();
            if (var.length() == 0) {
                prg.getSymTable().setValue(VarName, 0);
            } else if (var == null) {
                throw new CustomException("Reached end of file");

            } else {
                Integer var2 = Integer.parseInt(var);
                prg.getSymTable().setValue(VarName, var2);

            }
        }
        catch(IOException e){
            throw new CustomException(e.getMessage());
        }

        return null;
    }
    public String toString()
    {
        return "readFile(" + this.FileDesc + ", " + this.VarName + ")";
    }


}
