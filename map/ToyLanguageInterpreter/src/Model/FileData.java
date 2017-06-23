package Model;

import java.io.BufferedReader;

/**
 * Created by flo on 2016-11-14.
 */
public class FileData {
    private String filename;
    private BufferedReader reader;

    public FileData(String f, BufferedReader br){
        filename=f;
        reader=br;
    }

    public String getFilename() {
        return filename;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setReader(BufferedReader fileDesc) {
        this.reader = fileDesc;
    }

    @Override
    public String toString(){
        return filename;
    }
}
