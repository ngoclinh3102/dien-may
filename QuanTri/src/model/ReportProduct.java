package model;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/09/04
*/

public class ReportProduct {
    private String productCode;
    private String productName;
    private int total;

    public ReportProduct(String productCode, String productName, int total) {
        this.productCode = productCode;
        this.productName = productName;
        this.total = total;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ReportProduct{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", total=" + total +
                '}';
    }
}
