package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.TabletStatus;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TabletStatusDao_Impl implements TabletStatusDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TabletStatus> __insertionAdapterOfTabletStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllTabletStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTabTabletStatus;

  public TabletStatusDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTabletStatus = new EntityInsertionAdapter<TabletStatus>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TabletStatus` (`qrID`,`loggedCRL_Id`,`loggedCRL_Name`,`prathamId`,`date`,`serialNo`,`status`,`oldFlag`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TabletStatus value) {
        if (value.getQrID() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getQrID());
        }
        if (value.getLoggedCRL_Id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLoggedCRL_Id());
        }
        if (value.getLoggedCRL_Name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLoggedCRL_Name());
        }
        if (value.getPrathamId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPrathamId());
        }
        if (value.getDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDate());
        }
        if (value.getSerialNo() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSerialNo());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getStatus());
        }
        final int _tmp;
        _tmp = value.getOldFlag() ? 1 : 0;
        stmt.bindLong(8, _tmp);
      }
    };
    this.__preparedStmtOfDeleteAllTabletStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TabletStatus";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteTabTabletStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TabletStatus WHERE qrID=?";
        return _query;
      }
    };
  }

  @Override
  public long insertTabTrack(final TabletStatus tabletStatus) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfTabletStatus.insertAndReturnId(tabletStatus);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllTabletStatus(final List<TabletStatus> tabletStatuse) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTabletStatus.insert(tabletStatuse);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllTabletStatus() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllTabletStatus.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllTabletStatus.release(_stmt);
    }
  }

  @Override
  public void deleteTabTabletStatus(final String qrId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTabTabletStatus.acquire();
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
      __preparedStmtOfDeleteTabTabletStatus.release(_stmt);
    }
  }

  @Override
  public List<TabletStatus> checkExistance(final String id) {
    final String _sql = "SELECT * FROM TabletStatus WHERE qrID=?";
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
      final int _cursorIndexOfQrID = CursorUtil.getColumnIndexOrThrow(_cursor, "qrID");
      final int _cursorIndexOfLoggedCRLId = CursorUtil.getColumnIndexOrThrow(_cursor, "loggedCRL_Id");
      final int _cursorIndexOfLoggedCRLName = CursorUtil.getColumnIndexOrThrow(_cursor, "loggedCRL_Name");
      final int _cursorIndexOfPrathamId = CursorUtil.getColumnIndexOrThrow(_cursor, "prathamId");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfSerialNo = CursorUtil.getColumnIndexOrThrow(_cursor, "serialNo");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfOldFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "oldFlag");
      final List<TabletStatus> _result = new ArrayList<TabletStatus>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TabletStatus _item;
        _item = new TabletStatus();
        final String _tmpQrID;
        if (_cursor.isNull(_cursorIndexOfQrID)) {
          _tmpQrID = null;
        } else {
          _tmpQrID = _cursor.getString(_cursorIndexOfQrID);
        }
        _item.setQrID(_tmpQrID);
        final String _tmpLoggedCRL_Id;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLId)) {
          _tmpLoggedCRL_Id = null;
        } else {
          _tmpLoggedCRL_Id = _cursor.getString(_cursorIndexOfLoggedCRLId);
        }
        _item.setLoggedCRL_Id(_tmpLoggedCRL_Id);
        final String _tmpLoggedCRL_Name;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLName)) {
          _tmpLoggedCRL_Name = null;
        } else {
          _tmpLoggedCRL_Name = _cursor.getString(_cursorIndexOfLoggedCRLName);
        }
        _item.setLoggedCRL_Name(_tmpLoggedCRL_Name);
        final String _tmpPrathamId;
        if (_cursor.isNull(_cursorIndexOfPrathamId)) {
          _tmpPrathamId = null;
        } else {
          _tmpPrathamId = _cursor.getString(_cursorIndexOfPrathamId);
        }
        _item.setPrathamId(_tmpPrathamId);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        _item.setDate(_tmpDate);
        final String _tmpSerialNo;
        if (_cursor.isNull(_cursorIndexOfSerialNo)) {
          _tmpSerialNo = null;
        } else {
          _tmpSerialNo = _cursor.getString(_cursorIndexOfSerialNo);
        }
        _item.setSerialNo(_tmpSerialNo);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
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
  public List<TabletStatus> getAllTabletStatus() {
    final String _sql = "SELECT * FROM TabletStatus";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfQrID = CursorUtil.getColumnIndexOrThrow(_cursor, "qrID");
      final int _cursorIndexOfLoggedCRLId = CursorUtil.getColumnIndexOrThrow(_cursor, "loggedCRL_Id");
      final int _cursorIndexOfLoggedCRLName = CursorUtil.getColumnIndexOrThrow(_cursor, "loggedCRL_Name");
      final int _cursorIndexOfPrathamId = CursorUtil.getColumnIndexOrThrow(_cursor, "prathamId");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfSerialNo = CursorUtil.getColumnIndexOrThrow(_cursor, "serialNo");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfOldFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "oldFlag");
      final List<TabletStatus> _result = new ArrayList<TabletStatus>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TabletStatus _item;
        _item = new TabletStatus();
        final String _tmpQrID;
        if (_cursor.isNull(_cursorIndexOfQrID)) {
          _tmpQrID = null;
        } else {
          _tmpQrID = _cursor.getString(_cursorIndexOfQrID);
        }
        _item.setQrID(_tmpQrID);
        final String _tmpLoggedCRL_Id;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLId)) {
          _tmpLoggedCRL_Id = null;
        } else {
          _tmpLoggedCRL_Id = _cursor.getString(_cursorIndexOfLoggedCRLId);
        }
        _item.setLoggedCRL_Id(_tmpLoggedCRL_Id);
        final String _tmpLoggedCRL_Name;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLName)) {
          _tmpLoggedCRL_Name = null;
        } else {
          _tmpLoggedCRL_Name = _cursor.getString(_cursorIndexOfLoggedCRLName);
        }
        _item.setLoggedCRL_Name(_tmpLoggedCRL_Name);
        final String _tmpPrathamId;
        if (_cursor.isNull(_cursorIndexOfPrathamId)) {
          _tmpPrathamId = null;
        } else {
          _tmpPrathamId = _cursor.getString(_cursorIndexOfPrathamId);
        }
        _item.setPrathamId(_tmpPrathamId);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        _item.setDate(_tmpDate);
        final String _tmpSerialNo;
        if (_cursor.isNull(_cursorIndexOfSerialNo)) {
          _tmpSerialNo = null;
        } else {
          _tmpSerialNo = _cursor.getString(_cursorIndexOfSerialNo);
        }
        _item.setSerialNo(_tmpSerialNo);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
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
