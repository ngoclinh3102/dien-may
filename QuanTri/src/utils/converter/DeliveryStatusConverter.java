package utils.converter;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/25
*/

import utils.mune.DeliveryStatus;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.HashMap;

@FacesConverter("DeliveryStatusConverter")
@ManagedBean(name = "DeliveryStatusConverter")
@RequestScoped
public class DeliveryStatusConverter implements Converter {
    private HashMap<String, String> deliveryStatusHashMap;

    public HashMap<String, String> getDeliveryStatusHashMap() {
        if (deliveryStatusHashMap == null) {
            deliveryStatusHashMap = new HashMap<>();
            deliveryStatusHashMap.put(DeliveryStatus.READY.toString(),"Sẵn sàng");
            deliveryStatusHashMap.put(DeliveryStatus.CONFIRMED.toString(),"Đã xác nhận");
            deliveryStatusHashMap.put(DeliveryStatus.DELIVERING.toString(),"Đang giao hàng");
            deliveryStatusHashMap.put(DeliveryStatus.DELIVERED.toString(),"Đã giao hàng");
            deliveryStatusHashMap.put(DeliveryStatus.FULFILLED.toString(),"Hoàn thành");
            deliveryStatusHashMap.put(DeliveryStatus.UNFULFILLED.toString(),"Không hoàn thành");
            deliveryStatusHashMap.put(DeliveryStatus.CANCELED.toString(),"Hủy bỏ");
        }
        return deliveryStatusHashMap;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        for (String key : getDeliveryStatusHashMap().keySet()) {
            String value = getDeliveryStatusHashMap().get(key);
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
                return getDeliveryStatusHashMap().get(o);
            }
            else {
                String value = String.valueOf(o);
                String label = getDeliveryStatusHashMap().get(value);
                if (null != label) {
                    return label;
                }
            }
        }
        return "";
    }
}
