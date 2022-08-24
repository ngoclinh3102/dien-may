package bean.product;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/19
*/

import bean.BaseBean;
import model.Product;
import service.ProductService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

@ManagedBean(name = "ProductDetailBean", eager = true)
@ViewScoped
public class ProductDetailBean extends BaseBean {
    /* DECLARE */
    private final String id =  getParameter("id");
    private Product product;
    private Part image;
    private String imagePath;

    /* SETTER & GETTER */
    public Product getProduct() {
        if (product == null) {
            product = ProductService.getProduct(id);
            System.out.println(product.toString());
        }
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void readImagePath() {
        imagePath = image.getSubmittedFileName();
        System.out.println("imagePath: " + imagePath);
    }

    /* METHOD */
    public void actionPutProduct() {
        int i = ProductService.putProduct(product);

        if (i == 0) {
            showInfo("Đã cập nhật sản phẩm " + product.getCode() + "!!");
        }
        else if (i == -1) {
            showError("Lỗi kết nối đến CSDL!!");
        }
        else if (i == -2) {
            showError("Lỗi thực thi câu lệnh truy vấn!!");
        }
        else if (i == -3) {
            showError("Lỗi thực thi cập nhật ảnh sản phẩm!!");
        }
    }

    public void actionPostImage() {
        if (imagePath != null) {
            for (String path : product.getImages()) {
                if (imagePath.equals(path)) {
                    showWarn("Sản phẩm đã có ảnh này!!");
                    return;
                }
            }

            int i = ProductService.postProductImage(product.getCode(),imagePath);

            closeDialog("dlgAddImage");
            if (i == 0) {
                showInfo("Đã cập nhật ảnh sản phẩm " + product.getCode() + "!!");
                resetInputImage();
            }
            else if (i == -1) {
                showError("Lỗi kết nối đến CSDL!!");
            }
            else if (i == -2) {
                showError("Lỗi thực thi câu lệnh truy vấn!!");
            }
            else if (i == -3) {
                showError("Lỗi thực thi cập nhật ảnh sản phẩm!!");
            }
        }
        else {
            showWarn("Vui lòng chọn ảnh!!");
        }
    }

    public void actionDeleteImage(String imagePath) {
        int i = ProductService.deleteProductImage(product.getCode(),imagePath);

        if (i == 0) {
            showInfo("Đã xóa ảnh của sản phẩm " + product.getCode() + "!!");
            resetInputImage();
        }
        else if (i == -1) {
            showError("Lỗi kết nối đến CSDL!!");
        }
        else if (i == -2) {
            showError("Lỗi thực thi câu lệnh truy vấn!!");
        }
        else if (i == -3) {
            showError("Lỗi thực thi xóa ảnh sản phẩm!!");
        }
    }

    private void resetInputImage() {
        product = null;
        image = null;
        imagePath = null;
    }
}
