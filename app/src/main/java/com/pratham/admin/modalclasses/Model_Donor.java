package com.pratham.admin.modalclasses;

import com.google.gson.annotations.SerializedName;

public class Model_Donor {

    @SerializedName("donarname")
    String donorName;

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }
}
