package com.pratham.admin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pratham.admin.modalclasses.Aser;

import java.util.List;

/**
 * Created by PEF on 29/11/2018.
 */
@Dao
public interface AserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllAserList(List<Aser> studentsList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAser(Aser aserData);

    @Query("SELECT * FROM Aser WHERE StudentId=:StudentId AND TestType=:TestType")
    public boolean CheckDataExists(String StudentId, int TestType);

    @Query("update Aser set English=:engSpin,EnglishSelected=:engMeaning,ChildID=:ChildID, TestDate=:testDate, Lang=:lang, Num=:num, OAdd=:oad, OSub=:osb, OMul=:oml, ODiv =:odv, WAdd =:wad, WSub =:wsb, CreatedBy =:crtby, CreatedDate =:crtdt, FLAG =:isSelected, sentFlag=:sentFlag WHERE StudentId=:studentID AND TestType =:TstType")
    public void UpdateAserData(int engSpin, int engMeaning, String ChildID, String testDate, int lang, int num, int oad, int osb, int oml, int odv, int wad, int wsb, String crtby, String crtdt, int isSelected, int sentFlag, String studentID, int TstType);

    @Query("DELETE FROM Aser")
    public void deleteAllAser();

    @Query("select * from Aser where StudentId =:StudentID AND TestType =:testV")
    public List<Aser> GetAllByStudentID(String StudentID, int testV);

    @Query("SELECT * FROM Aser WHERE sentFlag=:status")
    public List<Aser> getNewAser(int status);

    @Query("UPDATE Aser SET sentFlag=:pushStatus")
    void updateAllSentFlag(int pushStatus);

    @Query("UPDATE Aser SET sentFlag=:pushStatus WHERE StudentId =:sID AND TestType=:testT")
    void updateSentFlag(int pushStatus, String sID, int testT);
}
