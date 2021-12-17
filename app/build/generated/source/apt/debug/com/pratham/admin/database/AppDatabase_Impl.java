package com.pratham.admin.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ECEAsmtDao _eCEAsmtDao;

  private volatile AttendanceDao _attendanceDao;

  private volatile CRLdao _cRLdao;

  private volatile LogDao _logDao;

  private volatile CRLmd_dao _cRLmdDao;

  private volatile TabletStatusDao _tabletStatusDao;

  private volatile CoachDao _coachDao;

  private volatile CourseDao _courseDao;

  private volatile CommunityDao _communityDao;

  private volatile CompletionDao _completionDao;

  private volatile GroupSessionDao _groupSessionDao;

  private volatile GroupVisitDao _groupVisitDao;

  private volatile TabTrackDao _tabTrackDao;

  private volatile TabletManageDeviceDao _tabletManageDeviceDao;

  private volatile GroupDao _groupDao;

  private volatile StudentDao _studentDao;

  private volatile AserDao _aserDao;

  private volatile VillageDao _villageDao;

  private volatile MetaDataDao _metaDataDao;

  private volatile TempStudentdao _tempStudentdao;

  private volatile YouthDao _youthDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(9) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ECEAsmt` (`ECEAsmtID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `StudentId` TEXT, `AsmtType` INTEGER NOT NULL, `Date` TEXT, `StartTime` TEXT, `EndTime` TEXT, `ActMatchingCards` INTEGER NOT NULL, `ActSequencingCards` INTEGER NOT NULL, `ActNumberReco` INTEGER NOT NULL, `ActWordReco` INTEGER NOT NULL, `WS11a` INTEGER NOT NULL, `WS11b` INTEGER NOT NULL, `WS12a` INTEGER NOT NULL, `WS12b` INTEGER NOT NULL, `OQ11` INTEGER NOT NULL, `OQ12` INTEGER NOT NULL, `OQ13` INTEGER NOT NULL, `OQ14` INTEGER NOT NULL, `sentFlag` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Attendance` (`AttendanceID` TEXT NOT NULL, `VillageID` TEXT, `GroupID` TEXT, `StudentID` TEXT, `Date` TEXT, `Present` INTEGER NOT NULL, `sentFlag` INTEGER NOT NULL, PRIMARY KEY(`AttendanceID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CRL` (`CRLId` TEXT NOT NULL, `RoleId` TEXT, `RoleName` TEXT, `ProgramId` TEXT, `ProgramName` TEXT, `State` TEXT, `FirstName` TEXT, `LastName` TEXT, `Mobile` TEXT, `Email` TEXT, `Block` TEXT, `District` TEXT, `UserName` TEXT, `Password` TEXT, `ReportingPersonId` TEXT, `ReportingPersonName` TEXT, PRIMARY KEY(`CRLId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CRLmd` (`CRLId` TEXT NOT NULL, `RoleId` TEXT, `RoleName` TEXT, `ProgramId` TEXT, `ProgramName` TEXT, `State` TEXT, `FirstName` TEXT, `LastName` TEXT, `Mobile` TEXT, `Email` TEXT, `Block` TEXT, `District` TEXT, `UserName` TEXT, `Password` TEXT, `status` TEXT, PRIMARY KEY(`CRLId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Coach` (`CoachID` TEXT NOT NULL, `CoachName` TEXT, `CoachAge` INTEGER NOT NULL, `CoachGender` TEXT, `CoachOccupation` TEXT, `CoachSpeciality` TEXT, `CoachSubjectExpert` TEXT, `CoachEducation` TEXT, `CoachGroupID` TEXT, `CoachVillageID` TEXT, `StartDate` TEXT, `EndDate` TEXT, `CoachActive` INTEGER NOT NULL, `CreatedBy` TEXT, `CreatedDate` TEXT, `sentFlag` INTEGER NOT NULL, PRIMARY KEY(`CoachID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Course` (`CourseID` TEXT NOT NULL, `CourseName` TEXT, `CourseIdInPos` TEXT, `CourseCode` TEXT, `CourseSubject` TEXT, `CourseLang` TEXT, `isDelete` INTEGER NOT NULL, `listTopic` TEXT, `sentFlag` INTEGER NOT NULL, PRIMARY KEY(`CourseID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Community` (`CommunityID` TEXT NOT NULL, `Community` TEXT, `VillageID` TEXT, `ProgramId` TEXT, `GroupID` TEXT, `CourseAdded` TEXT, `TopicAdded` TEXT, `CoachID` TEXT, `StartDate` TEXT, `EndDate` TEXT, `ParentParticipation` INTEGER NOT NULL, `PresentStudent` INTEGER NOT NULL, `AddedCourseID` TEXT, `AddedTopicsID` TEXT, `CreatedBy` TEXT, `CreatedDate` TEXT, `sentFlag` INTEGER NOT NULL, PRIMARY KEY(`CommunityID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Completion` (`CompletionID` TEXT NOT NULL, `GroupType` TEXT, `VillageID` TEXT, `ProgramId` TEXT, `GroupID` TEXT, `CourseCompleted` TEXT, `TopicCompleted` TEXT, `StartDate` TEXT, `EndDate` TEXT, `ParentParticipation` INTEGER NOT NULL, `PresentParents` INTEGER NOT NULL, `Event` INTEGER NOT NULL, `CreatedBy` TEXT, `sentFlag` INTEGER NOT NULL, PRIMARY KEY(`CompletionID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Groups` (`GroupId` TEXT NOT NULL, `GroupName` TEXT, `VillageId` TEXT, `ProgramId` INTEGER NOT NULL, `GroupCode` TEXT, `SchoolName` TEXT, `VIllageName` TEXT, `DeviceId` TEXT, `CreatedBy` TEXT, `CreatedOn` TEXT, `sentFlag` INTEGER NOT NULL, PRIMARY KEY(`GroupId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Student` (`GroupId` TEXT, `GroupName` TEXT, `FullName` TEXT, `Stud_Class` TEXT, `Age` TEXT, `Gender` TEXT, `sentFlag` INTEGER NOT NULL, `StudentId` TEXT NOT NULL, `FirstName` TEXT, `MiddleName` TEXT, `LastName` TEXT, `CreatedBy` TEXT, `CreatedOn` TEXT, `UpdatedDate` TEXT, `DOB` TEXT, `SchoolType` TEXT, `GuardianName` TEXT, `phoneType` TEXT, `phoneNo` TEXT, `relation_with_phone_owner` TEXT, PRIMARY KEY(`StudentId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `GroupSession` (`GroupSessionID` TEXT NOT NULL, `VillageID` INTEGER NOT NULL, `Village` TEXT, `GroupIDVisited` TEXT, `Group` TEXT, `DateVisited` TEXT, `StartTime` TEXT, `EndTime` TEXT, `CoachPresentInVillage` TEXT, `WorkCrosscheckedGroupIDs` TEXT, `PresentStudents` TEXT, `sentFlag` INTEGER NOT NULL, PRIMARY KEY(`GroupSessionID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `GroupVisit` (`GroupVisitID` TEXT NOT NULL, `VillageID` INTEGER NOT NULL, `Village` TEXT, `DateVisited` TEXT, `StartTime` TEXT, `EndTime` TEXT, `GroupIDVisited` TEXT, `PresentGroupIDs` TEXT, `WorkCrosscheckedGroupIDs` TEXT, `Group` TEXT, `CoachPresentInVillage` TEXT, `PresentStudents` TEXT, `sentFlag` INTEGER NOT NULL, PRIMARY KEY(`GroupVisitID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Village` (`VillageId` TEXT NOT NULL, `VillageCode` TEXT, `VillageName` TEXT, `Block` TEXT, `District` TEXT, `State` TEXT, `CRLId` TEXT, PRIMARY KEY(`VillageId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MetaData` (`keys` TEXT NOT NULL, `value` TEXT, PRIMARY KEY(`keys`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TempStudent` (`GroupId` TEXT, `GroupName` TEXT, `FullName` TEXT, `Stud_Class` TEXT, `Age` TEXT, `Gender` TEXT, `StudentId` TEXT NOT NULL, PRIMARY KEY(`StudentId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TabTrack` (`QR_ID` TEXT NOT NULL, `CRL_ID` TEXT, `CRL_Name` TEXT, `State` TEXT, `Pratham_ID` TEXT, `date` TEXT, `Serial_NO` TEXT, `oldFlag` INTEGER NOT NULL, PRIMARY KEY(`QR_ID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TabletManageDevice` (`id` TEXT NOT NULL, `QR_ID` TEXT, `Pratham_ID` TEXT, `tabSerial_ID` TEXT, `date` TEXT, `assigned_CRL_ID` TEXT, `assigned_CRL_Name` TEXT, `logged_CRL_ID` TEXT, `logged_CRL_NAME` TEXT, `collectedTabPrathamID` TEXT, `collectedTabQrID` TEXT, `collectedTab_serial_ID` TEXT, `collectedTabs_senior` TEXT, `collected_date` TEXT, `is_Damaged` TEXT, `damageType` TEXT, `status` TEXT, `comment` TEXT, `oldFlag` INTEGER NOT NULL, `villageName` TEXT, `villageID` TEXT, `isPushed` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Logs` (`logId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `currentDateTime` TEXT, `exceptionMessage` TEXT, `exceptionStackTrace` TEXT, `methodName` TEXT, `errorType` TEXT, `groupId` TEXT, `deviceId` TEXT, `LogDetail` TEXT, `sentFlag` INTEGER NOT NULL, `sessionId` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TabletStatus` (`qrID` TEXT NOT NULL, `loggedCRL_Id` TEXT, `loggedCRL_Name` TEXT, `prathamId` TEXT, `date` TEXT, `serialNo` TEXT, `status` TEXT, `oldFlag` INTEGER NOT NULL, PRIMARY KEY(`qrID`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Aser` (`AserID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `StudentId` TEXT, `TestType` INTEGER NOT NULL, `TestDate` TEXT, `ChildID` TEXT, `Lang` INTEGER NOT NULL, `Num` INTEGER NOT NULL, `OAdd` INTEGER NOT NULL, `OSub` INTEGER NOT NULL, `OMul` INTEGER NOT NULL, `ODiv` INTEGER NOT NULL, `WAdd` INTEGER NOT NULL, `WSub` INTEGER NOT NULL, `FLAG` INTEGER NOT NULL, `CreatedBy` TEXT, `CreatedDate` TEXT, `DeviceId` TEXT, `GroupID` TEXT, `sharedBy` TEXT, `SharedAtDateTime` TEXT, `appVersion` TEXT, `appName` TEXT, `CreatedOn` TEXT, `sentFlag` INTEGER NOT NULL, `English` INTEGER NOT NULL, `EnglishSelected` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Youth` (`youthId` TEXT NOT NULL, `groupId` TEXT, `groupName` TEXT, `firstName` TEXT, `middleName` TEXT, `lastName` TEXT, `phoneNumber` TEXT, `guardianName` TEXT, `birthDate` TEXT, `gender` TEXT, `maritalStatus` TEXT, `areyoustudying` INTEGER NOT NULL, `education` TEXT, `occupation` TEXT, `haveSmartphone` INTEGER NOT NULL, `useSmartphone` INTEGER NOT NULL, `wantToJoin` INTEGER NOT NULL, `createdBy` TEXT, `createdOn` TEXT, `isDeleted` INTEGER NOT NULL, `sentFlag` INTEGER NOT NULL, PRIMARY KEY(`youthId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8df9062135b8467c3373e5643abbed4a')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `ECEAsmt`");
        _db.execSQL("DROP TABLE IF EXISTS `Attendance`");
        _db.execSQL("DROP TABLE IF EXISTS `CRL`");
        _db.execSQL("DROP TABLE IF EXISTS `CRLmd`");
        _db.execSQL("DROP TABLE IF EXISTS `Coach`");
        _db.execSQL("DROP TABLE IF EXISTS `Course`");
        _db.execSQL("DROP TABLE IF EXISTS `Community`");
        _db.execSQL("DROP TABLE IF EXISTS `Completion`");
        _db.execSQL("DROP TABLE IF EXISTS `Groups`");
        _db.execSQL("DROP TABLE IF EXISTS `Student`");
        _db.execSQL("DROP TABLE IF EXISTS `GroupSession`");
        _db.execSQL("DROP TABLE IF EXISTS `GroupVisit`");
        _db.execSQL("DROP TABLE IF EXISTS `Village`");
        _db.execSQL("DROP TABLE IF EXISTS `MetaData`");
        _db.execSQL("DROP TABLE IF EXISTS `TempStudent`");
        _db.execSQL("DROP TABLE IF EXISTS `TabTrack`");
        _db.execSQL("DROP TABLE IF EXISTS `TabletManageDevice`");
        _db.execSQL("DROP TABLE IF EXISTS `Logs`");
        _db.execSQL("DROP TABLE IF EXISTS `TabletStatus`");
        _db.execSQL("DROP TABLE IF EXISTS `Aser`");
        _db.execSQL("DROP TABLE IF EXISTS `Youth`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsECEAsmt = new HashMap<String, TableInfo.Column>(19);
        _columnsECEAsmt.put("ECEAsmtID", new TableInfo.Column("ECEAsmtID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("StudentId", new TableInfo.Column("StudentId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("AsmtType", new TableInfo.Column("AsmtType", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("Date", new TableInfo.Column("Date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("StartTime", new TableInfo.Column("StartTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("EndTime", new TableInfo.Column("EndTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("ActMatchingCards", new TableInfo.Column("ActMatchingCards", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("ActSequencingCards", new TableInfo.Column("ActSequencingCards", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("ActNumberReco", new TableInfo.Column("ActNumberReco", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("ActWordReco", new TableInfo.Column("ActWordReco", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("WS11a", new TableInfo.Column("WS11a", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("WS11b", new TableInfo.Column("WS11b", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("WS12a", new TableInfo.Column("WS12a", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("WS12b", new TableInfo.Column("WS12b", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("OQ11", new TableInfo.Column("OQ11", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("OQ12", new TableInfo.Column("OQ12", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("OQ13", new TableInfo.Column("OQ13", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("OQ14", new TableInfo.Column("OQ14", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsECEAsmt.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysECEAsmt = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesECEAsmt = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoECEAsmt = new TableInfo("ECEAsmt", _columnsECEAsmt, _foreignKeysECEAsmt, _indicesECEAsmt);
        final TableInfo _existingECEAsmt = TableInfo.read(_db, "ECEAsmt");
        if (! _infoECEAsmt.equals(_existingECEAsmt)) {
          return new RoomOpenHelper.ValidationResult(false, "ECEAsmt(com.pratham.admin.modalclasses.ECEAsmt).\n"
                  + " Expected:\n" + _infoECEAsmt + "\n"
                  + " Found:\n" + _existingECEAsmt);
        }
        final HashMap<String, TableInfo.Column> _columnsAttendance = new HashMap<String, TableInfo.Column>(7);
        _columnsAttendance.put("AttendanceID", new TableInfo.Column("AttendanceID", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendance.put("VillageID", new TableInfo.Column("VillageID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendance.put("GroupID", new TableInfo.Column("GroupID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendance.put("StudentID", new TableInfo.Column("StudentID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendance.put("Date", new TableInfo.Column("Date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendance.put("Present", new TableInfo.Column("Present", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendance.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAttendance = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAttendance = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAttendance = new TableInfo("Attendance", _columnsAttendance, _foreignKeysAttendance, _indicesAttendance);
        final TableInfo _existingAttendance = TableInfo.read(_db, "Attendance");
        if (! _infoAttendance.equals(_existingAttendance)) {
          return new RoomOpenHelper.ValidationResult(false, "Attendance(com.pratham.admin.modalclasses.Attendance).\n"
                  + " Expected:\n" + _infoAttendance + "\n"
                  + " Found:\n" + _existingAttendance);
        }
        final HashMap<String, TableInfo.Column> _columnsCRL = new HashMap<String, TableInfo.Column>(16);
        _columnsCRL.put("CRLId", new TableInfo.Column("CRLId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("RoleId", new TableInfo.Column("RoleId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("RoleName", new TableInfo.Column("RoleName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("ProgramId", new TableInfo.Column("ProgramId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("ProgramName", new TableInfo.Column("ProgramName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("State", new TableInfo.Column("State", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("FirstName", new TableInfo.Column("FirstName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("LastName", new TableInfo.Column("LastName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("Mobile", new TableInfo.Column("Mobile", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("Email", new TableInfo.Column("Email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("Block", new TableInfo.Column("Block", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("District", new TableInfo.Column("District", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("UserName", new TableInfo.Column("UserName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("Password", new TableInfo.Column("Password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("ReportingPersonId", new TableInfo.Column("ReportingPersonId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRL.put("ReportingPersonName", new TableInfo.Column("ReportingPersonName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCRL = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCRL = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCRL = new TableInfo("CRL", _columnsCRL, _foreignKeysCRL, _indicesCRL);
        final TableInfo _existingCRL = TableInfo.read(_db, "CRL");
        if (! _infoCRL.equals(_existingCRL)) {
          return new RoomOpenHelper.ValidationResult(false, "CRL(com.pratham.admin.modalclasses.CRL).\n"
                  + " Expected:\n" + _infoCRL + "\n"
                  + " Found:\n" + _existingCRL);
        }
        final HashMap<String, TableInfo.Column> _columnsCRLmd = new HashMap<String, TableInfo.Column>(15);
        _columnsCRLmd.put("CRLId", new TableInfo.Column("CRLId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("RoleId", new TableInfo.Column("RoleId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("RoleName", new TableInfo.Column("RoleName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("ProgramId", new TableInfo.Column("ProgramId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("ProgramName", new TableInfo.Column("ProgramName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("State", new TableInfo.Column("State", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("FirstName", new TableInfo.Column("FirstName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("LastName", new TableInfo.Column("LastName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("Mobile", new TableInfo.Column("Mobile", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("Email", new TableInfo.Column("Email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("Block", new TableInfo.Column("Block", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("District", new TableInfo.Column("District", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("UserName", new TableInfo.Column("UserName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("Password", new TableInfo.Column("Password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCRLmd.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCRLmd = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCRLmd = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCRLmd = new TableInfo("CRLmd", _columnsCRLmd, _foreignKeysCRLmd, _indicesCRLmd);
        final TableInfo _existingCRLmd = TableInfo.read(_db, "CRLmd");
        if (! _infoCRLmd.equals(_existingCRLmd)) {
          return new RoomOpenHelper.ValidationResult(false, "CRLmd(com.pratham.admin.modalclasses.CRLmd).\n"
                  + " Expected:\n" + _infoCRLmd + "\n"
                  + " Found:\n" + _existingCRLmd);
        }
        final HashMap<String, TableInfo.Column> _columnsCoach = new HashMap<String, TableInfo.Column>(16);
        _columnsCoach.put("CoachID", new TableInfo.Column("CoachID", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CoachName", new TableInfo.Column("CoachName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CoachAge", new TableInfo.Column("CoachAge", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CoachGender", new TableInfo.Column("CoachGender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CoachOccupation", new TableInfo.Column("CoachOccupation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CoachSpeciality", new TableInfo.Column("CoachSpeciality", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CoachSubjectExpert", new TableInfo.Column("CoachSubjectExpert", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CoachEducation", new TableInfo.Column("CoachEducation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CoachGroupID", new TableInfo.Column("CoachGroupID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CoachVillageID", new TableInfo.Column("CoachVillageID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("StartDate", new TableInfo.Column("StartDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("EndDate", new TableInfo.Column("EndDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CoachActive", new TableInfo.Column("CoachActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CreatedBy", new TableInfo.Column("CreatedBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("CreatedDate", new TableInfo.Column("CreatedDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCoach.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCoach = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCoach = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCoach = new TableInfo("Coach", _columnsCoach, _foreignKeysCoach, _indicesCoach);
        final TableInfo _existingCoach = TableInfo.read(_db, "Coach");
        if (! _infoCoach.equals(_existingCoach)) {
          return new RoomOpenHelper.ValidationResult(false, "Coach(com.pratham.admin.modalclasses.Coach).\n"
                  + " Expected:\n" + _infoCoach + "\n"
                  + " Found:\n" + _existingCoach);
        }
        final HashMap<String, TableInfo.Column> _columnsCourse = new HashMap<String, TableInfo.Column>(9);
        _columnsCourse.put("CourseID", new TableInfo.Column("CourseID", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("CourseName", new TableInfo.Column("CourseName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("CourseIdInPos", new TableInfo.Column("CourseIdInPos", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("CourseCode", new TableInfo.Column("CourseCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("CourseSubject", new TableInfo.Column("CourseSubject", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("CourseLang", new TableInfo.Column("CourseLang", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("isDelete", new TableInfo.Column("isDelete", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("listTopic", new TableInfo.Column("listTopic", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourse.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCourse = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCourse = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCourse = new TableInfo("Course", _columnsCourse, _foreignKeysCourse, _indicesCourse);
        final TableInfo _existingCourse = TableInfo.read(_db, "Course");
        if (! _infoCourse.equals(_existingCourse)) {
          return new RoomOpenHelper.ValidationResult(false, "Course(com.pratham.admin.modalclasses.Course).\n"
                  + " Expected:\n" + _infoCourse + "\n"
                  + " Found:\n" + _existingCourse);
        }
        final HashMap<String, TableInfo.Column> _columnsCommunity = new HashMap<String, TableInfo.Column>(17);
        _columnsCommunity.put("CommunityID", new TableInfo.Column("CommunityID", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("Community", new TableInfo.Column("Community", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("VillageID", new TableInfo.Column("VillageID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("ProgramId", new TableInfo.Column("ProgramId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("GroupID", new TableInfo.Column("GroupID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("CourseAdded", new TableInfo.Column("CourseAdded", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("TopicAdded", new TableInfo.Column("TopicAdded", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("CoachID", new TableInfo.Column("CoachID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("StartDate", new TableInfo.Column("StartDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("EndDate", new TableInfo.Column("EndDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("ParentParticipation", new TableInfo.Column("ParentParticipation", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("PresentStudent", new TableInfo.Column("PresentStudent", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("AddedCourseID", new TableInfo.Column("AddedCourseID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("AddedTopicsID", new TableInfo.Column("AddedTopicsID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("CreatedBy", new TableInfo.Column("CreatedBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("CreatedDate", new TableInfo.Column("CreatedDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommunity.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCommunity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCommunity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCommunity = new TableInfo("Community", _columnsCommunity, _foreignKeysCommunity, _indicesCommunity);
        final TableInfo _existingCommunity = TableInfo.read(_db, "Community");
        if (! _infoCommunity.equals(_existingCommunity)) {
          return new RoomOpenHelper.ValidationResult(false, "Community(com.pratham.admin.modalclasses.Community).\n"
                  + " Expected:\n" + _infoCommunity + "\n"
                  + " Found:\n" + _existingCommunity);
        }
        final HashMap<String, TableInfo.Column> _columnsCompletion = new HashMap<String, TableInfo.Column>(14);
        _columnsCompletion.put("CompletionID", new TableInfo.Column("CompletionID", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("GroupType", new TableInfo.Column("GroupType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("VillageID", new TableInfo.Column("VillageID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("ProgramId", new TableInfo.Column("ProgramId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("GroupID", new TableInfo.Column("GroupID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("CourseCompleted", new TableInfo.Column("CourseCompleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("TopicCompleted", new TableInfo.Column("TopicCompleted", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("StartDate", new TableInfo.Column("StartDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("EndDate", new TableInfo.Column("EndDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("ParentParticipation", new TableInfo.Column("ParentParticipation", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("PresentParents", new TableInfo.Column("PresentParents", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("Event", new TableInfo.Column("Event", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("CreatedBy", new TableInfo.Column("CreatedBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompletion.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCompletion = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCompletion = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCompletion = new TableInfo("Completion", _columnsCompletion, _foreignKeysCompletion, _indicesCompletion);
        final TableInfo _existingCompletion = TableInfo.read(_db, "Completion");
        if (! _infoCompletion.equals(_existingCompletion)) {
          return new RoomOpenHelper.ValidationResult(false, "Completion(com.pratham.admin.modalclasses.Completion).\n"
                  + " Expected:\n" + _infoCompletion + "\n"
                  + " Found:\n" + _existingCompletion);
        }
        final HashMap<String, TableInfo.Column> _columnsGroups = new HashMap<String, TableInfo.Column>(11);
        _columnsGroups.put("GroupId", new TableInfo.Column("GroupId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroups.put("GroupName", new TableInfo.Column("GroupName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroups.put("VillageId", new TableInfo.Column("VillageId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroups.put("ProgramId", new TableInfo.Column("ProgramId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroups.put("GroupCode", new TableInfo.Column("GroupCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroups.put("SchoolName", new TableInfo.Column("SchoolName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroups.put("VIllageName", new TableInfo.Column("VIllageName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroups.put("DeviceId", new TableInfo.Column("DeviceId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroups.put("CreatedBy", new TableInfo.Column("CreatedBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroups.put("CreatedOn", new TableInfo.Column("CreatedOn", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroups.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGroups = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGroups = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGroups = new TableInfo("Groups", _columnsGroups, _foreignKeysGroups, _indicesGroups);
        final TableInfo _existingGroups = TableInfo.read(_db, "Groups");
        if (! _infoGroups.equals(_existingGroups)) {
          return new RoomOpenHelper.ValidationResult(false, "Groups(com.pratham.admin.modalclasses.Groups).\n"
                  + " Expected:\n" + _infoGroups + "\n"
                  + " Found:\n" + _existingGroups);
        }
        final HashMap<String, TableInfo.Column> _columnsStudent = new HashMap<String, TableInfo.Column>(20);
        _columnsStudent.put("GroupId", new TableInfo.Column("GroupId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("GroupName", new TableInfo.Column("GroupName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("FullName", new TableInfo.Column("FullName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("Stud_Class", new TableInfo.Column("Stud_Class", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("Age", new TableInfo.Column("Age", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("Gender", new TableInfo.Column("Gender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("StudentId", new TableInfo.Column("StudentId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("FirstName", new TableInfo.Column("FirstName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("MiddleName", new TableInfo.Column("MiddleName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("LastName", new TableInfo.Column("LastName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("CreatedBy", new TableInfo.Column("CreatedBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("CreatedOn", new TableInfo.Column("CreatedOn", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("UpdatedDate", new TableInfo.Column("UpdatedDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("DOB", new TableInfo.Column("DOB", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("SchoolType", new TableInfo.Column("SchoolType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("GuardianName", new TableInfo.Column("GuardianName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("phoneType", new TableInfo.Column("phoneType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("phoneNo", new TableInfo.Column("phoneNo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudent.put("relation_with_phone_owner", new TableInfo.Column("relation_with_phone_owner", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStudent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStudent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStudent = new TableInfo("Student", _columnsStudent, _foreignKeysStudent, _indicesStudent);
        final TableInfo _existingStudent = TableInfo.read(_db, "Student");
        if (! _infoStudent.equals(_existingStudent)) {
          return new RoomOpenHelper.ValidationResult(false, "Student(com.pratham.admin.modalclasses.Student).\n"
                  + " Expected:\n" + _infoStudent + "\n"
                  + " Found:\n" + _existingStudent);
        }
        final HashMap<String, TableInfo.Column> _columnsGroupSession = new HashMap<String, TableInfo.Column>(12);
        _columnsGroupSession.put("GroupSessionID", new TableInfo.Column("GroupSessionID", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("VillageID", new TableInfo.Column("VillageID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("Village", new TableInfo.Column("Village", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("GroupIDVisited", new TableInfo.Column("GroupIDVisited", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("Group", new TableInfo.Column("Group", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("DateVisited", new TableInfo.Column("DateVisited", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("StartTime", new TableInfo.Column("StartTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("EndTime", new TableInfo.Column("EndTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("CoachPresentInVillage", new TableInfo.Column("CoachPresentInVillage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("WorkCrosscheckedGroupIDs", new TableInfo.Column("WorkCrosscheckedGroupIDs", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("PresentStudents", new TableInfo.Column("PresentStudents", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupSession.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGroupSession = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGroupSession = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGroupSession = new TableInfo("GroupSession", _columnsGroupSession, _foreignKeysGroupSession, _indicesGroupSession);
        final TableInfo _existingGroupSession = TableInfo.read(_db, "GroupSession");
        if (! _infoGroupSession.equals(_existingGroupSession)) {
          return new RoomOpenHelper.ValidationResult(false, "GroupSession(com.pratham.admin.modalclasses.GroupSession).\n"
                  + " Expected:\n" + _infoGroupSession + "\n"
                  + " Found:\n" + _existingGroupSession);
        }
        final HashMap<String, TableInfo.Column> _columnsGroupVisit = new HashMap<String, TableInfo.Column>(13);
        _columnsGroupVisit.put("GroupVisitID", new TableInfo.Column("GroupVisitID", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("VillageID", new TableInfo.Column("VillageID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("Village", new TableInfo.Column("Village", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("DateVisited", new TableInfo.Column("DateVisited", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("StartTime", new TableInfo.Column("StartTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("EndTime", new TableInfo.Column("EndTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("GroupIDVisited", new TableInfo.Column("GroupIDVisited", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("PresentGroupIDs", new TableInfo.Column("PresentGroupIDs", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("WorkCrosscheckedGroupIDs", new TableInfo.Column("WorkCrosscheckedGroupIDs", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("Group", new TableInfo.Column("Group", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("CoachPresentInVillage", new TableInfo.Column("CoachPresentInVillage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("PresentStudents", new TableInfo.Column("PresentStudents", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGroupVisit.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGroupVisit = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGroupVisit = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGroupVisit = new TableInfo("GroupVisit", _columnsGroupVisit, _foreignKeysGroupVisit, _indicesGroupVisit);
        final TableInfo _existingGroupVisit = TableInfo.read(_db, "GroupVisit");
        if (! _infoGroupVisit.equals(_existingGroupVisit)) {
          return new RoomOpenHelper.ValidationResult(false, "GroupVisit(com.pratham.admin.modalclasses.GroupVisit).\n"
                  + " Expected:\n" + _infoGroupVisit + "\n"
                  + " Found:\n" + _existingGroupVisit);
        }
        final HashMap<String, TableInfo.Column> _columnsVillage = new HashMap<String, TableInfo.Column>(7);
        _columnsVillage.put("VillageId", new TableInfo.Column("VillageId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVillage.put("VillageCode", new TableInfo.Column("VillageCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVillage.put("VillageName", new TableInfo.Column("VillageName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVillage.put("Block", new TableInfo.Column("Block", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVillage.put("District", new TableInfo.Column("District", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVillage.put("State", new TableInfo.Column("State", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVillage.put("CRLId", new TableInfo.Column("CRLId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVillage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVillage = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVillage = new TableInfo("Village", _columnsVillage, _foreignKeysVillage, _indicesVillage);
        final TableInfo _existingVillage = TableInfo.read(_db, "Village");
        if (! _infoVillage.equals(_existingVillage)) {
          return new RoomOpenHelper.ValidationResult(false, "Village(com.pratham.admin.modalclasses.Village).\n"
                  + " Expected:\n" + _infoVillage + "\n"
                  + " Found:\n" + _existingVillage);
        }
        final HashMap<String, TableInfo.Column> _columnsMetaData = new HashMap<String, TableInfo.Column>(2);
        _columnsMetaData.put("keys", new TableInfo.Column("keys", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMetaData.put("value", new TableInfo.Column("value", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMetaData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMetaData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMetaData = new TableInfo("MetaData", _columnsMetaData, _foreignKeysMetaData, _indicesMetaData);
        final TableInfo _existingMetaData = TableInfo.read(_db, "MetaData");
        if (! _infoMetaData.equals(_existingMetaData)) {
          return new RoomOpenHelper.ValidationResult(false, "MetaData(com.pratham.admin.modalclasses.MetaData).\n"
                  + " Expected:\n" + _infoMetaData + "\n"
                  + " Found:\n" + _existingMetaData);
        }
        final HashMap<String, TableInfo.Column> _columnsTempStudent = new HashMap<String, TableInfo.Column>(7);
        _columnsTempStudent.put("GroupId", new TableInfo.Column("GroupId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTempStudent.put("GroupName", new TableInfo.Column("GroupName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTempStudent.put("FullName", new TableInfo.Column("FullName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTempStudent.put("Stud_Class", new TableInfo.Column("Stud_Class", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTempStudent.put("Age", new TableInfo.Column("Age", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTempStudent.put("Gender", new TableInfo.Column("Gender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTempStudent.put("StudentId", new TableInfo.Column("StudentId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTempStudent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTempStudent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTempStudent = new TableInfo("TempStudent", _columnsTempStudent, _foreignKeysTempStudent, _indicesTempStudent);
        final TableInfo _existingTempStudent = TableInfo.read(_db, "TempStudent");
        if (! _infoTempStudent.equals(_existingTempStudent)) {
          return new RoomOpenHelper.ValidationResult(false, "TempStudent(com.pratham.admin.modalclasses.TempStudent).\n"
                  + " Expected:\n" + _infoTempStudent + "\n"
                  + " Found:\n" + _existingTempStudent);
        }
        final HashMap<String, TableInfo.Column> _columnsTabTrack = new HashMap<String, TableInfo.Column>(8);
        _columnsTabTrack.put("QR_ID", new TableInfo.Column("QR_ID", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabTrack.put("CRL_ID", new TableInfo.Column("CRL_ID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabTrack.put("CRL_Name", new TableInfo.Column("CRL_Name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabTrack.put("State", new TableInfo.Column("State", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabTrack.put("Pratham_ID", new TableInfo.Column("Pratham_ID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabTrack.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabTrack.put("Serial_NO", new TableInfo.Column("Serial_NO", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabTrack.put("oldFlag", new TableInfo.Column("oldFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTabTrack = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTabTrack = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTabTrack = new TableInfo("TabTrack", _columnsTabTrack, _foreignKeysTabTrack, _indicesTabTrack);
        final TableInfo _existingTabTrack = TableInfo.read(_db, "TabTrack");
        if (! _infoTabTrack.equals(_existingTabTrack)) {
          return new RoomOpenHelper.ValidationResult(false, "TabTrack(com.pratham.admin.modalclasses.TabTrack).\n"
                  + " Expected:\n" + _infoTabTrack + "\n"
                  + " Found:\n" + _existingTabTrack);
        }
        final HashMap<String, TableInfo.Column> _columnsTabletManageDevice = new HashMap<String, TableInfo.Column>(22);
        _columnsTabletManageDevice.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("QR_ID", new TableInfo.Column("QR_ID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("Pratham_ID", new TableInfo.Column("Pratham_ID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("tabSerial_ID", new TableInfo.Column("tabSerial_ID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("assigned_CRL_ID", new TableInfo.Column("assigned_CRL_ID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("assigned_CRL_Name", new TableInfo.Column("assigned_CRL_Name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("logged_CRL_ID", new TableInfo.Column("logged_CRL_ID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("logged_CRL_NAME", new TableInfo.Column("logged_CRL_NAME", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("collectedTabPrathamID", new TableInfo.Column("collectedTabPrathamID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("collectedTabQrID", new TableInfo.Column("collectedTabQrID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("collectedTab_serial_ID", new TableInfo.Column("collectedTab_serial_ID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("collectedTabs_senior", new TableInfo.Column("collectedTabs_senior", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("collected_date", new TableInfo.Column("collected_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("is_Damaged", new TableInfo.Column("is_Damaged", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("damageType", new TableInfo.Column("damageType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("comment", new TableInfo.Column("comment", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("oldFlag", new TableInfo.Column("oldFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("villageName", new TableInfo.Column("villageName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("villageID", new TableInfo.Column("villageID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletManageDevice.put("isPushed", new TableInfo.Column("isPushed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTabletManageDevice = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTabletManageDevice = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTabletManageDevice = new TableInfo("TabletManageDevice", _columnsTabletManageDevice, _foreignKeysTabletManageDevice, _indicesTabletManageDevice);
        final TableInfo _existingTabletManageDevice = TableInfo.read(_db, "TabletManageDevice");
        if (! _infoTabletManageDevice.equals(_existingTabletManageDevice)) {
          return new RoomOpenHelper.ValidationResult(false, "TabletManageDevice(com.pratham.admin.modalclasses.TabletManageDevice).\n"
                  + " Expected:\n" + _infoTabletManageDevice + "\n"
                  + " Found:\n" + _existingTabletManageDevice);
        }
        final HashMap<String, TableInfo.Column> _columnsLogs = new HashMap<String, TableInfo.Column>(11);
        _columnsLogs.put("logId", new TableInfo.Column("logId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogs.put("currentDateTime", new TableInfo.Column("currentDateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogs.put("exceptionMessage", new TableInfo.Column("exceptionMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogs.put("exceptionStackTrace", new TableInfo.Column("exceptionStackTrace", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogs.put("methodName", new TableInfo.Column("methodName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogs.put("errorType", new TableInfo.Column("errorType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogs.put("groupId", new TableInfo.Column("groupId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogs.put("deviceId", new TableInfo.Column("deviceId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogs.put("LogDetail", new TableInfo.Column("LogDetail", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogs.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLogs.put("sessionId", new TableInfo.Column("sessionId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLogs = new TableInfo("Logs", _columnsLogs, _foreignKeysLogs, _indicesLogs);
        final TableInfo _existingLogs = TableInfo.read(_db, "Logs");
        if (! _infoLogs.equals(_existingLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "Logs(com.pratham.admin.modalclasses.Modal_Log).\n"
                  + " Expected:\n" + _infoLogs + "\n"
                  + " Found:\n" + _existingLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsTabletStatus = new HashMap<String, TableInfo.Column>(8);
        _columnsTabletStatus.put("qrID", new TableInfo.Column("qrID", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletStatus.put("loggedCRL_Id", new TableInfo.Column("loggedCRL_Id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletStatus.put("loggedCRL_Name", new TableInfo.Column("loggedCRL_Name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletStatus.put("prathamId", new TableInfo.Column("prathamId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletStatus.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletStatus.put("serialNo", new TableInfo.Column("serialNo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletStatus.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTabletStatus.put("oldFlag", new TableInfo.Column("oldFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTabletStatus = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTabletStatus = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTabletStatus = new TableInfo("TabletStatus", _columnsTabletStatus, _foreignKeysTabletStatus, _indicesTabletStatus);
        final TableInfo _existingTabletStatus = TableInfo.read(_db, "TabletStatus");
        if (! _infoTabletStatus.equals(_existingTabletStatus)) {
          return new RoomOpenHelper.ValidationResult(false, "TabletStatus(com.pratham.admin.modalclasses.TabletStatus).\n"
                  + " Expected:\n" + _infoTabletStatus + "\n"
                  + " Found:\n" + _existingTabletStatus);
        }
        final HashMap<String, TableInfo.Column> _columnsAser = new HashMap<String, TableInfo.Column>(26);
        _columnsAser.put("AserID", new TableInfo.Column("AserID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("StudentId", new TableInfo.Column("StudentId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("TestType", new TableInfo.Column("TestType", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("TestDate", new TableInfo.Column("TestDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("ChildID", new TableInfo.Column("ChildID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("Lang", new TableInfo.Column("Lang", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("Num", new TableInfo.Column("Num", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("OAdd", new TableInfo.Column("OAdd", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("OSub", new TableInfo.Column("OSub", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("OMul", new TableInfo.Column("OMul", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("ODiv", new TableInfo.Column("ODiv", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("WAdd", new TableInfo.Column("WAdd", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("WSub", new TableInfo.Column("WSub", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("FLAG", new TableInfo.Column("FLAG", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("CreatedBy", new TableInfo.Column("CreatedBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("CreatedDate", new TableInfo.Column("CreatedDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("DeviceId", new TableInfo.Column("DeviceId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("GroupID", new TableInfo.Column("GroupID", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("sharedBy", new TableInfo.Column("sharedBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("SharedAtDateTime", new TableInfo.Column("SharedAtDateTime", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("appVersion", new TableInfo.Column("appVersion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("appName", new TableInfo.Column("appName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("CreatedOn", new TableInfo.Column("CreatedOn", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("English", new TableInfo.Column("English", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAser.put("EnglishSelected", new TableInfo.Column("EnglishSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAser = new TableInfo("Aser", _columnsAser, _foreignKeysAser, _indicesAser);
        final TableInfo _existingAser = TableInfo.read(_db, "Aser");
        if (! _infoAser.equals(_existingAser)) {
          return new RoomOpenHelper.ValidationResult(false, "Aser(com.pratham.admin.modalclasses.Aser).\n"
                  + " Expected:\n" + _infoAser + "\n"
                  + " Found:\n" + _existingAser);
        }
        final HashMap<String, TableInfo.Column> _columnsYouth = new HashMap<String, TableInfo.Column>(21);
        _columnsYouth.put("youthId", new TableInfo.Column("youthId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("groupId", new TableInfo.Column("groupId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("groupName", new TableInfo.Column("groupName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("firstName", new TableInfo.Column("firstName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("middleName", new TableInfo.Column("middleName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("lastName", new TableInfo.Column("lastName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("guardianName", new TableInfo.Column("guardianName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("birthDate", new TableInfo.Column("birthDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("gender", new TableInfo.Column("gender", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("maritalStatus", new TableInfo.Column("maritalStatus", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("areyoustudying", new TableInfo.Column("areyoustudying", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("education", new TableInfo.Column("education", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("occupation", new TableInfo.Column("occupation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("haveSmartphone", new TableInfo.Column("haveSmartphone", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("useSmartphone", new TableInfo.Column("useSmartphone", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("wantToJoin", new TableInfo.Column("wantToJoin", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("createdBy", new TableInfo.Column("createdBy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("createdOn", new TableInfo.Column("createdOn", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("isDeleted", new TableInfo.Column("isDeleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsYouth.put("sentFlag", new TableInfo.Column("sentFlag", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysYouth = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesYouth = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoYouth = new TableInfo("Youth", _columnsYouth, _foreignKeysYouth, _indicesYouth);
        final TableInfo _existingYouth = TableInfo.read(_db, "Youth");
        if (! _infoYouth.equals(_existingYouth)) {
          return new RoomOpenHelper.ValidationResult(false, "Youth(com.pratham.admin.modalclasses.Youth).\n"
                  + " Expected:\n" + _infoYouth + "\n"
                  + " Found:\n" + _existingYouth);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8df9062135b8467c3373e5643abbed4a", "4040b9677a871ebd6a917f5028a6e7ad");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "ECEAsmt","Attendance","CRL","CRLmd","Coach","Course","Community","Completion","Groups","Student","GroupSession","GroupVisit","Village","MetaData","TempStudent","TabTrack","TabletManageDevice","Logs","TabletStatus","Aser","Youth");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `ECEAsmt`");
      _db.execSQL("DELETE FROM `Attendance`");
      _db.execSQL("DELETE FROM `CRL`");
      _db.execSQL("DELETE FROM `CRLmd`");
      _db.execSQL("DELETE FROM `Coach`");
      _db.execSQL("DELETE FROM `Course`");
      _db.execSQL("DELETE FROM `Community`");
      _db.execSQL("DELETE FROM `Completion`");
      _db.execSQL("DELETE FROM `Groups`");
      _db.execSQL("DELETE FROM `Student`");
      _db.execSQL("DELETE FROM `GroupSession`");
      _db.execSQL("DELETE FROM `GroupVisit`");
      _db.execSQL("DELETE FROM `Village`");
      _db.execSQL("DELETE FROM `MetaData`");
      _db.execSQL("DELETE FROM `TempStudent`");
      _db.execSQL("DELETE FROM `TabTrack`");
      _db.execSQL("DELETE FROM `TabletManageDevice`");
      _db.execSQL("DELETE FROM `Logs`");
      _db.execSQL("DELETE FROM `TabletStatus`");
      _db.execSQL("DELETE FROM `Aser`");
      _db.execSQL("DELETE FROM `Youth`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ECEAsmtDao.class, ECEAsmtDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AttendanceDao.class, AttendanceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CRLdao.class, CRLdao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LogDao.class, LogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CRLmd_dao.class, CRLmd_dao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TabletStatusDao.class, TabletStatusDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CoachDao.class, CoachDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CourseDao.class, CourseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CommunityDao.class, CommunityDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CompletionDao.class, CompletionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GroupSessionDao.class, GroupSessionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GroupVisitDao.class, GroupVisitDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TabTrackDao.class, TabTrackDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TabletManageDeviceDao.class, TabletManageDeviceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GroupDao.class, GroupDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StudentDao.class, StudentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AserDao.class, AserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VillageDao.class, VillageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MetaDataDao.class, MetaDataDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TempStudentdao.class, TempStudentdao_Impl.getRequiredConverters());
    _typeConvertersMap.put(YouthDao.class, YouthDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public ECEAsmtDao getECEAsmtDao() {
    if (_eCEAsmtDao != null) {
      return _eCEAsmtDao;
    } else {
      synchronized(this) {
        if(_eCEAsmtDao == null) {
          _eCEAsmtDao = new ECEAsmtDao_Impl(this);
        }
        return _eCEAsmtDao;
      }
    }
  }

  @Override
  public AttendanceDao getAttendanceDao() {
    if (_attendanceDao != null) {
      return _attendanceDao;
    } else {
      synchronized(this) {
        if(_attendanceDao == null) {
          _attendanceDao = new AttendanceDao_Impl(this);
        }
        return _attendanceDao;
      }
    }
  }

  @Override
  public CRLdao getCRLdao() {
    if (_cRLdao != null) {
      return _cRLdao;
    } else {
      synchronized(this) {
        if(_cRLdao == null) {
          _cRLdao = new CRLdao_Impl(this);
        }
        return _cRLdao;
      }
    }
  }

  @Override
  public LogDao getLogDao() {
    if (_logDao != null) {
      return _logDao;
    } else {
      synchronized(this) {
        if(_logDao == null) {
          _logDao = new LogDao_Impl(this);
        }
        return _logDao;
      }
    }
  }

  @Override
  public CRLmd_dao getCRLmd_dao() {
    if (_cRLmdDao != null) {
      return _cRLmdDao;
    } else {
      synchronized(this) {
        if(_cRLmdDao == null) {
          _cRLmdDao = new CRLmd_dao_Impl(this);
        }
        return _cRLmdDao;
      }
    }
  }

  @Override
  public TabletStatusDao getTabletStatusDao() {
    if (_tabletStatusDao != null) {
      return _tabletStatusDao;
    } else {
      synchronized(this) {
        if(_tabletStatusDao == null) {
          _tabletStatusDao = new TabletStatusDao_Impl(this);
        }
        return _tabletStatusDao;
      }
    }
  }

  @Override
  public CoachDao getCoachDao() {
    if (_coachDao != null) {
      return _coachDao;
    } else {
      synchronized(this) {
        if(_coachDao == null) {
          _coachDao = new CoachDao_Impl(this);
        }
        return _coachDao;
      }
    }
  }

  @Override
  public CourseDao getCoursesDao() {
    if (_courseDao != null) {
      return _courseDao;
    } else {
      synchronized(this) {
        if(_courseDao == null) {
          _courseDao = new CourseDao_Impl(this);
        }
        return _courseDao;
      }
    }
  }

  @Override
  public CommunityDao getCommunityDao() {
    if (_communityDao != null) {
      return _communityDao;
    } else {
      synchronized(this) {
        if(_communityDao == null) {
          _communityDao = new CommunityDao_Impl(this);
        }
        return _communityDao;
      }
    }
  }

  @Override
  public CompletionDao getCompletionDao() {
    if (_completionDao != null) {
      return _completionDao;
    } else {
      synchronized(this) {
        if(_completionDao == null) {
          _completionDao = new CompletionDao_Impl(this);
        }
        return _completionDao;
      }
    }
  }

  @Override
  public GroupSessionDao getGroupSessionDao() {
    if (_groupSessionDao != null) {
      return _groupSessionDao;
    } else {
      synchronized(this) {
        if(_groupSessionDao == null) {
          _groupSessionDao = new GroupSessionDao_Impl(this);
        }
        return _groupSessionDao;
      }
    }
  }

  @Override
  public GroupVisitDao getGroupVisitDao() {
    if (_groupVisitDao != null) {
      return _groupVisitDao;
    } else {
      synchronized(this) {
        if(_groupVisitDao == null) {
          _groupVisitDao = new GroupVisitDao_Impl(this);
        }
        return _groupVisitDao;
      }
    }
  }

  @Override
  public TabTrackDao getTabTrackDao() {
    if (_tabTrackDao != null) {
      return _tabTrackDao;
    } else {
      synchronized(this) {
        if(_tabTrackDao == null) {
          _tabTrackDao = new TabTrackDao_Impl(this);
        }
        return _tabTrackDao;
      }
    }
  }

  @Override
  public TabletManageDeviceDao getTabletManageDeviceDao() {
    if (_tabletManageDeviceDao != null) {
      return _tabletManageDeviceDao;
    } else {
      synchronized(this) {
        if(_tabletManageDeviceDao == null) {
          _tabletManageDeviceDao = new TabletManageDeviceDao_Impl(this);
        }
        return _tabletManageDeviceDao;
      }
    }
  }

  @Override
  public GroupDao getGroupDao() {
    if (_groupDao != null) {
      return _groupDao;
    } else {
      synchronized(this) {
        if(_groupDao == null) {
          _groupDao = new GroupDao_Impl(this);
        }
        return _groupDao;
      }
    }
  }

  @Override
  public StudentDao getStudentDao() {
    if (_studentDao != null) {
      return _studentDao;
    } else {
      synchronized(this) {
        if(_studentDao == null) {
          _studentDao = new StudentDao_Impl(this);
        }
        return _studentDao;
      }
    }
  }

  @Override
  public AserDao getAserDao() {
    if (_aserDao != null) {
      return _aserDao;
    } else {
      synchronized(this) {
        if(_aserDao == null) {
          _aserDao = new AserDao_Impl(this);
        }
        return _aserDao;
      }
    }
  }

  @Override
  public VillageDao getVillageDao() {
    if (_villageDao != null) {
      return _villageDao;
    } else {
      synchronized(this) {
        if(_villageDao == null) {
          _villageDao = new VillageDao_Impl(this);
        }
        return _villageDao;
      }
    }
  }

  @Override
  public MetaDataDao getMetaDataDao() {
    if (_metaDataDao != null) {
      return _metaDataDao;
    } else {
      synchronized(this) {
        if(_metaDataDao == null) {
          _metaDataDao = new MetaDataDao_Impl(this);
        }
        return _metaDataDao;
      }
    }
  }

  @Override
  public TempStudentdao getTempStudentDao() {
    if (_tempStudentdao != null) {
      return _tempStudentdao;
    } else {
      synchronized(this) {
        if(_tempStudentdao == null) {
          _tempStudentdao = new TempStudentdao_Impl(this);
        }
        return _tempStudentdao;
      }
    }
  }

  @Override
  public YouthDao getYouthDao() {
    if (_youthDao != null) {
      return _youthDao;
    } else {
      synchronized(this) {
        if(_youthDao == null) {
          _youthDao = new YouthDao_Impl(this);
        }
        return _youthDao;
      }
    }
  }
}
