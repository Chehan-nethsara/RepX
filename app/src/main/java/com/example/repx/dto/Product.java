package com.example.repx.dto;

public class Product {

    private String productDocumnetID;
    private String productName;
    private String productCode;
    private Double prductQuantity;
    private String productPrice;
    private String productDescription;

    public Product() {
    }

    public Product(String productDocumnetID, String productName, String productCode, Double prductQuantity, String productPrice, String productDescription) {
        this.productDocumnetID = productDocumnetID;
        this.productName = productName;
        this.productCode = productCode;
        this.prductQuantity = prductQuantity;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    public String getProductDocumnetID() {
        return productDocumnetID;
    }

    public void setProductDocumnetID(String productDocumnetID) {
        this.productDocumnetID = productDocumnetID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getPrductQuantity() {
        return prductQuantity;
    }

    public void setPrductQuantity(Double prductQuantity) {
        this.prductQuantity = prductQuantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}