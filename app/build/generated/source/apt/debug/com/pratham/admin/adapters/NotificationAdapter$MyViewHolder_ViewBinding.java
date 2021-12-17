// Generated code from Butter Knife. Do not modify!
package com.pratham.admin.adapters;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pratham.admin.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotificationAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private NotificationAdapter.MyViewHolder target;

  @UiThread
  public NotificationAdapter$MyViewHolder_ViewBinding(NotificationAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    target.nf_date = Utils.findRequiredViewAsType(source, R.id.nf_date, "field 'nf_date'", TextView.class);
    target.nf_content = Utils.findRequiredViewAsType(source, R.id.nf_content, "field 'nf_content'", TextView.class);
    target.nf_damageType = Utils.findRequiredViewAsType(source, R.id.nf_damageType, "field 'nf_damageType'", TextView.class);
    target.upperView = Utils.findRequiredView(source, R.id.iv_upper_view, "field 'upperView'");
    target.lowerView = Utils.findRequiredView(source, R.id.iv_lower_view, "field 'lowerView'");
    target.itemSeperator = Utils.findRequiredView(source, R.id.itemSeperator, "field 'itemSeperator'");
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.nf_date = null;
    target.nf_content = null;
    target.nf_damageType = null;
    target.upperView = null;
    target.lowerView = null;
    target.itemSeperator = null;
  }
}
