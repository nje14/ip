package nyonbot;
public class NyonBot {
    public static void main(String[] args) {
        String banner = ResourceLoader.readTextfile("static/ascii-banner.txt");
        System.out.println(banner);
   
    }
}
