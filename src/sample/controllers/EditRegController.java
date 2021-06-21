package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EditRegController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_edit_reg;

    @FXML
    private TextField email_edit_reg;

    @FXML
    private PasswordField pass_reg;

    @FXML
    private Button btn_edit_reg;

    @FXML
    private TextField attention_edit_reg;

    @FXML
    void initialize() {

        btn_edit_reg.setOnAction(event ->{



        });


    }
}
