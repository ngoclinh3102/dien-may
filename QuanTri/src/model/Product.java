package model;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/19
*/

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String code;
    private String name;
    private List<String> images;
    private String desc;
    private String brand;
    private String categoryCode;
    private String unit;
    private int inventory;
    private float price;
    private float price0;
    private int bought;
    private String  discountCode;
    private boolean status;
    private int warranty;

    public Product() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImages() {
        if (images == null) {
            images = new ArrayList<>();
        }
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice0() {
        return price0;
    }

    public void setPrice0(float price0) {
        this.price0 = price0;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + "', " +
                "name='" + name + "', " +
                "images=" + images +  "', " +
                "desc='" + "desc" + "', " +
                "brand='" + brand + "', " +
                "categoryCode='" + categoryCode + "', " +
                "unit='" + unit + "', " +
                "inventory=" + inventory +  ", " +
                "price=" + price +  ", " +
                "price0=" + price0 +  ", " +
                "bought=" + bought +  ", " +
                "discountCode='" + discountCode + "', " +
                "status=" + status + " }";
    }
}
