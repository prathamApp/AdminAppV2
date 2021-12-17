package com.pratham.admin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pratham.admin.modalclasses.GroupSession;

import java.util.List;

@Dao
public interface GroupSessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllGroupSession(List<GroupSession> GroupSessionDaoList);

    @Query("DELETE FROM GroupSession")
    public void deleteAllGroupSession();

    @Query("SELECT * FROM GroupSession")
    public List<GroupSession> getAllGroupSession();

    @Query("SELECT * FROM GroupSession WHERE sentFlag=:status")
    public List<GroupSession> getNewGroupSessions(int status);

    @Query("UPDATE GroupSession SET sentFlag=:pushStatus")
    void updateAllSentFlag(int pushStatus);

    @Query("UPDATE GroupSession SET sentFlag=:pushStatus WHERE GroupSessionID=:gID")
    void updateSentFlag(int pushStatus, String gID);

}
