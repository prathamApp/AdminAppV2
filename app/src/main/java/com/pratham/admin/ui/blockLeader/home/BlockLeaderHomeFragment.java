package com.pratham.admin.ui.blockLeader.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pratham.admin.R;
import com.pratham.admin.activities.SelectVillageDialog;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.interfaces.ConnectionReceiverListener;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.interfaces.NetworkCallListnerSelectProgram;
import com.pratham.admin.modalclasses.ProgramsModal;
import com.pratham.admin.modalclasses.TabletCount;
import com.pratham.admin.modalclasses.Village;
import com.pratham.admin.ui.login.SelectProgram;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.ConnectionReceiver;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

import static com.pratham.admin.util.APIs.village;

@EFragment(R.layout.fragment_block_leader_home)
public class BlockLeaderHomeFragment extends Fragment implements NetworkCallListnerSelectProgram, ConnectionReceiverListener, NetworkCallListener {

    @ViewById(R.id.spinner_program)
    Spinner spinner_program;

    @ViewById(R.id.spinner_state)
    Spinner spinner_state;

    @ViewById(R.id.spinner_block)
    Spinner spinner_block;

    @ViewById(R.id.tv_desputed_tab)
    TextView tv_desputedCount;

    @ViewById(R.id.tv_assigned_tab)
    TextView tv_assignedCount;

    @ViewById(R.id.tv_unassigned_tab)
    TextView tv_unassignedCount;

    @ViewById(R.id.tv_totalNoOfTabs)
    TextView tv_totalNoOfTabs;

    @ViewById(R.id.switch_vendor)
    SwitchMaterial switchMaterial;

    @ViewById(R.id.rl_tabStatusTwo)
    RelativeLayout rl_tabStatusTwo;

    @ViewById(R.id.rl_tabStatus)
    RelativeLayout rl_tabStatus;

    @ViewById(R.id.ll_spinner)
    LinearLayout ll_spinner;

    @ViewById(R.id.rl_spinnerParent)
    RelativeLayout rl_spinnerParent;

    @ViewById(R.id.rl_spinnerParentTwo)
    RelativeLayout rl_spinnerParentTwo;

    Dialog dialog;
    String[] stateCode;
    String selectedBlock = "NO BLOCKS";
    public static String selectedProgramID = null;
    public static String selectedProgramName = null;

    boolean apiLoadFlag = false;
    boolean internetIsAvailable = false;
    boolean errorDetected = false;

    private String[] states;
    private int selectedState;
    private String selectedStateName = "MH";

    private List<Village> villageList = new ArrayList();
    private ArrayList<ProgramsModal> programsList = new ArrayList<>();
    List<String> prgrms = new ArrayList<>();

    public BlockLeaderHomeFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {

        if (FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("1")) {
            switchMaterial.setVisibility(View.VISIBLE);
            ll_spinner.setVisibility(View.VISIBLE);
        } else {
            switchMaterial.setVisibility(View.GONE);
            ll_spinner.setVisibility(View.GONE);
        }

        checkConnection();
/*        spinner_block.setEnabled(false);
        spinner_state.setEnabled(false);
        states = getResources().getStringArray(R.array.india_states);
        ArrayAdapter adapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_state.setAdapter(adapter);*/
//        loadprogams();
        getVillageStatewise(); //calling to get directly blocks
        loadTabletCount();

        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rl_tabStatusTwo.setVisibility(View.VISIBLE);
                    rl_tabStatus.setVisibility(View.GONE);
                    rl_spinnerParentTwo.setVisibility(View.VISIBLE);
                    rl_spinnerParent.setVisibility(View.GONE);
                } else {
                    rl_tabStatusTwo.setVisibility(View.GONE);
                    rl_tabStatus.setVisibility(View.VISIBLE);
                    rl_spinnerParentTwo.setVisibility(View.GONE);
                    rl_spinnerParent.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    //Used to get count of despute, assigned, unassigned and total tablets
    private void loadTabletCount() {
        checkConnection();
        if (internetIsAvailable) {
            String url = APIs.tabletCountAPI + FastSave.getInstance().getString("CRLid", "no_crl");
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

    @Click(R.id.btn_go)
    public void loadCountBlockWise() {

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

    @Click(R.id.btn_refresh)
    public void refreshTabCount() {
        loadTabletCount();
        spinner_block.setSelection(0);
    }

    private void loadprogams() {
        //NetworkCalls.getNetworkCallsInstance(this).getRequest(this, url, "Loading Programs..", "loading_devises");

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
    }

    private void showPrograms(final List<ProgramsModal> programsModalList) {
        for (ProgramsModal mp : programsList) {
            prgrms.add(mp.getProgramName());
        }
        //Toast.makeText(requireActivity(), "Please Select Program", Toast.LENGTH_SHORT).show();
        ArrayAdapter programAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, prgrms);
        spinner_program.setAdapter(programAdapter);
        spinner_program.setVisibility(View.VISIBLE);

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
    }

    private void loadStates() {
        spinner_state.setEnabled(true);
        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                apiLoadFlag = false;
                // spinner_village.setVisibility(View.INVISIBLE);
                //getVillageStatewise(selectedProgramID, selectedProgramName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getVillageStatewise() {
        //  if (ConnectionManager.getConnectionManager().checkConnection(this)) {
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
        dismissShownDialog();
    }


    private void showDialoginApiCalling(String program, String type) {
        if (dialog == null) {
            dialog = new ProgressDialog(getActivity());
        }
        dialog.setTitle(getString(R.string.pulling) + program + " " + type);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
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
                dismissShownDialog();
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
        if (header.equalsIgnoreCase("loading_tablets")) {
            Gson gson = new Gson();
            TabletCount tabletCount = gson.fromJson(response, TabletCount.class);
            tv_desputedCount.setText(tabletCount.getTotalDisputed());
            tv_assignedCount.setText(tabletCount.getTotalAssigned());
            tv_unassignedCount.setText(tabletCount.getTotalUnassigned());
            tv_totalNoOfTabs.setText(Html.fromHtml("Total number of Tablets : <b>" + tabletCount.getTotalCount() + "</b>"));
        }
    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equals("loading_tablets")) {
            tv_desputedCount.setText("0");
            tv_assignedCount.setText("0");
            tv_unassignedCount.setText("0");
            tv_totalNoOfTabs.setText(Html.fromHtml("Total number of Tablets : <b>0</b>"));
            Toast.makeText(requireActivity(), R.string.chkInternet, Toast.LENGTH_SHORT).show();
        }
    }
}