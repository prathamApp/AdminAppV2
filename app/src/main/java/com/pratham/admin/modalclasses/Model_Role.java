package com.pratham.admin.modalclasses;

import com.google.gson.annotations.SerializedName;

public class Model_Role {
    @SerializedName("roleid")
    private Integer roleid;

    @SerializedName("rolename")
    private String rolename;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
