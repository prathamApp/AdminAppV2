package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.GroupVisit;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class GroupVisitDao_Impl implements GroupVisitDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GroupVisit> __insertionAdapterOfGroupVisit;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllGroupVisit;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSentFlag;

  public GroupVisitDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGroupVisit = new EntityInsertionAdapter<GroupVisit>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `GroupVisit` (`GroupVisitID`,`VillageID`,`Village`,`DateVisited`,`StartTime`,`EndTime`,`GroupIDVisited`,`PresentGroupIDs`,`WorkCrosscheckedGroupIDs`,`Group`,`CoachPresentInVillage`,`PresentStudents`,`sentFlag`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GroupVisit value) {
        if (value.GroupVisitID == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.GroupVisitID);
        }
        stmt.bindLong(2, value.VillageID);
        if (value.Village == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.Village);
        }
        if (value.DateVisited == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.DateVisited);
        }
        if (value.StartTime == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.StartTime);
        }
        if (value.EndTime == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.EndTime);
        }
        if (value.GroupIDVisited == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.GroupIDVisited);
        }
        if (value.PresentGroupIDs == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.PresentGroupIDs);
        }
        if (value.WorkCrosscheckedGroupIDs == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.WorkCrosscheckedGroupIDs);
        }
        if (value.Group == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.Group);
        }
        if (value.CoachPresentInVillage == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.CoachPresentInVillage);
        }
        if (value.PresentStudents == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.PresentStudents);
        }
        stmt.bindLong(13, value.sentFlag);
      }
    };
    this.__preparedStmtOfDeleteAllGroupVisit = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM GroupVisit";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE GroupVisit SET sentFlag=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE GroupVisit SET sentFlag=? WHERE GroupVisitID=?";
        return _query;
      }
    };
  }

  @Override
  public void insertAllCRLVisit(final List<GroupVisit> GroupVisitDaoList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfGroupVisit.insert(GroupVisitDaoList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllGroupVisit() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllGroupVisit.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllGroupVisit.release(_stmt);
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
  public void updateSentFlag(final int pushStatus, final String gID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSentFlag.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, pushStatus);
    _argIndex = 2;
    if (gID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, gID);
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
  public List<GroupVisit> getAllGroupVisit() {
    final String _sql = "SELECT * FROM GroupVisit";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupVisitID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupVisitID");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfVillage = CursorUtil.getColumnIndexOrThrow(_cursor, "Village");
      final int _cursorIndexOfDateVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "DateVisited");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "StartTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "EndTime");
      final int _cursorIndexOfGroupIDVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupIDVisited");
      final int _cursorIndexOfPresentGroupIDs = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentGroupIDs");
      final int _cursorIndexOfWorkCrosscheckedGroupIDs = CursorUtil.getColumnIndexOrThrow(_cursor, "WorkCrosscheckedGroupIDs");
      final int _cursorIndexOfGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "Group");
      final int _cursorIndexOfCoachPresentInVillage = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachPresentInVillage");
      final int _cursorIndexOfPresentStudents = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentStudents");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<GroupVisit> _result = new ArrayList<GroupVisit>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GroupVisit _item;
        _item = new GroupVisit();
        if (_cursor.isNull(_cursorIndexOfGroupVisitID)) {
          _item.GroupVisitID = null;
        } else {
          _item.GroupVisitID = _cursor.getString(_cursorIndexOfGroupVisitID);
        }
        _item.VillageID = _cursor.getInt(_cursorIndexOfVillageID);
        if (_cursor.isNull(_cursorIndexOfVillage)) {
          _item.Village = null;
        } else {
          _item.Village = _cursor.getString(_cursorIndexOfVillage);
        }
        if (_cursor.isNull(_cursorIndexOfDateVisited)) {
          _item.DateVisited = null;
        } else {
          _item.DateVisited = _cursor.getString(_cursorIndexOfDateVisited);
        }
        if (_cursor.isNull(_cursorIndexOfStartTime)) {
          _item.StartTime = null;
        } else {
          _item.StartTime = _cursor.getString(_cursorIndexOfStartTime);
        }
        if (_cursor.isNull(_cursorIndexOfEndTime)) {
          _item.EndTime = null;
        } else {
          _item.EndTime = _cursor.getString(_cursorIndexOfEndTime);
        }
        if (_cursor.isNull(_cursorIndexOfGroupIDVisited)) {
          _item.GroupIDVisited = null;
        } else {
          _item.GroupIDVisited = _cursor.getString(_cursorIndexOfGroupIDVisited);
        }
        if (_cursor.isNull(_cursorIndexOfPresentGroupIDs)) {
          _item.PresentGroupIDs = null;
        } else {
          _item.PresentGroupIDs = _cursor.getString(_cursorIndexOfPresentGroupIDs);
        }
        if (_cursor.isNull(_cursorIndexOfWorkCrosscheckedGroupIDs)) {
          _item.WorkCrosscheckedGroupIDs = null;
        } else {
          _item.WorkCrosscheckedGroupIDs = _cursor.getString(_cursorIndexOfWorkCrosscheckedGroupIDs);
        }
        if (_cursor.isNull(_cursorIndexOfGroup)) {
          _item.Group = null;
        } else {
          _item.Group = _cursor.getString(_cursorIndexOfGroup);
        }
        if (_cursor.isNull(_cursorIndexOfCoachPresentInVillage)) {
          _item.CoachPresentInVillage = null;
        } else {
          _item.CoachPresentInVillage = _cursor.getString(_cursorIndexOfCoachPresentInVillage);
        }
        if (_cursor.isNull(_cursorIndexOfPresentStudents)) {
          _item.PresentStudents = null;
        } else {
          _item.PresentStudents = _cursor.getString(_cursorIndexOfPresentStudents);
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
  public List<GroupVisit> getNewGroupVisits(final int status) {
    final String _sql = "SELECT * FROM GroupVisit WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupVisitID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupVisitID");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfVillage = CursorUtil.getColumnIndexOrThrow(_cursor, "Village");
      final int _cursorIndexOfDateVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "DateVisited");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "StartTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "EndTime");
      final int _cursorIndexOfGroupIDVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupIDVisited");
      final int _cursorIndexOfPresentGroupIDs = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentGroupIDs");
      final int _cursorIndexOfWorkCrosscheckedGroupIDs = CursorUtil.getColumnIndexOrThrow(_cursor, "WorkCrosscheckedGroupIDs");
      final int _cursorIndexOfGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "Group");
      final int _cursorIndexOfCoachPresentInVillage = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachPresentInVillage");
      final int _cursorIndexOfPresentStudents = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentStudents");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<GroupVisit> _result = new ArrayList<GroupVisit>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GroupVisit _item;
        _item = new GroupVisit();
        if (_cursor.isNull(_cursorIndexOfGroupVisitID)) {
          _item.GroupVisitID = null;
        } else {
          _item.GroupVisitID = _cursor.getString(_cursorIndexOfGroupVisitID);
        }
        _item.VillageID = _cursor.getInt(_cursorIndexOfVillageID);
        if (_cursor.isNull(_cursorIndexOfVillage)) {
          _item.Village = null;
        } else {
          _item.Village = _cursor.getString(_cursorIndexOfVillage);
        }
        if (_cursor.isNull(_cursorIndexOfDateVisited)) {
          _item.DateVisited = null;
        } else {
          _item.DateVisited = _cursor.getString(_cursorIndexOfDateVisited);
        }
        if (_cursor.isNull(_cursorIndexOfStartTime)) {
          _item.StartTime = null;
        } else {
          _item.StartTime = _cursor.getString(_cursorIndexOfStartTime);
        }
        if (_cursor.isNull(_cursorIndexOfEndTime)) {
          _item.EndTime = null;
        } else {
          _item.EndTime = _cursor.getString(_cursorIndexOfEndTime);
        }
        if (_cursor.isNull(_cursorIndexOfGroupIDVisited)) {
          _item.GroupIDVisited = null;
        } else {
          _item.GroupIDVisited = _cursor.getString(_cursorIndexOfGroupIDVisited);
        }
        if (_cursor.isNull(_cursorIndexOfPresentGroupIDs)) {
          _item.PresentGroupIDs = null;
        } else {
          _item.PresentGroupIDs = _cursor.getString(_cursorIndexOfPresentGroupIDs);
        }
        if (_cursor.isNull(_cursorIndexOfWorkCrosscheckedGroupIDs)) {
          _item.WorkCrosscheckedGroupIDs = null;
        } else {
          _item.WorkCrosscheckedGroupIDs = _cursor.getString(_cursorIndexOfWorkCrosscheckedGroupIDs);
        }
        if (_cursor.isNull(_cursorIndexOfGroup)) {
          _item.Group = null;
        } else {
          _item.Group = _cursor.getString(_cursorIndexOfGroup);
        }
        if (_cursor.isNull(_cursorIndexOfCoachPresentInVillage)) {
          _item.CoachPresentInVillage = null;
        } else {
          _item.CoachPresentInVillage = _cursor.getString(_cursorIndexOfCoachPresentInVillage);
        }
        if (_cursor.isNull(_cursorIndexOfPresentStudents)) {
          _item.PresentStudents = null;
        } else {
          _item.PresentStudents = _cursor.getString(_cursorIndexOfPresentStudents);
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
