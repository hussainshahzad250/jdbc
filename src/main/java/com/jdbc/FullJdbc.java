package com.jdbc;

import com.jdbc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.Statement;

public class FullJdbc {

    public static void main(String[] args) throws Exception {

        Connection conn = JdbcUtils.getMySqlConnection();
        Statement stmt = null;
        //STEP 4: Execute a query
        System.out.println("Creating table in given database...");
        stmt = conn.createStatement();
        String sql = "CREATE TABLE EMPLOYEES (id INTEGER not NULL, first VARCHAR(255), " +
                " last VARCHAR(255), age INTEGER, PRIMARY KEY ( id ))";
        stmt.executeUpdate(sql);
        System.out.println("Created table in given database...");
        System.out.println("Inserting records into the table...");
        stmt = conn.createStatement();
        sql = "INSERT INTO Employees VALUES (100, 'Kriss', 'Kurian', 18)";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO Employees VALUES (101, 'Enrique', 'John', 25)";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO Employees VALUES(102, 'Taylor', 'Swift', 30)";
        stmt.executeUpdate(sql);
        sql = "INSERT INTO Employees VALUES(103, 'Linkin', 'Park', 28)";
        stmt.executeUpdate(sql);
        System.out.println("Inserted records into the table...");
    }
}
