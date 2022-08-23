package service;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/15
*/

import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService extends BaseService {

    /* GET CUSTOMERS */
    public static ArrayList<Customer> getCustomers() {
        if (getStatement() != null) {
            ArrayList<Customer> list = new ArrayList<>();
            String query = "SELECT * FROM CUSTOMER";
            try {
                ResultSet resultSet = getStatement().executeQuery(query);
                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getInt(1));
                    customer.setFirstName(resultSet.getString(2));
                    customer.setLastName(resultSet.getString(3));
                    customer.setBirthday(resultSet.getString(4));
                    customer.setPhone(resultSet.getString(5));
                    customer.setMail(resultSet.getString(6));
                    customer.setAvatar(resultSet.getString(7));
                    customer.setPassword(resultSet.getString(8));
                    customer.setStatus(resultSet.getInt(9) == 1);
                    list.add(customer);
                }
                System.out.println("CustomerService.getCustomers(): SUCCESS");
                System.out.println("==================================================");
            }
            catch (SQLException e) {
                System.out.println("CustomerService.getCustomers(): FAILED");
                System.out.println("==================================================");
                e.printStackTrace();
            }
            return list;
        }
        return new ArrayList<>();
    }

    /* GET CUSTOMER */
    public static Customer getCustomer(String id) {
        Customer customer = new Customer();
        if (getStatement() != null) {
            String query = "SELECT * FROM CUSTOMER WHERE ID=" + id;
            try {
                ResultSet resultSet = getStatement().executeQuery(query);
                if (resultSet.next()) {
                    customer.setId(resultSet.getInt(1));
                    customer.setFirstName(resultSet.getString(2));
                    customer.setLastName(resultSet.getString(3));
                    customer.setBirthday(resultSet.getString(4));
                    customer.setPhone(resultSet.getString(5));
                    customer.setMail(resultSet.getString(6));
                    customer.setAvatar(resultSet.getString(7));
                    customer.setPassword(resultSet.getString(8));
                    customer.setStatus(resultSet.getInt(9) == 1);
                }
                System.out.println("CustomerService.getCustomer(): SUCCESS");
                System.out.println("==================================================");
            }
            catch (SQLException e) {
                System.out.println("CustomerService.getCustomer(): FAILED");
                System.out.println("==================================================");
                e.printStackTrace();
            }
        }
        return customer;
    }

    /* PUT CUSTOMER */
    public static long putCustomer(Customer customer) {
        if (getStatement() != null) {
            String sql =
                    "UPDATE CUSTOMER " +
                            "SET first_name = '" + customer.getFirstName() + "', " +
                            "last_name = '" + customer.getLastName() + "', " +
                            "birthday = '" + customer.getBirthday() + "', " +
                            "phone = '" + customer.getPhone() + "', " +
                            "mail = '" + customer.getMail() + "', " +
                            "avatar = '" + customer.getAvatar() + "', " +
                            "password = '" + customer.getPassword() + "', " +
                            "status = " + (customer.isStatus() ? 1 : 0) + " " +
                            "WHERE id=" + customer.getId();
            try {
                long count = getStatement().executeUpdate(sql);
                System.out.println("count: " + count);
                return 1;
            }
            catch (SQLException e) {
                System.out.println(e.getErrorCode() + ":" + e.getMessage());
                e.printStackTrace();
            }
        }
        return 0;
    }
}
