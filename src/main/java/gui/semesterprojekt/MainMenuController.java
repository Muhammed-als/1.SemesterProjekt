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

public class MainMenuController {
    @FXML
    public Button quitBTN;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Label nameLabelMain;
    PlayerMovement playerMovement = new PlayerMovement();

    public void switchToGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("gameView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(playerMovement.move());
        stage.show();
    }
    public void switchToHow(ActionEvent event) throws IOException {
        String username = nameLabelMain.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("howTo.fxml"));
        root = loader.load();

        HowToController howToController = loader.getController();
        howToController.displayNameHow(username);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
    public void quitBtn(ActionEvent event) throws IOException {
        Stage stage = (Stage) quitBTN.getScene().getWindow();
        stage.close();
    }
    public void displayNameMain(String username){
        nameLabelMain.setText(username);
    }

}
