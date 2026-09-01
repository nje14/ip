package nyonbot;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;


/**
 * Controller class for the main GUI
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    
    private NyonBot nyonBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/static/Kawkaw_battle_idle.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/static/Kawkaw_battle_spared.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setNyonBot(NyonBot nyonBot) {
        this.nyonBot = nyonBot;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        String response = nyonBot.respond(input);
        if (response.isBlank()) {
            return;
        }
        dialogContainer.getChildren().add(DialogBox.getBotDialog(response, botImage));
        userInput.clear();
    }
    
}
