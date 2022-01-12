package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Youth;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class YouthDao_Impl implements YouthDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Youth> __insertionAdapterOfYouth;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllYouths;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  public YouthDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfYouth = new EntityInsertionAdapter<Youth>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Youth` (`youthId`,`groupId`,`groupName`,`firstName`,`middleName`,`lastName`,`phoneNumber`,`guardianName`,`birthDate`,`gender`,`maritalStatus`,`areyoustudying`,`education`,`occupation`,`haveSmartphone`,`useSmartphone`,`wantToJoin`,`createdBy`,`createdOn`,`isDeleted`,`sentFlag`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Youth value) {
        if (value.youthId == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.youthId);
        }
        if (value.groupId == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.groupId);
        }
        if (value.groupName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.groupName);
        }
        if (value.firstName == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.firstName);
        }
        if (value.middleName == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.middleName);
        }
        if (value.lastName == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.lastName);
        }
        if (value.phoneNumber == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.phoneNumber);
        }
        if (value.guardianName == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.guardianName);
        }
        if (value.birthDate == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.birthDate);
        }
        if (value.gender == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.gender);
        }
        if (value.maritalStatus == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.maritalStatus);
        }
        stmt.bindLong(12, value.areyoustudying);
        if (value.education == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.education);
        }
        if (value.occupation == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.occupation);
        }
        stmt.bindLong(15, value.haveSmartphone);
        stmt.bindLong(16, value.useSmartphone);
        stmt.bindLong(17, value.wantToJoin);
        if (value.createdBy == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.createdBy);
        }
        if (value.createdOn == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.createdOn);
        }
        stmt.bindLong(20, value.isDeleted);
        stmt.bindLong(21, value.sentFlag);
      }
    };
    this.__preparedStmtOfDeleteAllYouths = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Youth";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Youth SET sentFlag=?";
        return _query;
      }
    };
  }

  @Override
  public void insertAllYouths(final List<Youth> youthList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfYouth.insert(youthList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertYouth(final Youth youth) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfYouth.insert(youth);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllYouths() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllYouths.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllYouths.release(_stmt);
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
  public List<Youth> getAllYouth() {
    final String _sql = "SELECT * FROM Youth";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfYouthId = CursorUtil.getColumnIndexOrThrow(_cursor, "youthId");
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "groupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "groupName");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "firstName");
      final int _cursorIndexOfMiddleName = CursorUtil.getColumnIndexOrThrow(_cursor, "middleName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "lastName");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
      final int _cursorIndexOfGuardianName = CursorUtil.getColumnIndexOrThrow(_cursor, "guardianName");
      final int _cursorIndexOfBirthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "birthDate");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
      final int _cursorIndexOfMaritalStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "maritalStatus");
      final int _cursorIndexOfAreyoustudying = CursorUtil.getColumnIndexOrThrow(_cursor, "areyoustudying");
      final int _cursorIndexOfEducation = CursorUtil.getColumnIndexOrThrow(_cursor, "education");
      final int _cursorIndexOfOccupation = CursorUtil.getColumnIndexOrThrow(_cursor, "occupation");
      final int _cursorIndexOfHaveSmartphone = CursorUtil.getColumnIndexOrThrow(_cursor, "haveSmartphone");
      final int _cursorIndexOfUseSmartphone = CursorUtil.getColumnIndexOrThrow(_cursor, "useSmartphone");
      final int _cursorIndexOfWantToJoin = CursorUtil.getColumnIndexOrThrow(_cursor, "wantToJoin");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "createdBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
      final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Youth> _result = new ArrayList<Youth>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Youth _item;
        _item = new Youth();
        if (_cursor.isNull(_cursorIndexOfYouthId)) {
          _item.youthId = null;
        } else {
          _item.youthId = _cursor.getString(_cursorIndexOfYouthId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _item.groupId = null;
        } else {
          _item.groupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _item.groupName = null;
        } else {
          _item.groupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _item.firstName = null;
        } else {
          _item.firstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        if (_cursor.isNull(_cursorIndexOfMiddleName)) {
          _item.middleName = null;
        } else {
          _item.middleName = _cursor.getString(_cursorIndexOfMiddleName);
        }
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _item.lastName = null;
        } else {
          _item.lastName = _cursor.getString(_cursorIndexOfLastName);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
          _item.phoneNumber = null;
        } else {
          _item.phoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        }
        if (_cursor.isNull(_cursorIndexOfGuardianName)) {
          _item.guardianName = null;
        } else {
          _item.guardianName = _cursor.getString(_cursorIndexOfGuardianName);
        }
        if (_cursor.isNull(_cursorIndexOfBirthDate)) {
          _item.birthDate = null;
        } else {
          _item.birthDate = _cursor.getString(_cursorIndexOfBirthDate);
        }
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _item.gender = null;
        } else {
          _item.gender = _cursor.getString(_cursorIndexOfGender);
        }
        if (_cursor.isNull(_cursorIndexOfMaritalStatus)) {
          _item.maritalStatus = null;
        } else {
          _item.maritalStatus = _cursor.getString(_cursorIndexOfMaritalStatus);
        }
        _item.areyoustudying = _cursor.getInt(_cursorIndexOfAreyoustudying);
        if (_cursor.isNull(_cursorIndexOfEducation)) {
          _item.education = null;
        } else {
          _item.education = _cursor.getString(_cursorIndexOfEducation);
        }
        if (_cursor.isNull(_cursorIndexOfOccupation)) {
          _item.occupation = null;
        } else {
          _item.occupation = _cursor.getString(_cursorIndexOfOccupation);
        }
        _item.haveSmartphone = _cursor.getInt(_cursorIndexOfHaveSmartphone);
        _item.useSmartphone = _cursor.getInt(_cursorIndexOfUseSmartphone);
        _item.wantToJoin = _cursor.getInt(_cursorIndexOfWantToJoin);
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.createdBy = null;
        } else {
          _item.createdBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
          _item.createdOn = null;
        } else {
          _item.createdOn = _cursor.getString(_cursorIndexOfCreatedOn);
        }
        _item.isDeleted = _cursor.getInt(_cursorIndexOfIsDeleted);
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
  public List<Youth> getNewYouths(final int status) {
    final String _sql = "SELECT * FROM Youth WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfYouthId = CursorUtil.getColumnIndexOrThrow(_cursor, "youthId");
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "groupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "groupName");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "firstName");
      final int _cursorIndexOfMiddleName = CursorUtil.getColumnIndexOrThrow(_cursor, "middleName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "lastName");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumber");
      final int _cursorIndexOfGuardianName = CursorUtil.getColumnIndexOrThrow(_cursor, "guardianName");
      final int _cursorIndexOfBirthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "birthDate");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
      final int _cursorIndexOfMaritalStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "maritalStatus");
      final int _cursorIndexOfAreyoustudying = CursorUtil.getColumnIndexOrThrow(_cursor, "areyoustudying");
      final int _cursorIndexOfEducation = CursorUtil.getColumnIndexOrThrow(_cursor, "education");
      final int _cursorIndexOfOccupation = CursorUtil.getColumnIndexOrThrow(_cursor, "occupation");
      final int _cursorIndexOfHaveSmartphone = CursorUtil.getColumnIndexOrThrow(_cursor, "haveSmartphone");
      final int _cursorIndexOfUseSmartphone = CursorUtil.getColumnIndexOrThrow(_cursor, "useSmartphone");
      final int _cursorIndexOfWantToJoin = CursorUtil.getColumnIndexOrThrow(_cursor, "wantToJoin");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "createdBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
      final int _cursorIndexOfIsDeleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isDeleted");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Youth> _result = new ArrayList<Youth>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Youth _item;
        _item = new Youth();
        if (_cursor.isNull(_cursorIndexOfYouthId)) {
          _item.youthId = null;
        } else {
          _item.youthId = _cursor.getString(_cursorIndexOfYouthId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _item.groupId = null;
        } else {
          _item.groupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _item.groupName = null;
        } else {
          _item.groupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _item.firstName = null;
        } else {
          _item.firstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        if (_cursor.isNull(_cursorIndexOfMiddleName)) {
          _item.middleName = null;
        } else {
          _item.middleName = _cursor.getString(_cursorIndexOfMiddleName);
        }
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _item.lastName = null;
        } else {
          _item.lastName = _cursor.getString(_cursorIndexOfLastName);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneNumber)) {
          _item.phoneNumber = null;
        } else {
          _item.phoneNumber = _cursor.getString(_cursorIndexOfPhoneNumber);
        }
        if (_cursor.isNull(_cursorIndexOfGuardianName)) {
          _item.guardianName = null;
        } else {
          _item.guardianName = _cursor.getString(_cursorIndexOfGuardianName);
        }
        if (_cursor.isNull(_cursorIndexOfBirthDate)) {
          _item.birthDate = null;
        } else {
          _item.birthDate = _cursor.getString(_cursorIndexOfBirthDate);
        }
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _item.gender = null;
        } else {
          _item.gender = _cursor.getString(_cursorIndexOfGender);
        }
        if (_cursor.isNull(_cursorIndexOfMaritalStatus)) {
          _item.maritalStatus = null;
        } else {
          _item.maritalStatus = _cursor.getString(_cursorIndexOfMaritalStatus);
        }
        _item.areyoustudying = _cursor.getInt(_cursorIndexOfAreyoustudying);
        if (_cursor.isNull(_cursorIndexOfEducation)) {
          _item.education = null;
        } else {
          _item.education = _cursor.getString(_cursorIndexOfEducation);
        }
        if (_cursor.isNull(_cursorIndexOfOccupation)) {
          _item.occupation = null;
        } else {
          _item.occupation = _cursor.getString(_cursorIndexOfOccupation);
        }
        _item.haveSmartphone = _cursor.getInt(_cursorIndexOfHaveSmartphone);
        _item.useSmartphone = _cursor.getInt(_cursorIndexOfUseSmartphone);
        _item.wantToJoin = _cursor.getInt(_cursorIndexOfWantToJoin);
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _item.createdBy = null;
        } else {
          _item.createdBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
          _item.createdOn = null;
        } else {
          _item.createdOn = _cursor.getString(_cursorIndexOfCreatedOn);
        }
        _item.isDeleted = _cursor.getInt(_cursorIndexOfIsDeleted);
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
