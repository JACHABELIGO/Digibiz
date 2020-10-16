package com.appr.digibiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class OutOfStockModel {
    @SerializedName("product_name")
    @Expose
    public String product_name;
    @SerializedName("quantity")
    @Expose
    public String quantity;
    @SerializedName("price_per_item")
    @Expose
    public String price_per_item;

    /**
     * No args constructor for use in serialization
     *
     */

    public OutOfStockModel() {
    }

    /**
     * @param product_name
     * @param quantity
     * @param price_per_item
     */

    public OutOfStockModel(String product_name, String quantity, String price_per_item) {
        super();
        this.product_name = product_name;
        this.quantity = quantity;
        this.price_per_item = price_per_item;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;

    }

    public String getPricePerItem() {
        return price_per_item;
    }

    public void setPricePerItem(String price_per_item) {
        this.price_per_item = price_per_item;

    }
}
