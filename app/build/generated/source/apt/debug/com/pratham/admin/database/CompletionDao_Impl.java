package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Completion;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CompletionDao_Impl implements CompletionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Completion> __insertionAdapterOfCompletion;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCompletion;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  public CompletionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCompletion = new EntityInsertionAdapter<Completion>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Completion` (`CompletionID`,`GroupType`,`VillageID`,`ProgramId`,`GroupID`,`CourseCompleted`,`TopicCompleted`,`StartDate`,`EndDate`,`ParentParticipation`,`PresentParents`,`Event`,`CreatedBy`,`sentFlag`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Completion value) {
        if (value.CompletionID == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.CompletionID);
        }
        if (value.GroupType == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.GroupType);
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
        if (value.CourseCompleted == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.CourseCompleted);
        }
        if (value.TopicCompleted == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.TopicCompleted);
        }
        if (value.StartDate == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.StartDate);
        }
        if (value.EndDate == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.EndDate);
        }
        stmt.bindLong(10, value.ParentParticipation);
        stmt.bindLong(11, value.PresentParents);
        stmt.bindLong(12, value.Event);
        if (value.CreatedBy == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.CreatedBy);
        }
        stmt.bindLong(14, value.sentFlag);
      }
    };
    this.__preparedStmtOfDeleteAllCompletion = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Completion";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Completion SET sentFlag=? WHERE CompletionID =?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Completion SET sentFlag=?";
        return _query;
      }
    };
  }

  @Override
  public void insertCompletion(final List<Completion> completionList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCompletion.insert(completionList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllCompletion() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCompletion.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllCompletion.release(_stmt);
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
  public List<Completion> getAllCompletion() {
    final String _sql = "SELECT * FROM Completion";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCompletionID = CursorUtil.getColumnIndexOrThrow(_cursor, "CompletionID");
      final int _cursorIndexOfGroupType = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupType");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupID");
      final int _cursorIndexOfCourseCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseCompleted");
      final int _cursorIndexOfTopicCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "TopicCompleted");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "EndDate");
      final int _cursorIndexOfParentParticipation = CursorUtil.getColumnIndexOrThrow(_cursor, "ParentParticipation");
      final int _cursorIndexOfPresentParents = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentParents");
      final int _cursorIndexOfEvent = CursorUtil.getColumnIndexOrThrow(_cursor, "Event");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Completion> _result = new ArrayList<Completion>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Completion _item;
        _item = new Completion();
        if (_cursor.isNull(_cursorIndexOfCompletionID)) {
          _item.CompletionID = null;
        } else {
          _item.CompletionID = _cursor.getString(_cursorIndexOfCompletionID);
        }
        if (_cursor.isNull(_cursorIndexOfGroupType)) {
          _item.GroupType = null;
        } else {
          _item.GroupType = _cursor.getString(_cursorIndexOfGroupType);
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
        if (_cursor.isNull(_cursorIndexOfCourseCompleted)) {
          _item.CourseCompleted = null;
        } else {
          _item.CourseCompleted = _cursor.getString(_cursorIndexOfCourseCompleted);
        }
        if (_cursor.isNull(_cursorIndexOfTopicCompleted)) {
          _item.TopicCompleted = null;
        } else {
          _item.TopicCompleted = _cursor.getString(_cursorIndexOfTopicCompleted);
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
        _item.PresentParents = _cursor.getInt(_cursorIndexOfPresentParents);
        _item.Event = _cursor.getInt(_cursorIndexOfEvent);
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
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
  public List<Completion> getNewCompletions(final int status) {
    final String _sql = "SELECT * FROM Completion WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCompletionID = CursorUtil.getColumnIndexOrThrow(_cursor, "CompletionID");
      final int _cursorIndexOfGroupType = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupType");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupID");
      final int _cursorIndexOfCourseCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseCompleted");
      final int _cursorIndexOfTopicCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "TopicCompleted");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "EndDate");
      final int _cursorIndexOfParentParticipation = CursorUtil.getColumnIndexOrThrow(_cursor, "ParentParticipation");
      final int _cursorIndexOfPresentParents = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentParents");
      final int _cursorIndexOfEvent = CursorUtil.getColumnIndexOrThrow(_cursor, "Event");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Completion> _result = new ArrayList<Completion>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Completion _item;
        _item = new Completion();
        if (_cursor.isNull(_cursorIndexOfCompletionID)) {
          _item.CompletionID = null;
        } else {
          _item.CompletionID = _cursor.getString(_cursorIndexOfCompletionID);
        }
        if (_cursor.isNull(_cursorIndexOfGroupType)) {
          _item.GroupType = null;
        } else {
          _item.GroupType = _cursor.getString(_cursorIndexOfGroupType);
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
        if (_cursor.isNull(_cursorIndexOfCourseCompleted)) {
          _item.CourseCompleted = null;
        } else {
          _item.CourseCompleted = _cursor.getString(_cursorIndexOfCourseCompleted);
        }
        if (_cursor.isNull(_cursorIndexOfTopicCompleted)) {
          _item.TopicCompleted = null;
        } else {
          _item.TopicCompleted = _cursor.getString(_cursorIndexOfTopicCompleted);
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
        _item.PresentParents = _cursor.getInt(_cursorIndexOfPresentParents);
        _item.Event = _cursor.getInt(_cursorIndexOfEvent);
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
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
