package com.pratham.admin.ui.blockLeader.Inventory;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.ui.home.assignedToMe.AssignedToMeFragment;
import com.pratham.admin.ui.home.assignedToMe.DeviceListAdapter;
import com.pratham.admin.ui.home.replaceTablet.ReplaceTabItemClick;
import com.pratham.admin.ui.home.replaceTablet.ReplaceTabListAdapter;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.ConnectionReceiver;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EFragment(R.layout.fragment_inventory)
public class InventoryFragment extends Fragment implements NetworkCallListener, ReplaceTabItemClick {

    @ViewById(R.id.et_search)
    EditText searchTab;

    @ViewById(R.id.rv_tabHolder)
    RecyclerView rv_tabHolders;

    @ViewById(R.id.tv_totalTabCount)
    TextView tv_totalTablets;

    @ViewById(R.id.spinner_tabStatus)
    Spinner spinner_tabStatus;

    boolean internetIsAvailable = false;
    List<DeviseList> deviceList;
    Context context;
    JSONArray response;

    ReplaceTabListAdapter deviceAdapter;

    ArrayAdapter adapterTabStatus;

    public InventoryFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init(){
        myDeviceList();
        //spinner adapters
        adapterTabStatus = ArrayAdapter.createFromResource(getActivity(), R.array.tab_status, R.layout.support_simple_spinner_dropdown_item);
        spinner_tabStatus.setAdapter(adapterTabStatus);

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
    }

    public void initializeAdapter(JSONArray response) {
        Gson gson = new Gson();
        Type devicesList = new TypeToken<ArrayList<DeviseList>>() {
        }.getType();
        this.context = context;
        deviceList = gson.fromJson(response.toString(), devicesList);
        try {
            deviceAdapter = new ReplaceTabListAdapter(getActivity(), deviceList, InventoryFragment.this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);
            rv_tabHolders.setLayoutManager(layoutManager);
            rv_tabHolders.setAdapter(deviceAdapter);
            deviceAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_totalTablets.setText("Total Tablet(s) :  "+deviceList.size());
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<DeviseList> filterdNames = new ArrayList<>();

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
        //calling a method of the adapter class and passing the filtered list
        deviceAdapter.filterList(filterdNames);
    }


    public void myDeviceList() {
        //todo check internret connection
        checkConnection();
        if (internetIsAvailable) {
            String url = APIs.DeviceList + "6501";//FastSave.getInstance().getString("CRLid", "no_crl");
            //String url = APIs.DeviceList + FastSave.getInstance().getString("CRLid", "no_crl");
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
    public void onTabItemClicked(int position, DeviseList deviseList) {

    }
}