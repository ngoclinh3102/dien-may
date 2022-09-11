package service;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/28
*/

import model.Delivery;
import model.DeliveryDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryService extends BaseService {
    /* GET DELIVERIES */
    public static List<Delivery> getDeliveries() {
        if (getStatement()!=null) {
            List<Delivery> list = new ArrayList<>();
            String sql =
                    "SELECT * " +
                            "FROM delivery LEFT JOIN shipping_agent ON delivery.shipping_agent_code = shipping_agent.`code` " +
                                            "LEFT JOIN voucher ON delivery.voucher_code = voucher.`code` " +
                            "ORDER BY create_at DESC";
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                while (rs.next()) {
                    Delivery delivery = new Delivery();
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

                    list.add(delivery);
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
    public static Delivery getDelivery(int deliveryID) {
        if (getStatement()!=null) {
            Delivery delivery = new Delivery();
            String sql =
                    "SELECT * " +
                            "FROM delivery LEFT JOIN shipping_agent ON delivery.shipping_agent_code = shipping_agent.`code` " +
                                            "LEFT JOIN voucher ON delivery.voucher_code = voucher.`code` " +
                            "WHERE id=" + deliveryID;
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
                    System.out.println("Fail to get Delivery with deliveryID=?"+deliveryID+" !!");
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
                System.out.println("Fail to get Delivery with deliveryID=?"+deliveryID+" !!");
                throwables.printStackTrace();
            }
        }
        return new Delivery();
    }

    /* PUT DELIVERY : update status*/
    public static int putDelivery(Delivery delivery, String command) {
        if (getStatement() != null) {
            String updateCode;
            switch (command) {
                case "CANCEL": {
                    updateCode = "CANCELED";
                    break;
                }
                case "CONFIRM": {
                    updateCode = "CONFIRMED";
                    break;
                }
                case "DELIVER": {
                    updateCode = "DELIVERING";
                    break;
                }
                case "CONFIRM_DELIVERING": {
                    updateCode = "DELIVERED";
                    break;
                }
                default: return -200;
            }
            String sql = "UPDATE delivery SET delivery.`status`='"+updateCode+"' WHERE delivery.id=" + delivery.getId();

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
