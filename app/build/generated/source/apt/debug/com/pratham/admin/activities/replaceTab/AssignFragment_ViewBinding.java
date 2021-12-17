// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities.replaceTab;

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

public class AssignFragment_ViewBinding implements Unbinder {
  private AssignFragment target;

  private View view7f0a0221;

  private View view7f0a0222;

  @UiThread
  public AssignFragment_ViewBinding(final AssignFragment target, View source) {
    this.target = target;

    View view;
    target.qr_frame = Utils.findRequiredViewAsType(source, R.id.qr_frame, "field 'qr_frame'", FrameLayout.class);
    target.village = Utils.findRequiredViewAsType(source, R.id.village, "field 'village'", Spinner.class);
    target.txt_count = Utils.findRequiredViewAsType(source, R.id.txt_count_village, "field 'txt_count'", TextView.class);
    target.qr_pratham_id = Utils.findRequiredViewAsType(source, R.id.qr_pratham_id, "field 'qr_pratham_id'", EditText.class);
    target.qr_serialNo = Utils.findRequiredViewAsType(source, R.id.qr_serialNo, "field 'qr_serialNo'", EditText.class);
    target.successMessage = Utils.findRequiredViewAsType(source, R.id.successMessage, "field 'successMessage'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.qr_btn_reset, "method 'resetCamera'");
    view7f0a0221 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.resetCamera();
      }
    });
    view = Utils.findRequiredView(source, R.id.qr_btn_save, "method 'saveTabTrack'");
    view7f0a0222 = view;
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
    AssignFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.qr_frame = null;
    target.village = null;
    target.txt_count = null;
    target.qr_pratham_id = null;
    target.qr_serialNo = null;
    target.successMessage = null;

    view7f0a0221.setOnClickListener(null);
    view7f0a0221 = null;
    view7f0a0222.setOnClickListener(null);
    view7f0a0222 = null;
  }
}
