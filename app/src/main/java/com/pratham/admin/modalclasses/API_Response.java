package com.pratham.admin.modalclasses;

import com.google.gson.annotations.SerializedName;

public class API_Response {
    //{"TransId":null,"ErrorId":"1","ErrorDesc":"CRL_ID Not Found"}

    String TransId;
    String ErrorId;
    String ErrorDesc;

    public String getTransId() {
        return TransId;
    }

    public void setTransId(String transId) {
        TransId = transId;
    }

    public String getErrorId() {
        return ErrorId;
    }

    public void setErrorId(String errorId) {
        ErrorId = errorId;
    }

    public String getErrorDesc() {
        return ErrorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        ErrorDesc = errorDesc;
    }

    /*    @SerializedName("Auto_ID")
    private Integer autoID;

    @SerializedName("CRL_ID")
    private String crlId;

    @SerializedName("SubmitedToID")
    private String submitedToID;

    @SerializedName("NoOfTablet")
    private Integer noOfTablet;

    @SerializedName("RequestDate")
    private String requestDate;

    @SerializedName("Trans_id")
    private Object transId;

    @SerializedName("status")
    private String status;

    @SerializedName("dateadded")
    private Object dateadded;

    public Integer getAutoID() {
        return autoID;
    }

    public void setAutoID(Integer autoID) {
        this.autoID = autoID;
    }

    public String getCrlId() {
        return crlId;
    }

    public void setCrlId(String crlId) {
        this.crlId = crlId;
    }

    public String getSubmitedToID() {
        return submitedToID;
    }

    public void setSubmitedToID(String submitedToID) {
        this.submitedToID = submitedToID;
    }

    public Integer getNoOfTablet() {
        return noOfTablet;
    }

    public void setNoOfTablet(Integer noOfTablet) {
        this.noOfTablet = noOfTablet;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public Object getTransId() {
        return transId;
    }

    public void setTransId(Object transId) {
        this.transId = transId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getDateadded() {
        return dateadded;
    }

    public void setDateadded(Object dateadded) {
        this.dateadded = dateadded;
    }*/
}
