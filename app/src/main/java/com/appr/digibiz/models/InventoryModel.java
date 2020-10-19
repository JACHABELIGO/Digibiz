package com.appr.digibiz.models;

import org.parceler.Parcel;

@Parcel
public class InventoryModel {
    public String product_name;
    public String quantity;
    public String price_per_item;

    public InventoryModel() {
    }

    public InventoryModel(String product_name, String quantity, String price_per_item) {
        this.product_name = product_name;
        this.quantity = quantity;
        this.price_per_item = price_per_item;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice_per_item() {
        return price_per_item;
    }

    public void setPrice_per_item(String price_per_item) {
        this.price_per_item = price_per_item;
    }

    @Override
    public String toString() {
        return "InventoryModel{" +
                "product_name='" + product_name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price_per_item='" + price_per_item + '\'' +
                '}';
    }
}
