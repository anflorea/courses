package Interpretor;

/**
 * Created by flo on 2016-11-16.
 */
public class ExitCmd extends Cmd{
    public ExitCmd(String key, String desc){
        super(key, desc);
    }
    @Override
    public void execute() {
        System.exit(0); }
}
