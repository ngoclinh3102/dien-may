package utils;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/15
*/

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "Message")
@RequestScoped
public class Message {

    public static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public static void showInfo(String message) {
        addMessage(FacesMessage.SEVERITY_INFO, message,"");
    }

    public static void showWarn(String message) {
        addMessage(FacesMessage.SEVERITY_WARN, message,"");
    }

    public static void showError(String message) {
        addMessage(FacesMessage.SEVERITY_ERROR, message,"");
    }

    public static void showSticky(String message) {
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, message,""));
    }

    public static void showMultiple(String message) {
        addMessage(FacesMessage.SEVERITY_INFO, message,"1");
        addMessage(FacesMessage.SEVERITY_WARN, message,"2");
        addMessage(FacesMessage.SEVERITY_ERROR, message,"3");
    }
}
