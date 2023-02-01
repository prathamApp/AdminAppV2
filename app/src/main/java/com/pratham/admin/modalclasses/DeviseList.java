package com.pratham.admin.modalclasses;

public class DeviseList {
    String userid;
    String deviceid;
    String serialno;
    String brand;
    String model;
    String status;
    String Pratham_ID;
    String QR_ID;
    String WiFiMacAddress;
    String ponumber;
    String donorname;
    String yearofpurchase;
    String progname;

    transient boolean isChecked = false;

    transient boolean isSelected = false;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPratham_ID() {
        return Pratham_ID;
    }

    public void setPratham_ID(String pratham_ID) {
        Pratham_ID = pratham_ID;
    }

    public String getQR_ID() {
        return QR_ID;
    }

    public void setQR_ID(String QR_ID) {
        this.QR_ID = QR_ID;
    }

    public String getWiFiMacAddress() {
        return WiFiMacAddress;
    }

    public void setWiFiMacAddress(String wiFiMacAddress) {
        WiFiMacAddress = wiFiMacAddress;
    }

    public boolean isSelected() { return isSelected; }

    public void setSelected(boolean selected) { isSelected = selected; }

    public boolean isChecked() { return isChecked; }

    public void setChecked(boolean checked) { isChecked = checked; }

    public String getPonumber() { return ponumber; }

    public void setPonumber(String ponumber) { this.ponumber = ponumber; }

    public String getDonorname() { return donorname; }

    public void setDonorname(String donorname) { this.donorname = donorname; }

    public String getYearofpurchase() { return yearofpurchase; }

    public void setYearofpurchase(String yearofpurchase) { this.yearofpurchase = yearofpurchase; }

    public String getProgname() { return progname; }

    public void setProgname(String progname) { this.progname = progname; }
}
