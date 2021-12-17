// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CheckInternetDialog_ViewBinding implements Unbinder {
  private CheckInternetDialog target;

  private View view7f0a0094;

  @UiThread
  public CheckInternetDialog_ViewBinding(CheckInternetDialog target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CheckInternetDialog_ViewBinding(final CheckInternetDialog target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_exit, "field 'exit' and method 'onExit'");
    target.exit = Utils.castView(view, R.id.btn_exit, "field 'exit'", Button.class);
    view7f0a0094 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onExit();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CheckInternetDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.exit = null;

    view7f0a0094.setOnClickListener(null);
    view7f0a0094 = null;
  }
}
