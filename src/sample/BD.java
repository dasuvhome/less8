package sample;

import sample.model.User;

import java.sql.*;

public class BD {
    private final String HOST = "localhost";
    private final String PORT = "3307";
    private final String DB_NAME = "db_suvorov";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";

     private Connection dbConnection = null;
    private Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connStr, LOGIN, PASSWORD);

        return dbConnection;
    }

     public void isConnected () throws SQLException, ClassNotFoundException {
        dbConnection = getDBConnection();
         System.out.println(dbConnection.isValid(1000));
     }

     public  boolean regUser(String login, String email, String password) throws SQLException, ClassNotFoundException {
         Statement statement = getDBConnection().createStatement();

        ResultSet res = statement.executeQuery("SELECT * FROM `users` WHERE `login` = '" + login + "' LIMIT 1");
           if (res.next()){
               return false;
           }
        String sql = "INSERT INTO `users`(`login`, `email`, `password`) VALUES(?,?,?)";
            PreparedStatement psmt = getDBConnection().prepareStatement(sql);
            psmt.setString(1, login);
            psmt.setString(2, email);
            psmt.setString(3, password);
            psmt.executeUpdate();
        return true;

     }

     public boolean authUser(String login, String password) throws SQLException, ClassNotFoundException {
        Statement statement = getDBConnection().createStatement();
            String sql = "SELECT * FROM `users` WHERE `login` = '" + login + "' AND `password` = '" + password + "'  LIMIT 1";
            ResultSet res = statement.executeQuery(sql);

                return res.next();


     }

    public boolean regEditUser(User user) throws SQLException, ClassNotFoundException {

            String sql = "UPDATE `users` SET `login` = ?,  `email` = ?, `password` = ? WHERE `id` = ?;";
            PreparedStatement psmt = getDBConnection().prepareStatement(sql);
            int id = user.getId();
            String login = user.getLogin();
            String email = user.getEmail();
            String password = user.getPassword();
            psmt.setString(1, login);
            psmt.setString(2, email);
            psmt.setString(3, password);
            psmt.setInt(4, id);
            psmt.executeUpdate();
            return true;


    }

    public User createUser(User user) throws SQLException, ClassNotFoundException {

        Statement statement = getDBConnection().createStatement();
        String sql = "SELECT * FROM `users` WHERE `id` = '" + 14 + "'";
        ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
            }

        return user;
    }
}
