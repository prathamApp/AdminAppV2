package com.pratham.admin.ui.blockLeader.home.AddNewTablet;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.Result;
import com.pratham.admin.R;
import com.pratham.admin.activities.Activity_QRScan;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.modalclasses.DeviseList;
import com.pratham.admin.modalclasses.Model_Donor;
import com.pratham.admin.modalclasses.Model_NewTablet;
import com.pratham.admin.modalclasses.Model_Vendor;
import com.pratham.admin.modalclasses.Model_YearOfPurchase;
import com.pratham.admin.modalclasses.ProgramsModal;
import com.pratham.admin.util.Utility;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_add_new_tablet)
public class AddNewTabletFragment extends Fragment implements ZXingScannerView.ResultHandler {

    @ViewById(R.id.barcode_frame)
    FrameLayout barcode_frame;

    @ViewById(R.id.spinner_vendor)
    Spinner spinner_vendor;

    @ViewById(R.id.spinner_donor)
    Spinner spinner_donor;

    @ViewById(R.id.spinner_yearOfPurchase)
    Spinner spinner_yearOfpurchase;

/*    @ViewById(R.id.spinner_programm)
    Spinner spinner_program;*/

    @ViewById(R.id.et_brand)
    EditText et_brand;

    @ViewById(R.id.et_model)
    EditText et_model;

    @ViewById(R.id.tv_serialNo)
    TextView tv_serialNo;

    @ViewById(R.id.lv_newTabs)
    ListView lv_newTabs;

    @ViewById(R.id.rl_spinnerParent)
    RelativeLayout rl_spinnerParent;

    @ViewById(R.id.btn_sendTablets)
    Button btn_sendTablets;

    public ZXingScannerView mScannerView;

    List<Model_NewTablet> newTabletList = new ArrayList<>();
    ArrayList<String> scannedTabList = new ArrayList<>();

    ArrayList<String> spnrDonorList = new ArrayList<>();
    ArrayList<String> spnrVendorList = new ArrayList<>();
    ArrayList<String> spnrYopList = new ArrayList<>();
    ArrayList<String> spnrProgramList = new ArrayList<>();
    ArrayList<ProgramsModal> programsList;

    public static String selectedProgramID = null;
    public static String selectedProgramName = null;

    Model_NewTablet modelNewTablet;

    public AddNewTabletFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {
        btn_sendTablets.setVisibility(View.GONE);
        spnrDonorList = requireArguments().getStringArrayList("Donor_List");
        spnrVendorList = requireArguments().getStringArrayList("Vendor_List");
        spnrYopList = requireArguments().getStringArrayList("YOP_List");
        //programsList = requireArguments().getParcelableArrayList("Program_List");

        showDonors(spnrDonorList);
        showVendors(spnrVendorList);
        showYearOfPurchase(spnrYopList);
        //showPrograms(programsList);
        initCamera();
    }

    @Click(R.id.iv_refresh)
    public void reset() {
        mScannerView.stopCamera();
        mScannerView.startCamera();
        mScannerView.resumeCameraPreview(AddNewTabletFragment.this);
/*        spinner_yearOfpurchase.setSelection(0);
        spinner_donor.setSelection(0);
        //spinner_program.setSelection(0);
        spinner_vendor.setSelection(0);
        et_brand.setText("");
        et_model.setText("");
        //tv_serialNo.setText("Serial No.");
        newTabletList.clear();
        scannedTabList.clear();*/
    }

    @Click(R.id.btn_addToInventory)
    public void addToInventory() {
        Log.e("fffffffffffff  :  ", String.valueOf(scannedTabList.size()));
        if (scannedTabList.size() == 0) {
            Toast.makeText(getActivity(), "Scan Tablet First!", Toast.LENGTH_SHORT).show();
            btn_sendTablets.setVisibility(View.GONE);
        } else {
            rl_spinnerParent.setVisibility(View.VISIBLE);
            btn_sendTablets.setVisibility(View.VISIBLE);
        }

/*        addTabToList();

        String val=null;
        boolean found = false;
        for (Model_NewTablet n : newTabletList) {
            val = modelNewTablet.getSerialNo();
            if (n.getSerialNo().equalsIgnoreCase(modelNewTablet.getSerialNo())) {
                found = true;
                break;
            }
        }

        if(found)
            Toast.makeText(getActivity(), "Tablet Already Scanned!", Toast.LENGTH_SHORT).show();
        else {
            newTabletList.add(modelNewTablet);
            scannedTabList.add(modelNewTablet.getSerialNo());
            Log.e("sssss : ", val+" is not found.");
        }



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                scannedTabList);

        lv_newTabs.setAdapter(arrayAdapter);
        mScannerView.stopCamera();
        mScannerView.startCamera();
        mScannerView.resumeCameraPreview(AddNewTabletFragment.this);*/
    }

