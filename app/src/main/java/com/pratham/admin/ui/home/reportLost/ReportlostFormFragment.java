package com.pratham.admin.ui.home.reportLost;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pratham.admin.R;
import com.pratham.admin.async.NetworkCalls;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.interfaces.NetworkCallListener;
import com.pratham.admin.modalclasses.API_Response;
import com.pratham.admin.modalclasses.Model_ReplaceTab;
import com.pratham.admin.modalclasses.Model_ReportLost;
import com.pratham.admin.util.APIs;
import com.pratham.admin.util.PA_Constants;
import com.pratham.admin.util.Utility;
import com.pratham.prathamdigital.custom.permissions.KotlinPermissions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.pratham.admin.util.APIs.replaceTabletTAPI;
import static com.pratham.admin.util.APIs.reportLostAPI;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_reportlost_form)
public class ReportlostFormFragment extends Fragment implements NetworkCallListener {

    private static final int CAMERA_REQUEST = 1;

    String tabletSerialId, tabletDeviceId, tabletBrand;
    String lastSeenWith, personReporting, lastSeenDate, charger, cover, sdCard, additionalRemark, policeComplaint, incidentVerification, enqUndertaken, enqConcluded;

    @ViewById(R.id.et_contactNumber)
    EditText et_contactNumber;

    @ViewById(R.id.tv_lastSeenDate)
    TextView tv_lastSeenDate;

    @ViewById(R.id.tv_serialNo)
    TextView tv_serialNo;

    @ViewById(R.id.tv_brandModel)
    TextView tv_brandModel;

    @ViewById(R.id.et_additionalRemark)
    EditText et_additionalRemark;

    @ViewById(R.id.rg_lastSeen)
    RadioGroup rg_lastSeen;

    @ViewById(R.id.rg_reportingPerson)
    RadioGroup rg_reportingPerson;

    @ViewById(R.id.rg_charger)
    RadioGroup rg_charger;

    @ViewById(R.id.rg_cover)
    RadioGroup rg_cover;

    @ViewById(R.id.rg_sdCard)
    RadioGroup rg_sdCard;

    @ViewById(R.id.rg_policeComplaint)
    RadioGroup rg_policeComplaint;

    @ViewById(R.id.rg_incidentVerifiedAtOne)
    RadioGroup rg_incidentVerifiedAtOne;

    @ViewById(R.id.rg_incidentVerifiedAtTwo)
    RadioGroup rg_incidentVerifiedAtTwo;

    @ViewById(R.id.rg_enqUndertaken)
    RadioGroup rg_enqUndertaken;

    @ViewById(R.id.rg_enqConcluded)
    RadioGroup rg_enqConcluded;

    @ViewById(R.id.iv_backButton)
    ImageView iv_backButton;

    @ViewById(R.id.btn_reportLost)
    ExtendedFloatingActionButton btn_reportLost;

    @ViewById(R.id.iv_openCamera)
    ImageView iv_openCamera;

    RadioButton lastSeenRadioButton, reportingPersonRadioButton, chargerRadioButton, coverRadioButton, sdCardRadioButton;
    RadioButton policeComplaintRadioButton, incidentVerificationRadioButton, enqUndertakenRadioButton, enqCocludedRadioButton;

    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    Animation animation;

    File imageFileName;

    private String imgPath = "";

    boolean uploadFIR = false;
    boolean imageClicked=false;

    String fileName="";

    public ReportlostFormFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {
        //to avoid this error - "android.os.FileUriExposedException: file.jpg exposed beyond app through ClipData.Item.getUri()"
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);
        tabletSerialId = requireArguments().getString(PA_Constants.TABLET_SERIAL_ID);
        tabletDeviceId = requireArguments().getString(PA_Constants.TABLET_DEVICE_ID);
        tabletBrand = requireArguments().getString(PA_Constants.TABLET_BRAND);

        tv_serialNo.setText(tabletSerialId);
        tv_brandModel.setText(tabletBrand);
        btn_reportLost.setEnabled(false);

