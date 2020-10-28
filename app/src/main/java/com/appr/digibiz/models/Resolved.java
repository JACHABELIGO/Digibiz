package com.appr.digibiz.models;

public class Resolved {
    String name_of_creditor ;
    int total_amount ;
    String due_date ;

    String transaction_details;
    String invoice_id ;

    public Resolved() {
    }

    public Resolved(String name_of_creditor, int total_amount, String due_date, String transaction_details, String invoice_id) {
        this.name_of_creditor = name_of_creditor;
        this.total_amount = total_amount;
        this.due_date = due_date;
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

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
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
}
