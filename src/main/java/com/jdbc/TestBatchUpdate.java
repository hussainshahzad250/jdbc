package com.jdbc;

import com.jdbc.utils.JdbcUtils;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.BatchUpdateException;

public class TestBatchUpdate {


    public static void main(String[] args) throws Exception {
        Connection conn = JdbcUtils.getMySqlConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            conn.setAutoCommit(false);
            stmt.addBatch("INSERT INTO survey(id, name) VALUES('15', 'A')");
            stmt.addBatch("INSERT INTO survey(id, name) VALUES('16', 'B')");
            stmt.addBatch("INSERT INTO survey(id, name) VALUES('17', 'C')");
            int[] updateCounts = stmt.executeBatch();
            conn.commit();
            System.out.println(updateCounts.length+ " records updated successfully");
            rs = stmt.executeQuery("SELECT * FROM survey");
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                System.out.println("id=" + id + "  name=" + name);
            }

        } catch (BatchUpdateException b) {
            System.err.println("SQLException: " + b.getMessage());
            System.err.println("SQLState: " + b.getSQLState());
            System.err.println("Message: " + b.getMessage());
            System.err.println("Vendor error code: " + b.getErrorCode());
            System.err.print("Update counts: ");
            int[] updateCounts = b.getUpdateCounts();
            for (int i = 0; i < updateCounts.length; i++) {
                System.err.print(updateCounts[i] + " ");
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("Message: " + ex.getMessage());
            System.err.println("Vendor error code: " + ex.getErrorCode());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
            stmt.close();
            conn.close();
        }
    }
}
