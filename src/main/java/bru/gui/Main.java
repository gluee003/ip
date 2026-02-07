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

    private Bru bru = new Bru();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            this.bru.initialise();
            fxmlLoader.<MainWindow>getController().setBru(this.bru); // inject the bru instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
