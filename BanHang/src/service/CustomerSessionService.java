package service;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/25
*/

import model.*;

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

    /* GET SIGN UP : đăng ký */
    public static int getSignUp(Customer signUpCustomer) {
        if (getStatement()!=null) {
            String sql =
                    "CALL SP_CUSTOMERSIGNUP(" +
                            "'" + signUpCustomer.getLastName() + "'," +
                            "'" + signUpCustomer.getFirstName() + "'," +
                            (signUpCustomer.getBirthday().equals("") ? "NULL," : ("'" + (signUpCustomer.getBirthday()) + "',")) +
                            (signUpCustomer.getPhone().equals("") ? "NULL," : ("'" + (signUpCustomer.getPhone()) + "',")) +
                            (signUpCustomer.getMail().equals("") ? "NULL," : ("'" + (signUpCustomer.getMail()) + "',")) +
                            "'" + signUpCustomer.getPassword() + "')";
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                if (rs.next()) {
                    System.out.println("rs: " + rs);
                    return rs.getInt(1);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
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

    /* GET CART_DETAIL FOR CHECKOUT : lấy giỏ hàng để tạo phiếu */
    public static List<DeliveryDetail> getCartDetailsForCheckout(int customerID) {
        if (getStatement() != null) {
            String sql = "SELECT * FROM cart_detail WHERE cart_detail.customer_id=" + customerID + " AND cart_detail.status=1";
            List<DeliveryDetail> list = new ArrayList<>();
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                while (rs.next()) {
                    DeliveryDetail dd = new DeliveryDetail();
                    dd.setDeliveryID(0);
                    dd.setProductCode(rs.getString(2));
                    dd.setQuantity(rs.getInt(3));

                    list.add(dd);
                }
            }
            catch (SQLException e) {
                System.out.println("CustomerService.getCartDetailsForCheckout(): FAILED in 1st step");
                System.out.println("==================================================");
                e.printStackTrace();
                return new ArrayList<>();
            }

            for (DeliveryDetail dd : list) {
                Product p = ProductService.getProduct(dd.getProductCode());
                if (p.getCode().equals(dd.getProductCode())) {
                    dd.setProductName(p.getName());
                    dd.setProductThumbnail(p.getImages().get(0));
                    dd.setProductPrice(p.getPrice());
                }
                else {
                    System.out.println("CustomerService.getCartDetailsForCheckout(): FAILED in 2nd step");
                    System.out.println("==================================================");
                    return new ArrayList<>();
                }
            }

            System.out.println("CustomerService.getCartDetailsForCheckout(): SUCCESS");
            System.out.println("==================================================");
            return list;
        }
        return new ArrayList<>();
    }

    /* CHECK VOUCHER */
    public static float checkVoucher(String code) {
        if (getStatement()!=null) {
            String sql = "CALL SP_CHECKVOUCHER('" + code + "')";
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                if (rs.next()) {
                    return rs.getFloat(1);
                }
                return -2;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return 0;
    }

    /* POST DELIVERY */
    public static int postDelivery(Delivery delivery) {
        if (getStatement() != null) {
            String sql =
                    "CALL SP_POSTDELIVERY('" +
                            delivery.getPaymentMethod() + "', '" +
                            delivery.getShippingAddress() + "', " +
                            delivery.getCustomerId() + ", '" +
                            delivery.getNote() + "', '" +
                            delivery.getShippingAgent().getCode() + "', '" +
                            delivery.getVoucher().getCode() + "')";
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                if (rs.next()) {
                    delivery.setId(rs.getInt(1));
                }
                else {
                    return -2;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }

            for (DeliveryDetail deliveryDetail : delivery.getDeliveryDetails()) {
                String sql2 =
                        "INSERT INTO delivery_detail " +
                                "VALUES (" + delivery.getId() + ",'" +
                                            deliveryDetail.getProductCode() + "'," +
                                            deliveryDetail.getQuantity() + "," +
                                            deliveryDetail.getProductPrice() + ")";
                try {
                    int rs2 = getStatement().executeUpdate(sql2);
                    if (rs2 != 1) {
                        getStatement().executeUpdate("DELETE FROM delivery WHERE id=" + delivery.getId());
                        getStatement().executeUpdate("DELETE FROM delivery_detail WHERE delivery_id=" + delivery.getId());
                        return -3;
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            try {
                //update voucher
                String sql3 ="UPDATE voucher SET used = used + 1 WHERE `code`='"+delivery.getVoucher().getCode()+"'";
                getStatement().executeUpdate(sql3);
                //update cart
                String sql4 ="DELETE FROM cart_detail WHERE customer_id = " + delivery.getCustomerId() + " AND `status` = 1";
                getStatement().executeUpdate(sql4);

                return delivery.getId();
            }
            catch (SQLException e) {
                System.out.println("Add delivery: cannot update voucher.used!!!");
                e.printStackTrace();
            }
        }
        return 0;
    }

    /* GET DELIVERIES */
    public static List<Delivery> getDeliveries(int customerID) {
        if (getStatement()!=null) {
            List<Delivery> list = new ArrayList<>();
            String sql =
                    "SELECT * " +
                            "FROM delivery LEFT JOIN shipping_agent ON delivery.shipping_agent_code = shipping_agent.`code` " +
                                            "LEFT JOIN voucher ON delivery.voucher_code = voucher.`code` " +
                            "WHERE customer_id=" + customerID + " " +
                            "ORDER BY create_at DESC ";
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                while (rs.next()) {
                    Delivery d = new Delivery();
                    d.setId(rs.getInt(1));
                    d.setCreatedAt(rs.getString(2));
                    d.setPaymentMethod(rs.getString(3));
                    d.setShippingAddress(rs.getString(4));
                    d.setCustomerId(rs.getInt(5));
                    d.setEmployeeId(rs.getInt(6));
                    d.setStatus(rs.getString(7));
                    d.setNote(rs.getString(8));
                    d.getShippingAgent().setCode(rs.getString(9));
                    d.getVoucher().setCode(rs.getString(10));

                    d.getShippingAgent().setName(rs.getString(12));
                    d.getShippingAgent().setCost(rs.getInt(13));
                    d.getShippingAgent().setDeliveryAverageTime(rs.getInt(14));

                    d.getVoucher().setDesc(rs.getString(16));
                    d.getVoucher().setValue(rs.getFloat(17));

                    list.add(d);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            if (list.size()!=0) {
                for (Delivery d : list) {
                    List<DeliveryDetail> deliveryDetailList = new ArrayList<>();
                    String sql2 =
                            "SELECT * " +
                                    "FROM delivery_detail LEFT JOIN (SELECT `code`,`name`,image " +
                                                                        "FROM product LEFT JOIN product_img " +
                                                                                        "ON product.`code`=product_img.product_code " +
                                                                        "GROUP BY product_code) P " +
                                                            "ON delivery_detail.product_code = P.`code` " +
                                    "WHERE delivery_id=" + d.getId();
                    try {
                        ResultSet rs2 = getStatement().executeQuery(sql2);
                        while (rs2.next()) {
                            DeliveryDetail dd = new DeliveryDetail();
                            dd.setDeliveryID(rs2.getInt(1));
                            dd.setProductCode(rs2.getString(2));
                            dd.setQuantity(rs2.getInt(3));
                            dd.setProductPrice(rs2.getInt(4));
                            dd.setProductName(rs2.getString(5));
                            dd.setProductThumbnail(rs2.getString(6));

                            deliveryDetailList.add(dd);
                        }
                        if (deliveryDetailList.size()!=0) {
                            d.setDeliveryDetails(deliveryDetailList);
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }
        }
        return new ArrayList<>();
    }

    public static List<Delivery> getDeliveries(int customerID, String status) {
        if (getStatement()!=null) {
            List<Delivery> list = new ArrayList<>();
            String sql =
                    "SELECT * " +
                            "FROM delivery LEFT JOIN shipping_agent ON delivery.shipping_agent_code = shipping_agent.`code` " +
                                            "LEFT JOIN voucher ON delivery.voucher_code = voucher.`code` " +
                            "WHERE customer_id=" + customerID + " AND status = '" + status + "' " +
                            "ORDER BY create_at DESC ";
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                while (rs.next()) {
                    Delivery d = new Delivery();
                    d.setId(rs.getInt(1));
                    d.setCreatedAt(rs.getString(2));
                    d.setPaymentMethod(rs.getString(3));
                    d.setShippingAddress(rs.getString(4));
                    d.setCustomerId(rs.getInt(5));
                    d.setEmployeeId(rs.getInt(6));
                    d.setStatus(rs.getString(7));
                    d.setNote(rs.getString(8));
                    d.getShippingAgent().setCode(rs.getString(9));
                    d.getVoucher().setCode(rs.getString(10));

                    d.getShippingAgent().setName(rs.getString(12));
                    d.getShippingAgent().setCost(rs.getInt(13));
                    d.getShippingAgent().setDeliveryAverageTime(rs.getInt(14));

                    d.getVoucher().setDesc(rs.getString(16));
                    d.getVoucher().setValue(rs.getFloat(17));

                    list.add(d);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            if (list.size()!=0) {
                for (Delivery d : list) {
                    List<DeliveryDetail> deliveryDetailList = new ArrayList<>();
                    String sql2 =
                            "SELECT * " +
                                    "FROM delivery_detail LEFT JOIN (SELECT `code`,`name`,image " +
                                                                        "FROM product LEFT JOIN product_img " +
                                                                                        "ON product.`code`=product_img.product_code " +
                                                                        "GROUP BY product_code) P " +
                                                            "ON delivery_detail.product_code = P.`code` " +
                                    "WHERE delivery_id=" + d.getId();
                    try {
                        ResultSet rs2 = getStatement().executeQuery(sql2);
                        while (rs2.next()) {
                            DeliveryDetail dd = new DeliveryDetail();
                            dd.setDeliveryID(rs2.getInt(1));
                            dd.setProductCode(rs2.getString(2));
                            dd.setQuantity(rs2.getInt(3));
                            dd.setProductPrice(rs2.getInt(4));
                            dd.setProductName(rs2.getString(5));
                            dd.setProductThumbnail(rs2.getString(6));

                            deliveryDetailList.add(dd);
                        }
                        if (deliveryDetailList.size()!=0) {
                            d.setDeliveryDetails(deliveryDetailList);
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }
        }
        return new ArrayList<>();
    }

    /* GET DELIVERY */
    public static Delivery getDelivery(int customerID, int deliveryID) {
        if (getStatement()!=null) {
            Delivery delivery = new Delivery();
            String sql =
                    "SELECT * " +
                            "FROM delivery LEFT JOIN shipping_agent ON delivery.shipping_agent_code = shipping_agent.`code` " +
                                            "LEFT JOIN voucher ON delivery.voucher_code = voucher.`code` " +
                            "WHERE customer_id=" + customerID + " AND id=" + deliveryID;
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                if (rs.next()) {
                    delivery.setId(rs.getInt(1));
                    delivery.setCreatedAt(rs.getString(2));
                    delivery.setPaymentMethod(rs.getString(3));
                    delivery.setShippingAddress(rs.getString(4));
                    delivery.setCustomerId(rs.getInt(5));
                    delivery.setEmployeeId(rs.getInt(6));
                    delivery.setStatus(rs.getString(7));
                    delivery.setNote(rs.getString(8));
                    delivery.getShippingAgent().setCode(rs.getString(9));
                    delivery.getVoucher().setCode(rs.getString(10));

                    delivery.getShippingAgent().setName(rs.getString(12));
                    delivery.getShippingAgent().setCost(rs.getInt(13));
                    delivery.getShippingAgent().setDeliveryAverageTime(rs.getInt(14));

                    delivery.getVoucher().setDesc(rs.getString(16));
                    delivery.getVoucher().setValue(rs.getFloat(17));
                }
                else {
                    System.out.println("Fail to get Delivery with customerId="+customerID+" and deliveryID=?"+deliveryID+" !!");
                    return new Delivery();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            List<DeliveryDetail> list = new ArrayList<>();
            String sql2 =
                    "SELECT * " +
                            "FROM delivery_detail LEFT JOIN (SELECT `code`,`name`,image " +
                                                                "FROM product LEFT JOIN product_img " +
                                                                                "ON product.`code`=product_img.product_code " +
                                                                "GROUP BY product_code) P " +
                                                    "ON delivery_detail.product_code = P.`code` " +
                            "WHERE delivery_id=" + delivery.getId();
            try {
                ResultSet rs2 = getStatement().executeQuery(sql2);
                while (rs2.next()) {
                    DeliveryDetail dd = new DeliveryDetail();
                    dd.setDeliveryID(rs2.getInt(1));
                    dd.setProductCode(rs2.getString(2));
                    dd.setQuantity(rs2.getInt(3));
                    dd.setProductPrice(rs2.getInt(4));
                    dd.setProductName(rs2.getString(6));
                    dd.setProductThumbnail(rs2.getString(7));

                    list.add(dd);
                }
                if (list.size()!=0) {
                    delivery.setDeliveryDetails(list);
                    return delivery;
                }
            }
            catch (SQLException throwables) {
                System.out.println("Fail to get Delivery with customerId="+customerID+" and deliveryID=?"+deliveryID+" !!");
                throwables.printStackTrace();
            }
        }
        return new Delivery();
    }

    /* PUT DELIVERY */
    public static int putDelivery(Delivery delivery, String changingCode) {
        if (getStatement() != null) {
            String sql;
            if (changingCode.equals("CANCEL")) {
                sql = "UPDATE delivery SET delivery.`status`='CANCELED' WHERE delivery.id=" + delivery.getId();
            }
            else if (changingCode.equals("FULFILL")) {
                sql = "UPDATE delivery SET delivery.`status`='FULFILLED' WHERE delivery.id=" + delivery.getId();
            }
            else {
                return -100;
            }
            try {
                int rs = getStatement().executeUpdate(sql);
                if (rs == 1) {
                    return 1;
                }
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

}
