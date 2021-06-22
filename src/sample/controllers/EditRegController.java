package sample.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.BD;
import sample.model.User;

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
    private PasswordField pass_edit_reg;

    @FXML
    private Button btn_edit_reg;

    @FXML
    private TextField attention_edit_reg;

      private User user = new User();

    private BD bd = new BD();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        user = bd.createUser(user);
         fillForm(user);
        btn_edit_reg.setOnAction(event ->{
            if(login_edit_reg.getCharacters().length() <=3) {
                setAttention("Логин меньше 4 символов");
                return;
            }else if(email_edit_reg.getCharacters().length() <=5) {
                setAttention("email меньше 6 символов");
                return;
            }else if(pass_edit_reg.getCharacters().length() <=3) {
                setAttention("Пароль меньше 4 символов");
                return;
            }
            try {
                user.setLogin(login_edit_reg.getCharacters().toString());
                user.setEmail(email_edit_reg.getCharacters().toString());
                user.setPassword(RegController.md5(pass_edit_reg.getCharacters().toString()));

                boolean isEditReg = bd.checkUserExistForBD(user);


                if(isEditReg){
                    login_edit_reg.setText("");
                    email_edit_reg.setText("");
                    pass_edit_reg.setText("");
                    attention_edit_reg.setStyle("-fx-text-fill: Green");
                    attention_edit_reg.setText("Успешно");



                } else {
                    attention_edit_reg.setText("Введите другой логин");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }




        });


    }

    public void setAttention(String element){
        attention_edit_reg.setStyle("-fx-border-color: red");
        attention_edit_reg.setStyle("-fx-text-fill: red");
        attention_edit_reg.setText(element);
    }

    public void fillForm(User user){
        login_edit_reg.setText(user.getLogin());
        email_edit_reg.setText(user.getEmail());
        pass_edit_reg.setText(user.getPassword());
    }
}
