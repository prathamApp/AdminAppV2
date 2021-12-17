package com.pratham.admin.forms;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.pratham.admin.R;
import com.pratham.admin.database.AppDatabase;
import com.pratham.admin.modalclasses.Coach;
import com.pratham.admin.modalclasses.Village;
import com.pratham.admin.util.BaseActivity;
import com.pratham.admin.util.CustomGroup;
import com.pratham.admin.util.DatePickerFragmentOne;
import com.pratham.admin.util.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoachRetentionForm extends BaseActivity/* implements ConnectionReceiverListener */ {

    @BindView(R.id.sp_Village)
    Spinner sp_Village;
    @BindView(R.id.sp_SelectCoach)
    Spinner sp_SelectCoach;
    @BindView(R.id.rg_DropOut)
    RadioGroup rg_DropOut;
    @BindView(R.id.rb_Yes)
    RadioButton rb_Yes;
    @BindView(R.id.btn_DatePicker)
    Button btn_DatePicker;
    @BindView(R.id.btn_Submit)
    Button btn_Submit;
    List<Village> villageList = new ArrayList<>();
    List<Coach> coachList = new ArrayList<>();
    String selectedCoachID = "";
    List<Coach> updatedCoachList = new ArrayList<>();
    String villageName;
    String vid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_retention_form);
        ButterKnife.bind(this);
        //getSupportActionBar().hide();

        btn_DatePicker.setText(new Utility().GetCurrentDate().toString());
        btn_DatePicker.setPadding(8, 8, 8, 8);

        // Populate Village Spinner
        villageList = AppDatabase.getDatabaseInstance(this).getVillageDao().getAllVillages();
        Collections.sort(villageList, new Comparator<Village>() {
            public int compare(Village v1, Village v2) {
                return v1.getVillageName().compareTo(v2.getVillageName());
            }
        });
        populateVillages();

    }

    @OnClick(R.id.btn_Submit)
    public void submitForm(View view) {

        if ((sp_Village.getSelectedItemPosition() > 0) && (sp_SelectCoach.getSelectedItemPosition() > 0)) {
            String endDate = btn_DatePicker.getText().toString().trim();

            // Dropout code on Submit Click
            int selectedId = rg_DropOut.getCheckedRadioButtonId();
            RadioButton selectedOption = (RadioButton) findViewById(selectedId);
            String DropOut = selectedOption.getText().toString();
            int status;
            if (DropOut.equalsIgnoreCase(getString(R.string.yes))) {
                status = 0; // Inactive
            } else {
                status = 1; // Active
                endDate = ""; // Still Active
            }
            String coachID = selectedCoachID;

            updatedCoachList = AppDatabase.getDatabaseInstance(CoachRetentionForm.this).getCoachDao().getCoachByID(selectedCoachID);

            // get Coach info by id
            Coach cObj = new Coach();
            cObj.CoachID = updatedCoachList.get(0).CoachID;
            cObj.CoachName = updatedCoachList.get(0).CoachName;
            cObj.CoachAge = updatedCoachList.get(0).CoachAge;
            cObj.CoachGender = updatedCoachList.get(0).CoachGender;
            cObj.CoachSubjectExpert = updatedCoachList.get(0).CoachSubjectExpert;
            cObj.CoachOccupation = updatedCoachList.get(0).CoachOccupation;
            cObj.CoachSpeciality = updatedCoachList.get(0).CoachSpeciality;
            cObj.CoachEducation = updatedCoachList.get(0).CoachEducation;
            cObj.CoachActive = status; // new Values 1:Active, 0:Inactive
            cObj.CoachGroupID = updatedCoachList.get(0).CoachGroupID;
            cObj.StartDate = updatedCoachList.get(0).StartDate;
            cObj.EndDate = endDate; // new Values
            cObj.CreatedBy = updatedCoachList.get(0).CreatedBy;
            cObj.CreatedDate = updatedCoachList.get(0).CreatedDate;
            cObj.CoachVillageID = vid;
            cObj.sentFlag = 0;

            if (btn_Submit.getText().toString().equalsIgnoreCase(getString(R.string.submit))) {

                AppDatabase.getDatabaseInstance(this).getCoachDao().updateCoachStatus(status, endDate, coachID);
                Toast.makeText(this, R.string.formSubmittedtoDB, Toast.LENGTH_SHORT).show();
                resetForm();

            } else {
                // Preview Dialog
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CoachRetentionForm.this, android.R.style.Theme_Material_Light_Dialog);
                dialogBuilder.setCancelable(false);
                dialogBuilder.setTitle(R.string.formdatapreview);

                dialogBuilder.setMessage(getString(R.string.villagename) + villageName
                        + "\n"+getString(R.string.coachname) + updatedCoachList.get(0).CoachName
                        + "\n"+getString(R.string.coachage) + updatedCoachList.get(0).CoachAge
                        + "\n"+getString(R.string.coachgender) + updatedCoachList.get(0).CoachGender
                        + "\n"+getString(R.string.drpout) + DropOut
                        + "\n"+getString(R.string.eddate) + endDate);

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


        } else {
            Toast.makeText(this,R.string.selectAllFields , Toast.LENGTH_SHORT).show();
        }

    }

    private void resetForm() {
        btn_Submit.setText(R.string.preview);
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

        rg_DropOut.clearCheck();
        rb_Yes.setChecked(true);
        btn_DatePicker.setText(new Utility().GetCurrentDate().toString());
        btn_DatePicker.setPadding(8, 8, 8, 8);
    }


    private void populateVillages() {

        final List VillageName = new ArrayList();
        vid = "";
        villageName = "";
        if (!villageList.isEmpty()) {
            VillageName.add(new CustomGroup(getString(R.string.selectvillage)));
            for (int j = 0; j < villageList.size(); j++) {
                CustomGroup customGroup = new CustomGroup(villageList.get(j).getVillageName(), villageList.get(j).getVillageId());
                VillageName.add(customGroup);
            }
            ArrayAdapter villageAdapter = new ArrayAdapter(CoachRetentionForm.this, android.R.layout.simple_spinner_dropdown_item, VillageName);
            sp_Village.setAdapter(villageAdapter);
        }

        sp_Village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                btn_Submit.setText(R.string.preview);
                CustomGroup customGroup = (CustomGroup) VillageName.get(pos);
                vid = "";
                vid = customGroup.getId();
                villageName = customGroup.getName();

                // populate coaches by villageid
                // Populate Coach Spinner
                coachList.clear();
                coachList = AppDatabase.getDatabaseInstance(CoachRetentionForm.this).getCoachDao().getCoachByVillageID(vid);
                Collections.sort(coachList, new Comparator<Coach>() {
                    public int compare(Coach v1, Coach v2) {
                        return v1.getCoachName().compareTo(v2.getCoachName());
                    }
                });
                populateCoaches();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void populateCoaches() {
        final List CoachName = new ArrayList();
        if (!coachList.isEmpty()) {
            CoachName.add(new CustomGroup(getString(R.string.selectcoach)));
            for (int j = 0; j < coachList.size(); j++) {
                CustomGroup customGroup = new CustomGroup(coachList.get(j).getCoachName(), coachList.get(j).getCoachID());
                CoachName.add(customGroup);
            }
            ArrayAdapter coachAdapter = new ArrayAdapter(CoachRetentionForm.this, android.R.layout.simple_spinner_dropdown_item, CoachName);
            sp_SelectCoach.setAdapter(coachAdapter);
            selectedCoachID = "";
        }

        sp_SelectCoach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                btn_Submit.setText(R.string.preview);
                CustomGroup customGroup = (CustomGroup) CoachName.get(pos);
                selectedCoachID = customGroup.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @OnClick(R.id.btn_DatePicker)
    public void endDatePicker(View view) {
        btn_Submit.setText(R.string.preview);
        DialogFragment newFragment = new DatePickerFragmentOne();
        newFragment.show(getFragmentManager(), "DatePicker");
    }
}
