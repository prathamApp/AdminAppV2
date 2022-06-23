package com.pratham.admin.ui.blockLeader.tabHolders;

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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.pratham.admin.modalclasses.Model_Role;
import com.pratham.admin.modalclasses.Model_User;
import com.pratham.admin.modalclasses.ProgramsModal;
import com.pratham.admin.modalclasses.Village;
import com.pratham.admin.ui.CRL_ProfileFragment_;
import com.pratham.admin.ui.blockLeader.Inventory.InventoryFragment_;
import com.pratham.admin.util.APIs;
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

import static com.pratham.admin.util.APIs.getIdWiseUser;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_tab_holder)
public class TabHolderFragment extends Fragment implements TabHolderListItemListener, NetworkCallListener {

    @ViewById(R.id.et_search)
    EditText searchTab;

    @ViewById(R.id.spinner_program)
    Spinner spinner_program;

    @ViewById(R.id.spinner_state)
    Spinner spinner_state;

    @ViewById(R.id.spinner_block)
    Spinner spinner_block;

    @ViewById(R.id.spinner_tabHolder)
    Spinner spinner_tabHolder;

    @ViewById(R.id.rv_tabHolder)
    public RecyclerView rv_tabHolder;

    @ViewById(R.id.ll_spinnerOne)
    LinearLayout ll_spinnerOne;

    @ViewById(R.id.ll_spinnerTwo)
    LinearLayout ll_spinnerTwo;

    @ViewById(R.id.tv_noDataFound)
    TextView tv_noDataFound;

    private List<CRL> crlList = new ArrayList<>();
    private TabHoldersAdapter tabHoldersAdapter;

    //BlurPopup Sms Dialog
    private BlurPopupWindow writeSmsDialog;
    EditText et_writeSms;

    String assigneePersonId, assigneePersonName;

    private List<Model_User> userList = new ArrayList<>();
    boolean isStoreManager = false;

    String[] stateCode;
    String selectedBlock = "NO BLOCKS";
    public static String selectedProgramID = null;
    public static String selectedRoleID = null;
    private String[] states;
    private int selectedState;
    private String selectedStateName = "MH";


    boolean apiLoadFlag = false;

    JSONArray programResponse;
    private ArrayList<ProgramsModal> programsList = new ArrayList<>();
    List<String> prgrms = new ArrayList<>();

    JSONArray blockResponse;
    private ArrayList<Village> villageList = new ArrayList<>();
    List<String> spnr_blockList = new ArrayList<>();
    List<String> villages = new ArrayList<>();


    JSONArray tabholdersResponse;

    JSONArray roleResponse;
    List<Model_Role> roleList = new ArrayList<>();
    List<String> spnr_roleList = new ArrayList<>();

    public TabHolderFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {
        NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, APIs.getRole, "Loading...", "roleApi", getActivity());

        if (FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("13")) {
            ll_spinnerOne.setVisibility(View.VISIBLE);
            ll_spinnerTwo.setVisibility(View.VISIBLE);
            isStoreManager = true;
            storeManagerAPI();
        } else {
            ll_spinnerOne.setVisibility(View.GONE);
            ll_spinnerTwo.setVisibility(View.VISIBLE);
            isStoreManager = false;
            getBlocks();
            prepareData();
            initializeAdapter();
        }

        spinner_block.setEnabled(false);
        spinner_state.setEnabled(false);
        spinner_tabHolder.setEnabled(false);
        states = getResources().getStringArray(R.array.india_states);
        ArrayAdapter adapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_state.setAdapter(adapter);

