// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.forms;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class YouthInfoForm_ViewBinding implements Unbinder {
  private YouthInfoForm target;

  @UiThread
  public YouthInfoForm_ViewBinding(YouthInfoForm target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public YouthInfoForm_ViewBinding(YouthInfoForm target, View source) {
    this.target = target;

    target.edt_Fname = Utils.findRequiredViewAsType(source, R.id.edt_FirstName, "field 'edt_Fname'", EditText.class);
    target.edt_Mname = Utils.findRequiredViewAsType(source, R.id.edt_MiddleName, "field 'edt_Mname'", EditText.class);
    target.edt_Lname = Utils.findRequiredViewAsType(source, R.id.edt_LastName, "field 'edt_Lname'", EditText.class);
    target.edt_GuardianName = Utils.findRequiredViewAsType(source, R.id.edt_GuardianName, "field 'edt_GuardianName'", EditText.class);
    target.edt_PhoneNumber = Utils.findRequiredViewAsType(source, R.id.edt_Phonenumber, "field 'edt_PhoneNumber'", EditText.class);
    target.btn_Submit = Utils.findRequiredViewAsType(source, R.id.btn_Submit, "field 'btn_Submit'", Button.class);
    target.btn_Clear = Utils.findRequiredViewAsType(source, R.id.btn_Clear, "field 'btn_Clear'", Button.class);
    target.btn_BirthDatePicker = Utils.findRequiredViewAsType(source, R.id.btn_BirthDatePicker, "field 'btn_BirthDatePicker'", Button.class);
    target.rg_Gender = Utils.findRequiredViewAsType(source, R.id.rg_Gender, "field 'rg_Gender'", RadioGroup.class);
    target.rg_marital = Utils.findRequiredViewAsType(source, R.id.rg_marital_status, "field 'rg_marital'", RadioGroup.class);
    target.rg_rustudy = Utils.findRequiredViewAsType(source, R.id.rg_rustudy, "field 'rg_rustudy'", RadioGroup.class);
    target.rg_q1 = Utils.findRequiredViewAsType(source, R.id.rg_haveSmartphone, "field 'rg_q1'", RadioGroup.class);
    target.rg_q2 = Utils.findRequiredViewAsType(source, R.id.rg_useSmartphone, "field 'rg_q2'", RadioGroup.class);
    target.rg_q3 = Utils.findRequiredViewAsType(source, R.id.rg_iwanttojoin, "field 'rg_q3'", RadioGroup.class);
    target.rb_Male = Utils.findRequiredViewAsType(source, R.id.rb_Male, "field 'rb_Male'", RadioButton.class);
    target.rb_Female = Utils.findRequiredViewAsType(source, R.id.rb_Female, "field 'rb_Female'", RadioButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    YouthInfoForm target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edt_Fname = null;
    target.edt_Mname = null;
    target.edt_Lname = null;
    target.edt_GuardianName = null;
    target.edt_PhoneNumber = null;
    target.btn_Submit = null;
    target.btn_Clear = null;
    target.btn_BirthDatePicker = null;
    target.rg_Gender = null;
    target.rg_marital = null;
    target.rg_rustudy = null;
    target.rg_q1 = null;
    target.rg_q2 = null;
    target.rg_q3 = null;
    target.rb_Male = null;
    target.rb_Female = null;
  }
}
