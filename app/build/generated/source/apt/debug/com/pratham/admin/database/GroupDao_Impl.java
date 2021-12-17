package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Groups;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class GroupDao_Impl implements GroupDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Groups> __insertionAdapterOfGroups;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllGroups;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfRemoveDeletedGroupRecords;

  public GroupDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGroups = new EntityInsertionAdapter<Groups>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Groups` (`GroupId`,`GroupName`,`VillageId`,`ProgramId`,`GroupCode`,`SchoolName`,`VIllageName`,`DeviceId`,`CreatedBy`,`CreatedOn`,`sentFlag`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Groups value) {
        if (value.GroupId == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.GroupId);
        }
        if (value.GroupName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.GroupName);
        }
        if (value.VillageId == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.VillageId);
        }
        stmt.bindLong(4, value.ProgramId);
        if (value.GroupCode == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.GroupCode);
        }
        if (value.SchoolName == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.SchoolName);
        }
        if (value.VIllageName == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.VIllageName);
        }
        if (value.DeviceId == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.DeviceId);
        }
        if (value.CreatedBy == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.CreatedBy);
        }
        if (value.CreatedOn == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.CreatedOn);
        }
        stmt.bindLong(11, value.sentFlag);
      }
    };
    this.__preparedStmtOfDeleteAllGroups = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Groups";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Groups SET sentFlag=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Groups SET sentFlag=? WHERE GroupId =?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveDeletedGroupRecords = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Groups WHERE DeviceId='deleted'";
        return _query;
      }
    };
  }

  @Override
  public void insertAllGroups(final List<Groups> groupsList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfGroups.insert(groupsList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertGroup(final Groups grp) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfGroups.insert(grp);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllGroups() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllGroups.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllGroups.release(_stmt);
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
  public void removeDeletedGroupRecords() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveDeletedGroupRecords.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveDeletedGroupRecords.release(_stmt);
    }
  }

  @Override
  public List<Groups> GetGroups(final int vid) {
    final String _sql = "SELECT * From Groups WHERE VillageId =? ORDER BY GroupName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vid);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfVillageId = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageId");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfGroupCode = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupCode");
      final int _cursorIndexOfSchoolName = CursorUtil.getColumnIndexOrThrow(_cursor, "SchoolName");
      final int _cursorIndexOfVIllageName = CursorUtil.getColumnIndexOrThrow(_cursor, "VIllageName");
      final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "DeviceId");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Groups> _result = new ArrayList<Groups>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Groups _item;
        _item = new Groups();
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _item.GroupId = null;
        } else {
          _item.GroupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _item.GroupName = null;
        } else {
          _item.GroupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        if (_cursor.isNull(_cursorIndexOfVillageId)) {
          _item.VillageId = null;
        } else {
          _item.VillageId = _cursor.getString(_cursorIndexOfVillageId);
        }
        _item.ProgramId = _cursor.getInt(_cursorIndexOfProgramId);
        if (_cursor.isNull(_cursorIndexOfGroupCode)) {
          _item.GroupCode = null;
        } else {
          _item.GroupCode = _cursor.getString(_cursorIndexOfGroupCode);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolName)) {
          _item.SchoolName = null;
        } else {
          _item.SchoolName = _cursor.getString(_cursorIndexOfSchoolName);
        }
        if (_cursor.isNull(_cursorIndexOfVIllageName)) {
          _item.VIllageName = null;
        } else {
          _item.VIllageName = _cursor.getString(_cursorIndexOfVIllageName);
        }
        if (_cursor.isNull(_cursorIndexOfDeviceId)) {
          _item.DeviceId = null;
        } else {
          _item.DeviceId = _cursor.getString(_cursorIndexOfDeviceId);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
          _item.CreatedOn = null;
        } else {
          _item.CreatedOn = _cursor.getString(_cursorIndexOfCreatedOn);
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
  public List<Groups> getAllGroups() {
    final String _sql = "SELECT * FROM Groups ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfVillageId = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageId");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfGroupCode = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupCode");
      final int _cursorIndexOfSchoolName = CursorUtil.getColumnIndexOrThrow(_cursor, "SchoolName");
      final int _cursorIndexOfVIllageName = CursorUtil.getColumnIndexOrThrow(_cursor, "VIllageName");
      final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "DeviceId");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Groups> _result = new ArrayList<Groups>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Groups _item;
        _item = new Groups();
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _item.GroupId = null;
        } else {
          _item.GroupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _item.GroupName = null;
        } else {
          _item.GroupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        if (_cursor.isNull(_cursorIndexOfVillageId)) {
          _item.VillageId = null;
        } else {
          _item.VillageId = _cursor.getString(_cursorIndexOfVillageId);
        }
        _item.ProgramId = _cursor.getInt(_cursorIndexOfProgramId);
        if (_cursor.isNull(_cursorIndexOfGroupCode)) {
          _item.GroupCode = null;
        } else {
          _item.GroupCode = _cursor.getString(_cursorIndexOfGroupCode);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolName)) {
          _item.SchoolName = null;
        } else {
          _item.SchoolName = _cursor.getString(_cursorIndexOfSchoolName);
        }
        if (_cursor.isNull(_cursorIndexOfVIllageName)) {
          _item.VIllageName = null;
        } else {
          _item.VIllageName = _cursor.getString(_cursorIndexOfVIllageName);
        }
        if (_cursor.isNull(_cursorIndexOfDeviceId)) {
          _item.DeviceId = null;
        } else {
          _item.DeviceId = _cursor.getString(_cursorIndexOfDeviceId);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
          _item.CreatedOn = null;
        } else {
          _item.CreatedOn = _cursor.getString(_cursorIndexOfCreatedOn);
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
  public List<Groups> getNewGroups(final int status) {
    final String _sql = "SELECT * FROM Groups WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfVillageId = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageId");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfGroupCode = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupCode");
      final int _cursorIndexOfSchoolName = CursorUtil.getColumnIndexOrThrow(_cursor, "SchoolName");
      final int _cursorIndexOfVIllageName = CursorUtil.getColumnIndexOrThrow(_cursor, "VIllageName");
      final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "DeviceId");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Groups> _result = new ArrayList<Groups>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Groups _item;
        _item = new Groups();
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _item.GroupId = null;
        } else {
          _item.GroupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _item.GroupName = null;
        } else {
          _item.GroupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        if (_cursor.isNull(_cursorIndexOfVillageId)) {
          _item.VillageId = null;
        } else {
          _item.VillageId = _cursor.getString(_cursorIndexOfVillageId);
        }
        _item.ProgramId = _cursor.getInt(_cursorIndexOfProgramId);
        if (_cursor.isNull(_cursorIndexOfGroupCode)) {
          _item.GroupCode = null;
        } else {
          _item.GroupCode = _cursor.getString(_cursorIndexOfGroupCode);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolName)) {
          _item.SchoolName = null;
        } else {
          _item.SchoolName = _cursor.getString(_cursorIndexOfSchoolName);
        }
        if (_cursor.isNull(_cursorIndexOfVIllageName)) {
          _item.VIllageName = null;
        } else {
          _item.VIllageName = _cursor.getString(_cursorIndexOfVIllageName);
        }
        if (_cursor.isNull(_cursorIndexOfDeviceId)) {
          _item.DeviceId = null;
        } else {
          _item.DeviceId = _cursor.getString(_cursorIndexOfDeviceId);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
          _item.CreatedOn = null;
        } else {
          _item.CreatedOn = _cursor.getString(_cursorIndexOfCreatedOn);
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
  public List<Groups> GetDeletedGroups() {
    final String _sql = "select * from Groups WHERE DeviceID = 'deleted'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfVillageId = CursorUtil.getColumnIndexOrThrow(_cursor, "VillageId");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfGroupCode = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupCode");
      final int _cursorIndexOfSchoolName = CursorUtil.getColumnIndexOrThrow(_cursor, "SchoolName");
      final int _cursorIndexOfVIllageName = CursorUtil.getColumnIndexOrThrow(_cursor, "VIllageName");
      final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "DeviceId");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Groups> _result = new ArrayList<Groups>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Groups _item;
        _item = new Groups();
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _item.GroupId = null;
        } else {
          _item.GroupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _item.GroupName = null;
        } else {
          _item.GroupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        if (_cursor.isNull(_cursorIndexOfVillageId)) {
          _item.VillageId = null;
        } else {
          _item.VillageId = _cursor.getString(_cursorIndexOfVillageId);
        }
        _item.ProgramId = _cursor.getInt(_cursorIndexOfProgramId);
        if (_cursor.isNull(_cursorIndexOfGroupCode)) {
          _item.GroupCode = null;
        } else {
          _item.GroupCode = _cursor.getString(_cursorIndexOfGroupCode);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolName)) {
          _item.SchoolName = null;
        } else {
          _item.SchoolName = _cursor.getString(_cursorIndexOfSchoolName);
        }
        if (_cursor.isNull(_cursorIndexOfVIllageName)) {
          _item.VIllageName = null;
        } else {
          _item.VIllageName = _cursor.getString(_cursorIndexOfVIllageName);
        }
        if (_cursor.isNull(_cursorIndexOfDeviceId)) {
          _item.DeviceId = null;
        } else {
          _item.DeviceId = _cursor.getString(_cursorIndexOfDeviceId);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
          _item.CreatedOn = null;
        } else {
          _item.CreatedOn = _cursor.getString(_cursorIndexOfCreatedOn);
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
