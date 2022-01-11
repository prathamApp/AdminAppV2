// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.forms;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class CoachInformationForm_ViewBinding implements Unbinder {
  private CoachInformationForm target;

  private View view7f0a0081;

  private View view7f0a008c;

  @UiThread
  public CoachInformationForm_ViewBinding(CoachInformationForm target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CoachInformationForm_ViewBinding(final CoachInformationForm target, View source) {
    this.target = target;

    View view;
    target.sp_Village = Utils.findRequiredViewAsType(source, R.id.sp_Village, "field 'sp_Village'", Spinner.class);
    target.edt_Name = Utils.findRequiredViewAsType(source, R.id.edt_Name, "field 'edt_Name'", EditText.class);
    target.edt_Age = Utils.findRequiredViewAsType(source, R.id.edt_Age, "field 'edt_Age'", EditText.class);
    target.rg_Gender = Utils.findRequiredViewAsType(source, R.id.rg_Gender, "field 'rg_Gender'", RadioGroup.class);
    target.rb_Male = Utils.findRequiredViewAsType(source, R.id.rb_Male, "field 'rb_Male'", RadioButton.class);
    target.sp_Occupation = Utils.findRequiredViewAsType(source, R.id.sp_Occupation, "field 'sp_Occupation'", Spinner.class);
    target.sp_Speciality = Utils.findRequiredViewAsType(source, R.id.sp_Speciality, "field 'sp_Speciality'", Spinner.class);
    target.sp_Education = Utils.findRequiredViewAsType(source, R.id.sp_Education, "field 'sp_Education'", Spinner.class);
    target.sp_SubjectExpert = Utils.findRequiredViewAsType(source, R.id.sp_SubjectExpert, "field 'sp_SubjectExpert'", MultiSpinner.class);
    target.sp_Groups = Utils.findRequiredViewAsType(source, R.id.sp_Groups, "field 'sp_Groups'", MultiSpinner.class);
    view = Utils.findRequiredView(source, R.id.btn_DatePicker, "field 'btn_DatePicker' and method 'startDatePicker'");
    target.btn_DatePicker = Utils.castView(view, R.id.btn_DatePicker, "field 'btn_DatePicker'", Button.class);
    view7f0a0081 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startDatePicker(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_Submit, "field 'btn_Submit' and method 'submitData'");
    target.btn_Submit = Utils.castView(view, R.id.btn_Submit, "field 'btn_Submit'", Button.class);
    view7f0a008c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submitData(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CoachInformationForm target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sp_Village = null;
    target.edt_Name = null;
    target.edt_Age = null;
    target.rg_Gender = null;
    target.rb_Male = null;
    target.sp_Occupation = null;
    target.sp_Speciality = null;
    target.sp_Education = null;
    target.sp_SubjectExpert = null;
    target.sp_Groups = null;
    target.btn_DatePicker = null;
    target.btn_Submit = null;

    view7f0a0081.setOnClickListener(null);
    view7f0a0081 = null;
    view7f0a008c.setOnClickListener(null);
    view7f0a008c = null;
  }
}
