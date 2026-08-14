package nyonbot;

import java.util.Scanner;

public class Ui {
    private static Ui instance = null;
    private static Scanner scanner = new Scanner(System.in);
    private Ui() {
        // Todo
    }

    public static synchronized Ui getInstance() {
        if (instance == null) {
            instance = new Ui();
        }
        return instance;
    }

    public void welcome() {
        banner();

        String welcome = ResourceLoader.readTextfile("static/welcome.txt");
        System.out.println(welcome);
    }

    public void goodbye() {
        System.out.println("Nyon...");
    }

    public void banner() {
        String banner = ResourceLoader.readTextfile("static/ascii-banner.txt");
        System.out.println(banner);
    }

    public String readCommand() {

        String userInput = scanner.nextLine();

        return userInput;
    }

    public void showOutput(String out) {
        System.out.println(out);
    }
}
