package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.ECEAsmt;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ECEAsmtDao_Impl implements ECEAsmtDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ECEAsmt> __insertionAdapterOfECEAsmt;

  private final SharedSQLiteStatement __preparedStmtOfUpdateECEAsmtData;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllECEAsmt;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSentFlag;

  public ECEAsmtDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfECEAsmt = new EntityInsertionAdapter<ECEAsmt>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ECEAsmt` (`ECEAsmtID`,`StudentId`,`AsmtType`,`Date`,`StartTime`,`EndTime`,`ActMatchingCards`,`ActSequencingCards`,`ActNumberReco`,`ActWordReco`,`WS11a`,`WS11b`,`WS12a`,`WS12b`,`OQ11`,`OQ12`,`OQ13`,`OQ14`,`sentFlag`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ECEAsmt value) {
        stmt.bindLong(1, value.ECEAsmtID);
        if (value.StudentId == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.StudentId);
        }
        stmt.bindLong(3, value.AsmtType);
        if (value.Date == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.Date);
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
        stmt.bindLong(7, value.ActMatchingCards);
        stmt.bindLong(8, value.ActSequencingCards);
        stmt.bindLong(9, value.ActNumberReco);
        stmt.bindLong(10, value.ActWordReco);
        stmt.bindLong(11, value.WS11a);
        stmt.bindLong(12, value.WS11b);
        stmt.bindLong(13, value.WS12a);
        stmt.bindLong(14, value.WS12b);
        stmt.bindLong(15, value.OQ11);
        stmt.bindLong(16, value.OQ12);
        stmt.bindLong(17, value.OQ13);
        stmt.bindLong(18, value.OQ14);
        stmt.bindLong(19, value.sentFlag);
      }
    };
    this.__preparedStmtOfUpdateECEAsmtData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update ECEAsmt set Date=?,StartTime=?,EndTime=?, ActMatchingCards=?, ActSequencingCards=?, ActNumberReco=?, ActWordReco=?, WS11a=?, WS11b=?, WS12a=?, WS12b=?, OQ11=?, OQ12=?, OQ13=?, OQ14=?, sentFlag=? WHERE StudentId=? AND AsmtType =?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllECEAsmt = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ECEAsmt";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE ECEAsmt SET sentFlag=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE ECEAsmt SET sentFlag=? WHERE StudentId =? AND AsmtType=?";
        return _query;
      }
    };
  }

  @Override
  public void insertAllECEAsmtList(final List<ECEAsmt> studentsList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfECEAsmt.insert(studentsList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertECEAsmt(final ECEAsmt ECEAsmtData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfECEAsmt.insert(ECEAsmtData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void UpdateECEAsmtData(final String date, final String startTime, final String endTime,
      final int mc, final int sc, final int nr, final int wr, final int ws11a, final int ws11b,
      final int ws12a, final int ws12b, final int oq11, final int oq12, final int oq13,
      final int oq14, final int sentFlag, final String studentID, final int asmtType) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateECEAsmtData.acquire();
    int _argIndex = 1;
    if (date == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, date);
    }
    _argIndex = 2;
    if (startTime == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, startTime);
    }
    _argIndex = 3;
    if (endTime == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, endTime);
    }
    _argIndex = 4;
    _stmt.bindLong(_argIndex, mc);
    _argIndex = 5;
    _stmt.bindLong(_argIndex, sc);
    _argIndex = 6;
    _stmt.bindLong(_argIndex, nr);
    _argIndex = 7;
    _stmt.bindLong(_argIndex, wr);
    _argIndex = 8;
    _stmt.bindLong(_argIndex, ws11a);
    _argIndex = 9;
    _stmt.bindLong(_argIndex, ws11b);
    _argIndex = 10;
    _stmt.bindLong(_argIndex, ws12a);
    _argIndex = 11;
    _stmt.bindLong(_argIndex, ws12b);
    _argIndex = 12;
    _stmt.bindLong(_argIndex, oq11);
    _argIndex = 13;
    _stmt.bindLong(_argIndex, oq12);
    _argIndex = 14;
    _stmt.bindLong(_argIndex, oq13);
    _argIndex = 15;
    _stmt.bindLong(_argIndex, oq14);
    _argIndex = 16;
    _stmt.bindLong(_argIndex, sentFlag);
    _argIndex = 17;
    if (studentID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, studentID);
    }
    _argIndex = 18;
    _stmt.bindLong(_argIndex, asmtType);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateECEAsmtData.release(_stmt);
    }
  }

  @Override
  public void deleteAllECEAsmt() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllECEAsmt.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllECEAsmt.release(_stmt);
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
  public void updateSentFlag(final int pushStatus, final String sID, final int AsmtType) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSentFlag.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, pushStatus);
    _argIndex = 2;
    if (sID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, sID);
    }
    _argIndex = 3;
    _stmt.bindLong(_argIndex, AsmtType);
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
  public boolean CheckDataExists(final String StudentId, final int AsmtType) {
    final String _sql = "SELECT * FROM ECEAsmt WHERE StudentId=? AND AsmtType=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (StudentId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, StudentId);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, AsmtType);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if(_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<ECEAsmt> GetAsmtDataByStudentID(final String StudentID, final int AsmtType) {
    final String _sql = "select * from ECEAsmt where StudentId =? AND AsmtType =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (StudentID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, StudentID);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, AsmtType);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfECEAsmtID = CursorUtil.getColumnIndexOrThrow(_cursor, "ECEAsmtID");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final int _cursorIndexOfAsmtType = CursorUtil.getColumnIndexOrThrow(_cursor, "AsmtType");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "Date");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "StartTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "EndTime");
      final int _cursorIndexOfActMatchingCards = CursorUtil.getColumnIndexOrThrow(_cursor, "ActMatchingCards");
      final int _cursorIndexOfActSequencingCards = CursorUtil.getColumnIndexOrThrow(_cursor, "ActSequencingCards");
      final int _cursorIndexOfActNumberReco = CursorUtil.getColumnIndexOrThrow(_cursor, "ActNumberReco");
      final int _cursorIndexOfActWordReco = CursorUtil.getColumnIndexOrThrow(_cursor, "ActWordReco");
      final int _cursorIndexOfWS11a = CursorUtil.getColumnIndexOrThrow(_cursor, "WS11a");
      final int _cursorIndexOfWS11b = CursorUtil.getColumnIndexOrThrow(_cursor, "WS11b");
      final int _cursorIndexOfWS12a = CursorUtil.getColumnIndexOrThrow(_cursor, "WS12a");
      final int _cursorIndexOfWS12b = CursorUtil.getColumnIndexOrThrow(_cursor, "WS12b");
      final int _cursorIndexOfOQ11 = CursorUtil.getColumnIndexOrThrow(_cursor, "OQ11");
      final int _cursorIndexOfOQ12 = CursorUtil.getColumnIndexOrThrow(_cursor, "OQ12");
      final int _cursorIndexOfOQ13 = CursorUtil.getColumnIndexOrThrow(_cursor, "OQ13");
      final int _cursorIndexOfOQ14 = CursorUtil.getColumnIndexOrThrow(_cursor, "OQ14");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<ECEAsmt> _result = new ArrayList<ECEAsmt>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ECEAsmt _item;
        _item = new ECEAsmt();
        _item.ECEAsmtID = _cursor.getInt(_cursorIndexOfECEAsmtID);
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _item.StudentId = null;
        } else {
          _item.StudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        _item.AsmtType = _cursor.getInt(_cursorIndexOfAsmtType);
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _item.Date = null;
        } else {
          _item.Date = _cursor.getString(_cursorIndexOfDate);
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
        _item.ActMatchingCards = _cursor.getInt(_cursorIndexOfActMatchingCards);
        _item.ActSequencingCards = _cursor.getInt(_cursorIndexOfActSequencingCards);
        _item.ActNumberReco = _cursor.getInt(_cursorIndexOfActNumberReco);
        _item.ActWordReco = _cursor.getInt(_cursorIndexOfActWordReco);
        _item.WS11a = _cursor.getInt(_cursorIndexOfWS11a);
        _item.WS11b = _cursor.getInt(_cursorIndexOfWS11b);
        _item.WS12a = _cursor.getInt(_cursorIndexOfWS12a);
        _item.WS12b = _cursor.getInt(_cursorIndexOfWS12b);
        _item.OQ11 = _cursor.getInt(_cursorIndexOfOQ11);
        _item.OQ12 = _cursor.getInt(_cursorIndexOfOQ12);
        _item.OQ13 = _cursor.getInt(_cursorIndexOfOQ13);
        _item.OQ14 = _cursor.getInt(_cursorIndexOfOQ14);
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
  public List<ECEAsmt> getNewECEAsmt(final int status) {
    final String _sql = "SELECT * FROM ECEAsmt WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfECEAsmtID = CursorUtil.getColumnIndexOrThrow(_cursor, "ECEAsmtID");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final int _cursorIndexOfAsmtType = CursorUtil.getColumnIndexOrThrow(_cursor, "AsmtType");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "Date");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "StartTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "EndTime");
      final int _cursorIndexOfActMatchingCards = CursorUtil.getColumnIndexOrThrow(_cursor, "ActMatchingCards");
      final int _cursorIndexOfActSequencingCards = CursorUtil.getColumnIndexOrThrow(_cursor, "ActSequencingCards");
      final int _cursorIndexOfActNumberReco = CursorUtil.getColumnIndexOrThrow(_cursor, "ActNumberReco");
      final int _cursorIndexOfActWordReco = CursorUtil.getColumnIndexOrThrow(_cursor, "ActWordReco");
      final int _cursorIndexOfWS11a = CursorUtil.getColumnIndexOrThrow(_cursor, "WS11a");
      final int _cursorIndexOfWS11b = CursorUtil.getColumnIndexOrThrow(_cursor, "WS11b");
      final int _cursorIndexOfWS12a = CursorUtil.getColumnIndexOrThrow(_cursor, "WS12a");
      final int _cursorIndexOfWS12b = CursorUtil.getColumnIndexOrThrow(_cursor, "WS12b");
      final int _cursorIndexOfOQ11 = CursorUtil.getColumnIndexOrThrow(_cursor, "OQ11");
      final int _cursorIndexOfOQ12 = CursorUtil.getColumnIndexOrThrow(_cursor, "OQ12");
      final int _cursorIndexOfOQ13 = CursorUtil.getColumnIndexOrThrow(_cursor, "OQ13");
      final int _cursorIndexOfOQ14 = CursorUtil.getColumnIndexOrThrow(_cursor, "OQ14");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<ECEAsmt> _result = new ArrayList<ECEAsmt>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ECEAsmt _item;
        _item = new ECEAsmt();
        _item.ECEAsmtID = _cursor.getInt(_cursorIndexOfECEAsmtID);
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _item.StudentId = null;
        } else {
          _item.StudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        _item.AsmtType = _cursor.getInt(_cursorIndexOfAsmtType);
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _item.Date = null;
        } else {
          _item.Date = _cursor.getString(_cursorIndexOfDate);
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
        _item.ActMatchingCards = _cursor.getInt(_cursorIndexOfActMatchingCards);
        _item.ActSequencingCards = _cursor.getInt(_cursorIndexOfActSequencingCards);
        _item.ActNumberReco = _cursor.getInt(_cursorIndexOfActNumberReco);
        _item.ActWordReco = _cursor.getInt(_cursorIndexOfActWordReco);
        _item.WS11a = _cursor.getInt(_cursorIndexOfWS11a);
        _item.WS11b = _cursor.getInt(_cursorIndexOfWS11b);
        _item.WS12a = _cursor.getInt(_cursorIndexOfWS12a);
        _item.WS12b = _cursor.getInt(_cursorIndexOfWS12b);
        _item.OQ11 = _cursor.getInt(_cursorIndexOfOQ11);
        _item.OQ12 = _cursor.getInt(_cursorIndexOfOQ12);
        _item.OQ13 = _cursor.getInt(_cursorIndexOfOQ13);
        _item.OQ14 = _cursor.getInt(_cursorIndexOfOQ14);
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
