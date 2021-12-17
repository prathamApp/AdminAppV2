package com.pratham.admin.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import com.pratham.admin.R;
import com.pratham.admin.adapters.QRScanAdapter;
import com.pratham.admin.interfaces.QRRecyclerListener;
import com.pratham.admin.interfaces.QRScanListener;
import com.pratham.admin.modalclasses.TabTrack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CustomDialogQRActivity extends Dialog implements QRRecyclerListener {
    @BindView(R.id.count)
    TextView txt_count;
    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    List<TabTrack> changesList;
    Context context;
    Activity_QRScan activity_qrScan;
    QRScanListener qrScanListener;
    QRScanAdapter qrScanAdapter;

    public CustomDialogQRActivity(@NonNull Context context, List changesList) {
        super(context, android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        this.changesList = changesList;
        this.context = context;
        activity_qrScan = (Activity_QRScan) context;
        qrScanListener = (QRScanListener) context;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        AppDatabase.getDatabaseInstance(context).getTabTrackDao().deleteAllTabTracks();
//        AppDatabase.getDatabaseInstance(context).getTabTrackDao().insertAllTabTrack(changesList);
        activity_qrScan.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_list_dialog);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        message.setText(R.string.uploadChange);
        setCount();
    //    qrScanAdapter = new QRScanAdapter(this, changesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(qrScanAdapter);
        qrScanAdapter.notifyDataSetChanged();
    }

    public void setCount() {
        txt_count.setText("Total Changed CRL's : " + changesList.size());
    }

    @OnClick(R.id.txt_Ok)
    public void update() {
        qrScanListener.update();
    }


    @OnClick(R.id.txt_clear_changes_village)
    public void clearChangesList() {
        qrScanListener.clearChanges();
        this.dismiss();
    }

    @Override
    public void delete(int position) {
        String qrId = changesList.get(position).getQR_ID();
        deleteElement(qrId);
//        AppDatabase.getDatabaseInstance(context).getTabTrackDao().deleteTabTracks(qrId);
//        changesList.remove(position);
        setCount();
        qrScanAdapter.notifyDataSetChanged();
        if (changesList.isEmpty()) {
            this.dismiss();
        }
    }

    private void deleteElement(String qrId) {
        for (int i = 0; i < changesList.size(); i++) {
            if (changesList.get(i).getQR_ID().equalsIgnoreCase(qrId)) {
                changesList.remove(i);
            }
        }
    }
}
