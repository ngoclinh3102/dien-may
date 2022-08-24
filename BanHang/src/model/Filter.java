package model;

/*
    Author      : Ngoc Linh, Vu
    Last modify : 2022/08/24
*/

import java.util.ArrayList;
import java.util.List;

public class Filter {
    /* DECLARE */
    private String search; //product.name
    private String brand;
    private List<String> categories;
    private float priceMin;
    private float priceMax;
    private boolean bestSelling;

    /* CONSTRUCTOR */
    public Filter() {
        this.search = "";
        this.brand = "";
        this.categories = new ArrayList<>();
        this.priceMin = 0;
        this.priceMax = 999999999;
        this.bestSelling = false;
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

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public float getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(float priceMin) {
        this.priceMin = priceMin;
    }

    public float getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(float priceMax) {
        this.priceMax = priceMax;
    }

    public boolean isBestSelling() {
        return bestSelling;
    }

    public void setBestSelling(boolean bestSelling) {
        this.bestSelling = bestSelling;
    }

}
