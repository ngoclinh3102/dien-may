package bean;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/24
*/

import model.Filter;
import model.Product;
import service.ProductService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "HomeBean", eager = true)
@ViewScoped
public class HomeBean extends BaseBean {
    /* DECLARE */
    private final List<Product> productsAll = ProductService.getProducts(99);
    private List<Product> products;
    private Filter filter;

    /* GETTER & SETTER */
    public List<Product> getProducts() {
        if (products == null) {
            products = productsAll;
        }
        return products;
    }

    public Filter getFilter() {
        if (filter == null) {
            filter = new Filter();
        }
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    /* METHOD */
    public void actionResetFilter() {
        products = productsAll;
        filter = null;
    }


    /* REDIRECT */
    public void actionToHomePage() {
        redirect("../ban-hang/e-commerce/home.xhtml");
    }

    public void actionToLoginPage() {
        redirect("../ban-hang/login.xhtml");
    }

    public void actionToSingUpPage() {
        redirect("../ban-hang/sign-up.xhtml");
    }

    public void actionToCartPage() {
        redirect("../ban-hang/e-commerce/cart.xhtml");
    }
}
