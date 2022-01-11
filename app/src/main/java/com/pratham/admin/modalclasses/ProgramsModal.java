package com.pratham.admin.modalclasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgramsModal implements Parcelable {

    @SerializedName("ProgramId")
    private Integer programId;
    @SerializedName("ProgramName")
    private String programName;

    /*public ProgramsModal(Integer programId, String programName) {
        this.programId = programId;
        this.programName = programName;
    }*/

    public ProgramsModal(){}

    protected ProgramsModal(Parcel in) {
        if (in.readByte() == 0) {
            programId = null;
        } else {
            programId = in.readInt();
        }
        programName = in.readString();
    }

    public static final Creator<ProgramsModal> CREATOR = new Creator<ProgramsModal>() {
        @Override
        public ProgramsModal createFromParcel(Parcel in) {
            return new ProgramsModal(in);
        }

        @Override
        public ProgramsModal[] newArray(int size) {
            return new ProgramsModal[size];
        }
    };

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (programId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(programId);
        }
        dest.writeString(programName);
    }
}