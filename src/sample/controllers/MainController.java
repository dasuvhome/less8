package sample.controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.User;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_exit;

    @FXML
    private TextField attention;

    @FXML
    void initialize() {

        btn_exit.setOnAction(actionEvent -> {
            try {
                FileOutputStream fos = new FileOutputStream("user.settings");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                  oos.writeObject(new User(""));
                Parent root = null;

                root = FXMLLoader.load(getClass().getResource("/sample/scene/sample.fxml"));
                Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                primaryStage.setTitle("Программа");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
