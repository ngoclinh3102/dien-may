package service;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/28
*/

import model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeService extends BaseService {
    /* GET LOGIN */
    public static int getLogin(String loginID, String loginPassword) {
        System.out.println("loginID:" + loginID + "loginPassword:" + loginPassword);
        if (getStatement() != null) {
            String sql = "CALL SP_ADMINLOGIN('" + loginID + "','" + loginPassword + "');";
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    /* GET EMPLOYEE */
    public static Employee getEmployee(int id) {
        if (getStatement()!=null) {
            String sql = "SELECT * FROM `employee` WHERE id=" + id;
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                if (rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt(1));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setRole(rs.getString(4));
                    employee.setBirthday(rs.getString(5));
                    employee.setPhone(rs.getString(6));
                    employee.setStatus(rs.getInt(7)==1);
                    employee.setPassword(rs.getString(8));

                    return employee;
                }
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return new Employee();
    }
}
