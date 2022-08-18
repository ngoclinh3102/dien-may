package bean.product;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/15
*/

import bean.BaseBean;
import model.Product;
import service.ProductService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ProductCreateBean", eager = true)
@ViewScoped
public class ProductDetailBean extends BaseBean {
    /* DECLARE */
    private String id;
    private Product product;

    /* SETTER & GETTER */
    public String getId() {
        if (id == null || id.equals("")) {
            id = getParameter("id");
        }
        return id;
    }

    public Product getProduct() {
        if (product == null) {
            product = ProductService.getProduct(getId());
        }
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    /* METHOD */
}
