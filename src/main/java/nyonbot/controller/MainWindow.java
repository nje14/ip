package nyonbot.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nyonbot.NyonBot;
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
    private Image errorImage = new Image(this.getClass().getResourceAsStream("/static/Kawkaw_battle_hurt.png"));

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
        if (input.startsWith("bye")) {
            if (!nyonBot.onClose()) {
                dialogContainer.getChildren().add(DialogBox.getUserDialog(input, botImage));
                dialogContainer.getChildren().add(DialogBox.getBotDialog("couldn't save your file", errorImage));
            } else {
                Platform.exit();
            }
        }
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        String response = nyonBot.respond(input);
        if (response.isBlank()) {
            return;
        }
        if (response.startsWith("Nyon...")) {
            dialogContainer.getChildren().add(DialogBox.getBotDialog(response, errorImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getBotDialog(response, botImage));
            userInput.clear();
        }

    }

}
