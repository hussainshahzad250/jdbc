package com.jdbc.product;

import com.jdbc.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = JdbcUtils.getMySqlConnection();
        try {
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            st.addBatch("INSERT INTO product (id, name, price) VALUE ('1', 'Laptop',12)");
            st.addBatch("INSERT INTO product (id, name, price) VALUE ('2', 'Mobile',19)");
            st.addBatch("INSERT INTO product (id, name, price) VALUE ('3', 'TV',25)");
            st.addBatch("INSERT INTO product (id, name, price) VALUE ('4', 'Bulb',50)");

            int[] updateCounts = st.executeBatch();
            conn.commit();
            System.out.println(updateCounts.length+ " records inserted successfully");
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
    }
}