package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Aser;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AserDao_Impl implements AserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Aser> __insertionAdapterOfAser;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAserData;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllAser;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSentFlag;

  public AserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAser = new EntityInsertionAdapter<Aser>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Aser` (`AserID`,`StudentId`,`TestType`,`TestDate`,`ChildID`,`Lang`,`Num`,`OAdd`,`OSub`,`OMul`,`ODiv`,`WAdd`,`WSub`,`FLAG`,`CreatedBy`,`CreatedDate`,`DeviceId`,`GroupID`,`sharedBy`,`SharedAtDateTime`,`appVersion`,`appName`,`CreatedOn`,`sentFlag`,`English`,`EnglishSelected`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Aser value) {
        stmt.bindLong(1, value.AserID);
        if (value.StudentId == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.StudentId);
        }
        stmt.bindLong(3, value.TestType);
        if (value.TestDate == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.TestDate);
        }
        if (value.ChildID == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.ChildID);
        }
        stmt.bindLong(6, value.Lang);
        stmt.bindLong(7, value.Num);
        stmt.bindLong(8, value.OAdd);
        stmt.bindLong(9, value.OSub);
        stmt.bindLong(10, value.OMul);
        stmt.bindLong(11, value.ODiv);
        stmt.bindLong(12, value.WAdd);
        stmt.bindLong(13, value.WSub);
        stmt.bindLong(14, value.FLAG);
        if (value.CreatedBy == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.CreatedBy);
        }
        if (value.CreatedDate == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.CreatedDate);
        }
        if (value.DeviceId == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.DeviceId);
        }
        if (value.GroupID == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.GroupID);
        }
        if (value.sharedBy == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.sharedBy);
        }
        if (value.SharedAtDateTime == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.SharedAtDateTime);
        }
        if (value.appVersion == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.appVersion);
        }
        if (value.appName == null) {
          stmt.bindNull(22);
        } else {
          stmt.bindString(22, value.appName);
        }
        if (value.CreatedOn == null) {
          stmt.bindNull(23);
        } else {
          stmt.bindString(23, value.CreatedOn);
        }
        stmt.bindLong(24, value.sentFlag);
        stmt.bindLong(25, value.English);
        stmt.bindLong(26, value.EnglishSelected);
      }
    };
    this.__preparedStmtOfUpdateAserData = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update Aser set English=?,EnglishSelected=?,ChildID=?, TestDate=?, Lang=?, Num=?, OAdd=?, OSub=?, OMul=?, ODiv =?, WAdd =?, WSub =?, CreatedBy =?, CreatedDate =?, FLAG =?, sentFlag=? WHERE StudentId=? AND TestType =?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllAser = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Aser";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Aser SET sentFlag=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Aser SET sentFlag=? WHERE StudentId =? AND TestType=?";
        return _query;
      }
    };
  }

  @Override
  public void insertAllAserList(final List<Aser> studentsList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAser.insert(studentsList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAser(final Aser aserData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAser.insert(aserData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void UpdateAserData(final int engSpin, final int engMeaning, final String ChildID,
      final String testDate, final int lang, final int num, final int oad, final int osb,
      final int oml, final int odv, final int wad, final int wsb, final String crtby,
      final String crtdt, final int isSelected, final int sentFlag, final String studentID,
      final int TstType) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAserData.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, engSpin);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, engMeaning);
    _argIndex = 3;
    if (ChildID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, ChildID);
    }
    _argIndex = 4;
    if (testDate == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, testDate);
    }
    _argIndex = 5;
    _stmt.bindLong(_argIndex, lang);
    _argIndex = 6;
    _stmt.bindLong(_argIndex, num);
    _argIndex = 7;
    _stmt.bindLong(_argIndex, oad);
    _argIndex = 8;
    _stmt.bindLong(_argIndex, osb);
    _argIndex = 9;
    _stmt.bindLong(_argIndex, oml);
    _argIndex = 10;
    _stmt.bindLong(_argIndex, odv);
    _argIndex = 11;
    _stmt.bindLong(_argIndex, wad);
    _argIndex = 12;
    _stmt.bindLong(_argIndex, wsb);
    _argIndex = 13;
    if (crtby == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, crtby);
    }
    _argIndex = 14;
    if (crtdt == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, crtdt);
    }
    _argIndex = 15;
    _stmt.bindLong(_argIndex, isSelected);
    _argIndex = 16;
    _stmt.bindLong(_argIndex, sentFlag);
    _argIndex = 17;
    if (studentID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, studentID);
    }
    _argIndex = 18;
    _stmt.bindLong(_argIndex, TstType);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateAserData.release(_stmt);
    }
  }

  @Override
  public void deleteAllAser() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllAser.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllAser.release(_stmt);
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
  public void updateSentFlag(final int pushStatus, final String sID, final int testT) {
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
    _stmt.bindLong(_argIndex, testT);
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
  public boolean CheckDataExists(final String StudentId, final int TestType) {
    final String _sql = "SELECT * FROM Aser WHERE StudentId=? AND TestType=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (StudentId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, StudentId);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, TestType);
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
  public List<Aser> GetAllByStudentID(final String StudentID, final int testV) {
    final String _sql = "select * from Aser where StudentId =? AND TestType =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (StudentID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, StudentID);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, testV);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfAserID = CursorUtil.getColumnIndexOrThrow(_cursor, "AserID");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final int _cursorIndexOfTestType = CursorUtil.getColumnIndexOrThrow(_cursor, "TestType");
      final int _cursorIndexOfTestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "TestDate");
      final int _cursorIndexOfChildID = CursorUtil.getColumnIndexOrThrow(_cursor, "ChildID");
      final int _cursorIndexOfLang = CursorUtil.getColumnIndexOrThrow(_cursor, "Lang");
      final int _cursorIndexOfNum = CursorUtil.getColumnIndexOrThrow(_cursor, "Num");
      final int _cursorIndexOfOAdd = CursorUtil.getColumnIndexOrThrow(_cursor, "OAdd");
      final int _cursorIndexOfOSub = CursorUtil.getColumnIndexOrThrow(_cursor, "OSub");
      final int _cursorIndexOfOMul = CursorUtil.getColumnIndexOrThrow(_cursor, "OMul");
      final int _cursorIndexOfODiv = CursorUtil.getColumnIndexOrThrow(_cursor, "ODiv");
      final int _cursorIndexOfWAdd = CursorUtil.getColumnIndexOrThrow(_cursor, "WAdd");
      final int _cursorIndexOfWSub = CursorUtil.getColumnIndexOrThrow(_cursor, "WSub");
      final int _cursorIndexOfFLAG = CursorUtil.getColumnIndexOrThrow(_cursor, "FLAG");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedDate");
      final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "DeviceId");
      final int _cursorIndexOfGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupID");
      final int _cursorIndexOfSharedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "sharedBy");
      final int _cursorIndexOfSharedAtDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "SharedAtDateTime");
      final int _cursorIndexOfAppVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "appVersion");
      final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "English");
      final int _cursorIndexOfEnglishSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "EnglishSelected");
      final List<Aser> _result = new ArrayList<Aser>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Aser _item;
        _item = new Aser();
        _item.AserID = _cursor.getInt(_cursorIndexOfAserID);
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _item.StudentId = null;
        } else {
          _item.StudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        _item.TestType = _cursor.getInt(_cursorIndexOfTestType);
        if (_cursor.isNull(_cursorIndexOfTestDate)) {
          _item.TestDate = null;
        } else {
          _item.TestDate = _cursor.getString(_cursorIndexOfTestDate);
        }
        if (_cursor.isNull(_cursorIndexOfChildID)) {
          _item.ChildID = null;
        } else {
          _item.ChildID = _cursor.getString(_cursorIndexOfChildID);
        }
        _item.Lang = _cursor.getInt(_cursorIndexOfLang);
        _item.Num = _cursor.getInt(_cursorIndexOfNum);
        _item.OAdd = _cursor.getInt(_cursorIndexOfOAdd);
        _item.OSub = _cursor.getInt(_cursorIndexOfOSub);
        _item.OMul = _cursor.getInt(_cursorIndexOfOMul);
        _item.ODiv = _cursor.getInt(_cursorIndexOfODiv);
        _item.WAdd = _cursor.getInt(_cursorIndexOfWAdd);
        _item.WSub = _cursor.getInt(_cursorIndexOfWSub);
        _item.FLAG = _cursor.getInt(_cursorIndexOfFLAG);
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
          _item.CreatedDate = null;
        } else {
          _item.CreatedDate = _cursor.getString(_cursorIndexOfCreatedDate);
        }
        if (_cursor.isNull(_cursorIndexOfDeviceId)) {
          _item.DeviceId = null;
        } else {
          _item.DeviceId = _cursor.getString(_cursorIndexOfDeviceId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupID)) {
          _item.GroupID = null;
        } else {
          _item.GroupID = _cursor.getString(_cursorIndexOfGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfSharedBy)) {
          _item.sharedBy = null;
        } else {
          _item.sharedBy = _cursor.getString(_cursorIndexOfSharedBy);
        }
        if (_cursor.isNull(_cursorIndexOfSharedAtDateTime)) {
          _item.SharedAtDateTime = null;
        } else {
          _item.SharedAtDateTime = _cursor.getString(_cursorIndexOfSharedAtDateTime);
        }
        if (_cursor.isNull(_cursorIndexOfAppVersion)) {
          _item.appVersion = null;
        } else {
          _item.appVersion = _cursor.getString(_cursorIndexOfAppVersion);
        }
        if (_cursor.isNull(_cursorIndexOfAppName)) {
          _item.appName = null;
        } else {
          _item.appName = _cursor.getString(_cursorIndexOfAppName);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
          _item.CreatedOn = null;
        } else {
          _item.CreatedOn = _cursor.getString(_cursorIndexOfCreatedOn);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        _item.English = _cursor.getInt(_cursorIndexOfEnglish);
        _item.EnglishSelected = _cursor.getInt(_cursorIndexOfEnglishSelected);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Aser> getNewAser(final int status) {
    final String _sql = "SELECT * FROM Aser WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfAserID = CursorUtil.getColumnIndexOrThrow(_cursor, "AserID");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final int _cursorIndexOfTestType = CursorUtil.getColumnIndexOrThrow(_cursor, "TestType");
      final int _cursorIndexOfTestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "TestDate");
      final int _cursorIndexOfChildID = CursorUtil.getColumnIndexOrThrow(_cursor, "ChildID");
      final int _cursorIndexOfLang = CursorUtil.getColumnIndexOrThrow(_cursor, "Lang");
      final int _cursorIndexOfNum = CursorUtil.getColumnIndexOrThrow(_cursor, "Num");
      final int _cursorIndexOfOAdd = CursorUtil.getColumnIndexOrThrow(_cursor, "OAdd");
      final int _cursorIndexOfOSub = CursorUtil.getColumnIndexOrThrow(_cursor, "OSub");
      final int _cursorIndexOfOMul = CursorUtil.getColumnIndexOrThrow(_cursor, "OMul");
      final int _cursorIndexOfODiv = CursorUtil.getColumnIndexOrThrow(_cursor, "ODiv");
      final int _cursorIndexOfWAdd = CursorUtil.getColumnIndexOrThrow(_cursor, "WAdd");
      final int _cursorIndexOfWSub = CursorUtil.getColumnIndexOrThrow(_cursor, "WSub");
      final int _cursorIndexOfFLAG = CursorUtil.getColumnIndexOrThrow(_cursor, "FLAG");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedDate");
      final int _cursorIndexOfDeviceId = CursorUtil.getColumnIndexOrThrow(_cursor, "DeviceId");
      final int _cursorIndexOfGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupID");
      final int _cursorIndexOfSharedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "sharedBy");
      final int _cursorIndexOfSharedAtDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "SharedAtDateTime");
      final int _cursorIndexOfAppVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "appVersion");
      final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfEnglish = CursorUtil.getColumnIndexOrThrow(_cursor, "English");
      final int _cursorIndexOfEnglishSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "EnglishSelected");
      final List<Aser> _result = new ArrayList<Aser>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Aser _item;
        _item = new Aser();
        _item.AserID = _cursor.getInt(_cursorIndexOfAserID);
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _item.StudentId = null;
        } else {
          _item.StudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        _item.TestType = _cursor.getInt(_cursorIndexOfTestType);
        if (_cursor.isNull(_cursorIndexOfTestDate)) {
          _item.TestDate = null;
        } else {
          _item.TestDate = _cursor.getString(_cursorIndexOfTestDate);
        }
        if (_cursor.isNull(_cursorIndexOfChildID)) {
          _item.ChildID = null;
        } else {
          _item.ChildID = _cursor.getString(_cursorIndexOfChildID);
        }
        _item.Lang = _cursor.getInt(_cursorIndexOfLang);
        _item.Num = _cursor.getInt(_cursorIndexOfNum);
        _item.OAdd = _cursor.getInt(_cursorIndexOfOAdd);
        _item.OSub = _cursor.getInt(_cursorIndexOfOSub);
        _item.OMul = _cursor.getInt(_cursorIndexOfOMul);
        _item.ODiv = _cursor.getInt(_cursorIndexOfODiv);
        _item.WAdd = _cursor.getInt(_cursorIndexOfWAdd);
        _item.WSub = _cursor.getInt(_cursorIndexOfWSub);
        _item.FLAG = _cursor.getInt(_cursorIndexOfFLAG);
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.CreatedBy = null;
        } else {
          _item.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
          _item.CreatedDate = null;
        } else {
          _item.CreatedDate = _cursor.getString(_cursorIndexOfCreatedDate);
        }
        if (_cursor.isNull(_cursorIndexOfDeviceId)) {
          _item.DeviceId = null;
        } else {
          _item.DeviceId = _cursor.getString(_cursorIndexOfDeviceId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupID)) {
          _item.GroupID = null;
        } else {
          _item.GroupID = _cursor.getString(_cursorIndexOfGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfSharedBy)) {
          _item.sharedBy = null;
        } else {
          _item.sharedBy = _cursor.getString(_cursorIndexOfSharedBy);
        }
        if (_cursor.isNull(_cursorIndexOfSharedAtDateTime)) {
          _item.SharedAtDateTime = null;
        } else {
          _item.SharedAtDateTime = _cursor.getString(_cursorIndexOfSharedAtDateTime);
        }
        if (_cursor.isNull(_cursorIndexOfAppVersion)) {
          _item.appVersion = null;
        } else {
          _item.appVersion = _cursor.getString(_cursorIndexOfAppVersion);
        }
        if (_cursor.isNull(_cursorIndexOfAppName)) {
          _item.appName = null;
        } else {
          _item.appName = _cursor.getString(_cursorIndexOfAppName);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
          _item.CreatedOn = null;
        } else {
          _item.CreatedOn = _cursor.getString(_cursorIndexOfCreatedOn);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        _item.English = _cursor.getInt(_cursorIndexOfEnglish);
        _item.EnglishSelected = _cursor.getInt(_cursorIndexOfEnglishSelected);
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
