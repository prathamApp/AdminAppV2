package com.pratham.admin.interfaces;

import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.modalclasses.Student;

public interface OnCheckBoxSelectedItem {
    public void addStudent(int pos, Student student);
    public void setSelectedRightSide(int pos, Student student);
    public void addDevice(int pos, DeviseList deviseList);
}
