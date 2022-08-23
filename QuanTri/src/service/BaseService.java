package service;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/19
*/

import java.io.Serializable;
import java.sql.*;

public class BaseService implements Serializable {
    private static Statement statement;
    public static Statement getStatement() {
        if (statement == null) {
            String url = "jdbc:mysql://localhost:3306/dien-may";
            String username = "root";
            String password = "123456";
            try {
                Connection CONNECTION = DriverManager.getConnection(url, username, password);
                System.out.println(" === CONNECTED TO " + CONNECTION.getCatalog() + " ====");
                statement =  CONNECTION.createStatement();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return statement;
    }

    public static void main(String[] args) {
        try {
            statement.executeUpdate("select * from cutomer");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
