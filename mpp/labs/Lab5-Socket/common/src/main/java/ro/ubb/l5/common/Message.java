package ro.ubb.l5.common;

import ro.ubb.l5.common.domain.Client;
import ro.ubb.l5.common.domain.Movie;

import java.io.*;
import java.util.List;


/**
 * Created by andrapop on 2017-03-26.
 */
public class Message implements Serializable {
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";
    private static String LINE_SEPARATOR = System.getProperty("line.separator");

    private String header;
    private Integer size;
    private String body;
    private Movie movie;
    private Client client;
    private List<Movie> movies;


    public Message() {
    }

    public Message(String header, String body) {
        this.header = header;
        this.size = body.length();
        this.body = body;
    }

    public String body() {
        return body;
    }

    public String header() {
        return header;
    }



    @Override
    public String toString() {
        return "Message{" + "header='" + header + '\'' + ", body='" + body + '\'' + '}';
    }

    public void writeTo(OutputStream os) throws IOException {
        byte[] stringToWrite = (header + LINE_SEPARATOR + size + LINE_SEPARATOR + body).getBytes();
        os.write(stringToWrite, 0, stringToWrite.length);
    }



    public void readFrom(InputStream is) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            header = br.readLine();
            size = Integer.valueOf(br.readLine());
            char[] someString = new char[size];
            br.read(someString, 0, size);
            body = String.valueOf(someString);
        }
        catch(IOException e){
            throw new IOException(e.getMessage());
        }
    }

}
