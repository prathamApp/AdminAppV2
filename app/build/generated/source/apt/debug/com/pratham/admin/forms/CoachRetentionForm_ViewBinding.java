// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.forms;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CoachRetentionForm_ViewBinding implements Unbinder {
  private CoachRetentionForm target;

  private View view7f0a0080;

  private View view7f0a008b;

  @UiThread
  public CoachRetentionForm_ViewBinding(CoachRetentionForm target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CoachRetentionForm_ViewBinding(final CoachRetentionForm target, View source) {
    this.target = target;

    View view;
    target.sp_Village = Utils.findRequiredViewAsType(source, R.id.sp_Village, "field 'sp_Village'", Spinner.class);
    target.sp_SelectCoach = Utils.findRequiredViewAsType(source, R.id.sp_SelectCoach, "field 'sp_SelectCoach'", Spinner.class);
    target.rg_DropOut = Utils.findRequiredViewAsType(source, R.id.rg_DropOut, "field 'rg_DropOut'", RadioGroup.class);
    target.rb_Yes = Utils.findRequiredViewAsType(source, R.id.rb_Yes, "field 'rb_Yes'", RadioButton.class);
    view = Utils.findRequiredView(source, R.id.btn_DatePicker, "field 'btn_DatePicker' and method 'endDatePicker'");
    target.btn_DatePicker = Utils.castView(view, R.id.btn_DatePicker, "field 'btn_DatePicker'", Button.class);
    view7f0a0080 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.endDatePicker(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_Submit, "field 'btn_Submit' and method 'submitForm'");
    target.btn_Submit = Utils.castView(view, R.id.btn_Submit, "field 'btn_Submit'", Button.class);
    view7f0a008b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submitForm(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CoachRetentionForm target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sp_Village = null;
    target.sp_SelectCoach = null;
    target.rg_DropOut = null;
    target.rb_Yes = null;
    target.btn_DatePicker = null;
    target.btn_Submit = null;

    view7f0a0080.setOnClickListener(null);
    view7f0a0080 = null;
    view7f0a008b.setOnClickListener(null);
    view7f0a008b = null;
  }
}
