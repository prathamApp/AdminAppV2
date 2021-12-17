// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import fr.ganfra.materialspinner.MaterialSpinner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ECESampleAssessment_ViewBinding implements Unbinder {
  private ECESampleAssessment target;

  private View view7f0a0080;

  private View view7f0a008c;

  private View view7f0a008b;

  private View view7f0a007f;

  @UiThread
  public ECESampleAssessment_ViewBinding(ECESampleAssessment target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ECESampleAssessment_ViewBinding(final ECESampleAssessment target, View source) {
    this.target = target;

    View view;
    target.sp_Block = Utils.findRequiredViewAsType(source, R.id.sp_Block, "field 'sp_Block'", Spinner.class);
    target.sp_Village = Utils.findRequiredViewAsType(source, R.id.sp_Village, "field 'sp_Village'", Spinner.class);
    target.sp_Groups = Utils.findRequiredViewAsType(source, R.id.sp_Groups, "field 'sp_Groups'", Spinner.class);
    target.sp_existingStudent = Utils.findRequiredViewAsType(source, R.id.sp_existingStudent, "field 'sp_existingStudent'", Spinner.class);
    target.sp_AsmtType = Utils.findRequiredViewAsType(source, R.id.sp_AsmtType, "field 'sp_AsmtType'", Spinner.class);
    target.tv_FirstName = Utils.findRequiredViewAsType(source, R.id.tv_FirstName, "field 'tv_FirstName'", TextView.class);
    target.tv_MiddleName = Utils.findRequiredViewAsType(source, R.id.tv_MiddleName, "field 'tv_MiddleName'", TextView.class);
    target.tv_LastName = Utils.findRequiredViewAsType(source, R.id.tv_LastName, "field 'tv_LastName'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_DatePicker, "field 'btn_DatePicker' and method 'visitDatePicker'");
    target.btn_DatePicker = Utils.castView(view, R.id.btn_DatePicker, "field 'btn_DatePicker'", Button.class);
    view7f0a0080 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.visitDatePicker(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_TimeRangePicker, "field 'btn_TimeRangePicker' and method 'TimeRangePicker'");
    target.btn_TimeRangePicker = Utils.castView(view, R.id.btn_TimeRangePicker, "field 'btn_TimeRangePicker'", Button.class);
    view7f0a008c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.TimeRangePicker(p0);
      }
    });
    target.sp_MatchingCards = Utils.findRequiredViewAsType(source, R.id.sp_MatchingCards, "field 'sp_MatchingCards'", MaterialSpinner.class);
    target.sp_SequencingCards = Utils.findRequiredViewAsType(source, R.id.sp_SequencingCards, "field 'sp_SequencingCards'", MaterialSpinner.class);
    target.sp_NumberRecognition = Utils.findRequiredViewAsType(source, R.id.sp_NumberRecognition, "field 'sp_NumberRecognition'", MaterialSpinner.class);
    target.sp_WordRecognition = Utils.findRequiredViewAsType(source, R.id.sp_WordRecognition, "field 'sp_WordRecognition'", MaterialSpinner.class);
    target.sp_W11a = Utils.findRequiredViewAsType(source, R.id.sp_W11a, "field 'sp_W11a'", MaterialSpinner.class);
    target.sp_W11b = Utils.findRequiredViewAsType(source, R.id.sp_W11b, "field 'sp_W11b'", MaterialSpinner.class);
    target.sp_W12a = Utils.findRequiredViewAsType(source, R.id.sp_W12a, "field 'sp_W12a'", MaterialSpinner.class);
    target.sp_W12b = Utils.findRequiredViewAsType(source, R.id.sp_W12b, "field 'sp_W12b'", MaterialSpinner.class);
    target.sp_OQ11 = Utils.findRequiredViewAsType(source, R.id.sp_OQ11, "field 'sp_OQ11'", MaterialSpinner.class);
    target.sp_OQ12 = Utils.findRequiredViewAsType(source, R.id.sp_OQ12, "field 'sp_OQ12'", MaterialSpinner.class);
    target.sp_OQ13 = Utils.findRequiredViewAsType(source, R.id.sp_OQ13, "field 'sp_OQ13'", MaterialSpinner.class);
    target.sp_OQ14 = Utils.findRequiredViewAsType(source, R.id.sp_OQ14, "field 'sp_OQ14'", MaterialSpinner.class);
    view = Utils.findRequiredView(source, R.id.btn_Submit, "field 'btn_Submit' and method 'save'");
    target.btn_Submit = Utils.castView(view, R.id.btn_Submit, "field 'btn_Submit'", Button.class);
    view7f0a008b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.save();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_Clear, "field 'btn_Clear' and method 'reset'");
    target.btn_Clear = Utils.castView(view, R.id.btn_Clear, "field 'btn_Clear'", Button.class);
    view7f0a007f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.reset(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ECESampleAssessment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sp_Block = null;
    target.sp_Village = null;
    target.sp_Groups = null;
    target.sp_existingStudent = null;
    target.sp_AsmtType = null;
    target.tv_FirstName = null;
    target.tv_MiddleName = null;
    target.tv_LastName = null;
    target.btn_DatePicker = null;
    target.btn_TimeRangePicker = null;
    target.sp_MatchingCards = null;
    target.sp_SequencingCards = null;
    target.sp_NumberRecognition = null;
    target.sp_WordRecognition = null;
    target.sp_W11a = null;
    target.sp_W11b = null;
    target.sp_W12a = null;
    target.sp_W12b = null;
    target.sp_OQ11 = null;
    target.sp_OQ12 = null;
    target.sp_OQ13 = null;
    target.sp_OQ14 = null;
    target.btn_Submit = null;
    target.btn_Clear = null;

    view7f0a0080.setOnClickListener(null);
    view7f0a0080 = null;
    view7f0a008c.setOnClickListener(null);
    view7f0a008c = null;
    view7f0a008b.setOnClickListener(null);
    view7f0a008b = null;
    view7f0a007f.setOnClickListener(null);
    view7f0a007f = null;
  }
}
