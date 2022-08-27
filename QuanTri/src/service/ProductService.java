package service;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/24
*/

import model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends BaseService {
    /* GET PRODUCTS */
    public static List<Product> getProducts() {
        if (getStatement() != null) {
            List<Product> list = new ArrayList<>();
            String sql = "SELECT * FROM product ORDER BY `created_at`";
            try {
                ResultSet resultSet = getStatement().executeQuery(sql);
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setCode(resultSet.getString(1));
                    product.setName(resultSet.getString(2));
                    product.setDesc(resultSet.getString(3));
                    product.setBrand(resultSet.getString(4));
                    product.setCategoryCode(resultSet.getString(5));
                    product.setUnit(resultSet.getString(6));
                    product.setInventory(resultSet.getInt(7));
                    product.setPrice(resultSet.getInt(8));
                    product.setPrice0(resultSet.getInt(9));
                    product.setBought(resultSet.getInt(10));
                    product.setDiscountCode(resultSet.getString(11));
                    product.setStatus(resultSet.getInt(12)==1);
                    product.setWarranty(resultSet.getInt(13));
                    product.setCreatedAt(resultSet.getString(14));

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
                    ResultSet resultSet2 = getStatement().executeQuery(sql2);
                    List<String> images = new ArrayList<>();
                    while (resultSet2.next()) {
                        images.add(resultSet2.getString(1));
                    }
                    product.setImages(images);
                }
                catch (SQLException e) {
                    System.out.println("ProductService.getProducts().getImages("+product.getCode()+"): FAILED");
                    System.out.println("==================================================");
                    e.printStackTrace();
                }
            }
            System.out.println("ProductService.getProducts().getImages(): SUCCESS");
            System.out.println("==================================================");
            return list;
        }
        else {
            System.out.println("ProductService.getProducts(): CANNOT CONNECT TO DATABASE!!");
            System.out.println("==================================================");
        }
        return new ArrayList<>();
    }

    public static List<Product> getProducts(int getProductForSell) {
        System.out.println(getProductForSell);
        if (getStatement() != null) {
            List<Product> list = new ArrayList<>();
            String sql = "SELECT * FROM product WHERE `status`=1 ORDER BY `created_at` DESC";
            try {
                ResultSet resultSet = getStatement().executeQuery(sql);
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setCode(resultSet.getString(1));
                    product.setName(resultSet.getString(2));
                    product.setDesc(resultSet.getString(3));
                    product.setBrand(resultSet.getString(4));
                    product.setCategoryCode(resultSet.getString(5));
                    product.setUnit(resultSet.getString(6));
                    product.setInventory(resultSet.getInt(7));
                    product.setPrice(resultSet.getInt(8));
                    product.setPrice0(resultSet.getInt(9));
                    product.setBought(resultSet.getInt(10));
                    product.setDiscountCode(resultSet.getString(11));
                    product.setStatus(resultSet.getInt(12)==1);
                    product.setWarranty(resultSet.getInt(13));
                    product.setCreatedAt(resultSet.getString(14));

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
                    ResultSet resultSet2 = getStatement().executeQuery(sql2);
                    List<String> images = new ArrayList<>();
                    while (resultSet2.next()) {
                        images.add(resultSet2.getString(1));
                    }
                    product.setImages(images);
                }
                catch (SQLException e) {
                    System.out.println("ProductService.getProducts().getImages("+product.getCode()+"): FAILED");
                    System.out.println("==================================================");
                    e.printStackTrace();
                }
            }
            System.out.println("ProductService.getProducts().getImages(): SUCCESS");
            System.out.println("==================================================");

            List<Product> tempList = new ArrayList<>();
            for (Product p : list) {
                if (p.getInventory()==0) {
                    tempList.add(p);
                }
            }
            list.removeIf(product -> product.getInventory()==0);
            list.addAll(tempList);

            return list;
        }
        else {
            System.out.println("ProductService.getProducts(): CANNOT CONNECT TO DATABASE!!");
            System.out.println("==================================================");
        }
        return new ArrayList<>();
    }

    /* GET PRODUCT BY ID */
    public static Product getProduct(String code) {
        if (getStatement()!=null) {
            String sql = "SELECT * FROM product WHERE `code`='" + code + "'";
            try {
                ResultSet resultSet = getStatement().executeQuery(sql);
                if (resultSet.next()) {
                    Product product = new Product();
                    product.setCode(resultSet.getString(1));
                    product.setName(resultSet.getString(2));
                    product.setDesc(resultSet.getString(3));
                    product.setBrand(resultSet.getString(4));
                    product.setCategoryCode(resultSet.getString(5));
                    product.setUnit(resultSet.getString(6));
                    product.setInventory(resultSet.getInt(7));
                    product.setPrice(resultSet.getInt(8));
                    product.setPrice0(resultSet.getInt(9));
                    product.setBought(resultSet.getInt(10));
                    product.setDiscountCode(resultSet.getString(11));
                    product.setStatus(resultSet.getInt(12)==1);
                    product.setWarranty(resultSet.getInt(13));
                    product.setCreatedAt(resultSet.getString(14));

                    System.out.println("ProductService.getProduct(" + code + "): SUCCESS");
                    System.out.println("==================================================");

                    //get images
                    String sql2 = "SELECT `image` FROM product_img WHERE `product_code`='" + product.getCode() +"'";
                    try {
                        ResultSet resultSet2 = getStatement().executeQuery(sql2);
                        List<String> images = new ArrayList<>();
                        while (resultSet2.next()) {
                            images.add(resultSet2.getString(1));
                        }
                        product.setImages(images);

                        System.out.println("ProductService.getImage(" + code + "): SUCCESS");
                        System.out.println("==================================================");
                        return product;
                    }
                    catch (SQLException e) {
                        System.out.println("ProductService.getImage(" + code + "): FAILED");
                        System.out.println("==================================================");
                        e.printStackTrace();
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Product();
    }

    /* PUT PRODUCT */
    public static int putProduct(Product product) {
        if (getStatement()!=null) {
            String sql = "UPDATE product " +
                            "SET `name`='" + product.getName() + "', " +
                                "`desc`='" + product.getDesc() + "', " +
                                "`brand`='" + product.getBrand() + "', " +
                                "`category_code`='" + product.getCategoryCode() + "', " +
                                "`unit`='" + product.getUnit() + "', " +
                                "`inventory`=" + product.getInventory() + ", " +
                                "`price`='" + product.getPrice() + "', " +
                                "`price_0`=" + product.getPrice0() + ", " +
                                "`bought`=" + product.getBought() + ", " +
                                (product.getDiscountCode()==null?"":("`discount_code`='" + product.getDiscountCode() + "', ")) +
                                "`status`=" + product.isStatus() + ", " +
                                "`warranty`=" + product.getWarranty() + " " +
                            "WHERE `code`='" + product.getCode() + "' ";
            try {
                int rs = getStatement().executeUpdate(sql);
                if (rs == 1) {
                    System.out.println("ProductService.putProduct(): SUCCESS!!");
                    System.out.println("==================================================");
                    return 0;
                }
                else {
                    return -3;
                }
            }
            catch (SQLException e) {
                System.out.println("ProductService.putProduct(): FAILED!!");
                System.out.println("==================================================");
                e.printStackTrace();
                return -2;
            }
        }
        return -1;
    }

    /* DELETE PRODUCT IMAGE */
    public static int deleteProductImage(String productCode, String image) {
        if (getStatement()!=null) {
            String sql = "DELETE FROM product_img WHERE `product_code`='" + productCode + "' AND `image`='" + image + "'";
            try {
                int rs = getStatement().executeUpdate(sql);
                if (rs == 1) {
                    System.out.println("ProductService.deleteProductImage(): SUCCESS");
                    System.out.println("==================================================");
                    return 0;
                }
                else {
                    return -3;
                }
            }
            catch (SQLException e) {
                System.out.println("ProductService.deleteProductImage(): FAILED");
                System.out.println("==================================================");
                e.printStackTrace();
                return -2;
            }
        }
        return -1;
    }

    /* POST PRODUCT IMAGE */
    public static int postProductImage(String productCode, String image) {
        if (getStatement()!=null) {
            String sql = "INSERT INTO product_img (`product_code`,`image`) VALUES ('" + productCode + "','" + image + "')";
            try {
                int rs = getStatement().executeUpdate(sql);
                if (rs == 1) {
                    System.out.println("ProductService.postProductImage(): SUCCESS");
                    System.out.println("==================================================");
                    return 0;
                }
                else {
                    return -3;
                }
            }
            catch (SQLException e) {
                System.out.println("ProductService.postProductImage(): FAILED");
                System.out.println("==================================================");
                e.printStackTrace();
                return -2;
            }
        }
        return -1;
    }

    /* POST PRODUCT */
    public static int postProduct(Product product) {
        if (getStatement() != null) {
            if (product.getDesc() == null || product.getDesc().equals("")) {
                product.setDesc("<chưa có mô tả>");
            }

            String sql =
                    "INSERT INTO `product` (`code`," +
                                            "`name`," +
                                            "`desc`," +
                                            "`brand`," +
                                            "`category_code`," +
                                            "`unit`," +
                                            "`inventory`," +
                                            "`price`," +
                                            "`price_0`," +
                                            "`bought`," +
                                            "`status`," +
                                            "`warranty`, " +
                                            "`created_at`) " +
                            "VALUES ('" + product.getCode() + "'," +
                                    "'" + product.getName() + "'," +
                                    "'" + product.getDesc() + "'," +
                                    "'" + product.getBrand() + "'," +
                                    "'" + product.getCategoryCode() + "'," +
                                    "'" + product.getUnit() + "'," +
                                    "" + product.getInventory() + "," +
                                    "" + product.getPrice() + "," +
                                    "" + product.getPrice0() + "," +
                                    "" + product.getBought() + "," +
                                    "" + (product.isStatus()?"1":"0") + "," +
                                    "" + product.getWarranty() + "," +
                                    "CURRENT_TIMESTAMP()" +
                                    ")";
            try {
                int rs =  getStatement().executeUpdate(sql);
                if (rs != 1) {
                    return -3;
                }
            }
            catch (SQLException e) {
                System.out.println("ProductService.postProduct(): FAILED");
                System.out.println("==================================================");
                e.printStackTrace();
                return -2;
            }

            for (String image : product.getImages()) {
                String sql2 = "INSERT INTO `product_img` (`product_code`,`image`) " +
                        "VALUES ('" + product.getCode() + "','" + image + "')";
                try {
                    int rs = getStatement().executeUpdate(sql2);
                    if (rs == 1) {
                        System.out.println("ProductService.postProduct(): SUCCESS");
                        System.out.println("==================================================");
                        return 0;
                    }
                    else {
                        System.out.println("ProductService.postProduct().postImages(): NOT SUCCESSFUL");
                        System.out.println("==================================================");
                    }
                }
                catch (SQLException e) {
                    System.out.println("ProductService.postProduct().postImages(): FAILED");
                    System.out.println("==================================================");
                    e.printStackTrace();
                    return -2;
                }
            }
        }
        return -1;
    }
}
