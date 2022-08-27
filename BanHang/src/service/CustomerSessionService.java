package service;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/25
*/

import model.CartDetail;
import model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerSessionService extends BaseService {
    /* GET LOGIN : đăng nhập */
    public static int getLogin(String loginID, String loginPassword) {
        System.out.println("loginID:" + loginID + "loginPassword:" + loginPassword);
        if (getStatement() != null) {
            String sql = "CALL SP_LOGIN('" + loginID + "','" + loginPassword + "');";
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                if (rs.next()) {
                    System.out.println("CustomerService.getLogin(): rs = " + rs.getInt(1));
                    System.out.println("==================================================");
                    return rs.getInt(1);
                }
            }
            catch (SQLException e) {
                System.out.println("CustomerService.getLogin(): FAILED");
                System.out.println("==================================================");
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    /* POST CART_DETAIL : thêm sản phẩm vào giỏ hàng */
    public static int postCartDetail(int customerID, String productCode) {
        if (getStatement() != null) {
            String sql = "CALL SP_POSTCARTDETAIL('" + customerID + "','" + productCode + "')";
            try {
                ResultSet resultSet = getStatement().executeQuery(sql);
                if (resultSet.next()) {
                    int rs = resultSet.getInt(1);
                    System.out.println("CustomerService.postCartDetail(): SUCCESS with code = " + rs);
                    System.out.println("==================================================");
                    return 1;
                }
                else {
                    System.out.println("CustomerService.getLogin(): unsuccessful");
                    System.out.println("==================================================");
                    return -2;
                }
            }
            catch (SQLException e) {
                System.out.println("CustomerService.getLogin(): FAILED");
                System.out.println("==================================================");
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    /* GET CART_DETAILS */
    public static List<CartDetail> getCartDetails(int customerID) {
        if (getStatement() != null) {
            List<CartDetail> list = new ArrayList<>();
            String sql = "SELECT * FROM cart_detail WHERE cart_detail.customer_id=" + customerID;
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                while (rs.next()) {
                    CartDetail cd = new CartDetail();
                    cd.setCustomerId(rs.getInt(1));
                    cd.setProductCode(rs.getString(2));
                    cd.setQuantity(rs.getInt(3));
                    cd.setStatus(rs.getInt(4) == 1);

                    list.add(cd);
                }
            }
            catch (SQLException e) {
                System.out.println("CustomerService.getCartDetails(): FAILED in 1st step");
                System.out.println("==================================================");
                e.printStackTrace();
            }

            for (CartDetail cd : list) {
                Product p = ProductService.getProduct(cd.getProductCode());
                if (p.getCode().equals(cd.getProductCode())) {
                    cd.setProductName(p.getName());
                    cd.setProductThumbnail(p.getImages().get(0));
                    cd.setProductPrice(p.getPrice());
                    cd.setProductPrice0(p.getPrice0());
                }
                else {
                    System.out.println("CustomerService.getCartDetails(): FAILED in 2nd step");
                    System.out.println("==================================================");
                    return new ArrayList<>();
                }
            }
            System.out.println("CustomerService.getCartDetails(): SUCCESS");
            System.out.println("==================================================");
            return list;
        }
        return new ArrayList<>();
    }

    /* PUT CART_DETAIL */
    public static int putCartDetail(CartDetail cartDetail) {
        if (getStatement() != null) {
            String sql =
                    "UPDATE cart_detail " +
                            "SET cart_detail.quantity=" + cartDetail.getQuantity() + ",cart_detail.`status`=" + cartDetail.getStatus() + " " +
                            "WHERE cart_detail.customer_id=" + cartDetail.getCustomerId() +
                            " AND cart_detail.product_code='" + cartDetail.getProductCode() + "'";
            try {
                int rs = getStatement().executeUpdate(sql);
                if (rs == 1) {
                    return 1;
                }
                else {
                    return -2;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    /* DELETE CART_DETAIL */
    public static int deleteCartDetail(int customerID, String productCode) {
        if (getStatement() != null) {
            String sql =
                    "DELETE FROM cart_detail " +
                            "WHERE cart_detail.customer_id=" + customerID + " AND cart_detail.product_code='" + productCode + "'";
            try {
                int rs = getStatement().executeUpdate(sql);
                if (rs == 1) {
                    return 1;
                }
                else {
                    return -2;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }
}
