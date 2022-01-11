package com.pratham.admin.modalclasses;

import com.google.gson.annotations.SerializedName;

public class Model_YearOfPurchase {

    @SerializedName("yearofpurchase")
    String yearOfPurchase;

    public String getYearOfPurchase() {
        return yearOfPurchase;
    }

    public void setYearOfPurchase(String yearOfPurchase) {
        this.yearOfPurchase = yearOfPurchase;
    }
}
