package com.pratham.admin.modalclasses;

import com.google.gson.annotations.SerializedName;

public class Model_TabletCount {

    @SerializedName("TotalCount")
    private String totalCount;
    @SerializedName("TotalAssigned")
    private String totalAssigned;
    @SerializedName("TotalUnassigned")
    private String totalUnassigned;
    @SerializedName("TotalDisputed")
    private String totalDisputed;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getTotalAssigned() {
        return totalAssigned;
    }

    public void setTotalAssigned(String totalAssigned) {
        this.totalAssigned = totalAssigned;
    }

    public String getTotalUnassigned() {
        return totalUnassigned;
    }

    public void setTotalUnassigned(String totalUnassigned) {
        this.totalUnassigned = totalUnassigned;
    }

    public String getTotalDisputed() {
        return totalDisputed;
    }

    public void setTotalDisputed(String totalDisputed) {
        this.totalDisputed = totalDisputed;
    }

}
