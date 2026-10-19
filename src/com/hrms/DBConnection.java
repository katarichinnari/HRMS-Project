package com.hrms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        String url = "jdbc:mysql://localhost:3306/hrms";
        String username = "root";
        String password = "Katari@2003";

        try {
            con = DriverManager.getConnection(
                url, username, password
            );

            System.out.println("Database connected successfully!");

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        return con;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
