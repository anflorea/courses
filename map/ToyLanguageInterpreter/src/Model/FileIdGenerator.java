package Model;

/**
 * Created by flo on 2016-11-14.
 */
public class FileIdGenerator {
    private static int id = 2;
    public static int generateId(){
        return ++id;
    }
}
