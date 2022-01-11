package com.pratham.admin.modalclasses;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class CRL implements Comparable, Parcelable {
    @NonNull
    @PrimaryKey
    @SerializedName("CRLId")
    String CRLId;
    @SerializedName("RoleId")
    String RoleId;
    @SerializedName("RoleName")
    String RoleName;
    @SerializedName("ProgramId")
    String ProgramId;
    @SerializedName("ProgramName")
    String ProgramName;
    @SerializedName("State")
    String State;
    @SerializedName("FirstName")
    String FirstName;
    @SerializedName("LastName")
    String LastName;
    @SerializedName("Mobile")
    String Mobile;
    @SerializedName("Email")
    String Email;
    @SerializedName("Block")
    String Block;
    @SerializedName("District")
    String District;
    @SerializedName("UserName")
    String UserName;
    @SerializedName("Password")
    String Password;
    @SerializedName("ReportingPersonId")
    String ReportingPersonId;
    @SerializedName("ReportingPersonName")
    String ReportingPersonName;

    @Ignore
    private boolean isSelected = false;

    public CRL(){
    }

    private CRL(Parcel in) {
        CRLId = in.readString();
        RoleId = in.readString();
        RoleName = in.readString();
        ProgramId = in.readString();
        ProgramName = in.readString();
        State = in.readString();
        FirstName = in.readString();
        LastName = in.readString();
        Mobile = in.readString();
        Email = in.readString();
        Block = in.readString();
        District = in.readString();
        UserName = in.readString();
        Password = in.readString();
        ReportingPersonId = in.readString();
        ReportingPersonName = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<CRL> CREATOR = new Creator<CRL>() {
        @Override
        public CRL createFromParcel(Parcel in) {
            return new CRL(in);
        }

        @Override
        public CRL[] newArray(int size) {
            return new CRL[size];
        }
    };

    @NonNull
    public String getCRLId() {
        return CRLId;
    }

    public void setCRLId(@NonNull String CRLId) {
        this.CRLId = CRLId;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getProgramId() {
        return ProgramId;
    }

    public void setProgramId(String programId) {
        ProgramId = programId;
    }

    public String getProgramName() {
        return ProgramName;
    }

    public void setProgramName(String programName) {
        ProgramName = programName;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBlock() {
        return Block;
    }

    public void setBlock(String block) {
        Block = block;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getReportingPersonId() { return ReportingPersonId; }

    public void setReportingPersonId(String reportingPersonId) { ReportingPersonId = reportingPersonId; }

    public String getReportingPersonName() { return ReportingPersonName; }

    public void setReportingPersonName(String reportingPersonName) { ReportingPersonName = reportingPersonName; }

    public boolean isSelected() { return isSelected; }

    public void setSelected(boolean selected) { isSelected = selected; }

    @Override
    public int compareTo(Object o) {
        CRL compare = (CRL) o;
        if (compare.getCRLId().equals(this.CRLId) && compare.isSelected() == this.isSelected)
            return 0;
        else return 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(CRLId);
        dest.writeString(FirstName);
        dest.writeString(Block);
        dest.writeString(Email);
        dest.writeString(UserName);
        dest.writeString(ProgramName);
    }
}
