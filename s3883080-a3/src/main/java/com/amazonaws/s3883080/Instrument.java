package com.amazonaws.s3883080;

public class Instrument {
    private String seller;
    private String location;
    private String instrumentType;
    private String instrumentBrand;
    private String instrumentModel;
    private String description;
    private float cost;
    private boolean ono;

    private boolean sold;

    public Instrument(String seller, String location, String instrumentType, String instrumentBrand, String instrumentModel, String description, float cost, boolean ono, boolean sold) {
        this.seller = seller;
        this.location = location;
        this.instrumentType = instrumentType;
        this.instrumentBrand = instrumentBrand;
        this.instrumentModel = instrumentModel;
        this.description = description;
        this.cost = cost;
        this.ono = ono;
        this.sold = sold;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getInstrumentBrand() {
        return instrumentBrand;
    }

    public void setInstrumentBrand(String instrumentBrand) {
        this.instrumentBrand = instrumentBrand;
    }

    public String getInstrumentModel() {
        return instrumentModel;
    }

    public void setInstrumentModel(String instrumentModel) {
        this.instrumentModel = instrumentModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isOno() {
        return ono;
    }

    public void setOno(boolean ono) {
        this.ono = ono;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
