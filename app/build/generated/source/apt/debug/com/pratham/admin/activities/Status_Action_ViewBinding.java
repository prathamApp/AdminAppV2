// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Status_Action_ViewBinding implements Unbinder {
  private Status_Action target;

  private View view7f0a0081;

  private View view7f0a022d;

  private View view7f0a022e;

  @UiThread
  public Status_Action_ViewBinding(Status_Action target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Status_Action_ViewBinding(final Status_Action target, View source) {
    this.target = target;

    View view;
    target.qr_frame = Utils.findRequiredViewAsType(source, R.id.qr_frame, "field 'qr_frame'", FrameLayout.class);
    target.statusRadioGroup = Utils.findRequiredViewAsType(source, R.id.statusRadioGroup, "field 'statusRadioGroup'", RadioGroup.class);
    target.qr_spinner_crl = Utils.findRequiredViewAsType(source, R.id.qr_spinner_crl, "field 'qr_spinner_crl'", TextView.class);
    target.txt_count = Utils.findRequiredViewAsType(source, R.id.txt_count_village, "field 'txt_count'", TextView.class);
    target.qr_pratham_id = Utils.findRequiredViewAsType(source, R.id.qr_pratham_id, "field 'qr_pratham_id'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_DatePicker, "field 'btn_DatePicker' and method 'datePicker'");
    target.btn_DatePicker = Utils.castView(view, R.id.btn_DatePicker, "field 'btn_DatePicker'", Button.class);
    view7f0a0081 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.datePicker(p0);
      }
    });
    target.qr_serialNo = Utils.findRequiredViewAsType(source, R.id.qr_serialNo, "field 'qr_serialNo'", EditText.class);
    target.successMessage = Utils.findRequiredViewAsType(source, R.id.successMessage, "field 'successMessage'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.qr_btn_reset, "method 'resetCamera'");
    view7f0a022d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.resetCamera();
      }
    });
    view = Utils.findRequiredView(source, R.id.qr_btn_save, "method 'saveTabTrack'");
    view7f0a022e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.saveTabTrack();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Status_Action target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.qr_frame = null;
    target.statusRadioGroup = null;
    target.qr_spinner_crl = null;
    target.txt_count = null;
    target.qr_pratham_id = null;
    target.btn_DatePicker = null;
    target.qr_serialNo = null;
    target.successMessage = null;

    view7f0a0081.setOnClickListener(null);
    view7f0a0081 = null;
    view7f0a022d.setOnClickListener(null);
    view7f0a022d = null;
    view7f0a022e.setOnClickListener(null);
    view7f0a022e = null;
  }
}
