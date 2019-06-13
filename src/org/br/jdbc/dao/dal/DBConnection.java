/*
Class Singleton
 */
package org.br.jdbc.dao.dal;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static DBConnection single;
    private Connection connection;

    private DBConnection() throws SQLException {
        Properties props = new Properties();
        try(FileInputStream fis = new FileInputStream("resources/conf.properties")){
            props.load(fis);
        }
        catch (IOException e){

        }
        String url = props.getProperty("ds.jdbc.url");
        String login = props.getProperty("ds.user.login");
        String pwd = props.getProperty("ds.user.pwd");
        connection = DriverManager.getConnection(url,login,pwd);
    }
    public static DBConnection getSingle() throws SQLException {
        if(null == single){
            single = new DBConnection();
        }
        return single;
    }
}
