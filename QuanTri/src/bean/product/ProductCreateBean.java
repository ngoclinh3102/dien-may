package bean.product;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/15
*/

import bean.BaseBean;
import org.primefaces.model.file.UploadedFile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ProductCreateBean", eager = true)
@ViewScoped
public class ProductCreateBean extends BaseBean {
    /* DECLARE */
    private UploadedFile file;
    private String image;

    /* SETTER & GETTER */
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getImage() {
        if (image == null) {
            return "abc";
        }
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /* METHOD */
    public void submit() {
        image = file.getFileName();
    }
}
