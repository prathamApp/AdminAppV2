package com.pratham.admin.forms;

        import android.app.AlertDialog;
        import android.app.DialogFragment;
        import android.app.FragmentManager;
        import android.content.DialogInterface;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

        import com.pratham.admin.ApplicationController;
        import com.pratham.admin.R;
        import com.pratham.admin.custom.MultiSpinner;
        import com.pratham.admin.custom.RangeTimePickerDialog;
        import com.pratham.admin.database.AppDatabase;
        import com.pratham.admin.modalclasses.Coach;
        import com.pratham.admin.modalclasses.GroupSession;
        import com.pratham.admin.modalclasses.Groups;
        import com.pratham.admin.modalclasses.Modal_Log;
        import com.pratham.admin.modalclasses.Village;
        import com.pratham.admin.util.BackupDatabase;
        import com.pratham.admin.util.BaseActivity;
        import com.pratham.admin.util.CustomGroup;
        import com.pratham.admin.util.DatePickerFragmentOne;
        import com.pratham.admin.util.Utility;

        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;
        import java.util.Date;
        import java.util.List;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import butterknife.OnClick;

public class GroupSessionForm extends BaseActivity implements /*ConnectionReceiverListener,*/ RangeTimePickerDialog.ISelectedTime {

    @BindView(R.id.sp_Village)
    Spinner sp_Village;
    @BindView(R.id.btn_DatePicker)
    Button btn_DatePicker;
    @BindView(R.id.btn_TimeRangePicker)
    Button btn_TimeRangePicker;
    @BindView(R.id.sp_VisitedGroups_multiselect)
    MultiSpinner sp_VisitedGroups_multiselect;
    @BindView(R.id.sp_PresentCoaches_multiselect)
    MultiSpinner sp_PresentCoaches_multiselect;
    @BindView(R.id.sp_WorkCrosscheckedGrps_multiselect)
    MultiSpinner sp_WorkCrosscheckedGrps_multiselect;
    @BindView(R.id.edt_PresentStdCount)
    EditText edt_PresentStdCount;
    @BindView(R.id.btn_Submit)
    Button btn_Submit;

    List<Village> villageList = new ArrayList<>();
    List<Coach> coachList = new ArrayList<>();
    List<Groups> AllGroupsInDB;

    // Visited Groups VG
    List<String> selectedVGArray;
    List<String> selectedVGArrayName;
    List registeredVGGRPs;
    private boolean[] selectedVGItems;
    List<String> VG;
    List<String> VGNames;
    String selectedVG = "";
    String selectedVGNames = "";

    // WorkCrosscheckedGrps WCCG
    List<String> selectedWCCGArray;
    private boolean[] selectedWCCGItems;
    List<String> WCCG;
    String selectedWCCG = "";
    String selectedWCCGNames = "";

    // Present Coaches PC
    List<CustomGroup> registeredPCGRPs;
    private boolean[] selectedPCItems;
    List<String> PC = new ArrayList<>();
    List<String> PCNames = new ArrayList<>();
    String selectedPC = "";
    String selectedPCNames = "";

