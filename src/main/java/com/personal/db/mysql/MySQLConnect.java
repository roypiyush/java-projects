package com.personal.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnect {

    public static void main(String[] args) {

        String url = "jdbc:mysql://0.0.0.0:3306/mydb";
        String username = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM customers")) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                System.out.printf("id=%s name=%s address=%s\n", id, name, address);
            }

        } catch (SQLException e) {
            System.err.println("Error connecting to MySQL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}