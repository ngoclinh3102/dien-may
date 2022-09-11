package model;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/24
*/

public class Filter {
    /* DECLARE */
    private String search; //product.name
    private String brand;
    private String categories;
    private String ascendant;

    /* CONSTRUCTOR */
    public Filter() {
        search = "";
        brand = "all";
        categories = "all";
        ascendant = "";
    }

    /* SETTER & GETTER */
    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getAscendant() {
        return ascendant;
    }

    public void setAscendant(String ascendant) {
        this.ascendant = ascendant;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "search='" + search + '\'' +
                ", brand='" + brand + '\'' +
                ", categories='" + categories + '\'' +
                ", ascendant='" + ascendant + '\'' +
                '}';
    }
}
