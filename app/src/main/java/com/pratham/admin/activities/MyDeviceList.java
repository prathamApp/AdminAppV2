package com.pratham.admin.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pratham.admin.R;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.ui.home.assignedToMe.DeviceListAdapter;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MyDeviceList extends Dialog {
    RecyclerView recyclerView;
    List<DeviseList> deviceList;
    Context context;

    public MyDeviceList(@NonNull Context context, JSONArray response) {
        super(context, android.R.style.Theme_Material_Light_DarkActionBar);
        Gson gson = new Gson();
        Type devicesList = new TypeToken<ArrayList<DeviseList>>() {
        }.getType();
        this.context = context;
        deviceList = gson.fromJson(response.toString(), devicesList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_device_list_dilog);
        if (deviceList.isEmpty()) {
            setTitle("No Device Found");
        } else {
            setTitle("Select Device From The List");
        }
        recyclerView = findViewById(R.id.recycler_view);
        DeviceListAdapter deviceAdapter = new DeviceListAdapter(context, deviceList, (DevicePrathamIdLisner) MyDeviceList.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(deviceAdapter);
        deviceAdapter.notifyDataSetChanged();
    }
}
