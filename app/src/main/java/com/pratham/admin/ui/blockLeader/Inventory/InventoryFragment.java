package com.pratham.admin.ui.blockLeader.Inventory;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pratham.admin.ApplicationController;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.database.AppDatabase;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.API_Response;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.modalclasses.MetaData;
import com.pratham.admin.modalclasses.Model_AssignTab;
import com.pratham.admin.ui.home.assignedToMe.AssignedToMeFragment;
import com.pratham.admin.ui.home.assignedToMe.DeviceListAdapter;
import com.pratham.admin.ui.home.replaceTablet.ReplaceTabItemClick;
import com.pratham.admin.ui.home.replaceTablet.ReplaceTabListAdapter;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.ConnectionReceiver;
import com.pratham.admin.util.PA_Constants;
import com.pratham.admin.util.Utility;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.pratham.admin.util.APIs.assignTabletAPI;
import static com.pratham.admin.util.APIs.reportLostAPI;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_inventory)
public class InventoryFragment extends Fragment implements NetworkCallListener, InventoryTabItemClick {

    @ViewById(R.id.et_search)
    EditText searchTab;

    @ViewById(R.id.rv_tabHolder)
    RecyclerView rv_tabHolders;

    @ViewById(R.id.tv_totalTabCount)
    TextView tv_totalTablets;

    @ViewById(R.id.spinner_tabStatus)
    Spinner spinner_tabStatus;

    @ViewById(R.id.btn_assignTab)
    Button btn_assgnTab;

    @ViewById(R.id.iv_backButton)
    ImageView iv_backButton;

    boolean internetIsAvailable = false;
    List<DeviseList> deviceList;
    List<DeviseList> assignTabList;
    Context context;
    JSONArray response;

    InventoryTabListAdapter deviceAdapter;

    ArrayAdapter adapterTabStatus;

    String assigneePersonId, assigneePersonName;
    String selectedTabJson = "";
    String metaDataJSON;

    Gson gson = new Gson();

