package nyonbot.controller;

import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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

    private final Image userImage = loadImage("/static/Kawkaw_battle_idle.png");
    private final Image botImage = loadImage("/static/Kawkaw_battle_spared.png");
    private final Image errorImage = loadImage("/static/Kawkaw_battle_hurt.png");

    static {
        loadApplicationFont("/fonts/big-shot.ttf");
    }

    private static void loadApplicationFont(String resourcePath) {
        URL resource = MainWindow.class.getResource(resourcePath);
        if (resource != null) {
            Font.loadFont(resource.toExternalForm(), 14);
        }
    }

    private Image loadImage(String resourcePath) {
        URL resource = getClass().getResource(resourcePath);
        return resource == null ? null : new Image(resource.toExternalForm());
    }

    /**
     * Configures the dynamic layout after the FXML fields are available.
     */
    @FXML
    public void initialize() {
        dialogContainer.setFillWidth(true);
        dialogContainer.heightProperty().addListener(
                (observable, oldHeight, newHeight) -> scrollToBottom());
    }

    /**
     * Associates this view with the chatbot that processes user commands.
     *
     * @param nyonBot chatbot instance used by this window
     * @throws IOException if the startup warning dialog cannot be loaded
     */
    public void setNyonBot(NyonBot nyonBot) throws IOException {
        this.nyonBot = nyonBot;
        showStartupMessage();
    }

    private void showStartupMessage() throws IOException {
        String startupMessage = nyonBot.getStartupMessage();
        if (!startupMessage.isBlank()) {
            String response = String.format("Nyon... (%s)", startupMessage);
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(response, errorImage));
        }
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
