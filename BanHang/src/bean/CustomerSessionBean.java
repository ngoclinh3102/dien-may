package bean;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/24
*/

import model.CartDetail;
import model.Customer;
import service.CustomerService;
import service.CustomerSessionService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "CustomerSessionBean", eager = true)
@SessionScoped
public class CustomerSessionBean extends BaseBean {
    /* DECLARE */
    private String loginID;
    private String loginPassword;
    public static Customer customer;


    /* GETTER & SETTER */
    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public Customer getCustomer() {
        return customer;
    }

//    public static void setCustomer(Customer customer) {
//        CustomerSessionBean.customer = customer;
//    }

    /* ACTION */
    public void actionLogin() {
        if (loginID == null || loginID.trim().equals("")) {
            showWarn("Không được bỏ trống tên đăng nhập!!");
        }
        else {
            if (loginPassword == null || loginPassword.trim().equals("")) {
                showWarn("Không được bỏ trống mật khẩu!!");
            }
            else {
                int rs = CustomerSessionService.getLogin(loginID.trim(),loginPassword.trim());
                if (rs==-2) {
                    showError("Email hoặc Số điện thoại không tồn tại!!");
                }
                else if (rs==-1) {
                    showError("Sai mật khẩu!!");
                }
                else if (rs==0) {
                    showError("Lỗi kết nối CSDL!!");
                }
                else {
                    customer = CustomerService.getCustomer(rs);
                    loginID = null;
                    loginPassword = null;
                    redirect("../ban-hang/e-commerce/home.xhtml");
                }
            }
        }
    }

    public void actionLogout() {
        customer = null;
    }

    public void actionSignUp(boolean i) {
        // TODO
        System.out.println(i);
    }

    public void actionAddCart(String productCode) {
        if (customer == null) {
            showWarn("Vui lòng đăng nhập trước khi thao tác!!");
        }
        else {
            int rs = CustomerSessionService.postCartDetail(customer.getId(),productCode);
            if (rs==1) {
                showInfo("Đã thêm sản phẩm vào giỏ hàng!!");
            }
            else {
                showError("Có lỗi trong quá trình xử lí!!");
            }
        }
    }
}