    public InventoryFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {
        try {
            assigneePersonId = requireArguments().getString("assigneeId");
            assigneePersonName = requireArguments().getString("assigneeName");
            Log.e("dddddddddddd", assigneePersonId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(assigneePersonId!=null && assigneePersonName!=null) {
            btn_assgnTab.setVisibility(View.VISIBLE);
            iv_backButton.setVisibility(View.VISIBLE);
        }
        else {
            btn_assgnTab.setVisibility(View.GONE);
            iv_backButton.setVisibility(View.GONE);
        }

        myDeviceList();
        //spinner adapters
        adapterTabStatus = ArrayAdapter.createFromResource(getActivity(), R.array.tab_status, R.layout.support_simple_spinner_dropdown_item);
        spinner_tabStatus.setAdapter(adapterTabStatus);

        spinner_tabStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (adapterTabStatus.getItem(position).toString().equalsIgnoreCase("Unassigned")) {
                    filter("Working");
                } else if (adapterTabStatus.getItem(position).toString().equalsIgnoreCase("Disputed")) {
                    filter("Pending");
                } else if (adapterTabStatus.getItem(position).toString().equalsIgnoreCase("All")) {
                    filter("All");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        searchTab.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rv_tabHolders.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });
        assignTabList = new ArrayList<DeviseList>();
    }

    public void initializeAdapter(JSONArray response) {
        Gson gson = new Gson();
        Type devicesList = new TypeToken<ArrayList<DeviseList>>() {
        }.getType();
        this.context = context;
        deviceList = gson.fromJson(response.toString(), devicesList);
        try {
            deviceAdapter = new InventoryTabListAdapter(getActivity(), deviceList, InventoryFragment.this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
            rv_tabHolders.setLayoutManager(layoutManager);
            rv_tabHolders.setAdapter(deviceAdapter);
            deviceAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_totalTablets.setText("Total Tablet(s) :  " + deviceList.size());
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<DeviseList> filterdNames = new ArrayList<>();

        if(text.equalsIgnoreCase("All")){
            filterdNames.addAll(deviceList);
        } else {
            //looping through existing elements
            for (int i = 0; i < deviceList.size(); i++) {
                final String damageType = deviceList.get(i).getSerialno();
                final String fromName = deviceList.get(i).getDeviceid();
                final String fromId = deviceList.get(i).getModel();
                final String status = deviceList.get(i).getStatus();
                //if the existing elements contains the search input
                if (damageType.toLowerCase().contains(text.toLowerCase())
                        || fromName.toLowerCase().contains(text.toLowerCase())
                        || fromId.toLowerCase().contains(text.toLowerCase())
                        || status.toLowerCase().contains(text.toLowerCase())) {
                    //adding the element to filtered list
                    filterdNames.add(deviceList.get(i));
                }
            }
        }
        //calling a method of the adapter class and passing the filtered list
        deviceAdapter.filterList(filterdNames);
    }


    public void myDeviceList() {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
            //String url = APIs.DeviceList + "6501";
            String url = APIs.DeviceList + FastSave.getInstance().getString("CRLid", "no_crl");
            Log.e("url : ", url);
            //String url = APIs.DeviceList + FastSave.getInstance().getString("CRLid", "no_crl");
            loadDevises(url);
        } else {
            new AlertDialog.Builder(requireActivity()).setTitle("Warning").setMessage("No internet connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
    }

    private void loadDevises(String url) {
        NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, url, "Loading Devices..", "loading_devises", getActivity());
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
        } else if (header.equalsIgnoreCase("AssignTablet")) {
            Log.e("responseAssignTablet : ", response1);
            assignTabList.clear();
            deviceList.clear();
            myDeviceList();
/*            Gson gson = new Gson();
            API_Response apiResponse;
            Type json = new TypeToken<API_Response>() {
            }.getType();
            apiResponse = gson.fromJson(response1, json);*/
            Toast.makeText(getActivity(), "Tablet Assigned.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equals("loading_devises")) {
            Utility.dismissLoadingDialog();
            Toast.makeText(requireActivity(), R.string.chkInternet, Toast.LENGTH_SHORT).show();
        } else if (header.equalsIgnoreCase("AssignTablet")) {
            Utility.dismissLoadingDialog();
            Log.e("error :", String.valueOf(anError.getErrorCode()));
            Log.e("error :", anError.getErrorBody());
        }
    }

    @Override
    public void onTabItemClicked(int position, DeviseList deviseList) {
        for (DeviseList device : deviceList) {
            if (device.getDeviceid().equalsIgnoreCase(deviseList.getDeviceid())) {
                if (device.isSelected()) {
                    device.setSelected(false);
                    assignTabList.remove(device);
                } else {
                    device.setSelected(true);
                    assignTabList.add(device);
                }
                deviseList = device;
                break;
            }
        }
        deviceAdapter.notifyItemChanged(position, deviseList);

        Gson gson = new Gson();
        Type devicesList = new TypeToken<ArrayList<DeviseList>>() {
        }.getType();
        selectedTabJson = gson.toJson(assignTabList, devicesList);
        Log.e("selectedTabJSON : ", selectedTabJson);
    }

    @Click(R.id.btn_assignTab)
    public void assignTablet() {
        if(assignTabList.size()<=0) {
            Toast.makeText(getActivity(), "Select Atleast One Tablet.", Toast.LENGTH_SHORT).show();
        } else {
            addMetaDataToJson();
            try {
                Model_AssignTab model_assignTab = new Model_AssignTab(Utility.GetUniqueID().toString(),
                        FastSave.getInstance().getString("CRLid", "no_crl"),
                        FastSave.getInstance().getString("CRLname", "no_crl"),
                        assigneePersonId,
                        assigneePersonName,
                        new Utility().GetCurrentDateNew(),
                        assignTabList,
                        metaDataJSON);

                String json = gson.toJson(model_assignTab);
                Log.e("json : ", json);

                if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
                    NetworkCalls.getNetworkCallsInstance(getActivity()).postRequest(this, assignTabletAPI, "UPLOADING ... ", json, "AssignTablet");
                }
            } catch (Exception e) {
                Utility.dismissLoadingDialog();
                e.printStackTrace();
            }

        }
    }

    @Click(R.id.iv_backButton)
    public void backButton(){
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private void addMetaDataToJson() {
        try {
            JSONObject metaDataObject = new JSONObject();
            List<MetaData> metaDataList = AppDatabase.getDatabaseInstance(getActivity()).getMetaDataDao().getAllMetaData();
            for (MetaData metadata : metaDataList) {
                metaDataObject.put(metadata.getKeys(), metadata.getValue());
            }
            metaDataJSON = metaDataObject.toString();
            Log.e("meta data : ", metaDataJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}