package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.TabletManageDevice;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TabletManageDeviceDao_Impl implements TabletManageDeviceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TabletManageDevice> __insertionAdapterOfTabletManageDevice;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTabletManageDevice;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllTabletManageDevice;

  private final SharedSQLiteStatement __preparedStmtOfUpdateIsPushedFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAssignAndReturnIsPushedFlag;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAssignAndReturnDevice;

  private final SharedSQLiteStatement __preparedStmtOfDeleteReplaceDevice;

  private final SharedSQLiteStatement __preparedStmtOfUpdateReplaceIsPushedFlag;

  public TabletManageDeviceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTabletManageDevice = new EntityInsertionAdapter<TabletManageDevice>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TabletManageDevice` (`id`,`QR_ID`,`Pratham_ID`,`tabSerial_ID`,`date`,`assigned_CRL_ID`,`assigned_CRL_Name`,`logged_CRL_ID`,`logged_CRL_NAME`,`collectedTabPrathamID`,`collectedTabQrID`,`collectedTab_serial_ID`,`collectedTabs_senior`,`collected_date`,`is_Damaged`,`damageType`,`status`,`comment`,`oldFlag`,`villageName`,`villageID`,`isPushed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TabletManageDevice value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getQR_ID() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getQR_ID());
        }
        if (value.getPratham_ID() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPratham_ID());
        }
        if (value.getTabSerial_ID() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTabSerial_ID());
        }
        if (value.getDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDate());
        }
        if (value.getAssigned_CRL_ID() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAssigned_CRL_ID());
        }
        if (value.getAssigned_CRL_Name() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAssigned_CRL_Name());
        }
        if (value.getLogged_CRL_ID() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getLogged_CRL_ID());
        }
        if (value.getLogged_CRL_NAME() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getLogged_CRL_NAME());
        }
        if (value.getCollectedTabPrathamID() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getCollectedTabPrathamID());
        }
        if (value.getCollectedTabQrID() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getCollectedTabQrID());
        }
        if (value.getCollectedTab_serial_ID() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getCollectedTab_serial_ID());
        }
        if (value.getCollectedTabs_senior() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getCollectedTabs_senior());
        }
        if (value.getCollected_date() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getCollected_date());
        }
        if (value.getIs_Damaged() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getIs_Damaged());
        }
        if (value.getDamageType() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getDamageType());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.getStatus());
        }
        if (value.getComment() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.getComment());
        }
        final int _tmp;
        _tmp = value.getOldFlag() ? 1 : 0;
        stmt.bindLong(19, _tmp);
        if (value.getVillageName() == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.getVillageName());
        }
        if (value.getVillageID() == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.getVillageID());
        }
        stmt.bindLong(22, value.getIsPushed());
      }
    };
    this.__preparedStmtOfDeleteTabletManageDevice = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TabletManageDevice WHERE QR_ID=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllTabletManageDevice = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TabletManageDevice";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateIsPushedFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update TabletManageDevice set isPushed=1";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAssignAndReturnIsPushedFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update TabletManageDevice set isPushed=1 WHERE (status='Assign' OR status='Return')";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAssignAndReturnDevice = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TabletManageDevice  WHERE (status='Assign' OR status='Return')";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteReplaceDevice = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TabletManageDevice WHERE isPushed=0 AND (status='Replace_assign' OR status='Replace_collect')";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateReplaceIsPushedFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update TabletManageDevice set isPushed=1 WHERE (status='Replace_assign' OR status='Replace_collect')";
        return _query;
      }
    };
  }

  @Override
  public long insertTabletManageDevice(final TabletManageDevice tabletManageDevice) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfTabletManageDevice.insertAndReturnId(tabletManageDevice);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertTabletAllManageDevice(final List<TabletManageDevice> tabletManageDevices) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTabletManageDevice.insert(tabletManageDevices);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTabletManageDevice(final String qrId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTabletManageDevice.acquire();
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
      __preparedStmtOfDeleteTabletManageDevice.release(_stmt);
    }
  }

  @Override
  public void deleteAllTabletManageDevice() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllTabletManageDevice.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllTabletManageDevice.release(_stmt);
    }
  }

  @Override
  public void updateIsPushedFlag() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateIsPushedFlag.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateIsPushedFlag.release(_stmt);
    }
  }

  @Override
  public void updateAssignAndReturnIsPushedFlag() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAssignAndReturnIsPushedFlag.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateAssignAndReturnIsPushedFlag.release(_stmt);
    }
  }

  @Override
  public void deleteAssignAndReturnDevice() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAssignAndReturnDevice.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAssignAndReturnDevice.release(_stmt);
    }
  }

  @Override
  public void deleteReplaceDevice() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteReplaceDevice.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteReplaceDevice.release(_stmt);
    }
  }

  @Override
  public void updateReplaceIsPushedFlag() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateReplaceIsPushedFlag.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateReplaceIsPushedFlag.release(_stmt);
    }
  }

  @Override
  public List<TabletManageDevice> getAllTabletManageDevice() {
    final String _sql = "SELECT * FROM TabletManageDevice WHERE isPushed=0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfQRID = CursorUtil.getColumnIndexOrThrow(_cursor, "QR_ID");
      final int _cursorIndexOfPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "Pratham_ID");
      final int _cursorIndexOfTabSerialID = CursorUtil.getColumnIndexOrThrow(_cursor, "tabSerial_ID");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfAssignedCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "assigned_CRL_ID");
      final int _cursorIndexOfAssignedCRLName = CursorUtil.getColumnIndexOrThrow(_cursor, "assigned_CRL_Name");
      final int _cursorIndexOfLoggedCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "logged_CRL_ID");
      final int _cursorIndexOfLoggedCRLNAME = CursorUtil.getColumnIndexOrThrow(_cursor, "logged_CRL_NAME");
      final int _cursorIndexOfCollectedTabPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabPrathamID");
      final int _cursorIndexOfCollectedTabQrID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabQrID");
      final int _cursorIndexOfCollectedTabSerialID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTab_serial_ID");
      final int _cursorIndexOfCollectedTabsSenior = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabs_senior");
      final int _cursorIndexOfCollectedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "collected_date");
      final int _cursorIndexOfIsDamaged = CursorUtil.getColumnIndexOrThrow(_cursor, "is_Damaged");
      final int _cursorIndexOfDamageType = CursorUtil.getColumnIndexOrThrow(_cursor, "damageType");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final int _cursorIndexOfOldFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "oldFlag");
      final int _cursorIndexOfVillageName = CursorUtil.getColumnIndexOrThrow(_cursor, "villageName");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "villageID");
      final int _cursorIndexOfIsPushed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPushed");
      final List<TabletManageDevice> _result = new ArrayList<TabletManageDevice>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TabletManageDevice _item;
        _item = new TabletManageDevice();
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final String _tmpQR_ID;
        if (_cursor.isNull(_cursorIndexOfQRID)) {
          _tmpQR_ID = null;
        } else {
          _tmpQR_ID = _cursor.getString(_cursorIndexOfQRID);
        }
        _item.setQR_ID(_tmpQR_ID);
        final String _tmpPratham_ID;
        if (_cursor.isNull(_cursorIndexOfPrathamID)) {
          _tmpPratham_ID = null;
        } else {
          _tmpPratham_ID = _cursor.getString(_cursorIndexOfPrathamID);
        }
        _item.setPratham_ID(_tmpPratham_ID);
        final String _tmpTabSerial_ID;
        if (_cursor.isNull(_cursorIndexOfTabSerialID)) {
          _tmpTabSerial_ID = null;
        } else {
          _tmpTabSerial_ID = _cursor.getString(_cursorIndexOfTabSerialID);
        }
        _item.setTabSerial_ID(_tmpTabSerial_ID);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        _item.setDate(_tmpDate);
        final String _tmpAssigned_CRL_ID;
        if (_cursor.isNull(_cursorIndexOfAssignedCRLID)) {
          _tmpAssigned_CRL_ID = null;
        } else {
          _tmpAssigned_CRL_ID = _cursor.getString(_cursorIndexOfAssignedCRLID);
        }
        _item.setAssigned_CRL_ID(_tmpAssigned_CRL_ID);
        final String _tmpAssigned_CRL_Name;
        if (_cursor.isNull(_cursorIndexOfAssignedCRLName)) {
          _tmpAssigned_CRL_Name = null;
        } else {
          _tmpAssigned_CRL_Name = _cursor.getString(_cursorIndexOfAssignedCRLName);
        }
        _item.setAssigned_CRL_Name(_tmpAssigned_CRL_Name);
        final String _tmpLogged_CRL_ID;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLID)) {
          _tmpLogged_CRL_ID = null;
        } else {
          _tmpLogged_CRL_ID = _cursor.getString(_cursorIndexOfLoggedCRLID);
        }
        _item.setLogged_CRL_ID(_tmpLogged_CRL_ID);
        final String _tmpLogged_CRL_NAME;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLNAME)) {
          _tmpLogged_CRL_NAME = null;
        } else {
          _tmpLogged_CRL_NAME = _cursor.getString(_cursorIndexOfLoggedCRLNAME);
        }
        _item.setLogged_CRL_NAME(_tmpLogged_CRL_NAME);
        final String _tmpCollectedTabPrathamID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabPrathamID)) {
          _tmpCollectedTabPrathamID = null;
        } else {
          _tmpCollectedTabPrathamID = _cursor.getString(_cursorIndexOfCollectedTabPrathamID);
        }
        _item.setCollectedTabPrathamID(_tmpCollectedTabPrathamID);
        final String _tmpCollectedTabQrID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabQrID)) {
          _tmpCollectedTabQrID = null;
        } else {
          _tmpCollectedTabQrID = _cursor.getString(_cursorIndexOfCollectedTabQrID);
        }
        _item.setCollectedTabQrID(_tmpCollectedTabQrID);
        final String _tmpCollectedTab_serial_ID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabSerialID)) {
          _tmpCollectedTab_serial_ID = null;
        } else {
          _tmpCollectedTab_serial_ID = _cursor.getString(_cursorIndexOfCollectedTabSerialID);
        }
        _item.setCollectedTab_serial_ID(_tmpCollectedTab_serial_ID);
        final String _tmpCollectedTabs_senior;
        if (_cursor.isNull(_cursorIndexOfCollectedTabsSenior)) {
          _tmpCollectedTabs_senior = null;
        } else {
          _tmpCollectedTabs_senior = _cursor.getString(_cursorIndexOfCollectedTabsSenior);
        }
        _item.setCollectedTabs_senior(_tmpCollectedTabs_senior);
        final String _tmpCollected_date;
        if (_cursor.isNull(_cursorIndexOfCollectedDate)) {
          _tmpCollected_date = null;
        } else {
          _tmpCollected_date = _cursor.getString(_cursorIndexOfCollectedDate);
        }
        _item.setCollected_date(_tmpCollected_date);
        final String _tmpIs_Damaged;
        if (_cursor.isNull(_cursorIndexOfIsDamaged)) {
          _tmpIs_Damaged = null;
        } else {
          _tmpIs_Damaged = _cursor.getString(_cursorIndexOfIsDamaged);
        }
        _item.setIs_Damaged(_tmpIs_Damaged);
        final String _tmpDamageType;
        if (_cursor.isNull(_cursorIndexOfDamageType)) {
          _tmpDamageType = null;
        } else {
          _tmpDamageType = _cursor.getString(_cursorIndexOfDamageType);
        }
        _item.setDamageType(_tmpDamageType);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
        final String _tmpComment;
        if (_cursor.isNull(_cursorIndexOfComment)) {
          _tmpComment = null;
        } else {
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
        }
        _item.setComment(_tmpComment);
        final boolean _tmpOldFlag;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfOldFlag);
        _tmpOldFlag = _tmp != 0;
        _item.setOldFlag(_tmpOldFlag);
        final String _tmpVillageName;
        if (_cursor.isNull(_cursorIndexOfVillageName)) {
          _tmpVillageName = null;
        } else {
          _tmpVillageName = _cursor.getString(_cursorIndexOfVillageName);
        }
        _item.setVillageName(_tmpVillageName);
        final String _tmpVillageID;
        if (_cursor.isNull(_cursorIndexOfVillageID)) {
          _tmpVillageID = null;
        } else {
          _tmpVillageID = _cursor.getString(_cursorIndexOfVillageID);
        }
        _item.setVillageID(_tmpVillageID);
        final int _tmpIsPushed;
        _tmpIsPushed = _cursor.getInt(_cursorIndexOfIsPushed);
        _item.setIsPushed(_tmpIsPushed);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TabletManageDevice> getAllAssignAndReturnDevice() {
    final String _sql = "SELECT * FROM TabletManageDevice WHERE isPushed=0 AND (status='Assign' OR status='Return')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfQRID = CursorUtil.getColumnIndexOrThrow(_cursor, "QR_ID");
      final int _cursorIndexOfPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "Pratham_ID");
      final int _cursorIndexOfTabSerialID = CursorUtil.getColumnIndexOrThrow(_cursor, "tabSerial_ID");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfAssignedCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "assigned_CRL_ID");
      final int _cursorIndexOfAssignedCRLName = CursorUtil.getColumnIndexOrThrow(_cursor, "assigned_CRL_Name");
      final int _cursorIndexOfLoggedCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "logged_CRL_ID");
      final int _cursorIndexOfLoggedCRLNAME = CursorUtil.getColumnIndexOrThrow(_cursor, "logged_CRL_NAME");
      final int _cursorIndexOfCollectedTabPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabPrathamID");
      final int _cursorIndexOfCollectedTabQrID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabQrID");
      final int _cursorIndexOfCollectedTabSerialID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTab_serial_ID");
      final int _cursorIndexOfCollectedTabsSenior = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabs_senior");
      final int _cursorIndexOfCollectedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "collected_date");
      final int _cursorIndexOfIsDamaged = CursorUtil.getColumnIndexOrThrow(_cursor, "is_Damaged");
      final int _cursorIndexOfDamageType = CursorUtil.getColumnIndexOrThrow(_cursor, "damageType");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final int _cursorIndexOfOldFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "oldFlag");
      final int _cursorIndexOfVillageName = CursorUtil.getColumnIndexOrThrow(_cursor, "villageName");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "villageID");
      final int _cursorIndexOfIsPushed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPushed");
      final List<TabletManageDevice> _result = new ArrayList<TabletManageDevice>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TabletManageDevice _item;
        _item = new TabletManageDevice();
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final String _tmpQR_ID;
        if (_cursor.isNull(_cursorIndexOfQRID)) {
          _tmpQR_ID = null;
        } else {
          _tmpQR_ID = _cursor.getString(_cursorIndexOfQRID);
        }
        _item.setQR_ID(_tmpQR_ID);
        final String _tmpPratham_ID;
        if (_cursor.isNull(_cursorIndexOfPrathamID)) {
          _tmpPratham_ID = null;
        } else {
          _tmpPratham_ID = _cursor.getString(_cursorIndexOfPrathamID);
        }
        _item.setPratham_ID(_tmpPratham_ID);
        final String _tmpTabSerial_ID;
        if (_cursor.isNull(_cursorIndexOfTabSerialID)) {
          _tmpTabSerial_ID = null;
        } else {
          _tmpTabSerial_ID = _cursor.getString(_cursorIndexOfTabSerialID);
        }
        _item.setTabSerial_ID(_tmpTabSerial_ID);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        _item.setDate(_tmpDate);
        final String _tmpAssigned_CRL_ID;
        if (_cursor.isNull(_cursorIndexOfAssignedCRLID)) {
          _tmpAssigned_CRL_ID = null;
        } else {
          _tmpAssigned_CRL_ID = _cursor.getString(_cursorIndexOfAssignedCRLID);
        }
        _item.setAssigned_CRL_ID(_tmpAssigned_CRL_ID);
        final String _tmpAssigned_CRL_Name;
        if (_cursor.isNull(_cursorIndexOfAssignedCRLName)) {
          _tmpAssigned_CRL_Name = null;
        } else {
          _tmpAssigned_CRL_Name = _cursor.getString(_cursorIndexOfAssignedCRLName);
        }
        _item.setAssigned_CRL_Name(_tmpAssigned_CRL_Name);
        final String _tmpLogged_CRL_ID;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLID)) {
          _tmpLogged_CRL_ID = null;
        } else {
          _tmpLogged_CRL_ID = _cursor.getString(_cursorIndexOfLoggedCRLID);
        }
        _item.setLogged_CRL_ID(_tmpLogged_CRL_ID);
        final String _tmpLogged_CRL_NAME;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLNAME)) {
          _tmpLogged_CRL_NAME = null;
        } else {
          _tmpLogged_CRL_NAME = _cursor.getString(_cursorIndexOfLoggedCRLNAME);
        }
        _item.setLogged_CRL_NAME(_tmpLogged_CRL_NAME);
        final String _tmpCollectedTabPrathamID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabPrathamID)) {
          _tmpCollectedTabPrathamID = null;
        } else {
          _tmpCollectedTabPrathamID = _cursor.getString(_cursorIndexOfCollectedTabPrathamID);
        }
        _item.setCollectedTabPrathamID(_tmpCollectedTabPrathamID);
        final String _tmpCollectedTabQrID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabQrID)) {
          _tmpCollectedTabQrID = null;
        } else {
          _tmpCollectedTabQrID = _cursor.getString(_cursorIndexOfCollectedTabQrID);
        }
        _item.setCollectedTabQrID(_tmpCollectedTabQrID);
        final String _tmpCollectedTab_serial_ID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabSerialID)) {
          _tmpCollectedTab_serial_ID = null;
        } else {
          _tmpCollectedTab_serial_ID = _cursor.getString(_cursorIndexOfCollectedTabSerialID);
        }
        _item.setCollectedTab_serial_ID(_tmpCollectedTab_serial_ID);
        final String _tmpCollectedTabs_senior;
        if (_cursor.isNull(_cursorIndexOfCollectedTabsSenior)) {
          _tmpCollectedTabs_senior = null;
        } else {
          _tmpCollectedTabs_senior = _cursor.getString(_cursorIndexOfCollectedTabsSenior);
        }
        _item.setCollectedTabs_senior(_tmpCollectedTabs_senior);
        final String _tmpCollected_date;
        if (_cursor.isNull(_cursorIndexOfCollectedDate)) {
          _tmpCollected_date = null;
        } else {
          _tmpCollected_date = _cursor.getString(_cursorIndexOfCollectedDate);
        }
        _item.setCollected_date(_tmpCollected_date);
        final String _tmpIs_Damaged;
        if (_cursor.isNull(_cursorIndexOfIsDamaged)) {
          _tmpIs_Damaged = null;
        } else {
          _tmpIs_Damaged = _cursor.getString(_cursorIndexOfIsDamaged);
        }
        _item.setIs_Damaged(_tmpIs_Damaged);
        final String _tmpDamageType;
        if (_cursor.isNull(_cursorIndexOfDamageType)) {
          _tmpDamageType = null;
        } else {
          _tmpDamageType = _cursor.getString(_cursorIndexOfDamageType);
        }
        _item.setDamageType(_tmpDamageType);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
        final String _tmpComment;
        if (_cursor.isNull(_cursorIndexOfComment)) {
          _tmpComment = null;
        } else {
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
        }
        _item.setComment(_tmpComment);
        final boolean _tmpOldFlag;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfOldFlag);
        _tmpOldFlag = _tmp != 0;
        _item.setOldFlag(_tmpOldFlag);
        final String _tmpVillageName;
        if (_cursor.isNull(_cursorIndexOfVillageName)) {
          _tmpVillageName = null;
        } else {
          _tmpVillageName = _cursor.getString(_cursorIndexOfVillageName);
        }
        _item.setVillageName(_tmpVillageName);
        final String _tmpVillageID;
        if (_cursor.isNull(_cursorIndexOfVillageID)) {
          _tmpVillageID = null;
        } else {
          _tmpVillageID = _cursor.getString(_cursorIndexOfVillageID);
        }
        _item.setVillageID(_tmpVillageID);
        final int _tmpIsPushed;
        _tmpIsPushed = _cursor.getInt(_cursorIndexOfIsPushed);
        _item.setIsPushed(_tmpIsPushed);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TabletManageDevice> getAllReplaceDevice() {
    final String _sql = "SELECT * FROM TabletManageDevice WHERE isPushed=0 AND (status='Replace_assign' OR status='Replace_collect')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfQRID = CursorUtil.getColumnIndexOrThrow(_cursor, "QR_ID");
      final int _cursorIndexOfPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "Pratham_ID");
      final int _cursorIndexOfTabSerialID = CursorUtil.getColumnIndexOrThrow(_cursor, "tabSerial_ID");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfAssignedCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "assigned_CRL_ID");
      final int _cursorIndexOfAssignedCRLName = CursorUtil.getColumnIndexOrThrow(_cursor, "assigned_CRL_Name");
      final int _cursorIndexOfLoggedCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "logged_CRL_ID");
      final int _cursorIndexOfLoggedCRLNAME = CursorUtil.getColumnIndexOrThrow(_cursor, "logged_CRL_NAME");
      final int _cursorIndexOfCollectedTabPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabPrathamID");
      final int _cursorIndexOfCollectedTabQrID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabQrID");
      final int _cursorIndexOfCollectedTabSerialID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTab_serial_ID");
      final int _cursorIndexOfCollectedTabsSenior = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabs_senior");
      final int _cursorIndexOfCollectedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "collected_date");
      final int _cursorIndexOfIsDamaged = CursorUtil.getColumnIndexOrThrow(_cursor, "is_Damaged");
      final int _cursorIndexOfDamageType = CursorUtil.getColumnIndexOrThrow(_cursor, "damageType");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final int _cursorIndexOfOldFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "oldFlag");
      final int _cursorIndexOfVillageName = CursorUtil.getColumnIndexOrThrow(_cursor, "villageName");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "villageID");
      final int _cursorIndexOfIsPushed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPushed");
      final List<TabletManageDevice> _result = new ArrayList<TabletManageDevice>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TabletManageDevice _item;
        _item = new TabletManageDevice();
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final String _tmpQR_ID;
        if (_cursor.isNull(_cursorIndexOfQRID)) {
          _tmpQR_ID = null;
        } else {
          _tmpQR_ID = _cursor.getString(_cursorIndexOfQRID);
        }
        _item.setQR_ID(_tmpQR_ID);
        final String _tmpPratham_ID;
        if (_cursor.isNull(_cursorIndexOfPrathamID)) {
          _tmpPratham_ID = null;
        } else {
          _tmpPratham_ID = _cursor.getString(_cursorIndexOfPrathamID);
        }
        _item.setPratham_ID(_tmpPratham_ID);
        final String _tmpTabSerial_ID;
        if (_cursor.isNull(_cursorIndexOfTabSerialID)) {
          _tmpTabSerial_ID = null;
        } else {
          _tmpTabSerial_ID = _cursor.getString(_cursorIndexOfTabSerialID);
        }
        _item.setTabSerial_ID(_tmpTabSerial_ID);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        _item.setDate(_tmpDate);
        final String _tmpAssigned_CRL_ID;
        if (_cursor.isNull(_cursorIndexOfAssignedCRLID)) {
          _tmpAssigned_CRL_ID = null;
        } else {
          _tmpAssigned_CRL_ID = _cursor.getString(_cursorIndexOfAssignedCRLID);
        }
        _item.setAssigned_CRL_ID(_tmpAssigned_CRL_ID);
        final String _tmpAssigned_CRL_Name;
        if (_cursor.isNull(_cursorIndexOfAssignedCRLName)) {
          _tmpAssigned_CRL_Name = null;
        } else {
          _tmpAssigned_CRL_Name = _cursor.getString(_cursorIndexOfAssignedCRLName);
        }
        _item.setAssigned_CRL_Name(_tmpAssigned_CRL_Name);
        final String _tmpLogged_CRL_ID;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLID)) {
          _tmpLogged_CRL_ID = null;
        } else {
          _tmpLogged_CRL_ID = _cursor.getString(_cursorIndexOfLoggedCRLID);
        }
        _item.setLogged_CRL_ID(_tmpLogged_CRL_ID);
        final String _tmpLogged_CRL_NAME;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLNAME)) {
          _tmpLogged_CRL_NAME = null;
        } else {
          _tmpLogged_CRL_NAME = _cursor.getString(_cursorIndexOfLoggedCRLNAME);
        }
        _item.setLogged_CRL_NAME(_tmpLogged_CRL_NAME);
        final String _tmpCollectedTabPrathamID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabPrathamID)) {
          _tmpCollectedTabPrathamID = null;
        } else {
          _tmpCollectedTabPrathamID = _cursor.getString(_cursorIndexOfCollectedTabPrathamID);
        }
        _item.setCollectedTabPrathamID(_tmpCollectedTabPrathamID);
        final String _tmpCollectedTabQrID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabQrID)) {
          _tmpCollectedTabQrID = null;
        } else {
          _tmpCollectedTabQrID = _cursor.getString(_cursorIndexOfCollectedTabQrID);
        }
        _item.setCollectedTabQrID(_tmpCollectedTabQrID);
        final String _tmpCollectedTab_serial_ID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabSerialID)) {
          _tmpCollectedTab_serial_ID = null;
        } else {
          _tmpCollectedTab_serial_ID = _cursor.getString(_cursorIndexOfCollectedTabSerialID);
        }
        _item.setCollectedTab_serial_ID(_tmpCollectedTab_serial_ID);
        final String _tmpCollectedTabs_senior;
        if (_cursor.isNull(_cursorIndexOfCollectedTabsSenior)) {
          _tmpCollectedTabs_senior = null;
        } else {
          _tmpCollectedTabs_senior = _cursor.getString(_cursorIndexOfCollectedTabsSenior);
        }
        _item.setCollectedTabs_senior(_tmpCollectedTabs_senior);
        final String _tmpCollected_date;
        if (_cursor.isNull(_cursorIndexOfCollectedDate)) {
          _tmpCollected_date = null;
        } else {
          _tmpCollected_date = _cursor.getString(_cursorIndexOfCollectedDate);
        }
        _item.setCollected_date(_tmpCollected_date);
        final String _tmpIs_Damaged;
        if (_cursor.isNull(_cursorIndexOfIsDamaged)) {
          _tmpIs_Damaged = null;
        } else {
          _tmpIs_Damaged = _cursor.getString(_cursorIndexOfIsDamaged);
        }
        _item.setIs_Damaged(_tmpIs_Damaged);
        final String _tmpDamageType;
        if (_cursor.isNull(_cursorIndexOfDamageType)) {
          _tmpDamageType = null;
        } else {
          _tmpDamageType = _cursor.getString(_cursorIndexOfDamageType);
        }
        _item.setDamageType(_tmpDamageType);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
        final String _tmpComment;
        if (_cursor.isNull(_cursorIndexOfComment)) {
          _tmpComment = null;
        } else {
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
        }
        _item.setComment(_tmpComment);
        final boolean _tmpOldFlag;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfOldFlag);
        _tmpOldFlag = _tmp != 0;
        _item.setOldFlag(_tmpOldFlag);
        final String _tmpVillageName;
        if (_cursor.isNull(_cursorIndexOfVillageName)) {
          _tmpVillageName = null;
        } else {
          _tmpVillageName = _cursor.getString(_cursorIndexOfVillageName);
        }
        _item.setVillageName(_tmpVillageName);
        final String _tmpVillageID;
        if (_cursor.isNull(_cursorIndexOfVillageID)) {
          _tmpVillageID = null;
        } else {
          _tmpVillageID = _cursor.getString(_cursorIndexOfVillageID);
        }
        _item.setVillageID(_tmpVillageID);
        final int _tmpIsPushed;
        _tmpIsPushed = _cursor.getInt(_cursorIndexOfIsPushed);
        _item.setIsPushed(_tmpIsPushed);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TabletManageDevice> checkExistanceTabletManageDevice(final String id) {
    final String _sql = "SELECT * FROM TabletManageDevice WHERE id=?";
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
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfQRID = CursorUtil.getColumnIndexOrThrow(_cursor, "QR_ID");
      final int _cursorIndexOfPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "Pratham_ID");
      final int _cursorIndexOfTabSerialID = CursorUtil.getColumnIndexOrThrow(_cursor, "tabSerial_ID");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfAssignedCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "assigned_CRL_ID");
      final int _cursorIndexOfAssignedCRLName = CursorUtil.getColumnIndexOrThrow(_cursor, "assigned_CRL_Name");
      final int _cursorIndexOfLoggedCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "logged_CRL_ID");
      final int _cursorIndexOfLoggedCRLNAME = CursorUtil.getColumnIndexOrThrow(_cursor, "logged_CRL_NAME");
      final int _cursorIndexOfCollectedTabPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabPrathamID");
      final int _cursorIndexOfCollectedTabQrID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabQrID");
      final int _cursorIndexOfCollectedTabSerialID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTab_serial_ID");
      final int _cursorIndexOfCollectedTabsSenior = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabs_senior");
      final int _cursorIndexOfCollectedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "collected_date");
      final int _cursorIndexOfIsDamaged = CursorUtil.getColumnIndexOrThrow(_cursor, "is_Damaged");
      final int _cursorIndexOfDamageType = CursorUtil.getColumnIndexOrThrow(_cursor, "damageType");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final int _cursorIndexOfOldFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "oldFlag");
      final int _cursorIndexOfVillageName = CursorUtil.getColumnIndexOrThrow(_cursor, "villageName");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "villageID");
      final int _cursorIndexOfIsPushed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPushed");
      final List<TabletManageDevice> _result = new ArrayList<TabletManageDevice>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TabletManageDevice _item;
        _item = new TabletManageDevice();
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final String _tmpQR_ID;
        if (_cursor.isNull(_cursorIndexOfQRID)) {
          _tmpQR_ID = null;
        } else {
          _tmpQR_ID = _cursor.getString(_cursorIndexOfQRID);
        }
        _item.setQR_ID(_tmpQR_ID);
        final String _tmpPratham_ID;
        if (_cursor.isNull(_cursorIndexOfPrathamID)) {
          _tmpPratham_ID = null;
        } else {
          _tmpPratham_ID = _cursor.getString(_cursorIndexOfPrathamID);
        }
        _item.setPratham_ID(_tmpPratham_ID);
        final String _tmpTabSerial_ID;
        if (_cursor.isNull(_cursorIndexOfTabSerialID)) {
          _tmpTabSerial_ID = null;
        } else {
          _tmpTabSerial_ID = _cursor.getString(_cursorIndexOfTabSerialID);
        }
        _item.setTabSerial_ID(_tmpTabSerial_ID);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        _item.setDate(_tmpDate);
        final String _tmpAssigned_CRL_ID;
        if (_cursor.isNull(_cursorIndexOfAssignedCRLID)) {
          _tmpAssigned_CRL_ID = null;
        } else {
          _tmpAssigned_CRL_ID = _cursor.getString(_cursorIndexOfAssignedCRLID);
        }
        _item.setAssigned_CRL_ID(_tmpAssigned_CRL_ID);
        final String _tmpAssigned_CRL_Name;
        if (_cursor.isNull(_cursorIndexOfAssignedCRLName)) {
          _tmpAssigned_CRL_Name = null;
        } else {
          _tmpAssigned_CRL_Name = _cursor.getString(_cursorIndexOfAssignedCRLName);
        }
        _item.setAssigned_CRL_Name(_tmpAssigned_CRL_Name);
        final String _tmpLogged_CRL_ID;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLID)) {
          _tmpLogged_CRL_ID = null;
        } else {
          _tmpLogged_CRL_ID = _cursor.getString(_cursorIndexOfLoggedCRLID);
        }
        _item.setLogged_CRL_ID(_tmpLogged_CRL_ID);
        final String _tmpLogged_CRL_NAME;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLNAME)) {
          _tmpLogged_CRL_NAME = null;
        } else {
          _tmpLogged_CRL_NAME = _cursor.getString(_cursorIndexOfLoggedCRLNAME);
        }
        _item.setLogged_CRL_NAME(_tmpLogged_CRL_NAME);
        final String _tmpCollectedTabPrathamID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabPrathamID)) {
          _tmpCollectedTabPrathamID = null;
        } else {
          _tmpCollectedTabPrathamID = _cursor.getString(_cursorIndexOfCollectedTabPrathamID);
        }
        _item.setCollectedTabPrathamID(_tmpCollectedTabPrathamID);
        final String _tmpCollectedTabQrID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabQrID)) {
          _tmpCollectedTabQrID = null;
        } else {
          _tmpCollectedTabQrID = _cursor.getString(_cursorIndexOfCollectedTabQrID);
        }
        _item.setCollectedTabQrID(_tmpCollectedTabQrID);
        final String _tmpCollectedTab_serial_ID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabSerialID)) {
          _tmpCollectedTab_serial_ID = null;
        } else {
          _tmpCollectedTab_serial_ID = _cursor.getString(_cursorIndexOfCollectedTabSerialID);
        }
        _item.setCollectedTab_serial_ID(_tmpCollectedTab_serial_ID);
        final String _tmpCollectedTabs_senior;
        if (_cursor.isNull(_cursorIndexOfCollectedTabsSenior)) {
          _tmpCollectedTabs_senior = null;
        } else {
          _tmpCollectedTabs_senior = _cursor.getString(_cursorIndexOfCollectedTabsSenior);
        }
        _item.setCollectedTabs_senior(_tmpCollectedTabs_senior);
        final String _tmpCollected_date;
        if (_cursor.isNull(_cursorIndexOfCollectedDate)) {
          _tmpCollected_date = null;
        } else {
          _tmpCollected_date = _cursor.getString(_cursorIndexOfCollectedDate);
        }
        _item.setCollected_date(_tmpCollected_date);
        final String _tmpIs_Damaged;
        if (_cursor.isNull(_cursorIndexOfIsDamaged)) {
          _tmpIs_Damaged = null;
        } else {
          _tmpIs_Damaged = _cursor.getString(_cursorIndexOfIsDamaged);
        }
        _item.setIs_Damaged(_tmpIs_Damaged);
        final String _tmpDamageType;
        if (_cursor.isNull(_cursorIndexOfDamageType)) {
          _tmpDamageType = null;
        } else {
          _tmpDamageType = _cursor.getString(_cursorIndexOfDamageType);
        }
        _item.setDamageType(_tmpDamageType);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
        final String _tmpComment;
        if (_cursor.isNull(_cursorIndexOfComment)) {
          _tmpComment = null;
        } else {
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
        }
        _item.setComment(_tmpComment);
        final boolean _tmpOldFlag;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfOldFlag);
        _tmpOldFlag = _tmp != 0;
        _item.setOldFlag(_tmpOldFlag);
        final String _tmpVillageName;
        if (_cursor.isNull(_cursorIndexOfVillageName)) {
          _tmpVillageName = null;
        } else {
          _tmpVillageName = _cursor.getString(_cursorIndexOfVillageName);
        }
        _item.setVillageName(_tmpVillageName);
        final String _tmpVillageID;
        if (_cursor.isNull(_cursorIndexOfVillageID)) {
          _tmpVillageID = null;
        } else {
          _tmpVillageID = _cursor.getString(_cursorIndexOfVillageID);
        }
        _item.setVillageID(_tmpVillageID);
        final int _tmpIsPushed;
        _tmpIsPushed = _cursor.getInt(_cursorIndexOfIsPushed);
        _item.setIsPushed(_tmpIsPushed);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TabletManageDevice> getCollectedTablist(final String crlID) {
    final String _sql = "SELECT * FROM TabletManageDevice WHERE logged_CRL_ID=? AND Pratham_ID='' AND tabSerial_ID=''";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (crlID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, crlID);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfQRID = CursorUtil.getColumnIndexOrThrow(_cursor, "QR_ID");
      final int _cursorIndexOfPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "Pratham_ID");
      final int _cursorIndexOfTabSerialID = CursorUtil.getColumnIndexOrThrow(_cursor, "tabSerial_ID");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfAssignedCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "assigned_CRL_ID");
      final int _cursorIndexOfAssignedCRLName = CursorUtil.getColumnIndexOrThrow(_cursor, "assigned_CRL_Name");
      final int _cursorIndexOfLoggedCRLID = CursorUtil.getColumnIndexOrThrow(_cursor, "logged_CRL_ID");
      final int _cursorIndexOfLoggedCRLNAME = CursorUtil.getColumnIndexOrThrow(_cursor, "logged_CRL_NAME");
      final int _cursorIndexOfCollectedTabPrathamID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabPrathamID");
      final int _cursorIndexOfCollectedTabQrID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabQrID");
      final int _cursorIndexOfCollectedTabSerialID = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTab_serial_ID");
      final int _cursorIndexOfCollectedTabsSenior = CursorUtil.getColumnIndexOrThrow(_cursor, "collectedTabs_senior");
      final int _cursorIndexOfCollectedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "collected_date");
      final int _cursorIndexOfIsDamaged = CursorUtil.getColumnIndexOrThrow(_cursor, "is_Damaged");
      final int _cursorIndexOfDamageType = CursorUtil.getColumnIndexOrThrow(_cursor, "damageType");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final int _cursorIndexOfOldFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "oldFlag");
      final int _cursorIndexOfVillageName = CursorUtil.getColumnIndexOrThrow(_cursor, "villageName");
      final int _cursorIndexOfVillageID = CursorUtil.getColumnIndexOrThrow(_cursor, "villageID");
      final int _cursorIndexOfIsPushed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPushed");
      final List<TabletManageDevice> _result = new ArrayList<TabletManageDevice>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TabletManageDevice _item;
        _item = new TabletManageDevice();
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        _item.setId(_tmpId);
        final String _tmpQR_ID;
        if (_cursor.isNull(_cursorIndexOfQRID)) {
          _tmpQR_ID = null;
        } else {
          _tmpQR_ID = _cursor.getString(_cursorIndexOfQRID);
        }
        _item.setQR_ID(_tmpQR_ID);
        final String _tmpPratham_ID;
        if (_cursor.isNull(_cursorIndexOfPrathamID)) {
          _tmpPratham_ID = null;
        } else {
          _tmpPratham_ID = _cursor.getString(_cursorIndexOfPrathamID);
        }
        _item.setPratham_ID(_tmpPratham_ID);
        final String _tmpTabSerial_ID;
        if (_cursor.isNull(_cursorIndexOfTabSerialID)) {
          _tmpTabSerial_ID = null;
        } else {
          _tmpTabSerial_ID = _cursor.getString(_cursorIndexOfTabSerialID);
        }
        _item.setTabSerial_ID(_tmpTabSerial_ID);
        final String _tmpDate;
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _tmpDate = null;
        } else {
          _tmpDate = _cursor.getString(_cursorIndexOfDate);
        }
        _item.setDate(_tmpDate);
        final String _tmpAssigned_CRL_ID;
        if (_cursor.isNull(_cursorIndexOfAssignedCRLID)) {
          _tmpAssigned_CRL_ID = null;
        } else {
          _tmpAssigned_CRL_ID = _cursor.getString(_cursorIndexOfAssignedCRLID);
        }
        _item.setAssigned_CRL_ID(_tmpAssigned_CRL_ID);
        final String _tmpAssigned_CRL_Name;
        if (_cursor.isNull(_cursorIndexOfAssignedCRLName)) {
          _tmpAssigned_CRL_Name = null;
        } else {
          _tmpAssigned_CRL_Name = _cursor.getString(_cursorIndexOfAssignedCRLName);
        }
        _item.setAssigned_CRL_Name(_tmpAssigned_CRL_Name);
        final String _tmpLogged_CRL_ID;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLID)) {
          _tmpLogged_CRL_ID = null;
        } else {
          _tmpLogged_CRL_ID = _cursor.getString(_cursorIndexOfLoggedCRLID);
        }
        _item.setLogged_CRL_ID(_tmpLogged_CRL_ID);
        final String _tmpLogged_CRL_NAME;
        if (_cursor.isNull(_cursorIndexOfLoggedCRLNAME)) {
          _tmpLogged_CRL_NAME = null;
        } else {
          _tmpLogged_CRL_NAME = _cursor.getString(_cursorIndexOfLoggedCRLNAME);
        }
        _item.setLogged_CRL_NAME(_tmpLogged_CRL_NAME);
        final String _tmpCollectedTabPrathamID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabPrathamID)) {
          _tmpCollectedTabPrathamID = null;
        } else {
          _tmpCollectedTabPrathamID = _cursor.getString(_cursorIndexOfCollectedTabPrathamID);
        }
        _item.setCollectedTabPrathamID(_tmpCollectedTabPrathamID);
        final String _tmpCollectedTabQrID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabQrID)) {
          _tmpCollectedTabQrID = null;
        } else {
          _tmpCollectedTabQrID = _cursor.getString(_cursorIndexOfCollectedTabQrID);
        }
        _item.setCollectedTabQrID(_tmpCollectedTabQrID);
        final String _tmpCollectedTab_serial_ID;
        if (_cursor.isNull(_cursorIndexOfCollectedTabSerialID)) {
          _tmpCollectedTab_serial_ID = null;
        } else {
          _tmpCollectedTab_serial_ID = _cursor.getString(_cursorIndexOfCollectedTabSerialID);
        }
        _item.setCollectedTab_serial_ID(_tmpCollectedTab_serial_ID);
        final String _tmpCollectedTabs_senior;
        if (_cursor.isNull(_cursorIndexOfCollectedTabsSenior)) {
          _tmpCollectedTabs_senior = null;
        } else {
          _tmpCollectedTabs_senior = _cursor.getString(_cursorIndexOfCollectedTabsSenior);
        }
        _item.setCollectedTabs_senior(_tmpCollectedTabs_senior);
        final String _tmpCollected_date;
        if (_cursor.isNull(_cursorIndexOfCollectedDate)) {
          _tmpCollected_date = null;
        } else {
          _tmpCollected_date = _cursor.getString(_cursorIndexOfCollectedDate);
        }
        _item.setCollected_date(_tmpCollected_date);
        final String _tmpIs_Damaged;
        if (_cursor.isNull(_cursorIndexOfIsDamaged)) {
          _tmpIs_Damaged = null;
        } else {
          _tmpIs_Damaged = _cursor.getString(_cursorIndexOfIsDamaged);
        }
        _item.setIs_Damaged(_tmpIs_Damaged);
        final String _tmpDamageType;
        if (_cursor.isNull(_cursorIndexOfDamageType)) {
          _tmpDamageType = null;
        } else {
          _tmpDamageType = _cursor.getString(_cursorIndexOfDamageType);
        }
        _item.setDamageType(_tmpDamageType);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
        final String _tmpComment;
        if (_cursor.isNull(_cursorIndexOfComment)) {
          _tmpComment = null;
        } else {
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
        }
        _item.setComment(_tmpComment);
        final boolean _tmpOldFlag;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfOldFlag);
        _tmpOldFlag = _tmp != 0;
        _item.setOldFlag(_tmpOldFlag);
        final String _tmpVillageName;
        if (_cursor.isNull(_cursorIndexOfVillageName)) {
          _tmpVillageName = null;
        } else {
          _tmpVillageName = _cursor.getString(_cursorIndexOfVillageName);
        }
        _item.setVillageName(_tmpVillageName);
        final String _tmpVillageID;
        if (_cursor.isNull(_cursorIndexOfVillageID)) {
          _tmpVillageID = null;
        } else {
          _tmpVillageID = _cursor.getString(_cursorIndexOfVillageID);
        }
        _item.setVillageID(_tmpVillageID);
        final int _tmpIsPushed;
        _tmpIsPushed = _cursor.getInt(_cursorIndexOfIsPushed);
        _item.setIsPushed(_tmpIsPushed);
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
