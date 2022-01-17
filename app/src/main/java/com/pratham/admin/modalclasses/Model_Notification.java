package com.pratham.admin.modalclasses;

import java.util.List;

public class Model_Notification {
    String AssignDate;
    String AssignById;
    String AssignToId;
    String AssignByName;
    String AssignToName;
    String Status;
    List<DeviseList> TabList;


    public Model_Notification(){}

    public Model_Notification(String AssignDate, String AssignById, String AssignToId, String AssignByName, String AssignToName, String Status, List<DeviseList> TabList) {
        this.AssignDate = AssignDate;
        this.AssignById = AssignById;
        this.AssignToId = AssignToId;
        this.AssignByName = AssignByName;
        this.AssignToName = AssignToName;
        this.Status = Status;
        this.TabList = TabList;

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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<DeviseList> getTabList() {
        return TabList;
    }

    public void setTabList(List<DeviseList> tabList) {
        TabList = tabList;
    }
}
