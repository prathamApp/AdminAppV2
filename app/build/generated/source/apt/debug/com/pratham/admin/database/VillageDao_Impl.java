package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Village;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class VillageDao_Impl implements VillageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Village> __insertionAdapterOfVillage;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllVillages;

  public VillageDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVillage = new EntityInsertionAdapter<Village>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Village` (`VillageId`,`VillageCode`,`VillageName`,`Block`,`District`,`State`,`CRLId`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Village value) {
        if (value.VillageId == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.VillageId);
        }
        if (value.VillageCode == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.VillageCode);
        }
        if (value.VillageName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.VillageName);
        }
        if (value.Block == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.Block);
        }
        if (value.District == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.District);
        }
        if (value.State == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.State);
        }
        if (value.CRLId == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.CRLId);
        }
      }
    };
    this.__preparedStmtOfDeleteAllVillages = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Village";
        return _query;
      }
    };
  }

  @Override
  public void insertAllVillages(final List<Village> villagesList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfVillage.insert(villagesList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllVillages() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllVillages.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllVillages.release(_stmt);
    }
  }

  @Override
  public List<Village> getAllVillages() {
    final String _sql = "SELECT * FROM Village";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfVillageId = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageId");
      final int _cursorIndexOfVillageCode = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageCode");
      final int _cursorIndexOfVillageName = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageName");
      final int _cursorIndexOfBlock = CursorUtil.getColumnIndexOrThrow(_cursor, "Block");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "District");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "State");
      final int _cursorIndexOfCRLId = CursorUtil.getColumnIndexOrThrow(_cursor, "CRLId");
      final List<Village> _result = new ArrayList<Village>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Village _item;
        _item = new Village();
        if (_cursor.isNull(_cursorIndexOfVillageId)) {
          _item.VillageId = null;
        } else {
          _item.VillageId = _cursor.getString(_cursorIndexOfVillageId);
        }
        if (_cursor.isNull(_cursorIndexOfVillageCode)) {
          _item.VillageCode = null;
        } else {
          _item.VillageCode = _cursor.getString(_cursorIndexOfVillageCode);
        }
        if (_cursor.isNull(_cursorIndexOfVillageName)) {
          _item.VillageName = null;
        } else {
          _item.VillageName = _cursor.getString(_cursorIndexOfVillageName);
        }
        if (_cursor.isNull(_cursorIndexOfBlock)) {
          _item.Block = null;
        } else {
          _item.Block = _cursor.getString(_cursorIndexOfBlock);
        }
        if (_cursor.isNull(_cursorIndexOfDistrict)) {
          _item.District = null;
        } else {
          _item.District = _cursor.getString(_cursorIndexOfDistrict);
        }
        if (_cursor.isNull(_cursorIndexOfState)) {
          _item.State = null;
        } else {
          _item.State = _cursor.getString(_cursorIndexOfState);
        }
        if (_cursor.isNull(_cursorIndexOfCRLId)) {
          _item.CRLId = null;
        } else {
          _item.CRLId = _cursor.getString(_cursorIndexOfCRLId);
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
  public List<String> getState() {
    final String _sql = "SELECT DISTINCT State FROM Village ORDER BY State ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getString(0);
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
  public List<String> GetStatewiseBlock(final String st) {
    final String _sql = "SELECT DISTINCT Block FROM Village WHERE State=? ORDER BY Block ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (st == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, st);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getString(0);
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
  public List<Village> GetVillages(final String bk) {
    final String _sql = "SELECT * FROM Village WHERE Block=? ORDER BY VillageName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bk == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bk);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfVillageId = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageId");
      final int _cursorIndexOfVillageCode = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageCode");
      final int _cursorIndexOfVillageName = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageName");
      final int _cursorIndexOfBlock = CursorUtil.getColumnIndexOrThrow(_cursor, "Block");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "District");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "State");
      final int _cursorIndexOfCRLId = CursorUtil.getColumnIndexOrThrow(_cursor, "CRLId");
      final List<Village> _result = new ArrayList<Village>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Village _item;
        _item = new Village();
        if (_cursor.isNull(_cursorIndexOfVillageId)) {
          _item.VillageId = null;
        } else {
          _item.VillageId = _cursor.getString(_cursorIndexOfVillageId);
        }
        if (_cursor.isNull(_cursorIndexOfVillageCode)) {
          _item.VillageCode = null;
        } else {
          _item.VillageCode = _cursor.getString(_cursorIndexOfVillageCode);
        }
        if (_cursor.isNull(_cursorIndexOfVillageName)) {
          _item.VillageName = null;
        } else {
          _item.VillageName = _cursor.getString(_cursorIndexOfVillageName);
        }
        if (_cursor.isNull(_cursorIndexOfBlock)) {
          _item.Block = null;
        } else {
          _item.Block = _cursor.getString(_cursorIndexOfBlock);
        }
        if (_cursor.isNull(_cursorIndexOfDistrict)) {
          _item.District = null;
        } else {
          _item.District = _cursor.getString(_cursorIndexOfDistrict);
        }
        if (_cursor.isNull(_cursorIndexOfState)) {
          _item.State = null;
        } else {
          _item.State = _cursor.getString(_cursorIndexOfState);
        }
        if (_cursor.isNull(_cursorIndexOfCRLId)) {
          _item.CRLId = null;
        } else {
          _item.CRLId = _cursor.getString(_cursorIndexOfCRLId);
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
