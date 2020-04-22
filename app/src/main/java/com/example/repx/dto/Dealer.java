package com.example.repx.dto;

public class Dealer {

    private String id;
    private String name;
    private String area;
    private String category;
    private String telePhoneNumber;


    public Dealer() {
    }

    public Dealer(String id, String name, String area, String category, String telePhoneNumber) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.category = category;
        this.telePhoneNumber = telePhoneNumber;

    }

    public String getId() {

        return id;
    }
    public void setId(String id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }
    public void setName(String name) {

        this.name = name;
    }

    public String getArea() {

        return area;
    }
    public void setArea(String area) {

        this.area = area;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {

        this.category = category;
    }

    public String getTelePhoneNumber() {

        return telePhoneNumber;
    }
    public void setTelePhoneNumber(String telePhoneNumber) {

        this.telePhoneNumber = telePhoneNumber;
    }


}