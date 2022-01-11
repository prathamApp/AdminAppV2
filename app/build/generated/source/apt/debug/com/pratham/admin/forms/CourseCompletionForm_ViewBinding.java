// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.forms;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class CourseCompletionForm_ViewBinding implements Unbinder {
  private CourseCompletionForm target;

  private View view7f0a0081;

  private View view7f0a0082;

  private View view7f0a008c;

  @UiThread
  public CourseCompletionForm_ViewBinding(CourseCompletionForm target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CourseCompletionForm_ViewBinding(final CourseCompletionForm target, View source) {
    this.target = target;

    View view;
    target.sp_Village = Utils.findRequiredViewAsType(source, R.id.sp_Village, "field 'sp_Village'", Spinner.class);
    target.sp_Groups = Utils.findRequiredViewAsType(source, R.id.sp_Groups, "field 'sp_Groups'", Spinner.class);
    target.rg_Event = Utils.findRequiredViewAsType(source, R.id.rg_Event, "field 'rg_Event'", RadioGroup.class);
    target.rb_Yes = Utils.findRequiredViewAsType(source, R.id.rb_Yes, "field 'rb_Yes'", RadioButton.class);
    target.edt_ParentCount = Utils.findRequiredViewAsType(source, R.id.edt_ParentCount, "field 'edt_ParentCount'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_DatePicker, "field 'btn_DatePicker' and method 'startDatePicker'");
    target.btn_DatePicker = Utils.castView(view, R.id.btn_DatePicker, "field 'btn_DatePicker'", Button.class);
    view7f0a0081 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startDatePicker(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_DatePickerTwo, "field 'btn_DatePickerTwo' and method 'endDatePicker'");
    target.btn_DatePickerTwo = Utils.castView(view, R.id.btn_DatePickerTwo, "field 'btn_DatePickerTwo'", Button.class);
    view7f0a0082 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.endDatePicker(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_Submit, "field 'btn_Submit' and method 'submitForm'");
    target.btn_Submit = Utils.castView(view, R.id.btn_Submit, "field 'btn_Submit'", Button.class);
    view7f0a008c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submitForm(p0);
      }
    });
    target.tv_Course_Warning = Utils.findRequiredViewAsType(source, R.id.tv_Course_Warning, "field 'tv_Course_Warning'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CourseCompletionForm target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sp_Village = null;
    target.sp_Groups = null;
    target.rg_Event = null;
    target.rb_Yes = null;
    target.edt_ParentCount = null;
    target.btn_DatePicker = null;
    target.btn_DatePickerTwo = null;
    target.btn_Submit = null;
    target.tv_Course_Warning = null;

    view7f0a0081.setOnClickListener(null);
    view7f0a0081 = null;
    view7f0a0082.setOnClickListener(null);
    view7f0a0082 = null;
    view7f0a008c.setOnClickListener(null);
    view7f0a008c = null;
  }
}
