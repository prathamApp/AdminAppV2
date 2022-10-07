package com.pratham.admin.ui.blockLeader.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;
import com.pratham.admin.ApplicationController;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.interfaces.ConnectionReceiverListener;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.interfaces.NetworkCallListnerSelectProgram;
import com.pratham.admin.modalclasses.Model_DamagedTabletCount;
import com.pratham.admin.modalclasses.Model_Donor;
import com.pratham.admin.modalclasses.Model_TabletCountProgramwise;
import com.pratham.admin.modalclasses.Model_Vendor;
import com.pratham.admin.modalclasses.Model_YearOfPurchase;
import com.pratham.admin.modalclasses.ProgramsModal;
import com.pratham.admin.modalclasses.Model_TabletCount;
import com.pratham.admin.modalclasses.Village;
import com.pratham.admin.ui.blockLeader.home.AddNewTablet.AddNewTabletFragment_;
import com.pratham.admin.ui.home.assignedToMe.AssignedToMeFragment;
import com.pratham.admin.ui.home.assignedToMe.DeviceListAdapter;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.ConnectionReceiver;
import com.pratham.admin.util.Utility;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static com.pratham.admin.util.APIs.village;

@EFragment(R.layout.fragment_block_leader_home)
public class BlockLeaderHomeFragment extends Fragment implements NetworkCallListnerSelectProgram, ConnectionReceiverListener, NetworkCallListener {

    @ViewById(R.id.spinner_program)
    Spinner spinner_program;

    @ViewById(R.id.spinner_programm)
    Spinner spinner_programm;

    @ViewById(R.id.spinner_state)
    Spinner spinner_state;

    @ViewById(R.id.spinner_block)
    Spinner spinner_block;

    @ViewById(R.id.spinner_vendor)
    Spinner spinner_vendor;

    @ViewById(R.id.spinner_donor)
    Spinner spinner_donor;

    @ViewById(R.id.spinner_yearOfPurchase)
    Spinner spinner_yearOfpurchase;

    @ViewById(R.id.tv_desputed_tab)
    TextView tv_desputedCount;

    @ViewById(R.id.tv_assigned_tab)
    TextView tv_assignedCount;

    @ViewById(R.id.tv_unassigned_tab)
    TextView tv_unassignedCount;

    @ViewById(R.id.tv_totalNoOfTabs)
    TextView tv_totalNoOfTabs;

    @ViewById(R.id.tv_lost_tab)
    TextView tv_lostTabCount;

    @ViewById(R.id.tv_damaged_tab)
    TextView tv_damagedTabCount;

    @ViewById(R.id.tv_working_tab)
    TextView tv_workingTabCount;

    @ViewById(R.id.tv_dead_tab)
    TextView tv_deadTabCount;

    @ViewById(R.id.switch_vendor)
    SwitchMaterial switchMaterial;

    @ViewById(R.id.ll_tabStatusTwo)
    LinearLayout ll_tabStatusTwo;

    @ViewById(R.id.rl_tabStatus)
    RelativeLayout rl_tabStatus;

    @ViewById(R.id.ll_spinner)
    LinearLayout ll_spinner;

    @ViewById(R.id.rl_spinnerParent)
    RelativeLayout rl_spinnerParent;

    @ViewById(R.id.rl_spinnerParentTwo)
    RelativeLayout rl_spinnerParentTwo;

    @ViewById(R.id.ll_goBtn)
    LinearLayout ll_goBtn;

    @ViewById(R.id.ll_goBtnVendor)
    LinearLayout ll_goBtnVendor;

    @ViewById(R.id.tv_addNewTablet)
    TextView tv_addNewTablet;

    @ViewById(R.id.rl_btn)
    RelativeLayout rl_btn;

    @ViewById(R.id.rl_tabCountView)
    RelativeLayout rl_tabCountView;

    @ViewById(R.id.rl_blScreen)
    RelativeLayout rl_blScreen;

    Dialog dialog;
    String[] stateCode;
    String selectedBlock = "NO BLOCKS";
    public static String selectedProgramID = null;
    public static String selectedProgrammID = null;//program id for vendor spinner
    public static String selectedProgramName = null;

    boolean apiLoadFlag = false;
    boolean internetIsAvailable = false;
    boolean errorDetected = false;

