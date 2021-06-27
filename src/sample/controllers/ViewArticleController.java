package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.BD;

public class ViewArticleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_title;

    @FXML
    private Button btn_back_to_articles;

    @FXML
    private TextArea area_text_intro;

    BD bd = new BD();


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
            int i = MainController.idIntent;
           ResultSet res =  bd.getArticless();
           while(res.next()){
               int id = res.getInt("id");
               if(i == id){
                   lbl_title.setText(res.getString("title"));
                   area_text_intro.setText(res.getString("intro"));
               }
           }

            btn_back_to_articles.setOnAction(event->{
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
