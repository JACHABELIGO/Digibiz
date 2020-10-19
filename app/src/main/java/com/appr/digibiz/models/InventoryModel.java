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