        searchTab.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rv_tabHolder.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });
    }

    public void initializeAdapter() {
        tabHoldersAdapter = new TabHoldersAdapter(getActivity(), crlList, TabHolderFragment.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_tabHolder.setLayoutManager(mLayoutManager);
        rv_tabHolder.setItemAnimator(new DefaultItemAnimator());
        rv_tabHolder.setAdapter(tabHoldersAdapter);
        tabHoldersAdapter.notifyDataSetChanged();
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
        tabHoldersAdapter.filterList(filterdNames);
    }


    private void storeManagerAPI() {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
            String idWiseUser = getIdWiseUser+FastSave.getInstance().getString("CRLid","no_crl");
            Log.e("API : ", idWiseUser);
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, APIs.programsAPI, "Loading Programs...", "programApi", getActivity());
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, idWiseUser, "Loading TabHolders...", "loading_users", getActivity());
        }
    }

    private void prepareData() {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
            String idWiseUser = getIdWiseUser+FastSave.getInstance().getString("CRLid","no_crl");
            Log.e("API : ", idWiseUser);
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, idWiseUser, "Loading TabHolders...", "loading_users", getActivity());
        }
    }

    @Override
    public void sendSms(String mobileNumber) {
        if (mobileNumber.length() >= 10) {
            writeSmsDialog = new BlurPopupWindow.Builder(getActivity())
                    .setContentView(R.layout.dialog_write_sms)
                    .bindClickListener(v -> {
                        writeSmsDialog.dismiss();
                    }, R.id.dialog_btn_cancel)
                    .bindClickListener(v -> {
                        try {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(mobileNumber, null, et_writeSms.getText().toString(), null, null);
                            Toast.makeText(getActivity(), "SMS sent.", Toast.LENGTH_LONG).show();
/*                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("smsto:"));
                            i.setType("vnd.android-dir/mms-sms");
                            i.putExtra("address", new String("7620818200"));
                            i.putExtra("sms_body",et_writeSms.getText().toString());
                            startActivity(Intent.createChooser(i, "Send sms via:"));*/
                        } catch (Exception e) {
                            Log.e("error : ", e.getMessage());
                            Log.e("error 1: ", e.getLocalizedMessage());
                        }
                        writeSmsDialog.dismiss();
                    }, R.id.dialog_btn_send)
                    .setGravity(Gravity.CENTER)
                    .setDismissOnTouchBackground(false)
                    .setDismissOnClickBack(false)
                    .setScaleRatio(0.2f)
                    .setBlurRadius(10)
                    .setTintColor(0x30000000)
                    .build();
            et_writeSms = writeSmsDialog.findViewById(R.id.et_writeSms);
            writeSmsDialog.show();
        }
    }

    @Override
    public void tabHolderItemClicked(View v, CRL crl, int position) {
        for (CRL crl1 : tabHoldersAdapter.getCrlList()) {
            if (crl1.getCRLId().equalsIgnoreCase(crl.getCRLId())) {
                crl1.setSelected(true);
                assigneePersonId = crl1.getCRLId();
                assigneePersonName = crl1.getFirstName();
            } else
                crl1.setSelected(false);
        }
//        temp.set(position, modalGroup);
        tabHoldersAdapter.notifyDataSetChanged();
    }

    @Override
    public void tabHolderDetails(CRL crl) {
        Bundle tabHolderDetail = new Bundle();
        tabHolderDetail.putParcelable("TabHolder_Detail",  crl);
        Utility.showFragment(getActivity(), new CRL_ProfileFragment_(),R.id.fragment_container,
                tabHolderDetail, CRL_ProfileFragment_.class.getSimpleName());
    }

    @Click(R.id.btn_allocate)
    public void allocateTab() {
        if (assigneePersonId!=null) {
            Bundle homebundle = new Bundle();
            homebundle.putString("assigneeId", assigneePersonId);
            homebundle.putString("assigneeName", assigneePersonName);
            Utility.showFragment(getActivity(), new InventoryFragment_(), R.id.fragment_container,
                    homebundle, InventoryFragment_.class.getSimpleName());
        } else {
            Toast.makeText(getActivity(), "Select TabHolder First!", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.btn_go)
    public void loadTabHolders() {
        if (isStoreManager) {
            if (spinner_program.getSelectedItem().toString().equalsIgnoreCase("Program") ||
                    spinner_state.getSelectedItem().toString().equalsIgnoreCase("State") ||
                    spinner_block.getSelectedItem().toString().equalsIgnoreCase("Block") ||
                    spinner_tabHolder.getSelectedItem().toString().equalsIgnoreCase("TabHolder"))
                Toast.makeText(getActivity(), "Select Fields First!", Toast.LENGTH_SHORT).show();
            else {
                String url = APIs.getUsersByFields + selectedProgramID +
                        APIs.SERVER_ROLE + selectedRoleID +
                        APIs.SERVER_STATENAME + spinner_state.getSelectedItem().toString() +
                        APIs.SERVER_BLOCKNAME + spinner_block.getSelectedItem().toString();
                Log.e("ProgStateRole url : ", url);
                if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
                    NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, url, "Loading...", "loading_tabholders_byrole", getActivity());
                }
            }
        } else {
            crlList = AppDatabase.getDatabaseInstance(getActivity()).getCRLdao().getCRLsByBlockAndRole(spinner_block.getSelectedItem().toString(), spinner_tabHolder.getSelectedItem().toString(), FastSave.getInstance().getString("CRLid", ""));
            Log.e("CRL List Filter: ", String.valueOf(crlList.size()));
            tabHoldersAdapter.filterList((ArrayList<CRL>) crlList);
            if (crlList.size() == 0) {
                tv_noDataFound.setVisibility(View.VISIBLE);
            } else {
                tv_noDataFound.setVisibility(View.GONE);
            }
        }
    }

    //get blocks directly
    private void getBlocks() {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
            String url;
            stateCode = getResources().getStringArray(R.array.india_states_shortcode);
            url = APIs.pullVillagesServerURL +
                    FastSave.getInstance().getString("programId", "") +
                    APIs.SERVER_STATE +
                    FastSave.getInstance().getString("stateCode", "");
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, url, "Loading...", "blockApi", getActivity());
        } else {
            spinner_state.setSelection(0);
            Toast.makeText(requireActivity(), R.string.noInterntCon, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onResponse(String response, String header) {
        Gson gson = new Gson();
        if (header.equalsIgnoreCase("loading_users")) {
            try {
                tabholdersResponse = new JSONArray(response);
                if (tabholdersResponse.length() > 0) {
                    Type tabholderList = new TypeToken<ArrayList<CRL>>() {
                    }.getType();
                    crlList = gson.fromJson(response.toString(), tabholderList);
                    Log.e("crl : ", String.valueOf(crlList.size()));
                    initializeAdapter();
                    Utility.dismissLoadingDialog();
                    rv_tabHolder.setVisibility(View.VISIBLE);
                    tv_noDataFound.setVisibility(View.GONE);
                } else {
                    tv_noDataFound.setVisibility(View.VISIBLE);
                    rv_tabHolder.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "No TabHolder Found.", Toast.LENGTH_SHORT).show();
                    Utility.dismissLoadingDialog();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (header.equalsIgnoreCase("loading_tabholders_byrole")) {
            try {
                tabholdersResponse = new JSONArray(response);
                if (tabholdersResponse.length() > 0) {
                    Type tabholderList = new TypeToken<ArrayList<CRL>>() {
                    }.getType();
                    crlList = gson.fromJson(response.toString(), tabholderList);
                    Log.e("crl : ", String.valueOf(crlList.size()));
                    initializeAdapter();
                    Utility.dismissLoadingDialog();
                    rv_tabHolder.setVisibility(View.VISIBLE);
                    tv_noDataFound.setVisibility(View.GONE);
                } else {
                    tv_noDataFound.setVisibility(View.VISIBLE);
                    rv_tabHolder.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "No TabHolder Found.", Toast.LENGTH_SHORT).show();
                    Utility.dismissLoadingDialog();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (header.equalsIgnoreCase("roleApi")) {
            try {
                roleResponse = new JSONArray(response);
                if (roleResponse.length() > 0) {
                    Type roleLst = new TypeToken<ArrayList<Model_Role>>() {
                    }.getType();
                    roleList = gson.fromJson(response.toString(), roleLst);
                    Log.e("role : ", String.valueOf(roleList.size()));
                    showRoles(roleList);
                    if (!isStoreManager) Utility.dismissLoadingDialog();
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
        } else if (header.equalsIgnoreCase("blockApi")) {
            try {
                blockResponse = new JSONArray(response);
                Log.e("Block Response : ", blockResponse.toString());
                showBlocks(villageList, blockResponse);
                Utility.dismissLoadingDialog();
            } catch (Exception e) {
                Log.e("API Exception : ", e.getMessage());
                e.printStackTrace();
                Utility.dismissLoadingDialog();
            }
        }
    }

    @Override
    public void onError(ANError anError, String header) {
        Utility.dismissLoadingDialog();
        Log.e("API ERROR : ", anError.getMessage());

    }

    private void showRoles(List<Model_Role> rolList) {
        spnr_roleList.clear();
        Model_Role model_role = new Model_Role();
        model_role.setRoleid(0);
        model_role.setRolename("TabHolder");
        roleList.add(0, model_role);
        for (Model_Role mv : roleList) {
            spnr_roleList.add(mv.getRolename());
        }
        ArrayAdapter roleSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, spnr_roleList);
        spinner_tabHolder.setAdapter(roleSpnrAdapter);

        spinner_tabHolder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRoleID = String.valueOf(roleList.get(position).getRoleid());
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

        spinner_program.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProgramID = String.valueOf(programsModalList.get(position).getProgramId());

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
    }

    private void showBlocks(List<Village> villageModelList, JSONArray villageResponse) {
        Gson gson = new Gson();
        Type blkList = new TypeToken<ArrayList<Village>>() {
        }.getType();
        villageList = gson.fromJson(villageResponse.toString(), blkList);
        Log.e("block : ", String.valueOf(villageList.size()));

        spinner_block.setEnabled(true);
        ArrayList blockNames = new ArrayList();
        LinkedHashSet hs = new LinkedHashSet();
        if (villageList.size() == 0) {
            blockNames.add("NO BLOCK");
            Toast.makeText(getActivity(), "No Blocks Found!", Toast.LENGTH_SHORT).show();
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

        spinner_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBlock = parent.getItemAtPosition(position).toString();
                if ((!selectedBlock.equals("NO BLOCK")) && (!selectedBlock.equals("Block"))) {
                    spinner_tabHolder.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadStates() {
        spinner_state.setEnabled(true);
        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                apiLoadFlag = false;
                // spinner_village.setVisibility(View.INVISIBLE);
                getVillageStatewise(selectedProgramID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //get blocks by passing progamid and state from spinner
    private void getVillageStatewise(String spID) {
        //  if (ConnectionManager.getConnectionManager().checkConnection(this)) {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
            selectedState = spinner_state.getSelectedItemPosition();
            selectedStateName = states[selectedState];
            if (!states[selectedState].equals("Select State")) {
                String url;
                stateCode = getResources().getStringArray(R.array.india_states_shortcode);
                url = APIs.pullVillagesServerURL + spID + APIs.SERVER_STATE + stateCode[selectedState];
                NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, url, "Loading...", "blockApi", getActivity());
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


}