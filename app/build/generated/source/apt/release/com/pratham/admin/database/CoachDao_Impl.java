package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Coach;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CoachDao_Impl implements CoachDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Coach> __insertionAdapterOfCoach;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCoaches;

  private final SharedSQLiteStatement __preparedStmtOfUpdateCoachStatus;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  public CoachDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCoach = new EntityInsertionAdapter<Coach>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Coach` (`CoachID`,`CoachName`,`CoachAge`,`CoachGender`,`CoachOccupation`,`CoachSpeciality`,`CoachSubjectExpert`,`CoachEducation`,`CoachGroupID`,`CoachVillageID`,`StartDate`,`EndDate`,`CoachActive`,`CreatedBy`,`CreatedDate`,`sentFlag`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Coach value) {
        if (value.CoachID == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.CoachID);
        }
        if (value.CoachName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.CoachName);
        }
        stmt.bindLong(3, value.CoachAge);
        if (value.CoachGender == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.CoachGender);
        }
        if (value.CoachOccupation == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.CoachOccupation);
        }
        if (value.CoachSpeciality == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.CoachSpeciality);
        }
        if (value.CoachSubjectExpert == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.CoachSubjectExpert);
        }
        if (value.CoachEducation == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.CoachEducation);
        }
        if (value.CoachGroupID == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.CoachGroupID);
        }
        if (value.CoachVillageID == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.CoachVillageID);
        }
        if (value.StartDate == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.StartDate);
        }
        if (value.EndDate == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.EndDate);
        }
        stmt.bindLong(13, value.CoachActive);
        if (value.CreatedBy == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.CreatedBy);
        }
        if (value.CreatedDate == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.CreatedDate);
        }
        stmt.bindLong(16, value.sentFlag);
      }
    };
    this.__preparedStmtOfDeleteAllCoaches = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Coach";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateCoachStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Coach SET CoachActive = ?, EndDate = ? WHERE CoachID =?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Coach SET sentFlag=? WHERE CoachID =?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Coach SET sentFlag=?";
        return _query;
      }
    };
  }

  @Override
  public void insertCoach(final List<Coach> coachList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCoach.insert(coachList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllCoaches() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCoaches.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllCoaches.release(_stmt);
    }
  }

  @Override
  public void updateCoachStatus(final int cActive, final String eDate, final String cID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateCoachStatus.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, cActive);
    _argIndex = 2;
    if (eDate == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, eDate);
    }
    _argIndex = 3;
    if (cID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, cID);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateCoachStatus.release(_stmt);
    }
  }

  @Override
  public void updateSentFlag(final int pushStatus, final String cID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSentFlag.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, pushStatus);
    _argIndex = 2;
    if (cID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, cID);
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
  public List<Coach> getAllCoaches() {
    final String _sql = "SELECT * FROM Coach";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCoachID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachID");
      final int _cursorIndexOfCoachName = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachName");
      final int _cursorIndexOfCoachAge = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachAge");
      final int _cursorIndexOfCoachGender = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachGender");
      final int _cursorIndexOfCoachOccupation = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachOccupation");
      final int _cursorIndexOfCoachSpeciality = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachSpeciality");
      final int _cursorIndexOfCoachSubjectExpert = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachSubjectExpert");
      final int _cursorIndexOfCoachEducation = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachEducation");
      final int _cursorIndexOfCoachGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachGroupID");
      final int _cursorIndexOfCoachVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachVillageID");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "EndDate");
      final int _cursorIndexOfCoachActive = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachActive");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedDate");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Coach> _result = new ArrayList<Coach>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Coach _item;
        _item = new Coach();
        if (_cursor.isNull(_cursorIndexOfCoachID)) {
          _item.CoachID = null;
        } else {
          _item.CoachID = _cursor.getString(_cursorIndexOfCoachID);
        }
        if (_cursor.isNull(_cursorIndexOfCoachName)) {
          _item.CoachName = null;
        } else {
          _item.CoachName = _cursor.getString(_cursorIndexOfCoachName);
        }
        _item.CoachAge = _cursor.getInt(_cursorIndexOfCoachAge);
        if (_cursor.isNull(_cursorIndexOfCoachGender)) {
          _item.CoachGender = null;
        } else {
          _item.CoachGender = _cursor.getString(_cursorIndexOfCoachGender);
        }
        if (_cursor.isNull(_cursorIndexOfCoachOccupation)) {
          _item.CoachOccupation = null;
        } else {
          _item.CoachOccupation = _cursor.getString(_cursorIndexOfCoachOccupation);
        }
        if (_cursor.isNull(_cursorIndexOfCoachSpeciality)) {
          _item.CoachSpeciality = null;
        } else {
          _item.CoachSpeciality = _cursor.getString(_cursorIndexOfCoachSpeciality);
        }
        if (_cursor.isNull(_cursorIndexOfCoachSubjectExpert)) {
          _item.CoachSubjectExpert = null;
        } else {
          _item.CoachSubjectExpert = _cursor.getString(_cursorIndexOfCoachSubjectExpert);
        }
        if (_cursor.isNull(_cursorIndexOfCoachEducation)) {
          _item.CoachEducation = null;
        } else {
          _item.CoachEducation = _cursor.getString(_cursorIndexOfCoachEducation);
        }
        if (_cursor.isNull(_cursorIndexOfCoachGroupID)) {
          _item.CoachGroupID = null;
        } else {
          _item.CoachGroupID = _cursor.getString(_cursorIndexOfCoachGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfCoachVillageID)) {
          _item.CoachVillageID = null;
        } else {
          _item.CoachVillageID = _cursor.getString(_cursorIndexOfCoachVillageID);
        }
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _item.StartDate = null;
        } else {
          _item.StartDate = _cursor.getString(_cursorIndexOfStartDate);
        }
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _item.EndDate = null;
        } else {
          _item.EndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        _item.CoachActive = _cursor.getInt(_cursorIndexOfCoachActive);
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
  public List<Coach> getCoachByID(final String cID) {
    final String _sql = "SELECT * FROM Coach where CoachID =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (cID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, cID);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCoachID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachID");
      final int _cursorIndexOfCoachName = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachName");
      final int _cursorIndexOfCoachAge = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachAge");
      final int _cursorIndexOfCoachGender = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachGender");
      final int _cursorIndexOfCoachOccupation = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachOccupation");
      final int _cursorIndexOfCoachSpeciality = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachSpeciality");
      final int _cursorIndexOfCoachSubjectExpert = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachSubjectExpert");
      final int _cursorIndexOfCoachEducation = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachEducation");
      final int _cursorIndexOfCoachGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachGroupID");
      final int _cursorIndexOfCoachVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachVillageID");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "EndDate");
      final int _cursorIndexOfCoachActive = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachActive");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedDate");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Coach> _result = new ArrayList<Coach>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Coach _item;
        _item = new Coach();
        if (_cursor.isNull(_cursorIndexOfCoachID)) {
          _item.CoachID = null;
        } else {
          _item.CoachID = _cursor.getString(_cursorIndexOfCoachID);
        }
        if (_cursor.isNull(_cursorIndexOfCoachName)) {
          _item.CoachName = null;
        } else {
          _item.CoachName = _cursor.getString(_cursorIndexOfCoachName);
        }
        _item.CoachAge = _cursor.getInt(_cursorIndexOfCoachAge);
        if (_cursor.isNull(_cursorIndexOfCoachGender)) {
          _item.CoachGender = null;
        } else {
          _item.CoachGender = _cursor.getString(_cursorIndexOfCoachGender);
        }
        if (_cursor.isNull(_cursorIndexOfCoachOccupation)) {
          _item.CoachOccupation = null;
        } else {
          _item.CoachOccupation = _cursor.getString(_cursorIndexOfCoachOccupation);
        }
        if (_cursor.isNull(_cursorIndexOfCoachSpeciality)) {
          _item.CoachSpeciality = null;
        } else {
          _item.CoachSpeciality = _cursor.getString(_cursorIndexOfCoachSpeciality);
        }
        if (_cursor.isNull(_cursorIndexOfCoachSubjectExpert)) {
          _item.CoachSubjectExpert = null;
        } else {
          _item.CoachSubjectExpert = _cursor.getString(_cursorIndexOfCoachSubjectExpert);
        }
        if (_cursor.isNull(_cursorIndexOfCoachEducation)) {
          _item.CoachEducation = null;
        } else {
          _item.CoachEducation = _cursor.getString(_cursorIndexOfCoachEducation);
        }
        if (_cursor.isNull(_cursorIndexOfCoachGroupID)) {
          _item.CoachGroupID = null;
        } else {
          _item.CoachGroupID = _cursor.getString(_cursorIndexOfCoachGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfCoachVillageID)) {
          _item.CoachVillageID = null;
        } else {
          _item.CoachVillageID = _cursor.getString(_cursorIndexOfCoachVillageID);
        }
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _item.StartDate = null;
        } else {
          _item.StartDate = _cursor.getString(_cursorIndexOfStartDate);
        }
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _item.EndDate = null;
        } else {
          _item.EndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        _item.CoachActive = _cursor.getInt(_cursorIndexOfCoachActive);
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
  public List<Coach> getCoachByVillageID(final String vID) {
    final String _sql = "SELECT * FROM Coach where CoachVillageID =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (vID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, vID);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCoachID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachID");
      final int _cursorIndexOfCoachName = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachName");
      final int _cursorIndexOfCoachAge = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachAge");
      final int _cursorIndexOfCoachGender = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachGender");
      final int _cursorIndexOfCoachOccupation = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachOccupation");
      final int _cursorIndexOfCoachSpeciality = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachSpeciality");
      final int _cursorIndexOfCoachSubjectExpert = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachSubjectExpert");
      final int _cursorIndexOfCoachEducation = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachEducation");
      final int _cursorIndexOfCoachGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachGroupID");
      final int _cursorIndexOfCoachVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachVillageID");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "EndDate");
      final int _cursorIndexOfCoachActive = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachActive");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedDate");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Coach> _result = new ArrayList<Coach>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Coach _item;
        _item = new Coach();
        if (_cursor.isNull(_cursorIndexOfCoachID)) {
          _item.CoachID = null;
        } else {
          _item.CoachID = _cursor.getString(_cursorIndexOfCoachID);
        }
        if (_cursor.isNull(_cursorIndexOfCoachName)) {
          _item.CoachName = null;
        } else {
          _item.CoachName = _cursor.getString(_cursorIndexOfCoachName);
        }
        _item.CoachAge = _cursor.getInt(_cursorIndexOfCoachAge);
        if (_cursor.isNull(_cursorIndexOfCoachGender)) {
          _item.CoachGender = null;
        } else {
          _item.CoachGender = _cursor.getString(_cursorIndexOfCoachGender);
        }
        if (_cursor.isNull(_cursorIndexOfCoachOccupation)) {
          _item.CoachOccupation = null;
        } else {
          _item.CoachOccupation = _cursor.getString(_cursorIndexOfCoachOccupation);
        }
        if (_cursor.isNull(_cursorIndexOfCoachSpeciality)) {
          _item.CoachSpeciality = null;
        } else {
          _item.CoachSpeciality = _cursor.getString(_cursorIndexOfCoachSpeciality);
        }
        if (_cursor.isNull(_cursorIndexOfCoachSubjectExpert)) {
          _item.CoachSubjectExpert = null;
        } else {
          _item.CoachSubjectExpert = _cursor.getString(_cursorIndexOfCoachSubjectExpert);
        }
        if (_cursor.isNull(_cursorIndexOfCoachEducation)) {
          _item.CoachEducation = null;
        } else {
          _item.CoachEducation = _cursor.getString(_cursorIndexOfCoachEducation);
        }
        if (_cursor.isNull(_cursorIndexOfCoachGroupID)) {
          _item.CoachGroupID = null;
        } else {
          _item.CoachGroupID = _cursor.getString(_cursorIndexOfCoachGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfCoachVillageID)) {
          _item.CoachVillageID = null;
        } else {
          _item.CoachVillageID = _cursor.getString(_cursorIndexOfCoachVillageID);
        }
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _item.StartDate = null;
        } else {
          _item.StartDate = _cursor.getString(_cursorIndexOfStartDate);
        }
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _item.EndDate = null;
        } else {
          _item.EndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        _item.CoachActive = _cursor.getInt(_cursorIndexOfCoachActive);
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
  public List<Coach> getNewCoaches(final int status) {
    final String _sql = "SELECT * FROM Coach WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCoachID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachID");
      final int _cursorIndexOfCoachName = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachName");
      final int _cursorIndexOfCoachAge = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachAge");
      final int _cursorIndexOfCoachGender = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachGender");
      final int _cursorIndexOfCoachOccupation = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachOccupation");
      final int _cursorIndexOfCoachSpeciality = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachSpeciality");
      final int _cursorIndexOfCoachSubjectExpert = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachSubjectExpert");
      final int _cursorIndexOfCoachEducation = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachEducation");
      final int _cursorIndexOfCoachGroupID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachGroupID");
      final int _cursorIndexOfCoachVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachVillageID");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "StartDate");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "EndDate");
      final int _cursorIndexOfCoachActive = CursorUtil.getColumnIndexOrThrow(_cursor, "CoachActive");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedDate");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Coach> _result = new ArrayList<Coach>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Coach _item;
        _item = new Coach();
        if (_cursor.isNull(_cursorIndexOfCoachID)) {
          _item.CoachID = null;
        } else {
          _item.CoachID = _cursor.getString(_cursorIndexOfCoachID);
        }
        if (_cursor.isNull(_cursorIndexOfCoachName)) {
          _item.CoachName = null;
        } else {
          _item.CoachName = _cursor.getString(_cursorIndexOfCoachName);
        }
        _item.CoachAge = _cursor.getInt(_cursorIndexOfCoachAge);
        if (_cursor.isNull(_cursorIndexOfCoachGender)) {
          _item.CoachGender = null;
        } else {
          _item.CoachGender = _cursor.getString(_cursorIndexOfCoachGender);
        }
        if (_cursor.isNull(_cursorIndexOfCoachOccupation)) {
          _item.CoachOccupation = null;
        } else {
          _item.CoachOccupation = _cursor.getString(_cursorIndexOfCoachOccupation);
        }
        if (_cursor.isNull(_cursorIndexOfCoachSpeciality)) {
          _item.CoachSpeciality = null;
        } else {
          _item.CoachSpeciality = _cursor.getString(_cursorIndexOfCoachSpeciality);
        }
        if (_cursor.isNull(_cursorIndexOfCoachSubjectExpert)) {
          _item.CoachSubjectExpert = null;
        } else {
          _item.CoachSubjectExpert = _cursor.getString(_cursorIndexOfCoachSubjectExpert);
        }
        if (_cursor.isNull(_cursorIndexOfCoachEducation)) {
          _item.CoachEducation = null;
        } else {
          _item.CoachEducation = _cursor.getString(_cursorIndexOfCoachEducation);
        }
        if (_cursor.isNull(_cursorIndexOfCoachGroupID)) {
          _item.CoachGroupID = null;
        } else {
          _item.CoachGroupID = _cursor.getString(_cursorIndexOfCoachGroupID);
        }
        if (_cursor.isNull(_cursorIndexOfCoachVillageID)) {
          _item.CoachVillageID = null;
        } else {
          _item.CoachVillageID = _cursor.getString(_cursorIndexOfCoachVillageID);
        }
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _item.StartDate = null;
        } else {
          _item.StartDate = _cursor.getString(_cursorIndexOfStartDate);
        }
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _item.EndDate = null;
        } else {
          _item.EndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        _item.CoachActive = _cursor.getInt(_cursorIndexOfCoachActive);
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
