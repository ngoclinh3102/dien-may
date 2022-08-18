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
import java.util.List;

@ManagedBean(name = "ProductListBean", eager = true)
@ViewScoped
public class ProductListBean extends BaseBean {
    private List<Product> products;

    public List<Product> getProducts() {
        if (products == null) {
            products = ProductService.getProducts();
        }
        return products;
    }
}
