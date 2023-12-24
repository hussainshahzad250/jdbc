package com.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtils {

    public static Connection getHSQLConnection() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        System.out.println("Driver Loaded.");
        String url = "jdbc:hsqldb:data/tutorial";
        return DriverManager.getConnection(url, "sa", "");
    }

    public static Connection getMySqlConnection() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/test";
        String username = "root";
        String password = "P@ssw0rd";
        Class.forName(driver);
        System.out.println("MYSQL driver Loaded.");
        return DriverManager.getConnection(url, username, password);
    }

    public static Connection getOracleConnection() throws Exception {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:caspian";
        String username = "oracle";
        String password = "admin";
        Class.forName(driver);
        System.out.println("ORACLE driver Loaded.");
        return DriverManager.getConnection(url, username, password);
    }
}
