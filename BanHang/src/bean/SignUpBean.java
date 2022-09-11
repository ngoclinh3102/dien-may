package bean;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/09/04
*/

import model.Customer;
import service.CustomerSessionService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.time.LocalDate;

@ManagedBean(name = "SignUpBean", eager = true)
@ViewScoped
public class SignUpBean extends BaseBean {
    /* DECLARE */
    private Customer signUpCustomer;
    private LocalDate signUpDate;

    /* GETTER & SETTER */
    public Customer getSignUpCustomer() {
        if (signUpCustomer == null) {
            signUpCustomer = new Customer();
        }
        return signUpCustomer;
    }

    public LocalDate getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }

    /* ACTION */
    public void actionSignUp(boolean i) {
        if (validateSignUpForm()) {
            int rs = CustomerSessionService.getSignUp(signUpCustomer);
            if (rs == 1) {
                showInfo("Đăng ký thành công!!");
//            redirect("../ban-hang/login.xhtml");
                signUpCustomer = null;
                signUpDate = null;
            }
            else if (rs == 0) {
                showError("Lỗi kết nối CSDL!!");
            }
            else if (rs == -100) {
                showWarn("Số điện thoại này đã được sử dụng!!");
            }
            else if (rs == -101) {
                showWarn("Địa chỉ mail này đã được sử dụng!!");
            }
            else {
                showError("Lỗi không xác định!!");
            }
        }
    }

    /* VALIDATION */
    private boolean validateSignUpForm() {
        boolean flag = true;
        if (signUpDate != null) {
            signUpCustomer.setBirthday(signUpDate.toString());
        }
        else {
            signUpCustomer.setBirthday("");
        }
        if (signUpCustomer.getLastName().equals("") || signUpCustomer.getFirstName().equals("")) {
            flag = false;
            showWarn("Vui lòng nhập đầy đủ họ tên!!");
        }
        if (signUpCustomer.getPhone().equals("") && signUpCustomer.getMail().equals("")) {
            flag = false;
            showWarn("Vui lòng cung cấp số điện thoại hoặc email!!");
        }
        if (signUpCustomer.getPassword().equals("")) {
            flag = false;
            showWarn("Vui lòng nhập mật khẩu!!");
        }
        return flag;
    }
}
