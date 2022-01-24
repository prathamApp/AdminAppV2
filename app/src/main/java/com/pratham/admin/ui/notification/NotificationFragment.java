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
import android.widget.RelativeLayout;
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
import com.pratham.admin.modalclasses.API_Response;
import com.pratham.admin.modalclasses.CRL;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.modalclasses.MetaData;
import com.pratham.admin.modalclasses.Model_AssignTab;
import com.pratham.admin.modalclasses.Model_Notification;
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

import static com.pratham.admin.util.APIs.AcknowledgeDevice;
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

    @ViewById(R.id.notification_layout)
    RelativeLayout notification_layout;

    private List<Model_Notification> crlList = new ArrayList<>();
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
        ArrayList<Model_Notification> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (int i = 0; i < crlList.size(); i++) {
            final String byName = crlList.get(i).getAssignByName();
            final String byRole = crlList.get(i).getAssignToName();
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
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, url, "Loading...", "loading_users", getActivity());

        }
    }




    @Override
    public void tabHolderItemClicked(View v, Model_Notification Model_Notification, int position) {
        for (Model_Notification crl1 : notificationHoldersAdapter.getnotificationList()) {

        }
//        temp.set(position, modalGroup);
 //       notificationHoldersAdapter.notifyDataSetChanged();

        {
            Utility.showLoadingDialog(getActivity());
            addMetaDataToJson();
            Gson gson=new Gson();





            try {
               /* Model_AssignTab model_assignTab = new Model_AssignTab(Utility.GetUniqueID().toString(),
                        FastSave.getInstance().getString("CRLid", "no_crl"),
                        FastSave.getInstance().getString("CRLname", "no_crl"),
                        assigneePersonId,
                        assigneePersonName,
                        new Utility().GetCurrentDateNew(),
                        assignTabList,
                        metaDataJSON);

                String json = gson.toJson(model_assignTab);
                Log.e("json : ", json);*/


                JSONArray jsonArray = new JSONArray();
                String toid = Model_Notification.getAssignById();
                String fromid = Model_Notification.getAssignToId();
                String oldstatus = getoldstatus(Model_Notification.getStatus());
                String status = getstatus(Model_Notification.getStatus());
               String AcknowledDateTime= new Utility().GetCurrentDateNew();
                for(int i=0;i<Model_Notification.getLstackdevice().size();i++)
                {


                   DeviseList getData=Model_Notification.getLstackdevice().get(i);
                    JSONObject DataObject = new JSONObject();
                    DataObject.put("fromid",fromid);
                    DataObject.put("toid",toid);
                    DataObject.put("oldstatus",oldstatus);
                    DataObject.put("status",status);
                    DataObject.put("AcknowledDateTime",AcknowledDateTime);
                    DataObject.put("serialNo",getData.getSerialno());

                    jsonArray.put(DataObject);
                }

                String json = jsonArray.toString();
                Log.e("json : ", json);

                if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
                    NetworkCalls.getNetworkCallsInstance(getActivity()).postRequest(this, AcknowledgeDevice, "UPLOADING ... ", json, "AcknowledgeDevice");
                }
            } catch (Exception e){
                Utility.dismissLoadingDialog();
                e.printStackTrace();
            }
        }


    }

    @Override
    public void tabHolderDetails(Model_Notification model_Notification) {
        /*Bundle tabHolderDetail = new Bundle();
        tabHolderDetail.putParcelable("TabHolder_Detail",  model_Notification);
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
                    Log.i("load", tabholdersResponse.toString());
                    Type tabholderList = new TypeToken<ArrayList<Model_Notification>>() {
                    }.getType();
                    crlList.clear();
                    crlList = gson.fromJson(response.toString(), tabholderList);
                    Log.e("Model_Notification : ", String.valueOf(crlList.size()));
                    initializeAdapter();
                    Utility.dismissLoadingDialog();
                } else {
                    Utility.dismissLoadingDialog();
                    tv_noDataFound.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (header.equalsIgnoreCase("AcknowledgeDevice")) {
            Log.e("responseAssignTablet : ", response);


            Utility.dismissLoadingDialog();
            Log.e("responseReplace", response);

            API_Response apiResponse;
            Type json = new TypeToken<API_Response>() {
            }.getType();
            apiResponse = gson.fromJson(response, json);


            Toast.makeText(getActivity(), apiResponse.getErrorDesc(), Toast.LENGTH_SHORT).show();
            storeManagerAPI();
            //Utility.showSnackbar(getActivity(), notification_layout, apiResponse.getErrorDesc());
           // requireActivity().getSupportFragmentManager().popBackStack();


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

    public String getstatus(String status)
    {
        String oldstatus = "";

        if(status.contains("Lost"))
            oldstatus = "Stolen";
        else if(status.contains("Replace"))
            oldstatus = "Damaged";
        else
            oldstatus = "Working";
        return oldstatus;
    }

    public String getoldstatus(String status)
    {
        String oldstatus = "";

        if(status.contains("Lost"))
            oldstatus = "Lost";
        else if(status.contains("Replace"))
            oldstatus = "Replace";
        else
            oldstatus = "Assign";
        return oldstatus;
    }

}