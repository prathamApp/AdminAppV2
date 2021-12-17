package com.pratham.admin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pratham.admin.modalclasses.Attendance;

import java.util.List;

@Dao
public interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAttendance(List<Attendance> attendancesList);

    @Query("DELETE FROM Attendance")
    public void deleteAllAttendances();

    @Query("SELECT * FROM Attendance")
    public List<Attendance> getAllAttendances();

    @Query("UPDATE Attendance SET sentFlag=:pushStatus WHERE AttendanceID=:aID")
    void updateSentFlag(int pushStatus, String aID);

    @Query("SELECT * FROM Attendance WHERE sentFlag=:status")
    public List<Attendance> getNewAttendances(int status);

    @Query("UPDATE Attendance SET sentFlag=:pushStatus")
    void updateAllSentFlag(int pushStatus);

}
