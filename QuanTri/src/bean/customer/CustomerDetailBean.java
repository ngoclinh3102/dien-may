package bean.customer;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/15
*/

import bean.BaseBean;
import model.Customer;
import service.CustomerService;
import utils.Message;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "CustomerDetailBean", eager = true)
@ViewScoped
public class CustomerDetailBean extends BaseBean {
    /*  DECLARE  */
    private final String id = getParameter("id");
    private Customer customer;

    /*  GETTER & SETTER  */
    public Customer getCustomer() {
        if (this.customer==null) {
            this.customer = CustomerService.getCustomer(this.id);
        }
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /*  METHOD  */
    public void actionPutCustomer() {
        long c = CustomerService.putCustomer(customer);
        if (c == 1) {
            System.out.println("hehe!");
            Message.showInfo("Cập nhật khách hàng có id=" + customer.getId() + "!!");
        }
        else {
            System.out.println("huhu!");
            Message.showError("Lỗi cập nhật khách hàng!!");
        }
    }

    /*  RENDERED  */
    public void cba() {
        System.out.println("status: " + customer.isStatus());
    }
}
