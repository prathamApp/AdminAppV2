package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.CRL;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CRLdao_Impl implements CRLdao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CRL> __insertionAdapterOfCRL;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCRLs;

  public CRLdao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCRL = new EntityInsertionAdapter<CRL>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `CRL` (`CRLId`,`RoleId`,`RoleName`,`ProgramId`,`ProgramName`,`State`,`FirstName`,`LastName`,`Mobile`,`Email`,`Block`,`District`,`UserName`,`Password`,`ReportingPersonId`,`ReportingPersonName`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CRL value) {
        if (value.getCRLId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getCRLId());
        }
        if (value.getRoleId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getRoleId());
        }
        if (value.getRoleName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getRoleName());
        }
        if (value.getProgramId() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getProgramId());
        }
        if (value.getProgramName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getProgramName());
        }
        if (value.getState() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getState());
        }
        if (value.getFirstName() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getFirstName());
        }
        if (value.getLastName() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getLastName());
        }
        if (value.getMobile() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getMobile());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getEmail());
        }
        if (value.getBlock() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getBlock());
        }
        if (value.getDistrict() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getDistrict());
        }
        if (value.getUserName() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getUserName());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getPassword());
        }
        if (value.getReportingPersonId() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getReportingPersonId());
        }
        if (value.getReportingPersonName() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getReportingPersonName());
        }
      }
    };
    this.__preparedStmtOfDeleteAllCRLs = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM CRL";
        return _query;
      }
    };
  }

  @Override
  public void insertAllCRL(final List<CRL> crlList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCRL.insert(crlList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllCRLs() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCRLs.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllCRLs.release(_stmt);
    }
  }

  @Override
  public List<CRL> getAllCRLs() {
    final String _sql = "SELECT * FROM CRL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCRLId = CursorUtil.getColumnIndexOrThrow(_cursor, "CRLId");
      final int _cursorIndexOfRoleId = CursorUtil.getColumnIndexOrThrow(_cursor, "RoleId");
      final int _cursorIndexOfRoleName = CursorUtil.getColumnIndexOrThrow(_cursor, "RoleName");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfProgramName = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramName");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "State");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfMobile = CursorUtil.getColumnIndexOrThrow(_cursor, "Mobile");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
      final int _cursorIndexOfBlock = CursorUtil.getColumnIndexOrThrow(_cursor, "Block");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "District");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "UserName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "Password");
      final int _cursorIndexOfReportingPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "ReportingPersonId");
      final int _cursorIndexOfReportingPersonName = CursorUtil.getColumnIndexOrThrow(_cursor, "ReportingPersonName");
      final List<CRL> _result = new ArrayList<CRL>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CRL _item;
        _item = new CRL();
        final String _tmpCRLId;
        if (_cursor.isNull(_cursorIndexOfCRLId)) {
          _tmpCRLId = null;
        } else {
          _tmpCRLId = _cursor.getString(_cursorIndexOfCRLId);
        }
        _item.setCRLId(_tmpCRLId);
        final String _tmpRoleId;
        if (_cursor.isNull(_cursorIndexOfRoleId)) {
          _tmpRoleId = null;
        } else {
          _tmpRoleId = _cursor.getString(_cursorIndexOfRoleId);
        }
        _item.setRoleId(_tmpRoleId);
        final String _tmpRoleName;
        if (_cursor.isNull(_cursorIndexOfRoleName)) {
          _tmpRoleName = null;
        } else {
          _tmpRoleName = _cursor.getString(_cursorIndexOfRoleName);
        }
        _item.setRoleName(_tmpRoleName);
        final String _tmpProgramId;
        if (_cursor.isNull(_cursorIndexOfProgramId)) {
          _tmpProgramId = null;
        } else {
          _tmpProgramId = _cursor.getString(_cursorIndexOfProgramId);
        }
        _item.setProgramId(_tmpProgramId);
        final String _tmpProgramName;
        if (_cursor.isNull(_cursorIndexOfProgramName)) {
          _tmpProgramName = null;
        } else {
          _tmpProgramName = _cursor.getString(_cursorIndexOfProgramName);
        }
        _item.setProgramName(_tmpProgramName);
        final String _tmpState;
        if (_cursor.isNull(_cursorIndexOfState)) {
          _tmpState = null;
        } else {
          _tmpState = _cursor.getString(_cursorIndexOfState);
        }
        _item.setState(_tmpState);
        final String _tmpFirstName;
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _tmpFirstName = null;
        } else {
          _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        _item.setFirstName(_tmpFirstName);
        final String _tmpLastName;
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _tmpLastName = null;
        } else {
          _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
        }
        _item.setLastName(_tmpLastName);
        final String _tmpMobile;
        if (_cursor.isNull(_cursorIndexOfMobile)) {
          _tmpMobile = null;
        } else {
          _tmpMobile = _cursor.getString(_cursorIndexOfMobile);
        }
        _item.setMobile(_tmpMobile);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final String _tmpBlock;
        if (_cursor.isNull(_cursorIndexOfBlock)) {
          _tmpBlock = null;
        } else {
          _tmpBlock = _cursor.getString(_cursorIndexOfBlock);
        }
        _item.setBlock(_tmpBlock);
        final String _tmpDistrict;
        if (_cursor.isNull(_cursorIndexOfDistrict)) {
          _tmpDistrict = null;
        } else {
          _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
        }
        _item.setDistrict(_tmpDistrict);
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        _item.setUserName(_tmpUserName);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _item.setPassword(_tmpPassword);
        final String _tmpReportingPersonId;
        if (_cursor.isNull(_cursorIndexOfReportingPersonId)) {
          _tmpReportingPersonId = null;
        } else {
          _tmpReportingPersonId = _cursor.getString(_cursorIndexOfReportingPersonId);
        }
        _item.setReportingPersonId(_tmpReportingPersonId);
        final String _tmpReportingPersonName;
        if (_cursor.isNull(_cursorIndexOfReportingPersonName)) {
          _tmpReportingPersonName = null;
        } else {
          _tmpReportingPersonName = _cursor.getString(_cursorIndexOfReportingPersonName);
        }
        _item.setReportingPersonName(_tmpReportingPersonName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getCRLsCount() {
    final String _sql = "SELECT count(*) FROM CRL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getCRLsRoleById(final String id) {
    final String _sql = "SELECT RoleId FROM CRL where CRLId=?";
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
  public List<String> getDistinctCRLsdProgram(final String rollID) {
    final String _sql = "SELECT DISTINCT ProgramName FROM CRL WHERE RoleId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (rollID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, rollID);
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
  public List<String> getDistinctCRLsRoleId(final String rollID, final String programName) {
    final String _sql = "SELECT DISTINCT  RoleName FROM CRL WHERE RoleId=? and ProgramName=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (rollID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, rollID);
    }
    _argIndex = 2;
    if (programName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, programName);
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
  public List<CRL> getDistinctCRLsUserName(final String roleName, final String programName) {
    final String _sql = "SELECT DISTINCT UserName,CRLId,FirstName FROM CRL WHERE RoleName=? and ProgramName=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (roleName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, roleName);
    }
    _argIndex = 2;
    if (programName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, programName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "UserName");
      final int _cursorIndexOfCRLId = CursorUtil.getColumnIndexOrThrow(_cursor, "CRLId");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final List<CRL> _result = new ArrayList<CRL>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CRL _item;
        _item = new CRL();
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        _item.setUserName(_tmpUserName);
        final String _tmpCRLId;
        if (_cursor.isNull(_cursorIndexOfCRLId)) {
          _tmpCRLId = null;
        } else {
          _tmpCRLId = _cursor.getString(_cursorIndexOfCRLId);
        }
        _item.setCRLId(_tmpCRLId);
        final String _tmpFirstName;
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _tmpFirstName = null;
        } else {
          _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        _item.setFirstName(_tmpFirstName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getCRLsBlockName(final String CrlID) {
    final String _sql = "SELECT Block FROM CRL WHERE CRLId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (CrlID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, CrlID);
    }
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
  public List<CRL> getCRLBlockHeadBYBlockName(final String block) {
    final String _sql = "SELECT * from CRL Where Block=? and RoleId=5";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (block == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, block);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCRLId = CursorUtil.getColumnIndexOrThrow(_cursor, "CRLId");
      final int _cursorIndexOfRoleId = CursorUtil.getColumnIndexOrThrow(_cursor, "RoleId");
      final int _cursorIndexOfRoleName = CursorUtil.getColumnIndexOrThrow(_cursor, "RoleName");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfProgramName = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramName");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "State");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfMobile = CursorUtil.getColumnIndexOrThrow(_cursor, "Mobile");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
      final int _cursorIndexOfBlock = CursorUtil.getColumnIndexOrThrow(_cursor, "Block");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "District");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "UserName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "Password");
      final int _cursorIndexOfReportingPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "ReportingPersonId");
      final int _cursorIndexOfReportingPersonName = CursorUtil.getColumnIndexOrThrow(_cursor, "ReportingPersonName");
      final List<CRL> _result = new ArrayList<CRL>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CRL _item;
        _item = new CRL();
        final String _tmpCRLId;
        if (_cursor.isNull(_cursorIndexOfCRLId)) {
          _tmpCRLId = null;
        } else {
          _tmpCRLId = _cursor.getString(_cursorIndexOfCRLId);
        }
        _item.setCRLId(_tmpCRLId);
        final String _tmpRoleId;
        if (_cursor.isNull(_cursorIndexOfRoleId)) {
          _tmpRoleId = null;
        } else {
          _tmpRoleId = _cursor.getString(_cursorIndexOfRoleId);
        }
        _item.setRoleId(_tmpRoleId);
        final String _tmpRoleName;
        if (_cursor.isNull(_cursorIndexOfRoleName)) {
          _tmpRoleName = null;
        } else {
          _tmpRoleName = _cursor.getString(_cursorIndexOfRoleName);
        }
        _item.setRoleName(_tmpRoleName);
        final String _tmpProgramId;
        if (_cursor.isNull(_cursorIndexOfProgramId)) {
          _tmpProgramId = null;
        } else {
          _tmpProgramId = _cursor.getString(_cursorIndexOfProgramId);
        }
        _item.setProgramId(_tmpProgramId);
        final String _tmpProgramName;
        if (_cursor.isNull(_cursorIndexOfProgramName)) {
          _tmpProgramName = null;
        } else {
          _tmpProgramName = _cursor.getString(_cursorIndexOfProgramName);
        }
        _item.setProgramName(_tmpProgramName);
        final String _tmpState;
        if (_cursor.isNull(_cursorIndexOfState)) {
          _tmpState = null;
        } else {
          _tmpState = _cursor.getString(_cursorIndexOfState);
        }
        _item.setState(_tmpState);
        final String _tmpFirstName;
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _tmpFirstName = null;
        } else {
          _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        _item.setFirstName(_tmpFirstName);
        final String _tmpLastName;
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _tmpLastName = null;
        } else {
          _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
        }
        _item.setLastName(_tmpLastName);
        final String _tmpMobile;
        if (_cursor.isNull(_cursorIndexOfMobile)) {
          _tmpMobile = null;
        } else {
          _tmpMobile = _cursor.getString(_cursorIndexOfMobile);
        }
        _item.setMobile(_tmpMobile);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final String _tmpBlock;
        if (_cursor.isNull(_cursorIndexOfBlock)) {
          _tmpBlock = null;
        } else {
          _tmpBlock = _cursor.getString(_cursorIndexOfBlock);
        }
        _item.setBlock(_tmpBlock);
        final String _tmpDistrict;
        if (_cursor.isNull(_cursorIndexOfDistrict)) {
          _tmpDistrict = null;
        } else {
          _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
        }
        _item.setDistrict(_tmpDistrict);
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        _item.setUserName(_tmpUserName);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _item.setPassword(_tmpPassword);
        final String _tmpReportingPersonId;
        if (_cursor.isNull(_cursorIndexOfReportingPersonId)) {
          _tmpReportingPersonId = null;
        } else {
          _tmpReportingPersonId = _cursor.getString(_cursorIndexOfReportingPersonId);
        }
        _item.setReportingPersonId(_tmpReportingPersonId);
        final String _tmpReportingPersonName;
        if (_cursor.isNull(_cursorIndexOfReportingPersonName)) {
          _tmpReportingPersonName = null;
        } else {
          _tmpReportingPersonName = _cursor.getString(_cursorIndexOfReportingPersonName);
        }
        _item.setReportingPersonName(_tmpReportingPersonName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<CRL> getDistrictLeaderBYBlockName(final String block) {
    final String _sql = "SELECT * from CRL Where Block=? and RoleId=4";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (block == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, block);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCRLId = CursorUtil.getColumnIndexOrThrow(_cursor, "CRLId");
      final int _cursorIndexOfRoleId = CursorUtil.getColumnIndexOrThrow(_cursor, "RoleId");
      final int _cursorIndexOfRoleName = CursorUtil.getColumnIndexOrThrow(_cursor, "RoleName");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfProgramName = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramName");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "State");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfMobile = CursorUtil.getColumnIndexOrThrow(_cursor, "Mobile");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
      final int _cursorIndexOfBlock = CursorUtil.getColumnIndexOrThrow(_cursor, "Block");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "District");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "UserName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "Password");
      final int _cursorIndexOfReportingPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "ReportingPersonId");
      final int _cursorIndexOfReportingPersonName = CursorUtil.getColumnIndexOrThrow(_cursor, "ReportingPersonName");
      final List<CRL> _result = new ArrayList<CRL>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CRL _item;
        _item = new CRL();
        final String _tmpCRLId;
        if (_cursor.isNull(_cursorIndexOfCRLId)) {
          _tmpCRLId = null;
        } else {
          _tmpCRLId = _cursor.getString(_cursorIndexOfCRLId);
        }
        _item.setCRLId(_tmpCRLId);
        final String _tmpRoleId;
        if (_cursor.isNull(_cursorIndexOfRoleId)) {
          _tmpRoleId = null;
        } else {
          _tmpRoleId = _cursor.getString(_cursorIndexOfRoleId);
        }
        _item.setRoleId(_tmpRoleId);
        final String _tmpRoleName;
        if (_cursor.isNull(_cursorIndexOfRoleName)) {
          _tmpRoleName = null;
        } else {
          _tmpRoleName = _cursor.getString(_cursorIndexOfRoleName);
        }
        _item.setRoleName(_tmpRoleName);
        final String _tmpProgramId;
        if (_cursor.isNull(_cursorIndexOfProgramId)) {
          _tmpProgramId = null;
        } else {
          _tmpProgramId = _cursor.getString(_cursorIndexOfProgramId);
        }
        _item.setProgramId(_tmpProgramId);
        final String _tmpProgramName;
        if (_cursor.isNull(_cursorIndexOfProgramName)) {
          _tmpProgramName = null;
        } else {
          _tmpProgramName = _cursor.getString(_cursorIndexOfProgramName);
        }
        _item.setProgramName(_tmpProgramName);
        final String _tmpState;
        if (_cursor.isNull(_cursorIndexOfState)) {
          _tmpState = null;
        } else {
          _tmpState = _cursor.getString(_cursorIndexOfState);
        }
        _item.setState(_tmpState);
        final String _tmpFirstName;
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _tmpFirstName = null;
        } else {
          _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        _item.setFirstName(_tmpFirstName);
        final String _tmpLastName;
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _tmpLastName = null;
        } else {
          _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
        }
        _item.setLastName(_tmpLastName);
        final String _tmpMobile;
        if (_cursor.isNull(_cursorIndexOfMobile)) {
          _tmpMobile = null;
        } else {
          _tmpMobile = _cursor.getString(_cursorIndexOfMobile);
        }
        _item.setMobile(_tmpMobile);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final String _tmpBlock;
        if (_cursor.isNull(_cursorIndexOfBlock)) {
          _tmpBlock = null;
        } else {
          _tmpBlock = _cursor.getString(_cursorIndexOfBlock);
        }
        _item.setBlock(_tmpBlock);
        final String _tmpDistrict;
        if (_cursor.isNull(_cursorIndexOfDistrict)) {
          _tmpDistrict = null;
        } else {
          _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
        }
        _item.setDistrict(_tmpDistrict);
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        _item.setUserName(_tmpUserName);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _item.setPassword(_tmpPassword);
        final String _tmpReportingPersonId;
        if (_cursor.isNull(_cursorIndexOfReportingPersonId)) {
          _tmpReportingPersonId = null;
        } else {
          _tmpReportingPersonId = _cursor.getString(_cursorIndexOfReportingPersonId);
        }
        _item.setReportingPersonId(_tmpReportingPersonId);
        final String _tmpReportingPersonName;
        if (_cursor.isNull(_cursorIndexOfReportingPersonName)) {
          _tmpReportingPersonName = null;
        } else {
          _tmpReportingPersonName = _cursor.getString(_cursorIndexOfReportingPersonName);
        }
        _item.setReportingPersonName(_tmpReportingPersonName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<CRL> getCRLsByReportingPerson(final String reportingPersonId) {
    final String _sql = "SELECT * from CRL where ReportingPersonId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (reportingPersonId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, reportingPersonId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCRLId = CursorUtil.getColumnIndexOrThrow(_cursor, "CRLId");
      final int _cursorIndexOfRoleId = CursorUtil.getColumnIndexOrThrow(_cursor, "RoleId");
      final int _cursorIndexOfRoleName = CursorUtil.getColumnIndexOrThrow(_cursor, "RoleName");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfProgramName = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramName");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "State");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfMobile = CursorUtil.getColumnIndexOrThrow(_cursor, "Mobile");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
      final int _cursorIndexOfBlock = CursorUtil.getColumnIndexOrThrow(_cursor, "Block");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "District");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "UserName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "Password");
      final int _cursorIndexOfReportingPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "ReportingPersonId");
      final int _cursorIndexOfReportingPersonName = CursorUtil.getColumnIndexOrThrow(_cursor, "ReportingPersonName");
      final List<CRL> _result = new ArrayList<CRL>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CRL _item;
        _item = new CRL();
        final String _tmpCRLId;
        if (_cursor.isNull(_cursorIndexOfCRLId)) {
          _tmpCRLId = null;
        } else {
          _tmpCRLId = _cursor.getString(_cursorIndexOfCRLId);
        }
        _item.setCRLId(_tmpCRLId);
        final String _tmpRoleId;
        if (_cursor.isNull(_cursorIndexOfRoleId)) {
          _tmpRoleId = null;
        } else {
          _tmpRoleId = _cursor.getString(_cursorIndexOfRoleId);
        }
        _item.setRoleId(_tmpRoleId);
        final String _tmpRoleName;
        if (_cursor.isNull(_cursorIndexOfRoleName)) {
          _tmpRoleName = null;
        } else {
          _tmpRoleName = _cursor.getString(_cursorIndexOfRoleName);
        }
        _item.setRoleName(_tmpRoleName);
        final String _tmpProgramId;
        if (_cursor.isNull(_cursorIndexOfProgramId)) {
          _tmpProgramId = null;
        } else {
          _tmpProgramId = _cursor.getString(_cursorIndexOfProgramId);
        }
        _item.setProgramId(_tmpProgramId);
        final String _tmpProgramName;
        if (_cursor.isNull(_cursorIndexOfProgramName)) {
          _tmpProgramName = null;
        } else {
          _tmpProgramName = _cursor.getString(_cursorIndexOfProgramName);
        }
        _item.setProgramName(_tmpProgramName);
        final String _tmpState;
        if (_cursor.isNull(_cursorIndexOfState)) {
          _tmpState = null;
        } else {
          _tmpState = _cursor.getString(_cursorIndexOfState);
        }
        _item.setState(_tmpState);
        final String _tmpFirstName;
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _tmpFirstName = null;
        } else {
          _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        _item.setFirstName(_tmpFirstName);
        final String _tmpLastName;
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _tmpLastName = null;
        } else {
          _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
        }
        _item.setLastName(_tmpLastName);
        final String _tmpMobile;
        if (_cursor.isNull(_cursorIndexOfMobile)) {
          _tmpMobile = null;
        } else {
          _tmpMobile = _cursor.getString(_cursorIndexOfMobile);
        }
        _item.setMobile(_tmpMobile);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final String _tmpBlock;
        if (_cursor.isNull(_cursorIndexOfBlock)) {
          _tmpBlock = null;
        } else {
          _tmpBlock = _cursor.getString(_cursorIndexOfBlock);
        }
        _item.setBlock(_tmpBlock);
        final String _tmpDistrict;
        if (_cursor.isNull(_cursorIndexOfDistrict)) {
          _tmpDistrict = null;
        } else {
          _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
        }
        _item.setDistrict(_tmpDistrict);
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        _item.setUserName(_tmpUserName);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _item.setPassword(_tmpPassword);
        final String _tmpReportingPersonId;
        if (_cursor.isNull(_cursorIndexOfReportingPersonId)) {
          _tmpReportingPersonId = null;
        } else {
          _tmpReportingPersonId = _cursor.getString(_cursorIndexOfReportingPersonId);
        }
        _item.setReportingPersonId(_tmpReportingPersonId);
        final String _tmpReportingPersonName;
        if (_cursor.isNull(_cursorIndexOfReportingPersonName)) {
          _tmpReportingPersonName = null;
        } else {
          _tmpReportingPersonName = _cursor.getString(_cursorIndexOfReportingPersonName);
        }
        _item.setReportingPersonName(_tmpReportingPersonName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<CRL> getCRLsByBlockAndRole(final String blockName, final String roleName,
      final String rptId) {
    final String _sql = "SELECT * FROM CRL where Block=? AND RoleName=? AND ReportingPersonId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (blockName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, blockName);
    }
    _argIndex = 2;
    if (roleName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, roleName);
    }
    _argIndex = 3;
    if (rptId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, rptId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCRLId = CursorUtil.getColumnIndexOrThrow(_cursor, "CRLId");
      final int _cursorIndexOfRoleId = CursorUtil.getColumnIndexOrThrow(_cursor, "RoleId");
      final int _cursorIndexOfRoleName = CursorUtil.getColumnIndexOrThrow(_cursor, "RoleName");
      final int _cursorIndexOfProgramId = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramId");
      final int _cursorIndexOfProgramName = CursorUtil.getColumnIndexOrThrow(_cursor, "ProgramName");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "State");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfMobile = CursorUtil.getColumnIndexOrThrow(_cursor, "Mobile");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
      final int _cursorIndexOfBlock = CursorUtil.getColumnIndexOrThrow(_cursor, "Block");
      final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "District");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "UserName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "Password");
      final int _cursorIndexOfReportingPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "ReportingPersonId");
      final int _cursorIndexOfReportingPersonName = CursorUtil.getColumnIndexOrThrow(_cursor, "ReportingPersonName");
      final List<CRL> _result = new ArrayList<CRL>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CRL _item;
        _item = new CRL();
        final String _tmpCRLId;
        if (_cursor.isNull(_cursorIndexOfCRLId)) {
          _tmpCRLId = null;
        } else {
          _tmpCRLId = _cursor.getString(_cursorIndexOfCRLId);
        }
        _item.setCRLId(_tmpCRLId);
        final String _tmpRoleId;
        if (_cursor.isNull(_cursorIndexOfRoleId)) {
          _tmpRoleId = null;
        } else {
          _tmpRoleId = _cursor.getString(_cursorIndexOfRoleId);
        }
        _item.setRoleId(_tmpRoleId);
        final String _tmpRoleName;
        if (_cursor.isNull(_cursorIndexOfRoleName)) {
          _tmpRoleName = null;
        } else {
          _tmpRoleName = _cursor.getString(_cursorIndexOfRoleName);
        }
        _item.setRoleName(_tmpRoleName);
        final String _tmpProgramId;
        if (_cursor.isNull(_cursorIndexOfProgramId)) {
          _tmpProgramId = null;
        } else {
          _tmpProgramId = _cursor.getString(_cursorIndexOfProgramId);
        }
        _item.setProgramId(_tmpProgramId);
        final String _tmpProgramName;
        if (_cursor.isNull(_cursorIndexOfProgramName)) {
          _tmpProgramName = null;
        } else {
          _tmpProgramName = _cursor.getString(_cursorIndexOfProgramName);
        }
        _item.setProgramName(_tmpProgramName);
        final String _tmpState;
        if (_cursor.isNull(_cursorIndexOfState)) {
          _tmpState = null;
        } else {
          _tmpState = _cursor.getString(_cursorIndexOfState);
        }
        _item.setState(_tmpState);
        final String _tmpFirstName;
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _tmpFirstName = null;
        } else {
          _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        _item.setFirstName(_tmpFirstName);
        final String _tmpLastName;
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _tmpLastName = null;
        } else {
          _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
        }
        _item.setLastName(_tmpLastName);
        final String _tmpMobile;
        if (_cursor.isNull(_cursorIndexOfMobile)) {
          _tmpMobile = null;
        } else {
          _tmpMobile = _cursor.getString(_cursorIndexOfMobile);
        }
        _item.setMobile(_tmpMobile);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final String _tmpBlock;
        if (_cursor.isNull(_cursorIndexOfBlock)) {
          _tmpBlock = null;
        } else {
          _tmpBlock = _cursor.getString(_cursorIndexOfBlock);
        }
        _item.setBlock(_tmpBlock);
        final String _tmpDistrict;
        if (_cursor.isNull(_cursorIndexOfDistrict)) {
          _tmpDistrict = null;
        } else {
          _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
        }
        _item.setDistrict(_tmpDistrict);
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        _item.setUserName(_tmpUserName);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _item.setPassword(_tmpPassword);
        final String _tmpReportingPersonId;
        if (_cursor.isNull(_cursorIndexOfReportingPersonId)) {
          _tmpReportingPersonId = null;
        } else {
          _tmpReportingPersonId = _cursor.getString(_cursorIndexOfReportingPersonId);
        }
        _item.setReportingPersonId(_tmpReportingPersonId);
        final String _tmpReportingPersonName;
        if (_cursor.isNull(_cursorIndexOfReportingPersonName)) {
          _tmpReportingPersonName = null;
        } else {
          _tmpReportingPersonName = _cursor.getString(_cursorIndexOfReportingPersonName);
        }
        _item.setReportingPersonName(_tmpReportingPersonName);
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
