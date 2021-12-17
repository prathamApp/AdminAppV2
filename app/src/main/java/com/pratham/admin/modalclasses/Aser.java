package com.pratham.admin.modalclasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ameya on 19-Jun-17.
 */
@Entity
public class Aser {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @SerializedName("AserID")
    public int AserID;
    @SerializedName("StudentId")
    public String StudentId;
    @SerializedName("TestType")
    public int TestType;
    @SerializedName("TestDate")
    public String TestDate;
    @SerializedName("ChildID")
    public String ChildID;
    @SerializedName("Lang")
    public int Lang;
    @SerializedName("Num")
    public int Num;
    @SerializedName("OAdd")
    public int OAdd;
    @SerializedName("OSub")
    public int OSub;
    @SerializedName("OMul")
    public int OMul;
    @SerializedName("ODiv")
    public int ODiv;
    @SerializedName("WAdd")
    public int WAdd;
    @SerializedName("WSub")
    public int WSub;
    @SerializedName("FLAG")
    public int FLAG;
    @SerializedName("CreatedBy")
    public String CreatedBy;
    @SerializedName("CreatedDate")
    public String CreatedDate;
    @SerializedName("DeviceId")
    public String DeviceId;
    @SerializedName("GroupID")
    public String GroupID;
    @SerializedName("sharedBy")
    public String sharedBy;
    @SerializedName("SharedAtDateTime")
    public String SharedAtDateTime;
    @SerializedName("appVersion")
    public String appVersion;
    @SerializedName("appName")
    public String appName;
    @SerializedName("CreatedOn")
    public String CreatedOn;

    // new fields db version 4
    @SerializedName("sentFlag")
    public int sentFlag = 1;

    // new fields db version 5
    @SerializedName("English")
    public int English = 0;
    @SerializedName("EnglishSelected")
    public int EnglishSelected = 0;


    @Override
    public String toString() {
        return "Aser{" +
                "AserID=" + AserID +
                ", StudentId='" + StudentId + '\'' +
                ", TestType=" + TestType +
                ", TestDate='" + TestDate + '\'' +
                ", ChildID='" + ChildID + '\'' +
                ", Lang=" + Lang +
                ", Num=" + Num +
                ", OAdd=" + OAdd +
                ", OSub=" + OSub +
                ", OMul=" + OMul +
                ", ODiv=" + ODiv +
                ", WAdd=" + WAdd +
                ", WSub=" + WSub +
                ", FLAG=" + FLAG +
                ", CreatedBy='" + CreatedBy + '\'' +
                ", CreatedDate='" + CreatedDate + '\'' +
                ", DeviceId='" + DeviceId + '\'' +
                ", GroupID='" + GroupID + '\'' +
                ", sharedBy='" + sharedBy + '\'' +
                ", SharedAtDateTime='" + SharedAtDateTime + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", appName='" + appName + '\'' +
                ", CreatedOn='" + CreatedOn + '\'' +
                ", sentFlag=" + sentFlag +
                ", English=" + English +
                ", EnglishSelected=" + EnglishSelected +
                '}';
    }

    public int getAserID() {
        return AserID;
    }

    public void setAserID(int aserID) {
        AserID = aserID;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public int getTestType() {
        return TestType;
    }

    public void setTestType(int testType) {
        TestType = testType;
    }

    public String getTestDate() {
        return TestDate;
    }

    public void setTestDate(String testDate) {
        TestDate = testDate;
    }

    public String getChildID() {
        return ChildID;
    }

    public void setChildID(String childID) {
        ChildID = childID;
    }

    public int getLang() {
        return Lang;
    }

    public void setLang(int lang) {
        Lang = lang;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public int getOAdd() {
        return OAdd;
    }

    public void setOAdd(int OAdd) {
        this.OAdd = OAdd;
    }

    public int getOSub() {
        return OSub;
    }

    public void setOSub(int OSub) {
        this.OSub = OSub;
    }

    public int getOMul() {
        return OMul;
    }

    public void setOMul(int OMul) {
        this.OMul = OMul;
    }

    public int getODiv() {
        return ODiv;
    }

    public void setODiv(int ODiv) {
        this.ODiv = ODiv;
    }

    public int getWAdd() {
        return WAdd;
    }

    public void setWAdd(int WAdd) {
        this.WAdd = WAdd;
    }

    public int getWSub() {
        return WSub;
    }

    public void setWSub(int WSub) {
        this.WSub = WSub;
    }

    public int getFLAG() {
        return FLAG;
    }

    public void setFLAG(int FLAG) {
        this.FLAG = FLAG;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getGroupID() {
        return GroupID;
    }

    public void setGroupID(String groupID) {
        GroupID = groupID;
    }

    public String getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(String sharedBy) {
        this.sharedBy = sharedBy;
    }

    public String getSharedAtDateTime() {
        return SharedAtDateTime;
    }

    public void setSharedAtDateTime(String sharedAtDateTime) {
        SharedAtDateTime = sharedAtDateTime;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public int getSentFlag() {
        return sentFlag;
    }

    public void setSentFlag(int sentFlag) {
        this.sentFlag = sentFlag;
    }

    public int getEnglish() {
        return English;
    }

    public void setEnglish(int english) {
        English = english;
    }

    public int getEnglishSelected() {
        return EnglishSelected;
    }

    public void setEnglishSelected(int englishSelected) {
        EnglishSelected = englishSelected;
    }
}