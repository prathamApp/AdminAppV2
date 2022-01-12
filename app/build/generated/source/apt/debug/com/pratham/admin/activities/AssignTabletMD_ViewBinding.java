// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AssignTabletMD_ViewBinding implements Unbinder {
  private AssignTabletMD target;

  private View view7f0a022d;

  private View view7f0a022e;

  @UiThread
  public AssignTabletMD_ViewBinding(AssignTabletMD target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AssignTabletMD_ViewBinding(final AssignTabletMD target, View source) {
    this.target = target;

    View view;
    target.programSpinner = Utils.findRequiredViewAsType(source, R.id.program, "field 'programSpinner'", Spinner.class);
    target.roleCrlSpinner = Utils.findRequiredViewAsType(source, R.id.roleCrl, "field 'roleCrlSpinner'", Spinner.class);
    target.assignedCrlName = Utils.findRequiredViewAsType(source, R.id.assignedCrlName, "field 'assignedCrlName'", TextView.class);
    target.assignedCrlId = Utils.findRequiredViewAsType(source, R.id.assignedCrlId, "field 'assignedCrlId'", TextView.class);
    target.crlNameSpinner = Utils.findRequiredViewAsType(source, R.id.crlName, "field 'crlNameSpinner'", Spinner.class);
    target.qr_frame = Utils.findRequiredViewAsType(source, R.id.qr_frame, "field 'qr_frame'", FrameLayout.class);
    target.qr_pratham_id = Utils.findRequiredViewAsType(source, R.id.qr_pratham_id, "field 'qr_pratham_id'", EditText.class);
    target.qr_serialNo = Utils.findRequiredViewAsType(source, R.id.qr_serialNo, "field 'qr_serialNo'", EditText.class);
    target.txt_count = Utils.findRequiredViewAsType(source, R.id.txt_count_village, "field 'txt_count'", TextView.class);
    target.successMessage = Utils.findRequiredViewAsType(source, R.id.successMessage, "field 'successMessage'", LinearLayout.class);
    target.isDamaged = Utils.findRequiredViewAsType(source, R.id.isDamaged, "field 'isDamaged'", Spinner.class);
    target.damageType = Utils.findRequiredViewAsType(source, R.id.damageType, "field 'damageType'", Spinner.class);
    target.comments = Utils.findRequiredViewAsType(source, R.id.comments, "field 'comments'", EditText.class);
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
    AssignTabletMD target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.programSpinner = null;
    target.roleCrlSpinner = null;
    target.assignedCrlName = null;
    target.assignedCrlId = null;
    target.crlNameSpinner = null;
    target.qr_frame = null;
    target.qr_pratham_id = null;
    target.qr_serialNo = null;
    target.txt_count = null;
    target.successMessage = null;
    target.isDamaged = null;
    target.damageType = null;
    target.comments = null;

    view7f0a022d.setOnClickListener(null);
    view7f0a022d = null;
    view7f0a022e.setOnClickListener(null);
    view7f0a022e = null;
  }
}
