package com.pratham.admin.ui.home.replaceTablet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import com.pratham.admin.util.PA_Constants;
import com.pratham.admin.util.Utility;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.pratham.admin.util.APIs.replaceTabletTAPI;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_replace_form)
public class ReplaceFormFragment extends Fragment implements NetworkCallListener, CompoundButton.OnCheckedChangeListener {

    String tabletSerialId, tabletDeviceId;
    String screenDamage = "", bodyDamage = "", microphoneDamage = "", cameraDamage = "", speakerDamage = "", batteryDamage = "", unableToOn = "", otherDamage = "";

    @ViewById(R.id.cb_screenDamage)
    CheckBox cb_screenDamage;

    @ViewById(R.id.cb_bodyDamage)
    CheckBox cb_bodyDamage;

    @ViewById(R.id.cb_microphoneDamage)
    CheckBox cb_microphoneDamage;

    @ViewById(R.id.cb_cameraDamage)
    CheckBox cb_cameraDamage;

    @ViewById(R.id.cb_speakerDamage)
    CheckBox cb_speakerDamage;

    @ViewById(R.id.cb_batteryDamage)
    CheckBox cb_batteryDamage;

    @ViewById(R.id.cb_unableToOn)
    CheckBox cb_unableToOn;

/*    @ViewById(R.id.cb_otherDamage)
    CheckBox cb_otherDamage;*/

    @ViewById(R.id.contactNumber)
    EditText et_contactNumber;

    @ViewById(R.id.otherDetail)
    EditText et_otherDetail;

    @ViewById(R.id.btn_replace_tab)
    ExtendedFloatingActionButton btn_replace_tab;

    int globalCheckCount = 0;

    Animation animation;

    public ReplaceFormFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ClickableViewAccessibility")
    @AfterViews
    public void init() {

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.blink);
        tabletSerialId = requireArguments().getString(PA_Constants.TABLET_SERIAL_ID);
        tabletDeviceId = requireArguments().getString(PA_Constants.TABLET_DEVICE_ID);
        cb_screenDamage.setOnCheckedChangeListener(this);
        cb_bodyDamage.setOnCheckedChangeListener(this);
        cb_microphoneDamage.setOnCheckedChangeListener(this);
        cb_cameraDamage.setOnCheckedChangeListener(this);
        cb_speakerDamage.setOnCheckedChangeListener(this);
        cb_batteryDamage.setOnCheckedChangeListener(this);
        cb_unableToOn.setOnCheckedChangeListener(this);
        btn_replace_tab.setEnabled(false);

