package Interpretor;

/**
 * Created by flo on 2016-11-16.
 */
public abstract class Cmd {
    private String key, description;
    public Cmd(String key, String description) { this.key = key;
        this.description = description;
    }
    public abstract void execute();
    public String getKey(){return key;}
    public String getDescription(){return description;}
}
