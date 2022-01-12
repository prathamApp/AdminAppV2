package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.GroupSession;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class GroupSessionDao_Impl implements GroupSessionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GroupSession> __insertionAdapterOfGroupSession;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllGroupSession;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSentFlag;

  public GroupSessionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGroupSession = new EntityInsertionAdapter<GroupSession>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `GroupSession` (`GroupSessionID`,`VillageID`,`Village`,`GroupIDVisited`,`Group`,`DateVisited`,`StartTime`,`EndTime`,`CoachPresentInVillage`,`WorkCrosscheckedGroupIDs`,`PresentStudents`,`sentFlag`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GroupSession value) {
        if (value.GroupSessionID == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.GroupSessionID);
        }
        stmt.bindLong(2, value.VillageID);
        if (value.Village == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.Village);
        }
        if (value.GroupIDVisited == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.GroupIDVisited);
        }
        if (value.Group == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.Group);
        }
        if (value.DateVisited == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.DateVisited);
        }
        if (value.StartTime == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.StartTime);
        }
        if (value.EndTime == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.EndTime);
        }
        if (value.CoachPresentInVillage == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.CoachPresentInVillage);
        }
        if (value.WorkCrosscheckedGroupIDs == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.WorkCrosscheckedGroupIDs);
        }
        if (value.PresentStudents == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.PresentStudents);
        }
        stmt.bindLong(12, value.sentFlag);
      }
    };
    this.__preparedStmtOfDeleteAllGroupSession = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM GroupSession";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE GroupSession SET sentFlag=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE GroupSession SET sentFlag=? WHERE GroupSessionID=?";
        return _query;
      }
    };
  }

  @Override
  public void insertAllGroupSession(final List<GroupSession> GroupSessionDaoList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfGroupSession.insert(GroupSessionDaoList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllGroupSession() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllGroupSession.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllGroupSession.release(_stmt);
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
  public List<GroupSession> getAllGroupSession() {
    final String _sql = "SELECT * FROM GroupSession";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupSessionID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupSessionID");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfVillage = CursorUtil.getColumnIndexOrThrow(_cursor, "Village");
      final int _cursorIndexOfGroupIDVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupIDVisited");
      final int _cursorIndexOfGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "Group");
      final int _cursorIndexOfDateVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "DateVisited");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "StartTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "EndTime");
      final int _cursorIndexOfCoachPresentInVillage = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachPresentInVillage");
      final int _cursorIndexOfWorkCrosscheckedGroupIDs = CursorUtil.getColumnIndexOrThrow(_cursor, "WorkCrosscheckedGroupIDs");
      final int _cursorIndexOfPresentStudents = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentStudents");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<GroupSession> _result = new ArrayList<GroupSession>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GroupSession _item;
        _item = new GroupSession();
        if (_cursor.isNull(_cursorIndexOfGroupSessionID)) {
          _item.GroupSessionID = null;
        } else {
          _item.GroupSessionID = _cursor.getString(_cursorIndexOfGroupSessionID);
        }
        _item.VillageID = _cursor.getInt(_cursorIndexOfVillageID);
        if (_cursor.isNull(_cursorIndexOfVillage)) {
          _item.Village = null;
        } else {
          _item.Village = _cursor.getString(_cursorIndexOfVillage);
        }
        if (_cursor.isNull(_cursorIndexOfGroupIDVisited)) {
          _item.GroupIDVisited = null;
        } else {
          _item.GroupIDVisited = _cursor.getString(_cursorIndexOfGroupIDVisited);
        }
        if (_cursor.isNull(_cursorIndexOfGroup)) {
          _item.Group = null;
        } else {
          _item.Group = _cursor.getString(_cursorIndexOfGroup);
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
        if (_cursor.isNull(_cursorIndexOfCoachPresentInVillage)) {
          _item.CoachPresentInVillage = null;
        } else {
          _item.CoachPresentInVillage = _cursor.getString(_cursorIndexOfCoachPresentInVillage);
        }
        if (_cursor.isNull(_cursorIndexOfWorkCrosscheckedGroupIDs)) {
          _item.WorkCrosscheckedGroupIDs = null;
        } else {
          _item.WorkCrosscheckedGroupIDs = _cursor.getString(_cursorIndexOfWorkCrosscheckedGroupIDs);
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
  public List<GroupSession> getNewGroupSessions(final int status) {
    final String _sql = "SELECT * FROM GroupSession WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupSessionID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupSessionID");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfVillage = CursorUtil.getColumnIndexOrThrow(_cursor, "Village");
      final int _cursorIndexOfGroupIDVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupIDVisited");
      final int _cursorIndexOfGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "Group");
      final int _cursorIndexOfDateVisited = CursorUtil.getColumnIndexOrThrow(_cursor, "DateVisited");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "StartTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "EndTime");
      final int _cursorIndexOfCoachPresentInVillage = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachPresentInVillage");
      final int _cursorIndexOfWorkCrosscheckedGroupIDs = CursorUtil.getColumnIndexOrThrow(_cursor, "WorkCrosscheckedGroupIDs");
      final int _cursorIndexOfPresentStudents = CursorUtil.getColumnIndexOrThrow(_cursor, "PresentStudents");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<GroupSession> _result = new ArrayList<GroupSession>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GroupSession _item;
        _item = new GroupSession();
        if (_cursor.isNull(_cursorIndexOfGroupSessionID)) {
          _item.GroupSessionID = null;
        } else {
          _item.GroupSessionID = _cursor.getString(_cursorIndexOfGroupSessionID);
        }
        _item.VillageID = _cursor.getInt(_cursorIndexOfVillageID);
        if (_cursor.isNull(_cursorIndexOfVillage)) {
          _item.Village = null;
        } else {
          _item.Village = _cursor.getString(_cursorIndexOfVillage);
        }
        if (_cursor.isNull(_cursorIndexOfGroupIDVisited)) {
          _item.GroupIDVisited = null;
        } else {
          _item.GroupIDVisited = _cursor.getString(_cursorIndexOfGroupIDVisited);
        }
        if (_cursor.isNull(_cursorIndexOfGroup)) {
          _item.Group = null;
        } else {
          _item.Group = _cursor.getString(_cursorIndexOfGroup);
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
        if (_cursor.isNull(_cursorIndexOfCoachPresentInVillage)) {
          _item.CoachPresentInVillage = null;
        } else {
          _item.CoachPresentInVillage = _cursor.getString(_cursorIndexOfCoachPresentInVillage);
        }
        if (_cursor.isNull(_cursorIndexOfWorkCrosscheckedGroupIDs)) {
          _item.WorkCrosscheckedGroupIDs = null;
        } else {
          _item.WorkCrosscheckedGroupIDs = _cursor.getString(_cursorIndexOfWorkCrosscheckedGroupIDs);
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
