package nyonbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        String welcome = ResourceLoader.readTextFile("static/welcome.txt");
        System.out.println(welcome);
    }

    public void goodbye() {
        String out = ResourceLoader.readTextFile("static/goodbye.txt");
        System.out.println(out);
    }

    public void banner() {
        String banner = ResourceLoader.readTextFile("static/ascii-banner.txt");
        System.out.println(banner);
    }

    public String readCommand() {

        String userInput = scanner.nextLine();

        return userInput;
    }

    public void showOutput(String out) {
        System.out.println(out);
    }

    public static String showDate(LocalDateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return dateTime.format(dtf);
    }
}
