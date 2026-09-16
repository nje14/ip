package nyonbot.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nyonbot.NyonBot;

/**
 * Controls the main application window.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private NyonBot nyonBot;

    private final Image userImage = new Image(
            getClass().getResourceAsStream("/static/Kawkaw_battle_idle.png"));
    private final Image botImage = new Image(
            getClass().getResourceAsStream("/static/Kawkaw_battle_spared.png"));
    private final Image errorImage = new Image(
            getClass().getResourceAsStream("/static/Kawkaw_battle_hurt.png"));

    /**
     * Configures the dynamic layout after the FXML fields are available.
     */
    @FXML
    public void initialize() {
        dialogContainer.setFillWidth(true);
        dialogContainer
                .heightProperty()
                .addListener((observable, oldHeight, newHeight) -> scrollToBottom());
    }

    /**
     * Associates this view with the chatbot that processes user commands.
     *
     * @param nyonBot chatbot instance used by this window
     */
    public void setNyonBot(NyonBot nyonBot) {
        this.nyonBot = nyonBot;
    }

    /**
     * Adds the user's message and the resulting bot response to the conversation.
     *
     * @throws IOException if a dialog FXML cannot be loaded
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        String response = nyonBot.respond(input);
        if (response == null || response.isBlank()) {
            return;
        }

        if (isErrorResponse(response)) {
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(response, errorImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getBotDialog(response, botImage));
            userInput.clear();
        }
        userInput.requestFocus();
    }

    private boolean isErrorResponse(String response) {
        return response.startsWith("Nyon...");
    }

    private void scrollToBottom() {
        Platform.runLater(() -> scrollPane.setVvalue(scrollPane.getVmax()));
    }
}
