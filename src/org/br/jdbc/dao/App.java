package org.br.jdbc.dao;

import org.br.jdbc.dao.dal.DBConnection;
import org.br.jdbc.dao.dal.PersistenceManager;

import java.sql.*;

public class App {
    public static void main(String[] args){

        try{
            DBConnection.getSingle();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try (Connection connect = PersistenceManager.getConnection();
             Statement st = connect.createStatement();
             ResultSet rs = st.executeQuery("SELECT * from contact"))
        {
            while (rs.next()){
                System.out.println(rs.getString("id_contact"));
            }
        }catch (SQLException e){
            System.out.println("Attention : "+e.getMessage());
        }

    }
}
