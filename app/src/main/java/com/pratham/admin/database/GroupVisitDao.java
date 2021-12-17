package com.pratham.admin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pratham.admin.modalclasses.GroupVisit;

import java.util.List;

@Dao
public interface GroupVisitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllCRLVisit(List<GroupVisit> GroupVisitDaoList);

    @Query("DELETE FROM GroupVisit")
    public void deleteAllGroupVisit();

    @Query("SELECT * FROM GroupVisit")
    public List<GroupVisit> getAllGroupVisit();

    @Query("SELECT * FROM GroupVisit WHERE sentFlag=:status")
    public List<GroupVisit> getNewGroupVisits(int status);

    @Query("UPDATE GroupVisit SET sentFlag=:pushStatus")
    void updateAllSentFlag(int pushStatus);

    @Query("UPDATE GroupVisit SET sentFlag=:pushStatus WHERE GroupVisitID=:gID")
    void updateSentFlag(int pushStatus, String gID);

}
