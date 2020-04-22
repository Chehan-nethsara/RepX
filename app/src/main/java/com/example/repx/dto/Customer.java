package com.example.repx.dto;

public class Customer {
    String shopNameCustomer;
    String ownerNameCustomer;
    String phoneCustomer;
    String emailCustomer;
    String addressCustomer;

    public Customer(String shopNameCustomer, String ownerNameCustomer, String phoneCustomer, String emailCustomer, String addressCustomer) {
        this.shopNameCustomer = shopNameCustomer;
        this.ownerNameCustomer = ownerNameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.emailCustomer = emailCustomer;
        this.addressCustomer = addressCustomer;
    }

    public String getShopNameCustomer() {
        return shopNameCustomer;
    }

    public void setShopNameCustomer(String shopNameCustomer) {
        this.shopNameCustomer = shopNameCustomer;
    }

    public String getOwnerNameCustomer() {
        return ownerNameCustomer;
    }

    public void setOwnerNameCustomer(String ownerNameCustomer) {
        this.ownerNameCustomer = ownerNameCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {

        this.phoneCustomer = phoneCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getAddressCustomer() {

        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }
}
