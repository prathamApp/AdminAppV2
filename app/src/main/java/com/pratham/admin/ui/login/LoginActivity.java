package com.pratham.admin.ui.login;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;
import com.pratham.admin.ApplicationController;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.database.AppDatabase;
import com.pratham.admin.interfaces.ConnectionReceiverListener;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.Aser;
import com.pratham.admin.modalclasses.Attendance;
import com.pratham.admin.modalclasses.CRL;
import com.pratham.admin.modalclasses.Coach;
import com.pratham.admin.modalclasses.Community;
import com.pratham.admin.modalclasses.Completion;
import com.pratham.admin.modalclasses.ECEAsmt;
import com.pratham.admin.modalclasses.EventMessage;
import com.pratham.admin.modalclasses.GroupSession;
import com.pratham.admin.modalclasses.GroupVisit;
import com.pratham.admin.modalclasses.Groups;
import com.pratham.admin.modalclasses.MetaData;
import com.pratham.admin.modalclasses.Modal_Log;
import com.pratham.admin.modalclasses.Model_TabletCount;
import com.pratham.admin.modalclasses.Model_User;
import com.pratham.admin.modalclasses.Student;
import com.pratham.admin.ui.home.Home_;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.BackupDatabase;
import com.pratham.admin.util.BaseActivity;
import com.pratham.admin.util.ConnectionReceiver;
import com.pratham.admin.util.LocaleManager;
import com.pratham.admin.util.AA_Constants;
import com.pratham.admin.util.PermissionResult;
import com.pratham.admin.util.PermissionUtils;
import com.pratham.admin.util.Utility;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.pratham.admin.util.APIs.PushForms;
import static com.pratham.admin.util.ActivityManagePermission.isPermissionsGranted;

@SuppressLint("NonConstantResourceId")
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements ConnectionReceiverListener, PermissionResult, NetworkCallListener {

    @ViewById(R.id.userName)
    EditText userName;

    @ViewById(R.id.password)
    EditText password;

    @ViewById(R.id.programInfo)
    LinearLayout programInfoLayout;
    @ViewById(R.id.tv_selProg)
    TextView selProg;
    @ViewById(R.id.tv_selState)
    TextView selState;
    @ViewById(R.id.tv_selVillage)
    TextView selVillage;

    @ViewById(R.id.tv_english)
    TextView engLang;
    @ViewById(R.id.tv_hindi)
    TextView hinLang;
    @ViewById(R.id.tv_updateApp)
    TextView updateApp;

    @ViewById(R.id.masterSync)
    LinearLayout masterSync;

    @ViewById(R.id.loginScreen)
    RelativeLayout rl_loginScreen;

    String lastOfflineSavedDate;
    String loggerName, userID, reportingPersonId, reportingPersonName, roleId, programId, programName, stateCode;
    boolean internetIsAvailable = false;
    private PermissionResult permissionResult;
    private String permissionsAsk[];
    private final int KEY_PERMISSION = 200;

    boolean isUpdateClicked = false;
    Context context;
    Locale myLocale;
    String currentLanguage = "en", currentLang;
    String hindiLang = "hi";
    String englishLang = "en";

    @AfterViews
    public void init() {
        context = LoginActivity.this;
        addMetaData();
//        Toast.makeText(this, "New Version", Toast.LENGTH_SHORT).show();
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)) {
            String[] permissionArray = new String[]{PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE, PermissionUtils.Manifest_CAMERA,
                    PermissionUtils.Manifest_ACCESS_FINE_LOCATION, PermissionUtils.Manifest_RECORD_AUDIO, PermissionUtils.Manifest_CALL_PHONE,
                    PermissionUtils.Manifest_SEND_SMS};
            if (!isPermissionsGranted(LoginActivity.this, permissionArray))
                askCompactPermissions(permissionArray, this);
        }

        // check connection & then upgrade latest version if available
