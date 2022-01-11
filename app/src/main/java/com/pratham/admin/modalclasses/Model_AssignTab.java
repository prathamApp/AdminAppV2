package com.pratham.admin.modalclasses;

import java.util.List;

public class Model_AssignTab {
    String uuId;
    String loggedInPersonId;
    String loggedInPersonName;
    String assigneePersonId;
    String assigneePersonName;
    String currentDateTime;
    List<DeviseList> assignTabList;
    String metaData;

    public Model_AssignTab(){}

    public Model_AssignTab(String uuId, String loggedInPersonId, String loggedInPersonName, String assigneePersonId, String assigneePersonName, String currentDateTime, List<DeviseList> assignTabList, String metaData) {
        this.uuId = uuId;
        this.loggedInPersonId = loggedInPersonId;
        this.loggedInPersonName = loggedInPersonName;
        this.assigneePersonId = assigneePersonId;
        this.assigneePersonName = assigneePersonName;
        this.currentDateTime = currentDateTime;
        this.assignTabList = assignTabList;
        this.metaData = metaData;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public String getLoggedInPersonId() {
        return loggedInPersonId;
    }

    public void setLoggedInPersonId(String loggedInPersonId) {
        this.loggedInPersonId = loggedInPersonId;
    }

    public String getLoggedInPersonName() {
        return loggedInPersonName;
    }

    public void setLoggedInPersonName(String loggedInPersonName) {
        this.loggedInPersonName = loggedInPersonName;
    }

    public String getAssigneePersonId() {
        return assigneePersonId;
    }

    public void setAssigneePersonId(String assigneePersonId) {
        this.assigneePersonId = assigneePersonId;
    }

    public String getAssigneePersonName() {
        return assigneePersonName;
    }

    public void setAssigneePersonName(String assigneePersonName) {
        this.assigneePersonName = assigneePersonName;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public List<DeviseList> getAssignTabList() {
        return assignTabList;
    }

    public void setAssignTabList(List<DeviseList> assignTabList) {
        this.assignTabList = assignTabList;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }
}
