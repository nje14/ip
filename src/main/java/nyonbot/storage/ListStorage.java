package nyonbot.storage;

/**
 * A singleton list storage class for handling NyonBot's lists
 */
public class ListStorage extends Storage {
    private static ListStorage instance = null;
    /**
     * creates the ListStorage
     */
    private ListStorage() {
        super("data/nyonbot.txt");
    }

    /**
     * Instance getter
     */
    public static ListStorage getInstance() {
        if (instance == null) {
            instance = new ListStorage();
        }
        return instance;
    }
}
