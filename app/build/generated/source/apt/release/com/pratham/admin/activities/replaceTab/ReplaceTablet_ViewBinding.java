// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities.replaceTab;

import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReplaceTablet_ViewBinding implements Unbinder {
  private ReplaceTablet target;

  @UiThread
  public ReplaceTablet_ViewBinding(ReplaceTablet target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReplaceTablet_ViewBinding(ReplaceTablet target, View source) {
    this.target = target;

    target.fragmentLayout = Utils.findRequiredViewAsType(source, R.id.fragmentLayout, "field 'fragmentLayout'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReplaceTablet target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.fragmentLayout = null;
  }
}
