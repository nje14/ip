package nyonbot;


public class Parser {    
    private static Parser instance = null;
    private Parser() {

    }

    public static synchronized Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }
    public static void parse(String input) {
        String[] command = input.split(" ", 1);
        switch (command[0]) {
            case ("bye"):
   
            case ("nyon"):
     
            default:
    
        }
    }
}
