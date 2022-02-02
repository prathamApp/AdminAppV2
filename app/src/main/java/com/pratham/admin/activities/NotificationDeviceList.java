package com.pratham.admin.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pratham.admin.R;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.ui.home.assignedToMe.DeviceListAdapter;
import com.pratham.admin.ui.home.assignedToMe.NotificationDeviceListAdapter;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NotificationDeviceList extends Dialog {
    RecyclerView recyclerView;
    List<DeviseList> deviceList;
    Context context;

    public NotificationDeviceList(@NonNull Context context, List<DeviseList> response) {
        super(context, android.R.style.Theme_Material_Light_Dialog);
        this.context = context;
        deviceList = response;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_device_list_dilog);
        if (deviceList.isEmpty()) {
            setTitle("No Device Found");
        } else {
            setTitle("List of Device");
        }
        recyclerView = findViewById(R.id.recycler_view);
        NotificationDeviceListAdapter deviceAdapter = new NotificationDeviceListAdapter(context, deviceList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(deviceAdapter);
        deviceAdapter.notifyDataSetChanged();
    }
}
