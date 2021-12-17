package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Attendance;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AttendanceDao_Impl implements AttendanceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Attendance> __insertionAdapterOfAttendance;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllAttendances;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  public AttendanceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAttendance = new EntityInsertionAdapter<Attendance>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Attendance` (`AttendanceID`,`VillageID`,`GroupID`,`StudentID`,`Date`,`Present`,`sentFlag`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Attendance value) {
        if (value.AttendanceID == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.AttendanceID);
        }
        if (value.VillageID == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.VillageID);
        }
        if (value.GroupID == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.GroupID);
        }
        if (value.StudentID == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.StudentID);
        }
        if (value.Date == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.Date);
        }
        stmt.bindLong(6, value.Present);
        stmt.bindLong(7, value.sentFlag);
      }
    };
    this.__preparedStmtOfDeleteAllAttendances = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Attendance";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Attendance SET sentFlag=? WHERE AttendanceID=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Attendance SET sentFlag=?";
        return _query;
      }
    };
  }

  @Override
  public void insertAttendance(final List<Attendance> attendancesList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAttendance.insert(attendancesList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllAttendances() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllAttendances.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllAttendances.release(_stmt);
    }
  }

  @Override
  public void updateSentFlag(final int pushStatus, final String aID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSentFlag.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, pushStatus);
    _argIndex = 2;
    if (aID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, aID);
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
  public List<Attendance> getAllAttendances() {
    final String _sql = "SELECT * FROM Attendance";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfAttendanceID = CursorUtil.getColumnIndexOrThrow(_cursor, "AttendanceID");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupID");
      final int _cursorIndexOfStudentID = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentID");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "Date");
      final int _cursorIndexOfPresent = CursorUtil.getColumnIndexOrThrow(_cursor, "Present");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Attendance> _result = new ArrayList<Attendance>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Attendance _item;
        _item = new Attendance();
        if (_cursor.isNull(_cursorIndexOfAttendanceID)) {
          _item.AttendanceID = null;
        } else {
          _item.AttendanceID = _cursor.getString(_cursorIndexOfAttendanceID);
        }
        if (_cursor.isNull(_cursorIndexOfVillageID)) {
          _item.VillageID = null;
        } else {
          _item.VillageID = _cursor.getString(_cursorIndexOfVillageID);
        }
        if (_cursor.isNull(_cursorIndexOfGroupID)) {
          _item.GroupID = null;
        } else {
          _item.GroupID = _cursor.getString(_cursorIndexOfGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfStudentID)) {
          _item.StudentID = null;
        } else {
          _item.StudentID = _cursor.getString(_cursorIndexOfStudentID);
        }
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _item.Date = null;
        } else {
          _item.Date = _cursor.getString(_cursorIndexOfDate);
        }
        _item.Present = _cursor.getInt(_cursorIndexOfPresent);
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
  public List<Attendance> getNewAttendances(final int status) {
    final String _sql = "SELECT * FROM Attendance WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfAttendanceID = CursorUtil.getColumnIndexOrThrow(_cursor, "AttendanceID");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageID");
      final int _cursorIndexOfGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupID");
      final int _cursorIndexOfStudentID = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentID");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "Date");
      final int _cursorIndexOfPresent = CursorUtil.getColumnIndexOrThrow(_cursor, "Present");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Attendance> _result = new ArrayList<Attendance>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Attendance _item;
        _item = new Attendance();
        if (_cursor.isNull(_cursorIndexOfAttendanceID)) {
          _item.AttendanceID = null;
        } else {
          _item.AttendanceID = _cursor.getString(_cursorIndexOfAttendanceID);
        }
        if (_cursor.isNull(_cursorIndexOfVillageID)) {
          _item.VillageID = null;
        } else {
          _item.VillageID = _cursor.getString(_cursorIndexOfVillageID);
        }
        if (_cursor.isNull(_cursorIndexOfGroupID)) {
          _item.GroupID = null;
        } else {
          _item.GroupID = _cursor.getString(_cursorIndexOfGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfStudentID)) {
          _item.StudentID = null;
        } else {
          _item.StudentID = _cursor.getString(_cursorIndexOfStudentID);
        }
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _item.Date = null;
        } else {
          _item.Date = _cursor.getString(_cursorIndexOfDate);
        }
        _item.Present = _cursor.getInt(_cursorIndexOfPresent);
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
