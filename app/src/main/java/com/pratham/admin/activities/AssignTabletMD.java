package com.pratham.admin.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.Result;
import com.pratham.admin.ApplicationController;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.database.AppDatabase;
import com.pratham.admin.interfaces.ConnectionReceiverListener;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.interfaces.QRScanListener;
import com.pratham.admin.modalclasses.CRL;
import com.pratham.admin.modalclasses.CrlInfoRecycler;
import com.pratham.admin.modalclasses.MetaData;
import com.pratham.admin.modalclasses.Modal_Log;
import com.pratham.admin.modalclasses.TabletManageDevice;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.BackupDatabase;
import com.pratham.admin.util.BaseActivity;
import com.pratham.admin.util.ConnectionReceiver;
import com.pratham.admin.util.ROll_ID;
import com.pratham.admin.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class AssignTabletMD extends BaseActivity implements ZXingScannerView.ResultHandler, ConnectionReceiverListener, QRScanListener, DevicePrathamIdLisner, NetworkCallListener {
    final String ASSIGN = "Assign";
    final String RETURN = "Return";
    public ZXingScannerView mScannerView;
    @BindView(R.id.program)
    Spinner programSpinner;
    @BindView(R.id.roleCrl)
    Spinner roleCrlSpinner;
    /*@BindView(R.id.block)
    Spinner blockSpinner;*/
    @BindView(R.id.assignedCrlName)
    TextView assignedCrlName;
    @BindView(R.id.assignedCrlId)
    TextView assignedCrlId;
    @BindView(R.id.crlName)
    Spinner crlNameSpinner;
    @BindView(R.id.qr_frame)
    FrameLayout qr_frame;
    @BindView(R.id.qr_pratham_id)
    EditText qr_pratham_id;
    @BindView(R.id.qr_serialNo)
    EditText qr_serialNo;
    @BindView(R.id.txt_count_village)
    TextView txt_count;
    String prathamId;
    String QrId;
    @BindView(R.id.successMessage)
    LinearLayout successMessage;
    @BindView(R.id.isDamaged)
    Spinner isDamaged;
    @BindView(R.id.damageType)
    Spinner damageType;
    @BindView(R.id.comments)
    EditText comments;
    List<CRL> allCRLlist;
    boolean permission = false;
    String LoggedcrlName;
    String LoggedcrlId;
    String assignedCRL = "";
    CustomDialogQRScan_MD customDialogQRScan_md;
    //    List<TabletManageDevice> tabletMD;
    boolean internetIsAvailable = false;
    String tabStatus, isDamagedText, damageTypeText;
    List<CrlInfoRecycler> crlList_md = new ArrayList<>();
    MyDeviceList myDeviceList;
    ProgressDialog progressDialog;
    String[] options;
    private Context context;


    List<TabletManageDevice> mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_tablet);
        ButterKnife.bind(this);
        context = AssignTabletMD.this;
        allCRLlist = new ArrayList<>();
        mainList = new ArrayList<>();
        LoggedcrlId = getIntent().getStringExtra("CRLid");
        LoggedcrlName = getIntent().getStringExtra("CRLname");
        tabStatus = getIntent().getStringExtra("tabStatus");
        androidx.appcompat.app.ActionBar ab = getSupportActionBar();


        if (tabStatus.equals(ASSIGN)) {
            ab.setTitle("Assign Tablet");
            isDamaged.setVisibility(View.GONE);
            damageType.setVisibility(View.GONE);
            comments.setVisibility(View.GONE);
        } else if (tabStatus.equals(RETURN)) {
            ab.setTitle("Return Tablet");
            isDamaged.setVisibility(View.VISIBLE);
            damageType.setVisibility(View.VISIBLE);
            comments.setVisibility(View.VISIBLE);
            setListenerToReturnTabletSpinner();
        }
        loadCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
        Utility.clearCheckInternetInstance();
        //  int crlCount = AppDatabase.getDatabaseInstance(context).getCRLmd_dao().getCRLs_mdCount();
        int crlCount = AppDatabase.getDatabaseInstance(context).getCRLdao().getCRLsCount();
        if (crlCount <= 0) {
            Toast.makeText(context, R.string.pleasepulldata, Toast.LENGTH_SHORT).show();
            AlertDialog builder = new AlertDialog.Builder(this).create();
            builder.setTitle(R.string.noCrlAvailable);
            builder.setIcon(R.drawable.ic_error_outline_black_24dp);
            builder.setCancelable(false);
            builder.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AssignTabletMD.this.finish();
                }
            });
            builder.show();

        } else {
            setRules();
           /* List<TabletManageDevice> oldList = AppDatabase.getDatabaseInstance(this).getTabletManageDeviceDao().getAllAssignAndReturnDevice();
            if (!oldList.isEmpty()) {
                for (int i = 0; i < oldList.size(); i++) {
                    oldList.get(i).setOldFlag(true);
                }
                AppDatabase.getDatabaseInstance(this).getTabletManageDeviceDao().insertTabletAllManageDevice(oldList);
                oldList.clear();
            }*/

            loadSpinner();
            setCount();
        }
    }

    private void setRules() {

        String role = AppDatabase.getDatabaseInstance(this).getCRLdao().getCRLsRoleById(LoggedcrlId);
        if (tabStatus.equals(ASSIGN)) {
            switch (role) {
                case ROll_ID.BRG_CRL_Tutor:
                    options = new String[]{};
                    break;
                case ROll_ID.Block_Head:
                    options = new String[]{ROll_ID.BRG_CRL_Tutor};
                    break;
                case ROll_ID.District_Head:
                    options = new String[]{ROll_ID.Block_Head, ROll_ID.BRG_CRL_Tutor};
                    break;
                case ROll_ID.State_Program_Head:
                    options = new String[]{ROll_ID.District_Head, ROll_ID.Block_Head, ROll_ID.BRG_CRL_Tutor};
                    break;
                case ROll_ID.Program_Head:
                    options = new String[]{ROll_ID.State_Program_Head};
                    break;
                case ROll_ID.Vendor:
                    options = new String[]{ROll_ID.Block_Head, ROll_ID.District_Head, ROll_ID.State_Program_Head, ROll_ID.BRG_CRL_Tutor};
                    break;

                case ROll_ID.Admin:
                    options = new String[]{ROll_ID.BRG_CRL_Tutor, ROll_ID.Block_Head, ROll_ID.District_Head, ROll_ID.State_Program_Head, ROll_ID.Vendor};
                    break;
            }
        } else if (tabStatus.equals(RETURN)) {
            switch (role) {
                case ROll_ID.BRG_CRL_Tutor:
                    options = new String[]{ROll_ID.Block_Head, ROll_ID.District_Head};
                    break;
                case ROll_ID.Block_Head:
                    options = new String[]{ROll_ID.State_Program_Head, ROll_ID.Vendor};
                    break;
                case ROll_ID.District_Head:
                    options = new String[]{ROll_ID.State_Program_Head, ROll_ID.Vendor};
                    break;
                case ROll_ID.State_Program_Head:
                    options = new String[]{ROll_ID.Program_Head, ROll_ID.Vendor};
                    break;
                case ROll_ID.Program_Head:
                    options = new String[]{ROll_ID.Vendor};
                    break;
                case ROll_ID.Vendor:
                    options = new String[]{ROll_ID.Admin};
                    break;
                case ROll_ID.Admin:
                    options = new String[]{ROll_ID.Admin};
                    break;
            }
        }
    }

    private void setListenerToReturnTabletSpinner() {
        isDamaged.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                isDamagedText = adapterView.getSelectedItem().toString();

                if (pos > 0) {
                    if (isDamagedText.equals("No")) {
                        damageType.setSelection(0);
                        damageType.setEnabled(false);
                        damageTypeText = "";
                    } else {
                        damageType.setEnabled(true);
                    }
                } else {
                    damageType.setSelection(0);
                    damageType.setEnabled(false);
                    damageTypeText = "";
                    isDamagedText = "No";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        damageType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                damageTypeText = adapterView.getSelectedItem().toString();
                if (pos == 0) {
                    damageTypeText = "";
                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadSpinner() {
        //load spinner program From DB
        List<String> programList = new ArrayList<>();
        //List<String> stateList = new ArrayList<>();

        //List<String> crlList_md = new ArrayList<>();

        programList.add("Select Program");
        // programList.addAll(AppDatabase.getDatabaseInstance(context).getCRLmd_dao().getDistinctCRLs_mdProgram());
        for (String rollId : options) {
            programList.addAll(AppDatabase.getDatabaseInstance(context).getCRLdao().getDistinctCRLsdProgram(rollId));
        }
        Set<String> hs = new LinkedHashSet<>();
        hs.addAll(programList);
        programList.clear();
        programList.addAll(hs);
        ArrayAdapter programAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, programList);
        programSpinner.setAdapter(programAdapter);

        programSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                List<String> roleList = new ArrayList<>();
                roleList.add("Select Role");
                if (pos > 0) {
                    // roleList.addAll(AppDatabase.getDatabaseInstance(context).getCRLmd_dao().getDistinctCRLs_mdRoleId());
                    String programName = adapterView.getSelectedItem().toString();
                    for (String rollId : options) {
                        roleList.addAll(AppDatabase.getDatabaseInstance(context).getCRLdao().getDistinctCRLsRoleId(rollId, programName));
                    }
                }
                Set<String> hs = new LinkedHashSet<>();
                hs.addAll(roleList);
                roleList.clear();
                roleList.addAll(hs);
                ArrayAdapter stateAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, roleList);
                roleCrlSpinner.setAdapter(stateAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //load spinner state from DB
        roleCrlSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                crlList_md.clear();
                crlList_md.add(new CrlInfoRecycler("Select Name "));
                if (pos > 0) {
                    //load spinner crl from db
                    String selectedRole = roleCrlSpinner.getSelectedItem().toString();
                    List<CRL> tempList = AppDatabase.getDatabaseInstance(context).getCRLdao().getDistinctCRLsUserName(selectedRole, programSpinner.getSelectedItem().toString());
                    for (CRL crL : tempList) {
                        crlList_md.add(new CrlInfoRecycler(crL.getCRLId(), crL.getFirstName(), crL.getUserName()));
                    }
                }
                ArrayAdapter crlAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, crlList_md);
                crlNameSpinner.setAdapter(crlAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        crlNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                if (pos > 0) {
                    CrlInfoRecycler crlInfoRecycler = (CrlInfoRecycler) adapterView.getSelectedItem();
                    assignedCRL = crlInfoRecycler.getCrlId();
                    assignedCrlName.setText(crlInfoRecycler.getCrlName());
                    assignedCrlId.setText("( ID: " + crlInfoRecycler.getCrlId() + ")");

                } else {
                    assignedCRL = "";
                    assignedCrlName.setText("");
                    assignedCrlId.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadCamera() {

        if (ContextCompat.checkSelfPermission(AssignTabletMD.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.appReqCamPermsn);
                builder.setCancelable(false);
                builder.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(AssignTabletMD.this, new String[]{Manifest.permission.CAMERA}, 1);
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            } else {
                ActivityCompat.requestPermissions(AssignTabletMD.this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        } else {
            initCamera();
            permission = true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, R.string.youNeedCamPermsn, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                initCamera();
                permission = true;
            }
        }

    }

    public void initCamera() {
        mScannerView = new ZXingScannerView(getApplicationContext());
        mScannerView.setResultHandler(AssignTabletMD.this);
        qr_frame.addView((mScannerView));
        mScannerView.startCamera();
        //    mScannerView.resumeCameraPreview(Activity_QRScan.this);
    }

    @Override
    public void handleResult(Result rawResult) {
        QrId = null;
        prathamId = null;
        Log.d("RawResult:::", "****" + rawResult.getText());
        String result = rawResult.getText();
        mScannerView.stopCamera();
        String[] splitted = result.split(":");


        try {
            QrId = splitted[0];
            prathamId = splitted[1];
            if (QrId != null && prathamId != null && splitted.length == 2) {
                /* check prathamID is not none ie for Not blank QR code*/
                if ((!prathamId.equalsIgnoreCase("None"))) {
                    qr_pratham_id.setText(prathamId);
                    successMessage.setVisibility(View.VISIBLE);
                    qr_serialNo.setText("");
                    qr_serialNo.setEnabled(false);

                } else {
                    //todo show dialog
                    if (internetIsAvailable) {
                        String url = APIs.DeviceList + LoggedcrlId;
                        loadDevises(url);
                    } else {
                        checkConnection();
                        new AlertDialog.Builder(this).setTitle(R.string.warning).setMessage(R.string.noInterntCon).setPositiveButton("Close", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                resetCamera();
                                dialog.dismiss();
                            }
                        }).create().show();
                    }
                }
            } else {
                Toast.makeText(this, R.string.invalidQR, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Modal_Log log = new Modal_Log();
            log.setCurrentDateTime(new Utility().GetCurrentDate());
            log.setErrorType("ERROR");
            log.setExceptionMessage(e.getMessage());
            log.setExceptionStackTrace(e.getStackTrace().toString());
            log.setMethodName("AssignTabletMD" + "_" + "handleResult");
            log.setDeviceId("");
            AppDatabase.getDatabaseInstance(ApplicationController.getInstance()).getLogDao().insertLog(log);
            BackupDatabase.backup(ApplicationController.getInstance());

            e.printStackTrace();
            Toast.makeText(this, R.string.invalidQR, Toast.LENGTH_LONG).show();
        }

    }

    private void loadDevises(String url) {
        NetworkCalls.getNetworkCallsInstance(this).getRequest(this, url, "loading Devices...", "loading_device");
      /*  progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("loading Devices");
        progressDialog.setCancelable(false);
        progressDialog.show();
        AndroidNetworking.get(url).setPriority(Priority.MEDIUM).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                myDeviceList = new MyDeviceList(context, response);
                myDeviceList.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        resetCamera();
                    }
                });
                myDeviceList.show();
            }

            @Override
            public void onError(ANError anError) {
                progressDialog.dismiss();
                resetCamera();
                Toast.makeText(context, "Check internet connection", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public void onDestroy() {
        if (mScannerView != null) mScannerView.stopCamera();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (permission) {
            mScannerView.stopCamera();
        }
    }

    @OnClick(R.id.qr_btn_reset)
    public void resetCamera() {
        mScannerView.stopCamera();
        mScannerView.startCamera();
        mScannerView.resumeCameraPreview(AssignTabletMD.this);
        prathamId = "";
        QrId = "";
        qr_pratham_id.setText(prathamId);
        successMessage.setVisibility(View.GONE);
        qr_serialNo.setText("");
        qr_serialNo.setEnabled(true);
    }

    @OnClick(R.id.qr_btn_save)
    public void saveTabTrack() {

        //In case of assign/return  tab serial no is not required prathamId is editable because in case of blank Qr insert manually

        String serialNO = qr_serialNo.getText().toString();
        prathamId = qr_pratham_id.getText().toString();
        if (!assignedCRL.equals("")) {
            if ((!serialNO.equals("")) || !prathamId.equals("")) {
                if (tabStatus.equals(RETURN)) {
                    if ((isDamagedText.equals("Yes") && !damageTypeText.equals("")) || isDamagedText.equals("No")) {
                        TabletManageDevice tabletManageDevice = new TabletManageDevice();
                        tabletManageDevice.setId(Utility.GetUniqueID().toString());
                        tabletManageDevice.setQR_ID(QrId);
                        tabletManageDevice.setStatus(tabStatus);
                        tabletManageDevice.setLogged_CRL_ID(LoggedcrlId);
                        tabletManageDevice.setLogged_CRL_NAME(LoggedcrlName);
                        tabletManageDevice.setAssigned_CRL_ID(assignedCRL);
                        tabletManageDevice.setAssigned_CRL_Name(assignedCrlName.getText().toString());
                        tabletManageDevice.setPratham_ID(prathamId);
                        tabletManageDevice.setTabSerial_ID(serialNO);
                        tabletManageDevice.setIs_Damaged(isDamagedText);
                        tabletManageDevice.setDamageType(damageTypeText);
                        tabletManageDevice.setComment(comments.getText().toString());
                        tabletManageDevice.setOldFlag(false);
                        tabletManageDevice.setDate(new Utility().GetCurrentDateTime(false));
                        tabletManageDevice.setIsPushed(0);
//                        AppDatabase.getDatabaseInstance(this).getTabletManageDeviceDao().insertTabletManageDevice(tabletManageDevice);
                        mainList.add(tabletManageDevice);
                        Toast.makeText(AssignTabletMD.this, R.string.insertedSuccessfuly, Toast.LENGTH_LONG).show();
                        setCount();
                        clearFields();
                        resetCamera();
                    } else {
                        Toast.makeText(context, R.string.selectDamageType, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    //Assigned Tab
                    TabletManageDevice tabletManageDevice = new TabletManageDevice();
                    tabletManageDevice.setId(Utility.GetUniqueID().toString());
                    tabletManageDevice.setQR_ID(QrId);
                    tabletManageDevice.setPratham_ID(prathamId);
                    tabletManageDevice.setTabSerial_ID(serialNO);
                    tabletManageDevice.setStatus(tabStatus);
                    tabletManageDevice.setLogged_CRL_ID(LoggedcrlId);
                    tabletManageDevice.setLogged_CRL_NAME(LoggedcrlName);
                    tabletManageDevice.setAssigned_CRL_ID(assignedCRL);
                    tabletManageDevice.setAssigned_CRL_Name(assignedCrlName.getText().toString());
                    tabletManageDevice.setOldFlag(false);
                    tabletManageDevice.setDate(new Utility().GetCurrentDateTime(false));
                    tabletManageDevice.setIsPushed(0);
//                    AppDatabase.getDatabaseInstance(this).getTabletManageDeviceDao().insertTabletManageDevice(tabletManageDevice);
                    mainList.add(tabletManageDevice);
                    Toast.makeText(AssignTabletMD.this, R.string.insertedSuccessfuly, Toast.LENGTH_LONG).show();
                    setCount();
                    clearFields();
                    resetCamera();
                }
            } else {
                Toast.makeText(context, R.string.scanQrorEnterSid, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.fillAllDetails, Toast.LENGTH_SHORT).show();
        }
    }

    public void setCount() {
//        int count = AppDatabase.getDatabaseInstance(this).getTabletManageDeviceDao().getAllAssignAndReturnDevice().size();
        int count = mainList.size();
        txt_count.setText("Count " + count);
    }

    private void clearFields() {
        crlNameSpinner.setSelection(0);
        isDamaged.setSelection(0);
        comments.setText("");
        qr_pratham_id.setText("");
    }


    @Override
    public void onBackPressed() {
        //todo
//        tabletMD = AppDatabase.getDatabaseInstance(this).getTabletManageDeviceDao().getAllAssignAndReturnDevice();
//        tabletMD = mainList;
        if (!mainList.isEmpty()) {
            customDialogQRScan_md = new CustomDialogQRScan_MD(this, mainList);
            customDialogQRScan_md.show();
            txt_count.setText("Count " + 0);
        } else {
            Log.d("finished", "\"finished\",: ");
            finish();
        }
    }

    private void uploadAPI(String url, String json) {
        NetworkCalls.getNetworkCallsInstance(this).postRequest(this, url, "UPLOADING ... ", json, "AssignTab");

        /*final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("UPLOADING ... ");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();*/
        //todo URL FOR AssignTab
        /*AndroidNetworking.post(url).setContentType("application/json").addStringBody(json).build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                customDialogQRScan_md.dismiss();
                //todo user Based delete
                AppDatabase.getDatabaseInstance(AssignTabletMD.this).getTabletManageDeviceDao().deleteAllTabletManageDevice();
                finish();
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(AssignTabletMD.this, "NO Internet Connection", Toast.LENGTH_LONG).show();
                //Log.d("anError", "" + anError);
                dialog.dismiss();
            }
        });*/
    }

    @Override
    public void update() {
        if (internetIsAvailable) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//            String json = gson.toJson(tabletMD);
            String metadata = addMetaDataToJson();
            String json = "{ \"ManageDevicesJSON\":" + "" + gson.toJson(mainList)
                    + ",\"MetaData\":" + "" + metadata + "}";
            Log.d("@@@@@", json);
            uploadAPI(APIs.AssignReturn, json);
        } else {
            checkConnection();
            Toast.makeText(this, "No Internet Connection...", Toast.LENGTH_SHORT).show();
        }
    }

    private String addMetaDataToJson() {

        MetaData metaData = new MetaData();
        metaData.setKeys("pushDataTime");
        metaData.setValue(new Utility().GetCurrentDateTime(false));
        List<MetaData> metaDataList = AppDatabase.getDatabaseInstance(this).getMetaDataDao().getAllMetaData();
        String metaDataJSON = customParse(metaDataList);
        AppDatabase.getDatabaseInstance(this).getMetaDataDao().insertMetadata(metaData);
        return metaDataJSON;
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

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            Utility.showNoInternetDialog(this);
            internetIsAvailable = false;
        } else {
            Utility.dismissNoInternetDialog();
            internetIsAvailable = true;
        }
    }

    private void checkConnection() {
        boolean isConnected = ConnectionReceiver.isConnected();
        if (!isConnected) {
            internetIsAvailable = false;
        } else {
            internetIsAvailable = true;
        }
    }

    @Override
    public void clearChanges() {
        //todo
//        AppDatabase.getDatabaseInstance(AssignTabletMD.this).getTabletManageDeviceDao().deleteAssignAndReturnDevice();
        mainList.clear();
        customDialogQRScan_md.dismiss();
    }

    @Override
    public void getPrathamId(final String prathamIdNew, final String QrIdNEW) {
        if (QrIdNEW != null && prathamIdNew != null) {
            myDeviceList.dismiss();
            /* check prathamID is not none ie for Not blank QR code*/
            if ((!prathamIdNew.equalsIgnoreCase("None"))) {
              /*  List l = AppDatabase.getDatabaseInstance(this).getTabletManageDeviceDao().checkExistanceTabletManageDevice(QrId);
                if (l.isEmpty()) {*/
                // QrId = QrIdNEW;
                prathamId = prathamIdNew;
                qr_pratham_id.setText(prathamId);
                successMessage.setVisibility(View.VISIBLE);
                qr_serialNo.setText("");
                qr_serialNo.setEnabled(false);

            } else {

            }
        } else {
            Toast.makeText(this, "Invalid QR ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setDeviceDetail(String prathamId, String qrId, String deviceId, String serNo, String tabDetail) {

    }

    @Override
    public void onResponse(String response, String header) {
        if (header.equals("AssignTab")) {
            customDialogQRScan_md.dismiss();
            //todo user Based delete
            mainList.clear();
//            AppDatabase.getDatabaseInstance(context).getTabletManageDeviceDao().updateAssignAndReturnIsPushedFlag();
            // AppDatabase.getDatabaseInstance(AssignTabletMD.this).getTabletManageDeviceDao().deleteAllTabletManageDevice();
            finish();
        } else if (header.equals("loading_device")) {
            try {
                myDeviceList = new MyDeviceList(context, new JSONArray(response));
                myDeviceList.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        resetCamera();
                    }
                });
                myDeviceList.show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equals("AssignTab")) {
            Toast.makeText(AssignTabletMD.this, "NO Internet Connection", Toast.LENGTH_LONG).show();
            //Log.d("anError", "" + anError);
        } else if (header.equals("loading_device")) {
            resetCamera();
            Toast.makeText(context, "Check internet connection", Toast.LENGTH_SHORT).show();
        }
    }
}
