package com.appr.digibiz.models;

public class Customers {
    String name ;
    String mobile_number;

    public Customers(String name, String mobile_number) {
        this.name = name;
        this.mobile_number = mobile_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
}
