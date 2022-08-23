package bean.product;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/23
*/

import bean.BaseBean;
import model.Product;
import service.ProductService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

@ManagedBean(name = "ProductCreateBean", eager = true)
@ViewScoped
public class ProductCreateBean extends BaseBean {
    /* DECLARE */
    private Product product;
    private Part image;
    private String imagePath;

    /* SETTER & GETTER*/
    public Product getProduct() {
        if (product == null) {
            product = new Product();
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
    public void actionPostImage() {
        if (imagePath == null || imagePath.equals("")) {
            showWarn("Vui lòng chọn ảnh!!");
        }
        else {
            for (String path : product.getImages()) {
                if (imagePath.equals(path)) {
                    showWarn("Sản phẩm đã có ảnh này!!");
                    return;
                }
            }
            product.getImages().add(imagePath);
            closeDialog("dlgAddImage");
            resetInput();
        }
    }

    public void actionDeleteImage(String image) {
        for (String path : getProduct().getImages()) {
            if (path.equals(image)) {
                getProduct().getImages().remove(path);
                return;
            }
        }
    }

    public void actionPostProduct() {
        if (validateForm()) {
            int i = ProductService.postProduct(product);

            if (i == 0) {
                showInfo("Đã cập nhật ảnh sản phẩm " + product.getCode() + "!!");
                redirect("../quan-tri/manager/product/product-detail.xhtml?id=" + product.getCode());
            }
            else if (i == -1) {
                showError("Lỗi kết nối đến CSDL!!");
            }
            else if (i == -2) {
                showError("Lỗi thực thi câu lệnh truy vấn!!");
            }
            else if (i == -3) {
                showError("Lỗi thực thi thêm sản phẩm!!");
            }
        }
    }

    private boolean validateForm() {
        boolean flag = true;
        if (product.getCode() == null || product.getCode().equals("")) {
            flag = false;
            showWarn("Vui lòng nhập mã sản phẩm!!");
        }
        if (product.getCode() == null || product.getName().equals("")) {
            flag = false;
            showWarn("Vui lòng nhập tên sản phẩm!!");
        }
        if (product.getBrand() == null || product.getBrand().equals("")) {
            flag = false;
            showWarn("Vui lòng nhập thương hiệu!!");
        }
        if (product.getCategoryCode() == null || product.getCategoryCode().equals("")) {
            flag = false;
            showWarn("Vui lòng chọn loại mặt hàng!!");
        }
        if (product.getUnit() == null || product.getUnit().equals("")) {
            flag = false;
            showWarn("Vui lòng nhập đơn vị tính!!");
        }
        if (product.getInventory() < 0 || product.getBrand().equals("")) {
            flag = false;
            showWarn("Vui lòng nhập số lượng nguyên và không âm!!");
        }
        if (product.getPrice() <= 0 || product.getBrand().equals("")) {
            flag = false;
            showWarn("Vui lòng nhập giá bán lớn hơn 0!!");
        }

        return flag;
    }

    private void resetInput() {
        image = null;
        imagePath = null;
    }
}
