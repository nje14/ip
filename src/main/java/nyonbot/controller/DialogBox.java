package nyonbot.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;

/**
 * Represents one message shown in the conversation.
 */
public class DialogBox extends HBox {

    private static final AudioClip happySound = new AudioClip(
            Objects.requireNonNull(
                    DialogBox.class.getResource("/sounds/Kawkaw_voiceclip_happy_1.wav")).toExternalForm());

    private static final AudioClip sadSound = new AudioClip(
            Objects.requireNonNull(
                    DialogBox.class.getResource("/sounds/Kawkaw_voiceclip_sad_1.wav")).toExternalForm());

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image image) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
        dialog.maxWidthProperty().bind(widthProperty().multiply(0.7));
        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Changes the message layout so that bot messages are aligned to the left.
     * The image itself is not transformed; its orientation is determined by the
     * image file.
     */
    private void flip() {
        ObservableList<Node> components = FXCollections.observableArrayList(getChildren());
        Collections.reverse(components);
        getChildren().setAll(components);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("bot-label");
    }

    /**
     * Applies the visual treatment for an error message.
     */
    private void showError() {
        dialog.getStyleClass().add("error-label");
    }

    /**
     * Creates a right-aligned dialog for a user message.
     *
     * @param text  message entered by the user
     * @param image image displayed alongside the message
     * @return a dialog for the user message
     * @throws IOException if the dialog FXML cannot be loaded
     */
    public static DialogBox getUserDialog(String text, Image image) throws IOException {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.dialog.getStyleClass().add("user-label");
        return dialogBox;
    }

    /**
     * Creates a left-aligned dialog for a normal bot response.
     *
     * @param text  response produced by the bot
     * @param image image displayed alongside the response
     * @return a dialog for the bot response
     * @throws IOException if the dialog FXML cannot be loaded
     */
    public static DialogBox getBotDialog(String text, Image image) throws IOException {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        happySound.play(0.25);
        return dialogBox;
    }

    /**
     * Creates a left-aligned dialog for a bot error response.
     *
     * @param text  error response produced by the bot
     * @param image image displayed alongside the response
     * @return a dialog for the bot error response
     * @throws IOException if the dialog FXML cannot be loaded
     */
    public static DialogBox getErrorDialog(String text, Image image) throws IOException {
        DialogBox dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        dialogBox.showError();
        sadSound.play(0.25);
        return dialogBox;
    }
}
