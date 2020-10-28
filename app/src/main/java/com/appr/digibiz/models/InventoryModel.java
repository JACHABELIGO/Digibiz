package com.appr.digibiz.models;

import org.parceler.Parcel;

@Parcel
public class InventoryModel {
    public String product_name;
    public String quantity;
    public String price_per_item;
    public String inventory_id;

    public InventoryModel() {
    }

    public InventoryModel(String product_name, String quantity, String price_per_item, String inventory_id) {
        this.product_name = product_name;
        this.quantity = quantity;
        this.price_per_item = price_per_item;
        this.inventory_id = inventory_id;
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

    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
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
                ", inventory_id='" + inventory_id + '\'' +
                '}';
    }
}
