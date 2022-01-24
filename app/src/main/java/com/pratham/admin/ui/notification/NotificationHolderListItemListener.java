package com.pratham.admin.ui.notification;

import android.view.View;

import com.pratham.admin.modalclasses.CRL;
import com.pratham.admin.modalclasses.Model_Notification;

public interface NotificationHolderListItemListener {

    public void tabHolderItemClicked(View v, Model_Notification model_Notification, int position);
    public void tabHolderDetails(Model_Notification crl);
}
