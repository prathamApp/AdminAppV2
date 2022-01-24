package com.pratham.admin.modalclasses;

import java.util.List;

public class Model_Notification {
    String AssignDate;
    String AssignById;
    String AssignToId;
    String AssignByName;
    String AssignToName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;
    String Ackstatus;
    List<DeviseList> lstackdevice;


    public Model_Notification(){}

    public Model_Notification(String AssignDate, String AssignById, String AssignToId, String AssignByName, String AssignToName, String status,String Ackstatus, List<DeviseList> lstackdevice) {
        this.AssignDate = AssignDate;
        this.AssignById = AssignById;
        this.AssignToId = AssignToId;
        this.AssignByName = AssignByName;
        this.AssignToName = AssignToName;
        this.status = status;
        this.Ackstatus = Ackstatus;
        this.lstackdevice = lstackdevice;

    }

    public String getAssignDate() {
        return AssignDate;
    }

    public void setAssignDate(String assignDate) {
        AssignDate = assignDate;
    }

    public String getAssignById() {
        return AssignById;
    }

    public void setAssignById(String assignById) {
        AssignById = assignById;
    }

    public String getAssignToId() {
        return AssignToId;
    }

    public void setAssignToId(String assignToId) {
        AssignToId = assignToId;
    }

    public String getAssignByName() {
        return AssignByName;
    }

    public void setAssignByName(String assignByName) {
        AssignByName = assignByName;
    }

    public String getAssignToName() {
        return AssignToName;
    }

    public void setAssignToName(String assignToName) {
        AssignToName = assignToName;
    }



    public String getAckstatus() {
        return Ackstatus;
    }

    public void setAckstatus(String ackstatus) {
        Ackstatus = ackstatus;
    }

    public List<DeviseList> getLstackdevice() {
        return lstackdevice;
    }

    public void setLstackdevice(List<DeviseList> lstackdevice) {
        this.lstackdevice = lstackdevice;
    }
}
