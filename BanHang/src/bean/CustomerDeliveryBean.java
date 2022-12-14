package bean;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/28
*/

import model.Customer;
import model.Delivery;
import model.DeliveryDetail;
import service.CustomerSessionService;
import utils.mune.DeliveryStatus;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "CustomerDeliveryBean", eager = true)
@ViewScoped
public class CustomerDeliveryBean extends BaseBean {
    /* DECLARE */
    //list
    private final Customer customer = CustomerSessionBean.customer;
    private final List<Delivery> deliveriesAll = CustomerSessionService.getDeliveries(customer.getId());
    private List<Delivery> deliveries;

    //detail
    private Delivery delivery;
    private String status;

    /* GETTER & SETTER */
    public List<Delivery> getDeliveries() {
        if (deliveries == null) {
            deliveries = deliveriesAll;
        }
        return deliveries;
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

    public Delivery getDelivery() {
        if (delivery == null) {
            String id = getParameter("id");
            delivery = CustomerSessionService.getDelivery(customer.getId(), Integer.parseInt(id));
        }
        return delivery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /* ACTION */
    public void actionCancelDelivery() {
        int rs = CustomerSessionService.putDelivery(delivery,"CANCEL");
        if (rs == 1) {
            delivery.setStatus("CANCEL");
            showInfo("???? h???y ????n h??ng id = " + delivery.getId() + "!!");
        }
        else {
            showError("L???i h???y ????n h??ng!!");
        }
    }

    public void actionFulfillDelivery() {
        int rs = CustomerSessionService.putDelivery(delivery,"FULFILL");
        if (rs == 1) {
            delivery.setStatus("FULFILLED");
            showInfo("???? ho??n th??nh ????n h??ng id = " + delivery.getId() + "!!");
        }
        else {
            showError("L???i ho??n th??nh ????n h??ng!!");
        }
    }

    public void actionFilterDelivery() {
        System.out.println("status: " + status);
        if (!status.equals("all")) {
            deliveries = CustomerSessionService.getDeliveries(customer.getId(), status);
        }
        else {
            deliveries = deliveriesAll;
        }
    }

    /* RENDERED */
    public boolean renderedCancelDeliveryBtn() {
        return delivery.getStatus().equals(DeliveryStatus.READY.toString()) ||
                delivery.getStatus().equals(DeliveryStatus.CONFIRMED.toString());
    }

    public boolean renderedFulFillDeliveryBtn() {
        return delivery.getStatus().equals(DeliveryStatus.DELIVERING.toString()) ||
                delivery.getStatus().equals(DeliveryStatus.DELIVERED.toString());
    }
}
