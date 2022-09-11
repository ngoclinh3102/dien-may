package model;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/25
*/

import java.util.ArrayList;
import java.util.List;

public class Delivery {
    private int id;
    private String createdAt;
    private String paymentMethod;
    private String shippingAddress;
    private ShippingAgent shippingAgent;
    private String status;
    private String note;
    private int customerId;
    private int employeeId;
    private List<DeliveryDetail> deliveryDetails;
    private Voucher voucher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public List<DeliveryDetail> getDeliveryDetails() {
        if (deliveryDetails == null) {
            deliveryDetails = new ArrayList<>();
        }
        return deliveryDetails;
    }

    public void setDeliveryDetails(List<DeliveryDetail> deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public ShippingAgent getShippingAgent() {
        if (shippingAgent == null) {
            shippingAgent = new ShippingAgent();
        }
        return shippingAgent;
    }

    public void setShippingAgent(ShippingAgent shippingAgent) {
        this.shippingAgent = shippingAgent;
    }

    public Voucher getVoucher() {
        if (voucher == null) {
            voucher = new Voucher();
        }
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
}
