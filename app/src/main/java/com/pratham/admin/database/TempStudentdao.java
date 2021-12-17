package com.pratham.admin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pratham.admin.modalclasses.TempStudent;

import java.util.List;

@Dao
public interface TempStudentdao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTempStudent(List<TempStudent> tempChangesList);

    @Query("DELETE FROM TempStudent")
    public void deleteTempStudent();

    @Query("SELECT * FROM TempStudent")
    public List<TempStudent> getAllempStudent();

}
