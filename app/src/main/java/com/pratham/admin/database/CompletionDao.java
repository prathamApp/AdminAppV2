package com.pratham.admin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pratham.admin.modalclasses.Completion;

import java.util.List;

@Dao
public interface CompletionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCompletion(List<Completion> completionList);

    @Query("DELETE FROM Completion")
    public void deleteAllCompletion();

    @Query("SELECT * FROM Completion")
    public List<Completion> getAllCompletion();

    @Query("UPDATE Completion SET sentFlag=:pushStatus WHERE CompletionID =:cID")
    void updateSentFlag(int pushStatus, String cID);

    @Query("SELECT * FROM Completion WHERE sentFlag=:status")
    public List<Completion> getNewCompletions(int status);

    @Query("UPDATE Completion SET sentFlag=:pushStatus")
    void updateAllSentFlag(int pushStatus);

}
