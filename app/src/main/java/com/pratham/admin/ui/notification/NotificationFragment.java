package com.pratham.admin.ui.notification;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;
import com.pratham.admin.ApplicationController;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.database.AppDatabase;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.CRL;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.modalclasses.MetaData;
import com.pratham.admin.modalclasses.Model_AssignTab;
import com.pratham.admin.modalclasses.Model_Role;
import com.pratham.admin.modalclasses.Model_User;
import com.pratham.admin.modalclasses.ProgramsModal;
import com.pratham.admin.modalclasses.Village;
import com.pratham.admin.ui.CRL_ProfileFragment_;
import com.pratham.admin.ui.blockLeader.Inventory.InventoryFragment_;
import com.pratham.admin.ui.blockLeader.tabHolders.TabHolderListItemListener;
import com.pratham.admin.ui.blockLeader.tabHolders.TabHoldersAdapter;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.Utility;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.pratham.admin.util.APIs.assignTabletAPI;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_notification)
public class NotificationFragment extends Fragment implements NotificationHolderListItemListener, NetworkCallListener {

    @ViewById(R.id.et_search)
    EditText searchTab;
    @ViewById(R.id.rv_notiHolder)
    public RecyclerView rv_notiHolder;

    @ViewById(R.id.tv_noDataFound)
    TextView tv_noDataFound;

    private List<CRL> crlList = new ArrayList<>();
   private NotificationHoldersAdapter notificationHoldersAdapter;


       boolean isStoreManager = false;
    JSONArray tabholdersResponse;
    String assigneePersonId, assigneePersonName;
    String selectedTabJson="";
    String metaDataJSON;
    List<DeviseList> assignTabList;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {
        assignTabList = new ArrayList<DeviseList>();
        storeManagerAPI();
//        if (FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("13")) {
//
//            isStoreManager = true;
//            storeManagerAPI();
//        } else {
//
//            isStoreManager = false;
//
//            prepareData();
//            initializeAdapter();
//        }



        searchTab.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rv_notiHolder.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });
    }

    public void initializeAdapter() {
        notificationHoldersAdapter = new NotificationHoldersAdapter(getActivity(), crlList, NotificationFragment.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_notiHolder.setLayoutManager(mLayoutManager);
        rv_notiHolder.setItemAnimator(new DefaultItemAnimator());
        rv_notiHolder.setAdapter(notificationHoldersAdapter);
        notificationHoldersAdapter.notifyDataSetChanged();
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<CRL> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (int i = 0; i < crlList.size(); i++) {
            final String byName = crlList.get(i).getFirstName();
            final String byRole = crlList.get(i).getRoleName();
            //if the existing elements contains the search input
            if (byName.toLowerCase().contains(text.toLowerCase())
                    || byRole.toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(crlList.get(i));
            }
        }
        //calling a method of the adapter class and passing the filtered list
        notificationHoldersAdapter.filterList(filterdNames);
    }


    private void storeManagerAPI() {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
            String url = APIs.getAcknowledgeList + FastSave.getInstance().getString("CRLid", "no_crl");
            Log.i("urls",url);
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, APIs.getAllUsers, "Loading...", "loading_users", getActivity());

        }
    }

    private void prepareData() {
        crlList = AppDatabase.getDatabaseInstance(getActivity()).getCRLdao().getCRLsByReportingPerson(FastSave.getInstance().getString("CRLid", ""));
        Log.e("CRL List : ", String.valueOf(crlList.size()));

    }



    @Override
    public void tabHolderItemClicked(View v, CRL crl, int position) {
        for (CRL crl1 : notificationHoldersAdapter.getCrlList()) {
            if (crl1.getCRLId().equalsIgnoreCase(crl.getCRLId())) {
                crl1.setSelected(true);
               // assigneePersonId = crl1.getCRLId();
                //assigneePersonName = crl1.getFirstName();
            } else
                crl1.setSelected(false);
        }
//        temp.set(position, modalGroup);
 //       notificationHoldersAdapter.notifyDataSetChanged();

        {
            addMetaDataToJson();
Gson gson=new Gson();
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
                   // NetworkCalls.getNetworkCallsInstance(getActivity()).postRequest(this, assignTabletAPI, "UPLOADING ... ", json, "AssignTablet");
                }
            } catch (Exception e){
                Utility.dismissLoadingDialog();
                e.printStackTrace();
            }
        }


    }

    @Override
    public void tabHolderDetails(CRL crl) {
        /*Bundle tabHolderDetail = new Bundle();
        tabHolderDetail.putParcelable("TabHolder_Detail",  crl);
        Utility.showFragment(getActivity(), new CRL_ProfileFragment_(),R.id.fragment_container,
                tabHolderDetail, CRL_ProfileFragment_.class.getSimpleName());*/
    }








    @Override
    public void onResponse(String response, String header) {
        Gson gson = new Gson();
        if (header.equalsIgnoreCase("loading_users")) {
            try {
                tabholdersResponse = new JSONArray(response);

                if (tabholdersResponse.length() > 0) {
                    Log.i("load",tabholdersResponse.toString());
                    Type tabholderList = new TypeToken<ArrayList<CRL>>() {
                    }.getType();
                    crlList = gson.fromJson(response.toString(), tabholderList);
                    Log.e("crl : ", String.valueOf(crlList.size()));
                    initializeAdapter();
                    Utility.dismissLoadingDialog();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (header.equalsIgnoreCase("AssignTablet")) {
            Log.e("responseAssignTablet : ", response);
/*            Gson gson = new Gson();
            API_Response apiResponse;
            Type json = new TypeToken<API_Response>() {
            }.getType();
            apiResponse = gson.fromJson(response1, json);*/
           // Toast.makeText(getActivity(), "Tablet Assigned.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(ANError anError, String header) {
        Utility.dismissLoadingDialog();
        Log.e("API ERROR : ", anError.getMessage());

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
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}