package service;

import model.ShippingAgent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "ShippingAgentService")
@RequestScoped
public class ShippingAgentService extends BaseService {
    /* GET SHIPPING_AGENTS */
    public static List<ShippingAgent> getShippingAgents() {
        if (getStatement()!= null) {
            String sql = "SELECT * FROM shipping_agent";
            List<ShippingAgent> list = new ArrayList<>();
            try {
                ResultSet rs = getStatement().executeQuery(sql);
                while (rs.next()) {
                    ShippingAgent shippingAgent = new ShippingAgent();
                    shippingAgent.setCode(rs.getString(1));
                    shippingAgent.setName(rs.getString(2));
                    shippingAgent.setCost(rs.getInt(3));
                    shippingAgent.setDeliveryAverageTime(rs.getInt(4));

                    list.add(shippingAgent);
                }
                return list;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("getShippingAgents(): failed");
        return new ArrayList<>();
    }
}
