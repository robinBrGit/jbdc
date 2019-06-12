package org.br.jdbc.optim;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/digi-jdbc?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String login = "root";
        String pwd = "";

        try (Connection connect = DriverManager.getConnection(url,login,pwd);
             Statement st = connect.createStatement();
             ResultSet rs = st.executeQuery("SELECT * from contact"))
        {

        }catch (SQLException e){

        }
    }
}