        et_contactNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enableReplaceButton(s.toString(),globalCheckCount);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());

        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());


        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null)
                    et_otherDetail.setText(matches.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        et_otherDetail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if(event.getRawX() >= (et_otherDetail.getRight() - et_otherDetail.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                            mSpeechRecognizer.stopListening();
                            et_otherDetail.setHint("Press & Hold Mic Icon To Record");
                        }
                        break;

                    case MotionEvent.ACTION_DOWN:
                        if(event.getRawX() >= (et_otherDetail.getRight() - et_otherDetail.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                            mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                            et_otherDetail.setText("");
                            et_otherDetail.setHint("Listening...");
                        }
                        break;
                }
                return false;
            }
        });
    }

    @Click(R.id.btn_replace_tab)
    public void replaceTablet() {
        if (!et_contactNumber.getText().toString().isEmpty()) {
            if (et_contactNumber.getText().toString().length() == 10) {
                getCheckBoxValues();
                if (screenDamage.equalsIgnoreCase("No") && bodyDamage.equalsIgnoreCase("No") &&
                        microphoneDamage.equalsIgnoreCase("No") && cameraDamage.equalsIgnoreCase("No") &&
                        speakerDamage.equalsIgnoreCase("No") && batteryDamage.equalsIgnoreCase("No") &&
                        unableToOn.equalsIgnoreCase("No")) {
                    Toast.makeText(getActivity(), "Select Appropriate Damage.", Toast.LENGTH_SHORT).show();
                } else {
                    Utility.showLoadingDialog(getActivity(), "Sending Replace Request...");
                    try {
                        Model_ReplaceTab model_replaceTab = new Model_ReplaceTab(FastSave.getInstance().getString("CRLid",""),
                                tabletSerialId,
                                tabletDeviceId,
                                FastSave.getInstance().getString("reportingPersonId",""),
                                new Utility().GetCurrentDateNew(),
                                et_contactNumber.getText().toString(),
                                screenDamage,
                                bodyDamage,
                                microphoneDamage,
                                cameraDamage,
                                speakerDamage,
                                batteryDamage,
                                unableToOn,
                                otherDamage,
                                et_otherDetail.getText().toString());

                        Gson gson = new Gson();
                        String json = gson.toJson(model_replaceTab);
                        Log.e("json : ", json);

                NetworkCalls.getNetworkCallsInstance(getActivity()).postRequest(this, replaceTabletTAPI, "UPLOADING ... ", json, "ReplaceTablet");
                btn_replace_tab.setEnabled(false);
//            NetworkCalls.getNetworkCallsInstance(getActivity()).postRequestAsJSONObject(this, reportLostAPI, "UPLOADING ... ", object, "ReportLostTablet");
                    } catch (Exception e) {
                        Utility.dismissLoadingDialog();
                        e.printStackTrace();
                    }
                }
            } else {
                Toast.makeText(getActivity(), "Invalid Contact Number!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Contact Number is Mandatory!", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.iv_backButton)
    public void backButton(){
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private void getCheckBoxValues() {
        if (cb_screenDamage.isChecked()) screenDamage = "Yes";
        else screenDamage = "No";

        if (cb_bodyDamage.isChecked()) bodyDamage = "Yes";
        else bodyDamage = "No";

        if (cb_microphoneDamage.isChecked()) microphoneDamage = "Yes";
        else microphoneDamage = "No";

        if (cb_cameraDamage.isChecked()) cameraDamage = "Yes";
        else cameraDamage = "No";

        if (cb_speakerDamage.isChecked()) speakerDamage = "Yes";
        else speakerDamage = "No";

        if (cb_batteryDamage.isChecked()) batteryDamage = "Yes";
        else batteryDamage = "No";

        if (cb_unableToOn.isChecked()) unableToOn = "Yes";
        else unableToOn = "No";

/*        if (cb_otherDamage.isChecked()) otherDamage = "Yes";
        else otherDamage = "No";*/
    }

    @Override
    public void onResponse(String response, String header) {
        if (header.equals("ReplaceTablet")) {
            Utility.dismissLoadingDialog();
            Log.e("responseReplace", response);
            Gson gson = new Gson();
            API_Response apiResponse;
            Type json = new TypeToken<API_Response>() {
            }.getType();
            apiResponse = gson.fromJson(response, json);

            if (apiResponse.getErrorDesc().equalsIgnoreCase("ReplaceData Added Successfully")) {
                Utility.dismissLoadingDialog();
                Toast.makeText(getActivity(), R.string.replace_request_sent_success, Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStack();
            } else if (apiResponse.getErrorDesc().equalsIgnoreCase("CRL_ID Not Found")) {
                Utility.dismissLoadingDialog();
                Toast.makeText(getActivity(), R.string.crl_not_found, Toast.LENGTH_SHORT).show();
            } else if (apiResponse.getErrorDesc().equalsIgnoreCase("AssigneeID Not Found")) {
                Utility.dismissLoadingDialog();
                Toast.makeText(getActivity(), R.string.assignee_not_found, Toast.LENGTH_SHORT).show();
            } else if (apiResponse.getErrorDesc().equalsIgnoreCase("SerialID Not Found")) {
                Utility.dismissLoadingDialog();
                Toast.makeText(getActivity(), R.string.serialid_not_found, Toast.LENGTH_SHORT).show();
            } else {
                Utility.dismissLoadingDialog();
                Toast.makeText(getActivity(), R.string.request_failed, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onError(ANError anError, String header) {
        if (header.equals("ReplaceTablet")) {
            Utility.dismissLoadingDialog();
            Log.e("errorRT", String.valueOf(anError.getErrorCode()));
            Log.e("errorRT", anError.getErrorBody());
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()) {
            case R.id.cb_screenDamage:
            case R.id.cb_bodyDamage:
            case R.id.cb_cameraDamage:
            case R.id.cb_microphoneDamage:
            case R.id.cb_speakerDamage:
            case R.id.cb_batteryDamage:
            case R.id.cb_unableToOn:
                globalCheckCount = isChecked ? ++globalCheckCount : --globalCheckCount;
                break;
        }
        enableReplaceButton(et_contactNumber.getText().toString(),globalCheckCount);
    }

    @UiThread
    public void enableReplaceButton(String isMobNo, int checkCount){

        if(isMobNo.length()==10 && checkCount>0) {
            btn_replace_tab.startAnimation(animation);
            btn_replace_tab.setEnabled(true);
        } else {
            btn_replace_tab.clearAnimation();
            btn_replace_tab.setEnabled(false);
        }
    }
}