    private String[] states;
    private int selectedState;
    private String selectedStateName = "MH";

    private List<Village> villageList = new ArrayList();

    JSONArray programResponse;
    private ArrayList<ProgramsModal> programsList = new ArrayList<>();
    List<String> spnr_programList = new ArrayList<>();
    List<String> prgrms = new ArrayList<>();

    JSONArray vendorResponse;
    List<Model_Vendor> vendorList = new ArrayList<>();
    List<String> spnr_vendorList = new ArrayList<>();
    String selectedVendorID = "";

    JSONArray donorResponse;
    List<Model_Donor> donorList;
    List<String> spnr_donorList = new ArrayList<>();

    JSONArray yearOfPurchaseResponse;
    List<Model_YearOfPurchase> yearOfPurchasesList;
    List<String> spnr_yearOfPurchaseList = new ArrayList<>();

    JSONArray tabCountProgWise;
    List<Model_TabletCountProgramwise> tabCountProgWiseList;

    String totalDamagedCount = "", totalTabletCount = "";

    boolean isStoreManager = false;

    String donorName, vendorName, yop;

    TabCountProgramWiseListAdapter tabCountProgramWiseListAdapter;
    public BlurPopupWindow dlg_showTabletDetails;
    RecyclerView rv_showTabDetails;

