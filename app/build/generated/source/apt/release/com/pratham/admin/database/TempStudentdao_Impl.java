package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.TempStudent;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TempStudentdao_Impl implements TempStudentdao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TempStudent> __insertionAdapterOfTempStudent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTempStudent;

  public TempStudentdao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTempStudent = new EntityInsertionAdapter<TempStudent>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TempStudent` (`GroupId`,`GroupName`,`FullName`,`Stud_Class`,`Age`,`Gender`,`StudentId`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TempStudent value) {
        if (value.getGroupId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getGroupId());
        }
        if (value.getGroupName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getGroupName());
        }
        if (value.getFullName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFullName());
        }
        if (value.getStud_Class() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getStud_Class());
        }
        if (value.getAge() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAge());
        }
        if (value.getGender() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getGender());
        }
        if (value.getStudentId() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getStudentId());
        }
      }
    };
    this.__preparedStmtOfDeleteTempStudent = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TempStudent";
        return _query;
      }
    };
  }

  @Override
  public void insertTempStudent(final List<TempStudent> tempChangesList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTempStudent.insert(tempChangesList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTempStudent() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTempStudent.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteTempStudent.release(_stmt);
    }
  }

  @Override
  public List<TempStudent> getAllempStudent() {
    final String _sql = "SELECT * FROM TempStudent";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfStudClass = CursorUtil.getColumnIndexOrThrow(_cursor, "Stud_Class");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "Age");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "Gender");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final List<TempStudent> _result = new ArrayList<TempStudent>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TempStudent _item;
        _item = new TempStudent();
        final String _tmpGroupId;
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _tmpGroupId = null;
        } else {
          _tmpGroupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        _item.setGroupId(_tmpGroupId);
        final String _tmpGroupName;
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _tmpGroupName = null;
        } else {
          _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        _item.setGroupName(_tmpGroupName);
        final String _tmpFullName;
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _tmpFullName = null;
        } else {
          _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        }
        _item.setFullName(_tmpFullName);
        final String _tmpStud_Class;
        if (_cursor.isNull(_cursorIndexOfStudClass)) {
          _tmpStud_Class = null;
        } else {
          _tmpStud_Class = _cursor.getString(_cursorIndexOfStudClass);
        }
        _item.setStud_Class(_tmpStud_Class);
        final String _tmpAge;
        if (_cursor.isNull(_cursorIndexOfAge)) {
          _tmpAge = null;
        } else {
          _tmpAge = _cursor.getString(_cursorIndexOfAge);
        }
        _item.setAge(_tmpAge);
        final String _tmpGender;
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _tmpGender = null;
        } else {
          _tmpGender = _cursor.getString(_cursorIndexOfGender);
        }
        _item.setGender(_tmpGender);
        final String _tmpStudentId;
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _tmpStudentId = null;
        } else {
          _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        _item.setStudentId(_tmpStudentId);
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
