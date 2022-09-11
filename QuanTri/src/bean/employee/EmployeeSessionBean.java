package bean.employee;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/28
*/

import bean.BaseBean;
import model.Employee;
import service.EmployeeService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "EmployeeSessionBean", eager = true)
@SessionScoped
public class EmployeeSessionBean extends BaseBean {
    /* DECLARE */
    private String loginID;
    private String loginPassword;
    public static Employee employee;

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

    public Employee getEmployee() {
        if (employee == null) {
            redirect("../quan-tri/login.xhtml");
        }
        return employee;
    }

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
                int rs = EmployeeService.getLogin(loginID.trim(), loginPassword.trim());
                if (rs > 0) {
                    employee = EmployeeService.getEmployee(rs);
                    redirect("../quan-tri/manager/product/product-list.xhtml");
                }
                else if (rs == -100) {
                    showError("Sai mật khẩu!!");
                }
                else if (rs == -200) {
                    showError("Số điện thoại không tồn tại!!");
                }
                else {
                    showError("Lỗi kết nối CSDL!!");
                }
            }
        }
    }

    public void actionLogout() {
        employee = null;
        redirect("../quan-tri/login.xhtml");
    }

    /* RENDERED */
//    public boolean renderedHeaderInfo() {
//        return employee!=null;
//    }
}
