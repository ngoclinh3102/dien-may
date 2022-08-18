package service;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/15
*/

import java.io.Serializable;
import java.sql.*;

public class BaseService implements Serializable {
    private static Statement statement;

    static {
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

    public static Statement getStament() {
        return statement;
    }

//    public static void main(String[] args) {
//        String query = "SELECT * FROM CUSTOMER";
//        try {
//            ResultSet resultSet = getStament().executeQuery(query);
//            System.out.println(resultSet);
//        }
//        catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//    }
}
