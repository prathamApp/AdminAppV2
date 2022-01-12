package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.TabTrack;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TabTrackDao_Impl implements TabTrackDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TabTrack> __insertionAdapterOfTabTrack;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllTabTracks;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTabTracks;

  public TabTrackDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTabTrack = new EntityInsertionAdapter<TabTrack>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TabTrack` (`QR_ID`,`CRL_ID`,`CRL_Name`,`State`,`Pratham_ID`,`date`,`Serial_NO`,`oldFlag`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TabTrack value) {
        if (value.getQR_ID() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getQR_ID());
        }
        if (value.getCRL_ID() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCRL_ID());
        }
        if (value.getCRL_Name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCRL_Name());
        }
        if (value.getState() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getState());
        }
        if (value.getPratham_ID() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPratham_ID());
        }
        if (value.getDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate());
        }
        if (value.getSerial_NO() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getSerial_NO());
        }
        final int _tmp;
        _tmp = value.getOldFlag() ? 1 : 0;
        stmt.bindLong(8, _tmp);
      }
    };
    this.__preparedStmtOfDeleteAllTabTracks = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tabtrack";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteTabTracks = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM tabtrack WHERE QR_ID=?";
        return _query;
      }
    };
  }

  @Override
  public long insertTabTrack(final TabTrack tabTrack) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfTabTrack.insertAndReturnId(tabTrack);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllTabTrack(final List<TabTrack> tabTrack) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTabTrack.insert(tabTrack);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllTabTracks() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllTabTracks.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllTabTracks.release(_stmt);
    }
  }

  @Override
  public void deleteTabTracks(final String qrId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTabTracks.acquire();
    int _argIndex = 1;
    if (qrId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, qrId);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteTabTracks.release(_stmt);
    }
  }

  @Override
  public List<TabTrack> checkExistance(final String id) {
    final String _sql = "SELECT * FROM TabTrack WHERE QR_ID=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfQRID = CursorUtil.getColumnIndexOrThrow(_cursor, "QR_ID");
      final int _cursorIndexOfCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "CRL_ID");
      final int _cursorIndexOfCRLName = CursorUtil.getColumnIndexOrThrow(_cursor, "CRL_Name");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "State");
      final int _cursorIndexOfPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "Pratham_ID");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfSerialNO = CursorUtil.getColumnIndexOrThrow(_cursor, "Serial_NO");
      final int _cursorIndexOfOldFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "oldFlag");
      final List<TabTrack> _result = new ArrayList<TabTrack>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TabTrack _item;
        _item = new TabTrack();
        final String _tmpQR_ID;
        if (_cursor.isNull(_cursorIndexOfQRID)) {
          _tmpQR_ID = null;
        } else {
          _tmpQR_ID = _cursor.getString(_cursorIndexOfQRID);
        }
        _item.setQR_ID(_tmpQR_ID);
        final String _tmpCRL_ID;
        if (_cursor.isNull(_cursorIndexOfCRLID)) {
          _tmpCRL_ID = null;
        } else {
          _tmpCRL_ID = _cursor.getString(_cursorIndexOfCRLID);
        }
        _item.setCRL_ID(_tmpCRL_ID);
        final String _tmpCRL_Name;
        if (_cursor.isNull(_cursorIndexOfCRLName)) {
          _tmpCRL_Name = null;
        } else {
          _tmpCRL_Name = _cursor.getString(_cursorIndexOfCRLName);
        }
        _item.setCRL_Name(_tmpCRL_Name);
        final String _tmpState;
        if (_cursor.isNull(_cursorIndexOfState)) {
          _tmpState = null;
        } else {
          _tmpState = _cursor.getString(_cursorIndexOfState);
        }
        _item.setState(_tmpState);
        final String _tmpPratham_ID;
        if (_cursor.isNull(_cursorIndexOfPrathamID)) {
          _tmpPratham_ID = null;
        } else {
          _tmpPratham_ID = _cursor.getString(_cursorIndexOfPrathamID);
        }
        _item.setPratham_ID(_tmpPratham_ID);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        _item.setDate(_tmpDate);
        final String _tmpSerial_NO;
        if (_cursor.isNull(_cursorIndexOfSerialNO)) {
          _tmpSerial_NO = null;
        } else {
          _tmpSerial_NO = _cursor.getString(_cursorIndexOfSerialNO);
        }
        _item.setSerial_NO(_tmpSerial_NO);
        final boolean _tmpOldFlag;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfOldFlag);
        _tmpOldFlag = _tmp != 0;
        _item.setOldFlag(_tmpOldFlag);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TabTrack> getAllTabTrack() {
    final String _sql = "SELECT * FROM TabTrack";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfQRID = CursorUtil.getColumnIndexOrThrow(_cursor, "QR_ID");
      final int _cursorIndexOfCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "CRL_ID");
      final int _cursorIndexOfCRLName = CursorUtil.getColumnIndexOrThrow(_cursor, "CRL_Name");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "State");
      final int _cursorIndexOfPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "Pratham_ID");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfSerialNO = CursorUtil.getColumnIndexOrThrow(_cursor, "Serial_NO");
      final int _cursorIndexOfOldFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "oldFlag");
      final List<TabTrack> _result = new ArrayList<TabTrack>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TabTrack _item;
        _item = new TabTrack();
        final String _tmpQR_ID;
        if (_cursor.isNull(_cursorIndexOfQRID)) {
          _tmpQR_ID = null;
        } else {
          _tmpQR_ID = _cursor.getString(_cursorIndexOfQRID);
        }
        _item.setQR_ID(_tmpQR_ID);
        final String _tmpCRL_ID;
        if (_cursor.isNull(_cursorIndexOfCRLID)) {
          _tmpCRL_ID = null;
        } else {
          _tmpCRL_ID = _cursor.getString(_cursorIndexOfCRLID);
        }
        _item.setCRL_ID(_tmpCRL_ID);
        final String _tmpCRL_Name;
        if (_cursor.isNull(_cursorIndexOfCRLName)) {
          _tmpCRL_Name = null;
        } else {
          _tmpCRL_Name = _cursor.getString(_cursorIndexOfCRLName);
        }
        _item.setCRL_Name(_tmpCRL_Name);
        final String _tmpState;
        if (_cursor.isNull(_cursorIndexOfState)) {
          _tmpState = null;
        } else {
          _tmpState = _cursor.getString(_cursorIndexOfState);
        }
        _item.setState(_tmpState);
        final String _tmpPratham_ID;
        if (_cursor.isNull(_cursorIndexOfPrathamID)) {
          _tmpPratham_ID = null;
        } else {
          _tmpPratham_ID = _cursor.getString(_cursorIndexOfPrathamID);
        }
        _item.setPratham_ID(_tmpPratham_ID);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        _item.setDate(_tmpDate);
        final String _tmpSerial_NO;
        if (_cursor.isNull(_cursorIndexOfSerialNO)) {
          _tmpSerial_NO = null;
        } else {
          _tmpSerial_NO = _cursor.getString(_cursorIndexOfSerialNO);
        }
        _item.setSerial_NO(_tmpSerial_NO);
        final boolean _tmpOldFlag;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfOldFlag);
        _tmpOldFlag = _tmp != 0;
        _item.setOldFlag(_tmpOldFlag);
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
