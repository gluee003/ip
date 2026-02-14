package bru.gui;

import bru.Bru;
import bru.util.Pair;
import bru.util.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bru bru;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/stare.png"));
    private Image bruImage = new Image(this.getClass().getResourceAsStream("/images/bro.png"));

    /**
     * Says hello at the start of the session.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
        this.dialogContainer.getChildren()
                .addAll(DialogBox.getDukeDialog(Ui.getWelcomeMsg(Bru.NAME), this.bruImage));
    }

    /**
     * Injects the Bru instance
     */
    public void setBru(Bru bru) {
        this.bru = bru;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bru's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();

        assert input != null : "Input is null";

        Pair<Boolean, String> pair = this.bru.getResponse(input);
        boolean isBye = pair.getFirst();
        String response = pair.getSecond();

        assert response != null : "Reponse is null";

        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getDukeDialog(response, this.bruImage)
        );
        this.userInput.clear();
        if (isBye) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> Platform.exit());
            pause.play();
        }
    }
}

