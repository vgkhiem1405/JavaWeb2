package com.example.demo1.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectDB {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employee_java_api", "postgres", "camvoyeu1");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}
