package bean;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/24
*/

import model.CartDetail;
import service.CustomerSessionService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "CartBean", eager = true)
@ViewScoped
public class CartBean extends BaseBean {
    /* DECLARE */
    private final int customerID = CustomerSessionBean.customer.getId();
    private List<CartDetail> cartDetails;

    /* GETTER & SETTER */
    public List<CartDetail> getCartDetails() {
        if (cartDetails == null) {
            cartDetails = CustomerSessionService.getCartDetails(customerID);
        }
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public int getTotalCart() {
        if (getCartDetails().size()==0) {
            return 0;
        }
        int sum = 0;
        for (CartDetail cd : cartDetails) {
            if (cd.getStatus()) {
                sum += cd.getProductPrice() * cd.getQuantity();
            }
        }
        return sum;
    }

    /* ACTION */
    public void actionAdjustQuantity(CartDetail cartDetail) {
        int rs = CustomerSessionService.putCartDetail(cartDetail);
        if (rs==1) {
            System.out.println("adjust quantity success!!");
        }
        else {
            System.out.println("adjust quantity failed!!");
        }
    }

    public void actionSwitchStatus(CartDetail cartDetail) {
        int rs = CustomerSessionService.putCartDetail(cartDetail);
        if (rs==1) {
            System.out.println("switch status success!!");
        }
        else {
            System.out.println("switch status failed with code = " + rs);
        }
    }

    public void actionRemoveProduct(String productCode) {
        int rs = CustomerSessionService.deleteCartDetail(customerID,productCode);
        if (rs==1) {
            System.out.println("remove product success!!");
        }
        else {
            System.out.println("remove product failed with code = " + rs);
        }
        cartDetails = CustomerSessionService.getCartDetails(customerID);
    }

    public void actionToCreateOrderPage() {
        // TODO
    }
}