    @Click(R.id.btn_sendTablets)
    public void sendTablet() {
        submitTablets();
/*        mScannerView.stopCamera();
        mScannerView.startCamera();
        mScannerView.resumeCameraPreview(AddNewTabletFragment.this);*/
/*
        spinner_yearOfpurchase.setSelection(0);
        spinner_donor.setSelection(0);
        spinner_vendor.setSelection(0);
        et_brand.setText("");
        et_model.setText("");
        newTabletList.clear();
        scannedTabList.clear();

        rl_spinnerParent.setVisibility(View.GONE);
*/


/*        if(newTabletList.size()==0) {
            addTabToList();
        }
        if(newTabletList.size()>0) {
            Gson gson = new Gson();
            Type tabList = new TypeToken<ArrayList<Model_NewTablet>>() {
            }.getType();
            String newTab = gson.toJson(newTabletList, tabList);
            Log.e("Tabs : ", newTab);
            reset();
        } else {
            Toast.makeText(getActivity(), "Scan Tablet First.", Toast.LENGTH_SHORT).show();
        }*/
    }

    public void submitTablets() {
        if (spinner_donor.getSelectedItemPosition() != 0 && spinner_vendor.getSelectedItemPosition() != 0 &&
                spinner_yearOfpurchase.getSelectedItemPosition() != 0) {
            if (!et_brand.getText().toString().isEmpty() && !et_model.getText().toString().isEmpty()) {
                for (int i = 0; i < scannedTabList.size(); i++) {
                    modelNewTablet = new Model_NewTablet();
                    modelNewTablet.setUserId(FastSave.getInstance().getString("CRLid", "no_crl"));
                    modelNewTablet.setBrand(et_brand.getText().toString());
                    modelNewTablet.setModel(et_model.getText().toString());
                    modelNewTablet.setSerialNo(scannedTabList.get(i).toString());
                    modelNewTablet.setDonor(spinner_donor.getSelectedItem().toString());
                    modelNewTablet.setVendor(spinner_vendor.getSelectedItem().toString());
                    modelNewTablet.setYop(spinner_yearOfpurchase.getSelectedItem().toString());
                    modelNewTablet.setStatus("Working");
                    modelNewTablet.setProgramId("17");
                    //modelNewTablet.setProgramName(selectedProgramName);
                    modelNewTablet.setAddedOn(new Utility().GetCurrentDateTime(false));
                    modelNewTablet.setDeviceId("");
                    modelNewTablet.setPrathamId("");
                    modelNewTablet.setQrId("");
                    modelNewTablet.setWiFiMacAddress("");
                    newTabletList.add(modelNewTablet);
                }
/*                String val = null;
                boolean found = false;
                for (Model_NewTablet n : newTabletList) {
                    val = modelNewTablet.getSerialNo();
                    if (n.getSerialNo().equalsIgnoreCase(modelNewTablet.getSerialNo())) {
                        found = true;
                        break;
                    }
                }

                if (found)
                    Toast.makeText(getActivity(), "Tablet Already Scanned!", Toast.LENGTH_SHORT).show();
                else {
                    newTabletList.add(modelNewTablet);
                    scannedTabList.add(modelNewTablet.getSerialNo());
                    Log.e("sssss : ", val + " is not found.");
                }*/
            } else {
                Toast.makeText(getActivity(), "Enter All Tablet Details.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Select All Dropdown Value.", Toast.LENGTH_SHORT).show();
        }
        if (scannedTabList.size() > 0) {
            Gson gson = new Gson();
            Type tabList = new TypeToken<ArrayList<Model_NewTablet>>() {
            }.getType();
            String newTab = gson.toJson(newTabletList, tabList);
            Log.e("Tabs : ", newTab);
        } else {
            Toast.makeText(getActivity(), "Scan Tablet First.", Toast.LENGTH_SHORT).show();
        }
    }

    public void initCamera() {
        mScannerView = new ZXingScannerView(getActivity());
        mScannerView.setResultHandler(AddNewTabletFragment.this);
        barcode_frame.addView((mScannerView));
        mScannerView.startCamera();
        //    mScannerView.resumeCameraPreview(Activity_QRScan.this);
    }

    @Override
    public void handleResult(Result rawResult) {
        String result = rawResult.getText();
        mScannerView.stopCamera();

        if (scannedTabList.size() > 0) {
            if (scannedTabList.contains(result)) {
                Toast.makeText(getActivity(), "Tablet Already Scanned!", Toast.LENGTH_SHORT).show();
            } else {
                scannedTabList.add(result);
            }
        } else {
            scannedTabList.add(result);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                scannedTabList);

        lv_newTabs.setAdapter(arrayAdapter);
        mScannerView.stopCamera();
        mScannerView.startCamera();
        mScannerView.resumeCameraPreview(AddNewTabletFragment.this);
    }

    private void showYearOfPurchase(List<String> yearOfPurchasesList) {
        ArrayAdapter yopSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, yearOfPurchasesList);
        spinner_yearOfpurchase.setAdapter(yopSpnrAdapter);
    }

    private void showDonors(List<String> donorList) {
        ArrayAdapter donorSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, donorList);
        spinner_donor.setAdapter(donorSpnrAdapter);
    }

    private void showVendors(List<String> vendorList) {
        ArrayAdapter vendorSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, vendorList);
        spinner_vendor.setAdapter(vendorSpnrAdapter);
    }

    /*private void showPrograms(List<ProgramsModal> programsModalList) {
        for (ProgramsModal mp : programsList) {
            spnrProgramList.add(mp.getProgramName());
        }
        ArrayAdapter programAdapter = new ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, spnrProgramList);
        spinner_program.setAdapter(programAdapter);

        spinner_program.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProgramID = String.valueOf(programsModalList.get(position).getProgramId());
                selectedProgramName = programsModalList.get(position).getProgramName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }*/
}