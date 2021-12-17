package com.pratham.admin.modalclasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class Attendance {

    @NonNull
    @PrimaryKey
    @SerializedName("AttendanceID")
    public String AttendanceID;
    @SerializedName("VillageID")
    public String VillageID;
    @SerializedName("GroupID")
    public String GroupID;
    @SerializedName("StudentID")
    public String StudentID;
    @SerializedName("Date")
    public String Date;
    @SerializedName("Present")
    public int Present;
    @SerializedName("sentFlag")
    public int sentFlag = 1;

}
