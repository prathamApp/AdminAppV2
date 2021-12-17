// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Dashboard_ViewBinding implements Unbinder {
  private Dashboard target;

  @UiThread
  public Dashboard_ViewBinding(Dashboard target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Dashboard_ViewBinding(Dashboard target, View source) {
    this.target = target;

    target.iv_changeLog = Utils.findRequiredViewAsType(source, R.id.changeLog, "field 'iv_changeLog'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Dashboard target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv_changeLog = null;
  }
}