        //date picker
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                tv_lastSeenDate.setText(sdf.format(myCalendar.getTime()));
                enableReplaceButton(et_contactNumber.getText().toString(), tv_lastSeenDate.getText().toString());
            }

        };

        //radio group listeners
        rg_incidentVerifiedAtOne.setOnCheckedChangeListener(listener1);
        rg_incidentVerifiedAtTwo.setOnCheckedChangeListener(listener2);
        rg_policeComplaint.setOnCheckedChangeListener(rg_policeComplaintListener);

        et_contactNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enableReplaceButton(s.toString(), tv_lastSeenDate.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Click(R.id.tv_lastSeenDate)
    public void setLastSeenDate() {
        new DatePickerDialog(getActivity(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Click(R.id.iv_openCamera)
    public void openCamera() {
        //Open Camera to click photo
        KotlinPermissions.with(getActivity())
                .permissions(Manifest.permission.CAMERA)
                .onAccepted(permissionResult -> {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                })
                .ask();
    }

    //This method sets image uri in DCIM and returns image uri
    public Uri setImageUri() {
        // Store image in DCIM
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/",
                new Utility().GetCurrentDateNew() + "_" + tabletSerialId + ".jpg");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }


    //get the actual image path
    public String getImagePath() {
        return imgPath;
    }

    @Click(R.id.btn_reportLost)
    public void reportLost() {
        if (!et_contactNumber.getText().toString().isEmpty()) {
            if (et_contactNumber.getText().toString().length() == 10) {
                getRadioButtonValues();
                try{
                    fileName = imageFileName.getName();
                } catch (Exception e){
                    e.printStackTrace();
                }
                Utility.showLoadingDialog(getActivity());
                try {
                    Model_ReportLost model_reportLost = new Model_ReportLost(FastSave.getInstance().getString("CRLid", ""),
                            tabletSerialId,
                            tabletDeviceId,
                            new Utility().GetCurrentDateTime(false),
                            et_contactNumber.getText().toString(),
                            tv_lastSeenDate.getText().toString(),
                            tabletBrand,
                            lastSeenRadioButton.getText().toString(),
                            reportingPersonRadioButton.getText().toString(),
                            chargerRadioButton.getText().toString(),
                            coverRadioButton.getText().toString(),
                            sdCardRadioButton.getText().toString(),
                            et_additionalRemark.getText().toString(),
                            policeComplaintRadioButton.getText().toString(),
                            incidentVerificationRadioButton.getText().toString(),
                            enqUndertakenRadioButton.getText().toString(),
                            enqCocludedRadioButton.getText().toString(),
                            fileName);

                    Gson gson = new Gson();
                    String json = gson.toJson(model_reportLost);// converting model content to json
                    Log.e("json : ", json);

                    NetworkCalls.getNetworkCallsInstance(getActivity()).postRequest(this, reportLostAPI, "UPLOADING ... ", json, "ReportLostTablet");

                } catch (Exception e) {
                    Utility.dismissLoadingDialog();
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), "Invalid Contact Number!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Contact Number is Mandatory!", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.iv_backButton)
    public void backButton() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg_incidentVerifiedAtTwo.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception
                rg_incidentVerifiedAtTwo.clearCheck(); // clear the second RadioGroup!
                rg_incidentVerifiedAtTwo.setOnCheckedChangeListener(listener2); //reset the listener
                Log.e("XXX2", "do the work");
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg_incidentVerifiedAtOne.setOnCheckedChangeListener(null);
                rg_incidentVerifiedAtOne.clearCheck();
                rg_incidentVerifiedAtOne.setOnCheckedChangeListener(listener1);
                Log.e("XXX2", "do the work");
            }
        }
    };

    private final RadioGroup.OnCheckedChangeListener rg_policeComplaintListener = (group, checkedId) -> {

        switch (checkedId) {
            case R.id.rb_police_yes:
                uploadFIR = true;
                iv_openCamera.setVisibility(View.VISIBLE);
                break;

            case R.id.rb_police_no:
                uploadFIR = false;
                iv_openCamera.setVisibility(View.GONE);
                break;
        }
    };


    private void getRadioButtonValues() {
        // get selected radio button from radioGroup
        int selectedLastSeenId = rg_lastSeen.getCheckedRadioButtonId();
        int selectedReportingPerson = rg_reportingPerson.getCheckedRadioButtonId();
        int selectedCharger = rg_charger.getCheckedRadioButtonId();
        int selectedCover = rg_cover.getCheckedRadioButtonId();
        int selectedSdCard = rg_sdCard.getCheckedRadioButtonId();
        int selectedPoliceComplaint = rg_policeComplaint.getCheckedRadioButtonId();
        int selectedIncidentOne = rg_incidentVerifiedAtOne.getCheckedRadioButtonId();
        int selectedIncidentTwo = rg_incidentVerifiedAtTwo.getCheckedRadioButtonId();
        int selectedIncident = selectedIncidentOne == -1 ? selectedIncidentTwo : selectedIncidentOne;
        int selectedEnqUndertaken = rg_enqUndertaken.getCheckedRadioButtonId();
        int selectedEnqConcluded = rg_enqConcluded.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        lastSeenRadioButton = getView().findViewById(selectedLastSeenId);
        reportingPersonRadioButton = getView().findViewById(selectedReportingPerson);
        chargerRadioButton = getView().findViewById(selectedCharger);
        coverRadioButton = getView().findViewById(selectedCover);
        sdCardRadioButton = getView().findViewById(selectedSdCard);
        policeComplaintRadioButton = getView().findViewById(selectedPoliceComplaint);
        incidentVerificationRadioButton = getView().findViewById(selectedIncident);
//        incidentVerificationRadioButton = getView().findViewById(selectedIncidentOne);
        enqUndertakenRadioButton = getView().findViewById(selectedEnqUndertaken);
        enqCocludedRadioButton = getView().findViewById(selectedEnqConcluded);
    }

    @UiThread
    public void enableReplaceButton(String isMobNo, String lastSeenDate) {

        if (isMobNo.length() == 10 && lastSeenDate.contains("/")) {
            btn_reportLost.startAnimation(animation);
            btn_reportLost.setEnabled(true);
        } else {
            btn_reportLost.clearAnimation();
            btn_reportLost.setEnabled(false);
        }
    }

    @Override
    public void onResponse(String response, String header) {

        if (header.equals("ReportLostTablet")) {
            Log.e("responseReportLost : ", response);
            Gson gson = new Gson();
            API_Response apiResponse;
            Type json = new TypeToken<API_Response>() {
            }.getType();
            apiResponse = gson.fromJson(response, json);

            if (apiResponse.getErrorDesc().equalsIgnoreCase("ReportLost Added Successfully")) {
                if(imageClicked) {
                    NetworkCalls.getNetworkCallsInstance(requireActivity()).pushImageToInternet(this, APIs.pushImageToServer, imageFileName, "image_push");
                } else {
                    Utility.dismissLoadingDialog();
                    Toast.makeText(getActivity(), R.string.report_lost, Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack();
                }
            } else if (apiResponse.getErrorDesc().equalsIgnoreCase("CRL_ID Not Found")) {
                Utility.dismissLoadingDialog();
                Toast.makeText(getActivity(), R.string.crl_not_found, Toast.LENGTH_SHORT).show();
            } else if (apiResponse.getErrorDesc().equalsIgnoreCase("SerialID Not Found")) {
                Utility.dismissLoadingDialog();
                Toast.makeText(getActivity(), R.string.serialid_not_found, Toast.LENGTH_SHORT).show();
            } else {
                Utility.dismissLoadingDialog();
                Toast.makeText(getActivity(), R.string.request_failed, Toast.LENGTH_SHORT).show();
            }
        } else if (header.equalsIgnoreCase("image_push")) {
            Log.e("response : ", response);
            Utility.dismissLoadingDialog();
            Toast.makeText(getActivity(), R.string.report_lost, Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equals("ReportLostTablet")) {
            Utility.dismissLoadingDialog();
            Log.e("error", String.valueOf(anError.getErrorCode()));
            Log.e("error", anError.getErrorBody());
        } else if (header.equalsIgnoreCase("image_push")) {
            Log.e("response : ", anError.getMessage());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
/*            Toast.makeText(getActivity(), String.valueOf(data.getExtras().get("data")), Toast.LENGTH_SHORT).show();
            Bitmap photo = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            iv_openCamera.setImageBitmap(photo);
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(requireActivity(), photo);
            Log.e("path : ",tempUri.getPath());
            File finalFile = new File(getRealPathFromURI(tempUri));
            Log.e("path : ",finalFile.toString());*/
//            String url = APIs.pushImageToServer;
//            NetworkCalls.getNetworkCallsInstance(requireActivity()).pushImageToInternet(this,url, finalFile,"image_push");

            imageClicked=true;

            String selectedImagePath = getImagePath();
            Log.e("path : ", selectedImagePath);
            iv_openCamera.setImageBitmap(decodeFile(selectedImagePath));
            imageFileName = new File(getImagePath());
        }
    }
/*

    public Uri getImageUri(Context inContext, Bitmap inImage) {
*/
/*        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1000, 1000,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);*//*

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (requireActivity().getContentResolver() != null) {
            Cursor cursor = requireActivity().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }
*/

    //Used to decode the file from DCIM to set on imageview
    public Bitmap decodeFile(String path) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeFile(path, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }
}