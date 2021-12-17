package com.pratham.admin.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;
import com.pratham.admin.R;
import com.pratham.admin.adapters.tempAdapter;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.database.AppDatabase;
import com.pratham.admin.interfaces.ConnectionReceiverListener;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.MetaData;
import com.pratham.admin.modalclasses.Student;
import com.pratham.admin.modalclasses.TempStudent;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.ConnectionReceiver;
import com.pratham.admin.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomDialog extends Dialog implements ConnectionReceiverListener, NetworkCallListener {
    @BindView(R.id.btn_pushData)
    Button btn_pushData;
    @BindView(R.id.clear_changes)
    TextView clear_changes;
    @BindView(R.id.btn_close_village)
    ImageButton btn_close;
    @BindView(R.id.txt_cancel)
    TextView txt_cancel;
    @BindView(R.id.txt_count_village)
    TextView txt_count;
    @BindView(R.id.txt_message_village)
    TextView txt_message;
    @BindView(R.id.flowLayout)
    RecyclerView recyclerView_unUploaded;

    List<TempStudent> tempList;
    Context context;
    String crlName;
    String lastOfflineSavedDate, program;
    boolean internetIsAvailable = false;
    // DialogInterface dialogInterface;

    public CustomDialog(@NonNull Context context, List tempList, String crlName, String lastOfflineSavedDate) {
        super(context, android.R.style.Theme_Holo_Dialog_NoActionBar);
        this.tempList = tempList;
        this.context = context;
        this.crlName = crlName;
        //  this.dialogInterface = (DialogInterface) context;
        this.lastOfflineSavedDate = lastOfflineSavedDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        checkConnection();
        SharedPreferences preferences = context.getSharedPreferences("prathamInfo", Context.MODE_PRIVATE);
        program = preferences.getString("program", "null");
        txt_message.setText("Following data changed on " + lastOfflineSavedDate);
        txt_count.setText("Total Students:" + tempList.size());
        tempAdapter tempAdapter = new tempAdapter(tempList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView_unUploaded.setLayoutManager(layoutManager);
        recyclerView_unUploaded.setAdapter(tempAdapter);
        tempAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.txt_cancel)
    public void skip() {
        dismiss();
        //  dialogInterface.openNextActivity();
    }

    @OnClick(R.id.btn_close_village)
    public void closeDialog() {
        dismiss();
    }

    @OnClick(R.id.clear_changes)
    public void clearChanges() {
        AppDatabase.getDatabaseInstance(context).getTempStudentDao().deleteTempStudent();
        dismiss();
        // dialogInterface.openNextActivity();
    }

    @OnClick(R.id.btn_pushData)
    public void uploadChanges() {
        if (internetIsAvailable) {
            if (tempList != null) {
                Gson gson = new Gson();
                String updatedStudentJSON = gson.toJson(tempList);
                MetaData metaData = new MetaData();
                metaData.setKeys("pushDataTime");
//                metaData.setValue(DateFormat.getDateTimeInstance().format(new Date()));
                metaData.setValue(new Utility().GetCurrentDateTime(false));

                AppDatabase.getDatabaseInstance(context).getMetaDataDao().insertMetadata(metaData);
                List<MetaData> metaDataList = AppDatabase.getDatabaseInstance(context).getMetaDataDao().getAllMetaData();
                String metaDataJSON = customParse(metaDataList);//gson.toJson(metaDataList);
                String json = "{ \"student\":" + updatedStudentJSON + ",\"metadata\":" + metaDataJSON + "}";
                AppDatabase.destroyInstance();
                dismiss();
                switch (program) {
                    //todo urban programd new program push link
                    default:
                        uploadAPI(APIs.HLpushToServerURL, json);
                        break;
                  /*  case APIs.UP:
                        uploadAPI(APIs.HLpushToServerURL, json);
                        break;*/
                   /* case APIs.RI:
                        uploadAPI(APIs.RIpushToServerURL, json);
                        break;*/
                   /* case APIs.SC:
                        uploadAPI(APIs.SCpushToServerURL, json);
                        break;
                    case APIs.PI:
                        uploadAPI(APIs.PIpushToServerURL, json);
                        break;
                    case APIs.DSP:
                        uploadAPI(APIs.PIpushToServerURL, json);
                        break;*/

                }
            }
        } else {
            checkConnection();
            Toast.makeText(context, R.string.noInterntCon, Toast.LENGTH_SHORT).show();
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

    private void uploadAPI(String url, String json) {
        NetworkCalls.getNetworkCallsInstance(context).postRequest(this, url, "UPLOADING ... ", json, "custum_Dilaog");
        /*final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("UPLOADING ... ");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        AndroidNetworking.post(url).setContentType("application/json").addStringBody(json).build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                Log.d("responce", response);
                updateLocalDB();
                dialog.dismiss();
                Toast.makeText(context, "pushed SuccessFully", Toast.LENGTH_LONG).show();
                BlurPopupWindow.Builder blurPopupWindow = new BlurPopupWindow.Builder(context);
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
                //dialogInterface.openNextActivity();
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(context, "NO Internet Connection", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });*/
    }

    private void updateLocalDB() {
        List convertedTempToStud = new ArrayList();
        for (int i = 0; i < tempList.size(); i++) {
            Student student = new Student();
            student.setStudentId(tempList.get(i).getStudentId());
            student.setAge(tempList.get(i).getAge());
            student.setFullName(tempList.get(i).getFullName());
            student.setGender(tempList.get(i).getGender());
            student.setGroupId(tempList.get(i).getGroupId());
            student.setGroupName(tempList.get(i).getGroupName());
            student.setStud_Class(tempList.get(i).getStud_Class());
            convertedTempToStud.add(student);
        }
        AppDatabase.getDatabaseInstance(context).getStudentDao().updateAllStudent(convertedTempToStud);
        AppDatabase.getDatabaseInstance(context).getTempStudentDao().deleteTempStudent();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            internetIsAvailable = false;
        } else {
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
    public void onResponse(String response, String header) {
        if (header.equals("custum_Dilaog")) {
            Log.d("responce", response);
            updateLocalDB();
            Toast.makeText(context, R.string.pushSuccess, Toast.LENGTH_LONG).show();
            BlurPopupWindow.Builder blurPopupWindow = new BlurPopupWindow.Builder(context);
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

    @Override
    public void onError(ANError anError, String header) {
        if (header.equals("custum_Dilaog")) {
            Toast.makeText(context, R.string.noInterntCon, Toast.LENGTH_LONG).show();
        }
    }
}

