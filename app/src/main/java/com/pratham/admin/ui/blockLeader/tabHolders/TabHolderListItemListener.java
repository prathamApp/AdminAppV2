package com.pratham.admin.ui.blockLeader.tabHolders;

import android.view.View;

import com.pratham.admin.modalclasses.CRL;

public interface TabHolderListItemListener {
    public void sendSms(String mobileNumber);
    public void tabHolderItemClicked(View v, CRL crl, int position);
    public void tabHolderDetails(CRL crl);
}
