package org.br.jdbc.metadata;

import java.sql.*;
import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://127.0.0.1:3306/digi-jdbc?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String login = "root";
        String pwd = "";
        Connection connection = DriverManager.getConnection(url,login,pwd);

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getTables(connection.getCatalog(),null,"",null);

        System.out.println("liste des tables de la BDD : ");
        while(rs.next()){
            System.out.print(rs.getString("TABLE_NAME")+ " - ");
        }

        String response;
        Statement st = connection.createStatement();
        do{
            System.out.print("Entrez le nom de la table : ");
            response = sc.nextLine();
            if(!response.equals("exit")) {


                ResultSet resultSet = st.executeQuery("SELECT * FROM " + response);
                ResultSetMetaData rsMetaData = resultSet.getMetaData();
                int count = rsMetaData.getColumnCount();
                if (count > 0) {
                    System.out.println("Voici les informations de la table " + response);
                    for (int i = 1; i <= count; ++i) {
                        System.out.printf("%-30s", rsMetaData.getColumnName(i) + "[" + rsMetaData.getColumnTypeName(i) + "]");

                    }
                    System.out.println();
                    System.out.println("=============================================================================================================================");
                    while (resultSet.next()) {
                        for (int i = 1; i <= count; ++i) {
                            System.out.printf("%-30s", resultSet.getString(i));

                        }
                    }
                    System.out.println();
                }
                resultSet.close();
            }
        }while (!response.equalsIgnoreCase("exit"));
        st.close();
        rs.close();
        connection.close();
    }
}
