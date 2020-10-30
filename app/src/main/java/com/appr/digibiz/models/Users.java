package com.appr.digibiz.models;

public class Users {
    String business_name;
    String email;
    String phone_number;
    String security_level;
    public Users(){

    }

    public Users(String business_name, String email, String phone_number, String security_level) {
        this.business_name = business_name;
        this.email = email;
        this.phone_number = phone_number;
        this.security_level = security_level;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSecurity_level() {
        return security_level;
    }

    public void setSecurity_level(String security_level) {
        this.security_level = security_level;
    }
}
