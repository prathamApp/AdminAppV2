package com.pratham.admin.ui.blockLeader.home.AddNewTablet;

import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import com.androidnetworking.error.ANError;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.Result;
import com.pratham.admin.ApplicationController;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.Model_BrandModel;
import com.pratham.admin.modalclasses.Model_NewTablet;
import com.pratham.admin.modalclasses.Model_Vendor;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.Utility;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.pratham.admin.util.APIs.addNewTabAPI;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_add_new_tablet)
public class AddNewTabletFragment extends Fragment implements ZXingScannerView.ResultHandler, NetworkCallListener {

    @ViewById(R.id.barcode_frame)
    FrameLayout barcode_frame;

    @ViewById(R.id.spinner_vendor)
    Spinner spinner_vendor;

    @ViewById(R.id.spinner_donor)
    Spinner spinner_donor;

    @ViewById(R.id.spinner_yearOfPurchase)
    Spinner spinner_yearOfpurchase;

    @ViewById(R.id.spinner_brand)
    Spinner spinner_brand;

    @ViewById(R.id.spinner_model)
    Spinner spinner_model;

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

    @ViewById(R.id.btn_addToInventory)
    Button btn_addToInventory;

    @ViewById(R.id.et_donor)
    EditText et_donor;

    @ViewById(R.id.et_vendor)
    EditText et_vendor;

    @ViewById(R.id.tv_enterSerailNum)
    TextView tv_enterSerialNum;

    @ViewById(R.id.et_enterSerialNum)
    EditText et_enterSerialNum;

    public ZXingScannerView mScannerView;

    List<Model_NewTablet> newTabletList = new ArrayList<>();
    ArrayList<String> scannedTabList = new ArrayList<>();

    ArrayList<String> spnrDonorList = new ArrayList<>();
    ArrayList<String> spnrVendorList = new ArrayList<>();
    ArrayList<String> spnrYopList = new ArrayList<>();
    ArrayList<String> spnrBrandList = new ArrayList<>();
    ArrayList<String> spnrModelList = new ArrayList<>();

    Model_NewTablet modelNewTablet;

    boolean otherDonor = false;
    boolean otherVendor = false;
    boolean otherBrand = false;
    boolean otherModel = false;

    Animation animation, animationin;

    JSONArray brandModelResponse;
    List<Model_BrandModel> brandModelsList = new ArrayList<>();

