package bean;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/15
*/

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

public class BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public String getParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

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

    public void openDialog(String dialogName) {
        PrimeFaces.current().executeScript("PF('" + dialogName + "').show();");
    }

    public void closeDialog(String dialogName) {
        PrimeFaces.current().executeScript("PF('" + dialogName + "').hide();");
    }

    public void redirect(String destination) {
        try {
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/" + destination);
            System.out.println("REDIRECT: " + destination);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
