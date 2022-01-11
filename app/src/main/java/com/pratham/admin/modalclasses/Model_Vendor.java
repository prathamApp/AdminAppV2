package com.pratham.admin.modalclasses;

import com.google.gson.annotations.SerializedName;

public class Model_Vendor {
    @SerializedName("RoleId")
    private Integer roleId;

    @SerializedName("RoleName")
    private String roleName;

    @SerializedName("ProgramId")
    private Integer programId;

    @SerializedName("ProgramName")
    private String programName;

    @SerializedName("State")
    private String state;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("Mobile")
    private String mobile;

    @SerializedName("Email")
    private String email;

    @SerializedName("District")
    private String district;

    @SerializedName("Block")
    private String block;

    @SerializedName("UserName")
    private String userName;

    @SerializedName("Password")
    private String password;

    @SerializedName("CRLId")
    private Integer cRLId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getcRLId() {
        return cRLId;
    }

    public void setcRLId(Integer cRLId) {
        this.cRLId = cRLId;
    }
}
