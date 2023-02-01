package com.pratham.admin.modalclasses;

public class Model_NewTablet {

    String userId;// person who has logged in to app
    String deviceId;// send blank, auto generated at serer side
    String serialNo;// tablet serial no on scanning barcode
    String brand;
    String model;
    String status;
    String prathamId;// send blank
    String qrId;// send blank
    String WiFiMacAddress;// send blank
    String donor;
    String vendor;
    String yop;
    String programName;
    String programId;
    String addedOn;// current date time
    String poprogramid;
    String ponumber;

    public Model_NewTablet() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
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

    public String getPrathamId() {
        return prathamId;
    }

    public void setPrathamId(String prathamId) {
        this.prathamId = prathamId;
    }

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

    public String getWiFiMacAddress() {
        return WiFiMacAddress;
    }

    public void setWiFiMacAddress(String wiFiMacAddress) {
        WiFiMacAddress = wiFiMacAddress;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getYop() {
        return yop;
    }

    public void setYop(String yop) {
        this.yop = yop;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getPoprogramid() { return poprogramid; }

    public void setPoprogramid(String poprogramid) { this.poprogramid = poprogramid; }

    public String getPonumber() { return ponumber; }

    public void setPonumber(String ponumber) { this.ponumber = ponumber; }
}