    public AddNewTabletFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {
        btn_sendTablets.setVisibility(View.GONE);
        spnrDonorList = requireArguments().getStringArrayList("Donor_List");
        spnrVendorList = requireArguments().getStringArrayList("Vendor_List");
        spnrYopList = requireArguments().getStringArrayList("YOP_List");

        brandModelApiCall();
        showDonors(spnrDonorList);
        showVendors(spnrVendorList);
        showYearOfPurchase(spnrYopList);

        initCamera();
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_right);
        animationin = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);
    }

    private void brandModelApiCall() {
        if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
            NetworkCalls.getNetworkCallsInstance(requireActivity()).getRequestNew(this, APIs.brandModelAPI, "Loading...", "brandModelApi", getActivity());
        } else {
            new AlertDialog.Builder(requireActivity()).setTitle("Warning").setMessage("No internet connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }

    }

    @Click(R.id.iv_refresh)
    public void reset() {
        mScannerView.stopCamera();
        mScannerView.startCamera();
        mScannerView.resumeCameraPreview(AddNewTabletFragment.this);
/*      spinner_yearOfpurchase.setSelection(0);
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
            Toast.makeText(getActivity(), "Add Tablet First!", Toast.LENGTH_SHORT).show();
            btn_sendTablets.setVisibility(View.GONE);
        } else {
            rl_spinnerParent.setVisibility(View.VISIBLE);
            btn_sendTablets.setVisibility(View.VISIBLE);
            btn_addToInventory.setVisibility(View.GONE);
        }
    }

    @Click(R.id.btn_sendTablets)
    public void sendTablet() {
        submitTablets();
    }

    @Click(R.id.iv_backButton)
    public void backButton() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Click(R.id.tv_enterSerailNum)
    public void enterSerialNumber() {

        mScannerView.stopCamera();
        tv_enterSerialNum.startAnimation(animation);
        tv_enterSerialNum.setVisibility(View.GONE);
        et_enterSerialNum.startAnimation(animationin);
        et_enterSerialNum.setVisibility(View.VISIBLE);
    }

    @Touch(R.id.et_enterSerialNum)
    public boolean touch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getRawX() >= (et_enterSerialNum.getRight() - et_enterSerialNum.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                // your action here
                String serialnum = et_enterSerialNum.getText().toString();
                if (serialnum.length() > 1) {
                    addTabToList(serialnum);
                    et_enterSerialNum.setText("");
                    et_enterSerialNum.startAnimation(animation);
                    et_enterSerialNum.setVisibility(View.GONE);
                    tv_enterSerialNum.startAnimation(animationin);
                    tv_enterSerialNum.setVisibility(View.VISIBLE);
                } else
                    Toast.makeText(getActivity(), "Please enter serial number.", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    public void submitTablets() {
        String donor, vendor, brand, model;
        if (spinner_donor.getSelectedItemPosition() != 0
                && spinner_vendor.getSelectedItemPosition() != 0
                && spinner_yearOfpurchase.getSelectedItemPosition() != 0
                && spinner_brand.getSelectedItemPosition() != 0
                && spinner_model.getSelectedItemPosition() != 0) {

            if (otherDonor) donor = et_donor.getText().toString();
            else donor = spinner_donor.getSelectedItem().toString();
            if (otherVendor) vendor = et_vendor.getText().toString();
            else vendor = spinner_vendor.getSelectedItem().toString();
            if (otherBrand) brand = et_brand.getText().toString();
            else brand = spinner_brand.getSelectedItem().toString();
            if (otherModel) model = et_model.getText().toString();
            else model = spinner_model.getSelectedItem().toString();

            for (int i = 0; i < scannedTabList.size(); i++) {
                modelNewTablet = new Model_NewTablet();
                modelNewTablet.setUserId(FastSave.getInstance().getString("CRLid", "no_crl"));
                modelNewTablet.setBrand(brand);
                modelNewTablet.setModel(model);
                modelNewTablet.setSerialNo(scannedTabList.get(i).toString());
                modelNewTablet.setDonor(donor);
                modelNewTablet.setVendor(vendor);
                modelNewTablet.setYop(spinner_yearOfpurchase.getSelectedItem().toString());
                modelNewTablet.setStatus("Working");
                modelNewTablet.setProgramId("17");
                modelNewTablet.setAddedOn(new Utility().GetCurrentDateTime(false));
                modelNewTablet.setDeviceId("");
                modelNewTablet.setPrathamId("");
                modelNewTablet.setQrId("");
                modelNewTablet.setWiFiMacAddress("");
                newTabletList.add(modelNewTablet);
            }
            if (newTabletList.size() > 0) {
                Gson gson = new Gson();
                Type tabList = new TypeToken<ArrayList<Model_NewTablet>>() {
                }.getType();
                String newTab = gson.toJson(newTabletList, tabList);
                Log.e("Tabs : ", newTab);
                if (ApplicationController.wiseF.isDeviceConnectedToMobileOrWifiNetwork()) {
                    NetworkCalls.getNetworkCallsInstance(getActivity()).postRequest(this, addNewTabAPI, "UPLOADING ... ", newTab, "AddNewTablets");
                }
            } else {
                Toast.makeText(getActivity(), "Scan Tablet First.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Select All Dropdown Value.", Toast.LENGTH_SHORT).show();
        }
    }

    public void initCamera() {
        mScannerView = new ZXingScannerView(getActivity());
        mScannerView.setResultHandler(AddNewTabletFragment.this);
        barcode_frame.addView((mScannerView));
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String result = rawResult.getText();
        mScannerView.stopCamera();

        addTabToList(result);
        mScannerView.stopCamera();
        mScannerView.startCamera();
        mScannerView.resumeCameraPreview(AddNewTabletFragment.this);
    }

    private void addTabToList(String result) {
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
    }

    private void showYearOfPurchase(List<String> yearOfPurchasesList) {
        ArrayAdapter yopSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, yearOfPurchasesList);
        spinner_yearOfpurchase.setAdapter(yopSpnrAdapter);
    }

    private void showDonors(List<String> donorList) {
        donorList.add("Add New Donor");
        ArrayAdapter donorSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, donorList);
        spinner_donor.setAdapter(donorSpnrAdapter);

        spinner_donor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (donorList.get(position).equalsIgnoreCase("Add New Donor")) {
                    spinner_donor.setVisibility(View.GONE);
                    et_donor.setVisibility(View.VISIBLE);
                    et_donor.requestFocus();
                    otherDonor = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void showVendors(List<String> vendorList) {
        vendorList.add("Add New Vendor");
        ArrayAdapter vendorSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, vendorList);
        spinner_vendor.setAdapter(vendorSpnrAdapter);

        spinner_vendor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (vendorList.get(position).equalsIgnoreCase("Add New Vendor")) {
                    spinner_vendor.setVisibility(View.GONE);
                    et_vendor.setVisibility(View.VISIBLE);
                    et_vendor.requestFocus();
                    otherVendor = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void showBrandModel(ArrayList<Model_BrandModel> brandList) {
        spnrBrandList.clear();
        spnrModelList.clear();
        spnrBrandList.add("Brand");
        spnrModelList.add("Model");

        for (Model_BrandModel brandModel : brandList) {
            spnrBrandList.add(brandModel.getBrand());
            spnrModelList.add(brandModel.getModel());
        }
        spnrBrandList.add("Add New Brand");
        spnrModelList.add("Add New Model");

        Set<String> brandSet = new LinkedHashSet<>();
        Set<String> modelSet = new LinkedHashSet<>();
        brandSet.addAll(spnrBrandList);
        modelSet.addAll(spnrModelList);
        spnrBrandList.clear();
        spnrModelList.clear();
        spnrBrandList.addAll(brandSet);
        spnrModelList.addAll(modelSet);

        ArrayAdapter brandSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, spnrBrandList);
        spinner_brand.setAdapter(brandSpnrAdapter);

        ArrayAdapter modelSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, spnrModelList);
        spinner_model.setAdapter(modelSpnrAdapter);


        spinner_brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spnrBrandList.get(position).equalsIgnoreCase("Add New Brand")) {
                    spinner_brand.setVisibility(View.GONE);
                    et_brand.setVisibility(View.VISIBLE);
                    et_brand.requestFocus();
                    otherBrand = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spnrModelList.get(position).equalsIgnoreCase("Add New Model")) {
                    spinner_model.setVisibility(View.GONE);
                    et_model.setVisibility(View.VISIBLE);
                    et_model.requestFocus();
                    otherModel = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void showModels(ArrayList<String> modelList) {
        modelList.add("Add New Model");
        ArrayAdapter modelSpnrAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, modelList);
        spinner_model.setAdapter(modelSpnrAdapter);

        spinner_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (modelList.get(position).equalsIgnoreCase("Add New Model")) {
                    spinner_model.setVisibility(View.GONE);
                    et_model.setVisibility(View.VISIBLE);
                    et_model.requestFocus();
                    otherModel = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    @Override
    public void onResponse(String response, String header) {
        Gson gson = new Gson();
        if (header.equalsIgnoreCase("AddNewTablets")) {
            Toast.makeText(getActivity(), "Tablet added successfully...", Toast.LENGTH_SHORT).show();
            clearFields();
            btn_sendTablets.setVisibility(View.GONE);
            btn_addToInventory.setVisibility(View.VISIBLE);
        } else if (header.equalsIgnoreCase("brandModelApi")) {
            Utility.dismissLoadingDialog();
            try {
                brandModelResponse = new JSONArray(response);
                if (brandModelResponse.length() > 0) {
                    Type venList = new TypeToken<ArrayList<Model_BrandModel>>() {
                    }.getType();
                    brandModelsList = gson.fromJson(response.toString(), venList);
                    Log.e("BM : ", String.valueOf(brandModelsList.size()));
                    showBrandModel((ArrayList<Model_BrandModel>) brandModelsList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equalsIgnoreCase("AddNewTablets")) {
            Log.e("New Tab ErrorMessage : ", Objects.requireNonNull(anError.getMessage()));
            Log.e("New Tab ErrorDetail : ", Objects.requireNonNull(anError.getErrorDetail()));
            Toast.makeText(getActivity(), "Failed to add Tablet!", Toast.LENGTH_SHORT).show();
        } else if (header.equalsIgnoreCase("brandModelApi")) {
            Log.e("New Tab ErrorMessage : ", Objects.requireNonNull(anError.getMessage()));
            Log.e("New Tab ErrorDetail : ", Objects.requireNonNull(anError.getErrorDetail()));
            Toast.makeText(getActivity(), "Brand and Model not Found!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearFields() {
        spinner_yearOfpurchase.setSelection(0);
        spinner_donor.setSelection(0);
        spinner_vendor.setSelection(0);
        spinner_brand.setSelection(0);
        spinner_model.setSelection(0);
        et_brand.setText("");
        et_model.setText("");
        et_donor.setText("");
        et_vendor.setText("");
        newTabletList.clear();
        scannedTabList.clear();

        spinner_donor.setVisibility(View.VISIBLE);
        spinner_vendor.setVisibility(View.VISIBLE);
        spinner_brand.setVisibility(View.VISIBLE);
        spinner_model.setVisibility(View.VISIBLE);
        et_donor.setVisibility(View.GONE);
        rl_spinnerParent.setVisibility(View.GONE);
    }
}