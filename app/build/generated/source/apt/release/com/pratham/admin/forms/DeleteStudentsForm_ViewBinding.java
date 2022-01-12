// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.forms;

import android.view.View;
import android.widget.Button;
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

public class DeleteStudentsForm_ViewBinding implements Unbinder {
  private DeleteStudentsForm target;

  private View view7f0a008c;

  @UiThread
  public DeleteStudentsForm_ViewBinding(DeleteStudentsForm target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DeleteStudentsForm_ViewBinding(final DeleteStudentsForm target, View source) {
    this.target = target;

    View view;
    target.sp_Village = Utils.findRequiredViewAsType(source, R.id.sp_Village, "field 'sp_Village'", Spinner.class);
    target.sp_Groups = Utils.findRequiredViewAsType(source, R.id.sp_Groups, "field 'sp_Groups'", Spinner.class);
    target.sp_Students = Utils.findRequiredViewAsType(source, R.id.sp_Students, "field 'sp_Students'", MultiSpinner.class);
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
    DeleteStudentsForm target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.sp_Village = null;
    target.sp_Groups = null;
    target.sp_Students = null;
    target.btn_Submit = null;

    view7f0a008c.setOnClickListener(null);
    view7f0a008c = null;
  }
}
