package model;

public class ShippingAgent {
    private String code;
    private String name;
    private int cost;
    private int deliveryAverageTime;

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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDeliveryAverageTime() {
        return deliveryAverageTime;
    }

    public void setDeliveryAverageTime(int deliveryAverageTime) {
        this.deliveryAverageTime = deliveryAverageTime;
    }

    @Override
    public String toString() {
        return "ShippingAgent{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", deliveryAverageTime=" + deliveryAverageTime +
                '}';
    }
}