/*        ApplicationController.getInstance().setConnectionListener(this);
        checkConnection();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FIREBASE", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        //  String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("FIREBASE", token);
                        //Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = pinfo.versionName;
            SharedPreferences preferences = this.getSharedPreferences("prathamInfo", Context.MODE_PRIVATE);
            String version = preferences.getString("version", "null");
            if (!versionName.equals(version)) {
                //  clearData();
                SharedPreferences sharedPref = this.getSharedPreferences("prathamInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("version", versionName);
                editor.commit();
            }
        } catch (PackageManager.NameNotFoundException e) {
            Modal_Log log = new Modal_Log();
            log.setCurrentDateTime(new Utility().GetCurrentDate());
            log.setErrorType("ERROR");
            log.setExceptionMessage(e.getMessage());
            log.setExceptionStackTrace(e.getStackTrace().toString());
            log.setMethodName("MainActivity" + "_" + "packageNameOnCreate");
            log.setDeviceId("");
            AppDatabase.getDatabaseInstance(ApplicationController.getInstance()).getLogDao().insertLog(log);
            BackupDatabase.backup(ApplicationController.getInstance());

            e.printStackTrace();
            Toast.makeText(ApplicationController.getInstance(), "On Exception data cleared", Toast.LENGTH_SHORT).show();
            clearData();
        }*/
    }

    //Add meta date to db
    public void addMetaData() {
        MetaData metaData;

        metaData = new MetaData();
        metaData.setKeys("DeviceName");
        metaData.setValue(Utility.getDeviceName());
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);


        metaData = new MetaData();
        metaData.setKeys("DeviceID");
        metaData.setValue("" + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("SerialID");
        metaData.setValue(Utility.getDeviceSerialID());
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("WiFiMac");
        metaData.setValue(Utility.getWifiMac(context));
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("osVersionNum");
        metaData.setValue(Utility.getAndroidOSVersion());
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("osVersionName");
        metaData.setValue(Utility.getOSVersion());
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("osApiLevel");
        metaData.setValue("" + Utility.getApiLevel());
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("internalAvailableStorage");
        metaData.setValue(Utility.getInternalStorageStatus());
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("screenResolution");
        metaData.setValue(Utility.getDeviceResolution(this));
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("manufacturer");
        metaData.setValue(Utility.getDeviceManufacturer());
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("model");
        metaData.setValue(Utility.getDeviceModel());
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("apkVersion");
        metaData.setValue(Utility.getAppVersion(this));
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("apkDate");
        metaData.setValue(AA_Constants.apkDate);
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

        metaData = new MetaData();
        metaData.setKeys("DBVersion");
        metaData.setValue("" + AppDatabase.DB_VERSION);
        AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);

    }


    public void askCompactPermissions(String permissions[], PermissionResult permissionResult) {
        permissionsAsk = permissions;
        this.permissionResult = permissionResult;
        internalRequestPermission(permissionsAsk);
    }

    public boolean isPermissionGranted(Context context, String permission) {
        boolean granted = ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED));
        return granted;
    }


    private void internalRequestPermission(String[] permissionAsk) {
        String arrayPermissionNotGranted[];
        ArrayList<String> permissionsNotGranted = new ArrayList<>();

        for (int i = 0; i < permissionAsk.length; i++) {
            if (!isPermissionGranted(LoginActivity.this, permissionAsk[i])) {
                permissionsNotGranted.add(permissionAsk[i]);
            }
        }

        if (permissionsNotGranted.isEmpty()) {

            if (permissionResult != null)
                permissionResult.permissionGranted();
        } else {

            arrayPermissionNotGranted = new String[permissionsNotGranted.size()];
            arrayPermissionNotGranted = permissionsNotGranted.toArray(arrayPermissionNotGranted);
            ActivityCompat.requestPermissions(LoginActivity.this, arrayPermissionNotGranted, KEY_PERMISSION);
        }
    }

    @Subscribe
    public void onMessageEvent(EventMessage eventMessage) {
        if (eventMessage.getMessage().equalsIgnoreCase("GOT")) {
            EventMessage startUpdate = new EventMessage();
            startUpdate.setMessage("CHECK_UPDATE");
            EventBus.getDefault().post(startUpdate);
        } else if (eventMessage.getMessage().equalsIgnoreCase("UPDATE_AVAILABLE")) {
            Log.e("#", "AVAILABLE");
            showUpdateButton();
        }
    }

    public void showUpdateButton() {
        updateApp.setVisibility(View.VISIBLE);
        updateApp.setOnClickListener(v -> {
            EventMessage startUpdate = new EventMessage();
            startUpdate.setMessage("START_UPDATE");
            EventBus.getDefault().post(startUpdate);
            updateApp.setVisibility(View.GONE);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*userName.setText("ganeshtupe54");
        password.setText("pratham");*/
//        userName.setText("pravinthorat");
//        password.setText("pratham123");

/*                userName.setText("adminappblock");//test user
        password.setText("pratham123");*/

/*        userName.setText("amolmoghe");
        password.setText("pratham@123");*/

/*        userName.setText("sanchar");//test vendor
        password.setText("sanchar@123");*/

/*        userName.setText("dl_store6628");//test storemanager
        password.setText("Pratham@123");*/


        //userName.setText("");
        //password.setText("");
/*        userName.requestFocus();
        SharedPreferences preferences = this.getSharedPreferences("prathamInfo", Context.MODE_PRIVATE);
        String program = preferences.getString("program", "null");
        String state = preferences.getString("state", "null");
        String village = preferences.getString("village", "null");
        lastOfflineSavedDate = preferences.getString("offlineSaveTime", "null");*/
/*        if ((!program.equals("null")) && (!state.equals("null")) && (!village.equals("null"))) {
            programInfoLayout.setVisibility(View.VISIBLE);
            masterSync.setVisibility(View.GONE);
            selProg.setText(program);
            selState.setText(state);
            selVillage.setText(village);
        } else {
            programInfoLayout.setVisibility(View.GONE);
            masterSync.setVisibility(View.VISIBLE);
        }*/
    }

/*
    private void checkConnection() {

        try {
            boolean isConnected = ConnectionReceiver.isConnected();
            if (!isConnected) {
                internetIsAvailable = false;
            } else {
                internetIsAvailable = true;
                //checkVersion();
            }
        } catch (Exception e) {
            e.printStackTrace();

            Modal_Log log = new Modal_Log();
            log.setCurrentDateTime(new Utility().GetCurrentDate());
            log.setErrorType("ERROR");
            log.setExceptionMessage(e.getMessage());
            log.setExceptionStackTrace(e.getStackTrace().toString());
            log.setMethodName("MainActivity" + "_" + "checkConnection");
            log.setDeviceId("");
            AppDatabase.getDatabaseInstance(ApplicationController.getInstance()).getLogDao().insertLog(log);
            BackupDatabase.backup(ApplicationController.getInstance());
        }
    }
*/

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            internetIsAvailable = false;
            Utility.showSnackbar(context, rl_loginScreen, R.string.noInterntCon);

        } else {
            internetIsAvailable = true;
//            Utility.showSnackbar(context,rl_loginScreen, R.string.backOnline);
        }
    }

    @Override
    public void permissionGranted() {

    }

    @Override
    public void permissionDenied() {
        showPermissionWarningDilog();
    }

    @Override
    public void permissionForeverDenied() {
        showPermissionWarningDilog();
    }

    private void showPermissionWarningDilog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getString(R.string.alert));
        alertDialogBuilder.setMessage("" + "\n" + R.string.denyPermission);

        alertDialogBuilder.setPositiveButton("Ok", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(android.content.DialogInterface dialog, int which) {
                /*UPLOAD TO SERVER*/
                dialog.dismiss();

            }
        });
        alertDialogBuilder.show();
    }

    private String customParse(List<MetaData> metaDataList) {
        String json = "{";

        for (int i = 0; i < metaDataList.size(); i++) {
            json = json + "\"" + metaDataList.get(i).getKeys() + "\":\"" + metaDataList.get(i).getValue() + "\"";
            if (i < metaDataList.size() - 1) {
                json = json + ",";
            }
        }
        json = json + "}";

        return json;
    }

    //push new data when update is available
    public void pushNewData() {
        final List<Attendance> aObj;
        final List<Coach> coachesObj;
        final List<Community> communitiesObj;
        final List<Completion> completionsObj;
        final List<GroupSession> GroupSessionObj;
        final List<GroupVisit> GroupVisitObj;
        final List<Student> stdObj;
        final List<Aser> aserObj;
        final List<Groups> grpObj;
        final List<ECEAsmt> ECEAsmtObj;
        final List<Modal_Log> LogObj;
        List<MetaData> metaDataList;

        aObj = AppDatabase.getDatabaseInstance(this).getAttendanceDao().getNewAttendances(0);
        coachesObj = AppDatabase.getDatabaseInstance(this).getCoachDao().getNewCoaches(0);
        communitiesObj = AppDatabase.getDatabaseInstance(this).getCommunityDao().getNewCommunities(0);
        completionsObj = AppDatabase.getDatabaseInstance(this).getCompletionDao().getNewCompletions(0);
        GroupSessionObj = AppDatabase.getDatabaseInstance(this).getGroupSessionDao().getNewGroupSessions(0);
        GroupVisitObj = AppDatabase.getDatabaseInstance(this).getGroupVisitDao().getNewGroupVisits(0);
        stdObj = AppDatabase.getDatabaseInstance(this).getStudentDao().getNewStudents(0);
        aserObj = AppDatabase.getDatabaseInstance(this).getAserDao().getNewAser(0);
        grpObj = AppDatabase.getDatabaseInstance(this).getGroupDao().getNewGroups(0);
        ECEAsmtObj = AppDatabase.getDatabaseInstance(this).getECEAsmtDao().getNewECEAsmt(0);
        LogObj = AppDatabase.getDatabaseInstance(this).getLogDao().getAllLogs(0);

        // Push To Server
        try {
            if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {

                // Preview
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this, android.R.style.Theme_Material_Light_Dialog);
                dialogBuilder.setCancelable(false);
                dialogBuilder.setTitle(R.string.dataPreview);

                // Prepare Data
                Gson gson = new Gson();
                MetaData metaData = new MetaData();
/*                metaData.setKeys("pushDataTime");
                metaData.setValue(new Utility().GetCurrentDateTime(false));*/
                metaDataList = AppDatabase.getDatabaseInstance(this).getMetaDataDao().getAllMetaData();
                String metaDataJSON = customParse(metaDataList);

                final String json = "{ \"AttendanceJSON\":" + "" + gson.toJson(aObj).toString()
                        + ",\"CoachesJSON\":" + "" + gson.toJson(coachesObj).toString()
                        + ",\"CommunitiesJSON\":" + "" + gson.toJson(communitiesObj).toString()
                        + ",\"CompletionsJSON\":" + "" + gson.toJson(completionsObj).toString()
                        + ",\"StudentJSON\":" + "" + gson.toJson(stdObj).toString()
                        + ",\"AserJSON\":" + "" + gson.toJson(aserObj).toString()
                        + ",\"GroupsJSON\":" + "" + gson.toJson(grpObj).toString()
                        + ",\"GroupVisitsJSON\":" + "" + gson.toJson(GroupVisitObj).toString()
                        + ",\"GroupSessionJSON\":" + "" + gson.toJson(GroupSessionObj).toString()
                        + ",\"ECEAsmtJSON\":" + "" + gson.toJson(ECEAsmtObj).toString()
                        + ",\"LogJSON\":" + "" + gson.toJson(LogObj).toString()
                        + ",\"metadata\":" + "" + metaDataJSON + "}";

                // Preview Message
                dialogBuilder.setTitle(R.string.pushDataPreview);
                dialogBuilder.setMessage("Attendance : " + aObj.size()
                        + "\nCoaches : " + coachesObj.size()
                        + "\nCommunities : " + communitiesObj.size()
                        + "\nCompletions : " + completionsObj.size()
                        + "\nStudents : " + stdObj.size()
                        + "\nAsers : " + aserObj.size()
                        + "\nGroups : " + grpObj.size()
                        + "\nGroupVisits : " + GroupVisitObj.size()
                        + "\nGroupSessions : " + GroupSessionObj.size()
                        + "\nECE Assessments : " + ECEAsmtObj.size()
                        + "\nLogs : " + LogObj.size()
                );


                dialogBuilder.setPositiveButton(R.string.confirm, new android.content.DialogInterface.OnClickListener() {
                    public void onClick(android.content.DialogInterface dialog, int whichButton) {

                        try {
                            // Push Process
                            ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                            pd.setTitle(R.string.uploading);
                            pd.setCancelable(false);
                            pd.setCanceledOnTouchOutside(false);
                            pd.show();

                            if ((aObj.size() == 0) && (coachesObj.size() == 0) && (communitiesObj.size() == 0) && (completionsObj.size() == 0)
                                    && (GroupVisitObj.size() == 0) && (GroupSessionObj.size() == 0)
                                    && (stdObj.size() == 0) && (aserObj.size() == 0) && (grpObj.size() == 0) && (ECEAsmtObj.size() == 0) && (LogObj.size() == 0)) {
                                // No Data Available
                                //updateApp();
                                Toast.makeText(LoginActivity.this, R.string.noNewDataPush, Toast.LENGTH_LONG).show();
                                Log.d("json not pushed:::", json);
                                if (pd.isShowing())
                                    pd.dismiss();
                            } else {
                                pd.dismiss();
                                NetworkCalls.getNetworkCallsInstance(LoginActivity.this).postRequest(LoginActivity.this, PushForms, "uploading...", json, "push_forms");
                            }
                        } catch (Exception e) {
                            Modal_Log log = new Modal_Log();
                            log.setCurrentDateTime(new Utility().GetCurrentDate());
                            log.setErrorType("ERROR");
                            log.setExceptionMessage(e.getMessage());
                            log.setExceptionStackTrace(e.getStackTrace().toString());
                            log.setMethodName("Dashboard" + "_" + "PushNewData");
                            log.setDeviceId("");
                            AppDatabase.getDatabaseInstance(ApplicationController.getInstance()).getLogDao().insertLog(log);
                            BackupDatabase.backup(ApplicationController.getInstance());

                            e.printStackTrace();
                        }

                        dialog.dismiss();

                    }
                });

                dialogBuilder.setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(android.content.DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        isUpdateClicked = false;
                    }
                });

                AlertDialog b = dialogBuilder.create();
                b.show();
            } else {
                //No Internet
                try {
                    new BlurPopupWindow.Builder(LoginActivity.this)
                            .setContentView(R.layout.app_failure_dialog)
                            .setGravity(Gravity.CENTER)
                            .setScaleRatio(0.2f)
                            .setDismissOnClickBack(true)
                            .setDismissOnTouchBackground(true)
                            .setBlurRadius(10)
                            .setTintColor(0x30000000)
                            .build()
                            .show();
                } catch (Exception e) {

                    Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            Modal_Log log = new Modal_Log();
            log.setCurrentDateTime(new Utility().GetCurrentDate());
            log.setErrorType("ERROR");
            log.setExceptionMessage(e.getMessage());
            log.setExceptionStackTrace(e.getStackTrace().toString());
            log.setMethodName("Dashboard" + "_" + "PushNewData");
            log.setDeviceId("");
            AppDatabase.getDatabaseInstance(ApplicationController.getInstance()).getLogDao().insertLog(log);
            BackupDatabase.backup(ApplicationController.getInstance());

            e.printStackTrace();
        }
    }

    @Click(R.id.btn_login)
    public void loginCheck(View view) {
        //boolean userPass = false;

/*        List<CRL> Crl = AppDatabase.getDatabaseInstance(this).getCRLdao().getAllCRLs();
        for (int i = 0; i < Crl.size(); i++) {
            if ((Crl.get(i).getUserName().equals(CRLuserName)) && (Crl.get(i).getPassword().equals(CRLpassword))) {

                String crlID = AppDatabase.getDatabaseInstance(this).getMetaDataDao().getCrlMetaData();
                if (crlID != null) {
                    if (!(crlID.equals(Crl.get(i).getCRLId()))) {
                        AppDatabase.getDatabaseInstance(this).getTempStudentDao().deleteTempStudent();
                    }
                }
                MetaData metaData = new MetaData();
                metaData.setKeys("CRLloginTime");
//                    metaData.setValue(DateFormat.getDateTimeInstance().format(new Date()));
                metaData.setValue(new Utility().GetCurrentDateTime(false));
                AppDatabase.getDatabaseInstance(this).getMetaDataDao().insertMetadata(metaData);

                metaData.setKeys("CRL_ID");
                metaData.setValue(Crl.get(i).getCRLId());
                AppDatabase.getDatabaseInstance(this).getMetaDataDao().insertMetadata(metaData);


                AppDatabase.destroyInstance();
                userName = Crl.get(i).getFullName() + " " + Crl.get(i).getLastName(); //+ " (" + Crl.get(i).getCRLId() + ")";
                this.crlID = Crl.get(i).getCRLId();
                reportingPersonId = Crl.get(i).getReportingPersonId();
                reportingPersonName = Crl.get(i).getReportingPersonName();
                roleId = Crl.get(i).getRoleId();
                programId = Crl.get(i).getProgramId();
                programName = Crl.get(i).getProgramName();
                stateCode = Crl.get(i).getState();
                //showDialog();
                pushDataOnLogin();

                userPass = true;
                break;
            }
        }
        if (!userPass) {
            //todo : use material dialog
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(R.string.invalidCredential);
            alertDialog.setIcon(R.drawable.ic_error_outline_black_24dp);
            alertDialog.setButton("OK", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(android.content.DialogInterface dialog, int which) {
                    userName.setText("");
                    password.setText("");
                    userName.requestFocus();
                }
            });
            alertDialog.show();
        }*/
        if(userName.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            Utility.showSnackbar(this, rl_loginScreen, R.string.enter_unORpass);
        } else {
            String query = null;
            try {
                query = URLEncoder.encode(password.getText().toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //String url = "http://stackoverflow.com/search?q=" + query;
            String url = APIs.loginAPI + userName.getText().toString() + APIs.SERVER_PASSWORD + query;
            //String url = APIs.loginAPI + userName.getText().toString() + APIs.SERVER_PASSWORD + password.getText().toString();
            Log.e("login url : ", url);
            if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
                NetworkCalls.getNetworkCallsInstance(this).getRequestJsonObject(this, url, "Loading...", "loginApi", this);
            } else {
                Utility.showSnackbar(this, rl_loginScreen, R.string.noInterntCon);
            }
        }
    }

    @Override
    public void onResponse(String response, String header) {
        /*if (isUpdateClicked)
            updateApp();*/
        Gson gson = new Gson();
        if (header.equalsIgnoreCase("loginApi")) {
            Model_User user = gson.fromJson(response, Model_User.class);
            //AppDatabase.getDatabaseInstance(context).getUserDao().insertUser(user);
            // Make a db backup if Storage permission granted
            /*if (isPermissionGranted(LoginActivity.this, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE)) {
                // Initiate Backup DB
                BackupDatabase.backup(LoginActivity.this);
            }*/

            loggerName = user.getFullname();
            userID = user.getUserid();
            reportingPersonId = user.getReportingPersonId();
            reportingPersonName = user.getReportingPersonName();
            roleId = user.getRoleid().toString();
            programId = user.getProgid().toString();
            programName = user.getProgname();
            stateCode = user.getStatename();
            openNextActivity();
            Log.e("Login : ", response);
        }
    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equalsIgnoreCase("loginApi")) {
            Log.e("Login Error: ", String.valueOf(anError.getErrorCode()));
            Utility.showSnackbar(this, rl_loginScreen, getString(R.string.invalid_unorpass));
            userName.setText("");
            password.setText("");
            userName.requestFocus();
        }
    }


    @Click(R.id.masterSync)
    public void pullProgramDetails() {
        Intent intent = new Intent(this, SelectProgram_.class);
        startActivity(intent);
    }

    private void showDialog() {
        //todo push data when crl logs in
        pushDataOnLogin();
    }

    private void pushDataOnLogin() {
        List<MetaData> metaDataList;
        // Push To Server
        try {
            if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {

                // Prepare Data
                String metaData = addMetaDataToJson();
                String json = "{ \"ManageDevicesJSON\":" + "" + "[]"
                        + ",\"MetaData\":" + "" + metaData + "}";
                try {
                    // Push Process
                    ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                    pd.setTitle("UPLOADING ... ");
                    pd.setCancelable(false);
                    pd.setCanceledOnTouchOutside(false);
                    pd.show();

                    NetworkCalls.getNetworkCallsInstance(LoginActivity.this).postRequest(LoginActivity.this, APIs.ReplaceTab, "uploading...", json, "push_forms");
                    pd.dismiss();
                } catch (Exception e) {
                    Modal_Log log = new Modal_Log();
                    log.setCurrentDateTime(new Utility().GetCurrentDate());
                    log.setErrorType("ERROR");
                    log.setExceptionMessage(e.getMessage());
                    log.setExceptionStackTrace(e.getStackTrace().toString());
                    log.setMethodName("Dashboard" + "_" + "PushNewData");
                    log.setDeviceId("");
                    AppDatabase.getDatabaseInstance(ApplicationController.getInstance()).getLogDao().insertLog(log);
                    BackupDatabase.backup(ApplicationController.getInstance());

                    e.printStackTrace();
                }
            } else {
                //No Internet
                Toast.makeText(this, R.string.noInterntCon, Toast.LENGTH_SHORT).show();
            }

            openNextActivity();

        } catch (Exception e) {
            Modal_Log log = new Modal_Log();
            log.setCurrentDateTime(new Utility().GetCurrentDate());
            log.setErrorType("ERROR");
            log.setExceptionMessage(e.getMessage());
            log.setExceptionStackTrace(e.getStackTrace().toString());
            log.setMethodName("Dashboard" + "_" + "PushNewData");
            log.setDeviceId("");
            AppDatabase.getDatabaseInstance(ApplicationController.getInstance()).getLogDao().insertLog(log);
            BackupDatabase.backup(ApplicationController.getInstance());

            e.printStackTrace();
        }
    }

    private String addMetaDataToJson() {

//        MetaData metaData = new MetaData();
/*        metaData.setKeys("pushDataTime");
        metaData.setValue(new Utility().GetCurrentDateTime(false));*/
        List<MetaData> metaDataList = AppDatabase.getDatabaseInstance(this).getMetaDataDao().getAllMetaData();
        String metaDataJSON = customParse(metaDataList);
//        AppDatabase.getDatabaseInstance(this).getMetaDataDao().insertMetadata(metaData);
        return metaDataJSON;
    }

    @Click(R.id.btn_clearData)
    public void clearData() {
        userName.setText("");
        password.setText("");
        AppDatabase.getDatabaseInstance(this).getGroupDao().deleteAllGroups();
        AppDatabase.getDatabaseInstance(this).getAserDao().deleteAllAser();
        AppDatabase.getDatabaseInstance(this).getStudentDao().deleteAllStudents();
        AppDatabase.getDatabaseInstance(this).getVillageDao().deleteAllVillages();
        AppDatabase.getDatabaseInstance(this).getCRLdao().deleteAllCRLs();
        AppDatabase.getDatabaseInstance(this).getECEAsmtDao().deleteAllECEAsmt();
        AppDatabase.getDatabaseInstance(this).getAttendanceDao().deleteAllAttendances();
        AppDatabase.getDatabaseInstance(this).getTempStudentDao().deleteTempStudent();
        AppDatabase.getDatabaseInstance(this).getCoachDao().deleteAllCoaches();
        AppDatabase.getDatabaseInstance(this).getCoursesDao().deleteAllCourses();
        AppDatabase.getDatabaseInstance(this).getCommunityDao().deleteAllCommunity();
        AppDatabase.getDatabaseInstance(this).getCompletionDao().deleteAllCompletion();
        AppDatabase.getDatabaseInstance(this).getGroupSessionDao().deleteAllGroupSession();
        AppDatabase.getDatabaseInstance(this).getGroupVisitDao().deleteAllGroupVisit();
        AppDatabase.getDatabaseInstance(this).getLogDao().deleteLogs();
        AppDatabase.getDatabaseInstance(this).getYouthDao().deleteAllYouths();
        AppDatabase.destroyInstance();
        SharedPreferences preferences = this.getSharedPreferences("prathamInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();

        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = pinfo.versionName;
            SharedPreferences pref = this.getSharedPreferences("prathamInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = pref.edit();
            ed.putString("version", versionName);
            ed.commit();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.tv_english)
    public void setEngLang() {
        setNewLocale(this, LocaleManager.ENGLISH);
    }

    @Click(R.id.tv_hindi)
    public void setHinLang() {
        setNewLocale(this, LocaleManager.HINDI);
    }

    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);
        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public void openNextActivity() {
        Intent intent = new Intent(LoginActivity.this, Home_.class);
        FastSave.getInstance().saveString("CRLid", userID);
        FastSave.getInstance().saveString("CRLname", loggerName);
        FastSave.getInstance().saveString("reportingPersonId", reportingPersonId);
        FastSave.getInstance().saveString("reportingPersonName", reportingPersonName);
        FastSave.getInstance().saveString("roleId", roleId);
        FastSave.getInstance().saveString("programId", programId);
        FastSave.getInstance().saveString("programName", programName);
        FastSave.getInstance().saveString("stateCode", stateCode);
        intent.putExtra("CRLid", userID);
        intent.putExtra("CRLname", loggerName);
        intent.putExtra("CRLnameSwapStd", userName + "(" + userID + ")");
        intent.putExtra("localeName", hindiLang);
        intent.putExtra("reportingPersonId", reportingPersonId);
        intent.putExtra("reportingPersonName", reportingPersonName);
        startActivity(intent);
        finish();
    }
}