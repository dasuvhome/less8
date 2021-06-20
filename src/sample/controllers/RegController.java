package sample.controllers;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.BD;

public class RegController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField login_auth;
    @FXML
    private TextField pass_auth;

    @FXML
    private TextField login_reg;

    @FXML
    private TextField email_reg;

    @FXML
    private PasswordField pass_reg;

    @FXML
    private CheckBox confidincials;

    @FXML
    private Button btn_reg;

    @FXML
    private Button btn_auth;
    @FXML
    private TextField attention;

    private BD bd = new BD();

    @FXML
    void initialize() {
        btn_reg.setOnAction(event -> {
          //  attention.setStyle("-fx-text-fill: Green");

            if(login_reg.getCharacters().length() <=3) {
                    setAttention("Логин меньше 4 символов");
                return;
            }else if(email_reg.getCharacters().length() <=5) {
                        setAttention("email меньше 6 символов");
                        return;
            }else if(pass_reg.getCharacters().length() <=3) {
                        setAttention("Пароль меньше 4 символов");
                        return;
            }else if(!confidincials.isSelected()) {
                    setAttention("Согласитесь с условием");

            }
                try {
                    String login = login_reg.getCharacters().toString();
                    String email = email_reg.getCharacters().toString();
                    String  password  = md5(pass_reg.getCharacters().toString());
                 boolean isReg = bd.regUser(login, email, password);

                 if(isReg){
                     login_reg.setText("");
                     email_reg.setText("");
                     pass_reg.setText("");
                     attention.setStyle("-fx-text-fill: Green");
                     attention.setText("Успешно");



                 } else {
                     attention.setText("Введите другой логин");
                 }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


        });

        btn_auth.setOnAction(event -> {
//            attention.setStyle("-fx-text-fill: Green");
//            attention.setText("");
            if(login_auth.getCharacters().length() <=3) {
                setAttention("Логин меньше 4 символов");
                return;
            }else if(pass_auth.getCharacters().length() <=3) {
                setAttention("Пароль меньше 4 символов");
                return;
            }

                String login = login_auth.getCharacters().toString();
                String password = md5(pass_auth.getCharacters().toString());
            boolean isAuth = false;
            try {
                isAuth = bd.authUser(login, password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (isAuth) {
                    login_auth.setText("");
                    pass_auth.setText("");
                    attention.setStyle("-fx-text-fill: Green");
                    attention.setText("Успешно");


                } else {
                    attention.setText("Не найден");
                }



        });

    }

    public void setAttention(String element){
        attention.setStyle("-fx-border-color: red");
        attention.setStyle("-fx-text-fill: red");
        attention.setText(element);
    }

    public static String md5(String pass){
            MessageDigest messageDigest = null;
            byte [] digest = new byte[0];
        try {
             messageDigest= MessageDigest.getInstance("MD5");
             messageDigest.reset();
             messageDigest.update(pass.getBytes());
             digest = messageDigest.digest();
       } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1, digest);
            String md5Hex = bigInteger.toString();
            while(md5Hex.length() < 32){
                md5Hex = 0 + md5Hex;
            }
        return md5Hex;
    }
}
