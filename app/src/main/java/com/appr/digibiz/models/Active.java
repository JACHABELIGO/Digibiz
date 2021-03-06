package com.appr.digibiz.models;


import org.parceler.Parcel;

@Parcel
public class Active {
    String name_of_creditor ;
    int total_amount ;
    int quantity ;
    int price_per_item ;
    String due_date ;
    String mobile_number ;
    String transaction_details;
    String invoice_id ;

    public Active(){
    }

    public Active(String name_of_creditor, int quantity, int price_per_item, String due_date, String mobile_number, String transaction_details, String invoice_id, int total_amount) {
        this.name_of_creditor = name_of_creditor;
        this.total_amount = total_amount;
        this.quantity = quantity;
        this.price_per_item = price_per_item;
        this.due_date = due_date;
        this.mobile_number = mobile_number;
        this.transaction_details = transaction_details;
        this.invoice_id = invoice_id;
    }

    public String getName_of_creditor() {
        return name_of_creditor;
    }

    public void setName_of_creditor(String name_of_creditor) {
        this.name_of_creditor = name_of_creditor;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice_per_item() {
        return price_per_item;
    }

    public void setPrice_per_item(int price_per_item) {
        this.price_per_item = price_per_item;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getTransaction_details() {
        return transaction_details;
    }

    public void setTransaction_details(String transaction_details) {
        this.transaction_details = transaction_details;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    @Override
    public String toString() {
        return "Active{" +
                "name_of_creditor='" + name_of_creditor + '\'' +
                ", total_amount=" + total_amount +
                ", quantity=" + quantity +
                ", price_per_item=" + price_per_item +
                ", due_date='" + due_date + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                ", transaction_details='" + transaction_details + '\'' +
                ", invoice_id='" + invoice_id + '\'' +
                '}';
    }
}
