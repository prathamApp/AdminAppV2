package com.pratham.admin.ui.notification;

import android.view.View;

import com.pratham.admin.modalclasses.CRL;

public interface NotificationHolderListItemListener {

    public void tabHolderItemClicked(View v, CRL crl, int position);
    public void tabHolderDetails(CRL crl);
}
