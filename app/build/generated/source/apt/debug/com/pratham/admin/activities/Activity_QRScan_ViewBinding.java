// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Activity_QRScan_ViewBinding implements Unbinder {
  private Activity_QRScan target;

  private View view7f0a0238;

  private View view7f0a0239;

  @UiThread
  public Activity_QRScan_ViewBinding(Activity_QRScan target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Activity_QRScan_ViewBinding(final Activity_QRScan target, View source) {
    this.target = target;

    View view;
    target.qr_frame = Utils.findRequiredViewAsType(source, R.id.qr_frame, "field 'qr_frame'", FrameLayout.class);
    target.programInfoLayout = Utils.findRequiredViewAsType(source, R.id.programInfo, "field 'programInfoLayout'", ConstraintLayout.class);
    target.selProg = Utils.findRequiredViewAsType(source, R.id.tv_selProg, "field 'selProg'", TextView.class);
    target.selState = Utils.findRequiredViewAsType(source, R.id.tv_selState, "field 'selState'", TextView.class);
    target.selVillage = Utils.findRequiredViewAsType(source, R.id.tv_selVillage, "field 'selVillage'", TextView.class);
    target.qr_spinner_crl = Utils.findRequiredViewAsType(source, R.id.qr_spinner_crl, "field 'qr_spinner_crl'", TextView.class);
    target.txt_count = Utils.findRequiredViewAsType(source, R.id.txt_count_village, "field 'txt_count'", TextView.class);
    target.qr_pratham_id = Utils.findRequiredViewAsType(source, R.id.qr_pratham_id, "field 'qr_pratham_id'", EditText.class);
    target.qr_serialNo = Utils.findRequiredViewAsType(source, R.id.qr_serialNo, "field 'qr_serialNo'", EditText.class);
    target.successMessage = Utils.findRequiredViewAsType(source, R.id.successMessage, "field 'successMessage'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.qr_btn_reset, "method 'resetCamera'");
    view7f0a0238 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.resetCamera();
      }
    });
    view = Utils.findRequiredView(source, R.id.qr_btn_save, "method 'saveTabTrack'");
    view7f0a0239 = view;
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
    Activity_QRScan target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.qr_frame = null;
    target.programInfoLayout = null;
    target.selProg = null;
    target.selState = null;
    target.selVillage = null;
    target.qr_spinner_crl = null;
    target.txt_count = null;
    target.qr_pratham_id = null;
    target.qr_serialNo = null;
    target.successMessage = null;

    view7f0a0238.setOnClickListener(null);
    view7f0a0238 = null;
    view7f0a0239.setOnClickListener(null);
    view7f0a0239 = null;
  }
}
