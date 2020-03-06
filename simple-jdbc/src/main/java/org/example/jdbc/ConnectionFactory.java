package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/employee";
    private static final String username = "root";
    private static final String password = "poorvi";

    private static Connection con ;
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
            return con;

    }

    public static void closeConnection() throws SQLException {
        con.close();
    }
}
