package com.jdbc.survey;

import com.jdbc.utils.JdbcUtils;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = JdbcUtils.getMySqlConnection();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            conn.setAutoCommit(false);
            stmt.addBatch("INSERT INTO survey(id, name) VALUES('11', 'Alex')");
            stmt.addBatch("INSERT INTO survey(id, name) VALUES('22', 'Mary')");
            stmt.addBatch("INSERT INTO survey(id, name) VALUES('33', 'Bob')");
            int[] updateCounts = stmt.executeBatch();
            System.out.println(updateCounts.length);
            conn.commit();
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
            e.printStackTrace();
            System.err.println("Exception: " + e.getMessage());
        } finally {
            assert rs != null;
            rs.close();
            stmt.close();
            conn.close();
        }
    }
}

        