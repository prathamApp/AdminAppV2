package com.pratham.admin.ui.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;
import com.pratham.admin.R;
import com.pratham.admin.adapters.DashRVDataAdapter;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.API_Response;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.modalclasses.HomeOptions;
import com.pratham.admin.ui.home.assignedToMe.AssignedToMeFragment_;
import com.pratham.admin.ui.home.replaceTablet.ReplaceTabletFragment_;
import com.pratham.admin.ui.home.reportLost.ReportLostFragment_;
import com.pratham.admin.util.BackupDatabase;
import com.pratham.admin.util.Utility;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.pratham.admin.util.APIs.requestTabletAPI;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment implements NetworkCallListener {

    @ViewById(R.id.assigned_to_me)
    LinearLayout assigendToMe;

    @ViewById(R.id.rl_homeLayout)
    LinearLayout rl_homeLayout;

    String LoggedcrlId = "", LoggedcrlName = "", LoggedCRLnameSwapStd = "";
    String reportingPersonId, reportingPersonName;
    DashRVDataAdapter optionsAdapter;
    boolean internetIsAvailable = false;

    private ArrayList<HomeOptions> homeOptionsList = new ArrayList<>();

    String json = "";
    ProgressDialog pd;

    private BlurPopupWindow requestTabDialog;
    EditText et_requestTab;


    public HomeFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {

//        checkConnection();
        //backup Database
        BackupDatabase backupDatabase = new BackupDatabase();
        backupDatabase.backup(getActivity());
        LoggedcrlId = getActivity().getIntent().getStringExtra("CRLid");
        LoggedcrlName = getActivity().getIntent().getStringExtra("CRLname");
        LoggedCRLnameSwapStd = getActivity().getIntent().getStringExtra("CRLnameSwapStd");
        reportingPersonId = getActivity().getIntent().getStringExtra("reportingPersonId");

       // initializeAdapter();

    }

/*    private void initializeAdapter() {
        if (optionsAdapter == null) {
            optionsAdapter = new DashRVDataAdapter(students, getActivity(), Fragment_SelectStudent.this);
            rv_student.setHasFixedSize(true);
            FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getActivity(), FlexDirection.COLUMN);
            flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
            flexboxLayoutManager.setAlignItems(AlignItems.FLEX_START);
            rv_student.setLayoutManager(flexboxLayoutManager);
            rv_student.setAdapter(studentListAdapter);
        } else {
            studentListAdapter.notifyDataSetChanged();
        }
    }*/

/*    @Click(R.id.requestTablet)
    public void requestTab() {
        requestTabDialog = new BlurPopupWindow.Builder(getActivity())
                .setContentView(R.layout.dialog_request_tablet)
                .bindClickListener(v -> {
                    requestTabDialog.dismiss();
                }, R.id.dialog_btn_cancel)
                .bindClickListener(v -> {
                    try {
                        Utility.showDialog(getActivity());
                        JSONObject object = new JSONObject();
                        object.put("CRL_ID", "20");//todo testing id
                       //object.put("CRL_ID", LoggedcrlId+"_test");//todo remove test for actual
                        object.put("SubmitedToID", "20");
                        object.put("NoOfTablet", et_requestTab.getText());
                        object.put("RequestDate", new Utility().GetCurrentDateTime(false));
                        String jsonString = "["+object.toString()+"]";
                        Log.e("json : ", jsonString);
                        NetworkCalls.getNetworkCallsInstance(getActivity()).postRequest(this, requestTabletAPI, "UPLOADING ... ", jsonString, "RequestTab");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    requestTabDialog.dismiss();
                }, R.id.dialog_btn_okay)
                .setGravity(Gravity.CENTER)
                .setDismissOnTouchBackground(false)
                .setDismissOnClickBack(false)
                .setScaleRatio(0.2f)
                .setBlurRadius(10)
                .setTintColor(0x30000000)
                .build();
        et_requestTab = requestTabDialog.findViewById(R.id.et_requestTablet);
        requestTabDialog.show();
    }*/

    @Click(R.id.assigned_to_me)
    public void setAssigendToMe() {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AssignedToMeFragment_()).addToBackStack("d").commit();
    }

    @Click(R.id.replaceTab)
    public void replaceTab() {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReplaceTabletFragment_()).addToBackStack("d").commit();
    }

    @Click(R.id.reportLost)
    public void reportLost() {
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReportLostFragment_()).addToBackStack("d").commit();
    }

    @Override
    public void onResponse(String response, String header) {
        if (header.equals("RequestTab")){
            Log.e("response", response);
            Gson gson = new Gson();
            List<API_Response> apiResponseList;
            Type apiResponse = new TypeToken<ArrayList<API_Response>>() {
            }.getType();
            apiResponseList = gson.fromJson(response, apiResponse);
            if(apiResponseList.size()>0){
                for(API_Response api_response : apiResponseList) {
                    if(api_response.getErrorDesc().equalsIgnoreCase("OK")){
                        Utility.dismissDialog();
                        Utility.showSnackbar(getActivity(),rl_homeLayout,R.string.request_sent_success);
                    } else if(api_response.getErrorDesc().equalsIgnoreCase("CRL_ID Invalid")){
                        Utility.dismissDialog();
                        Utility.showSnackbar(getActivity(),rl_homeLayout,R.string.crl_not_found);
                    } else {
                        Utility.dismissDialog();
                        Utility.showSnackbar(getActivity(),rl_homeLayout,R.string.request_failed);
                    }

                }
            }
        }

    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equals("RequestTab")) {
            Log.e("error", String.valueOf(anError.getErrorCode()));
            Log.e("error", anError.getErrorBody());
        }
    }
}