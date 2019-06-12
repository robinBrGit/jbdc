package org.br.jdbc.intro;
import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");
        String url = "jdbc:mysql://127.0.0.1:3306/digi-jdbc?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String login = "root";
        String pwd = "";
        Connection connection = DriverManager.getConnection(url,login,pwd);
        Statement st = connection.createStatement();
        /*st.executeUpdate("INSERT INTO adress (rue,code_postale,ville) VALUES ('44 rue des lilas',44700,'orvault')",Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next() ){
            st.executeUpdate("INSERT INTO contact (email,first_name,last_name,id_adress) VALUES ('damien.test@gmail.com','Damien','Test',"+rs.getLong(1)+")");
        }*/



        ResultSet rsSelect = st.executeQuery("SELECT * from contact c inner join  adress a on c.id_adress=a.id_adress");
        while(rsSelect.next()){
            System.out.println(rsSelect.getString("id_contact")+" "+rsSelect.getString("first_name")+" "+rsSelect.getString("last_name")+" "+rsSelect.getString("rue"));
        }

        //rs.close();
        rsSelect.close();
        st.close();
        connection.close();


    }
}
