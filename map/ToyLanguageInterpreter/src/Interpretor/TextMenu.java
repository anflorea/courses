package Interpretor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by flo on 2016-11-16.
 */
public class TextMenu {
    private Map<String, Cmd> commands;

    public TextMenu() {
        commands = new HashMap<>();
    }

    public void addCommand(Cmd c) {
        commands.put(c.getKey(), c);
    }

    public void printMenu() {
        for (Cmd com : commands.values()) {
            String line = String.format("%4s: %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }
    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.printf("Input the option: ");
            String key = scanner.nextLine();
            Cmd com = commands.get(key);
            if (com == null) {
                System.out.println("Invalid Option");
                continue;
            }
            com.execute();
        }
    }
}
