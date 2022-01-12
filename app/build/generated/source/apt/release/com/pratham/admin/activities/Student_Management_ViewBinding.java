// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Student_Management_ViewBinding implements Unbinder {
  private Student_Management target;

  @UiThread
  public Student_Management_ViewBinding(Student_Management target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Student_Management_ViewBinding(Student_Management target, View source) {
    this.target = target;

    target.tv_appInfo = Utils.findRequiredViewAsType(source, R.id.tv_appInfo, "field 'tv_appInfo'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Student_Management target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_appInfo = null;
  }
}
