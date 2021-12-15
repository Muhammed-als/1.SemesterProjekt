package gui.semesterprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            URL location = HelloApplication.class.getResource("insertName.fxml");
            Parent root = FXMLLoader.load(location);

            Scene insertName = new Scene(root);
            Image icon = new Image(String.valueOf(getClass().getResource("Wave.png")));
            stage.getIcons().add(icon);
            stage.setTitle("World Of Zea");
            stage.setResizable(false);

            stage.setScene(insertName);
            stage.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}