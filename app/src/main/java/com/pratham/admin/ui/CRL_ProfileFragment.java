package com.pratham.admin.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pratham.admin.ApplicationController;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.CRL;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.ui.blockLeader.Inventory.InventoryFragment;
import com.pratham.admin.ui.blockLeader.Inventory.InventoryTabListAdapter;
import com.pratham.admin.ui.home.assignedToMe.DeviceListAdapter;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.Utility;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_crl_profile)
public class CRL_ProfileFragment extends Fragment implements NetworkCallListener, DevicePrathamIdLisner {

    @ViewById(R.id.tv_crlName)
    TextView tv_crlName;

    @ViewById(R.id.tv_crlUsername)
    TextView tv_crlUsername;

    @ViewById(R.id.tv_crlEmail)
    TextView tv_crlEmail;

    @ViewById(R.id.tv_programNameAndBlock)
    TextView tv_programNameAndBlock;

    @ViewById(R.id.rv_tabList)
    RecyclerView rv_tabList;

    CRL crl;

    List<DeviseList> deviceList;
    Context context;
    JSONArray response;
    DeviceListAdapter deviceAdapter;

    public CRL_ProfileFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @AfterViews
    public void init() {
        try {
            crl = requireArguments().getParcelable("TabHolder_Detail");
            tv_crlName.setText(crl.getFirstName());
            tv_programNameAndBlock.setText(crl.getProgramName() + ", " + crl.getBlock());
            tv_crlUsername.setText(crl.getUserName());
            tv_crlEmail.setText(crl.getEmail());
            myDeviceList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeAdapter(JSONArray response) {
        Gson gson = new Gson();
        Type devicesList = new TypeToken<ArrayList<DeviseList>>() {
        }.getType();
        this.context = context;
        deviceList = gson.fromJson(response.toString(), devicesList);
        try {
            deviceAdapter = new DeviceListAdapter(getActivity(), deviceList, CRL_ProfileFragment.this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
            rv_tabList.setLayoutManager(layoutManager);
            rv_tabList.setAdapter(deviceAdapter);
            deviceAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void myDeviceList() {
        //todo check internret connection
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
            //String url = APIs.DeviceList + "6501";
            String url = APIs.DeviceList + crl.getCRLId();
            Log.e("url : ", url);

            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, url, "Loading Devices..", "loading_devises", getActivity());
        } else {
            new AlertDialog.Builder(requireActivity()).setTitle("Warning").setMessage("No internet connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
    }

    @Override
    public void onResponse(String response1, String header) {
        if (header.equals("loading_devises")) {
            response = null;
            try {
                Utility.dismissLoadingDialog();
                response = new JSONArray(response1);
                if (response.length() > 0) {
//                    MyDeviceList myDeviceList = new MyDeviceList(context, response);
//                    myDeviceList.show();
                    initializeAdapter(response);
                    Log.e("response : ", response.toString());
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(requireActivity()).create();
                    alertDialog.setTitle("No Device found");
                    alertDialog.setIcon(R.drawable.ic_error_outline_black_24dp);
                    alertDialog.setButton("OK", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(android.content.DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equals("loading_devises")) {
            Utility.dismissLoadingDialog();
            Toast.makeText(requireActivity(), R.string.chkInternet, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void getPrathamId(String prathamId, String QrId) {

    }

    @Override
    public void setDeviceDetail(String prathamId, String qrId, String deviceId, String serNo, String tabDetail, String tabStatus) {

    }

    @Click(R.id.iv_backButton)
    public void backButton(){
        requireActivity().getSupportFragmentManager().popBackStack();
    }
}