    public BlockLeaderHomeFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {
        loadTabletCount();

        if (FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("13")) {
            isStoreManager = true;
            switchMaterial.setVisibility(View.VISIBLE);
            ll_spinner.setVisibility(View.VISIBLE);
            storeManagerAPICall();
        } else {
            isStoreManager = false;
            switchMaterial.setVisibility(View.GONE);
            ll_spinner.setVisibility(View.GONE);
        }

        checkConnection();
        spinner_block.setEnabled(false);
        spinner_state.setEnabled(false);
        states = getResources().getStringArray(R.array.india_states);
        ArrayAdapter adapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_state.setAdapter(adapter);
//        loadprogams();
        getVillageStatewise(); //calling to get directly blocks

        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ll_tabStatusTwo.setVisibility(View.VISIBLE);
                    rl_tabStatus.setVisibility(View.GONE);
                    rl_spinnerParentTwo.setVisibility(View.VISIBLE);
                    rl_spinnerParent.setVisibility(View.GONE);
                    ll_goBtn.setVisibility(View.GONE);
                    ll_goBtnVendor.setVisibility(View.VISIBLE);
                    tv_addNewTablet.setVisibility(View.VISIBLE);
                    loadVendorTabletCount();
                    getTabletCountProgramWise();
//                    tv_totalNoOfTabs.setText(Html.fromHtml("Total number of Tablets : <b>" + totalDamagedCount + "</b>"));
                } else {
                    ll_tabStatusTwo.setVisibility(View.GONE);
                    rl_tabStatus.setVisibility(View.VISIBLE);
                    rl_spinnerParentTwo.setVisibility(View.GONE);
                    rl_spinnerParent.setVisibility(View.VISIBLE);
                    ll_goBtn.setVisibility(View.VISIBLE);
                    ll_goBtnVendor.setVisibility(View.GONE);
                    tv_addNewTablet.setVisibility(View.GONE);
                    tv_totalNoOfTabs.setText(Html.fromHtml("Total number of Tablets : <b>" + totalTabletCount + "</b>"));
                }
            }
        });
    }

    @Click(R.id.ll_lost_tab)
    public void showLostTabletDetails(){
        showTabletDetails("lost");
    }
    @Click(R.id.ll_damage_tab)
    public void showDamageTabletDetails(){
        showTabletDetails("damage");
    }
    @Click(R.id.ll_working_tab)
    public void showWorkingTabletDetails(){
        showTabletDetails("working");
    }
    @Click(R.id.ll_dead_tab)
    public void showDeadTabletDetails(){
        showTabletDetails("dead");
    }

    public void showTabletDetails(String tabType){
        Log.e("dsgsg","argbdvBV");
        dlg_showTabletDetails = new BlurPopupWindow.Builder(getActivity())
                .setContentView(R.layout.dialog_tabletcount_programwise)
/*                .bindClickListener(v -> {
                    dlg_showTabletDetails.dismiss();
                }, R.id.dialog_btn_exit)*/
                .setGravity(Gravity.CENTER)
                .setDismissOnTouchBackground(false)
                .setDismissOnClickBack(true)
                .setScaleRatio(0.2f)
                .setBlurRadius(10)
                .setTintColor(0x30000000)
                .build();

        rv_showTabDetails = dlg_showTabletDetails.findViewById(R.id.rv_tabCount);
        try {
            tabCountProgramWiseListAdapter = new TabCountProgramWiseListAdapter(getActivity(), tabCountProgWiseList, tabType);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rv_showTabDetails.setLayoutManager(layoutManager);
            rv_showTabDetails.setAdapter(tabCountProgramWiseListAdapter);
            tabCountProgramWiseListAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }

        dlg_showTabletDetails.show();

    }

    private void getTabletCountProgramWise() {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
             NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, APIs.tabCountProgWiseAPI, "Loading...", "tabCountProgWiseApi", getActivity());
        }
    }

    private void storeManagerAPICall() {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, APIs.vendorAPI, "Loading...", "vendorApi", getActivity());
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, APIs.donorAPI, "Loading...", "donorApi", getActivity());
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, APIs.yearOfPurchaseAPI, "Loading...", "yearofpurchaseApi", getActivity());
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, APIs.programsAPI, "Loading...", "programApi", getActivity());
        } else {
/*            new AlertDialog.Builder(requireActivity()).setTitle("Warning").setMessage("No internet connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();*/
            Utility.showSnackbar(getActivity(), rl_blScreen, R.string.noInterntCon);
        }
    }

    //Used to get count of despute, assigned, unassigned and total tablets
    private void loadTabletCount() {
        checkConnection();
        if (internetIsAvailable) {
            String url = APIs.tabletCountAPI + FastSave.getInstance().getString("CRLid", "no_crl");
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestJsonObject(this, url, "Loading...", "loading_tablets", getActivity());
        } else {
/*            new AlertDialog.Builder(requireActivity()).setTitle("Warning").setMessage("No internet connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();*/
            Utility.showSnackbar(getActivity(), rl_blScreen, R.string.noInterntCon);
        }
    }

    private void loadVendorTabletCount() {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork())
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestJsonObject(this, APIs.totalDamagedTabletCount, "Loading...", "loading_damaged_tablets", getActivity());
    }

    @Click(R.id.btn_go)
    public void loadCountBlockWise() {

        if (isStoreManager) {
            if (spinner_program.getSelectedItem().toString().equalsIgnoreCase("Program") ||
                    spinner_state.getSelectedItem().toString().equalsIgnoreCase("State") ||
                    spinner_block.getSelectedItem().toString().equalsIgnoreCase("Block"))
                Toast.makeText(getActivity(), "Select Fields First!", Toast.LENGTH_SHORT).show();
            else {
                String url = APIs.tabletCountByProgramState + FastSave.getInstance().getString("CRLid", "no_crl") +
                        APIs.SERVER_PROGRAMID + selectedProgramID + APIs.SERVER_STATENAME + spinner_state.getSelectedItem().toString() +
                        APIs.SERVER_BLOCKNAME + spinner_block.getSelectedItem().toString();
                Log.e("ProgStateBlock url : ", url);
                if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
                    NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestJsonObject(this, url, "Loading...", "loading_tablets", getActivity());
                }
            }
        } else {
            if (spinner_block.getSelectedItem().toString().equalsIgnoreCase("Block"))
                Toast.makeText(getActivity(), "Select Block First!", Toast.LENGTH_SHORT).show();
            else {
                checkConnection();
                if (internetIsAvailable) {
                    String url = APIs.tabletCountByBlockNameAPI + FastSave.getInstance().getString("CRLid", "no_crl") + "&blockname=" + spinner_block.getSelectedItem();
                    NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestJsonObject(this, url, "Loading...", "loading_tablets", getActivity());
                } else {
                    new AlertDialog.Builder(requireActivity()).setTitle("Warning").setMessage("No internet connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                }
            }
        }
    }

    @Click(R.id.btn_goVendor)
    public void loadCountVendorWise() {
/*
        if (spinner_donor.getSelectedItem().toString().equalsIgnoreCase("Donor") ||
                spinner_vendor.getSelectedItem().toString().equalsIgnoreCase("Vendor") ||
                spinner_yearOfpurchase.getSelectedItem().toString().equalsIgnoreCase("YearOfPurchase") ||
                spinner_programm.getSelectedItem().toString().equalsIgnoreCase("Program"))
            Toast.makeText(getActivity(), "Select Fields First!", Toast.LENGTH_SHORT).show();
        else {
            String url = APIs.tabletCountByDonorVendor + selectedVendorID +
                    APIs.SERVER_PROGRAMID + selectedProgrammID +
                    APIs.SERVER_DONORNAME + spinner_donor.getSelectedItem().toString() +
                    APIs.SERVER_YOP + spinner_yearOfpurchase.getSelectedItem().toString();
            Log.e("ProgStateBlock url : ", url);
            if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
                NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestJsonObject(this, url, "Loading...", "loading_damaged_tablets", getActivity());
            }
        }
*/
        if (spinner_donor.getSelectedItem().toString().equalsIgnoreCase("Donor") &&
                spinner_vendor.getSelectedItem().toString().equalsIgnoreCase("Vendor") &&
                spinner_yearOfpurchase.getSelectedItem().toString().equalsIgnoreCase("Year") &&
                spinner_programm.getSelectedItem().toString().equalsIgnoreCase("Program"))
            Toast.makeText(getActivity(), "Select at least one field!", Toast.LENGTH_SHORT).show();
        else {
            getSpinnerValues();
            String url = APIs.tabletCountByDonorVendor + donorName +
                    APIs.SERVER_VENDORNAME + vendorName +
                    APIs.SERVER_YOP + yop +
                    APIs.SERVER_PROGRAMID + selectedProgrammID;
            if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
                Log.e("Filter url : ", url);
                NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestJsonObject(this, url, "Loading...", "loading_damaged_tablets", getActivity());
            } else {
                Utility.showSnackbar(getActivity(), rl_blScreen, getString(R.string.chkInternet));
            }

        }
    }

    private void getSpinnerValues() {
        if (spinner_donor.getSelectedItem().toString().equalsIgnoreCase("Donor")) donorName="0";
        else donorName = spinner_donor.getSelectedItem().toString();

        if (spinner_vendor.getSelectedItem().toString().equalsIgnoreCase("Vendor")) vendorName="0";
        else vendorName = spinner_vendor.getSelectedItem().toString();

        if (spinner_yearOfpurchase.getSelectedItem().toString().equalsIgnoreCase("Year")) yop="0";
        else yop = spinner_yearOfpurchase.getSelectedItem().toString();

        if (spinner_programm.getSelectedItem().toString().equalsIgnoreCase("Program")) selectedProgrammID="0";
    }

    @Click(R.id.btn_refresh)
    public void refreshTabCount() {
        loadTabletCount();
        spinner_block.setSelection(0);
        spinner_state.setSelection(0);
        spinner_program.setSelection(0);
    }

    @Click(R.id.btn_refreshvendor)
    public void refreshTabCountVendor() {
        loadVendorTabletCount();
        spinner_donor.setSelection(0);
        spinner_vendor.setSelection(0);
        spinner_yearOfpurchase.setSelection(0);
        spinner_programm.setSelection(0);
    }

    @Click(R.id.tv_addNewTablet)
    public void addNewTablet() {
        Bundle newTabBundle = new Bundle();
        newTabBundle.putStringArrayList("Donor_List", (ArrayList<String>) spnr_donorList);
        newTabBundle.putStringArrayList("Vendor_List", (ArrayList<String>) spnr_vendorList);
        newTabBundle.putStringArrayList("YOP_List", (ArrayList<String>) spnr_yearOfPurchaseList);
        newTabBundle.putParcelableArrayList("Program_List", programsList);
        Utility.showFragment(getActivity(), new AddNewTabletFragment_(), R.id.fragment_container,
                newTabBundle, AddNewTabletFragment_.class.getSimpleName());
    }


    private void loadprogams() {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, APIs.programsAPI, "Loading...", "vendorApi", getActivity());

        }

