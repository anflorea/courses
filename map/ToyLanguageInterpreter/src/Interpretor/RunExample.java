package Interpretor;
import Controller.*;
import Model.CustomException;

/**
 * Created by flo on 2016-11-17.
 */
public class RunExample extends Cmd
{
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{ ctr.executeAll();
        }
        catch (CustomException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } }
