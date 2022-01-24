package com.pratham.admin.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;
import com.pratham.admin.ApplicationController;
import com.pratham.admin.R;
import com.pratham.admin.activities.DeviceInformation.DeviceInformation_;
import com.pratham.admin.activities.Notification.Notification;
import com.pratham.admin.adapters.DashRVDataAdapter;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.database.AppDatabase;
import com.pratham.admin.interfaces.ConnectionReceiverListener;
import com.pratham.admin.interfaces.DashRVClickListener;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.Aser;
import com.pratham.admin.modalclasses.Attendance;
import com.pratham.admin.modalclasses.Coach;
import com.pratham.admin.modalclasses.Community;
import com.pratham.admin.modalclasses.Completion;
import com.pratham.admin.modalclasses.DashboardItem;
import com.pratham.admin.modalclasses.ECEAsmt;
import com.pratham.admin.modalclasses.GroupSession;
import com.pratham.admin.modalclasses.GroupVisit;
import com.pratham.admin.modalclasses.Groups;
import com.pratham.admin.modalclasses.MetaData;
import com.pratham.admin.modalclasses.Modal_Log;
import com.pratham.admin.modalclasses.Student;
import com.pratham.admin.modalclasses.Youth;
import com.pratham.admin.ui.login.LoginActivity_;
import com.pratham.admin.util.BackupDatabase;
import com.pratham.admin.util.BaseActivity;
import com.pratham.admin.util.ConnectionReceiver;
import com.pratham.admin.util.DashRVTouchListener;
import com.pratham.admin.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pratham.admin.util.APIs.PushForms;


public class Dashboard extends BaseActivity implements DashRVClickListener, ConnectionReceiverListener, NetworkCallListener {

    // Ref : https://www.dev2qa.com/android-cardview-with-image-and-text-example/
    String LoggedcrlId = "", LoggedcrlName = "", LoggedCRLnameSwapStd = "";
    DashRVDataAdapter DataAdapter;
    boolean internetIsAvailable = false;
    //@BindView(R.id.tv_appInfo)
    //TextView tv_appInfo;
    @BindView(R.id.changeLog)
    ImageView iv_changeLog;

    MetaData metaData;
    private String deviceID, apkVersion, serialID, WiFiMac, gpsFixDuration;
    private List<DashboardItem> DashboardItemList = null;

    List<Attendance> aObj = new ArrayList<>();
    List<Coach> coachesObj = new ArrayList<>();
    List<Community> communitiesObj = new ArrayList<>();
    List<Completion> completionsObj = new ArrayList<>();
    List<GroupSession> GroupSessionObj = new ArrayList<>();
    List<GroupVisit> GroupVisitObj = new ArrayList<>();
    List<Student> stdObj = new ArrayList<>();
    List<Aser> aserObj = new ArrayList<>();
    List<Groups> grpObj = new ArrayList<>();
    List<ECEAsmt> ECEAsmtObj = new ArrayList<>();
    List<Modal_Log> LogObj = new ArrayList<>();
    List<MetaData> metaDataList = new ArrayList<>();
    List<Youth> youthObj = new ArrayList<>();

    String json = "";
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        // Hide Actionbar
//        getSupportActionBar().hide();

        checkConnection();
        //backup Database
        BackupDatabase backupDatabase = new BackupDatabase();
        backupDatabase.backup(this);
        LoggedcrlId = getIntent().getStringExtra("CRLid");
        LoggedcrlName = getIntent().getStringExtra("CRLname");
        LoggedCRLnameSwapStd = getIntent().getStringExtra("CRLnameSwapStd");

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
                       // Toast.makeText(Dashboard.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        // Recycler View
        initializeItemList();

        // Create the recyclerview.
        RecyclerView dashRecyclerView = (RecyclerView) findViewById(R.id.card_view_recycler_list);
        // Create the grid layout manager with 2 columns.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        // Set layout manager.
        dashRecyclerView.setLayoutManager(gridLayoutManager);

        // Create recycler view data adapter with item list.
        DataAdapter = new DashRVDataAdapter(DashboardItemList);
        // Set data adapter.
        dashRecyclerView.setAdapter(DataAdapter);

        dashRecyclerView.addOnItemTouchListener(new DashRVTouchListener(getApplicationContext(), dashRecyclerView, Dashboard.this));

        populateProgramID();

        populateApkVersion();

        iv_changeLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_changelog, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Change Log");
                alertDialog.setMessage(Html.fromHtml(getString(R.string.change_log)));
                alertDialog.show();
            }
        });

    }

    private void populateApkVersion() {
        metaData = new MetaData();
        PackageInfo pInfo = null;
        String verCode = "";
        try {
            pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            verCode = pInfo.versionName;
            metaData.setKeys("apkVersion");
            metaData.setValue(verCode);
            AppDatabase.getDatabaseInstance(this).getMetaDataDao().insertMetadata(metaData);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void populateProgramID() {
        SharedPreferences preferences = this.getSharedPreferences("prathamInfo", Context.MODE_PRIVATE);
        //String program = preferences.getString("program", "null");
        String programID = preferences.getString("programId", "null");
        //Toast.makeText(this, programID, Toast.LENGTH_SHORT).show();
        /*int pid = 0;
        switch (program){
            case APIs.HL:
                pid = 1;
                break;
            case APIs.UP:
                //todo urban
                pid = 6;
                break;
            case APIs.KGBV:
                pid = 5;
                break;
            case APIs.ECE:
                pid = 8;
                break;
            case RI:
                pid = 2;
                break;
            case SC:
                pid = 3;
                break;
            case PI:
                pid = 10;
                break;
            case HG:
                pid = 13;
                break;
            case GP:
                pid = 14;
                break;
            case DSP:
                pid = 22;
                break;
            case RIM:
                pid = 11;
                break;
        }*/
        /*if (program.contains("Learning"))
            pid = 1;
        else if (program.contains("India"))
            pid = 2;
        else if (program.contains("Institutes"))
            pid = 10;
        else if (program.contains("Second"))
            pid = 3;
        else if (program.contains("Urban"))
            pid = 6;
        else if (program.contains("Gaon"))
            pid = 13;
        else if (program.contains("Government"))
            pid = 14;
        else if (program.contains("ECE"))
            pid = 8;
        else if (program.contains("KGBV"))
            pid = 5;*/

        metaData = new MetaData();
        metaData.setKeys("ProgramID");
        metaData.setValue(programID);
        AppDatabase.getDatabaseInstance(this).getMetaDataDao().insertMetadata(metaData);
    }

/*    private void initializeAppInfo() {
        deviceID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        metaData = new MetaData();
        metaData.setKeys("DeviceID");
        metaData.setValue(deviceID);
        AppDatabase.getDatabaseInstance(this).getMetaDataDao().insertMetadata(metaData);

        serialID = android.os.Build.SERIAL;
        metaData = new MetaData();
        metaData.setKeys("SerialID");
        metaData.setValue(serialID);

        AppDatabase.getDatabaseInstance(this).getMetaDataDao().insertMetadata(metaData);

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            apkVersion = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        WiFiMac = wInfo.getMacAddress();
        metaData = new MetaData();
        metaData.setKeys("WiFiMac");
        metaData.setValue(WiFiMac);
        AppDatabase.getDatabaseInstance(this).getMetaDataDao().insertMetadata(metaData);


        tv_appInfo.setText("Apk Version : " + apkVersion + "\t\t\tWiFi-MAC : " + WiFiMac +
                "\nDevice ID : " + deviceID + "\t\t\tSerial ID : " + serialID);
    }*/

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            internetIsAvailable = false;
            Toast.makeText(this, R.string.noInterntCon, Toast.LENGTH_SHORT).show();
        } else {
            internetIsAvailable = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
        ApplicationController.getInstance().setConnectionListener(this);
        //initializeAppInfo();
    }

    private void checkConnection() {
        boolean isConnected = ConnectionReceiver.isConnected();
        if (!isConnected) {
            internetIsAvailable = false;
        } else {
            internetIsAvailable = true;
        }
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


    public void pushNewData() {

        checkConnection();

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
        youthObj = AppDatabase.getDatabaseInstance(this).getYouthDao().getNewYouths(0);

        // Push To Server
        try {
            if (internetIsAvailable) {

                // Preview
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Dashboard.this, android.R.style.Theme_Material_Light_Dialog);
                dialogBuilder.setCancelable(false);
                dialogBuilder.setTitle(R.string.dataPreview);

                // Prepare Data
                Gson gson = new Gson();
                metaData = new MetaData();
/*                metaData.setKeys("pushDataTime");
                metaData.setValue(new Utility().GetCurrentDateTime(false));*/
                metaDataList = AppDatabase.getDatabaseInstance(this).getMetaDataDao().getAllMetaData();
                String metaDataJSON = customParse(metaDataList);

                json = "{ \"AttendanceJSON\":" + "" + gson.toJson(aObj).toString()
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
                        + ",\"YouthJSON\":" + "" + gson.toJson(youthObj).toString()
                        + ",\"metadata\":" + "" + metaDataJSON + "}";

                Log.d("json all push :::", json);

                // Preview Message
                dialogBuilder.setTitle("Push Data Preview");
                dialogBuilder.setMessage(getString(R.string.attendance) + aObj.size()
                        + "\n"+getString(R.string.coaches) + coachesObj.size()
                        + "\n"+getString(R.string.communities) + communitiesObj.size()
                        + "\n"+getString(R.string.completions) + completionsObj.size()
                        + "\n"+getString(R.string.students) + stdObj.size()
                        + "\n"+getString(R.string.asers) + aserObj.size()
                        + "\n"+getString(R.string.groups) + grpObj.size()
                        + "\n"+getString(R.string.groupvst) + GroupVisitObj.size()
                        + "\n"+getString(R.string.groupsesn) + GroupSessionObj.size()
                        + "\n"+getString(R.string.eceasmnt) + ECEAsmtObj.size()
                        + "\n"+getString(R.string.youths) + youthObj.size()
                        + "\n"+getString(R.string.logs) + LogObj.size()
                );


                dialogBuilder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        try {
                            // Push Process
                            pd = new ProgressDialog(Dashboard.this);
                            pd.setTitle(R.string.uploading);
                            pd.setCancelable(false);
                            pd.setCanceledOnTouchOutside(false);
                            pd.show();

                            if ((aObj.size() == 0) && (coachesObj.size() == 0) && (communitiesObj.size() == 0) && (completionsObj.size() == 0)
                                    && (GroupVisitObj.size() == 0) && (GroupSessionObj.size() == 0)
                                    && (stdObj.size() == 0) && (aserObj.size() == 0) && (grpObj.size() == 0) && (ECEAsmtObj.size() == 0) && (youthObj.size() == 0 )&& (LogObj.size() == 0)
                                /*&& (CRLVisitObj.size() == 0)*/) {
                                // No Data Available
                                showDialog(false);
                                Toast.makeText(Dashboard.this, "No New Data found for Pushing !", Toast.LENGTH_LONG).show();
                                Log.d("json not pushed:::", json);
                                if (pd.isShowing())
                                    pd.dismiss();
                            } else {
                                pd.dismiss();
                                NetworkCalls.getNetworkCallsInstance(Dashboard.this).postRequest(Dashboard.this, PushForms, "uploading...", json, "push_forms");
                               /* AndroidNetworking.post(PushForms).setContentType("application/json").addStringBody(json).build().getAsString(new StringRequestListener() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("responce", response);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getMetaDataDao().insertMetadata(metaData);
                                        showDialog(true);
                                        // update flag
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getAttendanceDao().updateAllSentFlag(1);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getCoachDao().updateAllSentFlag(1);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getCommunityDao().updateAllSentFlag(1);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getCompletionDao().updateAllSentFlag(1);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getGroupSessionDao().updateAllSentFlag(1);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getGroupVisitDao().updateAllSentFlag(1);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getStudentDao().updateAllSentFlag(1);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getAserDao().updateAllSentFlag(1);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getGroupDao().updateAllSentFlag(1);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getECEAsmtDao().updateAllSentFlag(1);
                                        AppDatabase.getDatabaseInstance(Dashboard.this).getLogDao().updateAllSentFlag(1);


                                        if (pd.isShowing())
                                            pd.dismiss();
                                    }

                                    @Override
                                    public void onError(ANError anError) {
                                        new BlurPopupWindow.Builder(Dashboard.this)
                                                .setContentView(R.layout.app_failure_dialog)
                                                .setGravity(Gravity.CENTER)
                                                .setScaleRatio(0.2f)
                                                .setDismissOnClickBack(true)
                                                .setDismissOnTouchBackground(true)
                                                .setBlurRadius(10)
                                                .setTintColor(0x30000000)
                                                .build()
                                                .show();
//                                        No need to reset flag as All the Data will be either sent or not at all
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getAttendanceDao().updateAllSentFlag(0);
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getCoachDao().updateAllSentFlag(0);
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getCommunityDao().updateAllSentFlag(0);
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getCompletionDao().updateAllSentFlag(0);
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getGroupVisitDao().updateAllSentFlag(0);
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getGroupSessionDao().updateAllSentFlag(0);
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getStudentDao().updateAllSentFlag(0);
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getAserDao().updateAllSentFlag(0);
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getGroupDao().updateAllSentFlag(0);
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getECEAsmtDao().updateAllSentFlag(0);
//                                        AppDatabase.getDatabaseInstance(Dashboard.this).getLogDao().updateAllSentFlag(0);

                                        Toast.makeText(Dashboard.this, "Error in Data Pushing !", Toast.LENGTH_LONG).show();
                                        if (pd.isShowing())
                                            pd.dismiss();
                                    }
                                });*/
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
                });

                dialogBuilder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });

                AlertDialog b = dialogBuilder.create();
                b.show();
            } else {
                //No Internet
                try {
                    new BlurPopupWindow.Builder(Dashboard.this)
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

    private void showDialog(boolean flag) {
        SharedPreferences preferences = this.getSharedPreferences("prathamInfo", Context.MODE_PRIVATE);
        String lastOfflineSavedDate = preferences.getString("offlineSaveTime", "null");
        List tempList = AppDatabase.getDatabaseInstance(this).getTempStudentDao().getAllempStudent();
        CustomDialog customDialog;
        if (tempList.size() >= 1) {
            customDialog = new CustomDialog(this, tempList, LoggedcrlName, lastOfflineSavedDate);
            customDialog.show();
        } else {
            if (flag) {
                BlurPopupWindow.Builder blurPopupWindow = new BlurPopupWindow.Builder(Dashboard.this);
                blurPopupWindow.setContentView(R.layout.app_success_dialog)
                        .setGravity(Gravity.CENTER)
                        .setScaleRatio(0.2f)
                        .setDismissOnClickBack(true)
                        .setDismissOnTouchBackground(true)
                        .setBlurRadius(10)
                        .setTintColor(0x30000000)
                        .setAnimationDuration(2)
                        .build()
                        .show();
            }
        }

    }

    /* Initialise items in list. */
    private void initializeItemList() {
        if (DashboardItemList == null) {
            DashboardItemList = new ArrayList<DashboardItem>();
            DashboardItemList.add(new DashboardItem(getString(R.string.forms), R.drawable.ic_form));
            DashboardItemList.add(new DashboardItem(getString(R.string.studentManagement), R.drawable.ic_pos));
            DashboardItemList.add(new DashboardItem(getString(R.string.pushdata), R.drawable.ic_push));
            DashboardItemList.add(new DashboardItem(getString(R.string.managedevice), R.drawable.tablet));
            DashboardItemList.add(new DashboardItem(getString(R.string.notifications), R.drawable.ic_notifications_none_black_24dp));
            DashboardItemList.add(new DashboardItem(getString(R.string.deviceinfo), R.drawable.deviceinfo));
        }
    }

    @Override
    public void onClick(View view, int position) {
        DashboardItem Dash = DashboardItemList.get(position);
        String name = Dash.getName();

        if (name.contains("Scan")) {
            Intent intent = new Intent(Dashboard.this, Activity_QRScan.class);
            intent.putExtra("CRLid", LoggedcrlId);
            intent.putExtra("CRLname", LoggedcrlName);
            startActivity(intent);
        } else if (name.contains("Swap")) {
            Intent intent = new Intent(Dashboard.this, SwapStudentsActivity.class);
            intent.putExtra("CRLname", LoggedCRLnameSwapStd);
            startActivity(intent);
        } else if (name.contains(getString(R.string.forms))) {
            Intent intent = new Intent(Dashboard.this, FormsActivity.class);
            intent.putExtra("CRLid", LoggedcrlId);
            intent.putExtra("CRLname", LoggedcrlName);
            intent.putExtra("CRLnameSwapStd", LoggedCRLnameSwapStd);
            startActivity(intent);
        } else if (name.contains(getString(R.string.studentManagement))) {
            Intent intent = new Intent(Dashboard.this, Student_Management.class);
            intent.putExtra("CRLid", LoggedcrlId);
            intent.putExtra("CRLname", LoggedcrlName);
            intent.putExtra("CRLnameSwapStd", LoggedCRLnameSwapStd);
            startActivity(intent);
        } else if (name.contains(getString(R.string.pushdata))) {
            pushNewData();

        } else if (name.contains(getString(R.string.managedevice))) {
            Intent intent = new Intent(Dashboard.this, ManageDevice.class);
            intent.putExtra("CRLid", LoggedcrlId);
            intent.putExtra("CRLname", LoggedcrlName);
            startActivity(intent);

        } else if (name.contains(getString(R.string.notifications))) {
            Intent intent = new Intent(Dashboard.this, Notification.class);
            intent.putExtra("CRLid", LoggedcrlId);
            intent.putExtra("CRLname", LoggedcrlName);
            startActivity(intent);
        } else if (name.contains(getString(R.string.deviceinfo))) {
            Intent intent = new Intent(Dashboard.this, DeviceInformation_.class);
            intent.putExtra("CRLid", LoggedcrlId);
            intent.putExtra("CRLname", LoggedcrlName);
            startActivity(intent);
        }
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public void onResponse(String response, String header) {
        if (header.equals("push_forms")) {
            Log.d("responce", response);
            AppDatabase.getDatabaseInstance(Dashboard.this).getMetaDataDao().insertMetadata(metaData);
            showDialog(true);
            // update flag
            AppDatabase.getDatabaseInstance(Dashboard.this).getAttendanceDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getCoachDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getCommunityDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getCompletionDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getGroupSessionDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getGroupVisitDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getStudentDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getAserDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getGroupDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getECEAsmtDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getYouthDao().updateAllSentFlag(1);
            AppDatabase.getDatabaseInstance(Dashboard.this).getLogDao().updateAllSentFlag(1);

        }
    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equals("push_forms")) {
            new BlurPopupWindow.Builder(Dashboard.this)
                    .setContentView(R.layout.app_failure_dialog)
                    .setGravity(Gravity.CENTER)
                    .setScaleRatio(0.2f)
                    .setDismissOnClickBack(true)
                    .setDismissOnTouchBackground(true)
                    .setBlurRadius(10)
                    .setTintColor(0x30000000)
                    .build()
                    .show();
            Toast.makeText(Dashboard.this, "Error in Data Pushing !", Toast.LENGTH_LONG).show();

//           No need to reset flag as All the Data will be either sent or not at all
//           AppDatabase.getDatabaseInstance(Dashboard.this).getAttendanceDao().updateAllSentFlag(0);
//           AppDatabase.getDatabaseInstance(Dashboard.this).getCoachDao().updateAllSentFlag(0);
//           AppDatabase.getDatabaseInstance(Dashboard.this).getCommunityDao().updateAllSentFlag(0);
//           AppDatabase.getDatabaseInstance(Dashboard.this).getCompletionDao().updateAllSentFlag(0);
//           AppDatabase.getDatabaseInstance(Dashboard.this).getGroupVisitDao().updateAllSentFlag(0);
//           AppDatabase.getDatabaseInstance(Dashboard.this).getGroupSessionDao().updateAllSentFlag(0);
//           AppDatabase.getDatabaseInstance(Dashboard.this).getStudentDao().updateAllSentFlag(0);
//           AppDatabase.getDatabaseInstance(Dashboard.this).getAserDao().updateAllSentFlag(0);
//           AppDatabase.getDatabaseInstance(Dashboard.this).getGroupDao().updateAllSentFlag(0);
//           AppDatabase.getDatabaseInstance(Dashboard.this).getECEAsmtDao().updateAllSentFlag(0);
//           AppDatabase.getDatabaseInstance(Dashboard.this).getLogDao().updateAllSentFlag(0);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity_.class);
        startActivity(intent);
        finish();
    }
}
