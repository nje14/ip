package nyonbot;

public class Ui {
    private static Ui instance = null;

    private Ui() {
        // Todo
    }

    public static synchronized Ui getInstance() {
        if (instance == null) {
            instance = new Ui();
        }
        return instance;
    }

    public static void welcome() {
        Ui.banner();

        String welcome = ResourceLoader.readTextfile("static/welcome.txt");
        System.out.println(welcome);
    }

    public static void banner() {
        String banner = ResourceLoader.readTextfile("static/ascii-banner.txt");
        System.out.println(banner);
    }
}
