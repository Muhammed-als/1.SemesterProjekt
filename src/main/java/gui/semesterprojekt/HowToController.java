package gui.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class HowToController {
    @FXML
    public Button quitBTN;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label nameLabelHow;

    public void switchToMenu(ActionEvent event) throws IOException {
        String username = nameLabelHow.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        root = loader.load();

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.displayNameMain(username);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
    public void displayNameHow(String username) throws IOException {
        nameLabelHow.setText(username);
    }
}
