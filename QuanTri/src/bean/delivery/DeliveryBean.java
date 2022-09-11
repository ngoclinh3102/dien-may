package bean.delivery;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/28
*/

import bean.BaseBean;
import model.Customer;
import model.Delivery;
import model.DeliveryDetail;
import service.CustomerService;
import service.DeliveryService;
import utils.mune.DeliveryStatus;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "DeliveryBean", eager = true)
@ViewScoped
public class DeliveryBean extends BaseBean {
    /* DECLARE */
    private List<Delivery> deliveries;
    private Delivery delivery;
    private Customer customer;

    /* GETTER & SETTER */
    public List<Delivery> getDeliveries() {
        if (deliveries == null) {
            deliveries = DeliveryService.getDeliveries();
        }
        return deliveries;
    }

    public Delivery getDelivery() {
        if (delivery == null) {
            String id = getParameter("id");
            delivery = DeliveryService.getDelivery(Integer.parseInt(id));
        }
        return delivery;
    }

    public int totalDelivery(Delivery delivery) {
        int total = 0;
        for (DeliveryDetail detail : delivery.getDeliveryDetails()) {
            total += detail.getQuantity()*detail.getProductPrice();
        }

        total += delivery.getShippingAgent().getCost();

        if (delivery.getVoucher().getValue()!=0) {
            total = (int) (total * (1-delivery.getVoucher().getValue()/100));
        }

        return total;
    }

    public int totalProductOnly(Delivery delivery) {
        int total = 0;
        for (DeliveryDetail detail : delivery.getDeliveryDetails()) {
            total += detail.getQuantity()*detail.getProductPrice();
        }
        return total;
    }

    public Customer getCustomer() {
        if (customer == null) {
            customer = CustomerService.getCustomer(getDelivery().getCustomerId());
        }
        return customer;
    }

    /* ACTION */
    public void actionUpdateDeliveryStatus(String command) {
        String updateCode = "";
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
        }
        int rs = DeliveryService.putDelivery(getDelivery(),command);
        if (rs==1) {
            getDelivery().setStatus(updateCode);
            showInfo("Cập nhật đơn hàng thành công!!");
        }
        else {
            showError("Lỗi cập nhật trạng thái đơn hàng!!");
        }
    }

    /* RENDERED */
    public boolean renderedCancelDeliveryBtn() {
        return !getDelivery().getStatus().equals(DeliveryStatus.DELIVERED.toString()) &&
                !getDelivery().getStatus().equals(DeliveryStatus.FULFILLED.toString()) &&
                !getDelivery().getStatus().equals(DeliveryStatus.UNFULFILLED.toString()) &&
                !getDelivery().getStatus().equals(DeliveryStatus.CANCELED.toString());
    }

    public boolean renderedUpdateDeliveryStatusBtn(String command) {
        if (command.equals("CONFIRM")) {
            return getDelivery().getStatus().equals(DeliveryStatus.READY.toString());
        }
        if (command.equals("DELIVER")) {
            return getDelivery().getStatus().equals(DeliveryStatus.CONFIRMED.toString());
        }
        if (command.equals("CONFIRM_DELIVERING")) {
            return getDelivery().getStatus().equals(DeliveryStatus.DELIVERING.toString());
        }
        return false;
    }

}