/*
        AndroidNetworking.get(APIs.programsAPI)
                .addHeaders("Content-Type", "application/json").build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        programsList.clear();
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ProgramsModal>>() {
                        }.getType();
                        programsList = gson.fromJson(response.toString(), listType);
                        if (programsList != null) {
                            ProgramsModal modalProgram = new ProgramsModal();
                            modalProgram.setProgramId(-1);
                            modalProgram.setProgramName("Program");
                            LinkedHashSet hs = new LinkedHashSet(programsList);//to remove redundant values
                            programsList.clear();
                            programsList.addAll(hs);
                            programsList.add(0, modalProgram);
                            //pullDataView.showProgram(prgrmList);
                            showPrograms(programsList);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
*/
    }


    private void loadStates() {
        spinner_state.setEnabled(true);
        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                apiLoadFlag = false;
                // spinner_village.setVisibility(View.INVISIBLE);
                getVillageStatewise(selectedProgramID, selectedProgramName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //get blocks directly
    private void getVillageStatewise() {
        if (internetIsAvailable) {
//            selectedState = spinner_state.getSelectedItemPosition();
//            selectedStateName = states[selectedState];
//            if (!states[selectedState].equals("State")) {
            String url;
            stateCode = getResources().getStringArray(R.array.india_states_shortcode);
            url = APIs.pullVillagesServerURL +
                    FastSave.getInstance().getString("programId", "") +
                    APIs.SERVER_STATE +
                    FastSave.getInstance().getString("stateCode", "");
            Log.e("URL : ", url);
            loadAPI(url, village, FastSave.getInstance().getString("programName", ""));
/*            } else {
                //Toast.makeText(this, "Please Select State", Toast.LENGTH_SHORT).show();
                spinner_block.setSelection(0);
                //    spinner_village.setSelection(0);
                spinner_block.setEnabled(false);
                //    spinner_village.setEnabled(false);
            }*/
        } else {
            spinner_state.setSelection(0);
            Toast.makeText(requireActivity(), R.string.noInterntCon, Toast.LENGTH_SHORT).show();
        }
    }

    //get blocks by passing progamid and state from spinner
    private void getVillageStatewise(String spID, String spName) {
        //  if (ConnectionManager.getConnectionManager().checkConnection(this)) {
        if (internetIsAvailable) {
            selectedState = spinner_state.getSelectedItemPosition();
            selectedStateName = states[selectedState];
            if (!states[selectedState].equals("Select State")) {
                String url;
                stateCode = getResources().getStringArray(R.array.india_states_shortcode);
                url = APIs.pullVillagesServerURL + spID + APIs.SERVER_STATE + stateCode[selectedState];
                Log.e("URL : ", url);
                loadAPI(url, village, spName);
            } else {
                //Toast.makeText(this, "Please Select State", Toast.LENGTH_SHORT).show();
                spinner_block.setSelection(0);
                //    spinner_village.setSelection(0);
                spinner_block.setEnabled(false);
                //    spinner_village.setEnabled(false);
            }
        } else {
            spinner_state.setSelection(0);
            Toast.makeText(getActivity(), R.string.noInterntCon, Toast.LENGTH_SHORT).show();
        }
    }

    public void loadAPI(final String url, final String type, final String programname) {
        showDialoginApiCalling(programname, type);
        NetworkCalls.getNetworkCallsInstance(getActivity()).getRequestWithProgram(this, url, "loadAPI", type, programname);
    }

    private void loadVillage(String json, String program) {

        spinner_block.setEnabled(true);
        villageList.clear();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Village>>() {
        }.getType();
        ArrayList<Village> modalClassesList = gson.fromJson(json, listType);
        villageList.addAll(modalClassesList);
        ArrayList blockNames = new ArrayList();
        LinkedHashSet hs = new LinkedHashSet();
        if (villageList.size() == 0) {
            blockNames.add("NO BLOCK");
        } else {
            blockNames.add("Block");
            for (int i = 0; i < villageList.size(); i++) {
                blockNames.add(villageList.get(i).getBlock());
            }
        }
        //remove Duplicate
        hs.addAll(blockNames);
        blockNames.clear();
        blockNames.addAll(hs);
        ArrayAdapter blockAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, blockNames);
        spinner_block.setAdapter(blockAdapter);
        spinner_block.setVisibility(spinner_block.VISIBLE);
        spinner_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBlock = parent.getItemAtPosition(position).toString();
                if ((!selectedBlock.equals("NO BLOCK")) && (!selectedBlock.equals("Block"))) {
                    if (apiLoadFlag) {

/*
                        ArrayList<Village> villageName = new ArrayList();
                        for (int i = 0; i < villageList.size(); i++) {
                            if (selectedBlock.equals(villageList.get(i).getBlock())) {
                                villageName.add(villageList.get(i));
                            }
                        }

                        SelectVillageDialog selectVillageDialog = new SelectVillageDialog(SelectProgram.this, villageName);
                        selectVillageDialog.show();
*/
                    }
                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //dismissShownDialog();
    }

    private void showDialoginApiCalling(String program, String type) {

        if (dialog == null) {
            dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(R.layout.dialog_loading_api);
        }
        TextView tv_dialog_title = dialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText(getString(R.string.pulling) + program + " " + type);
        dialog.show();
    }

    private void dismissShownDialog() {
        if (dialog != null) dialog.dismiss();
        dialog = null;
    }

    @Override
    public void onResponce(String response, String header, String type, String program) {
        if (header.equals("loadAPI")) {
            String json = response;
            // Toast.makeText(SelectProgram.this, json, Toast.LENGTH_LONG).show();
            apiLoadFlag = true;
            loadVillage(json, program);
        }
    }

    @Override
    public void onError(ANError anError, String header, String type, String program) {
        if (header.equals("loadAPI")) {
            errorDetected = true;
            if (type.equals("village")) {
                spinner_state.setSelection(0);
                //dismissShownDialog();
            }
            /*  dismissShownDialog();*/
            //parseJSON("[]", type, program);
            if (!internetIsAvailable) {
                Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Load API Failed." + type, Toast.LENGTH_LONG).show();
            }
            // Log.d("error", "" + error);

            apiLoadFlag = false;
        }

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            internetIsAvailable = false;
            Toast.makeText(getActivity(), R.string.noInterntCon, Toast.LENGTH_SHORT).show();
        } else {
            internetIsAvailable = true;
        }
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
    public void onResponse(String response, String header) {
        Gson gson = new Gson();
        if (header.equalsIgnoreCase("loading_tablets")) {
            Model_TabletCount tabletCount = gson.fromJson(response, Model_TabletCount.class);
            Log.e("url : ",tabletCount.getTotalDisputed());
            tv_desputedCount.setText(tabletCount.getTotalDisputed());
            tv_assignedCount.setText(tabletCount.getTotalAssigned());
            tv_unassignedCount.setText(tabletCount.getTotalUnassigned());
            totalTabletCount = tabletCount.getTotalCount();
            tv_totalNoOfTabs.setText(Html.fromHtml("Total number of Tablets : <b>" + tabletCount.getTotalCount() + "</b>"));
            dismissShownDialog();
        } else if (header.equalsIgnoreCase("loading_damaged_tablets")) {
            Model_DamagedTabletCount damagedTabletCount = gson.fromJson(response, Model_DamagedTabletCount.class);
            tv_lostTabCount.setText(damagedTabletCount.getTotalLost());
            tv_damagedTabCount.setText(damagedTabletCount.getTotalDamaged());
            tv_workingTabCount.setText(damagedTabletCount.getTotalWorking());
            tv_deadTabCount.setText(damagedTabletCount.getTotalDead());
            totalDamagedCount = damagedTabletCount.getTotalCount();
            tv_totalNoOfTabs.setText(Html.fromHtml("Total number of Tablets : <b>" + totalDamagedCount + "</b>"));
            //Log.e("Tot Other : ", damagedTabletCount.getTotalOther());
            //Log.e("Tot Dead : ", damagedTabletCount.getTotalDead());
        } else if (header.equalsIgnoreCase("vendorApi")) {
            try {
                vendorResponse = new JSONArray(response);
                if (vendorResponse.length() > 0) {
                    Type venList = new TypeToken<ArrayList<Model_Vendor>>() {
                    }.getType();
                    vendorList = gson.fromJson(response.toString(), venList);
                    Log.e("ven : ", String.valueOf(vendorList.size()));
                    showVendors(vendorList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (header.equalsIgnoreCase("donorApi")) {
            try {
                donorResponse = new JSONArray(response);
                if (donorResponse.length() > 0) {
                    Type donList = new TypeToken<ArrayList<Model_Donor>>() {
                    }.getType();
                    donorList = gson.fromJson(response.toString(), donList);
                    Log.e("don : ", String.valueOf(donorList.size()));
                    showDonors(donorList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (header.equalsIgnoreCase("yearofpurchaseApi")) {
            try {
                yearOfPurchaseResponse = new JSONArray(response);
                if (yearOfPurchaseResponse.length() > 0) {
                    Type yopList = new TypeToken<ArrayList<Model_YearOfPurchase>>() {
                    }.getType();
                    yearOfPurchasesList = gson.fromJson(response.toString(), yopList);
                    Log.e("yop : ", String.valueOf(yearOfPurchasesList.size()));
                    showYearOfPurchase(yearOfPurchasesList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (header.equalsIgnoreCase("programApi")) {
            try {
                programResponse = new JSONArray(response);
                if (programResponse.length() > 0) {
                    Type prgmList = new TypeToken<ArrayList<ProgramsModal>>() {
                    }.getType();
                    programsList = gson.fromJson(response.toString(), prgmList);
                    Log.e("prgm : ", String.valueOf(programsList.size()));
                    showPrograms(programsList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (header.equalsIgnoreCase("tabCountProgWiseApi")) {
            try {
                Log.e("TabCountProgWise : ", response);
                tabCountProgWise = new JSONArray(response);
                if (tabCountProgWise.length() > 0) {
                    Type respList = new TypeToken<ArrayList<Model_TabletCountProgramwise>>() {
                    }.getType();
                    tabCountProgWiseList = gson.fromJson(response.toString(), respList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equals("loading_tablets")) {
            tv_desputedCount.setText("0");
            tv_assignedCount.setText("0");
            tv_unassignedCount.setText("0");
            tv_totalNoOfTabs.setText(Html.fromHtml("Total number of Tablets : <b>0</b>"));
            Log.e("Load Tablets error : ", String.valueOf(anError.getErrorCode()));
            Toast.makeText(requireActivity(), R.string.somethingwrong, Toast.LENGTH_SHORT).show();
        }
    }


    private void showYearOfPurchase(List<Model_YearOfPurchase> yearOfPurchasesList) {
        spnr_yearOfPurchaseList.clear();
        spnr_yearOfPurchaseList.add("Year");
        for (Model_YearOfPurchase yop : yearOfPurchasesList) {
            spnr_yearOfPurchaseList.add(yop.getYearOfPurchase());
        }
        ArrayAdapter yopSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, spnr_yearOfPurchaseList);
        spinner_yearOfpurchase.setAdapter(yopSpnrAdapter);
    }

    private void showDonors(List<Model_Donor> donorList) {
        spnr_donorList.clear();
        spnr_donorList.add("Donor");
        for (Model_Donor donor : donorList) {
            spnr_donorList.add(donor.getDonorName());
        }
        ArrayAdapter donorSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, spnr_donorList);
        spinner_donor.setAdapter(donorSpnrAdapter);
    }

    private void showVendors(List<Model_Vendor> venList) {
        spnr_vendorList.clear();
        Model_Vendor model_vendor = new Model_Vendor();
        model_vendor.setcRLId(-1);
        model_vendor.setFullname("Vendor");
        vendorList.add(0, model_vendor);
        for (Model_Vendor mv : vendorList) {
            spnr_vendorList.add(mv.getFullname());
        }
        ArrayAdapter vendorSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, spnr_vendorList);
        spinner_vendor.setAdapter(vendorSpnrAdapter);

        spinner_vendor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedVendorID = String.valueOf(venList.get(position).getcRLId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void showPrograms(List<ProgramsModal> programsModalList) {
        prgrms.clear();
        ProgramsModal modalProgram = new ProgramsModal();
        modalProgram.setProgramId(-1);
        modalProgram.setProgramName("Program");
        programsList.add(0, modalProgram);
        for (ProgramsModal mp : programsList) {
            prgrms.add(mp.getProgramName());
        }
        ArrayAdapter programAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, prgrms);
        spinner_program.setAdapter(programAdapter);
        spinner_programm.setAdapter(programAdapter);

        spinner_program.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProgramID = String.valueOf(programsModalList.get(position).getProgramId());
                selectedProgramName = programsModalList.get(position).getProgramName();

                if (spinner_program.getSelectedItemPosition() > 0)
                    loadStates();
                else {
                    spinner_state.setEnabled(false);
                    spinner_state.setSelection(0);
                    spinner_block.setEnabled(false);
                    spinner_block.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_programm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProgrammID = String.valueOf(programsModalList.get(position).getProgramId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}