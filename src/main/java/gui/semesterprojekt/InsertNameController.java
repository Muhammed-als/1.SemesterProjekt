package gui.semesterprojekt;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InsertNameController {
    @FXML
    TextField nameTextField;

    public Button quitBTN;
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void insertName(ActionEvent event) throws IOException {
       if (nameTextField.getText().trim().isEmpty()) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Name missing");
           alert.setHeaderText(null);
           alert.setContentText("Insert a name before proceding!");

           alert.showAndWait();
       }

        else {
            String username = nameTextField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        root = loader.load();

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.displayNameMain(username);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene); }
    }
}
