package nyonbot.controller;

import java.io.IOException;
import java.util.Collections;

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

/**
 * Represents a dialog box for the NyonBot
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> components = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(components);
        getChildren().setAll(components);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
        displayPicture.getStyleClass().add("flipped-image");
    }

    public static DialogBox getUserDialog(String text, Image img) throws IOException {
        return new DialogBox(text, img);
    }

    public static DialogBox getBotDialog(String text, Image img) throws IOException {
        var dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        return dialogBox;
    }

}
