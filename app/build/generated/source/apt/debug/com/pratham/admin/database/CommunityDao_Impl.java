package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Community;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CommunityDao_Impl implements CommunityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Community> __insertionAdapterOfCommunity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCommunity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  public CommunityDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCommunity = new EntityInsertionAdapter<Community>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Community` (`CommunityID`,`Community`,`VillageID`,`ProgramId`,`GroupID`,`CourseAdded`,`TopicAdded`,`CoachID`,`StartDate`,`EndDate`,`ParentParticipation`,`PresentStudent`,`AddedCourseID`,`AddedTopicsID`,`CreatedBy`,`CreatedDate`,`sentFlag`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Community value) {
        if (value.CommunityID == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.CommunityID);
        }
        if (value.Community == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.Community);
        }
        if (value.VillageID == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.VillageID);
        }
        if (value.ProgramId == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.ProgramId);
        }
        if (value.GroupID == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.GroupID);
        }
        if (value.CourseAdded == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.CourseAdded);
        }
        if (value.TopicAdded == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.TopicAdded);
        }
        if (value.CoachID == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.CoachID);
        }
        if (value.StartDate == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.StartDate);
        }
        if (value.EndDate == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.EndDate);
        }
        stmt.bindLong(11, value.ParentParticipation);
        stmt.bindLong(12, value.PresentStudent);
        if (value.AddedCourseID == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.AddedCourseID);
        }
        if (value.AddedTopicsID == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.AddedTopicsID);
        }
        if (value.CreatedBy == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.CreatedBy);
        }
        if (value.CreatedDate == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.CreatedDate);
        }
        stmt.bindLong(17, value.sentFlag);
      }
    };
    this.__preparedStmtOfDeleteAllCommunity = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Community";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Community SET sentFlag=? WHERE CommunityID =?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Community SET sentFlag=?";
        return _query;
      }
    };
  }

  @Override
  public void insertCommunity(final List<Community> communityList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCommunity.insert(communityList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllCommunity() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCommunity.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllCommunity.release(_stmt);
    }
  }

  @Override
  public void updateSentFlag(final int pushStatus, final String cID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSentFlag.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, pushStatus);
    _argIndex = 2;
    if (cID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, cID);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateSentFlag.release(_stmt);
    }
  }

  @Override
  public void updateAllSentFlag(final int pushStatus) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAllSentFlag.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, pushStatus);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateAllSentFlag.release(_stmt);
    }
  }

  @Override
  public List<Community> getAllCommunity() {
    final String _sql = "SELECT * FROM Community";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCommunityID = CursorUtil.getColumnIndexOrThrow(_cursor, "CommunityID");
      final int _cursorIndexOfCommunity = CursorUtil.getColumnIndexOrThrow(_cursor, "Community");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupID");
      final int _cursorIndexOfCourseAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseAdded");
      final int _cursorIndexOfTopicAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "TopicAdded");
      final int _cursorIndexOfCoachID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachID");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "EndDate");
      final int _cursorIndexOfParentParticipation = CursorUtil.getColumnIndexOrThrow(_cursor, "ParentParticipation");
      final int _cursorIndexOfPresentStudent = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentStudent");
      final int _cursorIndexOfAddedCourseID = CursorUtil.getColumnIndexOrThrow(_cursor, "AddedCourseID");
      final int _cursorIndexOfAddedTopicsID = CursorUtil.getColumnIndexOrThrow(_cursor, "AddedTopicsID");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedDate");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Community> _result = new ArrayList<Community>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Community _item;
        _item = new Community();
        if (_cursor.isNull(_cursorIndexOfCommunityID)) {
          _item.CommunityID = null;
        } else {
          _item.CommunityID = _cursor.getString(_cursorIndexOfCommunityID);
        }
        if (_cursor.isNull(_cursorIndexOfCommunity)) {
          _item.Community = null;
        } else {
          _item.Community = _cursor.getString(_cursorIndexOfCommunity);
        }
        if (_cursor.isNull(_cursorIndexOfVillageID)) {
          _item.VillageID = null;
        } else {
          _item.VillageID = _cursor.getString(_cursorIndexOfVillageID);
        }
        if (_cursor.isNull(_cursorIndexOfProgramId)) {
          _item.ProgramId = null;
        } else {
          _item.ProgramId = _cursor.getString(_cursorIndexOfProgramId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupID)) {
          _item.GroupID = null;
        } else {
          _item.GroupID = _cursor.getString(_cursorIndexOfGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfCourseAdded)) {
          _item.CourseAdded = null;
        } else {
          _item.CourseAdded = _cursor.getString(_cursorIndexOfCourseAdded);
        }
        if (_cursor.isNull(_cursorIndexOfTopicAdded)) {
          _item.TopicAdded = null;
        } else {
          _item.TopicAdded = _cursor.getString(_cursorIndexOfTopicAdded);
        }
        if (_cursor.isNull(_cursorIndexOfCoachID)) {
          _item.CoachID = null;
        } else {
          _item.CoachID = _cursor.getString(_cursorIndexOfCoachID);
        }
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _item.StartDate = null;
        } else {
          _item.StartDate = _cursor.getString(_cursorIndexOfStartDate);
        }
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _item.EndDate = null;
        } else {
          _item.EndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        _item.ParentParticipation = _cursor.getInt(_cursorIndexOfParentParticipation);
        _item.PresentStudent = _cursor.getInt(_cursorIndexOfPresentStudent);
        if (_cursor.isNull(_cursorIndexOfAddedCourseID)) {
          _item.AddedCourseID = null;
        } else {
          _item.AddedCourseID = _cursor.getString(_cursorIndexOfAddedCourseID);
        }
        if (_cursor.isNull(_cursorIndexOfAddedTopicsID)) {
          _item.AddedTopicsID = null;
        } else {
          _item.AddedTopicsID = _cursor.getString(_cursorIndexOfAddedTopicsID);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
          _item.CreatedDate = null;
        } else {
          _item.CreatedDate = _cursor.getString(_cursorIndexOfCreatedDate);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Community> getCommunityByGroupID(final String gid) {
    final String _sql = "SELECT * FROM Community Where GroupID=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (gid == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, gid);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCommunityID = CursorUtil.getColumnIndexOrThrow(_cursor, "CommunityID");
      final int _cursorIndexOfCommunity = CursorUtil.getColumnIndexOrThrow(_cursor, "Community");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupID");
      final int _cursorIndexOfCourseAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseAdded");
      final int _cursorIndexOfTopicAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "TopicAdded");
      final int _cursorIndexOfCoachID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachID");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "EndDate");
      final int _cursorIndexOfParentParticipation = CursorUtil.getColumnIndexOrThrow(_cursor, "ParentParticipation");
      final int _cursorIndexOfPresentStudent = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentStudent");
      final int _cursorIndexOfAddedCourseID = CursorUtil.getColumnIndexOrThrow(_cursor, "AddedCourseID");
      final int _cursorIndexOfAddedTopicsID = CursorUtil.getColumnIndexOrThrow(_cursor, "AddedTopicsID");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedDate");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Community> _result = new ArrayList<Community>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Community _item;
        _item = new Community();
        if (_cursor.isNull(_cursorIndexOfCommunityID)) {
          _item.CommunityID = null;
        } else {
          _item.CommunityID = _cursor.getString(_cursorIndexOfCommunityID);
        }
        if (_cursor.isNull(_cursorIndexOfCommunity)) {
          _item.Community = null;
        } else {
          _item.Community = _cursor.getString(_cursorIndexOfCommunity);
        }
        if (_cursor.isNull(_cursorIndexOfVillageID)) {
          _item.VillageID = null;
        } else {
          _item.VillageID = _cursor.getString(_cursorIndexOfVillageID);
        }
        if (_cursor.isNull(_cursorIndexOfProgramId)) {
          _item.ProgramId = null;
        } else {
          _item.ProgramId = _cursor.getString(_cursorIndexOfProgramId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupID)) {
          _item.GroupID = null;
        } else {
          _item.GroupID = _cursor.getString(_cursorIndexOfGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfCourseAdded)) {
          _item.CourseAdded = null;
        } else {
          _item.CourseAdded = _cursor.getString(_cursorIndexOfCourseAdded);
        }
        if (_cursor.isNull(_cursorIndexOfTopicAdded)) {
          _item.TopicAdded = null;
        } else {
          _item.TopicAdded = _cursor.getString(_cursorIndexOfTopicAdded);
        }
        if (_cursor.isNull(_cursorIndexOfCoachID)) {
          _item.CoachID = null;
        } else {
          _item.CoachID = _cursor.getString(_cursorIndexOfCoachID);
        }
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _item.StartDate = null;
        } else {
          _item.StartDate = _cursor.getString(_cursorIndexOfStartDate);
        }
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _item.EndDate = null;
        } else {
          _item.EndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        _item.ParentParticipation = _cursor.getInt(_cursorIndexOfParentParticipation);
        _item.PresentStudent = _cursor.getInt(_cursorIndexOfPresentStudent);
        if (_cursor.isNull(_cursorIndexOfAddedCourseID)) {
          _item.AddedCourseID = null;
        } else {
          _item.AddedCourseID = _cursor.getString(_cursorIndexOfAddedCourseID);
        }
        if (_cursor.isNull(_cursorIndexOfAddedTopicsID)) {
          _item.AddedTopicsID = null;
        } else {
          _item.AddedTopicsID = _cursor.getString(_cursorIndexOfAddedTopicsID);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
          _item.CreatedDate = null;
        } else {
          _item.CreatedDate = _cursor.getString(_cursorIndexOfCreatedDate);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Community> getNewCommunities(final int status) {
    final String _sql = "SELECT * FROM Community WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCommunityID = CursorUtil.getColumnIndexOrThrow(_cursor, "CommunityID");
      final int _cursorIndexOfCommunity = CursorUtil.getColumnIndexOrThrow(_cursor, "Community");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupID");
      final int _cursorIndexOfCourseAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseAdded");
      final int _cursorIndexOfTopicAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "TopicAdded");
      final int _cursorIndexOfCoachID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachID");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "EndDate");
      final int _cursorIndexOfParentParticipation = CursorUtil.getColumnIndexOrThrow(_cursor, "ParentParticipation");
      final int _cursorIndexOfPresentStudent = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentStudent");
      final int _cursorIndexOfAddedCourseID = CursorUtil.getColumnIndexOrThrow(_cursor, "AddedCourseID");
      final int _cursorIndexOfAddedTopicsID = CursorUtil.getColumnIndexOrThrow(_cursor, "AddedTopicsID");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedDate");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Community> _result = new ArrayList<Community>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Community _item;
        _item = new Community();
        if (_cursor.isNull(_cursorIndexOfCommunityID)) {
          _item.CommunityID = null;
        } else {
          _item.CommunityID = _cursor.getString(_cursorIndexOfCommunityID);
        }
        if (_cursor.isNull(_cursorIndexOfCommunity)) {
          _item.Community = null;
        } else {
          _item.Community = _cursor.getString(_cursorIndexOfCommunity);
        }
        if (_cursor.isNull(_cursorIndexOfVillageID)) {
          _item.VillageID = null;
        } else {
          _item.VillageID = _cursor.getString(_cursorIndexOfVillageID);
        }
        if (_cursor.isNull(_cursorIndexOfProgramId)) {
          _item.ProgramId = null;
        } else {
          _item.ProgramId = _cursor.getString(_cursorIndexOfProgramId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupID)) {
          _item.GroupID = null;
        } else {
          _item.GroupID = _cursor.getString(_cursorIndexOfGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfCourseAdded)) {
          _item.CourseAdded = null;
        } else {
          _item.CourseAdded = _cursor.getString(_cursorIndexOfCourseAdded);
        }
        if (_cursor.isNull(_cursorIndexOfTopicAdded)) {
          _item.TopicAdded = null;
        } else {
          _item.TopicAdded = _cursor.getString(_cursorIndexOfTopicAdded);
        }
        if (_cursor.isNull(_cursorIndexOfCoachID)) {
          _item.CoachID = null;
        } else {
          _item.CoachID = _cursor.getString(_cursorIndexOfCoachID);
        }
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _item.StartDate = null;
        } else {
          _item.StartDate = _cursor.getString(_cursorIndexOfStartDate);
        }
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _item.EndDate = null;
        } else {
          _item.EndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        _item.ParentParticipation = _cursor.getInt(_cursorIndexOfParentParticipation);
        _item.PresentStudent = _cursor.getInt(_cursorIndexOfPresentStudent);
        if (_cursor.isNull(_cursorIndexOfAddedCourseID)) {
          _item.AddedCourseID = null;
        } else {
          _item.AddedCourseID = _cursor.getString(_cursorIndexOfAddedCourseID);
        }
        if (_cursor.isNull(_cursorIndexOfAddedTopicsID)) {
          _item.AddedTopicsID = null;
        } else {
          _item.AddedTopicsID = _cursor.getString(_cursorIndexOfAddedTopicsID);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
          _item.CreatedDate = null;
        } else {
          _item.CreatedDate = _cursor.getString(_cursorIndexOfCreatedDate);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
