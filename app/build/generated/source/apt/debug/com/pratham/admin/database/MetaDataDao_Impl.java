package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.MetaData;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MetaDataDao_Impl implements MetaDataDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MetaData> __insertionAdapterOfMetaData;

  public MetaDataDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMetaData = new EntityInsertionAdapter<MetaData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `MetaData` (`keys`,`value`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MetaData value) {
        if (value.getKeys() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getKeys());
        }
        if (value.getValue() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getValue());
        }
      }
    };
  }

  @Override
  public void insertMetadata(final MetaData metaData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMetaData.insert(metaData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<MetaData> getAllMetaData() {
    final String _sql = "SELECT * FROM MetaData";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfKeys = CursorUtil.getColumnIndexOrThrow(_cursor, "keys");
      final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
      final List<MetaData> _result = new ArrayList<MetaData>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MetaData _item;
        _item = new MetaData();
        final String _tmpKeys;
        if (_cursor.isNull(_cursorIndexOfKeys)) {
          _tmpKeys = null;
        } else {
          _tmpKeys = _cursor.getString(_cursorIndexOfKeys);
        }
        _item.setKeys(_tmpKeys);
        final String _tmpValue;
        if (_cursor.isNull(_cursorIndexOfValue)) {
          _tmpValue = null;
        } else {
          _tmpValue = _cursor.getString(_cursorIndexOfValue);
        }
        _item.setValue(_tmpValue);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getCrlMetaData() {
    final String _sql = "SELECT value  FROM MetaData WHERE keys=='CRL_ID'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getString(0);
        }
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getProgramID() {
    final String _sql = "SELECT value FROM MetaData WHERE keys=='ProgramID'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getString(0);
        }
      } else {
        _result = null;
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
