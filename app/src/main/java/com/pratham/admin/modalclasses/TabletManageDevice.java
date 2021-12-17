package com.pratham.admin.modalclasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class TabletManageDevice implements Parcelable {

    @NonNull
    @Expose
    @PrimaryKey()
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("QR_ID")
    String QR_ID;

    @Expose
    @SerializedName("Pratham_ID")
    String Pratham_ID;

    @Expose
    @SerializedName("Tab_serial_ID")
    String tabSerial_ID;

    @Expose
    @SerializedName("date")
    String date;

    @Expose
    @SerializedName("CRL_ID")
    String assigned_CRL_ID;

    @Expose
    @SerializedName("CRL_Name")
    String assigned_CRL_Name;


    @Expose
    @SerializedName("CRL_ID_LoggedIN")
    String logged_CRL_ID;


    @Expose
    @SerializedName("CRL_NAME_LoggedIN")
    String logged_CRL_NAME;


    @Expose
    @SerializedName("collectedTabPrathamID")
    String collectedTabPrathamID;

    @Expose
    @SerializedName("collectedTabQrID")
    String collectedTabQrID;

    @Expose
    @SerializedName("collectedTab_serial_ID")
    String collectedTab_serial_ID;

    @Expose
    @SerializedName("collectedTabs_seniorsID")
    String collectedTabs_senior;

    @Expose
    @SerializedName("collectedTab_date")
    String collected_date;
    @Expose
    @SerializedName("is_Damaged")
    String is_Damaged;

    @Expose
    @SerializedName("damageType")
    String damageType;

    @Expose
    @SerializedName("operation_Type")
    String status;


    @Expose
    @SerializedName("comment")
    String comment;

    @Expose(serialize = false)
    boolean oldFlag = false;

    @Expose
    @SerializedName("villageName")
    String villageName;

    @Expose
    @SerializedName("villageID")
    String villageID;

    @Expose(serialize = false)
    @SerializedName("isPushed")
    int isPushed;




    public TabletManageDevice() {
    }

    public TabletManageDevice(String villageID, String villageName) {
        this.villageName = villageName;
        this.villageID = villageID;
    }

    protected TabletManageDevice(Parcel in) {
        id = in.readString();
        QR_ID = in.readString();
        Pratham_ID = in.readString();
        tabSerial_ID = in.readString();
        date = in.readString();
        assigned_CRL_ID = in.readString();
        assigned_CRL_Name = in.readString();
        logged_CRL_ID = in.readString();
        logged_CRL_NAME = in.readString();
        collectedTabPrathamID = in.readString();
        collectedTabQrID = in.readString();
        collectedTab_serial_ID = in.readString();
        collectedTabs_senior = in.readString();
        collected_date = in.readString();
        is_Damaged = in.readString();
        damageType = in.readString();
        status = in.readString();
        comment = in.readString();
        oldFlag = in.readByte() != 0;
        villageName = in.readString();
        villageID = in.readString();
        isPushed = in.readInt();
    }

    public static final Creator<TabletManageDevice> CREATOR = new Creator<TabletManageDevice>() {
        @Override
        public TabletManageDevice createFromParcel(Parcel in) {
            return new TabletManageDevice(in);
        }

        @Override
        public TabletManageDevice[] newArray(int size) {
            return new TabletManageDevice[size];
        }
    };

    @Override
    public String toString() {
        if (collectedTabPrathamID != null && !collectedTabPrathamID.equalsIgnoreCase(""))
            return villageName + " (P ID : " + collectedTabPrathamID + ")";
        else if (collectedTab_serial_ID != null && !collectedTab_serial_ID.equalsIgnoreCase("")) {
            return villageName + "(S ID : " + collectedTab_serial_ID + ")";
        } else {
            if (villageID != null && villageID.equals("-1")) {
                return villageName;
            } else
                return null;
        }
    }

    public String getCollectedTabs_senior() {
        return collectedTabs_senior;
    }

    public void setCollectedTabs_senior(String collectedTabs_senior) {
        this.collectedTabs_senior = collectedTabs_senior;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public boolean getOldFlag() {
        return oldFlag;
    }

    public void setOldFlag(boolean oldFlag) {
        this.oldFlag = oldFlag;
    }

    @NonNull
    public String getQR_ID() {
        return QR_ID;
    }

    public void setQR_ID(@NonNull String QR_ID) {
        this.QR_ID = QR_ID;
    }

    public String getAssigned_CRL_ID() {
        return assigned_CRL_ID;
    }

    public void setAssigned_CRL_ID(String assigned_CRL_ID) {
        this.assigned_CRL_ID = assigned_CRL_ID;
    }

    public String getAssigned_CRL_Name() {
        return assigned_CRL_Name;
    }

    public void setAssigned_CRL_Name(String assigned_CRL_Name) {
        this.assigned_CRL_Name = assigned_CRL_Name;
    }

    public String getPratham_ID() {
        return Pratham_ID;
    }

    public void setPratham_ID(String pratham_ID) {
        Pratham_ID = pratham_ID;
    }

    public String getCollected_date() {
        return collected_date;
    }

    public void setCollected_date(String collected_date) {
        this.collected_date = collected_date;
    }

    public String getLogged_CRL_ID() {
        return logged_CRL_ID;
    }

    public void setLogged_CRL_ID(String logged_CRL_ID) {
        this.logged_CRL_ID = logged_CRL_ID;
    }

    public String getLogged_CRL_NAME() {
        return logged_CRL_NAME;
    }

    public void setLogged_CRL_NAME(String logged_CRL_NAME) {
        this.logged_CRL_NAME = logged_CRL_NAME;
    }

    public String getIs_Damaged() {
        return is_Damaged;
    }

    public void setIs_Damaged(String is_Damaged) {
        this.is_Damaged = is_Damaged;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTabSerial_ID() {
        return tabSerial_ID;
    }

    public void setTabSerial_ID(String tabSerial_ID) {
        this.tabSerial_ID = tabSerial_ID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageID() {
        return villageID;
    }

    public void setVillageID(String villageID) {
        this.villageID = villageID;
    }

    public String getCollectedTabPrathamID() {
        return collectedTabPrathamID;
    }

    public void setCollectedTabPrathamID(String collectedTabPrathamID) {
        this.collectedTabPrathamID = collectedTabPrathamID;
    }

    public String getCollectedTabQrID() {
        return collectedTabQrID;
    }

    public void setCollectedTabQrID(String collectedTabQrID) {
        this.collectedTabQrID = collectedTabQrID;
    }

    public String getCollectedTab_serial_ID() {
        return collectedTab_serial_ID;
    }

    public void setCollectedTab_serial_ID(String collectedTab_serial_ID) {
        this.collectedTab_serial_ID = collectedTab_serial_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsPushed() {
        return isPushed;
    }

    public void setIsPushed(int isPushed) {
        this.isPushed = isPushed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(QR_ID);
        dest.writeString(Pratham_ID);
        dest.writeString(tabSerial_ID);
        dest.writeString(date);
        dest.writeString(assigned_CRL_ID);
        dest.writeString(assigned_CRL_Name);
        dest.writeString(logged_CRL_ID);
        dest.writeString(logged_CRL_NAME);
        dest.writeString(collectedTabPrathamID);
        dest.writeString(collectedTabQrID);
        dest.writeString(collectedTab_serial_ID);
        dest.writeString(collectedTabs_senior);
        dest.writeString(collected_date);
        dest.writeString(is_Damaged);
        dest.writeString(damageType);
        dest.writeString(status);
        dest.writeString(comment);
        dest.writeByte((byte) (oldFlag ? 1 : 0));
        dest.writeString(villageName);
        dest.writeString(villageID);
        dest.writeInt(isPushed);
    }
}
