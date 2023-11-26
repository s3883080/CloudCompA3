package com.amazonaws.s3883080;

public class Receipt {

    private String buyer;
    private String seller;
    private String item;
    private String brand;
    private String model;
    private String type;
    private float cost;
    private float gst;
    public Receipt(String buyer, String seller, String brand, String model, String type, float cost) {
        this.buyer = buyer;
        this.seller = seller;
        this.brand = brand;
        this.model = model;
        this.item = brand + " - " + model + " " + type;
        this.type = type;
        this.cost = cost;
        setGst(cost);
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getGst() {
        return gst;
    }

    public void setGst(float cost) {
        this.gst = cost/10;
    }
}