    java.util.UUID UUID;
    String uniqueVisitID = "";
    String vid;
    private String vName = "";
    private String startTime = "";
    private String endTime = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_session_form);
        ButterKnife.bind(this);
        //getSupportActionBar().hide();

        // Generate Random UUID
        uniqueVisitID = UUID.randomUUID().toString();

        btn_DatePicker.setText(new Utility().GetCurrentDate().toString());
        btn_DatePicker.setPadding(8, 8, 8, 8);

        //retrive all groups from  DB
        AllGroupsInDB = AppDatabase.getDatabaseInstance(this).getGroupDao().getAllGroups();
        Collections.sort(AllGroupsInDB, new Comparator<Groups>() {
            public int compare(Groups v1, Groups v2) {
                return v1.getGroupName().compareTo(v2.getGroupName());
            }
        });

        // Populate Village Spinner
        villageList = AppDatabase.getDatabaseInstance(this).getVillageDao().getAllVillages();
        Collections.sort(villageList, new Comparator<Village>() {
            public int compare(Village v1, Village v2) {
                return v1.getVillageName().compareTo(v2.getVillageName());
            }
        });
        populateVillages();


    }

    private void resetForm() {
        btn_TimeRangePicker.setText(R.string.selecttime);
        btn_Submit.setText(R.string.preview);
        edt_PresentStdCount.getText().clear();
        btn_DatePicker.setText(new Utility().GetCurrentDate().toString());
        btn_DatePicker.setPadding(8, 8, 8, 8);
        uniqueVisitID = UUID.randomUUID().toString();
        //retrive all groups from  DB
        AllGroupsInDB.clear();
        AllGroupsInDB = AppDatabase.getDatabaseInstance(this).getGroupDao().getAllGroups();
        Collections.sort(AllGroupsInDB, new Comparator<Groups>() {
            public int compare(Groups v1, Groups v2) {
                return v1.getGroupName().compareTo(v2.getGroupName());
            }
        });

        // Populate Village Spinner
        villageList.clear();
        villageList = AppDatabase.getDatabaseInstance(this).getVillageDao().getAllVillages();
        Collections.sort(villageList, new Comparator<Village>() {
            public int compare(Village v1, Village v2) {
                return v1.getVillageName().compareTo(v2.getVillageName());
            }
        });
        populateVillages();

        // Populate Coach Spinner
        coachList.clear();
    }

    @OnClick(R.id.btn_DatePicker)
    public void visitDatePicker(View view) {
        btn_Submit.setText(R.string.preview);
        DialogFragment newFragment = new DatePickerFragmentOne();
        newFragment.show(getFragmentManager(), "DatePicker");
    }

    @OnClick(R.id.btn_TimeRangePicker)
    public void TimeRangePicker(View view) {
        btn_Submit.setText(R.string.preview);
        // Create an instance of the dialog fragment and show it
        RangeTimePickerDialog dialog = new RangeTimePickerDialog();
        dialog.newInstance();
        dialog.setIs24HourView(false);
        dialog.setRadiusDialog(16);
        dialog.setTextTabStart(getString(R.string.start));
        dialog.setTextTabEnd(getString(R.string.end));
        dialog.setTextBtnPositive(getString(R.string.accept));
        dialog.setTextBtnNegative(getString(R.string.close));
        dialog.setValidateRange(true);
        dialog.setColorBackgroundHeader(R.color.colorPrimary); // top header background
        dialog.setColorBackgroundTimePickerHeader(R.color.colorPrimary); // Digital Time Backgrounds
        dialog.setColorTextButton(R.color.colorPrimaryDark);
        FragmentManager fragmentManager = getFragmentManager();
        dialog.show(fragmentManager, "");
    }


    @Override
    public void onSelectedTime(int hourStart, int minuteStart, int hourEnd, int minuteEnd) {
        startTime = hourStart + ":" + minuteStart;
        endTime = hourEnd + ":" + minuteEnd;

        String sT = startTime;
        String eT = endTime;
        DateFormat df = new SimpleDateFormat("HH:mm");
        DateFormat outputformat = new SimpleDateFormat("hh:mm a");
        Date sdate = null;
        Date edate = null;
        String startoutput = null;
        String endoutput = null;
        try {
            sdate = df.parse(sT);
            startoutput = outputformat.format(sdate);
            edate = df.parse(eT);
            endoutput = outputformat.format(edate);
            btn_TimeRangePicker.setText("From : " + startoutput + " | " + "\tTo : " + endoutput);
        } catch (Exception e) {
            Modal_Log log = new Modal_Log();
            log.setCurrentDateTime(new Utility().GetCurrentDate());
            log.setErrorType("ERROR");
            log.setExceptionMessage(e.getMessage());
            log.setExceptionStackTrace(e.getStackTrace().toString());
            log.setMethodName("GroupSessionForm" + "_" + "onSelectedTime");
            log.setDeviceId("");
            AppDatabase.getDatabaseInstance(ApplicationController.getInstance()).getLogDao().insertLog(log);
            BackupDatabase.backup(ApplicationController.getInstance());

            e.printStackTrace();
        }

    }

    private void populateVillages() {
        final List VillageName = new ArrayList();
        if (!villageList.isEmpty()) {
            VillageName.add(new CustomGroup(getString(R.string.selectvillage)));
            for (int j = 0; j < villageList.size(); j++) {
                CustomGroup customGroup = new CustomGroup(villageList.get(j).getVillageName(), villageList.get(j).getVillageId());
                VillageName.add(customGroup);
            }
            ArrayAdapter villageAdapter = new ArrayAdapter(GroupSessionForm.this, android.R.layout.simple_spinner_dropdown_item, VillageName);
            sp_Village.setAdapter(villageAdapter);
        }

        sp_Village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                btn_Submit.setText(R.string.preview);

                CustomGroup customGroup = (CustomGroup) VillageName.get(pos);
                vid = customGroup.getId();
                vName = customGroup.getName();

                // Populate Visited Groups Spinner
                populateVisitedGroups(vid);

                // Populate Coach Spinner
                coachList = AppDatabase.getDatabaseInstance(GroupSessionForm.this).getCoachDao().getCoachByVillageID(vid);
                Collections.sort(coachList, new Comparator<Coach>() {
                    public int compare(Coach v1, Coach v2) {
                        return v1.getCoachName().compareTo(v2.getCoachName());
                    }
                });
                populatePresentCoaches();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    // VISITED GROUPS
    private void populateVisitedGroups(String villageID) {
        // todo get registered grps
        selectedVG = "";
        selectedVGNames = "";
        registeredVGGRPs = new ArrayList();
        if (AllGroupsInDB != null) {
            VG = new ArrayList<>();
            VGNames = new ArrayList<>();
            for (int i = 0; i < AllGroupsInDB.size(); i++) {
                if (AllGroupsInDB.get(i).getVillageId().equals(villageID)) {
                    registeredVGGRPs.add(new CustomGroup(AllGroupsInDB.get(i).getGroupName(), AllGroupsInDB.get(i).getGroupId()));
//                    VG[i] = AllGroupsInDB.get(i).getGroupId();
                    VG.add(AllGroupsInDB.get(i).getGroupId());
                    VGNames.add(AllGroupsInDB.get(i).getGroupName());
                }
            }
        }

        ArrayAdapter grpAdapter = new ArrayAdapter(GroupSessionForm.this, android.R.layout.simple_spinner_dropdown_item, registeredVGGRPs);
        sp_VisitedGroups_multiselect.setAdapter(grpAdapter, false, onVGSelectedListener);
        // set initial selection
        selectedVGItems = new boolean[grpAdapter.getCount()];
        sp_VisitedGroups_multiselect.setHint(getString(R.string.selectvisitedgrp));
        sp_VisitedGroups_multiselect.setHintTextColor(Color.BLACK);
    }

    // VG Listener
    private MultiSpinner.MultiSpinnerListener onVGSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
            btn_Submit.setText(R.string.preview);
            List<String> grp_sel = new ArrayList<>();
            selectedVGArray = new ArrayList<>();
            selectedVGArrayName = new ArrayList<>();
            selectedVG = "";
            selectedVGNames = "";
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) {
                    selectedVGArray.add(VG.get(i));
                    selectedVGArrayName.add(VGNames.get(i));
                    selectedVG = selectedVG + "," + VG.get(i);
                    selectedVGNames = selectedVGNames + "," + VGNames.get(i);
                }
            }
            selectedVG = selectedVG.replaceFirst(",", "");
            selectedVGNames = selectedVGNames.replaceFirst(",", "");

            // Populate WorkCrosscheckedGrps
            populateWorkCrosscheckedGrps(selectedVGArray, selectedVGArrayName);
        }
    };


    // WorkCrosscheckedGrps GROUPS
    private void populateWorkCrosscheckedGrps(final List<String> selectedGrpID, final List<String> selectedgrpName) {
        // todo get registered grps
        List registeredWCCGGRPs = new ArrayList();
        selectedWCCG = "";
        selectedWCCGNames = "";

        for (int i = 0; i < AllGroupsInDB.size(); i++) {
            for (int j = 0; j < selectedGrpID.size(); j++) {
                if (AllGroupsInDB.get(i).getGroupId().equalsIgnoreCase(selectedGrpID.get(j))) {
                    registeredWCCGGRPs.add(AllGroupsInDB.get(i).getGroupName());
                }
            }
        }

        ArrayAdapter grpAdapter = new ArrayAdapter(GroupSessionForm.this, android.R.layout.simple_spinner_dropdown_item, registeredWCCGGRPs);
        sp_WorkCrosscheckedGrps_multiselect.setAdapter(grpAdapter, false, new MultiSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] selected) {
                btn_Submit.setText(R.string.preview);

                selectedWCCGArray = new ArrayList<>();
                selectedWCCG = "";
                selectedWCCGNames = "";
                for (int i = 0; i < selected.length; i++) {
                    if (selected[i]) {
                        selectedWCCGArray.add(selectedGrpID.get(i));
                        selectedWCCG = selectedWCCG + "," + selectedGrpID.get(i);
                        selectedWCCGNames = selectedWCCGNames + "," + selectedgrpName.get(i);
                    }
                }
                selectedWCCG = selectedWCCG.replaceFirst(",", "");
                selectedWCCGNames = selectedWCCGNames.replaceFirst(",", "");
            }
        });
        // set initial selection
        selectedWCCGItems = new boolean[grpAdapter.getCount()];
        sp_WorkCrosscheckedGrps_multiselect.setHint(getString(R.string.workcrosschk));
        sp_WorkCrosscheckedGrps_multiselect.setHintTextColor(Color.BLACK);
    }

    // Present Groups
    private void populatePresentCoaches() {
        registeredPCGRPs = new ArrayList();
        selectedPC = "";
        selectedPCNames = "";

        for (int j = 0; j < coachList.size(); j++) {
            CustomGroup customGroup = new CustomGroup(coachList.get(j).getCoachName(), coachList.get(j).getCoachID());
            registeredPCGRPs.add(customGroup);
            PC.add(coachList.get(j).getCoachID());
            PCNames.add(coachList.get(j).getCoachName());
        }

        ArrayAdapter coachAdapter = new ArrayAdapter(GroupSessionForm.this, android.R.layout.simple_spinner_dropdown_item, registeredPCGRPs);
        sp_PresentCoaches_multiselect.setAdapter(coachAdapter, false, onPCSelectedListener);
        // set initial selection
        selectedPCItems = new boolean[coachAdapter.getCount()];
        sp_PresentCoaches_multiselect.setHint(getString(R.string.helpingcoaches));
        sp_PresentCoaches_multiselect.setHintTextColor(Color.BLACK);

    }

    // PC Listener
    private MultiSpinner.MultiSpinnerListener onPCSelectedListener = new MultiSpinner.MultiSpinnerListener() {
        public void onItemsSelected(boolean[] selected) {
            btn_Submit.setText(R.string.preview);
            // Do something here with the selected items
            List<String> selectedPCArray = new ArrayList<>();
            List<String> selectedPCArrayNames = new ArrayList<>();
            selectedPC = "";
            selectedPCNames = "";
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) {
                    selectedPCArray.add(PC.get(i));
                    selectedPCArrayNames.add(PCNames.get(i));
                    selectedPC = selectedPC + "," + PC.get(i);
                    selectedPCNames = selectedPCNames + "," + PCNames.get(i);
                }
            }
            selectedPC = selectedPC.replaceFirst(",", "");
            selectedPCNames = selectedPCNames.replaceFirst(",", "");
        }
    };

    @OnClick(R.id.btn_Submit)
    public void submitForm(View view) {
        if ((sp_Village.getSelectedItemPosition() > 0) && (selectedVG.trim().length() > 0)
                && (selectedWCCG.trim().length() > 0) && (selectedPC.trim().length() > 0)
                && (!btn_TimeRangePicker.getText().toString().equalsIgnoreCase(getString(R.string.selecttime)))
                && (edt_PresentStdCount.getText().toString().trim().length() > 0)) {
            try {

                String date = btn_DatePicker.getText().toString().trim();

                GroupSession grpSessionObj = new GroupSession();

                grpSessionObj.GroupSessionID = uniqueVisitID;
                grpSessionObj.VillageID = Integer.parseInt(vid);
                grpSessionObj.DateVisited = date;
                grpSessionObj.GroupIDVisited = selectedVG;
                grpSessionObj.CoachPresentInVillage = selectedPC;
                grpSessionObj.WorkCrosscheckedGroupIDs = selectedWCCG;
                grpSessionObj.PresentStudents = edt_PresentStdCount.getText().toString().trim();
                grpSessionObj.Village = vName;
                grpSessionObj.Group = "";
                grpSessionObj.StartTime = startTime;
                grpSessionObj.EndTime = endTime;
                grpSessionObj.sentFlag = 0;

                if (btn_Submit.getText().toString().equalsIgnoreCase(getString(R.string.submit))) {

                    AppDatabase.getDatabaseInstance(this).getGroupSessionDao().insertAllGroupSession(Collections.singletonList(grpSessionObj));
                    Toast.makeText(this, R.string.formSavedtoDB, Toast.LENGTH_SHORT).show();
                    resetForm();

                } else {
                    // Preview Dialog
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(GroupSessionForm.this, android.R.style.Theme_Material_Light_Dialog);
                    dialogBuilder.setCancelable(false);
                    dialogBuilder.setTitle(getString(R.string.formdatapreview));
                    dialogBuilder.setMessage(getString(R.string.villagename) + vName
                            + "\n"+getString(R.string.datevisited) + date
                            + "\n"+getString(R.string.observedgrp) + selectedVGNames
                            + "\n"+getString(R.string.workcrosschk)+" : " + selectedWCCGNames
                            + "\n"+getString(R.string.helpingcoaches)+" : " + selectedPCNames
                            + "\n"+getString(R.string.studentspresent)+" : " + edt_PresentStdCount.getText().toString().trim());

                    dialogBuilder.setPositiveButton(R.string.correct, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            btn_Submit.setText(R.string.submit);
                        }
                    });
                    dialogBuilder.setNegativeButton(R.string.wrong, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            btn_Submit.setText(R.string.preview);
                        }
                    });
                    AlertDialog b = dialogBuilder.create();
                    b.show();
                }

            } catch (Exception e) {
                Modal_Log log = new Modal_Log();
                log.setCurrentDateTime(new Utility().GetCurrentDate());
                log.setErrorType("ERROR");
                log.setExceptionMessage(e.getMessage());
                log.setExceptionStackTrace(e.getStackTrace().toString());
                log.setMethodName("GroupSessionForm" + "_" + "submitForm");
                log.setDeviceId("");
                AppDatabase.getDatabaseInstance(ApplicationController.getInstance()).getLogDao().insertLog(log);
                BackupDatabase.backup(ApplicationController.getInstance());

                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, R.string.fillAllFields, Toast.LENGTH_SHORT).show();
        }
    }


}
