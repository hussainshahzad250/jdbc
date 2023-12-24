package com.jdbc.student;

import com.jdbc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) throws Exception {

        Connection connection = JdbcUtils.getMySqlConnection();

        ResultSet rs = connection.createStatement().executeQuery("select * from student");

        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
        rs.close();
        connection.close();
    }
}