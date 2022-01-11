package com.pratham.admin.modalclasses;

import com.google.gson.annotations.SerializedName;

public class Model_DamagedTabletCount {

    @SerializedName("TotalCount")
    private String totalCount;

    @SerializedName("TotalWorking")
    private String totalWorking;

    @SerializedName("TotalDamaged")
    private String totalDamaged;

    @SerializedName("TotalLost")
    private String totalLost;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getTotalWorking() {
        return totalWorking;
    }

    public void setTotalWorking(String totalWorking) {
        this.totalWorking = totalWorking;
    }

    public String getTotalDamaged() {
        return totalDamaged;
    }

    public void setTotalDamaged(String totalDamaged) {
        this.totalDamaged = totalDamaged;
    }

    public String getTotalLost() {
        return totalLost;
    }

    public void setTotalLost(String totalLost) {
        this.totalLost = totalLost;
    }
}
