// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities.replaceTab;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Fragment_ChooseAssignOrReturn_ViewBinding implements Unbinder {
  private Fragment_ChooseAssignOrReturn target;

  private View view7f0a00d8;

  private View view7f0a0062;

  @UiThread
  public Fragment_ChooseAssignOrReturn_ViewBinding(final Fragment_ChooseAssignOrReturn target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.collect, "method 'onCollectClick'");
    view7f0a00d8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCollectClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.assign, "method 'onAssignClick'");
    view7f0a0062 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAssignClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0a00d8.setOnClickListener(null);
    view7f0a00d8 = null;
    view7f0a0062.setOnClickListener(null);
    view7f0a0062 = null;
  }
}
