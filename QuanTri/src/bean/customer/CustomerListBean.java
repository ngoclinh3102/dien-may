package bean.customer;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/15
*/

import bean.BaseBean;
import model.Customer;
import service.CustomerService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;

@ManagedBean(name = "CustomerListBean", eager = true)
@ViewScoped
public class CustomerListBean extends BaseBean {
    private ArrayList<Customer> customers;

    public ArrayList<Customer> getCustomers() {
        if (customers==null) {
            customers = CustomerService.getCustomers();
        }
        return customers;
    }
}
