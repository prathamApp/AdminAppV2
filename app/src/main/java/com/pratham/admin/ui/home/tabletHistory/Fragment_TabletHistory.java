package com.pratham.admin.ui.home.tabletHistory;

import androidx.fragment.app.Fragment;

import android.widget.TextView;

import com.pratham.admin.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_tablet_history)
public class Fragment_TabletHistory extends Fragment {

    @ViewById(R.id.ll_label)
    TextView ll_label;

    public Fragment_TabletHistory() {
        // Required empty public constructor
    }

    @AfterViews
    public void init(){
        String label = requireArguments().getString("LABEL");
        ll_label.setText(label);
    }

    @Click(R.id.iv_backButton)
    public void backButton(){
        requireActivity().getSupportFragmentManager().popBackStack();
    }

}