package com.pratham.admin.modalclasses;


public class Model_ReplaceTab {
    String CRL_ID;
    String TabletSerialId;
    String DeviceID;
    String AssigneeID;
    String ReplaceDate;
    String contactnumber;
    String isscreendamage;
    String isbodydamage;
    String ismicrophonedamage;
    String iscameradamage;
    String isspeakerissue;
    String isbatterydamage;
    String isswitchissue;
    String isotherissue;
    String detaildescription;

    transient boolean isChecked = false;

    public Model_ReplaceTab() {
    }

    public Model_ReplaceTab(String CRL_ID, String tabletSerialId, String deviceID, String assigneeID, String replaceDate, String contactnumber, String isscreendamage, String isbodydamage, String ismicrophonedamage, String iscameradamage, String isspeakerissue, String isbatterydamage, String isswitchissue, String isotherissue, String detaildescription) {
        this.CRL_ID = CRL_ID;
        TabletSerialId = tabletSerialId;
        DeviceID = deviceID;
        AssigneeID = assigneeID;
        ReplaceDate = replaceDate;
        this.contactnumber = contactnumber;
        this.isscreendamage = isscreendamage;
        this.isbodydamage = isbodydamage;
        this.ismicrophonedamage = ismicrophonedamage;
        this.iscameradamage = iscameradamage;
        this.isspeakerissue = isspeakerissue;
        this.isbatterydamage = isbatterydamage;
        this.isswitchissue = isswitchissue;
        this.isotherissue = isotherissue;
        this.detaildescription = detaildescription;
    }

    public String getCRL_ID() {
        return CRL_ID;
    }

    public void setCRL_ID(String CRL_ID) {
        this.CRL_ID = CRL_ID;
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

    public String getIsbatterydamage() {
        return isbatterydamage;
    }

    public void setIsbatterydamage(String isbatterydamage) {
        this.isbatterydamage = isbatterydamage;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getAssigneeID() {
        return AssigneeID;
    }

    public void setAssigneeID(String assigneeID) {
        AssigneeID = assigneeID;
    }

    public String getReplaceDate() {
        return ReplaceDate;
    }

    public void setReplaceDate(String replaceDate) {
        ReplaceDate = replaceDate;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getIsscreendamage() {
        return isscreendamage;
    }

    public void setIsscreendamage(String isscreendamage) {
        this.isscreendamage = isscreendamage;
    }

    public String getIsbodydamage() {
        return isbodydamage;
    }

    public void setIsbodydamage(String isbodydamage) {
        this.isbodydamage = isbodydamage;
    }

    public String getIsmicrophonedamage() {
        return ismicrophonedamage;
    }

    public void setIsmicrophonedamage(String ismicrophonedamage) {
        this.ismicrophonedamage = ismicrophonedamage;
    }

    public String getIscameradamage() {
        return iscameradamage;
    }

    public void setIscameradamage(String iscameradamage) {
        this.iscameradamage = iscameradamage;
    }

    public String getIsspeakerissue() {
        return isspeakerissue;
    }

    public void setIsspeakerissue(String isspeakerissue) {
        this.isspeakerissue = isspeakerissue;
    }

    public String getIsswitchissue() {
        return isswitchissue;
    }

    public void setIsswitchissue(String isswitchissue) {
        this.isswitchissue = isswitchissue;
    }

    public String getIsotherissue() {
        return isotherissue;
    }

    public void setIsotherissue(String isotherissue) {
        this.isotherissue = isotherissue;
    }

    public String getDetaildescription() {
        return detaildescription;
    }

    public void setDetaildescription(String detaildescription) {
        this.detaildescription = detaildescription;
    }
}
