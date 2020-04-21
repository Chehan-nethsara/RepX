package com.example.repx.dto;

public class Sale {
    private String product;
    private String qty;
    private String customer;
    private String discout;
    private String total;


    public Sale( String product, String qty, String customer, String discout, String total) {
        this.product = product;
        this.qty = qty;
        this.customer = customer;
        this.discout = discout;
        this.total = total;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getQty() {
        return qty;
    }
    public void setQty(String qty) {
        this.qty = qty;
    }
    public String getDiscout() {
        return discout;
    }

    public void setDiscout(String discout) {
        this.discout = discout;
    }
    public String getTotal() {
        return total;
    }

    public void setTotal(String discout) {
        this.total = total;
    }


}
