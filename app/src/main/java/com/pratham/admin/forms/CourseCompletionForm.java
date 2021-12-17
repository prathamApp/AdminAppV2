package com.pratham.admin.forms;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pratham.admin.R;
import com.pratham.admin.adapters.CourseTopicRVDataAdapter;
import com.pratham.admin.database.AppDatabase;
import com.pratham.admin.interfaces.DashRVClickListener;
import com.pratham.admin.modalclasses.Community;
import com.pratham.admin.modalclasses.Completion;
import com.pratham.admin.modalclasses.CourseTopicItem;
import com.pratham.admin.modalclasses.Groups;
import com.pratham.admin.modalclasses.Village;
import com.pratham.admin.util.BaseActivity;
import com.pratham.admin.util.CustomGroup;
import com.pratham.admin.util.DatePickerFragmentOne;
import com.pratham.admin.util.DatePickerFragmentTwo;
import com.pratham.admin.util.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseCompletionForm extends BaseActivity implements DashRVClickListener/*, ConnectionReceiverListener */ {

    @BindView(R.id.sp_Village)
    Spinner sp_Village;
    @BindView(R.id.sp_Groups)
    Spinner sp_Groups;
    @BindView(R.id.rg_Event)
    RadioGroup rg_Event;
    @BindView(R.id.rb_Yes)
    RadioButton rb_Yes;
    @BindView(R.id.edt_ParentCount)
    EditText edt_ParentCount;
    @BindView(R.id.btn_DatePicker)
    Button btn_DatePicker;
    @BindView(R.id.btn_DatePickerTwo)
    Button btn_DatePickerTwo;
    @BindView(R.id.btn_Submit)
    Button btn_Submit;
    @BindView(R.id.tv_Course_Warning)
    TextView tv_Course_Warning;

    List<Village> villageList = new ArrayList<>();
    List<Groups> AllGroupsInDB;
    List<Community> CourseTopicsByGrp;
    List registeredGRPs;
    RecyclerView CourseTopicsRV;

    private List<CourseTopicItem> CourseTopicItemList = new ArrayList<>();
    CourseTopicRVDataAdapter DataAdapter;

    String selectedCourseIDs;
    String selectedTopicIDs;
    String uniqueCompletionID = "";
    String villageID;
    String groupId;

    private String villageName;
    private String groupName;
    private String selectedTopicNames;
    private String selectedCourseNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_completion_form);
        ButterKnife.bind(this);
        //getSupportActionBar().hide();

        uniqueCompletionID = UUID.randomUUID().toString();

        btn_DatePicker.setText(new Utility().GetCurrentDate().toString());
        btn_DatePicker.setPadding(8, 8, 8, 8);
        btn_DatePickerTwo.setText(new Utility().GetCurrentDate().toString());
        btn_DatePickerTwo.setPadding(8, 8, 8, 8);

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

        // Create the recyclerview.
        CourseTopicsRV = (RecyclerView) findViewById(R.id.card_view_recycler_list);
    }

    /* Initialise items in list. */
    private void initializeItemList(String groupId) {
        CourseTopicItemList.clear();
        CourseTopicsByGrp = AppDatabase.getDatabaseInstance(this).getCommunityDao().getCommunityByGroupID(groupId);

        // If No Courses Available
        if (sp_Village.getSelectedItemPosition() > 0 && sp_Groups.getSelectedItemPosition() > 0)
            if (CourseTopicsByGrp.size() == 0) {
                tv_Course_Warning.setVisibility(View.VISIBLE);
            } else {
                tv_Course_Warning.setVisibility(View.GONE);
            }

        for (int i = 0; i < CourseTopicsByGrp.size(); i++) {
            CourseTopicItemList.add(new CourseTopicItem(CourseTopicsByGrp.get(i).AddedCourseID, CourseTopicsByGrp.get(i).CourseAdded, CourseTopicsByGrp.get(i).AddedTopicsID, CourseTopicsByGrp.get(i).TopicAdded, false));
        }
        if (DataAdapter == null) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            // Set layout manager.
            CourseTopicsRV.setLayoutManager(gridLayoutManager);
            // Create recycler view data adapter with item list.
            DataAdapter = new CourseTopicRVDataAdapter(CourseTopicItemList);
            // Set data adapter.
            CourseTopicsRV.setAdapter(DataAdapter);
        } else {
            DataAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.btn_Submit)
    public void submitForm(View view) {
        // getting Selected Items
        selectedCourseIDs = "";
        selectedCourseNames = "";
        selectedTopicIDs = "";
        selectedTopicNames = "";
        for (int i = 0; i < CourseTopicRVDataAdapter.ItemList.size(); i++) {
            if (CourseTopicRVDataAdapter.ItemList.get(i).getSelected()) {
                selectedCourseIDs = selectedCourseIDs + "," + CourseTopicRVDataAdapter.ItemList.get(i).getCourseIDs();
                selectedCourseNames = selectedCourseNames + "," + CourseTopicRVDataAdapter.ItemList.get(i).getCourse();
                if (CourseTopicRVDataAdapter.ItemList.get(i).getTopicIDs().trim().equalsIgnoreCase("")) {
                    selectedTopicIDs = selectedTopicIDs + "," + "NotAvailable";
                    selectedTopicNames = selectedTopicNames + "," + "NotAvailable";
                } else {
                    selectedTopicIDs = selectedTopicIDs + "," + CourseTopicRVDataAdapter.ItemList.get(i).getTopicIDs();
                    selectedTopicNames = selectedTopicNames + "," + CourseTopicRVDataAdapter.ItemList.get(i).getTopic();
                }
            }
        }

        // Submit Button Validation
        if ((sp_Village.getSelectedItemPosition() > 0) && (sp_Groups.getSelectedItemPosition() > 0)
                && (selectedCourseIDs.toString().trim().length() > 0) && (selectedTopicIDs.toString().trim().length() > 0)) {


            selectedCourseIDs = selectedCourseIDs.replaceFirst(",", "");
            selectedCourseNames = selectedCourseNames.replaceFirst(",", "");
            selectedTopicIDs = selectedTopicIDs.replaceFirst(",", "");
            selectedTopicNames = selectedTopicNames.replaceFirst(",", "");

            // isEvent code on Submit Click
            int selectedId = rg_Event.getCheckedRadioButtonId();
            RadioButton selectedEvent = (RadioButton) findViewById(selectedId);
            String event = selectedEvent.getText().toString();
            int isEvent;
            if (event.equalsIgnoreCase(getString(R.string.yes)))
                isEvent = 1;
            else
                isEvent = 0;

            // DB Entry
            Completion compObj = new Completion();
            compObj.CompletionID = uniqueCompletionID;
            compObj.VillageID = villageID;
            compObj.GroupID = groupId;
            compObj.CourseCompleted = selectedCourseIDs;
            compObj.TopicCompleted = selectedTopicIDs;
            compObj.StartDate = btn_DatePicker.getText().toString();
            compObj.EndDate = btn_DatePickerTwo.getText().toString();
            compObj.Event = isEvent;
            if (edt_ParentCount.getText().toString().trim().length() == 0)
                compObj.PresentParents = 0;
            else
                compObj.PresentParents = Integer.parseInt(edt_ParentCount.getText().toString().trim());
            compObj.sentFlag = 0;

            if (btn_Submit.getText().toString().equalsIgnoreCase(getString(R.string.submit))) {
                AppDatabase.getDatabaseInstance(this).getCompletionDao().insertCompletion(Collections.singletonList(compObj));
                Toast.makeText(this, R.string.formSubmittedtoDB, Toast.LENGTH_SHORT).show();
                resetForm();

            } else {
                // Preview Dialog
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CourseCompletionForm.this, android.R.style.Theme_Material_Light_Dialog);
                dialogBuilder.setCancelable(false);
                dialogBuilder.setTitle(getString(R.string.formdatapreview));
                dialogBuilder.setMessage(getString(R.string.villagename) + villageName
                        + "\n"+getString(R.string.groupname) + groupName
                        + "\n"+getString(R.string.courses) + selectedCourseNames
                        + "\n"+getString(R.string.topics) + selectedTopicNames
                        + "\n"+getString(R.string.stdate) + btn_DatePicker.getText().toString()
                        + "\n"+getString(R.string.eddate) + btn_DatePickerTwo.getText().toString()
                        + "\n"+getString(R.string.event) + event
                        + "\n"+getString(R.string.parentpresent) + edt_ParentCount.getText().toString());

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
            Toast.makeText(this, R.string.fillAllFields, Toast.LENGTH_SHORT).show();
        }


    }

    private void resetForm() {
        btn_Submit.setText(R.string.preview);
        uniqueCompletionID = UUID.randomUUID().toString();
        btn_DatePicker.setText(new Utility().GetCurrentDate().toString());
        btn_DatePicker.setPadding(8, 8, 8, 8);
        btn_DatePickerTwo.setText(new Utility().GetCurrentDate().toString());
        btn_DatePickerTwo.setPadding(8, 8, 8, 8);
        edt_ParentCount.getText().clear();
        rg_Event.clearCheck();
        rb_Yes.setChecked(true);
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
    }

    @OnClick(R.id.btn_DatePicker)
    public void startDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragmentOne();
        newFragment.show(getFragmentManager(), "DatePicker");
    }

    @OnClick(R.id.btn_DatePickerTwo)
    public void endDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragmentTwo();
        newFragment.show(getFragmentManager(), "DatePicker");
    }

    private void populateVillages() {
        final List VillageName = new ArrayList();
        if (!villageList.isEmpty()) {
            VillageName.add(new CustomGroup(getString(R.string.selectvillage)));
            for (int j = 0; j < villageList.size(); j++) {
                CustomGroup customGroup = new CustomGroup(villageList.get(j).getVillageName(), villageList.get(j).getVillageId());
                VillageName.add(customGroup);
            }
            ArrayAdapter villageAdapter = new ArrayAdapter(CourseCompletionForm.this, android.R.layout.simple_spinner_dropdown_item, VillageName);
            sp_Village.setAdapter(villageAdapter);
        }

        sp_Village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                CustomGroup customGroup = (CustomGroup) VillageName.get(pos);
                villageID = customGroup.getId();
                villageName = customGroup.getName();

                // Populate Registered Groups Spinner
                populateRegisteredGroups(villageID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateRegisteredGroups(String villageID) {
        // todo get registered grps
        registeredGRPs = new ArrayList();
        registeredGRPs.add(new CustomGroup(getString(R.string.selectgroup)));
        if (AllGroupsInDB != null) {
            for (int i = 0; i < AllGroupsInDB.size(); i++) {
                if (AllGroupsInDB.get(i).getVillageId().equals(villageID))
                    registeredGRPs.add(new CustomGroup(AllGroupsInDB.get(i).getGroupName(), AllGroupsInDB.get(i).getGroupId()));
            }
        }

        ArrayAdapter grpAdapter = new ArrayAdapter(CourseCompletionForm.this, android.R.layout.simple_spinner_dropdown_item, registeredGRPs);
        sp_Groups.setAdapter(grpAdapter);
        sp_Groups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                CustomGroup customGroup = (CustomGroup) registeredGRPs.get(pos);
                groupId = customGroup.getId();
                groupName = customGroup.getName();

                // Populate Courses of grps
                // Recycler View
                initializeItemList(groupId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onClick(View view, int position) {
        CourseTopicItem item = CourseTopicItemList.get(position);
        String Course = item.getCourse();
        String Topic = item.getTopic();
        Toast.makeText(this, "" + Course + "\n" + Topic, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
