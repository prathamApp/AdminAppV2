// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.activities;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ManageDevice_ViewBinding implements Unbinder {
  private ManageDevice target;

  @UiThread
  public ManageDevice_ViewBinding(ManageDevice target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ManageDevice_ViewBinding(ManageDevice target, View source) {
    this.target = target;

    target.btn_assignTablet = Utils.findRequiredViewAsType(source, R.id.btn_assignTablet, "field 'btn_assignTablet'", CardView.class);
    target.returnTablet = Utils.findRequiredViewAsType(source, R.id.returnTablet, "field 'returnTablet'", CardView.class);
    target.btn_replaceTab = Utils.findRequiredViewAsType(source, R.id.btn_replaceTab, "field 'btn_replaceTab'", CardView.class);
    target.acionstatus = Utils.findRequiredViewAsType(source, R.id.acionstatus, "field 'acionstatus'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ManageDevice target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_assignTablet = null;
    target.returnTablet = null;
    target.btn_replaceTab = null;
    target.acionstatus = null;
  }
}
