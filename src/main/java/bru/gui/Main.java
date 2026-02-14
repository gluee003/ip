package bru.gui;

import java.io.IOException;

import bru.Bru;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bru using FXML.
 */
public class Main extends Application {
    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 417;
    private Bru bru = new Bru();
    private String mainWindowFxmlPath = "/view/MainWindow.fxml";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(this.mainWindowFxmlPath));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(Main.MIN_HEIGHT);
            stage.setMinWidth(Main.MIN_WIDTH);

            this.bru.initialise();
            fxmlLoader.<MainWindow>getController().setBru(this.bru); // inject the bru instance

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
