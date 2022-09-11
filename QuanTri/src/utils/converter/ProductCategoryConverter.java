package utils.converter;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/25
*/

import utils.mune.Category;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.HashMap;

@FacesConverter("ProductCategoryConverter")
@ManagedBean(name = "ProductCategoryConverter")
@RequestScoped
public class ProductCategoryConverter implements Converter {
    private HashMap<String, String> categoryMap;

    public HashMap<String, String> getCategoryMap() {
        if (categoryMap == null) {
            categoryMap = new HashMap<>();
            categoryMap.put(Category.AIR_CON.toString(), "Máy điều hòa");
            categoryMap.put(Category.FRIDGE.toString(), "Tủ lạnh");
            categoryMap.put(Category.LAPTOP.toString(), "Laptop");
            categoryMap.put(Category.PHONE.toString(), "Điện thoại");
            categoryMap.put(Category.TV.toString(), "Ti vi");
            categoryMap.put(Category.WASHING_MACHINE.toString(), "Máy giặt");

        }
        return categoryMap;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        for (String key : getCategoryMap().keySet()) {
            String value = getCategoryMap().get(key);
            if (value.equals(s)) {
                return value;
            }
        }
        return "";
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (null != o) {
            if (o instanceof String) {
                return getCategoryMap().get(o);
            }
            else {
                String value = String.valueOf(o);
                String label = getCategoryMap().get(value);
                if (null != label) {
                    return label;
                }
            }
        }
        return "";
    }
}
