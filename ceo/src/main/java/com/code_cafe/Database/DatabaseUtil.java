package com.code_cafe.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/yourdatabase";
    private static final String USER = "yourusername";
    private static final String PASS = "yourpassword";

    public static Connection connect() throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected to the database.");
        return conn;
    }

    public static void fetchData() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM yourtable")) {

            while (rs.next()) {
                System.out.println("Data: " + rs.getString("columnname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
