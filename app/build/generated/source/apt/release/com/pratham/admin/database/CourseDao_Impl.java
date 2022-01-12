package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Course;
import com.pratham.admin.modalclasses.JSONArrayToString;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CourseDao_Impl implements CourseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Course> __insertionAdapterOfCourse;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCourses;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  public CourseDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourse = new EntityInsertionAdapter<Course>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Course` (`CourseID`,`CourseName`,`CourseIdInPos`,`CourseCode`,`CourseSubject`,`CourseLang`,`isDelete`,`listTopic`,`sentFlag`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Course value) {
        if (value.CourseID == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.CourseID);
        }
        if (value.CourseName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.CourseName);
        }
        if (value.CourseIdInPos == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.CourseIdInPos);
        }
        if (value.CourseCode == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.CourseCode);
        }
        if (value.CourseSubject == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.CourseSubject);
        }
        if (value.CourseLang == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.CourseLang);
        }
        final int _tmp;
        _tmp = value.isDelete ? 1 : 0;
        stmt.bindLong(7, _tmp);
        final String _tmp_1;
        _tmp_1 = JSONArrayToString.JSONArrayToString(value.listTopic);
        if (_tmp_1 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, _tmp_1);
        }
        stmt.bindLong(9, value.sentFlag);
      }
    };
    this.__preparedStmtOfDeleteAllCourses = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Course";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Course SET sentFlag=?";
        return _query;
      }
    };
  }

  @Override
  public void insertCourses(final List<Course> CourseList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCourse.insert(CourseList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllCourses() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCourses.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllCourses.release(_stmt);
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
  public List<Course> getAllCourse() {
    final String _sql = "SELECT * FROM Course";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCourseID = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseID");
      final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseName");
      final int _cursorIndexOfCourseIdInPos = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseIdInPos");
      final int _cursorIndexOfCourseCode = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseCode");
      final int _cursorIndexOfCourseSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseSubject");
      final int _cursorIndexOfCourseLang = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseLang");
      final int _cursorIndexOfIsDelete = CursorUtil.getColumnIndexOrThrow(_cursor, "isDelete");
      final int _cursorIndexOfListTopic = CursorUtil.getColumnIndexOrThrow(_cursor, "listTopic");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item;
        _item = new Course();
        if (_cursor.isNull(_cursorIndexOfCourseID)) {
          _item.CourseID = null;
        } else {
          _item.CourseID = _cursor.getString(_cursorIndexOfCourseID);
        }
        if (_cursor.isNull(_cursorIndexOfCourseName)) {
          _item.CourseName = null;
        } else {
          _item.CourseName = _cursor.getString(_cursorIndexOfCourseName);
        }
        if (_cursor.isNull(_cursorIndexOfCourseIdInPos)) {
          _item.CourseIdInPos = null;
        } else {
          _item.CourseIdInPos = _cursor.getString(_cursorIndexOfCourseIdInPos);
        }
        if (_cursor.isNull(_cursorIndexOfCourseCode)) {
          _item.CourseCode = null;
        } else {
          _item.CourseCode = _cursor.getString(_cursorIndexOfCourseCode);
        }
        if (_cursor.isNull(_cursorIndexOfCourseSubject)) {
          _item.CourseSubject = null;
        } else {
          _item.CourseSubject = _cursor.getString(_cursorIndexOfCourseSubject);
        }
        if (_cursor.isNull(_cursorIndexOfCourseLang)) {
          _item.CourseLang = null;
        } else {
          _item.CourseLang = _cursor.getString(_cursorIndexOfCourseLang);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsDelete);
        _item.isDelete = _tmp != 0;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfListTopic)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfListTopic);
        }
        _item.listTopic = JSONArrayToString.stringToJSONArray(_tmp_1);
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
  public List<Course> getAllCourseDetails(final String cID) {
    final String _sql = "SELECT * FROM Course WHERE CourseID=?";
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
      final int _cursorIndexOfCourseID = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseID");
      final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseName");
      final int _cursorIndexOfCourseIdInPos = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseIdInPos");
      final int _cursorIndexOfCourseCode = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseCode");
      final int _cursorIndexOfCourseSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseSubject");
      final int _cursorIndexOfCourseLang = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseLang");
      final int _cursorIndexOfIsDelete = CursorUtil.getColumnIndexOrThrow(_cursor, "isDelete");
      final int _cursorIndexOfListTopic = CursorUtil.getColumnIndexOrThrow(_cursor, "listTopic");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item;
        _item = new Course();
        if (_cursor.isNull(_cursorIndexOfCourseID)) {
          _item.CourseID = null;
        } else {
          _item.CourseID = _cursor.getString(_cursorIndexOfCourseID);
        }
        if (_cursor.isNull(_cursorIndexOfCourseName)) {
          _item.CourseName = null;
        } else {
          _item.CourseName = _cursor.getString(_cursorIndexOfCourseName);
        }
        if (_cursor.isNull(_cursorIndexOfCourseIdInPos)) {
          _item.CourseIdInPos = null;
        } else {
          _item.CourseIdInPos = _cursor.getString(_cursorIndexOfCourseIdInPos);
        }
        if (_cursor.isNull(_cursorIndexOfCourseCode)) {
          _item.CourseCode = null;
        } else {
          _item.CourseCode = _cursor.getString(_cursorIndexOfCourseCode);
        }
        if (_cursor.isNull(_cursorIndexOfCourseSubject)) {
          _item.CourseSubject = null;
        } else {
          _item.CourseSubject = _cursor.getString(_cursorIndexOfCourseSubject);
        }
        if (_cursor.isNull(_cursorIndexOfCourseLang)) {
          _item.CourseLang = null;
        } else {
          _item.CourseLang = _cursor.getString(_cursorIndexOfCourseLang);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsDelete);
        _item.isDelete = _tmp != 0;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfListTopic)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfListTopic);
        }
        _item.listTopic = JSONArrayToString.stringToJSONArray(_tmp_1);
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
  public List<Course> getNewCourses(final int status) {
    final String _sql = "SELECT * FROM Course WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfCourseID = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseID");
      final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseName");
      final int _cursorIndexOfCourseIdInPos = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseIdInPos");
      final int _cursorIndexOfCourseCode = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseCode");
      final int _cursorIndexOfCourseSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseSubject");
      final int _cursorIndexOfCourseLang = CursorUtil.getColumnIndexOrThrow(_cursor, "CourseLang");
      final int _cursorIndexOfIsDelete = CursorUtil.getColumnIndexOrThrow(_cursor, "isDelete");
      final int _cursorIndexOfListTopic = CursorUtil.getColumnIndexOrThrow(_cursor, "listTopic");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item;
        _item = new Course();
        if (_cursor.isNull(_cursorIndexOfCourseID)) {
          _item.CourseID = null;
        } else {
          _item.CourseID = _cursor.getString(_cursorIndexOfCourseID);
        }
        if (_cursor.isNull(_cursorIndexOfCourseName)) {
          _item.CourseName = null;
        } else {
          _item.CourseName = _cursor.getString(_cursorIndexOfCourseName);
        }
        if (_cursor.isNull(_cursorIndexOfCourseIdInPos)) {
          _item.CourseIdInPos = null;
        } else {
          _item.CourseIdInPos = _cursor.getString(_cursorIndexOfCourseIdInPos);
        }
        if (_cursor.isNull(_cursorIndexOfCourseCode)) {
          _item.CourseCode = null;
        } else {
          _item.CourseCode = _cursor.getString(_cursorIndexOfCourseCode);
        }
        if (_cursor.isNull(_cursorIndexOfCourseSubject)) {
          _item.CourseSubject = null;
        } else {
          _item.CourseSubject = _cursor.getString(_cursorIndexOfCourseSubject);
        }
        if (_cursor.isNull(_cursorIndexOfCourseLang)) {
          _item.CourseLang = null;
        } else {
          _item.CourseLang = _cursor.getString(_cursorIndexOfCourseLang);
        }
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsDelete);
        _item.isDelete = _tmp != 0;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfListTopic)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfListTopic);
        }
        _item.listTopic = JSONArrayToString.stringToJSONArray(_tmp_1);
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
