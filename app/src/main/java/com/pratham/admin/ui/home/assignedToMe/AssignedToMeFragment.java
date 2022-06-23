package com.pratham.admin.ui.home.assignedToMe;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.ConnectionReceiver;
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
@EFragment(R.layout.fragment_assigned_to_me)
public class AssignedToMeFragment extends Fragment implements NetworkCallListener, DevicePrathamIdLisner {

    @ViewById(R.id.et_search)
    EditText searchTab;

    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;

    @ViewById(R.id.tv_totalTabs)
    TextView tv_totalTablets;

    boolean internetIsAvailable = false;
    List<DeviseList> deviceList;
    Context context;
    JSONArray response;

    TextView tv_deviceId, tv_tabBrand, tv_serNo, tv_tabModel, tv_tabStatus;
    public BlurPopupWindow detailDialog;

    DeviceListAdapter deviceAdapter;

    public AssignedToMeFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {
        myDeviceList();

        searchTab.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                recyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });
    }

    public void initializeAdapter(JSONArray response) {
        Gson gson = new Gson();
        Type devicesList = new TypeToken<ArrayList<DeviseList>>() {
        }.getType();
        this.context = context;
        deviceList = gson.fromJson(response.toString(), devicesList);
        try {
            deviceAdapter = new DeviceListAdapter(getActivity(), deviceList, AssignedToMeFragment.this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(deviceAdapter);
            deviceAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_totalTablets.setText("Total Tablet(s) :  "+deviceList.size());
    }

    public void myDeviceList() {
        //todo check internret connection
        checkConnection();
        if (internetIsAvailable) {
            String url = APIs.DeviceList + FastSave.getInstance().getString("CRLid", "no_crl");
            loadDevises(url);
        } else {
            checkConnection();
            new AlertDialog.Builder(requireActivity()).setTitle("Warning").setMessage("No internet connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
    }

    @Click(R.id.iv_backButton)
    public void backButton(){
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<DeviseList> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (int i = 0; i < deviceList.size(); i++) {
            final String damageType = deviceList.get(i).getPratham_ID();
            final String fromName = deviceList.get(i).getDeviceid();
            final String fromId = deviceList.get(i).getModel();
            //if the existing elements contains the search input
            if (damageType.toLowerCase().contains(text.toLowerCase())
                    || fromName.toLowerCase().contains(text.toLowerCase())
                    || fromId.toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(deviceList.get(i));
            }
        }
        //calling a method of the adapter class and passing the filtered list
        deviceAdapter.filterList(filterdNames);
        tv_totalTablets.setText("Total Tablets : "+filterdNames.size());
    }

    private void loadDevises(String url) {
        NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, url, "Loading Devices..", "loading_devises", getActivity());
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
    public void onResponse(String response1, String header) {
        if (header.equals("loading_devises")) {
            response = null;
            try {
                response = new JSONArray(response1);
                Utility.dismissLoadingDialog();
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
            Toast.makeText(requireActivity(), R.string.chkInternet, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getPrathamId(String prathamId, String QrId) {
    }

    @Override
    public void setDeviceDetail(String tabBrand, String qrId, String deviceId, String serNo, String tabDetail, String status) {
        detailDialog = new BlurPopupWindow.Builder(getActivity())
                .setContentView(R.layout.dialog_mydevices)
                .bindClickListener(v -> {
                    detailDialog.dismiss();
                }, R.id.dialog_btn_exit)
                .setGravity(Gravity.CENTER)
                .setDismissOnTouchBackground(false)
                .setDismissOnClickBack(false)
                .setScaleRatio(0.2f)
                .setBlurRadius(10)
                .setTintColor(0x30000000)
                .build();
        tv_deviceId = detailDialog.findViewById(R.id.tv_deviceId);
        tv_tabBrand = detailDialog.findViewById(R.id.tv_tabBrand);
        tv_serNo = detailDialog.findViewById(R.id.tv_serialNo);
        tv_tabModel = detailDialog.findViewById(R.id.tv_tabModel);
        tv_tabStatus = detailDialog.findViewById(R.id.tv_status);
        tv_deviceId.setText(deviceId);
        tv_tabBrand.setText(tabBrand);
        tv_serNo.setText(serNo);
        tv_tabModel.setText(tabDetail);
        tv_tabStatus.setText(status);
        detailDialog.show();
    }
}