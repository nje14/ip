package nyonbot;
public class NyonBot {
    public static void main(String[] args) {
        String banner = ResourceLoader.readTextfile("static/ascii-banner.txt");
        System.out.println(banner);
        String welcome = ResourceLoader.readTextfile("static/welcome.txt");
        System.out.println(welcome);
        String goodbye = ResourceLoader.readTextfile("static/goodbye.txt");
        System.out.println(goodbye);
    }
}
    