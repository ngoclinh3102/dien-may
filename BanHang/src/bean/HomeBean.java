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
import java.text.Normalizer;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

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

    public void actionFilter() {
        List<Product> list = ProductService.getProducts(99);
        System.out.println("filter: " + filter);

        if (!filter.getSearch().equals("")) {
            list.removeIf(product -> !removeAccent(product.getName()).contains(removeAccent(filter.getSearch())));
        }

        if (!filter.getBrand().equals("all")) {
            list.removeIf(product -> !product.getBrand().equals(filter.getBrand()));
        }

        if (!filter.getCategories().equals("all")) {
            list.removeIf(product -> !product.getCategoryCode().equals(filter.getCategories()));
        }

        if (!filter.getAscendant().equals("")) {
//            list.removeIf(product -> (product.getInventory()==0));

            if (filter.getAscendant().equals("true")) {
                list.sort(Comparator.comparingInt(Product::getPrice));
            }
            else {
                list.sort(Comparator.comparingInt(Product::getPrice).reversed());
            }
        }

        products = list;
    }

    private String removeAccent(String s) {
        String temp = s.toLowerCase();
        temp = Normalizer.normalize(temp, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        temp = temp.replaceAll("đ", "d");
        temp = temp.replaceAll(" ", "");

        return temp;
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

    public void actionToDeliveryPage() {
        redirect("../ban-hang/e-commerce/delivery.xhtml");
    }
}
