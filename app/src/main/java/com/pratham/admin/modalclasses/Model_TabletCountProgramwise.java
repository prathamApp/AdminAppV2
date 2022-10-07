package com.pratham.admin.modalclasses;

import com.google.gson.annotations.SerializedName;

public class Model_TabletCountProgramwise {
    @SerializedName("ProgramId")
    private String programId;
    @SerializedName("ProgramName")
    private String programName;
    @SerializedName("TotalCount")
    private String totalCount;
    @SerializedName("TotalLost")
    private String totalLost;
    @SerializedName("TotalWorking")
    private String totalWorking;
    @SerializedName("TotalDamaged")
    private String totalDamaged;
    @SerializedName("TotalDead")
    private String totalDead;
    @SerializedName("TotalOther")
    private String totalOther;

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getTotalLost() {
        return totalLost;
    }

    public void setTotalLost(String totalLost) {
        this.totalLost = totalLost;
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

    public String getTotalDead() {
        return totalDead;
    }

    public void setTotalDead(String totalDead) {
        this.totalDead = totalDead;
    }

    public String getTotalOther() {
        return totalOther;
    }

    public void setTotalOther(String totalOther) {
        this.totalOther = totalOther;
    }

}