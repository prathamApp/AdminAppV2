// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities.Notification;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Notification_ViewBinding implements Unbinder {
  private Notification target;

  @UiThread
  public Notification_ViewBinding(Notification target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Notification_ViewBinding(Notification target, View source) {
    this.target = target;

    target.search = Utils.findRequiredViewAsType(source, R.id.search, "field 'search'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Notification target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.search = null;
  }
}
