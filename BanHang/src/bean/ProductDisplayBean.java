package bean;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/28
*/

import model.Product;
import service.ProductService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ProductDisplayBean", eager = true)
@ViewScoped
public class ProductDisplayBean extends BaseBean {
    /* DECLARE */
    private final String id = getParameter("id");
    private Product product;

    /* GETTER & SETTER */
    public Product getProduct() {
        if (product == null) {
            product = ProductService.getProduct(id);
        }
        return product;
    }

    /* ACTION */

}
