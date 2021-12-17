package com.pratham.admin.ui.home;

import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;

import com.pratham.admin.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

@SuppressLint("NonConstantResourceId")
@EFragment(R.layout.fragment_notification)
public class NotificationFragment extends Fragment {

    public NotificationFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init(){

    }

}