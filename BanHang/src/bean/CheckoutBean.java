package bean;

import model.Delivery;
import model.DeliveryDetail;
import model.ShippingAgent;
import service.CustomerSessionService;
import service.ShippingAgentService;
import utils.mune.DeliveryStatus;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "CheckoutBean", eager = true)
@ViewScoped
public class CheckoutBean extends BaseBean {
    /* DECLARE */
    private final int customerID = CustomerSessionBean.customer.getId();
    private Delivery delivery;
    private List<String> shippingAgentNames;

    //check voucher
    private String badgeVisibility;
    private String badgeMessage;
    private String badgeStatus;

    /* GETTER & SETTER */
    public Delivery getDelivery() {
        if (delivery == null) {
            delivery = new Delivery();
            delivery.setId(0);
            delivery.setCustomerId(customerID);
            delivery.setDeliveryDetails(CustomerSessionService.getCartDetailsForCheckout(customerID));
            delivery.setStatus(DeliveryStatus.READY.toString());
            delivery.setShippingAgent(getShippingAgents().get(0));
        }
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public int getTotalDelivery() {
        int sum = 0;
        if (delivery.getDeliveryDetails().size()!=0) {
            for (DeliveryDetail deliveryDetail : delivery.getDeliveryDetails()) {
                sum += deliveryDetail.getQuantity() * deliveryDetail.getProductPrice();
            }
        }
        return sum;
    }

    public List<ShippingAgent> getShippingAgents() {
        return ShippingAgentService.getShippingAgents();
    }

    public List<String> getShippingAgentNames() {
        if (shippingAgentNames == null) {
            shippingAgentNames = new ArrayList<>();
            for (ShippingAgent sa : getShippingAgents()) {
                shippingAgentNames.add(sa.getName());
            }
        }
        return shippingAgentNames;
    }

    public String getBadgeVisibility() {
        if (badgeVisibility == null) {
            badgeVisibility = "false";
        }
        return badgeVisibility;
    }

    public String getBadgeMessage() {
        if (badgeMessage == null) {
            badgeMessage = "";
        }
        return badgeMessage;
    }

    public String getBadgeStatus() {
        if (badgeStatus == null) {
            badgeStatus = "";
        }
        return badgeStatus;
    }

    /* ACTION */
    public void actionPostDelivery() {
        if (validationForm()) {
            int rs = CustomerSessionService.postDelivery(delivery);
            if (rs > 0) {
                redirect("../ban-hang/e-commerce/delivery-detail.xhtml?id=" + rs);
            }
            else {
                showError("Post delivery have failed with code = " + rs);
            }
        }
    }

    /* OTHERS */
    public void changeShippingAgent() {
        String name = delivery.getShippingAgent().getName();
        for (ShippingAgent sa : getShippingAgents()) {
            if (sa.getName().equals(name)) {
                delivery.setShippingAgent(sa);
                break;
            }
        }
    }

    public void ajaxCheckVoucher() {
        String code = delivery.getVoucher().getCode();
        if (code==null || code.equals("")) {
            badgeVisibility = "";
            badgeStatus = "";
            badgeMessage = "";
            return;
        }
        float rs = CustomerSessionService.checkVoucher(code);
        if (rs == -404) {
            showError("Không tìm thấy mã giảm giá!!");
            badgeVisibility = "true";
            badgeStatus = "danger";
            badgeMessage = "ERROR";
        }
        else if (rs == -100) {
            showError("Mã giảm giá đã hết hiệu lực!!");
            badgeVisibility = "true";
            badgeStatus = "warning";
            badgeMessage = "ERROR";
        }
        else if (rs == 0 || rs == -1 || rs == -2) {
            showError("Add voucher have failed with code = " + rs);
        }
        else {
            delivery.getVoucher().setValue(rs);
            badgeVisibility = "true";
            badgeStatus = "info";
            badgeMessage = "-"+rs+"%";
        }
    }

    private boolean validationForm() {
        boolean rs = true;
        if (delivery.getShippingAddress() == null || delivery.getShippingAddress().equals("")) {
            rs = false;
            showWarn("Vui lòng nhập địa chỉ!!");
        }
        if (getBadgeMessage().equals("ERROR")) {
            showWarn("Vui lòng nhập lại mã giảm giả đúng!!");
        }
        return rs;
    }
}
