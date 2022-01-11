// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.forms;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import com.pratham.admin.custom.MultiSpinner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CrlVisitForm_ViewBinding implements Unbinder {
  private CrlVisitForm target;

  private View view7f0a0081;

  private View view7f0a008d;

  @UiThread
  public CrlVisitForm_ViewBinding(CrlVisitForm target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CrlVisitForm_ViewBinding(final CrlVisitForm target, View source) {
    this.target = target;

    View view;
    target.sp_Village = Utils.findRequiredViewAsType(source, R.id.sp_Village, "field 'sp_Village'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.btn_DatePicker, "field 'btn_DatePicker' and method 'visitDatePicker'");
    target.btn_DatePicker = Utils.castView(view, R.id.btn_DatePicker, "field 'btn_DatePicker'", Button.class);
    view7f0a0081 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.visitDatePicker(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_TimeRangePicker, "field 'btn_TimeRangePicker' and method 'TimeRangePicker'");
    target.btn_TimeRangePicker = Utils.castView(view, R.id.btn_TimeRangePicker, "field 'btn_TimeRangePicker'", Button.class);
    view7f0a008d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.TimeRangePicker(p0);
      }
    });
    target.sp_VisitedGroups_multiselect = Utils.findRequiredViewAsType(source, R.id.sp_VisitedGroups_multiselect, "field 'sp_VisitedGroups_multiselect'", MultiSpinner.class);
    target.sp_PresentCoaches_multiselect = Utils.findRequiredViewAsType(source, R.id.sp_PresentCoaches_multiselect, "field 'sp_PresentCoaches_multiselect'", MultiSpinner.class);
    target.sp_CoachesWithGrp_multiselect = Utils.findRequiredViewAsType(source, R.id.sp_CoachesWithGrp_multiselect, "field 'sp_CoachesWithGrp_multiselect'", MultiSpinner.class);
    target.sp_GrpWithTheirGrp_multiselect = Utils.findRequiredViewAsType(source, R.id.sp_GrpWithTheirGrp_multiselect, "field 'sp_GrpWithTheirGrp_multiselect'", MultiSpinner.class);
    target.sp_WorkCrosscheckedGrps_multiselect = Utils.findRequiredViewAsType(source, R.id.sp_WorkCrosscheckedGrps_multiselect, "field 'sp_WorkCrosscheckedGrps_multiselect'", MultiSpinner.class);
    target.edt_PresentStdCount = Utils.findRequiredViewAsType(source, R.id.edt_PresentStdCount, "field 'edt_PresentStdCount'", EditText.class);
    target.btn_Submit = Utils.findRequiredViewAsType(source, R.id.btn_Submit, "field 'btn_Submit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CrlVisitForm target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sp_Village = null;
    target.btn_DatePicker = null;
    target.btn_TimeRangePicker = null;
    target.sp_VisitedGroups_multiselect = null;
    target.sp_PresentCoaches_multiselect = null;
    target.sp_CoachesWithGrp_multiselect = null;
    target.sp_GrpWithTheirGrp_multiselect = null;
    target.sp_WorkCrosscheckedGrps_multiselect = null;
    target.edt_PresentStdCount = null;
    target.btn_Submit = null;

    view7f0a0081.setOnClickListener(null);
    view7f0a0081 = null;
    view7f0a008d.setOnClickListener(null);
    view7f0a008d = null;
  }
}
