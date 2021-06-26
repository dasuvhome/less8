package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.BD;

public class AddArticleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField title_field;

    @FXML
    private Button btn_add;

    @FXML
    private TextArea intro_field;

    @FXML
    private TextArea intro_field1;

    @FXML
    private TextArea text_field;

    BD bd = new BD();
    @FXML
    void initialize() {
        btn_add.setOnAction(event -> {
            //  attention.setStyle("-fx-text-fill: Green");

            if(title_field.getCharacters().length() <=3) {
                btn_add.setText("Заголовок меньше 4 символов");
                return;
            }else if(intro_field.getText().length() <=5) {
                btn_add.setText("intro меньше 6 символов");
                return;
            }else if(text_field.getText().length() <=3) {
                btn_add.setText("Статья меньше 4 символов");
                return;
            }

                String title = title_field.getCharacters().toString();
                String intro = intro_field.getText();
                String  text  = text_field.getText();

            try {
                bd.addArticle(title, intro, text);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/sample/scene/main.fxml"));
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle("Программа");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

    }
}
