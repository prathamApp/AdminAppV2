package com.pratham.admin.modalclasses;

public class Model_ReportLost {
    String CRL_ID;
    String TabletSerialId;
    String DeviceID;
    String ReportLostDate;
    String contactnumber;
    String tabletlastseendate;
    String brandmodel;
    String lastseenwithperson;
    String personreportingto;
    String ischarger;
    String isleathercover;
    String issdcard;
    String additionalremark;
    String ispolicecomplaint;
    String incidentverifiedat;
    String enquiryundertaken;
    String enquiryconcluded;
    String imageurl;
    String reportstoid;

    public Model_ReportLost(String CRL_ID, String tabletSerialId, String deviceID, String reportLostDate, String contactnumber,
                            String tabletlastseendate, String brandmodel, String lastseenwithperson, String personreportingto,
                            String ischarger, String isleathercover, String issdcard, String additionalremark, String ispolicecomplaint,
                            String incidentverifiedat, String enquiryundertaken, String enquiryconcluded, String imageurl, String reportstoid) {
        this.CRL_ID = CRL_ID;
        TabletSerialId = tabletSerialId;
        DeviceID = deviceID;
        ReportLostDate = reportLostDate;
        this.contactnumber = contactnumber;
        this.tabletlastseendate = tabletlastseendate;
        this.brandmodel = brandmodel;
        this.lastseenwithperson = lastseenwithperson;
        this.personreportingto = personreportingto;
        this.ischarger = ischarger;
        this.isleathercover = isleathercover;
        this.issdcard = issdcard;
        this.additionalremark = additionalremark;
        this.ispolicecomplaint = ispolicecomplaint;
        this.incidentverifiedat = incidentverifiedat;
        this.enquiryundertaken = enquiryundertaken;
        this.enquiryconcluded = enquiryconcluded;
        this.imageurl = imageurl;
        this.reportstoid = reportstoid;
    }

    public String getCrlId() {
        return CRL_ID;
    }

    public void setCrlId(String crlId) {
        CRL_ID = crlId;
    }

    public String getTabletSerialId() {
        return TabletSerialId;
    }

    public void setTabletSerialId(String tabletSerialId) {
        TabletSerialId = tabletSerialId;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getReportLostDate() {
        return ReportLostDate;
    }

    public void setReportLostDate(String reportLostDate) {
        ReportLostDate = reportLostDate;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getTabletlastseendate() {
        return tabletlastseendate;
    }

    public void setTabletlastseendate(String tabletlastseendate) {
        this.tabletlastseendate = tabletlastseendate;
    }

    public String getBrandmodel() {
        return brandmodel;
    }

    public void setBrandmodel(String brandmodel) {
        this.brandmodel = brandmodel;
    }

    public String getLastseenwithperson() {
        return lastseenwithperson;
    }

    public void setLastseenwithperson(String lastseenwithperson) {
        this.lastseenwithperson = lastseenwithperson;
    }

    public String getPersonreportingto() {
        return personreportingto;
    }

    public void setPersonreportingto(String personreportingto) {
        this.personreportingto = personreportingto;
    }

    public String getIscharger() {
        return ischarger;
    }

    public void setIscharger(String ischarger) {
        this.ischarger = ischarger;
    }

    public String getIsleathercover() {
        return isleathercover;
    }

    public void setIsleathercover(String isleathercover) {
        this.isleathercover = isleathercover;
    }

    public String getIssdcard() {
        return issdcard;
    }

    public void setIssdcard(String issdcard) {
        this.issdcard = issdcard;
    }

    public String getAdditionalremark() {
        return additionalremark;
    }

    public void setAdditionalremark(String additionalremark) {
        this.additionalremark = additionalremark;
    }

    public String getIspolicecomplaint() {
        return ispolicecomplaint;
    }

    public void setIspolicecomplaint(String ispolicecomplaint) {
        this.ispolicecomplaint = ispolicecomplaint;
    }

    public String getIncidentverifiedat() {
        return incidentverifiedat;
    }

    public void setIncidentverifiedat(String incidentverifiedat) {
        this.incidentverifiedat = incidentverifiedat;
    }

    public String getEnquiryundertaken() {
        return enquiryundertaken;
    }

    public void setEnquiryundertaken(String enquiryundertaken) {
        this.enquiryundertaken = enquiryundertaken;
    }

    public String getEnquiryconcluded() {
        return enquiryconcluded;
    }

    public void setEnquiryconcluded(String enquiryconcluded) {
        this.enquiryconcluded = enquiryconcluded;
    }

    public String getImageurl() { return imageurl; }

    public void setImageurl(String imageurl) { this.imageurl = imageurl; }

    public String getReportstoid() { return reportstoid; }

    public void setReportstoid(String reportstoid) { this.reportstoid = reportstoid; }
}

