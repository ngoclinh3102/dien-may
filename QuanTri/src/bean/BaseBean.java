package bean;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/15
*/

import javax.faces.context.FacesContext;
import java.io.Serializable;

public class BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public String getParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }
}
