package sample.controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.BD;
import sample.model.User;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_exit, btn_add_atricle;

    @FXML
    private TextField attention;
    @FXML
    private VBox paneVBox;
    private BD bd = new BD();
    private  int idI = 0;
    public static int idIntent;
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        ResultSet res = bd.getArticless();
            while(res.next()) {
                Node node = null;
                try {
                    node = FXMLLoader.load(getClass().getResource("/sample/scene/articles.fxml"));
                    Label title = (Label)node.lookup("#title");
                    Label intro = (Label)node.lookup("#intro");
                    Label id = (Label) node.lookup("#id");
                    title.setText(res.getString("title"));
                    intro.setText(res.getString("intro"));
                    idI = res.getInt("id");
                    id.setText(""+idI);
                       final Node nodeSet = node;
                       node.setOnMouseClicked(event->{
                            idIntent = Integer.parseInt(id.getText());

                           Parent root = null;


                           try {
                               root = FXMLLoader.load(getClass().getResource("/sample/scene/viewArticles.fxml"));
                               Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                               primaryStage.setTitle("Программа");
                               primaryStage.setScene(new Scene(root, 600, 400));
                               primaryStage.show();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }


                       });
                    node.setOnMouseEntered(event-> {
                        nodeSet.setStyle("-fx-background-color: #707173");
                    });
                    nodeSet.setOnMouseExited(mouseEvent -> {
                        nodeSet.setStyle("-fx-background-color: #343434");
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


                HBox hBox = new HBox();
                hBox.getChildren().add(node);
                hBox.setAlignment(Pos.BASELINE_CENTER);
                paneVBox.getChildren().add(hBox);
                paneVBox.setSpacing(10);
            }

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
        btn_add_atricle.setOnAction(event->{
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/sample/scene/addArticle.fxml"));
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
