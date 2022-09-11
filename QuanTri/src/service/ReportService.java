package service;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/09/04
*/

import model.ReportProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportService extends BaseService {
    /* REPORT TOP5 PRODUCT */
    public static List<ReportProduct> top5Product(String type) {
        if (getStatement() != null) {
            String sql =
                    "SELECT DD.product_code," +
                            "P.`name`," +
                            "SUM(DD.quantity) " +
                        "FROM (SELECT * " +
                                    "FROM delivery " +
                                    "WHERE `status`='FULFILLED' " +
                                        "AND create_at > DATE_SUB(CURRENT_TIMESTAMP,INTERVAL " + type + ")) D " +
                                "LEFT JOIN delivery_detail DD ON D.id = DD.delivery_id " +
                                "LEFT JOIN product P ON DD.product_code = P.`code` " +
                        "GROUP BY DD.product_code " +
                        "ORDER BY SUM(DD.quantity) DESC " +
                        "LIMIT 5";
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                List<ReportProduct> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(new ReportProduct(rs.getString(1),rs.getString(2),rs.getInt(3)));
                }
                return list;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}
