package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Modal_Log;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LogDao_Impl implements LogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Modal_Log> __insertionAdapterOfModal_Log;

  private final SharedSQLiteStatement __preparedStmtOfDeleteLogs;

  private final SharedSQLiteStatement __preparedStmtOfSetSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  public LogDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfModal_Log = new EntityInsertionAdapter<Modal_Log>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Logs` (`logId`,`currentDateTime`,`exceptionMessage`,`exceptionStackTrace`,`methodName`,`errorType`,`groupId`,`deviceId`,`LogDetail`,`sentFlag`,`sessionId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Modal_Log value) {
        stmt.bindLong(1, value.logId);
        if (value.currentDateTime == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.currentDateTime);
        }
        if (value.exceptionMessage == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.exceptionMessage);
        }
        if (value.exceptionStackTrace == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.exceptionStackTrace);
        }
        if (value.methodName == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.methodName);
        }
        if (value.errorType == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.errorType);
        }
        if (value.groupId == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.groupId);
        }
        if (value.deviceId == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.deviceId);
        }
        if (value.LogDetail == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.LogDetail);
        }
        stmt.bindLong(10, value.sentFlag);
        if (value.sessionId == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.sessionId);
        }
      }
    };
    this.__preparedStmtOfDeleteLogs = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Logs";
        return _query;
      }
    };
    this.__preparedStmtOfSetSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update Logs set sentFlag=1 where sentFlag=0";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Logs SET sentFlag=?";
        return _query;
      }
    };
  }

  @Override
  public void insertLog(final Modal_Log log) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfModal_Log.insert(log);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteLogs() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteLogs.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteLogs.release(_stmt);
    }
  }

  @Override
  public void setSentFlag() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetSentFlag.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetSentFlag.release(_stmt);
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
  public List<Modal_Log> getAllLogs() {
    final String _sql = "select * from Logs";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "logId");
      final int _cursorIndexOfCurrentDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDateTime");
      final int _cursorIndexOfExceptionMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "exceptionMessage");
      final int _cursorIndexOfExceptionStackTrace = CursorUtil.getColumnIndexOrThrow(_cursor, "exceptionStackTrace");
      final int _cursorIndexOfMethodName = CursorUtil.getColumnIndexOrThrow(_cursor, "methodName");
      final int _cursorIndexOfErrorType = CursorUtil.getColumnIndexOrThrow(_cursor, "errorType");
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "groupId");
      final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
      final int _cursorIndexOfLogDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "LogDetail");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
      final List<Modal_Log> _result = new ArrayList<Modal_Log>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Modal_Log _item;
        _item = new Modal_Log();
        _item.logId = _cursor.getInt(_cursorIndexOfLogId);
        if (_cursor.isNull(_cursorIndexOfCurrentDateTime)) {
          _item.currentDateTime = null;
        } else {
          _item.currentDateTime = _cursor.getString(_cursorIndexOfCurrentDateTime);
        }
        if (_cursor.isNull(_cursorIndexOfExceptionMessage)) {
          _item.exceptionMessage = null;
        } else {
          _item.exceptionMessage = _cursor.getString(_cursorIndexOfExceptionMessage);
        }
        if (_cursor.isNull(_cursorIndexOfExceptionStackTrace)) {
          _item.exceptionStackTrace = null;
        } else {
          _item.exceptionStackTrace = _cursor.getString(_cursorIndexOfExceptionStackTrace);
        }
        if (_cursor.isNull(_cursorIndexOfMethodName)) {
          _item.methodName = null;
        } else {
          _item.methodName = _cursor.getString(_cursorIndexOfMethodName);
        }
        if (_cursor.isNull(_cursorIndexOfErrorType)) {
          _item.errorType = null;
        } else {
          _item.errorType = _cursor.getString(_cursorIndexOfErrorType);
        }
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _item.groupId = null;
        } else {
          _item.groupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfDeviceId)) {
          _item.deviceId = null;
        } else {
          _item.deviceId = _cursor.getString(_cursorIndexOfDeviceId);
        }
        if (_cursor.isNull(_cursorIndexOfLogDetail)) {
          _item.LogDetail = null;
        } else {
          _item.LogDetail = _cursor.getString(_cursorIndexOfLogDetail);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        if (_cursor.isNull(_cursorIndexOfSessionId)) {
          _item.sessionId = null;
        } else {
          _item.sessionId = _cursor.getString(_cursorIndexOfSessionId);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Modal_Log> getPushAllLogs() {
    final String _sql = "select * from Logs where sentFlag=0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "logId");
      final int _cursorIndexOfCurrentDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDateTime");
      final int _cursorIndexOfExceptionMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "exceptionMessage");
      final int _cursorIndexOfExceptionStackTrace = CursorUtil.getColumnIndexOrThrow(_cursor, "exceptionStackTrace");
      final int _cursorIndexOfMethodName = CursorUtil.getColumnIndexOrThrow(_cursor, "methodName");
      final int _cursorIndexOfErrorType = CursorUtil.getColumnIndexOrThrow(_cursor, "errorType");
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "groupId");
      final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
      final int _cursorIndexOfLogDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "LogDetail");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
      final List<Modal_Log> _result = new ArrayList<Modal_Log>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Modal_Log _item;
        _item = new Modal_Log();
        _item.logId = _cursor.getInt(_cursorIndexOfLogId);
        if (_cursor.isNull(_cursorIndexOfCurrentDateTime)) {
          _item.currentDateTime = null;
        } else {
          _item.currentDateTime = _cursor.getString(_cursorIndexOfCurrentDateTime);
        }
        if (_cursor.isNull(_cursorIndexOfExceptionMessage)) {
          _item.exceptionMessage = null;
        } else {
          _item.exceptionMessage = _cursor.getString(_cursorIndexOfExceptionMessage);
        }
        if (_cursor.isNull(_cursorIndexOfExceptionStackTrace)) {
          _item.exceptionStackTrace = null;
        } else {
          _item.exceptionStackTrace = _cursor.getString(_cursorIndexOfExceptionStackTrace);
        }
        if (_cursor.isNull(_cursorIndexOfMethodName)) {
          _item.methodName = null;
        } else {
          _item.methodName = _cursor.getString(_cursorIndexOfMethodName);
        }
        if (_cursor.isNull(_cursorIndexOfErrorType)) {
          _item.errorType = null;
        } else {
          _item.errorType = _cursor.getString(_cursorIndexOfErrorType);
        }
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _item.groupId = null;
        } else {
          _item.groupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfDeviceId)) {
          _item.deviceId = null;
        } else {
          _item.deviceId = _cursor.getString(_cursorIndexOfDeviceId);
        }
        if (_cursor.isNull(_cursorIndexOfLogDetail)) {
          _item.LogDetail = null;
        } else {
          _item.LogDetail = _cursor.getString(_cursorIndexOfLogDetail);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        if (_cursor.isNull(_cursorIndexOfSessionId)) {
          _item.sessionId = null;
        } else {
          _item.sessionId = _cursor.getString(_cursorIndexOfSessionId);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Modal_Log> getAllLogs(final String s_id) {
    final String _sql = "select * from Logs where sentFlag=0 AND sessionId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (s_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, s_id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "logId");
      final int _cursorIndexOfCurrentDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDateTime");
      final int _cursorIndexOfExceptionMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "exceptionMessage");
      final int _cursorIndexOfExceptionStackTrace = CursorUtil.getColumnIndexOrThrow(_cursor, "exceptionStackTrace");
      final int _cursorIndexOfMethodName = CursorUtil.getColumnIndexOrThrow(_cursor, "methodName");
      final int _cursorIndexOfErrorType = CursorUtil.getColumnIndexOrThrow(_cursor, "errorType");
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "groupId");
      final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
      final int _cursorIndexOfLogDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "LogDetail");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
      final List<Modal_Log> _result = new ArrayList<Modal_Log>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Modal_Log _item;
        _item = new Modal_Log();
        _item.logId = _cursor.getInt(_cursorIndexOfLogId);
        if (_cursor.isNull(_cursorIndexOfCurrentDateTime)) {
          _item.currentDateTime = null;
        } else {
          _item.currentDateTime = _cursor.getString(_cursorIndexOfCurrentDateTime);
        }
        if (_cursor.isNull(_cursorIndexOfExceptionMessage)) {
          _item.exceptionMessage = null;
        } else {
          _item.exceptionMessage = _cursor.getString(_cursorIndexOfExceptionMessage);
        }
        if (_cursor.isNull(_cursorIndexOfExceptionStackTrace)) {
          _item.exceptionStackTrace = null;
        } else {
          _item.exceptionStackTrace = _cursor.getString(_cursorIndexOfExceptionStackTrace);
        }
        if (_cursor.isNull(_cursorIndexOfMethodName)) {
          _item.methodName = null;
        } else {
          _item.methodName = _cursor.getString(_cursorIndexOfMethodName);
        }
        if (_cursor.isNull(_cursorIndexOfErrorType)) {
          _item.errorType = null;
        } else {
          _item.errorType = _cursor.getString(_cursorIndexOfErrorType);
        }
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _item.groupId = null;
        } else {
          _item.groupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfDeviceId)) {
          _item.deviceId = null;
        } else {
          _item.deviceId = _cursor.getString(_cursorIndexOfDeviceId);
        }
        if (_cursor.isNull(_cursorIndexOfLogDetail)) {
          _item.LogDetail = null;
        } else {
          _item.LogDetail = _cursor.getString(_cursorIndexOfLogDetail);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        if (_cursor.isNull(_cursorIndexOfSessionId)) {
          _item.sessionId = null;
        } else {
          _item.sessionId = _cursor.getString(_cursorIndexOfSessionId);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Modal_Log> getAllLogs(final int status) {
    final String _sql = "SELECT * FROM Logs WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfLogId = CursorUtil.getColumnIndexOrThrow(_cursor, "logId");
      final int _cursorIndexOfCurrentDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "currentDateTime");
      final int _cursorIndexOfExceptionMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "exceptionMessage");
      final int _cursorIndexOfExceptionStackTrace = CursorUtil.getColumnIndexOrThrow(_cursor, "exceptionStackTrace");
      final int _cursorIndexOfMethodName = CursorUtil.getColumnIndexOrThrow(_cursor, "methodName");
      final int _cursorIndexOfErrorType = CursorUtil.getColumnIndexOrThrow(_cursor, "errorType");
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "groupId");
      final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceId");
      final int _cursorIndexOfLogDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "LogDetail");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
      final List<Modal_Log> _result = new ArrayList<Modal_Log>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Modal_Log _item;
        _item = new Modal_Log();
        _item.logId = _cursor.getInt(_cursorIndexOfLogId);
        if (_cursor.isNull(_cursorIndexOfCurrentDateTime)) {
          _item.currentDateTime = null;
        } else {
          _item.currentDateTime = _cursor.getString(_cursorIndexOfCurrentDateTime);
        }
        if (_cursor.isNull(_cursorIndexOfExceptionMessage)) {
          _item.exceptionMessage = null;
        } else {
          _item.exceptionMessage = _cursor.getString(_cursorIndexOfExceptionMessage);
        }
        if (_cursor.isNull(_cursorIndexOfExceptionStackTrace)) {
          _item.exceptionStackTrace = null;
        } else {
          _item.exceptionStackTrace = _cursor.getString(_cursorIndexOfExceptionStackTrace);
        }
        if (_cursor.isNull(_cursorIndexOfMethodName)) {
          _item.methodName = null;
        } else {
          _item.methodName = _cursor.getString(_cursorIndexOfMethodName);
        }
        if (_cursor.isNull(_cursorIndexOfErrorType)) {
          _item.errorType = null;
        } else {
          _item.errorType = _cursor.getString(_cursorIndexOfErrorType);
        }
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _item.groupId = null;
        } else {
          _item.groupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfDeviceId)) {
          _item.deviceId = null;
        } else {
          _item.deviceId = _cursor.getString(_cursorIndexOfDeviceId);
        }
        if (_cursor.isNull(_cursorIndexOfLogDetail)) {
          _item.LogDetail = null;
        } else {
          _item.LogDetail = _cursor.getString(_cursorIndexOfLogDetail);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        if (_cursor.isNull(_cursorIndexOfSessionId)) {
          _item.sessionId = null;
        } else {
          _item.sessionId = _cursor.getString(_cursorIndexOfSessionId);
        }
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
