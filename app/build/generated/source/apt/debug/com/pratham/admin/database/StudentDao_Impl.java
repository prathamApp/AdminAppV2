package com.pratham.admin.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.pratham.admin.modalclasses.Student;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class StudentDao_Impl implements StudentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Student> __insertionAdapterOfStudent;

  private final EntityDeletionOrUpdateAdapter<Student> __updateAdapterOfStudent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllStudents;

  private final SharedSQLiteStatement __preparedStmtOfDeleteStudentByID;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAllSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSentFlag;

  private final SharedSQLiteStatement __preparedStmtOfRemoveDeletedStudentRecords;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDeletedGrpsStdRecords;

  private final SharedSQLiteStatement __preparedStmtOfUpdateStudent;

  public StudentDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudent = new EntityInsertionAdapter<Student>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Student` (`GroupId`,`GroupName`,`FullName`,`Stud_Class`,`Age`,`Gender`,`sentFlag`,`StudentId`,`FirstName`,`MiddleName`,`LastName`,`CreatedBy`,`CreatedOn`,`UpdatedDate`,`DOB`,`SchoolType`,`GuardianName`,`phoneType`,`phoneNo`,`relation_with_phone_owner`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Student value) {
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
        if (value.FullName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.FullName);
        }
        if (value.Stud_Class == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.Stud_Class);
        }
        if (value.Age == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.Age);
        }
        if (value.Gender == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.Gender);
        }
        stmt.bindLong(7, value.sentFlag);
        if (value.StudentId == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.StudentId);
        }
        if (value.FirstName == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.FirstName);
        }
        if (value.MiddleName == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.MiddleName);
        }
        if (value.LastName == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.LastName);
        }
        if (value.CreatedBy == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.CreatedBy);
        }
        if (value.CreatedOn == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.CreatedOn);
        }
        if (value.UpdatedDate == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.UpdatedDate);
        }
        if (value.DOB == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.DOB);
        }
        if (value.SchoolType == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.SchoolType);
        }
        if (value.GuardianName == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.GuardianName);
        }
        if (value.phoneType == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.phoneType);
        }
        if (value.phoneNo == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.phoneNo);
        }
        if (value.relation_with_phone_owner == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.relation_with_phone_owner);
        }
      }
    };
    this.__updateAdapterOfStudent = new EntityDeletionOrUpdateAdapter<Student>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Student` SET `GroupId` = ?,`GroupName` = ?,`FullName` = ?,`Stud_Class` = ?,`Age` = ?,`Gender` = ?,`sentFlag` = ?,`StudentId` = ?,`FirstName` = ?,`MiddleName` = ?,`LastName` = ?,`CreatedBy` = ?,`CreatedOn` = ?,`UpdatedDate` = ?,`DOB` = ?,`SchoolType` = ?,`GuardianName` = ?,`phoneType` = ?,`phoneNo` = ?,`relation_with_phone_owner` = ? WHERE `StudentId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Student value) {
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
        if (value.FullName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.FullName);
        }
        if (value.Stud_Class == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.Stud_Class);
        }
        if (value.Age == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.Age);
        }
        if (value.Gender == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.Gender);
        }
        stmt.bindLong(7, value.sentFlag);
        if (value.StudentId == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.StudentId);
        }
        if (value.FirstName == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.FirstName);
        }
        if (value.MiddleName == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.MiddleName);
        }
        if (value.LastName == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.LastName);
        }
        if (value.CreatedBy == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.CreatedBy);
        }
        if (value.CreatedOn == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.CreatedOn);
        }
        if (value.UpdatedDate == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.UpdatedDate);
        }
        if (value.DOB == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.DOB);
        }
        if (value.SchoolType == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.SchoolType);
        }
        if (value.GuardianName == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindString(17, value.GuardianName);
        }
        if (value.phoneType == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindString(18, value.phoneType);
        }
        if (value.phoneNo == null) {
          stmt.bindNull(19);
        } else {
          stmt.bindString(19, value.phoneNo);
        }
        if (value.relation_with_phone_owner == null) {
          stmt.bindNull(20);
        } else {
          stmt.bindString(20, value.relation_with_phone_owner);
        }
        if (value.StudentId == null) {
          stmt.bindNull(21);
        } else {
          stmt.bindString(21, value.StudentId);
        }
      }
    };
    this.__preparedStmtOfDeleteAllStudents = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Student";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteStudentByID = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Student Where StudentId=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAllSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Student SET sentFlag=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSentFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Student SET sentFlag=? WHERE StudentId =?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveDeletedStudentRecords = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Student WHERE Gender='deleted'";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteDeletedGrpsStdRecords = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Student WHERE GroupId=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateStudent = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update Student set Stud_Class=?,GuardianName=?,SchoolType=?,DOB=?,Age=?,Gender=?,phoneType=?,relation_with_phone_owner=?,phoneNo=?, sentFlag=? WHERE StudentId=?";
        return _query;
      }
    };
  }

  @Override
  public void insertAllStudents(final List<Student> studentsList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfStudent.insert(studentsList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertStudent(final Student student) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfStudent.insert(student);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int updateAllStudent(final List<Student> studList) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfStudent.handleMultiple(studList);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllStudents() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllStudents.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllStudents.release(_stmt);
    }
  }

  @Override
  public void deleteStudentByID(final String stdID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteStudentByID.acquire();
    int _argIndex = 1;
    if (stdID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, stdID);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteStudentByID.release(_stmt);
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
  public void updateSentFlag(final int pushStatus, final String sID) {
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
  public void removeDeletedStudentRecords() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveDeletedStudentRecords.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveDeletedStudentRecords.release(_stmt);
    }
  }

  @Override
  public void deleteDeletedGrpsStdRecords(final String grpID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDeletedGrpsStdRecords.acquire();
    int _argIndex = 1;
    if (grpID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, grpID);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteDeletedGrpsStdRecords.release(_stmt);
    }
  }

  @Override
  public void UpdateStudent(final String stdClass, final String guardianName,
      final String stdSchoolType, final String DOB, final String Age, final String gender,
      final String phoneType, final String relation_with_phone_owner, final String moNumber,
      final int sentFlag, final String StudentUniqID) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateStudent.acquire();
    int _argIndex = 1;
    if (stdClass == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, stdClass);
    }
    _argIndex = 2;
    if (guardianName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, guardianName);
    }
    _argIndex = 3;
    if (stdSchoolType == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, stdSchoolType);
    }
    _argIndex = 4;
    if (DOB == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, DOB);
    }
    _argIndex = 5;
    if (Age == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, Age);
    }
    _argIndex = 6;
    if (gender == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, gender);
    }
    _argIndex = 7;
    if (phoneType == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, phoneType);
    }
    _argIndex = 8;
    if (relation_with_phone_owner == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, relation_with_phone_owner);
    }
    _argIndex = 9;
    if (moNumber == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, moNumber);
    }
    _argIndex = 10;
    _stmt.bindLong(_argIndex, sentFlag);
    _argIndex = 11;
    if (StudentUniqID == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, StudentUniqID);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateStudent.release(_stmt);
    }
  }

  @Override
  public List<Student> getAllStudents() {
    final String _sql = "SELECT * FROM Student";
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
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfMiddleName = CursorUtil.getColumnIndexOrThrow(_cursor, "MiddleName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfUpdatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedDate");
      final int _cursorIndexOfDOB = CursorUtil.getColumnIndexOrThrow(_cursor, "DOB");
      final int _cursorIndexOfSchoolType = CursorUtil.getColumnIndexOrThrow(_cursor, "SchoolType");
      final int _cursorIndexOfGuardianName = CursorUtil.getColumnIndexOrThrow(_cursor, "GuardianName");
      final int _cursorIndexOfPhoneType = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneType");
      final int _cursorIndexOfPhoneNo = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNo");
      final int _cursorIndexOfRelationWithPhoneOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "relation_with_phone_owner");
      final List<Student> _result = new ArrayList<Student>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Student _item;
        _item = new Student();
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
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _item.FullName = null;
        } else {
          _item.FullName = _cursor.getString(_cursorIndexOfFullName);
        }
        if (_cursor.isNull(_cursorIndexOfStudClass)) {
          _item.Stud_Class = null;
        } else {
          _item.Stud_Class = _cursor.getString(_cursorIndexOfStudClass);
        }
        if (_cursor.isNull(_cursorIndexOfAge)) {
          _item.Age = null;
        } else {
          _item.Age = _cursor.getString(_cursorIndexOfAge);
        }
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _item.Gender = null;
        } else {
          _item.Gender = _cursor.getString(_cursorIndexOfGender);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _item.StudentId = null;
        } else {
          _item.StudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _item.FirstName = null;
        } else {
          _item.FirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        if (_cursor.isNull(_cursorIndexOfMiddleName)) {
          _item.MiddleName = null;
        } else {
          _item.MiddleName = _cursor.getString(_cursorIndexOfMiddleName);
        }
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _item.LastName = null;
        } else {
          _item.LastName = _cursor.getString(_cursorIndexOfLastName);
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
        if (_cursor.isNull(_cursorIndexOfUpdatedDate)) {
          _item.UpdatedDate = null;
        } else {
          _item.UpdatedDate = _cursor.getString(_cursorIndexOfUpdatedDate);
        }
        if (_cursor.isNull(_cursorIndexOfDOB)) {
          _item.DOB = null;
        } else {
          _item.DOB = _cursor.getString(_cursorIndexOfDOB);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolType)) {
          _item.SchoolType = null;
        } else {
          _item.SchoolType = _cursor.getString(_cursorIndexOfSchoolType);
        }
        if (_cursor.isNull(_cursorIndexOfGuardianName)) {
          _item.GuardianName = null;
        } else {
          _item.GuardianName = _cursor.getString(_cursorIndexOfGuardianName);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneType)) {
          _item.phoneType = null;
        } else {
          _item.phoneType = _cursor.getString(_cursorIndexOfPhoneType);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneNo)) {
          _item.phoneNo = null;
        } else {
          _item.phoneNo = _cursor.getString(_cursorIndexOfPhoneNo);
        }
        if (_cursor.isNull(_cursorIndexOfRelationWithPhoneOwner)) {
          _item.relation_with_phone_owner = null;
        } else {
          _item.relation_with_phone_owner = _cursor.getString(_cursorIndexOfRelationWithPhoneOwner);
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
  public List<Student> getGroupwiseStudents(final String gID) {
    final String _sql = "SELECT * FROM student WHERE GroupId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (gID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, gID);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfStudClass = CursorUtil.getColumnIndexOrThrow(_cursor, "Stud_Class");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "Age");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "Gender");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfMiddleName = CursorUtil.getColumnIndexOrThrow(_cursor, "MiddleName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfUpdatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedDate");
      final int _cursorIndexOfDOB = CursorUtil.getColumnIndexOrThrow(_cursor, "DOB");
      final int _cursorIndexOfSchoolType = CursorUtil.getColumnIndexOrThrow(_cursor, "SchoolType");
      final int _cursorIndexOfGuardianName = CursorUtil.getColumnIndexOrThrow(_cursor, "GuardianName");
      final int _cursorIndexOfPhoneType = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneType");
      final int _cursorIndexOfPhoneNo = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNo");
      final int _cursorIndexOfRelationWithPhoneOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "relation_with_phone_owner");
      final List<Student> _result = new ArrayList<Student>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Student _item;
        _item = new Student();
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
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _item.FullName = null;
        } else {
          _item.FullName = _cursor.getString(_cursorIndexOfFullName);
        }
        if (_cursor.isNull(_cursorIndexOfStudClass)) {
          _item.Stud_Class = null;
        } else {
          _item.Stud_Class = _cursor.getString(_cursorIndexOfStudClass);
        }
        if (_cursor.isNull(_cursorIndexOfAge)) {
          _item.Age = null;
        } else {
          _item.Age = _cursor.getString(_cursorIndexOfAge);
        }
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _item.Gender = null;
        } else {
          _item.Gender = _cursor.getString(_cursorIndexOfGender);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _item.StudentId = null;
        } else {
          _item.StudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _item.FirstName = null;
        } else {
          _item.FirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        if (_cursor.isNull(_cursorIndexOfMiddleName)) {
          _item.MiddleName = null;
        } else {
          _item.MiddleName = _cursor.getString(_cursorIndexOfMiddleName);
        }
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _item.LastName = null;
        } else {
          _item.LastName = _cursor.getString(_cursorIndexOfLastName);
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
        if (_cursor.isNull(_cursorIndexOfUpdatedDate)) {
          _item.UpdatedDate = null;
        } else {
          _item.UpdatedDate = _cursor.getString(_cursorIndexOfUpdatedDate);
        }
        if (_cursor.isNull(_cursorIndexOfDOB)) {
          _item.DOB = null;
        } else {
          _item.DOB = _cursor.getString(_cursorIndexOfDOB);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolType)) {
          _item.SchoolType = null;
        } else {
          _item.SchoolType = _cursor.getString(_cursorIndexOfSchoolType);
        }
        if (_cursor.isNull(_cursorIndexOfGuardianName)) {
          _item.GuardianName = null;
        } else {
          _item.GuardianName = _cursor.getString(_cursorIndexOfGuardianName);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneType)) {
          _item.phoneType = null;
        } else {
          _item.phoneType = _cursor.getString(_cursorIndexOfPhoneType);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneNo)) {
          _item.phoneNo = null;
        } else {
          _item.phoneNo = _cursor.getString(_cursorIndexOfPhoneNo);
        }
        if (_cursor.isNull(_cursorIndexOfRelationWithPhoneOwner)) {
          _item.relation_with_phone_owner = null;
        } else {
          _item.relation_with_phone_owner = _cursor.getString(_cursorIndexOfRelationWithPhoneOwner);
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
  public List<Student> getStudentByID(final String sID) {
    final String _sql = "SELECT * FROM student WHERE StudentId =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (sID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sID);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfStudClass = CursorUtil.getColumnIndexOrThrow(_cursor, "Stud_Class");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "Age");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "Gender");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfMiddleName = CursorUtil.getColumnIndexOrThrow(_cursor, "MiddleName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfUpdatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedDate");
      final int _cursorIndexOfDOB = CursorUtil.getColumnIndexOrThrow(_cursor, "DOB");
      final int _cursorIndexOfSchoolType = CursorUtil.getColumnIndexOrThrow(_cursor, "SchoolType");
      final int _cursorIndexOfGuardianName = CursorUtil.getColumnIndexOrThrow(_cursor, "GuardianName");
      final int _cursorIndexOfPhoneType = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneType");
      final int _cursorIndexOfPhoneNo = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNo");
      final int _cursorIndexOfRelationWithPhoneOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "relation_with_phone_owner");
      final List<Student> _result = new ArrayList<Student>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Student _item;
        _item = new Student();
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
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _item.FullName = null;
        } else {
          _item.FullName = _cursor.getString(_cursorIndexOfFullName);
        }
        if (_cursor.isNull(_cursorIndexOfStudClass)) {
          _item.Stud_Class = null;
        } else {
          _item.Stud_Class = _cursor.getString(_cursorIndexOfStudClass);
        }
        if (_cursor.isNull(_cursorIndexOfAge)) {
          _item.Age = null;
        } else {
          _item.Age = _cursor.getString(_cursorIndexOfAge);
        }
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _item.Gender = null;
        } else {
          _item.Gender = _cursor.getString(_cursorIndexOfGender);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _item.StudentId = null;
        } else {
          _item.StudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _item.FirstName = null;
        } else {
          _item.FirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        if (_cursor.isNull(_cursorIndexOfMiddleName)) {
          _item.MiddleName = null;
        } else {
          _item.MiddleName = _cursor.getString(_cursorIndexOfMiddleName);
        }
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _item.LastName = null;
        } else {
          _item.LastName = _cursor.getString(_cursorIndexOfLastName);
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
        if (_cursor.isNull(_cursorIndexOfUpdatedDate)) {
          _item.UpdatedDate = null;
        } else {
          _item.UpdatedDate = _cursor.getString(_cursorIndexOfUpdatedDate);
        }
        if (_cursor.isNull(_cursorIndexOfDOB)) {
          _item.DOB = null;
        } else {
          _item.DOB = _cursor.getString(_cursorIndexOfDOB);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolType)) {
          _item.SchoolType = null;
        } else {
          _item.SchoolType = _cursor.getString(_cursorIndexOfSchoolType);
        }
        if (_cursor.isNull(_cursorIndexOfGuardianName)) {
          _item.GuardianName = null;
        } else {
          _item.GuardianName = _cursor.getString(_cursorIndexOfGuardianName);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneType)) {
          _item.phoneType = null;
        } else {
          _item.phoneType = _cursor.getString(_cursorIndexOfPhoneType);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneNo)) {
          _item.phoneNo = null;
        } else {
          _item.phoneNo = _cursor.getString(_cursorIndexOfPhoneNo);
        }
        if (_cursor.isNull(_cursorIndexOfRelationWithPhoneOwner)) {
          _item.relation_with_phone_owner = null;
        } else {
          _item.relation_with_phone_owner = _cursor.getString(_cursorIndexOfRelationWithPhoneOwner);
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
  public List<Student> GetAllStudentsByGroupID(final String GroupID) {
    final String _sql = "SELECT * FROM Student WHERE GroupID =? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (GroupID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, GroupID);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfStudClass = CursorUtil.getColumnIndexOrThrow(_cursor, "Stud_Class");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "Age");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "Gender");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfMiddleName = CursorUtil.getColumnIndexOrThrow(_cursor, "MiddleName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfUpdatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedDate");
      final int _cursorIndexOfDOB = CursorUtil.getColumnIndexOrThrow(_cursor, "DOB");
      final int _cursorIndexOfSchoolType = CursorUtil.getColumnIndexOrThrow(_cursor, "SchoolType");
      final int _cursorIndexOfGuardianName = CursorUtil.getColumnIndexOrThrow(_cursor, "GuardianName");
      final int _cursorIndexOfPhoneType = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneType");
      final int _cursorIndexOfPhoneNo = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNo");
      final int _cursorIndexOfRelationWithPhoneOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "relation_with_phone_owner");
      final List<Student> _result = new ArrayList<Student>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Student _item;
        _item = new Student();
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
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _item.FullName = null;
        } else {
          _item.FullName = _cursor.getString(_cursorIndexOfFullName);
        }
        if (_cursor.isNull(_cursorIndexOfStudClass)) {
          _item.Stud_Class = null;
        } else {
          _item.Stud_Class = _cursor.getString(_cursorIndexOfStudClass);
        }
        if (_cursor.isNull(_cursorIndexOfAge)) {
          _item.Age = null;
        } else {
          _item.Age = _cursor.getString(_cursorIndexOfAge);
        }
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _item.Gender = null;
        } else {
          _item.Gender = _cursor.getString(_cursorIndexOfGender);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _item.StudentId = null;
        } else {
          _item.StudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _item.FirstName = null;
        } else {
          _item.FirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        if (_cursor.isNull(_cursorIndexOfMiddleName)) {
          _item.MiddleName = null;
        } else {
          _item.MiddleName = _cursor.getString(_cursorIndexOfMiddleName);
        }
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _item.LastName = null;
        } else {
          _item.LastName = _cursor.getString(_cursorIndexOfLastName);
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
        if (_cursor.isNull(_cursorIndexOfUpdatedDate)) {
          _item.UpdatedDate = null;
        } else {
          _item.UpdatedDate = _cursor.getString(_cursorIndexOfUpdatedDate);
        }
        if (_cursor.isNull(_cursorIndexOfDOB)) {
          _item.DOB = null;
        } else {
          _item.DOB = _cursor.getString(_cursorIndexOfDOB);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolType)) {
          _item.SchoolType = null;
        } else {
          _item.SchoolType = _cursor.getString(_cursorIndexOfSchoolType);
        }
        if (_cursor.isNull(_cursorIndexOfGuardianName)) {
          _item.GuardianName = null;
        } else {
          _item.GuardianName = _cursor.getString(_cursorIndexOfGuardianName);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneType)) {
          _item.phoneType = null;
        } else {
          _item.phoneType = _cursor.getString(_cursorIndexOfPhoneType);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneNo)) {
          _item.phoneNo = null;
        } else {
          _item.phoneNo = _cursor.getString(_cursorIndexOfPhoneNo);
        }
        if (_cursor.isNull(_cursorIndexOfRelationWithPhoneOwner)) {
          _item.relation_with_phone_owner = null;
        } else {
          _item.relation_with_phone_owner = _cursor.getString(_cursorIndexOfRelationWithPhoneOwner);
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
  public Student GetStudentDataByStdID(final String studentID) {
    final String _sql = "select * from Student where StudentID =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (studentID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, studentID);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfStudClass = CursorUtil.getColumnIndexOrThrow(_cursor, "Stud_Class");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "Age");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "Gender");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfMiddleName = CursorUtil.getColumnIndexOrThrow(_cursor, "MiddleName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfUpdatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedDate");
      final int _cursorIndexOfDOB = CursorUtil.getColumnIndexOrThrow(_cursor, "DOB");
      final int _cursorIndexOfSchoolType = CursorUtil.getColumnIndexOrThrow(_cursor, "SchoolType");
      final int _cursorIndexOfGuardianName = CursorUtil.getColumnIndexOrThrow(_cursor, "GuardianName");
      final int _cursorIndexOfPhoneType = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneType");
      final int _cursorIndexOfPhoneNo = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNo");
      final int _cursorIndexOfRelationWithPhoneOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "relation_with_phone_owner");
      final Student _result;
      if(_cursor.moveToFirst()) {
        _result = new Student();
        if (_cursor.isNull(_cursorIndexOfGroupId)) {
          _result.GroupId = null;
        } else {
          _result.GroupId = _cursor.getString(_cursorIndexOfGroupId);
        }
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _result.GroupName = null;
        } else {
          _result.GroupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _result.FullName = null;
        } else {
          _result.FullName = _cursor.getString(_cursorIndexOfFullName);
        }
        if (_cursor.isNull(_cursorIndexOfStudClass)) {
          _result.Stud_Class = null;
        } else {
          _result.Stud_Class = _cursor.getString(_cursorIndexOfStudClass);
        }
        if (_cursor.isNull(_cursorIndexOfAge)) {
          _result.Age = null;
        } else {
          _result.Age = _cursor.getString(_cursorIndexOfAge);
        }
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _result.Gender = null;
        } else {
          _result.Gender = _cursor.getString(_cursorIndexOfGender);
        }
        _result.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _result.StudentId = null;
        } else {
          _result.StudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _result.FirstName = null;
        } else {
          _result.FirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        if (_cursor.isNull(_cursorIndexOfMiddleName)) {
          _result.MiddleName = null;
        } else {
          _result.MiddleName = _cursor.getString(_cursorIndexOfMiddleName);
        }
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _result.LastName = null;
        } else {
          _result.LastName = _cursor.getString(_cursorIndexOfLastName);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
          _result.CreatedBy = null;
        } else {
          _result.CreatedBy = _cursor.getString(_cursorIndexOfCreatedBy);
        }
        if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
          _result.CreatedOn = null;
        } else {
          _result.CreatedOn = _cursor.getString(_cursorIndexOfCreatedOn);
        }
        if (_cursor.isNull(_cursorIndexOfUpdatedDate)) {
          _result.UpdatedDate = null;
        } else {
          _result.UpdatedDate = _cursor.getString(_cursorIndexOfUpdatedDate);
        }
        if (_cursor.isNull(_cursorIndexOfDOB)) {
          _result.DOB = null;
        } else {
          _result.DOB = _cursor.getString(_cursorIndexOfDOB);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolType)) {
          _result.SchoolType = null;
        } else {
          _result.SchoolType = _cursor.getString(_cursorIndexOfSchoolType);
        }
        if (_cursor.isNull(_cursorIndexOfGuardianName)) {
          _result.GuardianName = null;
        } else {
          _result.GuardianName = _cursor.getString(_cursorIndexOfGuardianName);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneType)) {
          _result.phoneType = null;
        } else {
          _result.phoneType = _cursor.getString(_cursorIndexOfPhoneType);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneNo)) {
          _result.phoneNo = null;
        } else {
          _result.phoneNo = _cursor.getString(_cursorIndexOfPhoneNo);
        }
        if (_cursor.isNull(_cursorIndexOfRelationWithPhoneOwner)) {
          _result.relation_with_phone_owner = null;
        } else {
          _result.relation_with_phone_owner = _cursor.getString(_cursorIndexOfRelationWithPhoneOwner);
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
  public List<Student> getNewStudents(final int status) {
    final String _sql = "SELECT * FROM Student WHERE sentFlag=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, status);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfGroupId = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupId");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "GroupName");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfStudClass = CursorUtil.getColumnIndexOrThrow(_cursor, "Stud_Class");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "Age");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "Gender");
      final int _cursorIndexOfSentFlag = CursorUtil.getColumnIndexOrThrow(_cursor, "sentFlag");
      final int _cursorIndexOfStudentId = CursorUtil.getColumnIndexOrThrow(_cursor, "StudentId");
      final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "FirstName");
      final int _cursorIndexOfMiddleName = CursorUtil.getColumnIndexOrThrow(_cursor, "MiddleName");
      final int _cursorIndexOfLastName = CursorUtil.getColumnIndexOrThrow(_cursor, "LastName");
      final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedBy");
      final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedOn");
      final int _cursorIndexOfUpdatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedDate");
      final int _cursorIndexOfDOB = CursorUtil.getColumnIndexOrThrow(_cursor, "DOB");
      final int _cursorIndexOfSchoolType = CursorUtil.getColumnIndexOrThrow(_cursor, "SchoolType");
      final int _cursorIndexOfGuardianName = CursorUtil.getColumnIndexOrThrow(_cursor, "GuardianName");
      final int _cursorIndexOfPhoneType = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneType");
      final int _cursorIndexOfPhoneNo = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNo");
      final int _cursorIndexOfRelationWithPhoneOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "relation_with_phone_owner");
      final List<Student> _result = new ArrayList<Student>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Student _item;
        _item = new Student();
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
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _item.FullName = null;
        } else {
          _item.FullName = _cursor.getString(_cursorIndexOfFullName);
        }
        if (_cursor.isNull(_cursorIndexOfStudClass)) {
          _item.Stud_Class = null;
        } else {
          _item.Stud_Class = _cursor.getString(_cursorIndexOfStudClass);
        }
        if (_cursor.isNull(_cursorIndexOfAge)) {
          _item.Age = null;
        } else {
          _item.Age = _cursor.getString(_cursorIndexOfAge);
        }
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _item.Gender = null;
        } else {
          _item.Gender = _cursor.getString(_cursorIndexOfGender);
        }
        _item.sentFlag = _cursor.getInt(_cursorIndexOfSentFlag);
        if (_cursor.isNull(_cursorIndexOfStudentId)) {
          _item.StudentId = null;
        } else {
          _item.StudentId = _cursor.getString(_cursorIndexOfStudentId);
        }
        if (_cursor.isNull(_cursorIndexOfFirstName)) {
          _item.FirstName = null;
        } else {
          _item.FirstName = _cursor.getString(_cursorIndexOfFirstName);
        }
        if (_cursor.isNull(_cursorIndexOfMiddleName)) {
          _item.MiddleName = null;
        } else {
          _item.MiddleName = _cursor.getString(_cursorIndexOfMiddleName);
        }
        if (_cursor.isNull(_cursorIndexOfLastName)) {
          _item.LastName = null;
        } else {
          _item.LastName = _cursor.getString(_cursorIndexOfLastName);
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
        if (_cursor.isNull(_cursorIndexOfUpdatedDate)) {
          _item.UpdatedDate = null;
        } else {
          _item.UpdatedDate = _cursor.getString(_cursorIndexOfUpdatedDate);
        }
        if (_cursor.isNull(_cursorIndexOfDOB)) {
          _item.DOB = null;
        } else {
          _item.DOB = _cursor.getString(_cursorIndexOfDOB);
        }
        if (_cursor.isNull(_cursorIndexOfSchoolType)) {
          _item.SchoolType = null;
        } else {
          _item.SchoolType = _cursor.getString(_cursorIndexOfSchoolType);
        }
        if (_cursor.isNull(_cursorIndexOfGuardianName)) {
          _item.GuardianName = null;
        } else {
          _item.GuardianName = _cursor.getString(_cursorIndexOfGuardianName);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneType)) {
          _item.phoneType = null;
        } else {
          _item.phoneType = _cursor.getString(_cursorIndexOfPhoneType);
        }
        if (_cursor.isNull(_cursorIndexOfPhoneNo)) {
          _item.phoneNo = null;
        } else {
          _item.phoneNo = _cursor.getString(_cursorIndexOfPhoneNo);
        }
        if (_cursor.isNull(_cursorIndexOfRelationWithPhoneOwner)) {
          _item.relation_with_phone_owner = null;
        } else {
          _item.relation_with_phone_owner = _cursor.getString(_cursorIndexOfRelationWithPhoneOwner);
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
