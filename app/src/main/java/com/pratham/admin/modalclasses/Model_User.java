package com.pratham.admin.modalclasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Model_User {

    @NonNull
    @PrimaryKey
    @SerializedName("userid")
    private String userid;

    @SerializedName("roleid")
    private Integer roleid;

    @SerializedName("rolename")
    private String rolename;

    @SerializedName("progid")
    private Integer progid;

    @SerializedName("progname")
    private String progname;

    @SerializedName("statename")
    private String statename;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("districtname")
    private String districtname;

    @SerializedName("blockname")
    private String blockname;

    @SerializedName("username")
    private String username;

    @SerializedName("userpassword")
    private String userpassword;

    @SerializedName("Mobile")
    private String mobile;

    @SerializedName("Email")
    private String email;

    @SerializedName("ReportingPersonId")
    private String reportingPersonId;

    @SerializedName("ReportingPersonName")
    private String reportingPersonName;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getProgid() {
        return progid;
    }

    public void setProgid(Integer progid) {
        this.progid = progid;
    }

    public String getProgname() {
        return progname;
    }

    public void setProgname(String progname) {
        this.progname = progname;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getReportingPersonId() {
        return reportingPersonId;
    }

    public void setReportingPersonId(String reportingPersonId) {
        this.reportingPersonId = reportingPersonId;
    }

    public String getReportingPersonName() {
        return reportingPersonName;
    }

    public void setReportingPersonName(String reportingPersonName) {
        this.reportingPersonName = reportingPersonName;
    }
}
