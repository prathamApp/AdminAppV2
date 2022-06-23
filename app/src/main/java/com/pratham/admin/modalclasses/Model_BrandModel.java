package com.pratham.admin.modalclasses;

import com.google.gson.annotations.SerializedName;

public class Model_BrandModel {

    @SerializedName("brand")
    private String brand;

    @SerializedName("model")
    private String model;

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
}
