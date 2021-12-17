package com.pratham.admin.ui.blockLeader.tabHolders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;
import com.pratham.admin.R;
import com.pratham.admin.custom.shared_preference.FastSave;
import com.pratham.admin.database.AppDatabase;
import com.pratham.admin.modalclasses.CRL;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_tab_holder)
public class TabHolderFragment extends Fragment implements TabHolderListItemListener {

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

    private List<CRL> crlList = new ArrayList<>();
    private TabHoldersAdapter tabHoldersAdapter;

    //BlurPopup Sms Dialog
    private BlurPopupWindow writeSmsDialog;
    EditText et_writeSms;

    public TabHolderFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {

        prepareData();
        tabHoldersAdapter = new TabHoldersAdapter(getActivity(), crlList, TabHolderFragment.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_tabHolder.setLayoutManager(mLayoutManager);
        rv_tabHolder.setItemAnimator(new DefaultItemAnimator());
        rv_tabHolder.setAdapter(tabHoldersAdapter);
        tabHoldersAdapter.notifyDataSetChanged();

        if (FastSave.getInstance().getString("roleId", "").equalsIgnoreCase("1")) {
            ll_spinnerOne.setVisibility(View.VISIBLE);
            ll_spinnerTwo.setVisibility(View.VISIBLE);
        } else {
            ll_spinnerOne.setVisibility(View.GONE);
            ll_spinnerTwo.setVisibility(View.VISIBLE);
        }


    }

    private void prepareData() {
        crlList = AppDatabase.getDatabaseInstance(getActivity()).getCRLdao().getCRLsByReportingPerson(FastSave.getInstance().getString("CRLid", ""));
        Log.e("CRL List : ", String.valueOf(crlList.size()));

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
                        } catch (Exception e){
                            Log.e("error : ",e.getMessage());
                            Log.e("error 1: ",e.getLocalizedMessage());
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
}