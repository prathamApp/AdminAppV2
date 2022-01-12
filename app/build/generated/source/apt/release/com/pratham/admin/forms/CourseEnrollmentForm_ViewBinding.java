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
import com.pratham.admin.custom.MultiSpinner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CourseEnrollmentForm_ViewBinding implements Unbinder {
  private CourseEnrollmentForm target;

  private View view7f0a0081;

  private View view7f0a008c;

  @UiThread
  public CourseEnrollmentForm_ViewBinding(CourseEnrollmentForm target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CourseEnrollmentForm_ViewBinding(final CourseEnrollmentForm target, View source) {
    this.target = target;

    View view;
    target.sp_Village = Utils.findRequiredViewAsType(source, R.id.sp_Village, "field 'sp_Village'", Spinner.class);
    target.sp_Groups = Utils.findRequiredViewAsType(source, R.id.sp_Groups, "field 'sp_Groups'", MultiSpinner.class);
    target.sp_Course = Utils.findRequiredViewAsType(source, R.id.sp_Course, "field 'sp_Course'", Spinner.class);
    target.ms_sp_Topics = Utils.findRequiredViewAsType(source, R.id.ms_sp_Topics, "field 'ms_sp_Topics'", MultiSpinner.class);
    target.sp_SelectCoach = Utils.findRequiredViewAsType(source, R.id.sp_SelectCoach, "field 'sp_SelectCoach'", MultiSpinner.class);
    view = Utils.findRequiredView(source, R.id.btn_DatePicker, "field 'btn_DatePicker' and method 'startDatePicker'");
    target.btn_DatePicker = Utils.castView(view, R.id.btn_DatePicker, "field 'btn_DatePicker'", Button.class);
    view7f0a0081 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startDatePicker(p0);
      }
    });
    target.rg_Community = Utils.findRequiredViewAsType(source, R.id.rg_Community, "field 'rg_Community'", RadioGroup.class);
    target.rb_Yes = Utils.findRequiredViewAsType(source, R.id.rb_Yes, "field 'rb_Yes'", RadioButton.class);
    target.rb_Community = Utils.findRequiredViewAsType(source, R.id.rb_Community, "field 'rb_Community'", RadioButton.class);
    view = Utils.findRequiredView(source, R.id.btn_Submit, "field 'btn_Submit' and method 'submitForm'");
    target.btn_Submit = Utils.castView(view, R.id.btn_Submit, "field 'btn_Submit'", Button.class);
    view7f0a008c = view;
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
    CourseEnrollmentForm target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sp_Village = null;
    target.sp_Groups = null;
    target.sp_Course = null;
    target.ms_sp_Topics = null;
    target.sp_SelectCoach = null;
    target.btn_DatePicker = null;
    target.rg_Community = null;
    target.rb_Yes = null;
    target.rb_Community = null;
    target.btn_Submit = null;

    view7f0a0081.setOnClickListener(null);
    view7f0a0081 = null;
    view7f0a008c.setOnClickListener(null);
    view7f0a008c = null;
  }
}
