package com.pratham.admin.ui.home.reportLost;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.interfaces.DevicePrathamIdLisner;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.interfaces.OnCheckBoxSelectedItem;
import com.pratham.admin.modalclasses.API_Response;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.modalclasses.EventMessage;
import com.pratham.admin.modalclasses.Model_ReplaceTab;
import com.pratham.admin.modalclasses.Model_ReportLost;
import com.pratham.admin.modalclasses.Student;
import com.pratham.admin.ui.home.replaceTablet.ReplaceTabItemClick;
import com.pratham.admin.ui.home.replaceTablet.ReplaceTabListAdapter;
import com.pratham.admin.ui.home.replaceTablet.ReplaceTabletFragment;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.ConnectionReceiver;
import com.pratham.admin.util.PA_Constants;
import com.pratham.admin.util.Utility;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.pratham.admin.util.APIs.reportLostAPI;
import static com.pratham.admin.util.APIs.requestTabletAPI;

@EFragment(R.layout.fragment_report_lost)
public class ReportLostFragment extends Fragment implements ReplaceTabItemClick, NetworkCallListener, DevicePrathamIdLisner {

    @ViewById(R.id.et_search)
    EditText searchTab;

    @ViewById(R.id.rv_reportLost)
    RecyclerView rv_reportLost;

    @ViewById(R.id.rl_parentLayout)
    RelativeLayout rl_parentLayout;

    @ViewById(R.id.iv_filter)
    ImageView iv_filter;

    @ViewById(R.id.tv_tabStatus)
    TextView tv_tabStatus;

    boolean internetIsAvailable = false;
    List<DeviseList> deviceList;
    Context context;
    JSONArray response;

    ReplaceTabListAdapter replaceTabListAdapter;

    List<Model_ReportLost> reportLostList;

    @AfterViews
    public void init() {

        myDeviceList();
        reportLostList = new ArrayList<>();
        tv_tabStatus.setText(Html.fromHtml("<b>Tab Status : </b><font color=#ff0000>Pending</font> | Working |" +
                " <font color=#ffe500>Lost</font> | <font color=#303f9f>Damaged</font>"));


        searchTab.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rv_reportLost.setVisibility(View.VISIBLE);

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
            //todo change adapter later
            replaceTabListAdapter = new ReplaceTabListAdapter(getActivity(), deviceList, ReportLostFragment.this);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2, LinearLayoutManager.VERTICAL, false);
            rv_reportLost.setLayoutManager(layoutManager);
            rv_reportLost.setAdapter(replaceTabListAdapter);
            replaceTabListAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.iv_backButton)
    public void backButton() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Click(R.id.iv_filter)
    public void filter(){
        PopupMenu popup = new PopupMenu(requireActivity(),iv_filter);
        popup.inflate(R.menu.filter_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                filter(item.getTitle().toString());
                return true;
            }
        });
        popup.show();
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

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<DeviseList> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (int i = 0; i < deviceList.size(); i++) {
            final String damageType = deviceList.get(i).getPratham_ID();
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
        replaceTabListAdapter.filterList(filterdNames);
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
    public void setDeviceDetail(String prathamId, String qrId, String deviceId, String serNo, String tabDetail, String tabStatus) {

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @UiThread
    @Subscribe
    public void onMessageEvent(EventMessage eventMessage) {
        if (eventMessage.getMessage().equalsIgnoreCase(String.valueOf(R.string.report_lost))) {
            for (DeviseList devList : deviceList) {
                if (devList.isChecked())
                    devList.setChecked(false);
            }
            replaceTabListAdapter.notifyDataSetChanged();
            reportLostList.clear();
        }
    }

    @Override
    public void onTabItemClicked(int position, DeviseList deviseList) {
        Bundle homebundle = new Bundle();
        homebundle.putString(PA_Constants.TABLET_SERIAL_ID, deviseList.getSerialno());
        homebundle.putString(PA_Constants.TABLET_DEVICE_ID, deviseList.getDeviceid());
        homebundle.putString(PA_Constants.TABLET_BRAND, deviseList.getBrand());
        Utility.showFragment(getActivity(), new ReportlostFormFragment_(), R.id.fragment_container,
                homebundle, ReportLostFragment_.class.getSimpleName());
    }
}