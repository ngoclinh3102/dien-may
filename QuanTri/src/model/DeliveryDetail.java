package model;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/25
*/

public class DeliveryDetail {
    private int deliveryID;
    private String productCode;
    private int quantity;
    private int productPrice;
    //
    private String productName;
    private String productThumbnail;

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(String productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "DeliveryDetail{" +
                "deliveryID=" + deliveryID +
                ", productCode='" + productCode + '\'' +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                ", productName='" + productName + '\'' +
                ", productThumbnail='" + productThumbnail + '\'' +
                '}';
    }
}
