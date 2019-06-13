package org.br.jdbc.dao.dal;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PersistenceManager {

    private static final int CHECK_CONNECTION_TIMEOUT = 10;
    private static Connection connection;


    private PersistenceManager(){

    }

    public static Connection getConnection() throws SQLException {
        if(null == connection || connection.isClosed() ||!connection.isValid(CHECK_CONNECTION_TIMEOUT)){

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
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (null != connection && !connection.isClosed()) {
            connection.close();
        }
    }
}
