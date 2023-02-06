package itstep.learning.db;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class DbDemo {
    public void run() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException: " + ex.getMessage());
            return;
        }

        Connection connection;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_011?useUnicode=true&characterEncoding=UTF-8",
                    "user_011", "pass_011"
            );
        }
        catch (SQLException ex) {
            System.err.println("Connection error: " + ex.getMessage());
            return;
        }

        System.out.println("Connection OK");

        String sql = "CREATE TABLE IF NOT EXISTS rands (id CHAR(36) PRIMARY KEY, num INT, str VARCHAR(32)) ENGINE=INNODB DEFAULT CHARSET=utf8";

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
        catch (SQLException ex) {
            System.err.println("CREATE error: " + ex.getMessage());
            return;
        }

        System.out.println("CREATE OK");

        Random random = new Random();
        /*sql = "INSERT INTO rands VALUES (UUID(), ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, random.nextInt(1000));
            preparedStatement.setString(2, "str" + random.nextInt(1000));
            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            System.err.println("INSERT error: " + ex.getMessage());
            return;
        }*/

        System.out.println("INSERT OK");

        sql = "SELECT id, num, str FROM rands";

        try(Statement statement = connection.createStatement()) {

            ResultSet res = statement.executeQuery(sql);

            while(res.next()) {
                System.out.println(res.getString(1) + " " + res.getInt(2) + " " + res.getString(3));
            }

            res.close();
        }
        catch (SQLException ex) {
            System.err.println("SELECT error: " + ex.getMessage());
            return;
        }

        System.out.println("------------------------------------------------------------------");

        sql = "SELECT id, num, str FROM rands WHERE `num` < ?";

        Scanner scanner = new Scanner(System.in);

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            System.out.println("Enter your number: ");
            int num = scanner.nextInt();
            preparedStatement.setInt(1, num);
            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {
                System.out.println(res.getString(1) + " " + res.getInt(2) + " " + res.getString(3));
            }

            res.close();
        }
        catch (SQLException ex) {
            System.err.println("SELECT  prepared statement error: " + ex.getMessage());
            return;
        }

        System.out.println("------------------------------------------------------------------");

        sql = "SELECT id, num, str FROM rands WHERE `str` LIKE ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            System.out.println("Enter your string of part of string: ");
            String strPattern = scanner.next();
            preparedStatement.setString(1, "%" + strPattern + "%");
            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {
                System.out.println(res.getString(1) + " " + res.getInt(2) + " " + res.getString(3));
            }

            res.close();
        }
        catch (SQLException ex) {
            System.err.println("SELECT  prepared statement error: " + ex.getMessage());
            return;
        }
    }
}

/*
CREATE DATABASE java_011 ;
CREATE USER 'user_011'@'localhost' IDENTIFIED BY 'pass_011' ;
GRANT ALL PRIVILEGES ON java_011.* TO user_011 ;
FLUSH PRIVILEGES ;
 */
