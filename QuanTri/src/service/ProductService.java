package service;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/15
*/

import model.Product;
import utils.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class ProductService extends BaseService {
    /* GET PRODUCTS */
    public static List<Product> getProducts() {
        if (getStament() != null) {
            List<Product> list = new ArrayList<>();
            String sql = "SELECT * FROM product";
            try {
                ResultSet resultSet = getStament().executeQuery(sql);
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setCode(resultSet.getString(1));
                    product.setName(resultSet.getString(2));
                    product.setDesc(resultSet.getString(3));
                    product.setBrand(resultSet.getString(4));
                    product.setCategoryCode(resultSet.getString(5));
                    product.setUnit(resultSet.getString(6));
                    product.setInventory(resultSet.getInt(7));
                    product.setPrice(resultSet.getFloat(8));
                    product.setPrice0(resultSet.getFloat(9));
                    product.setBought(resultSet.getInt(10));
                    product.setDiscountCode(resultSet.getString(11));
                    product.setStatus(resultSet.getInt(12)==1);

                    list.add(product);
                }

                System.out.println("ProductService.getProducts(): SUCCESS");
                System.out.println("==================================================");
            }
            catch (SQLException e) {
                System.out.println("ProductService.getProducts(): FAILED");
                System.out.println("==================================================");
                e.printStackTrace();
            }

            for (Product product : list) {
                String sql2 = "SELECT image FROM product_img WHERE product_code='" + product.getCode() +"'";
                try {
                    ResultSet resultSet2 = getStament().executeQuery(sql2);
                    List<String> images = new ArrayList<>();
                    while (resultSet2.next()) {
                        images.add(resultSet2.getString(1));
                    }
                    product.setImages(images);
                }
                catch (SQLException e) {
                    System.out.println("ProductService.getProducts().getImages(): FAILED");
                    System.out.println("==================================================");
                    e.printStackTrace();
                }
            }
            System.out.println("ProductService.getProducts().getImages(): SUCCESS");
            System.out.println("==================================================");
            return list;
        }
        else {
            Message.showError("Lỗi kết nối database!!");
        }
        return new ArrayList<>();
    }

    public static Product getProduct(String id) {
        if (getStament()!=null) {
            String sql = "SELECT * FROM product WHERE id=" + id;
            try {
                ResultSet resultSet = getStament().executeQuery(sql);
                if (resultSet.next()) {
                    Product product = new Product();
                    product.setCode(resultSet.getString(1));
                    product.setName(resultSet.getString(2));
                    product.setDesc(resultSet.getString(3));
                    product.setBrand(resultSet.getString(4));
                    product.setCategoryCode(resultSet.getString(5));
                    product.setUnit(resultSet.getString(6));
                    product.setInventory(resultSet.getInt(7));
                    product.setPrice(resultSet.getFloat(8));
                    product.setPrice0(resultSet.getFloat(9));
                    product.setBought(resultSet.getInt(10));
                    product.setDiscountCode(resultSet.getString(11));
                    product.setStatus(resultSet.getInt(12)==1);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Product();
    }
}
